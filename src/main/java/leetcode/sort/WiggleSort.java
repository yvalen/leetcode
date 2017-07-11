package leetcode.sort;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class WiggleSort {
	/*
	 * Given an unsorted array nums, reorder it in-place such that nums[0] <= nums[1] >= nums[2] <= nums[3]....
	 * For example, given nums = [3, 5, 2, 1, 6, 4], one possible answer is [1, 6, 2, 5, 3, 4].
	 * 
	 * Company: Google
	 * Difficulty: Medium
	 */
	// The final sorted nums needs to satisfy two conditions:
	// - If i is odd, then nums[i] >= nums[i - 1];
	// - If i is even, then nums[i] <= nums[i - 1].
	// The code is just to fix the orderings of nums that do not satisfy 1 and 2.
	// proof: 
	// if i is odd, we already have, nums[i - 2] >= nums[i - 1],
	// if nums[i - 1] <= nums[i], then we does not need to do anything, its already wiggled.
	// if nums[i - 1] > nums[i], then we swap element at i -1 and i. Due to previous wiggled elements (nums[i - 2] >= nums[i - 1]), 
	// we know after swap the sequence is ensured to be nums[i - 2] > nums[i - 1] < nums[i], which is wiggled.
	// similarly,
	// if i is even, we already have nums[i - 2] <= nums[i - 1],
	// if nums[i - 1] >= nums[i], pass
	// if nums[i - 1] < nums[i], after swap, we are sure to have wiggled nums[i - 2] < nums[i - 1] > nums[i].
	public void wiggleSort(int[] nums) {
		if (nums == null || nums.length <= 1) return;
		
		for (int i = 1; i < nums.length; i++) {
			//if ((i % 2 != 0 && nums[i] < nums[i-1]) ||
			//		(i % 2 == 0 && nums[i] > nums[i-1])){
			if (((i & 1) == 0) == (nums[i] > nums[i-1])) {
				int tmp = nums[i];
				nums[i] = nums[i-1];
				nums[i-1] = tmp;
			}
		}
    }

	/*
	 * Given an unsorted array nums, reorder it such that nums[0] < nums[1] > nums[2] < nums[3]....
	 * Example:
	 * (1) Given nums = [1, 5, 1, 1, 6, 4], one possible answer is [1, 4, 1, 5, 1, 6].
	 * (2) Given nums = [1, 3, 2, 2, 3, 1], one possible answer is [2, 3, 1, 3, 1, 2].
	 * Note: You may assume all input has valid answer.
	 * Follow Up: Can you do it in O(n) time and/or in-place with O(1) extra space?
	 * https://discuss.leetcode.com/topic/71990/summary-of-the-various-solutions-to-wiggle-sort-for-your-reference
	 * 
	 * Company: Google
	 * Difficulty: Medium
	 */
	// http://buttercola.blogspot.com/2016/01/leetcode-wiggle-sort-ii.html
	// https://discuss.leetcode.com/topic/32861/3-lines-python-with-explanation-proof
	// Time complexity: O(nlogn), Space complexity: O(n)
	public void wiggleSortII_sortAndExtraSpace(int[] nums) {
        if (nums == null || nums.length <= 1) return;
		
        // sort the array
        Arrays.sort(nums);
        
        // find the median
        // if there are odd number of elements, the left half must be 1 more than the right, not reverse. 
        // That is because the last element must be indexed as even, and since nums[even] < nums[odd], 
        // so the last number must be in the smaller half. 
        // start from the end of both halves to handle equal elements. e.g [4,5,5,6], make sure equal elements are not neighbors
        int left = (nums.length - 1) / 2, right = nums.length - 1;
        
        // pick one element from each half and form the result
        int[] result = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
        	if ((i & 1) == 0) { // even index
        		result[i] = nums[left--];
        	}
        	else { // odd index
        		result[i] = nums[right--];
        	}
        }
        System.arraycopy(result, 0, nums, 0, nums.length);
    }
	
	// https://discuss.leetcode.com/topic/32861/3-lines-python-with-explanation-proof 
	public void wiggleSortII_quickSelectAndExtraSpace(int[] nums) {
		if (nums == null || nums.length <= 1) return;
		
		int n = nums.length, m = (n+1) / 2;
		
		int median = kthSmallestElement(nums, m);
		
		int[] copy = Arrays.copyOf(nums, n);
		// three-way partition, ensure elements equal to median are in the middle
		for (int i = 0, j = 0, k = n-1 ; j <= k;) {
			if (copy[j] < median) {
				exch(copy, i++, j++);
			}
			else if (copy[j] > median) {
				exch(copy, j, k--);
			}
			else {
				j++;
			}
		}
		IntStream.of(copy).forEach(System.out::print);
		System.out.println();
		
		
		int left = m - 1, right = n-1;
		for (int i = 0; i < n; i++) {
			nums[i] = (i & 1) == 0 ? copy[left--] : copy[right--];
		}
		
	}
	
	private void exch(int[] nums, int i, int j) {
		int temp = nums[i];
		nums[i] = nums[j];
		nums[j] = temp;
	}
	
	private void shuffle(int[] nums) {
		Random random = new Random();
		for (int i = 0; i < nums.length; i++) {
			int j = i + random.nextInt(nums.length - i);
			exch(nums, i, j);
		}
	}
	
	private int kthSmallestElement(int[] nums, int k) {
		//shuffle(nums);
		IntStream.of(nums).forEach(System.out::print);
		System.out.println();
		
		int lo = 0, hi = nums.length - 1;
		while (lo < hi) {
			int p = partition(nums, lo, hi);
			if (p < k) {
				lo = p + 1;
			}
			else if (p > k) {
				hi = p - 1;
			}
			else break;
		}
		
		IntStream.of(nums).forEach(System.out::print);
		System.out.println();
		return nums[k];
	}
	
	private int partition(int[] nums, int lo, int hi) {
		int i = lo;
		for (int j = lo + 1; j <= hi; j++) {
			if (nums[j] < nums[lo]) exch(nums, ++i, j);
		}
		exch(nums, lo, i);
		return i;
	}
	
	public static void main(String[] args) {
		WiggleSort ws = new WiggleSort();
		//int[] nums = {3,5,2,1,6,4};
		//ws.wiggleSort(nums);
		
		//int[] nums = {1,1,2,1,2,2,1};
		//ws.wiggleSortII_sortAndExtraSpace(nums);
		//IntStream.of(nums).forEach(System.out::println);
		//int[] nums = {3,2,3,3,2,1,1,2,3,1,1,3,2,1,2,2,2,2,1};
		//int[] nums = {1,5,4,4,5,1,1,5,3,4,2,4,4,1,1,1,2,4,5};
		int[] nums = {4,1,4,5,2,1,4,3,4,1,2,1,1,5,5,4,5,1,4};
		ws.wiggleSortII_quickSelectAndExtraSpace(nums);
		System.out.println();
		IntStream.of(nums).forEach(System.out::print);
	}
}
