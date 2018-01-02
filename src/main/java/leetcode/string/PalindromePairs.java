package leetcode.string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/*
 * LEETCODE 336
 * Given a list of unique words, find all pairs of distinct indices (i, j) in the given list, 
 * so that the concatenation of the two words, i.e. words[i] + words[j] is a palindrome.
 * Example 1: given words = ["bat", "tab", "cat"] return [[0, 1], [1, 0]]
 * The palindromes are ["battab", "tabbat"]
 * Example 2: given words = ["abcd", "dcba", "lls", "s", "sssll"] 
 * return [[0, 1], [1, 0], [3, 2], [2, 4]]
 * The palindromes are ["dcbaabcd", "abcddcba", "slls", "llssssll"]
 * 
 * Company: Google, Airbnb
 * Difficulty: hard
 * zSimilar Questions: 5(LongestPalindromeSubstring), 214(ShortestPalindrome)
 */
public class PalindromePairs {
    /*
     * - Traverse the array, build map. Key is the reversed string, value is
     * index in array (0 based) 
     * - Main logic part. Partition the word into left and right, and see 
     * 1) if there exists a candidate in map equals to the left side of current word, 
     * and right side of current word is palindrome, so concatenate(current word, candidate) 
     * forms a pair: left | right | candidate. 
     * 2) same for checking the right side of current word: candidate | left | right. 
     * - Edge case - check if empty string exists. It's interesting that for given words {"a", ""}, 
     * it's expected to return two results [0,1] and [1,0]. Since my main logic can cover [0, 1]
     * concatenate("a", ""), so as to cover the other situation concatenate("", "a"), I need to 
     * traverse the words array again, find the palindrome word candidate except "" itself, 
     * and add pair("", palindrome word) to the final answer. 
     * Time complexity: O(n*k^2)
     */
    public List<List<Integer>> palindromePairs(String[] words) {
        if (words == null || words.length == 0)
            return Collections.emptyList();

        // create a dictionary with the reverse of each word as key and its
        // index as value
        Map<String, Integer> dict = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            StringBuilder sb = new StringBuilder(words[i]);
            dict.put(sb.reverse().toString(), i);
        }

        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < words.length; i++) {
            // process each word
            String word = words[i];
            for (int j = 0; j <= word.length(); j++) { // need to include =
                // partition the word into two pars
                String s1 = word.substring(0, j);
                String s2 = word.substring(j);

                if (isPalindrome(s1)) {
                    Integer idx = dict.get(s2);
                    if (idx != null && idx != i) { 
                        // need to exclude the word itself
                        result.add(Arrays.asList(idx, i));
                    }
                }

                if (isPalindrome(s2) && s2.length() != 0) { 
                    // the word is checked in the previous if statement, 
                    // need to exclude it by check the length of s2
                    Integer idx = dict.get(s1);
                    if (idx != null && idx != i) {
                        result.add(Arrays.asList(i, idx));
                    }
                }
            }
        }

        return result;
    }

    private boolean isPalindrome(String s) {
        if (s == null || s.isEmpty())
            return true;
        for (int i = 0, j = s.length() - 1; i < j; i++, j--) {
            if (s.charAt(i) != s.charAt(j))
                return false;
        }
        return true;
    }
    
    
    //
    // Use Trie
    // Time complexity: both building and searching the Trie structure take O(n*k^2)
    private static final class TrieNode {
        private int index; // index of the word in words array
        // list stores the index of the words that satisfies the following two conditions:
        // - has a suffix represented by the current Trie node
        // - the rest of the word forms a palindrome
        private List<Integer> list;
        private TrieNode[] next;
        TrieNode() {
            index = -1;
            list = new ArrayList<>();
            next = new TrieNode[26];
        }
    }
    
    public List<List<Integer>> palindromePairs_withTrie(String[] words) {
        TrieNode root = new TrieNode();
        for (int i = 0; i < words.length; i++) {
            insert(root, words[i], i);
        }
        
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < words.length; i++) {
            search(root, words[i], i, result);
        }
        return result;
    }
    
    private void insert(TrieNode node, String word, int index) {
        // insert the reverse of the word into trie
        for (int i = word.length() - 1; i >= 0; i--) {
            int idx = word.charAt(i) - 'a';
            if (node.next[idx] == null) node.next[idx] = new TrieNode();
            if (isPalindrome(word, 0, i)) node.list.add(index);
            node = node.next[idx];
        }
        node.index = index;
        node.list.add(index);
    }
    
    private boolean isPalindrome(String s, int start, int end) {
        while (start < end) {
            if (s.charAt(start++) != s.charAt(end--)) return false;
        }
        return true;
    }
    
    private void search(TrieNode node, String word, int index, List<List<Integer>> result ) {
        for (int i = 0; i < word.length(); i++) {
            if (node.index >= 0 && node.index != index && isPalindrome(word, i, word.length()-1)) {
                result.add(Arrays.asList(index, node.index));
            }
            node = node.next[word.charAt(i)-'a'];
            if (node == null) return;
        }
        
        for (int j : node.list) {
            // need to skip index
            if (j != index) result.add(Arrays.asList(index, j));
        }
    }
    
    public static void main(String[] args) {
        PalindromePairs pp = new PalindromePairs();
        String[] words = {"a","abc","aba",""};
        System.out.println(pp.palindromePairs_withTrie(words));
    }
}
