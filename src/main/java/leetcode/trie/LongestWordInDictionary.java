package leetcode.trie;

import java.util.Stack;

/*
 * LEETCODE 720
 * Given a list of strings words representing an English Dictionary, find the longest word 
 * in words that can be built one character at a time by other words in words. If there is 
 * more than one possible answer, return the longest word with the smallest lexicographical 
 * order. If there is no answer, return the empty string.
 * Example 1:
 * Input: words = ["w","wo","wor","worl", "world"]
 * Output: "world"
 * Explanation:  The word "world" can be built one character at a time by "w", "wo", "wor", and "worl".
 * Example 2:
 * Input: words = ["a", "banana", "app", "appl", "ap", "apply", "apple"]
 * Output: "apple"
 * Explanation:  Both "apply" and "apple" can be built from other words in the dictionary. 
 * However, "apple" is lexicographically smaller than "apply".
 * Note:
 * - All the strings in the input will only contain lower case letters.
 * -The length of words will be in the range [1, 1000].
 * - The length of words[i] will be in the range [1, 30].
 * 
 * Company: Pinterest
 * Difficulty: easy
 * Similar Questions: 676(MagicDictionary), 524(LongestWordInDictionaryThroughDeleting)
 */
public class LongestWordInDictionary {
    private static final class Node {
        private boolean isWord;
        private Node[] next = new Node[26];
    }
    
    // Time complexity: O(sum(wi)) where wi is the length of words[i]
    public String longestWord(String[] words) {
        Node root = buildTrie(words);
        return getLongestWord(root, "");
    }

    private String getLongestWord(Node node, String prefix) {
        String result = prefix;
        for (int i = 0; i < 26; i++) {
            if (node.next[i] == null || !node.next[i].isWord) continue; // skip if the next node is not a word
            String word = getLongestWord(node.next[i], prefix+String.valueOf((char) ('a'+i)));
            if (word.length() > result.length()) {
                result = word;
            }
        }
        return result;
    }
    
    private Node buildTrie(String[] words) {
        Node root = new Node();
        for (String word : words) {
            Node node = root;
            for (char c : word.toCharArray()) {
                if (node.next[c-'a'] == null) {
                    node.next[c-'a'] = new Node();
                }
                node = node.next[c-'a'];
            }
            node.isWord = true;
        }
        return root;
    }
    
    public static void main(String[] args) {
        LongestWordInDictionary lwd = new LongestWordInDictionary();
        //String[] words = {"w","wo","wor","worl", "world"};
        //String[] words = {"a", "banana", "app", "appl", "ap", "apply", "apple"};
        String[] words = {"ogz","eyj","e","ey","hmn","v","hm","ogznkb","ogzn","hmnm","eyjuo","vuq","ogznk","og","eyjuoi","d"}; // eyj
        System.out.println(lwd.longestWord(words));
    }
}
