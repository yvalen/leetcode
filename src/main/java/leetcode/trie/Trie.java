package leetcode.trie;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import jdk.internal.org.objectweb.asm.tree.IntInsnNode;

/*
 * LEETCODE 208
 * Implement a trie with insert, search, and startsWith methods.
 * Note: you may assume that all inputs are consist of lowercase letters a-z.
 * 
 * Company: Google, Uber, Facebook, Microsoft, Twitter, Bloomberg
 * Difficulty: medium
 * Similar Questions: 211(WordDictionary), 642(AutocompleteSystem),
 * 648(ReplaceWords), 676(MagicDictionary)
 */
public class Trie {
    private static class Node {
        private boolean endOfWord;
        private Node[] next = new Node[26];
    }

    private Node root;
    
    // Time complexity: number of array access for search and insert is 
    // at most 1 plus the length if the key

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
        node.next[c - 'a'] = insert(node.next[c - 'a'], word, index + 1);
        return node;
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        Node node = search(root, word, 0);
        return node != null && node.endOfWord;
    }

    /**
     * Returns if there is any word in the trie that starts with the given
     * prefix.
     */
    public boolean startsWith(String prefix) {
        Node node = search(root, prefix, 0);
        return node != null;
    }
    
    private Node search(Node node, String word, int d) {
        if (node == null) return null;
        if (d == word.length()) return node;
        return search(node.next[word.charAt(d)-'a'], word, d+1);
    }

    //
    // Iterative
    //
    public void insert_iterative(String word) {
        Node node = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (node.next[c - 'a'] == null) {
                node.next[c - 'a'] = new Node();
            }
            node = node.next[c - 'a'];
        }
        node.endOfWord = true;
    }

    private Node find(String word) {
        Node node = root;
        for (int i = 0; i < word.length() && node != null; i++) {
            char c = word.charAt(i);
            node = node.next[c - 'a'];
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

    public List<String> keysWithPrefix(String prefix) {
        // find the node for prefix first
        Node node = find(prefix);
     
        LinkedList<String> keys = new LinkedList<>();
        collect(node, prefix, keys);
        return keys;
    }
    
    private void collect(Node node, String prefix, LinkedList<String> keys) {
        if (node == null) return;
        if (node.endOfWord) keys.add(prefix);
        for (int i = 0; i < 26; i++) {
            collect(node.next[i], prefix+((char)(i+'a')), keys);
        }
    }
    
    public static void main(String[] args) {
        Trie obj = new Trie();
        /*
        String word = "shells", prefix = "she";
        // String word = "shells", prefix = "shec";
        obj.insert_iterative(word);
        System.out.println(obj.search_iterative(word));
        System.out.println(obj.startsWith_iterative(prefix));
        */
        
        obj.insert_iterative("she");
        obj.insert_iterative("sells");
        obj.insert_iterative("sea");
        obj.insert_iterative("by");
        obj.insert_iterative("sea");
        obj.insert_iterative("shore");
        System.out.println(obj.keysWithPrefix("se"));
    }
}
