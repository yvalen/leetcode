package leetcode.binarysearch;

public class SearchInRotatedSortedArray {
	/*
	 * LEETCODE 33
	 * Suppose a sorted array is rotated at some pivot unknown to you beforehand.
	 * (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2). You are given a target value to search. 
	 * If found in the array return its index, otherwise return -1.
	 * You may assume no duplicate exists in the array.
	 * 
	 * Company: Facebook, Microsoft, Bloomberg, Uber, LinkedIn
	 * Difficulty: medium                         
	 * Similar Question: 81,(Search in Rotated Sorted Array II), 153(Find Minimum in Rotated Sorted Array)
	 */
	public int search(int[] nums, int target) {
		if (nums == null || nums.length == 0) return -1;
		
		int lo = 0, hi = nums.length - 1;
		while (lo <= hi) { // need to check for equals here
			int mid = lo + (hi - lo) / 2;
			 
			if (target == nums[mid]) return mid;
			
			if (nums[mid] < nums[hi]) {
				if (nums[mid] < target && target <= nums[hi]) {
					lo = mid + 1;
				}
				else {
					hi = mid -1;
				}
			}
			else {
				if (nums[lo] <= target && target < nums[mid]) {
					hi = mid - 1;
				}
				else {
					lo = mid + 1;
				}		
			}
		}
        
		return -1;
    }
	
	/*
	 * LEETCODE 81
	 * Follow up for "Search in Rotated Sorted Array": What if duplicates are allowed?
	 * 
	 * Difficulty: medium
	 * Similar Questions: 33(Search in Rotated Sorted Array)
	 */
	public boolean search_withDuplicate(int[] nums, int target) {
		if (nums == null) return false;
		
		int lo = 0, hi = nums.length - 1;
		while (lo <= hi) {
			int mid = lo + (hi - lo) / 2;
			
			if (target == nums[mid]) return true;
			
			if (nums[mid] == nums[hi]) {
				hi--;
			}
			else if (nums[mid] == nums[lo]) {
				lo++;
			}
			else if (nums[mid] < nums[hi]) {
				if (nums[mid] < target && target <= nums[hi]) {
					lo = mid + 1;
				}
				else {
					hi = mid -1;
				}
			}
			else {
				if (nums[lo] <= target && target < nums[mid]) {
					hi = mid - 1;
				}
				else {
					lo = mid + 1;
				}		
			}
		}
        
		return false;
    }
	
	public static void main(String[] args) {
		SearchInRotatedSortedArray  srsa = new SearchInRotatedSortedArray ();
		int[] nums = {1,3,5};
		int target = 1;
		System.out.println(srsa.search_withDuplicate(nums, target));
	}
}
