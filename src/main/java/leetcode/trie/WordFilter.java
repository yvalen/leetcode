package leetcode.trie;

import com.sun.corba.se.impl.orbutil.graph.Node;

/*
 * LEETCODE 746
 * Given many words, words[i] has weight i. Design a class WordFilter that supports one function, 
 * WordFilter.f(String prefix, String suffix). It will return the word with given prefix and suffix 
 * with maximum weight. If no word exists, return -1.
 * Examples:
 * Input:
 * WordFilter(["apple"])
 * WordFilter.f("a", "e") // returns 0
 * WordFilter.f("b", "") // returns -1
 * Note:
 * - words has length in range [1, 15000].
 * - For each test case, up to words.length queries WordFilter.f may be made.
 * - words[i] has length in range [1, 10].
 * - prefix, suffix have lengths in range [0, 10].
 * - words[i] and prefix, suffix queries consist of lower case letters only.
 * 
 * Company: Facebook
 * Difficulty: hard
 */
public class WordFilter {
    // For each suffix of the word, we could insert that suffix, followed by '#', 
    // followed by the word, all into the trie. For example, we will insert '#apple', 
    // 'e#apple', 'le#apple', 'ple#apple', 'pple#apple', 'apple#apple' into the trie. 
    // Then for a query like prefix = "ap", suffix = "le", we can find it by querying 
    // our trie for le#ap.
    // Time Complexity: O(NK^2+QK) where N is the number of words, K is the maximum 
    // length of a word, and Q is the number of queries.
    // Space Complexity: O(NK^2), the size of the trie.
    
    private static final class TrieNode {
        private int weight;
        private TrieNode[] next = new TrieNode[27];
    }
    
    private TrieNode root;
    
    public WordFilter(String[] words) {
        root = new TrieNode();
        for (int w = 0; w < words.length; w++) {
            String word = words[w]; 
            for (int i = word.length(); i >= 0; i--) {
                insert(word.substring(i) + "#"+ word, w);
            }
        }
    }
    
    public int f(String prefix, String suffix) {
        String word = suffix + "#" + prefix;
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            if (node == null) break;
            node = node.next[indexOf(c)];
        }
        return node == null ? -1 : node.weight;
    }

    private int indexOf(char c) {
        return c == '#' ? 26: c-'a';
    }
    
    private void insert(String word, int weight) {
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            int index = indexOf(c);
            if (node.next[index] == null) {
                node.next[index] = new TrieNode();
            }
            node.next[index].weight = weight;
            node = node.next[index];
        }
    }
    
   public static void main(String[] args) {
       WordFilter wf = new WordFilter(new String[]{"apple", "leetcode"});
       System.out.println(wf.f("a", "e"));
       System.out.println(wf.f("b", ""));
       System.out.println(wf.f("le", "e"));
   }
}
