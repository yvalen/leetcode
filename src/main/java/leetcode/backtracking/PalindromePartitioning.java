package leetcode.backtracking;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class PalindromePartitioning {
	/*
	 * Given a string s, partition s such that every substring of the partition is a palindrome.
	 * Return all possible palindrome partitioning of s.
	 * For example, given s = "aab", return
	 * [
	 * 	["aa","b"],
	 * 	["a","a","b"]
	 * ]
	 */
	// Time Complexity: O(2^n)
	// T(n) = T(n-1)+T(n-2)+...+T(1)
	// T(n+1) = T(n)+T(n-1)+T(n-2)+...+T(1)
	// T(n+1) = 2T(n)
	public List<List<String>> partition(String s) {
		List<List<String>> result = new ArrayList<>();
        helper(s, result, 0, new LinkedList<>());
		return result;
    }
	
	private void helper(String s, List<List<String>> result, int start, LinkedList<String> list) {
		if (start == s.length()) {
			result.add(new ArrayList<>(list));
			return;
		}
		
		for (int i = start; i < s.length(); i++) {
			if (isPalindrome(s, start, i)) {
				list.add(s.substring(start, i+1));
				helper(s, result, i+1, list);
				list.removeLast();
			}
		}	
	}
	
	private boolean isPalindrome(String s, int start, int end) {
		while (start < end) {
			if (s.charAt(start) != s.charAt(end)) return false;
			start++;
			end--;
		}
		return true;
	}
	
	public List<List<String>> partition_iterative(String s) {
		List<List<String>>[] resultAry = new List[s.length()+1];
		resultAry[0] = new ArrayList<>();
		resultAry[0].add(new ArrayList<>());
		
		for (int i = 0; i < s.length(); i++) {
			List<List<String>> result = new ArrayList<>();
			for (int j = i; j >= 0; j--) {
				List<List<String>> pre = resultAry[j];
				if (isPalindrome(s, j, i)) {
					for (List<String> l : pre) {	
						List<String> list = new ArrayList<>(l);
						list.add(s.substring(j, i+1));
						result.add(list);
					}
				}
			}
			resultAry[i+1] = result;
		}
		
		return resultAry[s.length()];
	}
	
	public List<List<String>> partition_iterativeDP(String s) {
		int n = s.length();
		// dp[i][j] whether s(i...j) is palindrome
		// dp[left,right] = true if right-left<=1 or s[left] == s[right] && dp[left+1,right-1]
		boolean[][] dp = new boolean[n][n];
		
		List<List<String>>[] results = new List[n+1];
		results[0] = new ArrayList<>();
		results[0].add(new ArrayList<>());
		
		// Check each suffix of the given string, if the suffix is a palindrome, 
		// add it to each solution for subproblem of the matching prefix, else skip it.
		// result[0..right] = result[0..left-1] + s[left..right] if s[left..right] is a palindrome
		
		for (int right = 0;  right < n; right++) {
			results[right+1] = new ArrayList<>();
			for (int left = 0; left <= right; left++) {
				if (s.charAt(left) == s.charAt(right) && (right-left <= 1 || dp[left+1][right-1])) {
					dp[left][right] = true;
					String substr = s.substring(left, right+1);
					for (List<String> prev : results[left]) {
						List<String> list = new ArrayList<>(prev);
						list.add(substr);
						results[right+1].add(list);
					}
					
				}
			}
		}
		
		return results[s.length()];
	}
	
	public static void main(String[] args) {
		PalindromePartitioning p = new PalindromePartitioning();
		String s = "aab";
		System.out.println(p.partition_iterativeDP(s));
	}

}
