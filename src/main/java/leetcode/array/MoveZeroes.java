package leetcode.array;

import java.util.stream.IntStream;

/**
 *  Given an array nums, write a function to move all 0's to the end of it 
 *  while maintaining the relative order of the non-zero elements.
 *  For example, given nums = [0, 1, 0, 3, 12], after calling your function, 
 *  nums should be [1, 3, 12, 0, 0]. 
 *  Note: you must do this in-place without making a copy of the array.
 *  Minimize the total number of operations.
 *  
 *  Company: Bloomberg, Facebook
 *  Difficulty: easy
 */
public class MoveZeroes {
	public void moveZeroes(int[] nums) {
        int i = 0, j = 0;
        
        // locate the first zero element and have i points to it
        while (i < nums.length && nums[i] != 0) {
        	i++;
        	j++;
        }
        
        // j points to the first non zero element
        while (++j < nums.length) {
        	if (nums[j] != 0) {
        		nums[i] = nums[j];
        		nums[j] = 0;
        		i++;
        	}
        }	
    }
	
	public static void main(String[] args) {
		MoveZeroes m = new MoveZeroes();
		
		int[] nums = {0,1,0,3,12};
		m.moveZeroes(nums);
		IntStream.of(nums).forEach(System.out::println);
	}
	

}
