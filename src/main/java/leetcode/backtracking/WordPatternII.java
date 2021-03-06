package leetcode.backtracking;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/*
 * LEETCODE 291
 * Given a pattern and a string str, find if str follows the same pattern. 
 * Here follow means a full match, such that there is a bijection between a 
 * letter in pattern and a non-empty substring in str.
 * Examples:
 * pattern = "abab", str = "redblueredblue" should return true.
 * pattern = "aaaa", str = "asdasdasdasd" should return true.
 * pattern = "aabb", str = "xyzabcxzyabc" should return false.
 * Notes: You may assume both pattern and str contains only lower case letters. 
 * 
 * Company: Dropbox, Uber
 * Difficulty: hard
 * Similar Questions: 290(WordPattern)
 */
public class WordPatternII {
    /*
     * use a character in the pattern to match different length of substrings in the
     * input string, keep trying till we go through the input string and the pattern.
     * Time complexity: string length is n, and pattern string length is m, the problem 
     * is more like slicing the string into m pieces. How many slicing ways? C(n^m). 
     * For each slice, it takes O(n) to validate. So the total complexity is O(n * C(n^m))
     */
    public boolean wordPatternMatch(String pattern, String str) {
        return isMatch(pattern, str, 0, 0, new HashMap<>(), new HashSet<>());
    }

    private boolean isMatch(String pattern, String str, int i, int j, Map<Character, String> map, Set<String> matched) {
        if (i == pattern.length()) return j == str.length();

        // current char to match
        Character c = pattern.charAt(i);

        // optimization: pruning based on the remaining length
        int remainingLen = str.length() - j, len = 0;
        for (int k = i; k < pattern.length(); k++) {
            len += map.containsKey(c) ? map.get(c).length() : 1;
            if (len > remainingLen)
                return false;
        }

        // current pattern char has been matched before
        if (map.containsKey(c)) {
            String s = map.get(c);

            // optimization
            return s.length() <= str.length() - j && str.startsWith(s, j)
                    && isMatch(pattern, str, i + 1, j + s.length(), map, matched);

            /*
             * if (!str.startsWith(s, j)) { // str has to start with the string
             * that matches c starting at position j return false; } return
             * isMatch(pattern, str, i+1, j+s.length(), map, set);
             */
        }

        // first time match for c
        // for (int k = j; k < str.length(); k++) {
        for (int k = j; k <= str.length() - (pattern.length() - i); k++) { 
            // k is the end position of the substring
            // try to match c with substring starting at j
            String s = str.substring(j, k + 1); 

            // s has been matched with other character in pattern, it cannot be
            // matched to c
            if (matched.contains(s))
                continue;

            // update map and set
            matched.add(s);
            map.put(c, s);

            if (isMatch(pattern, str, i + 1, k + 1, map, matched)) {
                // return true if the rest matches, match str from k+1
                return true;
            }

            // back track for the next sub string
            map.remove(c);
            matched.remove(s);
        }

        return false;
    }

    public static void main(String[] args) {
        WordPatternII wp = new WordPatternII();
        String pattern = "d", str = "e";
        System.out.println(wp.wordPatternMatch(pattern, str));
    }
}
