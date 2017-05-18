package leetcode.trie;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/*
 * Given a set of words (without duplicates), find all word squares you can build from them. A sequence of words forms a valid word square 
 * if the kth row and column read the exact same string, where 0 ≤ k < max(numRows, numColumns). For example, the word sequence 
 * ["ball","area","lead","lady"] forms a word square because each word reads the same both horizontally and vertically.
 * 	b a l l
 * 	a r e a
 * 	l e a d
 * 	l a d y
 * Note:
 * - There are at least 1 and at most 1000 words.
 * - All words will have the exact same length.
 * - Word length is at least 1 and at most 5.
 * - Each word contains only lowercase English alphabet a-z.
 * Example 1:
 * Input: ["area","lead","wall","lady","ball"]
 * Output:
 * 	[
 * 		["wall",
 * 		 "area",
 * 		 "lead",
 * 		 "lady"
 * 		],
 * 		["ball",
 * 		 "area",
 * 		 "lead",
 * 		 "lady"
 * 		]
 * 	]
 * Explanation: the output consists of two word squares. The order of output does not matter (just the order of words in each word square matters).
 * Example 2:
 * Input: ["abat","baba","atan","atal"]
 * Output:
 * 	[
 * 		["baba",
 * 		 "abat",
 * 		 "baba",
 * 		 "atan"
 * 		],
 * 		["baba",
 * 		 "abat",
 * 		 "baba",
 * 		 "atal"
 * 		]
 * ]
 * Explanation: The output consists of two word squares. The order of output does not matter (just the order of words in each word square matters).
 */
public class WordSquare {

	private static class TrieNode {
		List<String> startsWith = new ArrayList<>();
		TrieNode[] next = new TrieNode[26];  
	}
	
	private class Trie {
		private TrieNode root;
		
		Trie(String[] words) {
			root = new TrieNode();
			for (String word : words) {
				TrieNode node = root;
				for (char c : word.toCharArray()) {
					int index = c - 'a';
					if (node.next[index] == null) node.next[index] = new TrieNode();
					node.next[index].startsWith.add(word);
					node = node.next[index];
				}
			}	
		}
		
		List<String> startsWith(String prefix) {
			List<String> result = new ArrayList<>();
			TrieNode node = root;
			for (char c : prefix.toCharArray()) {
				int index = c - 'a';
				if (node.next[index] == null) return result;
				node = node.next[index];
			}
			result.addAll(node.startsWith); // add to result outside the loop since we need to match the whole prefix
			return result;
		}
	}
	
	public List<List<String>> wordSquares(String[] words) {
		if (words == null || words.length == 0) return Collections.emptyList();
		
		Trie trie = new Trie(words);
		List<List<String>> result = new ArrayList<>();
		LinkedList<String> list = new LinkedList<>();
		for (String word : words) {
			list.add(word);
			wordSquares_helper(trie, result, list, words[0].length());
			list.removeLast();
		}
        
        return result;
    }
	
	private void wordSquares_helper(Trie trie, List<List<String>> result, LinkedList<String> list, int len) {
		if (list.size() == len) {
			result.add(new ArrayList<>(list));
			return;
		}
		
		// get the prefix to process, collect all characters in the list at position list.size
		StringBuilder prefixBuilder = new StringBuilder();
		for (String word : list) {
			prefixBuilder.append(word.charAt(list.size()));
		}
		
		List<String> wordsWithPrefix = trie.startsWith(prefixBuilder.toString());
		for (String candidate : wordsWithPrefix) {
			list.add(candidate);
			wordSquares_helper(trie, result, list, len);
			list.removeLast(); // backtrack
		}
	}
	
	public static void main(String[] args) {
		WordSquare ws = new WordSquare();
		//String[] words = {"area","lead","wall","lady","ball"};
		
		String[] words = {"abat","baba","atan","atal"};
		System.out.println(ws.wordSquares(words));
	}
}
