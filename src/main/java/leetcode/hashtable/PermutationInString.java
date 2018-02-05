package leetcode.hashtable;

/*
 * LEETCODE 567
 * Given two strings s1 and s2, write a function to return true if s2 contains the permutation of s1. 
 * In other words, one of the first string's permutations is the substring of the second string.
 * Example 1:
 * Input:s1 = "ab" s2 = "eidbaooo"
 * Output:True
 * Explanation: s2 contains one permutation of s1 ("ba").
 * Example 2:
 * Input:s1= "ab" s2 = "eidboaoo"
 * Output: False
 * Note:
 * - The input strings only contain lower case letters.
 * - The length of both given strings is in range [1, 10,000].
 * 
 * Company: Microsoft
 * Difficulty: medium
 * Similar Questions: 76(MinimumWindowSubstring), 438(FindAllAnagrams)
 */
public class PermutationInString {
    public boolean checkInclusion(String s1, String s2) {
        if (s2 == null || s2.isEmpty()) return s1 == null || s1.isEmpty();
        if (s1 == null || s1.isEmpty()) return true;
        if (s1.length() > s2.length()) return false;
        
        int[] charCount = new int[128];
        for (char c : s1.toCharArray()) charCount[c]++;
        
        int start = 0, end = 0, count = s1.length();
        while (end < s2.length()) {
            if (charCount[s2.charAt(end++)-'a']-- > 0) {
                count--;
            }
            while (count == 0) {
                if (end-start == s1.length()) return true;
                if (charCount[s2.charAt(start++)-'a']++ >= 0) {
                    count++;
                }
            }
        }
        
        /*
        int left = 0, right = 0, count = s1.length();
        while (right < s2.length()) {
            if (charCount[s2.charAt(right++)]-- > 0) count--;
            if (count == 0) return true;
            if (right - left == s1.length()) {
                if (charCount[s2.charAt(left)] >= 0) count++; 
                charCount[s2.charAt(left++)]++;
            }
        }*/
        
        return false;
    }

    public static void main(String[] args) {
        PermutationInString ps = new PermutationInString();
        //String s1 = "ab", s2 = "eidbaooo";
        String s1= "ab", s2 = "eidboaoo";
        System.out.println(ps.checkInclusion(s1, s2));
    }
}
