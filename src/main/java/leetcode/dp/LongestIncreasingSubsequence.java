package leetcode.dp;

/*
 * Given an unsorted array of integers, find the length of longest increasing subsequence.
 * For example, given [10, 9, 2, 5, 3, 7, 101, 18], the longest increasing subsequence is 
 * [2, 3, 7, 101], therefore the length is 4. Note that there may be more than one LIS combination, 
 * it is only necessary for you to return the length. 
 */
public class LongestIncreasingSubsequence {
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
	
	public static void main(String[] args) {
		LongestIncreasingSubsequence l = new LongestIncreasingSubsequence();
		int[] nums = {10,9,2,5,3,7,101,18};
		
		System.out.println(l.lengthOfLIS(nums));
	}
	
}
