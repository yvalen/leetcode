package leetcode.dp;

import leetcode.matrix.MatrixUtil;

/*
 * Given a string, your task is to count how many palindromic substrings in this string. The substrings with different 
 * start indexes or end indexes are counted as different substrings even they consist of same characters.
 * Example 1: Input: "abc" Output: 3
 * Explanation: Three palindromic strings: "a", "b", "c".
 * Example 2: Input: "aaa" Output: 6
 * Explanation: Six palindromic strings: "a", "a", "a", "aa", "aa", "aaa".
 * Note: The input string length won't exceed 1000.
 * 
 * Company: LinkedIn
 * Difficulty: medium
 */
public class PalindromicSubstring {
	// Time complexity: O(n^3), Space complexity: O(1)
	public int countSubstrings_bruteforce(String s) {
		if (s == null || s.length() == 0) return 0;
		
        int count = 0, n = s.length();
        for (int start = 0; start < n; start++) {
        	for (int end = start+1; end <= n; end++) {
        		if (isPalindrome(s.substring(start, end))) {
        			count++;
        		}
        	}
        }
        return count;
    }
	
	// Time complexity: O(n^2), Space complexity: O(n^2)
	public int countSubstrings_dp(String s) {
		if (s == null || s.length() == 0) return 0;
		
        int n = s.length(), count = 0;
        boolean[][] dp = new boolean[n][n];     
        for (int start = n-1; start >= 0; start--) {
        	for (int end = start ; end < n; end++) {
        		if (s.charAt(start) == s.charAt(end) && 
        				(end <= start+1 || dp[start+1][end-1])) {
        			dp[start][end] = true;
        			count++;
        		}
        	}
        }
        return count;
    }
	
	// There are 2N-1 possible centers for the palindrome: we could have a center at S[0], 
	// between S[0] and S[1], at S[1], between S[1] and S[2], at S[2], etc.
	// Time complexity: O(n^2), Space complexity: O(1)
	private int count;
	public int countSubstrings_expandAroundCenter(String s) {
		if (s == null || s.length() == 0) return 0;
		
		for (int i = 0 ; i < s.length(); i++) {
			expandAroundCenter(s, i, i);  // check odd length, center at s[i]
			expandAroundCenter(s, i, i+1);  // check event length, center between s[i] and s[i+1]
		}
        return count;
    }
	private void expandAroundCenter(String s, int left, int right) {
		while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
			left--;
			right++;
			count++;
		}
	}
	

	private boolean isPalindrome(String s) {
		for (int i = 0, j = s.length() -1; i < j; i++, j--) {
			if (s.charAt(i) != s.charAt(j)) return false;
		}
		return true;
	}
	
	public static void main(String[] args) {
		PalindromicSubstring ps = new PalindromicSubstring();
		//String s = "abc";
		//String s = "aaa";
		String s = "aaaaa";
		System.out.println(ps.countSubstrings_expandAroundCenter(s));
	}
}
