package leetcode.hashtable;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*
 * Given scores of N athletes, find their relative ranks and the people with the top three highest scores, 
 * who will be awarded medals: "Gold Medal", "Silver Medal" and "Bronze Medal".
 * Example 1: Input: [5, 4, 3, 2, 1], Output: ["Gold Medal", "Silver Medal", "Bronze Medal", "4", "5"]
 * Explanation: The first three athletes got the top three highest scores, so they got "Gold Medal", "Silver Medal" 
 * and "Bronze Medal". For the left two athletes, you just need to output their relative ranks according to their scores.
 * Note:
 * - N is a positive integer and won't exceed 10,000.
 * - All the scores of athletes are guaranteed to be unique.
 * 
 * Company: Google
 */
public class RelativeRanks {
	public String[] findRelativeRanks(int[] nums) {
        if (nums == null || nums.length == 0) return new String[] {};
        
        int n = nums.length;
        String[] result = new String[n];
        Map<Integer, Integer> idxMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
        	idxMap.put(nums[i], i);
        }
        
        Arrays.sort(nums);
       
        result[idxMap.get(nums[n-1])] = "Gold Medal"; 
        if (n > 1) {
        	result[idxMap.get(nums[n-2])] = "Silver Medal"; 
        }
        
        if (n > 2) {
        	result[idxMap.get(nums[n-3])] = "Bronze Medal"; 
        }
        for (int i = 4; i <= n; i++) {
        	result[idxMap.get(nums[n-i])] = String.valueOf(i);
        }
        
        return result;
    }
	
	public static void main(String[] args) {
		RelativeRanks rr = new RelativeRanks();
		int[] nums = {5, 4, 3, 2, 1};
		String[] result = rr.findRelativeRanks(nums);
		for (String r : result) {
			System.out.print(r + ",");
		}
	}
}
