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
	
	List<List<String>>[] resultAry;
	public List<List<String>> partition_iterative(String s) {
		resultAry = new List[s.length()+1];
		resultAry[0] = new ArrayList<>();
		resultAry[0].add(new ArrayList<>());
		
		for (int i = 0; i < s.length(); i++) {
			List<List<String>> result = new ArrayList<>();
			for (int j = i; j >= 0; j--) {
				List<List<String>> pre = resultAry[j];
				if (isPalindrome(s, i, j)) {
					for (List<String> l : pre) {	
						List<String> list = new ArrayList<>(l);
						list.add(s.substring(i, j+1));
						result.add(list);
					}
				}
			}
			resultAry[i+1] = result;
		}
		
		return resultAry[s.length()];
	}
	
	public static void main(String[] args) {
		PalindromePartitioning p = new PalindromePartitioning();
		String s = "aab";
		System.out.println(p.partition_iterative(s));
	}

}
