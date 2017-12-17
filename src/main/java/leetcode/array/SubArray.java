package leetcode.array;

public class SubArray {
    /**
     * Given an array of n positive integers and a positive integer s, find the
     * minimal length of a subarray of which the sum ≥ s. If there isn't one,
     * return 0 instead. For example, given the array [2,3,1,2,4,3] and s = 7,
     * the subarray [4,3] has the minimal length under the problem constraint.
     */
    // O(n)
    public int minSubArrayLen_linear(int s, int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;

        int minLen = Integer.MAX_VALUE, sum = 0;
        for (int i = 0, j = 0; j < nums.length; j++) {
            sum += nums[j];
            while (sum >= s && i <= j) {
                minLen = Math.min(minLen, j - i + 1);
                sum -= nums[i];
                i++;
            }

        }
        return minLen == Integer.MAX_VALUE ? 0 : minLen;
    }

    public static void main(String[] args) {
        SubArray s = new SubArray();

        /*
         * int[] nums = {-2,1,-3,4,-1,2,1,-5,4}; int maxSubArray =
         * s.maxSubArray_divideAndConquer(nums);
         * System.out.println(maxSubArray);
         */

        int[] nums = { 2, 3, 1, 2, 4, 3 };
        int minLen = s.minSubArrayLen_linear(7, nums);
        System.out.println(minLen);
    }

}
