package leetcode.dp;

/*
 * LEETCODE 238
 * Given an array of n integers where n > 1, nums, return an array output such that 
 * output[i] is equal to the product of all the elements of nums except nums[i].
 * Solve it without division and in O(n).
 * For example, given [1,2,3,4], return [24,12,8,6].
 * Follow up: Could you solve it with constant space complexity? 
 * (Note: The output array does not count as extra space for the purpose of space complexity analysis.)
 * 
 * Company: Facebook, Microsoft, Amazon, LinkedIn, Apple
 * Difficulty: medium
 * Similar Questions: 152(MaxProductSubarray)
 */
public class ProductOfArrayExceptSelf {
    /*
     * Given numbers [2, 3, 4, 5], regarding the third number 4, the product of
     * array except 4 is 2*3*5 which consists of two parts: left 2*3 and right
     * 5. The product is left*right. We can get lefts and rights: Numbers: 2 3 4
     * 5 Lefts: 1 2 2*3 2*3*4 Rights: 3*4*5 4*5 5 1 We can calculate lefts and
     * rights in 2 loops. The time complexity is O(n).
     */
    public int[] productExceptSelf(int[] nums) {
        int[] result = new int[nums.length];

        // calculate left, store left in the result array
        result[0] = 1;
        for (int i = 1; i < nums.length; i++) {
            result[i] = nums[i - 1] * result[i - 1];
        }

        // calculate right, store right in variable right
        int right = 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            result[i] *= right;
            right *= nums[i];
        }

        return result;
    }
}
