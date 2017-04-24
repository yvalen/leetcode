package leetcode.trie;

/*
 * Implement a trie with insert, search, and startsWith methods.
 * Note: you may assume that all inputs are consist of lowercase letters a-z.
 */
public class Trie {
	private static class Node {
		boolean endOfWord;
		private Node[] next = new Node[26];
	}
	
	private Node root;

	/** Initialize your data structure here. */
    public Trie() {
    	this.root = new Node();
    }
    
    /** Inserts a word into the trie. */
    public void insert(String word) {
        insert(root, word, 0);
    }
    
    private Node insert(Node node, String word, int index) {
    	if (node == null) node = new Node();
    	if (index == word.length()) {
    		node.endOfWord = true;
    		return node;
    	}
    	char c = word.charAt(index);
    	node.next[c-'a'] = insert(node.next[c-'a'], word, index+1);
    	return node;
    }
       
    /** Returns if the word is in the trie. */
    public boolean search(String word) {
    	return search(root, word, 0, false);
    }
    
    private boolean search(Node node, String word, int index, boolean prefixOnly) {
    	if (node == null) return false;
    	if (index == word.length()) {
    		return prefixOnly ? true : node.endOfWord;
    	}
    	return search(node.next[word.charAt(index)-'a'], word, index+1, prefixOnly);
    }
    
    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
    	return search(root, prefix, 0, true);
    }
    
    //
    // Iterative
    //
    public void insert_iterative(String word) {
        Node node = root;
        for (int i = 0; i < word.length(); i++) {
        	char c = word.charAt(i);
        	if (node.next[c-'a'] == null) {
        		node.next[c-'a'] = new Node();
        	}
        	node = node.next[c-'a'];
        }
        node.endOfWord = true;		
    }
    
    private Node find(String word) {
    	Node node = root;
        for (int i = 0; i < word.length() && node != null; i++) {
        	char c = word.charAt(i);
        	node = node.next[c-'a'];
        }
    	return node;
    }
    
    public boolean search_iterative(String word) {
    	Node node = find(word);
    	return node != null && node.endOfWord;
    }
    
    public boolean startsWith_iterative(String word) {
    	Node node = find(word);
    	return node != null;
    }
    
    public static void main(String[] args) {
    	Trie obj = new Trie();
    	String word = "shells", prefix = "she";
    	//String word = "shells", prefix = "shec";
    	obj.insert_iterative(word);
    	System.out.println(obj.search_iterative(word));
    	System.out.println(obj.startsWith_iterative(prefix));
    }
}
