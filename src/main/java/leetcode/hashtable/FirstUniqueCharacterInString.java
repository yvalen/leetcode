package leetcode.hashtable;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/*
 * LEETCODE 387
 * Given a string, find the first non-repeating character in it and return it's index. 
 * If it doesn't exist, return -1.
 * Examples: 
 * s = "leetcode" return 0.
 * s = "loveleetcode", return 2.
 * Note: You may assume the string contain only lower case letters. 
 * 
 * Company: Google, Amazon, Microsoft, Bloomberg
 * Difficulty: easy
 * Similar Questions: 451(SortCharactersByFrequency)
 */
public class FirstUniqueCharacterInString {
    public int firstUniqChar_onePass(String s) {
        if (s == null || s.isEmpty())
            return -1;

        Map<Character, Integer> map = new LinkedHashMap<>();
        Set<Character> set = new HashSet<>();
        for (int i = 0; i < s.length(); i++) {
            Character c = s.charAt(i);
            if (set.contains(s.charAt(c))) {
                map.remove(c);
            }
            else {
                set.add(c);
                map.put(c, i);
            }
        }
        return map.size() == 0 ? -1 : map.entrySet().iterator().next().getValue();
        /*
        int[] count = new int[26];
        int[] position = new int[26];
        int result = s.length();
        Arrays.fill(position, -1);
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            count[c - 'a']++;
            if (position[c - 'a'] == -1) {
                position[c - 'a'] = i;
            }
        }

        for (int i = 0; i < 26; i++) {
            if (count[i] == 1) {
                result = Math.min(result, position[i]);
            }
        }

        return result == s.length() ? -1 : result;
        */
    }
    
    public int firstUniqChar_withArray(String s) {
        int[] charCount = new int[26];
        for (char c : s.toCharArray()) {
            charCount[c-'a']++;
        }
        
        for (int i = 0; i < s.length(); i++) {
            if (charCount[s.charAt(i)-'a'] == 1) return i;
        }
        return -1;
    }
    
    public int firstUniqChar_withMap(String s) {
        if (s == null || s.isEmpty()) return -1;
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (map.containsKey(c)) {
                //if (map.get(c) != -1) {
                    map.put(c, -1);
                //}
            }
            else {
                map.put(c, i);
            }
        }
        int result = s.length();
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            if (entry.getValue() != -1) {
                result = Math.min(result, entry.getValue());
            }
        }
        return result == s.length() ? -1 : result;
    }
    
    public static void main(String[] args) {
        FirstUniqueCharacterInString fu = new FirstUniqueCharacterInString();
        String s = "aadadaad";
        System.out.println(fu.firstUniqChar_withMap(s));
    }
}
