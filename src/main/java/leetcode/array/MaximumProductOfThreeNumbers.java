package leetcode.array;

/*
 * LEETCODE 628
 * Given an integer array, find three numbers whose product is maximum and output the maximum product.
 * Example 1: Input: [1,2,3] Output: 6
 * Example 2: Input: [1,2,3,4] Output: 24
 * Note:
 * - The length of the given array will be in range [3,10^4] and all elements are in the range [-1000, 1000].
 * - Multiplication of any three numbers in the input won't exceed the range of 32-bit signed integer.
 * 
 * Company: Intuit
 * Difficulty: easy
 */
public class MaximumProductOfThreeNumbers {
    public int maximumProduct(int[] nums) {
        int max1 = -1001, max2 = -1001, max3 = -1001;
        int min1 = 1001, min2 = 1001;
        for (int num : nums) {
            if (num > max1) {
                max3 = max2;
                max2 = max1;
                max1 = num;
            } else if (num > max2) {
                max3 = max2;
                max2 = num;
            } else if (num > max3) {
                max3 = num;
            }

            if (num < min1) { // need to use if here instead of else if,
                              // otherwise min1 and min2 will not be set for
                              // increasing sequence
                min2 = min1;
                min1 = num;
            } else if (num < min2) {
                min2 = num;
            }
        }

        if (min2 > 0)
            return max1 * max2 * max3;

        return Math.max(min1 * min2 * max1, max1 * max2 * max3);
    }

    public static void main(String[] args) {
        MaximumProductOfThreeNumbers mptn = new MaximumProductOfThreeNumbers();
        // int[] nums = {1, 2, 3};
        int[] nums = { -4, -3, -2, -1, 60 };
        System.out.println(mptn.maximumProduct(nums));
    }

}
