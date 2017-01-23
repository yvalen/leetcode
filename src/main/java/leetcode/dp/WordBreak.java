package leetcode.dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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

	public boolean wordBreak_dp(String s, List<String> wordDict) {
		if (s == null || s.length() == 0) return true;
		
		int n = s.length();
		
		// dp[i] stands for whether substring(0, i) can be segmented
		boolean[] dp = new boolean[n + 1];
		
		// dp[0] is for empty string which should return true 
		dp[0] = true;
		
		for (int i = 1; i <= n; i++) {
			for (int j = 0; j < i; j++) {
				if (dp[j] && wordDict.contains(s.substring(j, i))) {
					dp[i] = true;
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
				if (wordDict.contains(substr)) {
					if (dp[i] == null) dp[i] = new ArrayList<String>();
					dp[i].add(substr);
				}
			}
		}
		
		//Stream.of(dp).forEach(System.out::println);
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
		}
		
		for (String last : dp[end]) {
			backtrack.add(last);
			dfs(dp, end-last.length(), result, backtrack);
			backtrack.remove(backtrack.size()-1);
		}
	}
	
	public static void main(String[] arg) {
		WordBreak w = new WordBreak();
		
		String s = "catsanddog";
		List<String> wordDict = Arrays.asList("cat", "cats", "and", "sand", "dog");
		
		List<String> result = w.wordBreakII(s, wordDict);
		System.out.println(result);	
	}
}
