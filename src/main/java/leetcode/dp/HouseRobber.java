package leetcode.dp;

// 0-1 knapsack problem explanation:
// https://www.quora.com/Whats-an-intuitive-explanation-for-the-0-1-knapsack-problem-in-data-structures-and-algorithms

public class HouseRobber {
	
	/** 
	 * You are a professional robber planning to rob houses along a street. Each house has a 
	 * certain amount of dp stashed, the only constraint stopping you from robbing each of 
	 * them is that adjacent houses have security system connected and it will automatically 
	 * contact the police if two adjacent houses were broken into on the same night.
	 * Given a list of non-negative integers representing the amount of dp of each house, 
	 * determine the maximum amount of dp you can rob tonight without alerting the police.
	 */
	public int rob_withDPArray(int[] nums) {
		if (nums == null || nums.length == 0) return 0;
		
		int n = nums.length;
		if (n == 1) return nums[0];
		
		int[] dp = new int[n];
		dp[0] = nums[0];
		dp[1] = Math.max(nums[0], nums[1]); // when processing 2nd element, need to compare it with the 1st
		for (int i = 2; i < n; i ++) {
			dp[i] = Math.max(dp[i-2]+nums[i], dp[i-1]);
		}
		
		return dp[n-1];
    }
	
	public int rob_spaceOptimization(int[] nums) {
		if (nums == null || nums.length == 0) return 0;
		
		int n = nums.length;
		if (n == 1) return nums[0];
		
		// max money up to previous house
		int prev = nums[0];
		
		// max money to current house
		int curr = Math.max(nums[0], nums[1]);
		
		for (int i = 2; i < n; i ++) {
			int tmp = curr;
			curr = Math.max(prev + nums[i], curr);
			prev = tmp;
		}
		
		return curr;
    }
	
	// https://discuss.leetcode.com/topic/14375/simple-ac-solution-in-java-in-o-n-with-explanation
	public int rob(int[] nums) {
		if (nums == null || nums.length == 0) return 0;
		
		int robPrev = 0, notRobPrev = 0;
		for (int i : nums) {
			int temp = notRobPrev;
			notRobPrev = Math.max(notRobPrev, robPrev);
			robPrev = i + temp;
		}
		
		return Math.max(robPrev, notRobPrev);
    }
	
	/**
	 * After robbing those houses on that street, the thief has found himself a new place for his thievery 
	 * so that he will not get too much attention. This time, all houses at this place are arranged in a circle. 
	 * That means the first house is the neighbor of the last one. Meanwhile, the security system for these 
	 * houses remain the same as for those in the previous street.
	 * Given a list of non-negative integers representing the amount of money of each house, determine the maximum 
	 * amount of money you can rob tonight without alerting the police.
	 */
	public int robCircle(int[] nums) {
		if (nums == null || nums.length == 0) return 0;
		
		if (nums.length == 1) return nums[0];
		
        return Math.max(robCircleHelper(nums, 0, nums.length-2), robCircleHelper(nums, 1, nums.length-1));
		
    }
	
	private int robCircleHelper(int[] nums, int start, int end) {
		int prevYes = 0, prevNo = 0;
		for (int i = start; i <=end; i++) {
			int temp = prevNo;
			prevNo = Math.max(prevYes, prevNo);
			prevYes = temp + nums[i];
		}
		return Math.max(prevYes, prevNo);
    }
}
