package leetcode.hashtable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
 * LEETCODE 438
 * Given a string s and a non-empty string p, find all the start indices of p's anagrams in s.
 * Strings consists of lower case English letters only and the length of both strings s and p 
 * will not be larger than 20,100. The order of output does not matter.
 * Example 1:
 * Input: s: "cbaebabacd" p: "abc"
 * Output: [0, 6]
 * Explanation: The substring with start index = 0 is "cba", which is an anagram of "abc".
 * The substring with start index = 6 is "bac", which is an anagram of "abc".
 * Example 2:
 * Input: s: "abab" p: "ab"
 * Output: [0, 1, 2]
 * Explanation: The substring with start index = 0 is "ab", which is an anagram of "ab".
 * The substring with start index = 1 is "ba", which is an anagram of "ab".
 * The substring with start index = 2 is "ab", which is an anagram of "ab".
 * 
 * Company: Amazon
 * Difficulty: easy
 * Similar Questions: 242(ValidAnagram), 567(PermutationInString)
 */
public class FindAllAnagrams {
    // https://discuss.leetcode.com/topic/30941/here-is-a-10-line-template-that-can-solve-most-substring-problems
    // Time Complexity will be O(n) because left and right will only move from left to right once.
    public List<Integer> findAnagrams(String s, String p) {
        if (s == null || s.isEmpty()) return Collections.emptyList();
        
        int[] charCount = new int[26];
        for (char c : p.toCharArray()) {
            charCount[c-'a']++;
        }
        
        List<Integer> result = new ArrayList<>();
        int left = 0, right = 0, count = p.length();
        while (right < s.length()) {
            // move right every time, decrement count if current char exists in p
            if (charCount[s.charAt(right++)-'a']-- > 0) {
                count--;
            }
            if (count == 0) result.add(left); 
            
            // when window size equals to p, move left to find the new window
            // also increment charCount for the old left as it was decremented previously
            if (right - left == p.length()) {
                if (charCount[s.charAt(left)-'a'] >= 0) {
                    // increment count if the character is in p
                    count++;
                }
                charCount[s.charAt(left++)-'a']++;
            }
        }
        
        /*
        for (int i = 0; i <= s.length() - p.length(); i++) {
            int[] charCount = new int[26];
            for (char c : p.toCharArray()) {
                charCount[c-'a']++;
            }
            int j = 0;
            while (j < p.length()) {
                if (--charCount[s.charAt(i+j)-'a'] < 0) break;
                j++;
            }
            if (j == p.length()) result.add(i);
        }*/
        return result;
    }
    
    public static void main(String[] args) {
        FindAllAnagrams faa = new FindAllAnagrams();
        //String s = "cbaebabacd", p = "abc";
        //String s = "abab", p = "ab";
        String s = "abcabc", p = "abc";
        System.out.println(faa.findAnagrams(s, p));
    }
}
