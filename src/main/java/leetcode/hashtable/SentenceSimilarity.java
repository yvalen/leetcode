package leetcode.hashtable;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class SentenceSimilarity {
	/*
	 * LEETCODE 734
	 * Given two sentences words1, words2 (each represented as an array of strings), 
	 * and a list of similar word pairs pairs, determine if two sentences are similar.
	 * For example, "great acting skills" and "fine drama talent" are similar, 
	 * if the similar word pairs are pairs = 
	 * [["great", "fine"], ["acting","drama"], ["skills","talent"]].
	 * Note that the similarity relation is not transitive. For example, if "great" 
	 * and "fine" are similar, and "fine" and "good" are similar, "great" and "good" are 
	 * not necessarily similar. However, similarity is symmetric. For example, "great" and 
	 * "fine" being similar is the same as "fine" and "great" being similar. Also, a word 
	 * is always similar with itself. For example, the sentences words1 = ["great"], 
	 * words2 = ["great"], pairs = [] are similar, even though there are no specified similar 
	 * word pairs. Finally, sentences can only be similar if they have the same number of words. 
	 * So a sentence like words1 = ["great"] can never be similar to words2 = ["doubleplus","good"].
	 * Note:
	 * - The length of words1 and words2 will not exceed 1000.
	 * - The length of pairs will not exceed 2000.
	 * - The length of each pairs[i] will be 2.
	 * - The length of each words[i] and pairs[i][j] will be in the range [1, 20].
	 * 
	 * Company: Google
	 * Difficulty: easy
	 * Similar Questions: 737(Sentence Similarity II)
	 */
	public boolean areSentencesSimilar(String[] words1, String[] words2, String[][] pairs) {
        if (words1 == null) return words2 == null;
        
        if (words1.length != words2.length) return false;
        
        // map value should be HashSet as one word can be mapped to multiple words 
        Map<String, Set<String>> map = new HashMap<>();
        for (String[] pair : pairs) {
            map.putIfAbsent(pair[0], new HashSet<>());
            map.get(pair[0]).add(pair[1]);
            map.putIfAbsent(pair[1], new HashSet<>());
            map.get(pair[1]).add(pair[0]);
        }
        
        for (int i = 0; i < words1.length; i++) {
            String word1 = words1[i], word2 = words2[i];
            if (!word1.equals(word2) && (!map.containsKey(word1) || !map.get(word1).contains(word2))) {
                //System.out.println("word1=" + word1 + " word2="+ word2);
                return false;
            }
        }
        return true;
    }
	
	/*
	 * LEETCODE 737
	 * Extension of 734(Sentence Similarity), the similarity relation is transitive. For example, 
	 * if "great" and "good" are similar, and "fine" and "good" are similar, then "great" and "fine" 
	 * are similar. 
	 * 
	 * Company: Google
	 * Difficulty: medium
	 * Similar Questions: 
	 */
	// Time complexity: O(np) n is the max length of words1 and words2, p is the length of pairs
	// Space complexity: O(p)
	public static boolean areSentencesSimilarTwo_dfs(String[] words1, String[] words2, String[][] pairs) {
		Map<String, Set<String>> map = new HashMap<>();
        for (String[] pair : pairs) {
            map.putIfAbsent(pair[0], new HashSet<>());
            map.get(pair[0]).add(pair[1]);
            map.putIfAbsent(pair[1], new HashSet<>());
            map.get(pair[1]).add(pair[0]);
        }
        
        for (int i = 0; i < words1.length; i++) {
            String word1 = words1[i], word2 = words2[i];
            if (!word1.equals(word2) && (!isSimilar(map, word1, word2))) {
                //System.out.println("word1=" + word1 + " word2="+ word2);
                return false;
            }
        }
        return true;
    }
	
	private static boolean isSimilar(Map<String, Set<String>> map, String word1, String word2) {
		if (!map.containsKey(word1) || !map.containsKey(word2)) return false;
		Set<String> visited = new HashSet<>();
		return dfs(map, word1, word2, visited);
	}
	
	private static boolean dfs(Map<String, Set<String>> map, String word1, String word2, Set<String> visited) {
		if (word1.equals(word2)) return true;
		
		visited.add(word1);
		for (String word : map.get(word1)) {
			if (visited.contains(word)) continue;
			if (dfs(map, word, word2, visited)) return true;
		}
		return false;
	}
	
	// similarity relation is transitive means graph is undirected,
	// we can apply union find
	// Time complexity: O(nlogp)
	public static boolean areSentencesSimilarTwo_unionfind(String[] words1, String[] words2, String[][] pairs) {
	    int id = 0;
	    Map<String, Integer> idMap = new HashMap<>();
	    UnionFind uf = new UnionFind(pairs.length*2);
	    for (String[] pair : pairs) {
	        // map all words in pairs to an id
	        for (String word : pair) {
	            if (!idMap.containsKey(word)) {
                    idMap.put(word, id++);
                }
	        }
	        uf.union(idMap.get(pair[0]), idMap.get(pair[1]));
	    }
	    System.out.println(idMap);
	    
	    for (int i = 0; i < words1.length; i++) {
	        String w1 = words1[i], w2 = words2[i];
	        // need to check if w1 equals to w2 first
	        // as w1 and w2 may not be defined in pairs
	        if (w1.equals(w2)) continue;
	        if (!idMap.containsKey(w1) || !idMap.containsKey(w2) ||
	                uf.find(idMap.get(w1)) != uf.find(idMap.get(w2))) {
	            return false;
	        }
	    }
	    return true;
	}
	
	private static class UnionFind {
	    private int[] ids;
	    UnionFind(int n) {
	        ids = new int[n];
	        for (int i = 0; i < n; i++) ids[i] = i;
	    }
	    
	    int find(int p) {
	        while (p != ids[p]) {
	            p = ids[p];
	        }
	        return p;
	    }
	    
	    void union(int p, int q) {
	        int i = find(p), j = find(q);
	        if (i == j) return;
	        ids[i] = j;
	    }
	}
	
	
	public static void main(String[] args) {
		String[] words1 = {"great","acting","skills"};
		String[] words2 = {"fine","drama","talent"};
		String[][] pairs = {{"great","good"},{"fine","good"},{"drama","acting"},{"skills","talent"}};
		
		System.out.println(areSentencesSimilarTwo_unionfind(words1, words2, pairs));
	}
}
