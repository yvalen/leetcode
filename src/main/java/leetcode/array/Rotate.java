package leetcode.array;

public class Rotate {
	/**
	 * Rotate an array of n elements to the right by k steps.
	 * For example, with n = 7 and k = 3, the array [1,2,3,4,5,6,7] is rotated to [5,6,7,1,2,3,4]. 
	 */
	public void rotate(int[] nums, int k) {
		int n = nums.length;
		if (n<=1 || k== 0 || k == n) return;
		if (k > n) k = k%n;
        reverse(nums, 0, n-k-1);
        reverse(nums, n-k, n-1);
		reverse(nums, 0, n-1);
    }
	
	private void reverse(int[] nums, int start, int end) {
		while (start < end) {
			int temp = nums[start];
			nums[start] = nums[end];
			nums[end] = temp;
			start++;
			end--;
		}
	}
	
	
	/**
	 * Suppose a sorted array is rotated at some pivot unknown to you beforehand.
	 * (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2). Find the minimum element.
	 * You may assume no duplicate exists in the array.
	 */
	// O(n)
	public int findMin(int[] nums) {
		if (nums == null || nums.length == 0) return Integer.MIN_VALUE;
		
		int min = nums[0];
		for (int i = 1; i < nums.length; i ++) {
			if (nums[i] < nums[i-1]) {
				min = nums[i];
				break;
			}
		}
		
		return min;
    }
	// O(lgN)
	public int findMin_binarySearch(int[] nums) {
		if (nums == null || nums.length == 0) return Integer.MIN_VALUE;
		
		int n = nums.length;
		if (nums[0] < nums[n-1]) {
			// not rotated
			return nums[0];
		}
		
		int lo = 0, hi = n -1;
		while (lo < hi - 1) {
			int mid = lo + (hi-lo)/2;
			if (nums[lo] < nums[mid]) {
				// left half is in increasing order
				// min is in the right half
				lo = mid;
			}
			else {
				hi = mid;
			}			
		}
		
		return Math.min(nums[lo], nums[hi]);
    }
	
	
	/**
	 * Follow up for "Find Minimum in Rotated Sorted Array":
	 * What if duplicates are allowed?
	 * Would this affect the run-time complexity? How and why?
	 * Worst case all elements are same, O(n)
	 */
	public int findMinWithDuplicate_binarySearch(int[] nums) {
		if (nums == null || nums.length == 0) return Integer.MIN_VALUE;
		
		
		int lo = 0, hi = nums.length -1;
		int min = nums[0];
		while (lo < hi - 1) {
			int mid = (lo + hi) / 2;
			
			if (nums[lo] < nums[mid]) {
				min = Math.min(min, nums[lo]);
				lo = mid + 1;
			}
			else if (nums[lo] > nums[mid]) {
				min = Math.min(min, nums[mid]);
				hi = mid - 1;
			}
			else {
				lo++;
			}
		}
		
		min = Math.min(nums[lo], min);
		min = Math.min(nums[hi], min);
		return min;
    }
	
	/**
	 * Suppose a sorted array is rotated at some pivot unknown to you beforehand.
	 * (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2). You are given a target value to search. 
	 * If found in the array return its index, otherwise return -1.
	 * You may assume no duplicate exists in the array.
	 */
	public int search(int[] nums, int target) {
		if (nums == null || nums.length == 0) return -1;
		
		int n = nums.length - 1;
		
		// array is in sorted order, return the 1st element
		if (nums[0] < nums[n-1]) return nums[0];
		
		int lo = 0, hi = n - 1;
		while (lo < hi) {
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
	
	/**
	 * Follow up for "Search in Rotated Sorted Array": What if duplicates are allowe?
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
		Rotate r = new Rotate();
		int[] nums = new int[] {2, 2, 2, 2, 0, 1, 1, 2};
		
		/*
		int[] nums = new int[] {1, 2, 3, 4, 5, 6};
		r.rotate(nums, 2);
		ArrayUtil.printArray(nums);
		 */
		
		/*
		int min = r.findMin_binarySearch(nums);
		System.out.println(min);
		*/
		
		/*
		boolean found = r.search_withDuplicate(nums, 1);
		System.out.println(found);
		*/
		
		int min = r.findMinWithDuplicate_binarySearch(nums);
		System.out.println(min);
	}
}
