package leetcode.dp;

import java.util.Arrays;

import leetcode.array.ArrayUtil;

/*
 * LEETCODE 416
 * Given a non-empty array containing only positive integers, find if the array can be partitioned into two subsets 
 * such that the sum of elements in both subsets is equal.
 * Note: Each of the array element will not exceed 100. The array size will not exceed 200.
 * Example 1: Input: [1, 5, 11, 5] Output: true
 * Explanation: The array can be partitioned as [1, 5, 5] and [11].
 * Example 2: Input: [1, 2, 3, 5] Output: false
 * Explanation: The array cannot be partitioned into equal sum subsets.
 * 0/1 knapsack: http://love-oriented.com/pack/P01.html
 * 
 * Company: eBay
 * Difficulty: medium
 * Similar Questions: 698(PartitionToKEqualSumSubsets)
 */
public class PartitionEqualSubsetSum {
	// This problem is essentially let us to find whether there are several numbers in a set which are able to sum 
	// to a specific value (in this problem, the value is sum/2).
	// this is a 0/1 knapsack problem, for each number, we can pick it or not. Let us assume dp[i][j] means whether 
	// the specific sum j can be gotten from the first i numbers. If we can pick such a series of numbers from 0-i whose 
	// sum is j, dp[i][j] is true, otherwise it is false.
	// Base case: dp[0][0] is true; (zero number consists of sum 0 is true)
	// Transition function: For each number, if we don't pick it, dp[i][j] = dp[i-1][j], which means if the first i-1 elements 
	// has made it to j, dp[i][j] would also make it to j (we can just ignore nums[i]). If we pick nums[i], dp[i][j] = dp[i-1][j-nums[i]]
	// which represents that j is composed of the current value nums[i] and the remaining composed of other previous numbers. 
	// Thus, the transition function is dp[i][j] = dp[i-1][j] || dp[i-1][j-nums[i]]
	// Time complexity: O(n*sum), Space complexity: O(n*sum)
	public boolean canPartition_withDPArray(int[] nums) {
		if (nums == null || nums.length == 0) return false;
		
		// calculate the sum
		int sum = 0;
		for (int num : nums) {
			sum += num;
		}
		
		// sum has to be an even number
		if ((sum & 1) == 1) return false;
		
		sum /= 2;
		boolean[][] dp = new boolean[nums.length+1][sum+1];
		dp[0][0] = true; // sum of zero number is 0
		for (int i = 1; i <= nums.length; i++) {
			dp[i][0] = true; // ith number is not picked
			for (int j = 1; j <= sum; j++) {
				dp[i][j] = dp[i-1][j] || ((j >= nums[i-1]) ? dp[i-1][j-nums[i-1]] : false);
			}
		}
		
		return dp[nums.length][sum];
    }
	
	// space optimization O(sum)
	public boolean canPartition(int[] nums) {
        if (nums == null || nums.length == 0) return false;
		
		// calculate the sum
		int sum = 0;
		for (int num : nums) {
			sum += num;
		}
		
		// sum has to be an even number
		if ((sum & 1) == 1) return false;
		
		sum /= 2;
		boolean[] dp = new boolean[sum+1];
		dp[0] = true;  // sum zero requires no number
		//Arrays.fill(dp,  true);
		for (int num : nums) {
			System.out.println(num);
			for (int j = sum; j > 0; j--) { // iterate from sum to 0 because we need the value of dp[j-sum] for previous num. From 0 to sum will overwrite that value
				dp[j] = dp[j] || // dp[j] is the result calculated for the previous num
						(j >= num ? dp[j-num] : false);
			}
			ArrayUtil.printArray(dp, ",");
		}
		return dp[sum];
	}
	
	public static void main(String[] args) {
		PartitionEqualSubsetSum pess = new PartitionEqualSubsetSum();
		int[] nums = {1, 5, 11, 5};
		//System.out.println(pess.canPartition_withDPArray(nums));
		System.out.println(pess.canPartition(nums));
	}
}
