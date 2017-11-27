package leetcode.array;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/*
 * LEETCODE 448
 * Given an array of integers where 1 ≤ a[i] ≤ n (n = size of array), some elements appear twice and others appear once.
 * Find all the elements of [1, n] inclusive that do not appear in this array. Could you do it without extra space and 
 * in O(n) runtime? You may assume the returned list does not count as extra space.
 * Example:
 * 	Input: [4,3,2,7,8,2,3,1]
 * 	Output: [5,6]
 * 
 * Company: Google
 * Difficulty: easy
 * Similar Questions: 41(firstMissingPositive), 442(FindAllDuplicatesInArray)
 */
public class FindAllDisappearedNumbersInArray {
	public List<Integer> findDisappearedNumbers(int[] nums) {
		if (nums == null || nums.length == 0) return Collections.emptyList();
        
		// iterate through input array, mark element as negative using 
		// nums[nums[i]-1] = -nums[nums[i]-1]. In this way all the numbers that 
		// we have seen will be marked as negative. 
		for (int num : nums) {
			// use absolute value since num could be negated already
			// Subtracting by 1 is so that we can map all integers from 1 to n using the 
			// current array (array indexing starts at 0 so value 1 in the array maps to 0 index etc) 
			int idx = Math.abs(num) -1;
			if (nums[idx] > 0) nums[idx] = -nums[idx];  // only flip the number when it is positive
		}
		
		List<Integer> result = new LinkedList<>();
		for (int i = 0; i < nums.length; i++) {
			// if a value is not marked as negative, it implies we have never 
			// seen that index before, so just add it to the return list.
			if (nums[i] > 0) result.add(i+1);
		}
		
		return result;
    }
	
	public List<Integer> findDisappearedNumbers_swap(int[] nums) {
        if (nums == null || nums.length == 0) return Collections.emptyList();
        
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            while (num > 0 && num <= nums.length && nums[num-1] != nums[i]) {
                nums[i] = nums[num-1];
                nums[num-1] = num;
                num = nums[i];
            }
            ArrayUtil.printArray(nums, ",");
        } 
        
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i + 1) result.add(i+1);
        }
        
        return result;
    }
	
	public static void main(String[] args) {
		FindAllDisappearedNumbersInArray fd = new FindAllDisappearedNumbersInArray();
		
		int[] nums = {4,3,2,7,8,2,3,1};
		System.out.println(fd.findDisappearedNumbers_swap(nums));
	}
}
