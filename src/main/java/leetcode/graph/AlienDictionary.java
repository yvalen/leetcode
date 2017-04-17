package leetcode.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/*
 * There is a new alien language which uses the latin alphabet. However, the order among letters are unknown to you. 
 * You receive a list of non-empty words from the dictionary, where words are sorted lexicographically by the rules 
 * of this new language. Derive the order of letters in this language.
 * Example 1: given the following words in dictionary,
 * [
 * 		"wrt",
 * 		"wrf",
 * 		"er",
 * 		"ett",
 * 		"rftt"
 * ]
 * The correct order is: "wertf".
 * Example 2: given the following words in dictionary,
 * [
 * 		"z",
 * 		"x"
 * ]
 * The correct order is: "zx".
 * Example 3: given the following words in dictionary,
 * [
 * 		"z",
 * 		"x",
 * 		"z"
 * ]
 * The order is invalid, so return "".
 * Note:
 * - You may assume all letters are in lowercase.
 * - You may assume that if a is a prefix of b, then a must appear before b in the given dictionary.
 * - If the order is invalid, return an empty string.
 * - There may be multiple valid order of letters, return any one of them is fine.
 */
public class AlienDictionary {
	
	public String alienOrder(String[] words) {
		if (words == null || words.length == 0) return "";
		
		// initialize the in-degree map for all known characters
		Map<Character, Integer> inDegrees = new HashMap<>(); 
		for (String word : words) {
			for (int i = 0; i < word.length(); i++) {
				inDegrees.put(word.charAt(i), 0);
			}
		}
		
		// build graph
		Map<Character, Set<Character>> graph = new HashMap<>();
		for (int i = 0; i < words.length-1; i++) {
			String current = words[i];
			String next = words[i+1];
			
			// if a is a prefix of b, then a must appear before b in the given dictionary.
			if (current.length() > next.length() && current.startsWith(next)) {
				return "";
			}
			
			// compare each adjacent pairs of words and try to find the first character that differs
			// that character in the next word must come after the character in the current word 
			int len = Integer.min(current.length(), next.length());
			for (int j = 0; j < len; j++) {
				Character c1 = current.charAt(j);
				Character c2 = next.charAt(j);
				if (c1 != c2) {
					if (!graph.containsKey(c1)) {
						graph.put(c1, new HashSet<>());
					}
					if (!graph.get(c1).contains(c2)) {
						// only add to adjacent list and increment in-degrees when if it is not there
						graph.get(c1).add(c2);
						inDegrees.put(c2, inDegrees.get(c2)+1); 
					}
					break; // don't do further after finding the first different character
				}
			}
		}
		
		Queue<Character> queue = new LinkedList<>();
		for (Map.Entry<Character, Integer> entry : inDegrees.entrySet()) {
			if (entry.getValue() == 0) {
				queue.offer(entry.getKey());
			}
		}
		
		StringBuilder sb = new StringBuilder();
		while (!queue.isEmpty()) {
			Character current = queue.poll();
			sb.append(current);
			// nodes without adjacent list will not be in the graph 
			if (!graph.containsKey(current)) continue;
			
			Set<Character> neighbors = graph.get(current);
			for (Character neighbor : neighbors) {
				inDegrees.put(neighbor, inDegrees.get(neighbor)-1);
				if (inDegrees.get(neighbor) == 0) {
					queue.offer(neighbor);
				}
			}
		}
		
		return sb.length() == inDegrees.size() ? sb.toString() : ""; 
		
    }
	
	public static void main(String[] args) {
		AlienDictionary ad = new AlienDictionary();
		//String[] words = {"wrt", "wrf", "er", "ett", "rftt"};
		//String[] words = {"z", "x"};
		//String[] words = {"z", "x", "z"};
		String[] words = {"za","zb","ca","cb"}; // expect "abzc"
		System.out.println(ad.alienOrder(words));
	}

}
