package leetcode.dp;

import leetcode.array.SubArray;

/**
 * LEETCODE 53 Find the contiguous subarray within an array (containing at least
 * one number) which has the largest sum. For example, given the array
 * [-2,1,-3,4,-1,2,1,-5,4], the contiguous subarray [4,-1,2,1] has the largest
 * sum = 6.
 * 
 * Company: Microsoft, Bloomberg, LinkedIn Difficukty: easy Similar Question:
 * 121(Best time to buy and sell stock),
 */
public class MaximumSumSubArray {
    public int maxSubArray_dpTwoDArray(int[] nums) {
        int n = nums.length;

        int[][] dp = new int[n][2];
        dp[0][0] = nums[0];
        dp[0][1] = nums[0];
        for (int i = 1; i < n; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1]);
            dp[i][1] = Math.max(dp[i - 1][1] + nums[i], nums[i]);
        }

        return Math.max(dp[n - 1][0], dp[n - 1][1]);
    }

    // DP with one dimensional array
    // Kandane's Algorithm
    // if we know the maximum subarray sum ending at position i (call this Bi)
    // the maximum subarray sum ending at position i+1 either includes the
    // maximum
    // subarray sum ending at i as a prefix or it doesn't
    public int maxSubArray_dpOneDArray(int[] nums) {
        int n = nums.length, max = nums[0];
        int[] dp = new int[n];
        dp[0] = nums[0];
        for (int i = 1; i < n; i++) {
            // To calculate sum(0...i) there are 2 choices: either adding
            // sum(0,i-1) to a[i], or not.
            // If sum(0,i-1) is negative, adding it to a[i] will only make a
            // smaller sum, so we add only if it's non-negative.
            dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]);
            max = Math.max(max, dp[i]);
        }
        return max;
    }

    // DP O(1) space, only dp[i-1] is needed, use a variable to track it
    public int maxSubArray_dp(int[] nums) {
        int n = nums.length, max = nums[0], sum = nums[0];
        for (int i = 1; i < n; i++) {
            sum = Math.max(sum + nums[i], nums[i]);
            max = Math.max(max, sum);
        }
        return max;
    }

    //
    // Divide and Conquer O(nlgn)
    //
    public int maxSubArray_divideAndConquer(int[] nums) {
        if (nums == null || nums.length == 0)
            return Integer.MIN_VALUE;

        return maxSubArray_divideAndConquerHelper(nums, 0, nums.length - 1);
    }

    private int maxSubArray_divideAndConquerHelper(int[] nums, int low, int high) {
        if (low == high) {
            return nums[low];
        }

        int mid = (low + high) / 2;

        int leftMax = maxSubArray_divideAndConquerHelper(nums, low, mid);
        int rightMax = maxSubArray_divideAndConquerHelper(nums, mid + 1, high);
        int crossingMax = maxSubArrayCrossing(nums, low, mid, high);

        int result = Math.max(leftMax, rightMax);
        return Math.max(result, crossingMax);
    }

    private int maxSubArrayCrossing(int[] nums, int low, int mid, int high) {
        int leftMax = nums[mid], rightMax = nums[mid + 1];
        int leftSum = nums[mid], rightSum = nums[mid + 1];
        for (int i = mid - 1; i >= low; i--) {
            leftSum += nums[i];
            leftMax = Math.max(leftSum, leftMax);
        }

        for (int i = mid + 2; i <= high; i++) {
            rightSum += nums[i];
            rightMax = Math.max(rightSum, rightMax);
        }

        return leftMax + rightMax;
    }

    public static void main(String[] args) {
        MaximumSumSubArray s = new MaximumSumSubArray();

        int[] nums = { -2, 1, -3, 4, -1, 2, 1, -5, 4 };
        System.out.println(s.maxSubArray_dpOneDArray(nums));
    }
}
