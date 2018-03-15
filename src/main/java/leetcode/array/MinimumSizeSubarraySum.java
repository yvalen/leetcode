package leetcode.array;

/*
 * LEETCODE 209
 * Given an array of n positive integers and a positive integer s, 
 * find the minimal length of a subarray of which the sum ≥ s. 
 * If there isn't one, return 0 instead.
 * For example, given the array [2,3,1,2,4,3] and s = 7,
 * the subarray [4,3] has the minimal length under the problem constraint. 
 * 
 * Company: Facebook
 * Difficulty: medium
 * Similar Questions: 76(MinimumWindowSubstring), 325(MaximumSizeSubArraySumEqualsK)
 */
public class MinimumSizeSubarraySum {
    public int minSubArrayLen(int s, int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;

        int sum = 0, min = Integer.MAX_VALUE;
        for (int left = 0, right = 0; right < nums.length; right++) {
            sum += nums[right];
            while (sum >= s) { // need to use while to keep checking until sum is less than s
                min = Math.min(min, right - left + 1);
                sum -= nums[left++];
            }
        }
        return min == Integer.MAX_VALUE ? 0 : min;
    }

    public static void main(String[] args) {
        MinimumSizeSubarraySum mss = new MinimumSizeSubarraySum();
        int[] nums = { 2, 3, 1, 2, 4, 3 };
        int s = 7;
        System.out.println(mss.minSubArrayLen(s, nums));
    }
}
