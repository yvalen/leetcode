package leetcode.dp;

/*
 * LEETCODE 42
 * Given n non-negative integers representing an elevation map where 
 * the width of each bar is 1, compute how much water it is able to 
 * trap after raining.
 * For example, given [0,1,0,2,1,0,1,3,2,1,2,1], return 6. 
 * 
 * Company: Google, Amazon, Bloomberg, Twitter, Apple, Zenefits
 * Difficulty: hard
 * Similar Questions: 11(ContainerWithMostWater), 238(ProductOfArrayExceptSelf), 
 * 407(TrappingRainWaterII)
 */
public class TrappingRainWater {
    //
    // For each element in the array, the maximum level of water it can trap
    // equals to
    // the minimum of the maximum bars on both sides minus its own height
    //

    // DP
    // 1. Find maximum height of bar from the left end up to index i in the
    // array max_left
    // 2. Find maximum height of bar from the right end up to index i in the
    // array max_right.
    // 3. Iterate over the height array and update ans by adding
    // min(max_left[i],max_right[i])−height[i] to it
    // Time complexity: O(n) Space complexity: O(n)
    public int trap_dp(int[] height) {
        if (height == null || height.length < 3)
            return 0;

        int n = height.length;

        int[] maxLeft = new int[n];
        maxLeft[0] = height[0];
        for (int i = 1; i < n; i++) {
            maxLeft[i] = Math.max(height[i], maxLeft[i - 1]);
        }

        int[] maxRight = new int[n];
        maxRight[n - 1] = height[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            maxRight[i] = Math.max(height[i], maxRight[i + 1]);
        }

        int result = 0;
        for (int i = 1; i < n - 1; i++) {
            result += Math.min(maxLeft[i], maxRight[i]) - height[i];
        }

        return result;
    }

    // based on DP approach, we notice that as long as the max_right[i] >
    // max_left[i], the water trapped depends upon the left_max, index 0 through
    // 6.
    // similarly when max_left[i] > max_right[i], the water trapped depends upon
    // the right_max, index 8 through 11
    // Time complexity: O(n), one pass Space complexity: O(1)
    public static int trap_twoPointers(int[] height) {
        int l = 0, r = height.length - 1;
        int maxLeft = 0, maxRight = 0, max = 0;
        while (l < r) {
            maxLeft = Integer.max(maxLeft, height[l]);
            maxRight = Integer.max(maxRight, height[r]);
            if (maxLeft < maxRight) {
                max += maxLeft - height[l];
                l++;
            } else {
                max += maxRight - height[r];
                r--;
            }
        }

        return max;
    }
    
    public static void main(String[] args) {
        int[] height = {0,1,0,2,1,0,1,3,2,1,2,1};
        System.out.println(trap_twoPointers(height));
    }
}
