package leetcode.dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/*
 * Given an unsorted array of integers, find the length of longest increasing subsequence.
 * For example, given [10, 9, 2, 5, 3, 7, 101, 18], the longest increasing subsequence is 
 * [2, 3, 7, 101], therefore the length is 4. Note that there may be more than one LIS combination, 
 * it is only necessary for you to return the length. 
 */
public class LongestIncreasingSubsequence {	
	// Complexity: O(n^2)
	public int lengthOfLIS(int[] nums) {
		if (nums == null || nums.length == 0) return 0;
		
		int n = nums.length;
		int[] dp = new int[n];
		int maxLen = Integer.MIN_VALUE;
		for (int i = 0; i < n; i++) {
			int len = Integer.MIN_VALUE;
			for (int j = 0; j < i; j++) {
				if (nums[i] > nums[j]) {
					len = Integer.max(len, dp[j]);
				}
			}
			dp[i] = len == Integer.MIN_VALUE ? 1 : len + 1;
			maxLen = Integer.max(maxLen, dp[i]);
		}
		
		return maxLen;
    }
	
	// Complexity: O(nlogn)
	// 1. traverse from 0 to len-1, the DP array keep the longest sequence.
	// 2. if val is bigger than largest in the dp array (last element), add it to the end;
	// 3. if it is among the sequence, return the pos of the smallest element which is bigger than val
	// 4. update the array with value at this position 
	public int lengthOfLIS_binarySearchWithList(int[] nums) {
		if (nums == null || nums.length == 0) return 0;
		
		int n = nums.length;
		List<Integer> dp = new ArrayList<>(n);
		dp.add(nums[0]);
		for (int i = 1; i < n; i++) {
			if (nums[i] > dp.get(dp.size()-1)) {
				// add element to list if it greater than the last element
				dp.add(nums[i]);
			}
			else {
				int pos = Collections.binarySearch(dp, nums[i]);
				if (pos < 0) dp.set(-(pos+1), nums[i]);
			}
		}
		
		return dp.size();
    }
	
	public int lengthOfLIS_binarySearchWithArray(int[] nums) {
		if (nums == null || nums.length == 0) return 0;
		
		int[] dp = new int[nums.length];
		int len = 0;
		for (int num : nums) {
			// Searches a range of the specified array of ints for the specified value using the binary search algorithm.
			// return index of the search key, if it is contained in the array within the specified range; 
			// otherwise, (-(insertion point) - 1). The insertion point is defined as the point at which the key would be 
			// inserted into the array: the index of the first element in the range greater than the key, or toIndex if all 
			// elements in the range are less than the specified key.
			int pos = Arrays.binarySearch(dp, 0, len, num);
			if (pos < 0) pos = -(pos+1);
			dp[pos] = num;
			if (pos == len) len++; // increment length if the element is greater than the last element
		}
		
		return len;
    }
	
	// source code for Arrays.binarySearch
	private static int binarySearch0(int[] a, int fromIndex, int toIndex, int key) {
		int low = fromIndex;
		int high = toIndex - 1;
		
		while (low <= high) {
			// unsigned shift, equivalent to low+(high-low)/2
			int mid = (low + high) >>> 1;
			int midVal = a[mid];
		
			if (midVal < key) low = mid + 1;
			else if (midVal > key) high = mid - 1;
			else return mid; // key found
		}
		return -(low + 1);  // key not found.	
	}
	
	public static void main(String[] args) {
		LongestIncreasingSubsequence l = new LongestIncreasingSubsequence();
		int[] nums = {10,9,2,5,3,7,101,18};
		
		System.out.println(l.lengthOfLIS_binarySearchWithArray(nums));
	}
	
}
