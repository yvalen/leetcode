package leetcode.dp;

import java.util.Arrays;

/*
 * LEETCODE 377
 * Given an integer array with all positive numbers and no duplicates, 
 * find the number of possible combinations that add up to a positive integer target.
 * Example: nums = [1, 2, 3] target = 4
 * The possible combination ways are:
 * 	(1, 1, 1, 1)
 * 	(1, 1, 2)
 * 	(1, 2, 1)
 * 	(1, 3)
 * 	(2, 1, 1)
 * 	(2, 2)
 * 	(3, 1)
 * Note that different sequences are counted as different combinations.
 * Therefore the output is 7.
 * Follow up: https://discuss.leetcode.com/topic/52227/7-liner-in-python-and-follow-up-question/2
 * － What if negative numbers are allowed in the given array?
 * － How does it change the problem?
 * － What limitation we need to add to the question to allow negative numbers? 
 * 
 * Company: Google, Facebook, Snapchat
 * Difficulty: medium
 * Similar Questions: 39(Combination Sum)
 */
public class CombinationSumIV {
	public int combinationSum4_recursive(int[] nums, int target) {
		if (nums == null || nums.length == 0) return 0;
		
		if (target == 0) return 1; // target reached, there is one solution 
		
		int result = 0;
		for (int i = 0; i < nums.length; i++) {
			if (target >= nums[i]) {
				result += combinationSum4_recursive(nums, target - nums[i]);
			}
		}
		return result;
    }
	
	// top down DP
	public int combinationSum4_topDown(int[] nums, int target) {
		if (nums == null || nums.length == 0) return 0;
		
		// stores the number of combinations for 0...target
		int[] dp = new int[target+1]; 
		
		// pre-fill dp array with -1 which indicates it hasn't been calculated
		// cannot use 0 here because 0 means no combination for target
		Arrays.fill(dp, -1);
		
		dp[0] = 1;
		topDown_helper(nums, target, dp);
		
		return dp[target];
    }
	
	private int topDown_helper(int[] nums, int target, int[] dp) {
		if (dp[target] != - 1) {
			return dp[target];
		}
		
		int result = 0;
		for (int i = 0; i < nums.length; i++) {
			if (target >= nums[i]) {
				result += topDown_helper(nums, target - nums[i], dp);
			}
		}
		dp[target] = result;
		return result;
	}
	
	// bottom-up dp
	public int combinationSum4_bottomUp(int[] nums, int target) {
		if (nums == null || nums.length == 0) return 0;
		
		// stores the number of combinations for 0...target
		int[] dp = new int[target+1]; 
		dp[0] = 1;
		for (int i = 1; i <= target; i++) {
			for (int j = 0; j < nums.length; j++) {
				if (i >= nums[j]) {
					dp[i] += dp[i - nums[j]];
					System.out.println("i=" + i + " j=" + j + " num=" + nums[j] + " dp[i]=" + dp[i] + " dp[i-nums[j]]=" + dp[i - nums[j]]);
				}
			}
		}
		
		return dp[target];
    }
	
	public static void main(String[] args) {
		CombinationSumIV c = new CombinationSumIV();
		
		int[] nums = {1, 2, 3};
		int target = 4;
		System.out.println(c.combinationSum4_bottomUp(nums, target));
	}
 }
