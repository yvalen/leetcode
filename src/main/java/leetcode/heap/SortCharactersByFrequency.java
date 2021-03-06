package leetcode.heap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/*
 * LEETCODE 451
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
 * Similar Questions: 347(TopKFrequentElements), 387(FirstUniqueCharacterInString)
 */
public class SortCharactersByFrequency {
	public String frequencySort_heap(String s) {
		if (s == null || s.length() <= 2)
			return s;

		Map<Character, Integer> frequencyMap = new HashMap<>();
		for (Character c : s.toCharArray()) {
			frequencyMap.put(c, frequencyMap.getOrDefault(c, 0) + 1);
		}

		// max heap, most frequent on top
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
		for (char c : s.toCharArray()) {
			frequencyMap.put(c, frequencyMap.getOrDefault(c, 0)+1);
			maxFrequency = Math.max(maxFrequency, frequencyMap.get(c));
		}

		List<Character>[] bucket = (List<Character>[]) new List[maxFrequency];
		for (Map.Entry<Character, Integer> entry : frequencyMap.entrySet()) {
			int index = entry.getValue()-1;
			if (bucket[index] == null) bucket[index] = new ArrayList<>();
			bucket[index].add(entry.getKey());
		}

		StringBuilder sb = new StringBuilder();
		for (int i = bucket.length-1; i >= 0; i--) {
			if (bucket[i] == null) continue;
			for (char c : bucket[i]) {
				for (int j = 0; j <= i; j++) sb.append(c);
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
