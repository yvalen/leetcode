package leetcode.array;

/*
 * LEETCODE 674
 * Given an unsorted array of integers, find the length of longest continuous increasing subsequence.
 * Example 1: Input: [1,3,5,4,7] Output: 3
 * Explanation: The longest continuous increasing subsequence is [1,3,5], its length is 3. 
 * Even though [1,3,5,7] is also an increasing subsequence, it's not a continuous one where 5 and 7 are separated by 4. 
 * Example 2: Input: [2,2,2,2,2] Output: 1
 * Explanation: The longest continuous increasing subsequence is [2], its length is 1. 
 * Note: Length of the array will not exceed 10,000. 
 * 
 * Company: Facebook
 * Difficulty: easy
 * 
 */
public class LongestContinuousIncreasingSubsequence {
    public int findLengthOfLCIS_twoPointer(int[] nums) {
        if (nums == null)
            return 0;

        int n = nums.length;
        if (n <= 1)
            return n;

        int maxLen = 1;
        int i = 0, j = 1;
        while (j < n) {
            if (nums[j] <= nums[j - 1]) {
                maxLen = Math.max(maxLen, j - i);
                i = j;
            }
            j++;
        }
        return Math.max(maxLen, j - i); // need to compare maxLen with j-i to
                                        // handle nums as an increasing sequence
    }

    public int findLengthOfLCIS(int[] nums) {
        if (nums == null)
            return 0;
        int maxLen = 0, len = 1;
        for (int i = 0; i < nums.length; i++) {
            if (i == 0 || nums[i - 1] < nums[i]) {
                len++;
                maxLen = Math.max(maxLen, len);
            } else
                len = 1;
        }
        return maxLen;
    }

    public static void main(String[] args) {
        LongestContinuousIncreasingSubsequence lcis = new LongestContinuousIncreasingSubsequence();
        int[] nums = { 1, 3, 5, 4, 7 };
        // int[] nums = {2,2,2,2,2};
        // int[] nums = {1,3,5,7};
        System.out.println(lcis.findLengthOfLCIS_twoPointer(nums));
        ;
    }
}
