package leetcode.binarysearch;

/**
 * Given a sorted array and a target value, return the index if the target is found. 
 * If not, return the index where it would be if it were inserted in order.
 * You may assume no duplicates in the array.
 * Here are few examples:
 * 	[1,3,5,6], 5 → 2
 * 	[1,3,5,6], 2 → 1
 * 	[1,3,5,6], 7 → 4
 * 	[1,3,5,6], 0 → 0 
 */
public class SearchInsertPosition {
	
	public int searchInsert(int[] nums, int target) {
		if (target > nums[nums.length - 1]) return nums.length;
		
		if (target < nums[0]) return 0;
		
        int low = 0, high = nums.length - 1;
        while (low <= high) {
        	int mid = (low + high) / 2;
        	
        	if (nums[mid] == target) return mid;
        	
        	if (target < nums[mid]) high = mid - 1;
        	else low = mid + 1;
        }
		
		return low;
    }
	
	
	// check boundary before each binary search
	public int searchInsert_faster(int[] nums, int target) {
		if (target > nums[nums.length - 1]) return nums.length;
		
		if (target < nums[0]) return 0;
		
        int low = 0, high = nums.length - 1;
        while (low <= high) {
        	int mid = (low + high) / 2;
        	
        	if (nums[mid] == target) return mid;
        	
        	if (target < nums[mid]) {
        		high = mid - 1;
        		if (nums[high] < target) return high + 1;
        	}
        	else {
        		low = mid + 1;
        		if (nums[low] > target) return low;
        	}
        }
		
		return low;
    }
	
	public static void main(String[] arg) {
		SearchInsertPosition s = new SearchInsertPosition();
		
		int[] nums = {1, 3};
		
		int result = s.searchInsert(nums, 4);
		System.out.println(result);
		
	}

}
