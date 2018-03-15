package leetcode.array;

/*
 * LEETCODE 713
 * Your are given an array of positive integers nums. Count and print the 
 * number of (contiguous) subarrays where the product of all the elements 
 * in the subarray is less than k.
 * Example 1:
 * Input: nums = [10, 5, 2, 6], k = 100
 * Output: 8
 * Explanation: The 8 subarrays that have product less than 100 are: 
 * [10], [5], [2], [6], [10, 5], [5, 2], [2, 6], [5, 2, 6].
 * Note that [10, 5, 2] is not included as the product of 100 is not strictly 
 * less than k.
 * Note:
 * - 0 < nums.length <= 50000.
 * - 0 < nums[i] < 1000.
 * - 0 <= k < 10^6.
 * 
 * Company: Yatra
 * Difficulty: medium
 * Similar Questions: 325(MaximumSizeSubArraySumEqualsK)
 */
public class SubarrayProductLessThanK {
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        int product = 1, count = 0;
        for (int left = 0, right = 0; right < nums.length; right++) {
            product *= nums[right];
            while (left <= right && product >= k) {
                product /= nums[left++];
            }
            // now the length of valid subarray is right-left+1,
            // since the array have only positive numbers, all subarrays
            // with length 1 ~ right-left+1 ending with nums[right] are valid,
            // and there are right-left+1 of them.
            count += right-left+1;
        }
        return count;
    }

}
