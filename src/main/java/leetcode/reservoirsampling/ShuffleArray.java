package leetcode.reservoirsampling;

import java.util.Arrays;
import java.util.Random;

/**
 * Shuffle a set of numbers without duplicates.
 * Example:
 * // Init an array with set 1, 2, and 3
 * int[] nums = {1,2,3};
 * Solution solution = new Solution(nums);
 * 
 * // Shuffle the array [1,2,3] and return its result. Any permutation of [1,2,3] must equally likely to be returned.
 * solution.shuffle();
 * 
 * // Resets the array back to its original configuration [1,2,3].
 * solution.reset();
 * 
 * // Returns the random shuffling of array [1,2,3].
 * solution.shuffle();
 * 
 * Fisher-Yates Shuffle https://en.wikipedia.org/wiki/Fisher%E2%80%93Yates_shuffle
 *
 * Difficulty: medium
 */
public class ShuffleArray {
	private int[] nums;
	private final int[] original;
	private final Random random;

	public ShuffleArray(int[] nums) {
		this.nums = nums;
		original = Arrays.copyOf(nums, nums.length);
		random = new Random();
	}
	
	/** Resets the array to its original configuration and return it. */
    public int[] reset() {
        return Arrays.copyOf(original, original.length);
    }
    
    /** Returns a random shuffling of the array. */
    /*
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
    */
	
    // Fisher-Yates Shuffle
    // To initialize an array a of n elements to a randomly shuffled copy of S, both 0-based: 
    // a[0] ← S[0] 
    // for i from 1 to n - 1 do 
    // 		r ← random (0 .. i) 
    //		a[i] ← a[r] 
    // 		a[r] ← S[i]
    public int[] shuffle() {
        for (int i = 0; i < nums.length; i++) {
            int j = random.nextInt(i+1);
            swap(i, j);
        }
        return nums;
    }
    
    private void swap(int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
