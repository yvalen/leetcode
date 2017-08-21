package leetcode.trie;

import java.util.Arrays;
import java.util.List;

/*
 * In English, we have a concept called root, which can be followed by some other words to form another longer word - let's call this word successor. 
 * For example, the root an, followed by other, which can form another word another. Now, given a dictionary consisting of many roots and a sentence. 
 * You need to replace all the successor in the sentence with the root forming it. If a successor has many roots can form it, replace it with the root 
 * with the shortest length. You need to output the sentence after the replacement.
 * Example 1: 
 * Input: dict = ["cat", "bat", "rat"]
 * sentence = "the cattle was rattled by the battery"
 * Output: "the cat was rat by the bat"
 * Note:
 * - The input will only have lower-case letters.
 * - 1 <= dict words number <= 1000
 * - 1 <= sentence words number <= 1000
 * - 1 <= root length <= 100
 * - 1 <= sentence words length <= 1000
 * 
 * Company: Uber
 * Difficulty: medium
 */
public class ReplaceWords {
	public String replaceWords(List<String> dict, String sentence) {
        if (dict == null || dict.isEmpty() || sentence == null || sentence.length() == 0) return sentence;
		
        Trie trie = new Trie();
		for (String word : dict) {
			trie.insert(word);
		}
       
		String[] words = sentence.split(" "); 
        StringBuilder sb = new StringBuilder();
        for (String word : words) {
        	String root = trie.getPrefix(word);
        	if (root != null) sb.append(root);
        	else sb.append(word);
        	sb.append(' ');
        }
        
        sb.setLength(sb.length()-1);
        return sb.toString();
    }
	
	private static class Trie {
		private static class Node {
			private boolean isWord;
			private Node[] next = new Node[26];
		}
		private Node root;
		
		Trie() {
			root = new Node();
		}
		
		void insert(String word) {
			Node node = root;
			for (char c : word.toCharArray()) {
				int idx = c - 'a';
				if (node.next[idx] == null) node.next[idx] = new Node();
				node = node.next[idx];
			}
			node.isWord = true;
		}
		
		String getPrefix(String word) {
			Node node = root;
			StringBuilder prefix = new StringBuilder();
			for (int i = 0; i < word.length() && node != null; i++) {
				char c = word.charAt(i);
				prefix.append(c);
				node = node.next[c-'a'];
				if (node != null && node.isWord) return prefix.toString();
			}
			return null;
		}
	}
	
	public static void main(String[] args) {
		ReplaceWords rw = new ReplaceWords();
		List<String> dict = Arrays.asList("ca", "cat", "bat", "rat");
		String sentence = "the cattle was rattled by the battery";
		System.out.println(rw.replaceWords(dict, sentence));
	}
}
