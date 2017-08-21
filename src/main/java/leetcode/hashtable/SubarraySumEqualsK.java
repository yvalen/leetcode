package leetcode.hashtable;

import java.util.HashMap;
import java.util.Map;

/*
 * Given an array of integers and an integer k, you need to find the total number of continuous subarrays whose sum equals to k.
 * Example 1: Input:nums = [1,1,1], k = 2 Output: 2
 * Note:
 * - The length of the array is in range [1, 20,000].
 * - The range of numbers in the array is [-1000, 1000] and the range of the integer k is [-1e7, 1e7].
 * 
 * Company: Google
 * Difficulty: medium
 */
public class SubarraySumEqualsK {

	// Time complexity: O(n^2), Space complexity: O(1)
	public int subarraySum_cumulativeSum(int[] nums, int k) {
		if (nums == null || nums.length == 0) return 0;
		
        int count = 0, n = nums.length;
        for (int i = 0; i < n; i++) {
        	int sum = 0;
        	for (int j = i; j < n; j++) {
        		sum += nums[j];
        		if (sum == k) count++;
        	}
        }
        
        return count;
    }
	
	// if the cumulative sum up to two indices, say i and j is at a difference of k i.e. if sum[i]−sum[j]=k 
	// the sum of elements lying between indices i and j is k.
	// Time complexity: O(n), Space complexity: O(n)
	public int subarraySum_hashMap(int[] nums, int k) {
		if (nums == null || nums.length == 0) return 0;
		
        int count = 0, sum = 0;
        Map<Integer, Integer> map = new HashMap<>(); // map stores the number of times a sum has occurred
        map.put(0, 1); 
        for (int i = 0; i < nums.length; i++) {
        	sum += nums[i];  // cumulative sum up to i
        	if (map.containsKey(sum -k)) count += map.get(sum-k);
        	map.put(sum, map.getOrDefault(sum, 0)+1);
        }
        
        return count;
    }
	
}
