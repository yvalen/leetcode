package leetcode.trie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/*
 * LEETCODE 692
 * Given a non-empty list of words, return the k most frequent elements. 
 * Your answer should be sorted by frequency from highest to lowest. 
 * If two words have the same frequency, then the word with the lower 
 * alphabetical order comes first.
 * Example 1:
 * Input: ["i", "love", "leetcode", "i", "love", "coding"], k = 2
 * Output: ["i", "love"]
 * Explanation: "i" and "love" are the two most frequent words. Note that "i" 
 * comes before "love" due to a lower alphabetical order.
 * Example 2:
 * Input: ["the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"], k = 4
 * Output: ["the", "is", "sunny", "day"]
 * Explanation: "the", "is", "sunny" and "day" are the four most frequent words, 
 * with the number of occurrence being 4, 3, 2 and 1 respectively.
 * Note:
 * - You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
 * - Input words contain only lower case letters.
 * Follow up:
 * - Try to solve it in O(n log k) time and O(n) extra space.
 * - Can you solve it in O(n) time with only O(k) extra space?
 * 
 * Company: Amazon, Bloomberg, Uber, Yelp, PocketGems
 * Difficulty: medium
 * Similar Questions: 347(TopKFrequentElements)
 */
public class TopKFrequentWords {
    // Time complexity: O(nlogk)
    // Space complexity: O(n) - count map
    public List<String> topKFrequent_withPriorityQueue(String[] words, int k) {
        Map<String, Integer> countMap = new HashMap<>();
        for (String word : words) {
            countMap.put(word, countMap.getOrDefault(word, 0) + 1);
        }

        // worst candidate on top
        PriorityQueue<Map.Entry<String, Integer>> pq = new PriorityQueue<>(k, 
                (a, b) -> a.getValue() == b.getValue() ? 
                        b.getKey().compareTo(a.getKey()) : a.getValue() - b.getValue());
        for (Map.Entry<String, Integer> entry : countMap.entrySet()) {
            if (pq.size() < k) {
                pq.offer(entry);
            } else {
                Map.Entry<String, Integer> top = pq.peek();
                if (top.getValue() < entry.getValue()
                        || (top.getValue() == entry.getValue() && top.getKey().compareTo(entry.getKey()) > 0)) {
                    pq.poll();
                    pq.offer(entry);
                }
            }
        }

        LinkedList<String> result = new LinkedList<>();
        while (!pq.isEmpty()) {
            result.addFirst(pq.poll().getKey());
        }
        return result;
    }
    
    
    // 1. calculate the frequency of each word and store the result in a hashmap.
    // 2. use bucket sort to store words, the minimum frequency is greater than or equal to 1 
    // and the maximum frequency is less than or equal to the length of the input string array.
    // 3. define a trie within each bucket to store all the words with the same frequency. 
    // With Trie, it ensures that the lower alphabetical word will be met first, saving the 
    // trouble to sort the words within the bucket.
    // Time complexity: O(n)
    public List<String> topKFrequent(String[] words, int k) {
        Map<String, Integer> countMap = new HashMap<>();
        for (String word : words) {
            countMap.put(word, countMap.getOrDefault(word, 0) + 1);
        }
        
        Trie[] tries = new Trie[words.length+1];
        for (Map.Entry<String, Integer> entry : countMap.entrySet()) {
            int count = entry.getValue();
            if (tries[count] == null) tries[count] = new Trie();
            tries[count].insert(entry.getKey());
        }
        
        List<String> result = new ArrayList<>();
        for (int i = words.length; i >= 0; i--) {
            if (tries[i] == null) continue;
            tries[i].appendWords(k, result);
            if (result.size() == k) break;
        }
        
        return result;
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
                if (node.next[c-'a'] == null) {
                    node.next[c-'a'] = new Node();
                }
                node = node.next[c-'a'];
            }
            node.isWord = true;
        }
        
        void appendWords(int k, List<String> words) {
            appendWords(root, "", words, k);
        }
        
        void appendWords(Node node, String prefix, List<String> words, int k) {
            if (node == null) return;
            if (node.isWord) words.add(prefix);
            if (words.size() == k) return;  
            for (int i = 0; i < 26; i++) {
                if (node.next[i] != null) {
                    appendWords(node.next[i], prefix+String.valueOf((char)('a'+i)), words, k);
                }
            }
        }
    }
    
    public static void main(String[] args) {
        TopKFrequentWords tkfq = new TopKFrequentWords();
        //String[] words = {"the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"};
        //int k = 4;
        String[] words = {"i", "love", "leetcode", "i", "love", "coding"};
        int k = 2;
        System.out.println(tkfq.topKFrequent(words, k));
    }

}
