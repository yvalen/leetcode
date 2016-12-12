package leetcode.array;

import java.util.Arrays;
import java.util.Random;

/**
 * Si,ilar problem: Fisher-Yates Shuffle https://en.wikipedia.org/wiki/Fisher%E2%80%93Yates_shuffle
 *
 */
public class Shuffle {
	private int[] nums;
	private final int[] original;
	private final Random random;

	public Shuffle(int[] nums) {
		this.nums = nums;
		original = Arrays.copyOf(nums, nums.length);
		random = new Random();
	}
	
	/** Resets the array to its original configuration and return it. */
    public int[] reset() {
        return Arrays.copyOf(original, original.length);
    }
    
    /** Returns a random shuffling of the array. */
    public int[] shuffle() {
    	int len = nums.length;
    	
    	// go through each position, assign it with a randomly chosen element.
    	for (int i = 0; i < len; i++) {
    		int j = i + random.nextInt(len - i);
    		int tmp = nums[i];
    		nums[i] = nums[j];
    		nums[j] = tmp;
    	}
    	
    	return nums;
    }
	
}
