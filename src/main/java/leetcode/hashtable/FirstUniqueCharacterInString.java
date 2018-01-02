package leetcode.hashtable;

import java.util.Arrays;

/*
 * LEETCODE 387
 * Given a string, find the first non-repeating character in it and return it's index. If it doesn't exist, return -1.
 * Examples: 
 * s = "leetcode" return 0.
 * s = "loveleetcode", return 2.
 * Note: You may assume the string contain only lower case letters. 
 * 
 * Company: Amazon, Microsoft, Bloomberg
 * Difficulty: easy
 * Similar Questions: 451(SortCharactersByFrequency)
 */
public class FirstUniqueCharacterInString {
    public int firstUniqChar(String s) {
        if (s == null || s.isEmpty())
            return -1;

        int[] count = new int[26];
        int[] position = new int[26];
        int result = s.length();
        Arrays.fill(position, -1);
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            count[c - 'a']++;
            if (position[c - 'a'] == -1)
                position[c - 'a'] = i;
        }

        for (int i = 0; i < 26; i++) {
            if (count[i] == 1) {
                result = Math.min(result, position[i]);
            }
        }

        return result == s.length() ? -1 : result;
    }
}
