package leetcode.bits;

import java.util.Arrays;

/*
 * Given an array containing n distinct numbers taken from 0, 1, 2, ..., n, find the one that is missing from the array.
 * For example, given nums = [0, 1, 3] return 2.
 * Note: your algorithm should run in linear runtime complexity. Could you implement it using only constant extra space complexity? 
 */
public class MissingNumber {
	
	public int missingNumber_withSum1(int[] nums) {
        int sum = nums.length;
        for (int i = 0; i < nums.length; i++) {
        	sum += i;
        	sum -= nums[i];
        }
        return sum;
    }
	
	// sum([1,n]) = n * (n+1) / 2
	public int missingNumber_withSum(int[] nums) {
		if (nums == null || nums.length == 0) return 0;
		
		int sum = nums.length * (nums.length + 1) / 2;
		for (int i = 0; i < nums.length; i++) {
			sum -= nums[i];
		}
		return sum;
    }
	
	// https://discuss.leetcode.com/topic/50315/a-summary-how-to-use-bit-manipulation-to-solve-problems-easily-and-efficiently
	// A number XOR itself will become 0, any number XOR with 0 will stay unchanged. 
	// So if every number from 1...n XOR with itself except the missing number, 
	// the result will be the missing number.
	public int missingNumber_withXOR(int[] nums) {
		if (nums == null || nums.length == 0) return 0;
		
		int result = nums.length;
		for (int i = 0; i < nums.length; i++) {
			result ^= i;
			result ^= nums[i];
		}
		return result;
    }
	
	public int missingNumber_withBinarySearch(int[] nums) {
		Arrays.sort(nums);
		int left = 0, right = nums.length - 1;
		while (left <= right) {
			int mid =  left + (right - left) / 2;
			if (nums[mid] > mid)  right = mid - 1; // something missing on the left
			else left = mid + 1;
		}
		
		return left;
    }
}
