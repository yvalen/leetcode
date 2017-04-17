package leetcode.bfsdfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class WordLadder {
	/*
	 * Given two words (beginWord and endWord), and a dictionary's word list, find the length of shortest 
	 * transformation sequence from beginWord to endWord, such that:
	 * - Only one letter can be changed at a time.
	 * - Each transformed word must exist in the word list. Note that beginWord is not a transformed word.
	 * For example, given
	 * 	beginWord = "hit"
	 * 	endWord = "cog"
	 * 	wordList = ["hot","dot","dog","lot","log","cog"]
	 * As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog", return its length 5. 
	 * Note:
	 *	Return 0 if there is no such transformation sequence.
	 *	All words have the same length.
	 *	All words contain only lowercase alphabetic characters.
	 *	You may assume no duplicates in the word list.
	 *	You may assume beginWord and endWord are non-empty and are not the same.
	 */
	// l - word length, n - 
	public int ladderLength(String beginWord, String endWord, List<String> wordList) {
		Set<String> dict = new HashSet<>();
		dict.addAll(wordList);
		
		HashSet<String> visited = new HashSet<>(); // keep track of words that have been checked
		
		Queue<String> q = new LinkedList<>();
		q.offer(beginWord);
		int len = 1;
		while (!q.isEmpty()) {
			len++;
			int qlen = q.size();
			for (int i = 0; i < qlen; i++) {
				String s = q.poll();
				Set<String> nextWords = getNextWords(s, dict, visited);
				for (String word : nextWords) {
					if (word.equals(endWord))  {
						return len;
					}
					q.offer(word);
					//visited.add(word); // we can remove word from dict to avoid using visited at all
					dict.remove(word);
				}
			}
		}
		
        return 0;
    }
	
	// return all words in dictionary that differs from s by one character and hasn't been visited
	// l = s.length O(l*26) -> O(l)
	private Set<String> getNextWords(String s, Set<String> dict, Set<String> visited) {
		Set<String> nextWords = new HashSet<>();
		char[] chars = s.toCharArray();
		for (int i = 0; i < s.length(); i++) {
			char c = chars[i];
			for (char j = 'a'; j <= 'z'; j++) { // test all possible chars for position i
				chars[i] = j;
				String str = new String(chars);
				if (dict.contains(str)) {
				//if (dict.contains(str) && !visited.contains(str)) {
					nextWords.add(str);
				}
			}
			chars[i] = c; // restore string
		}
		return nextWords;
	}	
	
	/*
	 * Given two words (beginWord and endWord), and a dictionary's word list, find all shortest transformation sequence(s) 
	 * from beginWord to endWord, such that
	 * - Only one letter can be changed at a time
	 * - Each transformed word must exist in the word list. Note that beginWord is not a transformed word.
	 * For example, given
	 * 	beginWord = "hit"
	 * 	endWord = "cog"
	 * 	wordList = ["hot","dot","dog","lot","log","cog"]
	 * Return
	 * 	[
	 * 		["hit","hot","dot","dog","cog"],
	 * 		["hit","hot","lot","log","cog"]
	 * 	]
	 */
	public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
		List<List<String>> result = new ArrayList<>();
		
		Set<String> dict = new HashSet<>();
		dict.addAll(wordList);
		
		Map<String, Integer> distanceMap = new HashMap<>(); // distance from the starting word
		distanceMap.put(beginWord, 0);
		
		Map<String, List<String>> neighborMap = new HashMap<>(); 
		
		// populate the shortest path graph
		bfs(beginWord, endWord, dict, neighborMap, distanceMap);
		
		// output shortest paths
		dfs(beginWord, endWord, neighborMap, distanceMap, result, new LinkedList<>());
		
        return result;
    }
	
	
	private void bfs(String beginWord, String endWord, Set<String> dict, Map<String, List<String>> neighborMap, Map<String, Integer> distanceMap) {
		Queue<String> q = new LinkedList<>();
		q.offer(beginWord);
		while (!q.isEmpty()) {
			int qlen = q.size();
			boolean found = false;
			for (int i = 0; i < qlen; i++) {
				String word = q.poll();
				if (!neighborMap.containsKey(word)) {
					neighborMap.put(word, new ArrayList<>());
				}
				
				int currentDistance = distanceMap.get(word);
				List<String> neighbors = getNeighbors(word, dict);
				for (String neighbor : neighbors) {
					neighborMap.get(word).add(neighbor); // add neighbor
					
					// skip visited neighbor
					if (!distanceMap.containsKey(neighbor)) {
						// add distance
						distanceMap.put(neighbor, currentDistance+1);
						if (neighbor.equals(endWord))  {
							found = true;
						}
						else {
							q.offer(neighbor);
						}
					}
				}
			}
			
			if (found) break; // shortest path found, no need to go to the next level
		}
	}
	
	private void dfs(String beginWord, String endWord, Map<String, List<String>> neighborMap, 
			Map<String, Integer> distanceMap, List<List<String>> result, LinkedList<String> wordList) {
		wordList.add(beginWord);
		if (beginWord.equals(endWord)) {
			result.add(new ArrayList<>(wordList)); 
			// cannot return from here because we need to do backtrack on wordList
		}
		else {
			List<String> neighbors = neighborMap.get(beginWord);
			if (neighbors != null) { // need to do null check because the graph is not fully populated
				for (String neighbor : neighbors) {
					if (distanceMap.get(neighbor) == distanceMap.get(beginWord)+1) {
						dfs(neighbor, endWord, neighborMap, distanceMap, result, wordList);
					}
				}
			}
		}
		wordList.removeLast();
	}
	
	// get all neighbors of s, neighbor of s differs from s by one character and is in dict
	private List<String> getNeighbors(String s, Set<String> dict) {
		List<String> neighbors = new ArrayList<>();
		char[] chars = s.toCharArray();
		for (int i = 0; i < chars.length; i++) {
			char original = chars[i];
			for (char c = 'a'; c <= 'z'; c++) {
				chars[i] = c;
				String neighbor = new String(chars);
				if (dict.contains(neighbor)) {
					neighbors.add(neighbor);
				}
			}
			chars[i] = original;
		}
		
		return neighbors;
	}
	
	
	
	public static void main(String[] args) {
		WordLadder w = new WordLadder();
		String beginWord = "hit", endWord = "cog";
		List<String> wordList = Arrays.asList("hot","dot","dog","lot","log","cog");
		//List<String> wordList = Arrays.asList("hot","dot","dog","lot","log");
		//System.out.println(w.ladderLength(beginWord, endWord, wordList));
		
		//String beginWord = "a", endWord = "c";
		//List<String> wordList = Arrays.asList("a","b","c");
		System.out.println(w.findLadders(beginWord, endWord, wordList));
		
	}

}
