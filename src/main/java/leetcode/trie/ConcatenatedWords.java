package leetcode.trie;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
 * Given a list of words (without duplicates), please write a program that returns all concatenated words in the given list of words.
 * A concatenated word is defined as a string that is comprised entirely of at least two shorter words in the given array.
 * Example:
 * Input: ["cat","cats","catsdogcats","dog","dogcatsdog","hippopotamuses","rat","ratcatdogcat"]
 * Output: ["catsdogcats","dogcatsdog","ratcatdogcat"]
 * Explanation: "catsdogcats" can be concatenated by "cats", "dog" and "cats"; "dogcatsdog" can be concatenated by "dog", "cats" and "dog"; 
 * "ratcatdogcat" can be concatenated by "rat", "cat", "dog" and "cat".
 * Note:
 * - The number of elements of the given array will not exceed 10,000
 * - The length sum of elements in the given array will not exceed 600,000.
 * - All the input string will only include lower case letters.
 * - The returned elements order does not matter.
 * 
 */
public class ConcatenatedWords {
	public List<String> findAllConcatenatedWordsInADict_dp(String[] words) {
		if (words == null || words.length <= 1) return Collections.emptyList();
		
		// sort words by its length as a concatenated word can only be formed by shorter words
		// after sorting only need to try words in front of it
		Arrays.sort(words, (a, b) -> a.length() - b.length());
		
		List<String> result = new ArrayList<>();
		Set<String> previousWords = new HashSet<>();
		for (String word : words) {
			if (canForm(word, previousWords)) result.add(word);
			previousWords.add(word);
		}
		return result;
    }
	
	// returns true if s can be formed by words in dict, similar to word break
	private boolean canForm(String s, Set<String> dict) {
		if (dict.isEmpty()) return false; // need to have this check for "". without it "" will be included in the result   
		
		int n = s.length();
		boolean[] dp = new boolean[n+1];
		dp[0] = true;
		for (int i = 1; i <= n; i++) {
			for (int j = 0; j < i; j++) {
				if (dp[j] && dict.contains(s.substring(j, i))) {
					dp[i] = true;
					break;
				}
			}
		}
		return dp[n];
	}
	
	//
	// Use Trie
	//
	private static class TrieNode {
		private String word;
		private TrieNode[] next = new TrieNode[26];
	}
	
	private TrieNode buildTrie(String[] words) {
		TrieNode root = new TrieNode();
		for (String word : words) {
			TrieNode node = root;
			for (char c : word.toCharArray()) {
				int idx = c - 'a';
				if (node.next[idx] == null) node.next[idx] = new TrieNode();
				node = node.next[idx];
			}
			node.word = word;
		}
		return root;
	}
	
	public List<String> findAllConcatenatedWordsInADict_trie(String[] words) {
		if (words == null || words.length <= 1) return Collections.emptyList();
		
		TrieNode root = buildTrie(words);
		
		List<String> result = new ArrayList<>();
		for (String word : words) {
			if (isConcatenatedWord(word, root, 0)) result.add(word);
		}
		return result;
    }
	
	private boolean isConcatenatedWord(String word, TrieNode root, int start) {
		TrieNode node = root;
		for (int i = start; i < word.length(); i++) {
			int idx = word.charAt(i) - 'a';
			if (node.next[idx] == null) return false;
			node = node.next[idx];
			if (node.word != null && !node.word.equals(word)) {
				if (isConcatenatedWord(word, root, i+1)) {
					return true;
				}
			}
		}
		return node != null && node.word != null && start != 0;
	}
	
	
	public static void main(String[] args) {
		ConcatenatedWords cw = new ConcatenatedWords ();
		String[] words ={"", "cat","cats","catsdogcats","dog","dogcatsdog","hippopotamuses","rat","ratcatdogcat"};
		System.out.println(cw.findAllConcatenatedWordsInADict_trie(words));
	}
}
