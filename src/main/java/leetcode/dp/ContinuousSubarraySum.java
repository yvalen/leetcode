package leetcode.dp;

import java.util.HashMap;
import java.util.Map;

/*
 * LEETCODE 523
 * Given a list of non-negative numbers and a target integer k, write a function to check if the array has a continuous 
 * subarray of size at least 2 that sums up to the multiple of k, that is, sums up to n*k where n is also an integer.
 * Example 1: Input: [23, 2, 4, 6, 7],  k=6 Output: True
 * Explanation: Because [2, 4] is a continuous subarray of size 2 and sums up to 6.
 * Example 2: Input: [23, 2, 6, 4, 7],  k=6 Output: True
 * Explanation: Because [23, 2, 6, 4, 7] is an continuous subarray of size 5 and sums up to 42.
 * Note:
 * - The length of the array won't exceed 10,000.
 * - You may assume the sum of all the numbers is in the range of a signed 32-bit integer.
 * 
 * Company: Facebook
 * Difficulty: medium
 * Similar Questions: 560(SubarraySumEqualsK)
 */
public class ContinuousSubarraySum {
	// Time complexity: O(n^2), Space complexity: O(1)
	public boolean checkSubarraySum_bruteforce(int[] nums, int k) {
        if (nums == null || nums.length == 0) return false;
        for (int i = 0; i < nums.length; i++) {
            int sum  = nums[i];
            for (int j = i+1; j < nums.length; j++) {
                sum += nums[j];
                if (sum == k || (k != 0 && sum % k == 0)) return true; // need to consider k is 0
            }
        }
        return false;
        
        /* 
        // Space : O(n)
        int n = nums.length;
        int[] sum = new int[n];
        sum[0] = nums[0];
        for (int i = 1; i < n; i++) {
        	sum[i] = nums[i] + sum[i-1];
        }
        for (int start = 0; start < n -1; start++) {
        	for (int end = start+1; end < n; end++) {
        		int s = sum[end] - sum[start] + nums[start];
        		if (s == k || (k != 0 && s % k == 0)) return true;
        	}
        }
		return false;
		*/
    }
	
	// (a+(n*x))%x is same as (a%x)
	// a = cumulative sum at ith element, a+n*x = cumulative sum at jth element
	// Time complexity: O(n), Space complexity: O(min(n, k)), hash map contains min(n, k) pairs
	public boolean checkSubarraySum_modularMap(int[] nums, int k) {
        if (nums == null || nums.length == 0) return false;
        
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0,  -1);  // handle [0, 0], 0
        int sum = 0;
        for (int i = 0 ; i < nums.length; i++) {
        	sum += nums[i];
        	if (k != 0) sum %= k;
        	System.out.println(sum);
        	if (map.containsKey(sum)) {
        		if (i - map.get(sum) > 1) return true; // ensure subarray has at least 2 elements
        	}
        	else { // only put into map if it doesn't exist 
        		map.put(sum, i);
        	}
        }
        return false;
	}
	
	public static void main(String[] args) {
		ContinuousSubarraySum css = new ContinuousSubarraySum();
		//int[] nums = {23, 2, 4, 6, 7};
		//int[] nums = {23, 2, 6, 4, 7};
		//int k = 6;
		//int[] nums = {0, 1, 0};
		//int[] nums = {0, 0};
		//int k = 0;
		int[] nums = {2, 5, 33, 6, 7, 25, 15};
		int k = 13;
		System.out.println(css.checkSubarraySum_modularMap(nums, k));
	}
}
