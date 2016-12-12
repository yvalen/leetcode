package leetcode.array;

import java.util.ArrayList;
import java.util.List;

public class MajorityElement {
	/**
	 * Given an array of size n, find the majority element. The majority element is 
	 * the element that appears more than ⌊ n/2 ⌋ times. You may assume that the array 
	 * is non-empty and the majority element always exist in the array.
	 * 
	 * Moore’s Voting Algorithm
	 * http://www.cs.utexas.edu/~moore/best-ideas/mjrty/
	 * https://www.quora.com/What-is-the-proof-of-correctness-of-Moores-voting-algorithm
	 */
	public int majorityElement(int[] nums) {
		int result = nums[0], count = 1;
		for (int i = 1; i < nums.length; i++) {
			if (nums[i] == result) {
				count++;
			}
			else {
				count--;
			}
			if (count == 0) {
				result = nums[i];
				count = 1;
			}
		}
        
		return result;
    }
	
	/**
	 * Given an integer array of size n, find all elements that appear more than ⌊ n/3 ⌋ times. 
	 * The algorithm should run in linear time and in O(1) space.
	 */
	public List<Integer> majorityElement2(int[] nums) {
		int r1 = 0, r2 = 1; // initialize with any two different numbers
		int c1 = 0, c2 = 0, n = nums.length;
		for (int i = 0; i < n; i++) {
			if (nums[i] == r1) {
				c1++;
			}
			else if (nums[i] == r2) {
				c2++;
			}
			else if (c1 == 0) {
				r1 = nums[i];
				c1 = 1;
			}
			else if (c2 == 0) {
				r2 = nums[i];
				c2 = 1;
			}
			else {
				c1--;
				c2--;
			}
			
			
		}
		
		c1 =0;
		c2 =0;
		for (int i = 0; i < n; i++) {
			if (nums[i] == r1) c1++;
			else if (nums[i] == r2) c2++;
		}
        
		List<Integer> result = new ArrayList<>();
		if (c1 > n/3 ) result.add(r1);
		if (c2 > n/3) result.add(r2);
		
		return result;
    }
	
	
	public static void main(String[] args) {
		MajorityElement m = new MajorityElement(); 
		int[] nums = {2, 2};
		/*
		int result = m.majorityElement(nums);
		System.out.println(result);
		*/
		
		List<Integer> result = m.majorityElement2(nums);
		System.out.println(result);
		
	}

	


}
