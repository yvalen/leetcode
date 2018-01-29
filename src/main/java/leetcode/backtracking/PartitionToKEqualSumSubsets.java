package leetcode.backtracking;

/*
 * LEETCODE 698
 * Given an array of integers nums and a positive integer k, find whether 
 * it's possible to divide this array into k non-empty subsets whose sums 
 * are all equal.
 * Example 1: Input: nums = [4, 3, 2, 3, 5, 2, 1], k = 4 Output: True
 * Explanation: It's possible to divide it into 4 subsets 
 * (5), (1, 4), (2,3), (2,3) with equal sums.
 * Note:
 * - 1 <= k <= len(nums) <= 16.
 * - 0 < nums[i] < 10000.
 * 
 * Company: LinkedIn
 * Difficulty: medium
 * Similar Questions: 416(PartitionEqualSubsetSum)
 */
public class PartitionToKEqualSumSubsets {
    // The dfs process is to find a subset of nums[] which sum equals to sum/k.
    // We use an array visited[] to record which element in nums[] is used.
    // Each time when we get a cur_sum = sum/k, we will start from position 0 in
    // nums[] to look up the elements that are not used yet and find another
    // cur_sum = sum/k. we need to test every possible combination until we find 
    // one with backtracking
    public boolean canPartitionKSubsets(int[] nums, int k) {
        if (nums == null || nums.length == 0)
            return false;

        int sum = 0;
        for (int num : nums)
            sum += num;

        if (sum % k != 0)
            return false;

        sum /= k;
        return canPartition(nums, new boolean[nums.length], 0, sum, k, 0);
    }

    private boolean canPartition(int[] nums, boolean[] visited, int currentSum, int target, int k, int start) {
        if (k == 1)
            return true;
        if (currentSum == target)
            return canPartition(nums, visited, 0, target, k - 1, 0);
        for (int i = start; i < nums.length; i++) {
            if (!visited[i]) {
                visited[i] = true;
                if (canPartition(nums, visited, currentSum + nums[i], target, k, i + 1))
                    return true;
                visited[i] = false; // back track
            }
        }
        return false;
    }
}
