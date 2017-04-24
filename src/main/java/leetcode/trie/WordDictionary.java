package leetcode.trie;

/*
 * Design a data structure that supports the following two operations:
 * void addWord(word)
 * bool search(word)
 * search(word) can search a literal word or a regular expression string containing only letters a-z or .. A 
 * . means it can represent any one letter. For example:
 * addWord("bad")
 * addWord("dad")
 * addWord("mad")
 * search("pad") -> false
 * search("bad") -> true
 * search(".ad") -> true
 * search("b..") -> true
 * Note: you may assume that all words are consist of lower case letters a-z. 
 */
public class WordDictionary {
	private static class TrieNode {
		private boolean isWord;
		private TrieNode[] next = new TrieNode[26];
	}
	
	private TrieNode root;
	
	public WordDictionary() {
		root = new TrieNode();
    }
    
	public void addWord(String word) {
        TrieNode node = root;
        for (int i = 0; i < word.length(); i++) {
        	int index = word.charAt(i) - 'a';
        	if (node.next[index] == null) node.next[index] = new TrieNode();
        	node = node.next[index];
        }
        node.isWord = true;
    }
	
	public boolean search(String word) {
		return search(word, 0, root);
    }
	
	private boolean search(String word, int index, TrieNode node) {
		if (node == null) return false;
		if (index == word.length()) return node.isWord;
		
		char c = word.charAt(index);
		for (int i = 0; i < 26; i++) {
			if (c == '.' || (c-'a') == i)  {
				if (search(word, index+1, node.next[i])) return true;
			}
		}
		return false;
	}
	
	
}
