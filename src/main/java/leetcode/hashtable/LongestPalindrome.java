package leetcode.hashtable;

import java.util.HashSet;
import java.util.Set;

/*
 * LEETCODE 409
 * Given a string which consists of lowercase or uppercase letters, find the length 
 * of the longest palindromes that can be built with those letters.
 * This is case sensitive, for example "Aa" is not considered a palindrome here.
 * Note: Assume the length of given string will not exceed 1,010.
 * Example:
 * Input: "abccccdd"
 * Output: 7
 * Explanation: One longest palindrome that can be built is "dccaccd", whose length is 7.
 * 
 * Company: Google
 * Difficulty: easy
 * Similar Questions: 266(PalindromePermutation)
 */
public class LongestPalindrome {
    // count how many letters appear an odd number of times. 
    // we can use all letters, except for each odd-count letter we must leave one, 
    // except one of them we can use.
    public int longestPalindrome_count(String s) {
        if (s == null || s.isEmpty()) return 0;
        
        // https://www.cs.cmu.edu/~pattis/15-1XX/common/handouts/ascii.html
        // A-Z: 65-90, a-z: 97-122
        int[] charCount = new int[128];
        int oddCount = 0;
        for (char c : s.toCharArray()) {
            oddCount += (++charCount[c] % 2 == 1 ? 1 : -1);
        }
        
        return s.length() - oddCount + (oddCount > 0 ? 1 : 0);
    }
    
    public int longestPalindrome_set(String s) {
        if (s == null || s.isEmpty()) return 0;
        
        Set<Character> charSet = new HashSet<>();
        int count = 0;
        for (char c : s.toCharArray()) {
            if (charSet.contains(c)) {
                charSet.remove(c);
                count++;
            }
            else charSet.add(c);
        }
        
        return charSet.isEmpty() ? count * 2 : count * 2 + 1;
    }

    public static void main(String[] args) {
        LongestPalindrome lp = new LongestPalindrome();
        String s = "abccccdd";
        //String s = "ccc";
        System.out.println(lp.longestPalindrome_set(s));
    }
}
