package leetcode.heap;

import java.util.Deque;
import java.util.LinkedList;

/*
 * Given an array nums, there is a sliding window of size k which is moving from the very left of the array to the very right. 
 * You can only see the k numbers in the window. Each time the sliding window moves right by one position.
 * For example, given nums = [1,3,-1,-3,5,3,6,7], and k = 3.
 * Window position                Max
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       3
 * 1 [3  -1  -3]  5  3  6  7       3
 * 1  3  [-1  -3  5] 3  6  7       5
 * 1  3  -1  [-3  5  3] 6  7       5
 * 1  3  -1  -3  [5  3  6] 7       6
 * 1  3  -1  -3  5  [3  6  7]      7
 * Therefore, return the max sliding window as [3,3,5,5,6,7].
 * Note: you may assume k is always valid, ie: 1 ≤ k ≤ input array's size for non-empty array.
 * Follow up: Could you solve it in linear time?
 * 
 * https://people.cs.uct.ac.za/~ksmith/articles/sliding_window_minimum.html
 */
public class SlidingWindowMaximum {
	// Time complexity O(n)
	public int[] maxSlidingWindow(int[] nums, int k) {
		if (nums == null || nums.length == 0) return new int[0];
		
		int n = nums.length;
		int[] result = new int[n-k+1];
		
		// stores the index, elements are in the same order they appear in the array
		Deque<Integer> dq = new LinkedList<>();
		for (int i = 0, j=0; i < n; i++) {
			// remove elements out of range
			// deque only contains elements from [i-(k-1), i]
			while (!dq.isEmpty() && dq.peekFirst() < i-k+1) {
				dq.poll();
			}
			
			// discard elements smaller than nums[i] in deque
			// if nums[x]<nums[i] where x<i, nums[x] has no chance to be the max in range 
			// [i-(k-1), i] or any subsequent window as a[i] will always be a better candidate
			while (!dq.isEmpty() && nums[dq.peekLast()] < nums[i]) {
				dq.removeLast();
			}
			
			dq.offer(i);
			
			// head element is always the max in range
			if (i >= k - 1) {
				result[j++] = nums[dq.peekFirst()];
			}
		}
		
    
		return result;
    }
	
	public static void main(String[] args) {
		SlidingWindowMaximum sw = new SlidingWindowMaximum();
		int[] nums = {1,3,-1,-3,5,3,6,7};
		int k = 3;
		sw.maxSlidingWindow(nums, k);
	}
}
