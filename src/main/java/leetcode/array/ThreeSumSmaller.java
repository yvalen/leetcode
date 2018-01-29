package leetcode.array;

import java.util.Arrays;

/*
 * LEETCODE 259
 * Given an array of n integers nums and a target, find the number of index 
 * triplets i, j, k with 0 <= i < j < k < n that satisfy the condition 
 * nums[i] + nums[j] + nums[k] < target.
 * For example, given nums = [-2, 0, 1, 3], and target = 2. Return 2. 
 * Because there are two triplets which sums are less than 2:
 * [-2, 0, 1]
 * [-2, 0, 3]
 * Follow up: Could you solve it in O(n^2) runtime? 
 * 
 * Company: Google
 * Difficulty: medium
 * Similar Questions: 15(3sum), 16(3sum closest)
 */
public class ThreeSumSmaller {
    // Time complexity O(n^2)
    public int threeSumSmaller(int[] nums, int target) {
        if (nums == null || nums.length == 0)
            return 0;

        // we can sort because we don't need to return the original index
        // i < j < k < n simply means one number cannot be used multiple times
        Arrays.sort(nums);

        int count = 0;
        // cannot check for nums[i] <= target here because the following number
        // may make the total smaller, i.e -4,-1,-1,0,1,2 target=5
        for (int i = 0; i < nums.length - 2; i++) { 
            for (int j = i + 1, k = nums.length - 1; j < k;) {
                int sum = nums[i] + nums[j] + nums[k];
                if (sum < target) {
                    count += k - j;
                    j++;
                } else
                    k--;
            }
        }
        return count;

        /*
         * int result = 0, n = nums.length; for (int i = 0; i < n; i++) { int j
         * = i + 1, k = n - 1; while (j < k) { int sum = nums[i] + nums[j] +
         * nums[k]; if (sum >= target) { k--; } else { result += k - j; // all
         * elements between k and j will satisfy j++; } } }
         * 
         * return result;
         */
    }

    public static void main(String[] args) {
        ThreeSumSmaller tss = new ThreeSumSmaller();
        // int[] nums = {-2, 0, 1, 3};
        int[] nums = { 3, 1, 0, -2 };
        int target = 4;
        System.out.println(tss.threeSumSmaller(nums, target));
    }
}
