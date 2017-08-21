package leetcode.reservoirsampling;

import java.util.Arrays;
import java.util.Random;

/*
 * Given an array of integers with possible duplicates, randomly output the index of a given target number. 
 * You can assume that the given target number must exist in the array.
 * Note: The array size can be very large. Solution that uses too much extra space will not pass the judge.
 * Example:
 * int[] nums = new int[] {1,2,3,3,3};
 * Solution solution = new Solution(nums);
 * 
 * // pick(3) should return either index 2, 3, or 4 randomly. Each index should have equal probability of returning.
 * solution.pick(3);
 * 
 * // pick(1) should return 0. Since in the array only nums[0] is equal to 1.
 * solution.pick(1);
 * 
 * Company: Facebook
 * Difficulty: medium
 */
public class RandomPickIndex {
	private final int[] nums;
	private final Random random;
	
	public RandomPickIndex(int[] nums) {
		this.nums = Arrays.copyOf(nums, nums.length);
		random = new Random();
	}
	
	/*
	 * Keep the first item in memory.
	 * When the i-th item arrives (for i > 1):
	 * - with probability 1 / i, keep the new item (discard the old one)
	 * - with probability 1 − 1 / i, keep the old item (ignore the new one)
	 * So:
	 * - when there is only one item, it is kept with probability 1;
	 * - when there are 2 items, each of them is kept with probability 1/2;
	 * - when there are 3 items, the third item is kept with probability 1/3, and each of the previous 2 items are also kept 
	 * with probability (1/2)(1-1/3) = (1/2)(2/3) = 1/3;
	 * - by induction, it is easy to prove that when there are n items, each item is kept with probability 1/n.
	 */
	public int pick(int target) {
        int result = 0, count = 0;
        for (int i = 0; i < nums.length; i++) {
        	if (nums[i] == target) {
        		if (count == random.nextInt(count+1)) result = i;
        		count++;	
        	}
        }
        return result;
    }

}
