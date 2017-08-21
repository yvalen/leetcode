package leetcode.dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WordBreak {
	
	/**
	 * Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, 
	 * determine if s can be segmented into a space-separated sequence of one or more dictionary words. 
	 * You may assume the dictionary does not contain duplicate words.
	 * For example, given s = "leetcode", dict = ["leet", "code"].
	 * Return true because "leetcode" can be segmented as "leet code". 
	 * 
	 * Company: Google, Facebook, Uber, Yahoo, Amazon, Bloomberg, Pocket Gems
	 * Difficulty: medium
	 */
	public boolean wordBreak_recursive(String s, List<String> wordDict) {
		if (s == null || s.length() == 0) return true;
		
		Set<String> dictSet = wordDict.stream().collect(Collectors.toSet());
		return wordBreak_recursive_helper(s, dictSet);
    }
	
	/*
	 * Consider each prefix and search it in dictionary. If the prefix is present in dictionary, 
	 * recur for rest of the string (or suffix). If the recursive call for suffix returns true, we 
	 * return true, otherwise try next prefix. If we have tried all prefixes and none of them resulted 
	 * in a solution, we return false.
	 */
	public boolean wordBreak_recursive_helper(String s, Set<String> dictSet) {
		if (dictSet.contains(s)) return true;
		
		for (int i = 1; i < s.length(); i++) {
			String prefix = s.substring(0, i);
			boolean checkSuffix = wordBreak_recursive_helper(s.substring(i, s.length()), dictSet);
			if (dictSet.contains(prefix) && checkSuffix) return true;
		}
		
		return false;
    }

	// Time complexity: O(n^2), Space complexity: O(n)
	public boolean wordBreak_dp(String s, List<String> wordDict) {
		if (s == null || s.length() == 0) return true;
		
		int n = s.length();
		
		// dp[i] stands for whether substring(0, i) can be segmented
		boolean[] dp = new boolean[n + 1];
		
		// dp[0] is for empty string which should return true 
		dp[0] = true;
		
		// consider substring of all possible lengths starting from the beginning using index i
		// for each such substring partition it into two substring s1 and s2 in all possible ways using index j
		for (int i = 1; i <= n; i++) { // i refers to the length of the substring considered so far from the beginning, end index of s2
			for (int j = 0; j < i; j++) { // j refers to the index partitioning the current substring into smaller substring (0,j) and (j+1,i)
				if (dp[j] && wordDict.contains(s.substring(j, i))) {
					dp[i] = true;  // both substring are in the dictionary
					break;
				}
			}
		}
		return dp[n];
	}
	
	
	/**
	 * Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, 
	 * add spaces in s to construct a sentence where each word is a valid dictionary word. 
	 * You may assume the dictionary does not contain duplicate words. Return all such possible sentences.
	 * For example, given s = "catsanddog", dict = ["cat", "cats", "and", "sand", "dog"].
	 * A solution is ["cats and dog", "cat sand dog"]. 
	 * 
	 * Company: Dropbox, Google, Uber, Snapchat, Twitter
	 * Difficulty: hard
	 */
	public List<String> wordBreakII(String s, List<String> wordDict) {
		List<String> result = new ArrayList<>();
		
		if (s == null || s.length() == 0) return result;
		
		int n = s.length();
		List<String>[] dp = (List<String>[]) new List[n+1];
		dp[0] = new ArrayList<>();
		
		for (int i = 1; i <= n; i++) {
			for (int j = 0; j < i; j++) {
				String substr = s.substring(j, i);
				if (dp[j] != null  && wordDict.contains(substr)) {
					if (dp[i] == null) dp[i] = new ArrayList<String>();
					dp[i].add(substr);
				}
			}
		}
		
		if (dp[n] == null) return result;
		dfs(dp, s.length(), result, new ArrayList<>());
		
		return result;
    }
	
	private void dfs(List<String>[] dp, int end, List<String> result, List<String> backtrack) {
		if (end <= 0) {
			String str = backtrack.get(backtrack.size() - 1);
			for (int i = backtrack.size() - 2; i >= 0; i--) {
				str += " " + backtrack.get(i);
			}
			result.add(str);
			return;
		}
		
		for (String last : dp[end]) {
			backtrack.add(last);
			dfs(dp, end-last.length(), result, backtrack);
			backtrack.remove(backtrack.size()-1);
		}
	}
	
	// check every possible prefix of the string s. If it is found in wordDict
	public List<String> wordBreakII_bruteforce(String s, List<String> wordDict) {
		return wordBreakII_bruteforce(s, wordDict, 0);
	}
	private List<String> wordBreakII_bruteforce(String s, List<String> wordDict, int start) {
		List<String> result = new ArrayList<>();
		if (start == s.length()) {
			result.add("");
		}

		for (int end = start + 1; end <= s.length(); end++) {
			String prefix = s.substring(start, end);
			if (wordDict.contains(prefix)) {
				List<String> list = wordBreakII_bruteforce(s, wordDict, end);
				for (String l : list) {
					result.add(prefix + (l.equals("") ? "" : " ") + l);
				}
			}
		}
		return result;
	}
	
	Map<Integer, List<String>> map = new HashMap<>(); // key is the current start
	public List<String> wordBreakII_memorization(String s, List<String> wordDict) {
		return wordBreakII_memorization(s, wordDict, 0);
	}
	private List<String> wordBreakII_memorization(String s, List<String> wordDict, int start) {
		if (map.containsKey(start)) return map.get(start);
		
		List<String> result = new ArrayList<>();
		if (start == s.length()) {
			result.add("");
		}

		for (int end = start + 1; end <= s.length(); end++) {
			String prefix = s.substring(start, end);
			if (wordDict.contains(prefix)) {
				List<String> list = wordBreakII_bruteforce(s, wordDict, end);
				for (String l : list) {
					result.add(prefix + (l.equals("") ? "" : " ") + l);
				}
			}
		}
		map.put(start, result);
		return result;
	}
	
	public static void main(String[] arg) {
		WordBreak w = new WordBreak();
		
		String s = "catsanddog";
		List<String> wordDict = Arrays.asList("cat", "cats", "and", "sand", "dog");
		
		//String s = "aaaaaaa";
		//List<String> wordDict = Arrays.asList("aaaa","aa");
		
		//String s = "a";
		//List<String> wordDict = Arrays.asList("a");
		
		List<String> result = w.wordBreakII_memorization(s, wordDict);
		System.out.println(result);	
	}
}
