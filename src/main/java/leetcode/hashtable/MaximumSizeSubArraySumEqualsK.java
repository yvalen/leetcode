package leetcode.hashtable;

import java.util.HashMap;
import java.util.Map;

/*
 * LEETCODE 325
 * Given an array nums and a target value k, find the maximum length of a subarray 
 * that sums to k. If there isn't one, return 0 instead.
 * Note: The sum of the entire nums array is guaranteed to fit within the 32-bit 
 * signed integer range.
 * Example 1: Given nums = [1, -1, 5, -2, 3], k = 3, return 4. 
 * (because the subarray [1, -1, 5, -2] sums to 3 and is the longest)
 * Example 2: Given nums = [-2, -1, 2, 1], k = 1, return 2. 
 * (because the subarray [-1, 2] sums to 1 and is the longest)
 * Follow Up: Can you do it in O(n) time?
 * 
 * Company: Palantir, Facebook
 * Difficulty: medium
 */
public class MaximumSizeSubArraySumEqualsK {

    // DP
    // Time complexity: O(n^2), Space complexity: O(n)
    public int maxSubArrayLen_dp(int[] nums, int k) {
        if (nums == null || nums.length == 0)
            return 0;

        int maxLen = 0, n = nums.length;

        int[] sums = new int[n];
        sums[0] = nums[0];
        for (int i = 1; i < n; i++) {
            sums[i] = sums[i - 1] + nums[i];
        }

        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                int sum = sums[j] - sums[i] + nums[i];
                if (sum == k) {
                    maxLen = Math.max(maxLen, j - i + 1);
                }
            }
        }

        return maxLen;
    }

    // hash table
    // Time complexity: O(n), Space complexity: O(n)
    public int maxSubArrayLen(int[] nums, int k) {
        if (nums == null || nums.length == 0)
            return 0;

        int maxLen = 0, sum = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i]; // current sum starting from the beginning of the
                            // array
            if (sum == k)
                maxLen = i + 1;
            else if (map.containsKey(sum - k))
                maxLen = Math.max(maxLen, i - map.get(sum - k));

            // only update map the first time because we want the start of the
            // subarray as left as possible
            map.putIfAbsent(sum, i);
        }

        return maxLen;
    }

    public static void main(String[] args) {
        MaximumSizeSubArraySumEqualsK msk = new MaximumSizeSubArraySumEqualsK();
        int[] nums = { 1, -1, 5, -2, 3 };
        int k = 3;
        System.out.println(msk.maxSubArrayLen_dp(nums, k));
    }
}
