package leetcode.trie;

/*
 * LEETCODE 676
 * Implement a magic directory with buildDict, and search methods. 
 * For the method buildDict, you'll be given a list of non-repetitive words to build a dictionary. 
 * For the method search, you'll be given a word, and judge whether if you modify exactly one character 
 * into another character in this word, the modified word is in the dictionary you just built.
 * Example 1:
 * Input: buildDict(["hello", "leetcode"]), Output: Null
 * Input: search("hello"), Output: False
 * Input: search("hhllo"), Output: True
 * Input: search("hell"), Output: False
 * Input: search("leetcoded"), Output: False
 * Note: You may assume that all the inputs are consist of lower case letters a-z.
 * 
 * Company: Google
 * Difficulty: easy
 * Similar Questions: 208(Trie), 720(LongestWordInDictionary)
 */
public class MagicDictionary {
    private static final class Node {
        private boolean isWord;
        private Node[] next = new Node[26];
    }
    
    private Node root;
    
    public MagicDictionary() {}

    public void buildDict(String[] dict) {
        root = new Node();
        for (String word : dict) {
            Node node = root;
            for (char c : word.toCharArray()) {
                if (node.next[c-'a'] == null) {
                    node.next[c-'a'] = new Node();
                }
                node = node.next[c-'a'];
            }
            node.isWord = true;
        }
    }
    
    public boolean search(String word) {
        char[] chars = word.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            for (char c = 'a'; c <= 'z'; c++) {
                if (c == chars[i]) continue;
                chars[i] = c;
                String s = new String(chars);
                if (searchTrie(s)) {
                    System.out.println(s);
                    return true;
                }
            }    
            // need to do backtrack inside this loop
            // as it needs to compare the original char
            chars[i] = word.charAt(i);
        }
        return false;
    }
    
    private boolean searchTrie(String word) {
        Node node = root;
        for (char c : word.toCharArray()) {
            if (node == null) break;
            node = node.next[c-'a'];
        }
        return node != null && node.isWord;
    }
    
    public static void main(String[] args) {
        MagicDictionary md = new MagicDictionary ();
        md.buildDict(new String[] {"hello"});
        System.out.println(md.search("hello"));
        //System.out.println(md.search("hhllo"));
    }
    
}
