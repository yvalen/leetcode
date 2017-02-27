package leetcode.array;

import java.util.PriorityQueue;

/*
 * Find the kth largest element in an unsorted array. Note that it is the kth largest element in the sorted order, 
 * not the kth distinct element. For example, given [3,2,1,5,6,4] and k = 2, return 5.
 * Note: You may assume k is always valid, 1 ≤ k ≤ array's length.
 */
public class KthLargestElement {
	
	// O(nlogk) run time, O(k) space
	public int findKthLargest_withPriorityQueue(int[] nums, int k) {
		if (nums == null || nums.length == 0 || 
				k < 1 || k > nums.length) {
			throw new IllegalArgumentException("invalid input");
		}
		
		PriorityQueue<Integer> pq = new PriorityQueue<>(k);
		for (int num : nums) {
			pq.offer(num);
			if (pq.size() > k) pq.poll();
		}
		
		return pq.peek();
    }
	
	// TODO quick select algorithm
	
	public static void main(String[] args) {
		KthLargestElement k = new KthLargestElement ();
		
		int[] nums = {3,2,1,5,6,4};
		System.out.println(k.findKthLargest_withPriorityQueue(nums, 2));
		
	}

}
