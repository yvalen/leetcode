package leetcode.hashtable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class LongestSubstringWithDistinctCharacters {
    // maintain a sliding window with 2 unique characters, store the last
    // occurrence of each character as the value in the hashmap. 
    // When the size of the hashmap exceeds 2, we can traverse through the map to
    // find the character with the left most index, and remove 1 character from
    // our map. Since the range of characters is constrained, we should be able to 
    // find the left most index in constant time.

    /*
     * LEETCODE 159
     * Given a string, find the length of the longest substring T 
     * that contains at most 2 distinct characters.
     * For example, Given s = “eceba”, T is "ece" which its length is 3.
     * 
     * Company: Google
     * Difficulty: hard
     * Similar Questions: 3(LongestNonReaptingSubstring), 
     */
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        if (s == null || s.isEmpty())
            return 0;

        Map<Character, Integer> map = new HashMap<>();
        int len = 0;
        for (int i = 0, j = 0; j < s.length(); j++) {
            char c = s.charAt(j);
            if (map.size() <= 2) {
                map.put(c, j);
            }

            if (map.size() > 2) {
                int leftMost = s.length();
                for (int idx : map.values()) {
                    leftMost = Math.min(leftMost, idx);
                }
                // need to remove the char at leftMost from map to maintain the
                // map size of 2
                map.remove(s.charAt(leftMost));
                i = leftMost + 1;
            }
            len = Math.max(len, j - i + 1);
        }

        return len;
    }

    public int lengthOfLongestSubstringTwoDistinct_withArray(String s) {
        if (s == null || s.isEmpty())
            return 0;

        int[] dict = new int[256]; // store the last occurrence index for all
                                   // possible ascii chars
        int len = 0, distinctCount = 0;
        for (int i = 0, j = 0; j < s.length(); j++) {
            char c = s.charAt(j);
            dict[c]++; // increment the occurrence count
            if (dict[c] == 1) { // new character
                distinctCount++;
                if (distinctCount > 2) {
                    while (--dict[s.charAt(i++)] > 0)
                        ;
                    distinctCount--;
                }
                /*
                 * while (distinctCount > 2) { // use while loop to check the
                 * count here to handle duplicate char // update start position
                 * i dict[s.charAt(i)]--; if (dict[s.charAt(i)] == 0) {
                 * distinctCount--; // update count when all duplicate chars are
                 * consumed } i++; }
                 */
            }
            len = Math.max(len, j - i + 1);
        }

        return len;
    }

    /*
     * Given a string, find the length of the longest substring T that contains
     * at most k distinct characters. For example, Given s = “eceba” and k = 2,
     * T is "ece" which its length is 3.
     */
    // Time complexity: O(kn)
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        if (s == null || s.isEmpty() || k <= 0)
            return 0;

        Map<Character, Integer> map = new HashMap<>();
        int len = 0;
        for (int i = 0, j = 0; j < s.length(); j++) {
            char c = s.charAt(j);
            if (map.size() <= k) {
                map.put(c, j);
            }
            if (map.size() > k) {
                int leftMost = s.length();
                for (int idx : map.values()) {
                    leftMost = Math.min(leftMost, idx);
                }
                map.remove(s.charAt(leftMost));
                i = leftMost + 1;
            }
            len = Math.max(len, j - i + 1);
        }
        return len;
    }

    public static void main(String[] args) {
        LongestSubstringWithDistinctCharacters lstd = new LongestSubstringWithDistinctCharacters();
        // String s = "eceba";
        String s = "cdaba";
        System.out.println(lstd.lengthOfLongestSubstringTwoDistinct_withArray(s));
    }
}
