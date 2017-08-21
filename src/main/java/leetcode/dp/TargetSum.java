package leetcode.dp;

import leetcode.array.ArrayUtil;

/*
 * You are given a list of non-negative integers, a1, a2, ..., an, and a target, S. Now you have 2 symbols + and -. 
 * For each integer, you should choose one from + and - as its new symbol. Find out how many ways to assign symbols 
 * to make sum of integers equal to target S.
 * Example 1:
 * Input: nums is [1, 1, 1, 1, 1], S is 3. 
 * Output: 5
 * Explanation: 
 * -1+1+1+1+1 = 3
 * +1-1+1+1+1 = 3
 * +1+1-1+1+1 = 3
 * +1+1+1-1+1 = 3
 * +1+1+1+1-1 = 3
 * There are 5 ways to assign symbols to make the sum of nums be target 3.
 * Note:
 * - The length of the given array is positive and will not exceed 20.
 * - The sum of elements in the given array will not exceed 1000.
 * - Your output answer is guaranteed to be fitted in a 32-bit integer.
 * 
 * Company: Google, Facebook
 * Difficulty: medium
 */
public class TargetSum {
	
	
	/*
	 * The original problem statement is equivalent to: 
	 * Find a subset of nums that need to be positive, and the rest of them negative, such that the sum is equal to target
	 * Let P be the positive subset and N be the negative subset
	 * For example: Given nums = [1, 2, 3, 4, 5] and target = 3 then one possible solution is +1-2+3-4+5 = 3. 
	 * Here positive subset is P = [1, 3, 5] and negative subset is N = [2, 4]
	 * Then let's see how this can be converted to a subset sum problem:
	 *                   sum(P) - sum(N) = target
	 * sum(P) + sum(N) + sum(P) - sum(N) = target + sum(P) + sum(N)
	 *                        2 * sum(P) = target + sum(nums)
	 * So the original problem has been converted to a subset sum problem as follows:
	 * Find a subset P of nums such that sum(P) = (target + sum(nums)) / 2
	 */
	public int findTargetSumWays_dp(int[] nums, int S) {
        int sum = 0;
        for (int num : nums) sum+= num;
        if (sum < S || ((S+sum) & 1) == 1) return 0;   
        
        sum = (S + sum) / 2;
        int[] dp = new int[sum+1];
        dp[0] = 1; // there is one way to get sum 0, choose no number
        for (int num : nums) {
        	for (int i = sum; i >= num; i--) {
        		dp[i] += dp[i-num];
        	}
        	System.out.println(num);
        	ArrayUtil.printArray(dp, ",");
        }
        return dp[sum];
    }
	
	public int findTargetSumWays_dfs(int[] nums, int S) {
		return dfs(nums, S, 0, 0);	
	}
	
	private int dfs(int[] nums, int S, int start, int sum) {
		if (start == nums.length) {
			if (sum == S) return 1;
			return 0;
		}
		
		return dfs(nums, S, start+1, sum+nums[start]) + dfs(nums, S, start+1, sum-nums[start]);
	}
	
	public static void main(String[] args) {
		TargetSum ts = new TargetSum();
		//int[] nums = {1000};
		//int S = 1000;
		int[] nums = {1, 1, 1, 1, 1};
		int S = 3;
		System.out.println(ts.findTargetSumWays_dp(nums, S));
	}
}
