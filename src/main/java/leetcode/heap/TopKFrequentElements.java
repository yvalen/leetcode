package leetcode.heap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/*
 * Given a non-empty array of integers, return the k most frequent elements.
 * For example, Given [1,1,1,2,2,3] and k = 2, return [1,2].
 * Note:
 * - You may assume k is always valid, and there are 1 to k number of unique elements.
 * - Your algorithm's time complexity must be better than O(n log n), where n is the array's size.
 * 
 * Company: Pocket Gem, Yelp
 * Difficulty: medium
 */
public class TopKFrequentElements {
	private static final class Element implements Comparable<Element> {
		private final int value;
		private final int count;
		Element(int value, int count) {
			this.value = value;
			this.count = count;
		}
		
		public int getValue() {
			return value;
		}

		public int getCount() {
			return count;
		}
		
		@Override
		public int compareTo(Element o) {
			return this.count - o.getCount();
		}
	}
	
	// Time complexity: O(n) + O(nlogk) ~ O(nlogk)
	public List<Integer> topKFrequent_heap(int[] nums, int k) {
		Map<Integer, Integer> countMap = new HashMap<>();
		for (int num : nums) {
			countMap.put(num, countMap.getOrDefault(num, 0) + 1);
		}
		
		//PriorityQueue<Element> pq = new PriorityQueue<>(k);
		PriorityQueue<Map.Entry<Integer, Integer>> pq = new PriorityQueue<>(k, 
				(e1, e2) -> e1.getValue() - e2.getValue());
		for (Map.Entry<Integer, Integer> entry: countMap.entrySet()) {
			//Element elem = new Element(entry.getKey(), entry.getValue());
			if (pq.size() < k) {
				//pq.offer(elem);
				pq.offer(entry);
			}
			//else if (pq.peek().compareTo(elem) < 0) {
			else if (pq.peek().getValue() < entry.getValue()) {	
				pq.poll();
				pq.offer(entry);
				//pq.offer(elem);
			}
		}
		
        LinkedList<Integer> result = new LinkedList<>();
        while (!pq.isEmpty()) {
        	//result.addFirst(pq.poll().getValue());
        	result.addFirst(pq.poll().getKey());
        }
        return result;
    }
	
	// Time Complexity: O(n)
	public List<Integer> topKFrequent_bucketSort(int[] nums, int k) {
		int maxCount = 0;
		Map<Integer, Integer> countMap = new HashMap<>();
		for (int num : nums) {
			int count = countMap.getOrDefault(num, 0) + 1;
			countMap.put(num, count);
			maxCount = Math.max(maxCount, count);
		}
		
		// for each element at position i in bucket, i is the frequency, 
		// bucket.get(i) is the number that has that frequency 
		List<List<Integer>> bucket = new ArrayList<>(maxCount);
		for (int i = 0; i <= maxCount; i++) {
			bucket.add(new ArrayList<>());
		}
		
		for (Map.Entry<Integer, Integer> entry: countMap.entrySet()) {
			Integer count = entry.getValue();
			bucket.get(count).add(entry.getKey());
		}
		
		List<Integer> result = new ArrayList<>(k);
		for (int i= bucket.size()-1; i > 0; i--) {
			for (Integer num : bucket.get(i)) {
				result.add(num);
				k--;
				if (k == 0) return result;
			}
		}
		return result;
	}
	
	public static void main(String[] args) {
		TopKFrequentElements tkfe = new TopKFrequentElements();
		int[] nums = {1,1,1,2,2,3};
		int k = 2;
		System.out.println(tkfe.topKFrequent_heap(nums, k));
	}
}
