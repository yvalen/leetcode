package leetcode.array;

import java.util.Arrays;

/*
 * LEETCODE 611
 * Given an array consists of non-negative integers, your task is to count the number of triplets chosen 
 * from the array that can make triangles if we take them as side lengths of a triangle.
 * Example 1: Input: [2,2,3,4] Output: 3
 * Explanation: Valid combinations are: 
 * 2,3,4 (using the first 2)
 * 2,3,4 (using the second 2)
 * 2,2,3
 * Note:
 * - The length of the given array won't exceed 1000.
 * - The integers in the given array are in the range of [0, 1000].
 * Valid triangle: The sum of the lengths of any two sides of a triangle is greater than the length of the third side.
 * 
 * Company: Expedia
 * Difficulty: medium
 * Similar Questions: 259(ThreeSumSmaller)
 */
public class ValidTriangleNumber {
    public int triangleNumber(int[] nums) {
        if (nums == null || nums.length < 3)
            return 0;
        Arrays.sort(nums);
        int count = 0;
        for (int i = nums.length - 1; i >= 2; i--) {
            for (int j = 0, k = i - 1; j < k;) {
                int sum = nums[j] + nums[k];
                if (sum > nums[i]) {
                    count += k - j;
                    k--;
                } else {
                    j++;
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        ValidTriangleNumber vtn = new ValidTriangleNumber();
        int[] nums = { 2, 2, 3, 4 };
        System.out.println(vtn.triangleNumber(nums));
    }
}
