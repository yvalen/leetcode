package leetcode.trie;

import java.util.HashMap;
import java.util.Map;

import com.sun.corba.se.impl.orbutil.graph.Node;

/*
 * LEETCODE 677
 * Implement a MapSum class with insert, and sum methods. 
 * For the method insert, you'll be given a pair of (string, integer). The string represents the key 
 * and the integer represents the value. If the key already existed, then the original key-value pair 
 * will be overridden to the new one.
 * For the method sum, you'll be given a string representing the prefix, and you need to return the 
 * sum of all the pairs' value whose key starts with the prefix.
 * Example 1:
 * Input: insert("apple", 3), Output: Null
 * Input: sum("ap"), Output: 3
 * Input: insert("app", 2), Output: Null
 * Input: sum("ap"), Output: 5
 * 
 * Company: Akuna Capital
 * Difficulty: medium
 */
public class MapSum {
    private static class TrieNode {
        private int value;
        private Map<Character, TrieNode> next = new HashMap<>();
    }
    
    private TrieNode root;
    
    public MapSum() {
        root = new TrieNode();
    }
    
    public void insert(String key, int val) {
        TrieNode node = root;
        for (char c : key.toCharArray()) {
            node.next.putIfAbsent(c, new TrieNode());
            node = node.next.get(c);
        }
        node.value = val;
    }
    
    public int sum(String prefix) {
        TrieNode node = root;
        for (int i = 0; i < prefix.length() && node != null; i++) {
            node = node.next.get(prefix.charAt(i));
        }
        return getSum(node, prefix);
    }
    
    private int getSum(TrieNode node, String prefix) {
        if (node == null) return 0;
        int sum = node.value;
        for (char c = 'a'; c <= 'z'; c++) {
            sum += getSum(node.next.get(c), prefix+c);
        }
        return sum;
    }
}
