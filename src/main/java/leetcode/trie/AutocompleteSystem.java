package leetcode.trie;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

/*
 * Design a search auto-complete system for a search engine. Users may input a sentence (at least one word and end with a special character '#'). 
 * For each character they type except '#', you need to return the top 3 historical hot sentences that have prefix the same as the part of sentence 
 * already typed. Here are the specific rules:
 * - The hot degree for a sentence is defined as the number of times a user typed the exactly same sentence before.
 * - The returned top 3 hot sentences should be sorted by hot degree (The first is the hottest one). If several sentences have the same degree of hot, 
 * you need to use ASCII-code order (smaller one appears first).
 * - If less than 3 hot sentences exist, then just return as many as you can.
 * - When the input is a special character, it means the sentence ends, and in this case, you need to return an empty list.
 * Your job is to implement the following functions:
 * - The constructor function:
 * AutocompleteSystem(String[] sentences, int[] times): This is the constructor. The input is historical data. Sentences is a string array consists of 
 * previously typed sentences. Times is the corresponding times a sentence has been typed. Your system should record these historical data.
 * Now, the user wants to input a new sentence. The following function will provide the next character the user types:
 * List<String> input(char c): The input c is the next character typed by the user. The character will only be lower-case letters ('a' to 'z'), 
 * blank space (' ') or a special character ('#'). Also, the previously typed sentence should be recorded in your system. The output will be the top 3 
 * historical hot sentences that have prefix the same as the part of sentence already typed.
 * 
 * Example:
 * Operation: AutocompleteSystem(["i love you", "island","ironman", "i love leetcode"], [5,3,2,2])
 * The system have already tracked down the following sentences and their corresponding times:
 * "i love you" : 5 times
 * "island" : 3 times
 * "ironman" : 2 times
 * "i love leetcode" : 2 times
 * Now, the user begins another search: Operation: input('i') Output: ["i love you", "island","i love leetcode"]
 * Explanation: There are four sentences that have prefix "i". Among them, "ironman" and "i love leetcode" have same hot degree. Since ' ' has ASCII code 32 and 
 * 'r' has ASCII code 114, "i love leetcode" should be in front of "ironman". Also we only need to output top 3 hot sentences, so "ironman" will be ignored.
 * 
 * Operation: input(' ') Output: ["i love you","i love leetcode"]
 * Explanation: There are only two sentences that have prefix "i ".
 * 
 * Operation: input('a') Output: []
 * Explanation: There are no sentences that have prefix "i a".
 * 
 * Operation: input('#') Output: []
 * Explanation: The user finished the input, the sentence "i a" should be saved as a historical sentence in system. And the following input will be counted as 
 * a new search.
 * Note:
 * - The input sentence will always start with a letter and end with '#', and only one blank space will exist between two words.
 * - The number of complete sentences that to be searched won't exceed 100. The length of each sentence including those in the historical data won't exceed 100.
 * - Please use double-quote instead of single-quote when you write test cases even for a character input.
 * - Please remember to RESET your class variables declared in class AutocompleteSystem, as static/class variables are persisted across multiple test cases. 
 * 
 * Company: Facebook, Microsoft
 * Difficulty: hard
 * Article: https://leetcode.com/articles/design-search-autocomplete-system/
 */
public class AutocompleteSystem {
	private Trie trie;
	private String current;
	public AutocompleteSystem(String[] sentences, int[] times) {
        trie = new Trie();
        for (int i = 0; i < times.length; i++) {
        	trie.insert(sentences[i], times[i]);
        }
        current = "";
    }
    
    public List<String> input(char c) {
    	if (c == '#') {
    		trie.insert(current, 1);  // insert the sentence into trie
    		current = "";
    		return Collections.emptyList();
    	}
    	else {
    		current += c;
    		return trie.search(current);
    	}
    }

    private static class Trie {
    	private static class SentenceWithCount implements Comparable<SentenceWithCount> {
        	private String sentence;
        	private int count;
        	SentenceWithCount(String sentence, int count) {
        		this.sentence = sentence;
        		this.count = count;
        	}
			@Override
			public int compareTo(SentenceWithCount o) {
				return (this.count == o.count) ? o.sentence.compareTo(this.sentence) : this.count - o.count;
			}
			@Override
			public String toString() {
				return "SentenceWithCount [sentence=" + sentence + ", count=" + count + "]";
			}
        }
    	
    	private static class Node {
    		private int times;
    		private Node[] next = new Node[27];
    	}
    	
    	private Node root;
    	private PriorityQueue<SentenceWithCount> pq;
    	
    	Trie() {
    		 root = new Node(); 
    		 pq = new PriorityQueue<>(3);
    	}
    	
    	private int index(char c) {
    		return c == ' ' ? 26 : c - 'a';
    	}
    	
    	void insert(String sentence, int times) {
    		Node node = root;
    		for (char c : sentence.toCharArray()) {
    			int idx = index(c);
    			if (node.next[idx] == null) node.next[idx] = new Node();
    			node = node.next[idx];
    		}
    		node.times += times;
    	}
    	
    	List<String> search(String prefix) {
    		pq.clear();
    		Node node = root;
    		for (int i = 0; i < prefix.length() && node != null; i++) {
    			int idx = index(prefix.charAt(i));
    			node = node.next[idx];
    		}
    		
    		if (node == null) return Collections.emptyList();
    		
    		traverse(prefix, node);
    		List<String> result = new ArrayList<>(3);
    		for (int i = 0; i < 3 && !pq.isEmpty(); i++) {
    			result.add(pq.poll().sentence);
    		}
    		Collections.reverse(result);
    		
    		return result;
    	}
    	
    	private void traverse(String prefix, Node node) {
    		if (node.times > 0) {
    			updatePriorityQueue(new SentenceWithCount(prefix, node.times));
    		}
    		for (char c = 'a'; c <= 'z'; c++) {
    			int idx = index(c);
    			if (node.next[idx] != null) {
    				traverse(prefix+c, node.next[idx]);
    			}
    		}
    		if (node.next[26] != null) {
    			traverse(prefix+' ', node.next[26]);
    		}
    	}
    	
    	private void updatePriorityQueue(SentenceWithCount sentenceWithCount) {
    		//System.out.println(sentenceWithCount);
    		if (pq.size() < 3) {
    			pq.offer(sentenceWithCount);
    		}
    		else if (pq.peek().compareTo(sentenceWithCount) < 0) {
    			pq.poll();
    			pq.offer(sentenceWithCount);
    		}
    		System.out.println(pq);
    	}
    }
    
    public static void main(String[] args) {
    	String[] sentences = {"i love you", "island","ironman", "i love leetcode"};
    	int[] times = {5,3,2,2};
    	AutocompleteSystem as = new AutocompleteSystem (sentences, times);
    	
    	System.out.println(as.input('i'));
    	System.out.println(as.input(' '));
    	System.out.println(as.input('a'));
    	System.out.println(as.input('#'));
    	
    	/*
    	Trie.SentenceWithCount a = new Trie.SentenceWithCount("ironman", 2);
    	Trie.SentenceWithCount b = new Trie.SentenceWithCount("island", 3);
    	Trie.SentenceWithCount c = new Trie.SentenceWithCount("i love leetcode", 2);
    	
    	System.out.println(a.compareTo(c));
    	System.out.println("ironman".compareTo("i love leetcode"));
    	*/
    }
    
    
}
