package leetcode.heap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/*
 * Given a string, sort it in decreasing order based on the frequency of characters.
 * Example 1: 
 * Input: "tree"
 * Output: "eert"
 * Explanation: 'e' appears twice while 'r' and 't' both appear once. So 'e' must appear 
 * before both 'r' and 't'. Therefore "eetr" is also a valid answer.
 * 
 * Example 2:
 * Input: "cccaaa"
 * Output: "cccaaa"
 * Explanation: Both 'c' and 'a' appear three times, so "aaaccc" is also a valid answer.
 * Note that "cacaca" is incorrect, as the same characters must be together.
 * 
 * Example 3:
 * Input: "Aabb"
 * Output: "bbAa"
 * Explanation: "bbaA" is also a valid answer, but "Aabb" is incorrect.
 * Note that 'A' and 'a' are treated as two different characters.
 * 
 * Company: Amazon, Google
 * Difficulty: medium
 */
public class SortCharactersByFrequency {
    public String frequencySort_heap(String s) {
        if (s == null || s.length() <= 2)
            return s;

        Map<Character, Integer> frequencyMap = new HashMap<>();
        for (Character c : s.toCharArray()) {
            frequencyMap.put(c, frequencyMap.getOrDefault(c, 0) + 1);
        }

        PriorityQueue<Map.Entry<Character, Integer>> pq = new PriorityQueue<>((x, y) -> y.getValue() - x.getValue());
        for (Map.Entry<Character, Integer> entry : frequencyMap.entrySet()) {
            pq.offer(entry);
        }

        StringBuilder sb = new StringBuilder();
        while (!pq.isEmpty()) { // cannot iterate through pq since pq is not
                                // sorted internally
            Map.Entry<Character, Integer> entry = pq.poll();
            int frequency = entry.getValue();
            while (frequency-- > 0)
                sb.append(entry.getKey());
        }
        return sb.toString();
    }

    public String frequencySort_bucketSort(String s) {
        if (s == null || s.length() <= 2)
            return s;

        Map<Character, Integer> frequencyMap = new HashMap<>();
        int maxFrequency = 0;
        for (Character c : s.toCharArray()) {
            int frequency = frequencyMap.getOrDefault(c, 0) + 1;
            frequencyMap.put(c, frequency);
            maxFrequency = Math.max(maxFrequency, frequency);
        }

        List<List<Character>> bucket = new ArrayList<>(maxFrequency + 1);
        for (int i = 0; i <= maxFrequency; i++) {
            bucket.add(new ArrayList<>());
        }

        for (Map.Entry<Character, Integer> entry : frequencyMap.entrySet()) {
            bucket.get(entry.getValue()).add(entry.getKey());
        }

        StringBuilder sb = new StringBuilder();
        for (int i = maxFrequency; i > 0; i--) {
            List<Character> charList = bucket.get(i);
            for (Character c : charList) {
                int freq = i;
                while (freq-- > 0)
                    sb.append(c);
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        SortCharactersByFrequency scf = new SortCharactersByFrequency();
        String s = "raaeaedere";
        System.out.println(scf.frequencySort_heap(s));
    }
}
