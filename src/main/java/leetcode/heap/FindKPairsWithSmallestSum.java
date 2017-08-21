package leetcode.heap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

/*
 * You are given two integer arrays nums1 and nums2 sorted in ascending order and an integer k. Define a pair (u,v) which consists of one 
 * element from the first array and one element from the second array. Find the k pairs (u1,v1),(u2,v2) ...(uk,vk) with the smallest sums.
 * Example 1: Given nums1 = [1,7,11], nums2 = [2,4,6],  k = 3 Return: [1,2],[1,4],[1,6]
 * The first 3 pairs are returned from the sequence: [1,2],[1,4],[1,6],[7,2],[7,4],[11,2],[7,6],[11,4],[11,6]
 * Example 2: Given nums1 = [1,1,2], nums2 = [1,2,3],  k = 2 Return: [1,1],[1,1]
 * The first 2 pairs are returned from the sequence: [1,1],[1,1],[1,2],[2,1],[1,2],[2,2],[1,3],[1,3],[2,3]
 * Example 3: Given nums1 = [1,2], nums2 = [3],  k = 3 Return: [1,3],[2,3]
 * All possible pairs are returned from the sequence: [1,3],[2,3]
 * 
 * Company: Google, Uber
 * Difficulty: medium
 */
public class FindKPairsWithSmallestSum {
	
	// Time complexity: O(mnlogk) Space complexity: O(k)
	public List<int[]> kSmallestPairs_bruteforce(int[] nums1, int[] nums2, int k) {
		if (nums1 == null || nums1.length == 0 || nums2 == null || nums2.length == 0 || k <= 0) return Collections.emptyList();
		
		PriorityQueue<int[]> pq = new PriorityQueue<>(k, (x, y) -> (y[0]+y[1]-x[0]-x[1])); // max heap
		for (int i = 0; i < nums1.length; i++) {
			for (int j = 0; j < nums2.length; j++) {
				if (pq.size() < k) {
					pq.offer(new int[] {nums1[i], nums2[j]});
					continue;
				}
				int[] elem = pq.peek();
				if (nums1[i]+nums2[j] < elem[0]+elem[1]) {
					pq.poll();
					pq.offer(new int[] {nums1[i], nums2[j]});
				}
			}
		}
		return new ArrayList<>(pq);
    }
	
	public List<int[]> kSmallestPairs(int[] nums1, int[] nums2, int k) {
		if (nums1 == null || nums1.length == 0 || nums2 == null || nums2.length == 0 || k <= 0) return Collections.emptyList();
		
		List<int[]> result = new ArrayList<>();
		/*
		PriorityQueue<int[]> pq = new PriorityQueue<>(k, (x, y) -> nums1[x[0]]+nums2[x[1]]-nums1[y[0]]-nums2[y[1]]); // min index heap
		// start with the very first pair
		pq.offer(new int[] {0, 0});
		
		while (!pq.isEmpty() && result.size() < k) {
			// pop the smallest from min heap and add it to result
			int[] pair = pq.poll();
			int i = pair[0]; // pointer in nums1
			int j = pair[1]; // pointer in nums2
			result.add(new int[] {nums1[i], nums2[j]});
			
			// add the next two possible smallest pairs
			if (j < nums2.length - 1) {
				pq.offer(new int[] {i, j+1});
			}
			if (j == 0 && i < nums1.length - 1) pq.offer(new int[]{i+1, j});
		}
		*/
		
		// Time complexity: O(m+klogm), Space complexity: O(m)
		// priority queue contains an array of the current element from nums1 and nums2, 
		// and the index of nums2 thst is being processed 
		PriorityQueue<int[]> pq = new PriorityQueue<>(k, (x, y) -> x[0]+x[1]-y[0]-y[1]);
		for (int i = 0; i < nums1.length && i < k; i++) {
			pq.offer(new int[] {nums1[i], nums2[0], 0}); // add all elements from nums1 and the first elem from nums2
		}
		
		while (k-- > 0 && !pq.isEmpty()) {
			int[] current = pq.poll();
			result.add(new int[] {current[0], current[1]});
			if (current[2] == nums2.length - 1) continue; // reach the end of the row
			pq.offer(new int[] {current[0], nums2[current[2]+1], current[2]+1});
		}
		
		return result;
    }
	
	

	public static void main(String[] args) {
		FindKPairsWithSmallestSum kpss = new FindKPairsWithSmallestSum();
		//int[] nums1 = {1, 7, 11}, nums2 = {2, 4, 6};
		//int k = 3;
		int[] nums1 = {1, 1, 2}, nums2 = {1, 2, 3};
		int k = 10;
		List<int[]> result = kpss.kSmallestPairs(nums1, nums2, k);
		result.stream().forEach(p -> System.out.println(p[0] + "," + p[1]));
	}
}
