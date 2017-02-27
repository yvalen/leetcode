package leetcode.array;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/*
 * Given an array of integers where 1 ≤ a[i] ≤ n (n = size of array), some elements appear twice and others appear once.
 * Find all the elements of [1, n] inclusive that do not appear in this array. Could you do it without extra space and 
 * in O(n) runtime? You may assume the returned list does not count as extra space.
 * Example:
 * 	Input: [4,3,2,7,8,2,3,1]
 * 	Output: [5,6]
 */
public class DisappearedNumbersInArray {
	public List<Integer> findDisappearedNumbers(int[] nums) {
		if (nums == null || nums.length == 0) return Collections.emptyList();
        
		List<Integer> result = new LinkedList<>();
		for (int num : nums) {
			// use absolute value since num could be negated already
			int idx = Math.abs(num) -1;
			if (nums[idx] > 0) nums[idx] = -nums[idx];
		}
		
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] > 0) result.add(i+1);
		}
		
		return result;
    }
}
