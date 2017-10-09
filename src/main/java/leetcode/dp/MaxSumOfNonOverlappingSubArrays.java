package leetcode.dp;

import leetcode.array.ArrayUtil;

/*
 * LEETCODE 689
 * In a given array nums of positive integers, find three non-overlapping subarrays with maximum sum.
 * Each subarray will be of size k, and we want to maximize the sum of all 3*k entries.
 * Return the result as a list of indices representing the starting position of each interval (0-indexed). 
 * If there are multiple answers, return the lexicographically smallest one.
 * Example: Input: [1,2,1,2,6,7,5,1], 2  Output: [0, 3, 5]
 * Explanation: Subarrays [1, 2], [2, 6], [7, 5] correspond to the starting indices [0, 3, 5].
 * We could have also taken [2, 1], but an answer of [1, 3, 5] would be lexicographically larger.
 * Note:
 * - nums.length will be between 1 and 20000.
 * - nums[i] will be between 1 and 65535.
 * - k will be between 1 and floor(nums.length / 3).
 * 
 * Company: Google, Facebook
 * Difficulty: medium
 * Similar Questions: 123(BuyAndSellStock.maxProfit)
 */
public class MaxSumOfNonOverlappingSubArrays {
	// for middle interval [i, i+k-1], where k <= i <= n-2*k
	// the left interval is in range [0, i-1], the right interval is in range [i+1, n-1]
	// we can test every possible index of middle interval [k, n-2k], and get the corresponding
	// left and right max sum intervals from DP
	// Time complexity: O(n)
	public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
		int n = nums.length;
		
		int[] sum = new int[n+1];
		for (int i = 1; i <= n; i++) sum[i] = sum[i-1] + nums[i-1];
	
		 // DP for starting index of the left max sum interval
		int[] posLeft = new int[n];  // posLeft[i] is the starting index for the left intervals in [0,i]
		for (int i = k, maxLeft = sum[k]-sum[0]; i < n; i++) {  // start from interval [0,k], slides  right, i is the end position of the interval 
			if (sum[i+1]-sum[i+1-k] > maxLeft) { // find a bigger interval
				maxLeft = sum[i+1]-sum[i+1-k];
				posLeft[i] = i+1-k;
			}
			else {
				posLeft[i] = posLeft[i-1];  // use the previous index
 			}
		}
		
		// DP for starting index of the right max sum interval
		// caution: the condition is ">= maxRight" for right interval, and "> maxLeft" for left interval
		// this will always select the left most interval when two intervals have the same sum
		// this is to get lexicographical smallest order,
		int[] posRight = new int[n]; // posRight[i] is the starting index for the right interval in [i, n-1]
		posRight[n-k] = n - k;
		for (int i = n-k-1, maxRight = sum[n]-sum[n-k]; i >= 0; i--) {
			if (sum[i+k] - sum[i] >= maxRight) {
				maxRight = sum[i+k] - sum[i];
				posRight[i] = i;
			}
			else {
				posRight[i] = posRight[i+1];
			}
		}
		
		// test all possible middle interval
		int[] result = new int[3];
		int maxSum = 0;
		for (int i = k; i <= n-2*k; i++) {
			int l = posLeft[i-1], r = posRight[i+k];
			int s = (sum[i+k]-sum[i]) + (sum[l+k]-sum[l]) + (sum[r+k]-sum[r]);
			if (maxSum < s) {
				maxSum = s;
				result[0] = l; 
				result[1] = i; 
				result[2] = r;
			}
		}
		
		return result;
    }
	
	public static void main(String[] args) {
		MaxSumOfNonOverlappingSubArrays msnosa = new MaxSumOfNonOverlappingSubArrays();
		int[] nums = {1,2,1,2,6,7,5,1};
		int k = 2;
		ArrayUtil.printArray(msnosa.maxSumOfThreeSubarrays(nums, k));
	}
}
