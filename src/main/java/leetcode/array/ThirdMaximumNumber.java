package leetcode.array;

import java.util.TreeSet;

/**
 * Given a non-empty array of integers, return the third maximum number in this array. 
 * If it does not exist, return the maximum distinct number. The time complexity must be in O(n).
 */
public class ThirdMaximumNumber {
	
	public int thirdMax(int[] nums) {
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
		
	}
	
}
