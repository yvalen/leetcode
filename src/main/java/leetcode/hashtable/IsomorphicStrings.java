package leetcode.hashtable;

import java.util.HashMap;
import java.util.Map;

/*
 * LEETCODE 205
 * Given two strings s and t, determine if they are isomorphic. Two strings are 
 * isomorphic if the characters in s can be replaced to get t. All occurrences 
 * of a character must be replaced with another character while preserving the 
 * order of characters. No two characters may map to the same character but a 
 * character may map to itself.
 * For example,
 * Given "egg", "add", return true.
 * Given "foo", "bar", return false.
 * Given "paper", "title", return true.
 * Note: You may assume both s and t have the same length.
 * 
 * Company: LinkedIn
 * Difficulty: easy
 * Similar Questions: 290(WordPattern)
 */
public class IsomorphicStrings {
    public boolean isIsomorphic_withMap(String s, String t) {
        Map<Character, Integer> mapS = new HashMap<>();
        Map<Character, Integer> mapT = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            char c1 = s.charAt(i);
            char c2 = t.charAt(i);
            // compare the last position of the current character, they need to
            // be the same
            // should use equal here instead of !=
            if (!mapS.getOrDefault(c1, -1).equals(mapT.getOrDefault(c2, -1)))
                return false;
            mapS.put(c1, i);
            mapT.put(c2, i);
        }
        return true;
    }

    public boolean isIsomorphic(String s, String t) {
        int[] m1 = new int[256];
        int[] m2 = new int[256];

        for (int i = 0; i < s.length(); i++) {
            int idx1 = s.charAt(i) - '0';
            int idx2 = t.charAt(i) - '0';
            if (m1[idx1] != m2[idx2]) {
                return false;
            }
            // need to use i+1 since array is initialized with 0
            m1[idx1] = i + 1; 
            m2[idx2] = i + 1;
        }
        return true;
    }

    public static void main(String[] args) {
        IsomorphicStrings is = new IsomorphicStrings();
        String s = "aba", t = "baa";
        System.out.println(is.isIsomorphic_withMap(s, t));

    }

}
