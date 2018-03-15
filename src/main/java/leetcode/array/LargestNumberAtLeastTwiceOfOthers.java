package leetcode.array;

/*
 * LEETCODE 747
 * In a given integer array nums, there is always exactly one largest element.
 * Find whether the largest element in the array is at least twice as much as 
 * every other number in the array. If it is, return the index of the largest 
 * element, otherwise return -1.
 * Example 1:
 * Input: nums = [3, 6, 1, 0]
 * Output: 1
 * Explanation: 6 is the largest integer, and for every other number in the array 
 * x, 6 is more than twice as big as x.  The index of value 6 is 1, so we return 1.
 * Example 2:
 * Input: nums = [1, 2, 3, 4]
 * Output: -1
 * Explanation: 4 isn't at least as big as twice the value of 3, so we return -1.
 * Note:
 * - nums will have a length in the range [1, 50].
 * - Every nums[i] will be an integer in the range [0, 99].
 * 
 * Company: Google
 * Difficulty: easy
 */
public class LargestNumberAtLeastTwiceOfOthers {
    public static int dominantIndex_onePass(int[] nums) {
        if (nums == null || nums.length == 0) return -1;
        if (nums.length == 1) return 0;
        
        int max = Integer.MIN_VALUE + 1, secMax = Integer.MIN_VALUE, maxIdx = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > max) {
                secMax = max;
                max = nums[i];
                maxIdx = i;
            }
            else if (nums[i] > secMax) {
                secMax = nums[i];
            }
        }
        if (secMax*2 > max) return -1;
        
        return maxIdx;
    }
    
    
    public static int dominantIndex_twoPass(int[] nums) {
        int max = nums[0], maxIdx = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > max) {
                max = nums[i];
                maxIdx = i;
            }
        }
        
        max /= 2;
        for (int i = 0; i < nums.length; i++) {
            // need to check skip max during this check
            if (nums[i] > max && maxIdx != i) {
                return -1;
            }
        }
        return maxIdx;
    }
    
    public static void main(String[] args) {
        //int[] nums = {0, 0, 0, 1};
        int[] nums = {2, 0, 0, 3};
        System.out.println(dominantIndex_twoPass(nums));
    }
}
