package leetcode.trie;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/*
 * LEETCODE 692
 * Given a non-empty list of words, return the k most frequent elements. Your answer should be sorted by frequency 
 * from highest to lowest. If two words have the same frequency, then the word with the lower alphabetical order comes first.
 * Example 1:
 * Input: ["i", "love", "leetcode", "i", "love", "coding"], k = 2
 * Output: ["i", "love"]
 * Explanation: "i" and "love" are the two most frequent words. Note that "i" comes before "love" due to a lower alphabetical order.
 * Example 2:
 * Input: ["the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"], k = 4
 * Output: ["the", "is", "sunny", "day"]
 * Explanation: "the", "is", "sunny" and "day" are the four most frequent words, with the number of occurrence being 4, 3, 2 and 1 respectively.
 * Note:
 * - You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
 * - Input words contain only lowercase letters.
 * Follow up:
 * - Try to solve it in O(n log k) time and O(n) extra space.
 * - Can you solve it in O(n) time with only O(k) extra space?
 * 
 * Company: Amazon, Bloomberg, Uber, Yelp, PocketGems
 * Difficulty: medium
 * Similar Questions: 347(TopKFrequentElements)
 */
public class TopKFrequentWords {
    public List<String> topKFrequent_withPriorityQueue(String[] words, int k) {
        Map<String, Integer> countMap = new HashMap<>();
        for (String word : words) {
            countMap.put(word, countMap.getOrDefault(word, 0) + 1);
        }

        PriorityQueue<Map.Entry<String, Integer>> pq = new PriorityQueue<>(k, (a, b) -> a.getValue() == b.getValue()
                ? b.getKey().compareTo(a.getKey()) : a.getValue() - b.getValue());
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

}
