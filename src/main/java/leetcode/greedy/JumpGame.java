package leetcode.greedy;

import java.util.Arrays;

public class JumpGame {
	/*
	 * Given an array of non-negative integers, you are initially positioned at the first index of the array.
	 * Each element in the array represents your maximum jump length at that position.
	 * Determine if you are able to reach the last index. For example:
	 * 	A = [2,3,1,1,4], return true.
	 * 	A = [3,2,1,0,4], return false. 
	 */
	// Greedy. Complexity: O(n) - time, O(1) -space 
	// From a given position, when we try to see if we can jump to a GOOD position, 
	// we only ever use one - the first one. In other words, the left-most one. 
	// Iterating right-to-left, for each position we check if there is a potential jump that 
	// reaches a GOOD index (currPosition + nums[currPosition] >= leftmostGoodIndex). 
	// If we can reach a GOOD index, then our position is itself GOOD. Also, this new GOOD position 
	// will be the new leftmost GOOD index. Iteration continues until the beginning of the array. 
	// If first position is a GOOD index then we can reach the last index from the first position.
	public boolean canJump(int[] nums) {
        int lastpos = nums.length-1;
        for (int i = nums.length - 2; i >=0; i--) {
        	if (i + nums[i] >= lastpos) {
        		lastpos = i;
        	}	
        }
		return lastpos == 0;
    }

	// Try every jump pattern from first position to last. Start from the first position 
	// and jump to every index that is reachable until the last index is reached. Backtrack when stuck
	// Complexity: O(2^n) - time, O(n) - space (for recursion calls)
	public boolean canJump_backtrack(int[] nums) {
		return backtrackHelper(0, nums);
	}
	
	private boolean backtrackHelper(int position, int[] nums) {
		if (position == nums.length - 1) return true;
		
		int furthestPosition = Integer.min(nums.length-1, position+nums[position]);
		for (int nextPosition = position+1; nextPosition <= furthestPosition; nextPosition++) {
			if (backtrackHelper(nextPosition, nums)) return true;
		}
		return false;
	}
	
	// Top down DP
	// Complexity: O(n^2) - time, O(n) - space
	public boolean canJump_dpTopDown(int[] nums) {
		// dp array stores whehther the current index is good or bad
		// 1 - good, 2 - bad, 0 - not processed
		int[] dp = new int[nums.length];
		dp[nums.length - 1] = 1;
		return topDownHelper(nums, dp, 0);
	}
	
	private boolean topDownHelper(int[] nums, int[] dp, int position) {
		if (dp[position] != 0) return dp[position] == 1;
		
		int furthestPosition = Integer.min(nums.length, position + nums[position]);
		for (int nextPosition = position + 1; nextPosition <= furthestPosition; nextPosition++) {
			if (topDownHelper(nums, dp, nextPosition)) {
				dp[position] = 1;
				return true;
			}
		}
		
		dp[position] = 2;
		return false;
	}
	
	// bottom up DP
	// Complexity: O(n^2) - time, O(n) - space
	public boolean canJump_dpBottomUp(int[] nums) {
		// dp array stores whehther the current index is good or bad
		// 1 - good, 2 - bad, 0 - not processed
		int[] dp = new int[nums.length];
		dp[nums.length - 1] = 1;
		
		for (int i = nums.length-2; i >= 0; i--) {
			int furthesetPosition = Integer.min(nums.length-1, i+nums[i]);
			for (int j = i+1; j <= furthesetPosition; j++) {
				if (dp[j] == 1) {
					dp[i] = 1;
					break;
				}
			}	
		}
		
		return dp[0] == 1;
	}
	
	
	/*
	 * Given an array of non-negative integers, you are initially positioned at the first index of the array.
	 * Each element in the array represents your maximum jump length at that position. Your goal is to reach 
	 * the last index in the minimum number of jumps. For example, given array A = [2,3,1,1,4]
	 * The minimum number of jumps to reach the last index is 2. (Jump 1 step from index 0 to 1, then 3 steps 
	 * to the last index.)
	 * Note: You can assume that you can always reach the last index.
	 */
	public int jump_DP(int[] nums) {
		
		int[] dp = new int[nums.length];
		Arrays.fill(dp, nums.length+1);
		//System.out.println(Arrays.toString(dp));
		dp[nums.length-1] = 0;
		for (int i = nums.length-2; i >= 0; i--) {
			int furthestPosition = Integer.min(nums.length-1, i+nums[i]);
			for (int j = i+1; j <= furthestPosition; j++) {
				dp[i] = Integer.min(dp[i], dp[j]+1);
			}
			//System.out.println(Arrays.toString(dp));
		}
		
		return dp[0];
    }
	
	public int jump_greedy(int nums[]) {
		int result = 0, currentEnd = 0, currentFurthest = 0;
		for (int i = 0; i < nums.length - 1; i++) {
			// currentFurthest is the farthest point that all points before currentEnd can reach
			currentFurthest = Integer.max(currentFurthest,  i + nums[i]);
			if  (currentFurthest >= nums.length - 1) {
				result++;
				break;
			}
			if (i == currentEnd) {
				// trigger another jump, update currentEnd
				result++;
				currentEnd = currentFurthest;
			}
		}
		return result;
	}
	
	public static void main(String[] args) {
		JumpGame j = new JumpGame();
		//int[] nums = {5,4,3,2,1,0,0};
		//int[] nums = {2, 4, 2, 1, 0, 2, 0};
		//System.out.println(j.canJump_dpBottomUp(nums));
		
		int[] nums = {2, 3, 1, 1, 4};
		System.out.println(j.jump_DP(nums));
	}
	
}
