package leetcode.dp;

/*
 * LEETCODE 303
 * Given an integer array nums, find the sum of the elements between indices i
 * and j (i ≤ j), inclusive. 
 * Example: given nums = [-2, 0, 3, -5, 2, -1]
 * sumRange(0, 2) -> 1 
 * sumRange(2, 5) -> -1
 * sumRange(0, 5) -> -3 
 * Note: You may assume that the array does not change. There are many calls to sumRange
 * function.
 * 
 * Company: Palantir
 * Difficulty: easy
 * Similar Questions: 307(RangeSumQueryMutable)
 */
public class RangeSumQueryImmutable {
    private int[] sum;

    public RangeSumQueryImmutable(int[] nums) {
        this.sum = new int[nums.length];
        if (nums.length == 0)
            return;

        sum[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            sum[i] = nums[i] + sum[i - 1];
        }
    }

    public int sumRange(int i, int j) {
        if (i < 0 || j < 0 || i > sum.length || j > sum.length || j < i) {
            throw new IllegalArgumentException();
        }

        if (i == 0)
            return sum[j];

        return sum[j] - sum[i - 1];
    }

}