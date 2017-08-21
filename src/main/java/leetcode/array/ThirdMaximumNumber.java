package leetcode.array;

import java.util.TreeSet;

/**
 * Given a non-empty array of integers, return the third maximum number in this array. 
 * If it does not exist, return the maximum distinct number. The time complexity must be in O(n).
 * 
 * Company: Amazon
 * Difficulty: easy
 */
public class ThirdMaximumNumber {
	
	public int thirdMax_withSet(int[] nums) {
		TreeSet<Integer> maxSet = new TreeSet<>();
		for (int i = 0; i < nums.length; i++) {
			if (maxSet.size() < 3) {
				maxSet.add(nums[i]);
			}
			else if (!maxSet.contains(nums[i]) && nums[i] > maxSet.first()) {
				maxSet.pollFirst();
				maxSet.add(nums[i]);
			}
		}
		
		return (maxSet.size() < 3) ? maxSet.last() :  maxSet.first();
    }
	
	// O(1) space
	public int thirdMax(int[] nums) {
		Integer max1 = null, max2 = null , max3 = null;
		for (Integer num : nums) {
			if (num.equals(max1) || num.equals(max2) || num.equals(max3)) continue;
			
			if (max1 == null || num > max1) {
				max3 = max2;
				max2 = max1;
				max1 = num;
			} else if (max2 == null || num > max2) {
				max3 = max2;
				max2 = num;
			} else if (max3 == null || num > max3) {
				max3 = num;
			}
		}
		return max3 == null ? max1 : max3;
	}
	

	
	public static void main(String[] args) {
		ThirdMaximumNumber test = new ThirdMaximumNumber();
		int[] nums = {1,2,2,5,3,5};
		int out = test.thirdMax(nums);
		System.out.println(out);
		
		
		// Input: [3, 2, 1], Output: 1
		int[] nums1 = {3, 2, 1};
		int out1 = test.thirdMax(nums1);
		System.out.println(out1);
		
		// Input: [1, 2], Output: 2
		int[] nums2 = {1, 2};
		int out2 = test.thirdMax(nums2);
		System.out.println(out2);
		
		// Input: [2, 2, 3, 1], Output: 1
		// The third maximum here means the third maximum distinct number.
		int[] nums3 = {2, 2, 3, 1};
		int out3 = test.thirdMax(nums3);
		System.out.println(out3);
		
		// Input: [1,2,-2147483648], Output: -2147483648
		
	}
	
}
