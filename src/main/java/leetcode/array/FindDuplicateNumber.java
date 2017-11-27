package leetcode.array;

/*
 * LEETCODE 287
 * Given an array nums containing n + 1 integers where each integer is between 1 and n (inclusive), 
 * prove that at least one duplicate number must exist. 
 * Assume that there is only one duplicate number, find the duplicate one.
 * Note:
 * - You must not modify the array (assume the array is read only).
 * - You must use only constant, O(1) extra space.
 * - Your runtime complexity should be less than O(n^2).
 * - There is only one duplicate number in the array, but it could be repeated more than once.
 * 
 * Company: Bloomberg
 * Difficulty: medium
 * Similar Questions: 41(FirstMissingPositive), 136(Single Number), 268(MissingNumber), 645(SetMismatch)
 * 142(Linked List Cycle II)
 * 
 */
public class FindDuplicateNumber {
	// At first the search space is numbers between 1 to n. Each time select a number mid (which is the one in the middle) and 
	// count all the numbers equal to or less than mid. If the count is more than mid, the search space will be [1...mid] 
	// otherwise [mid+1... n]. Do this until search space is only one number.
	// Proof
	// Let count be the number of elements in the range 1 .. mid
	// If count > mid, then there are more than mid elements in the range 1 .. mid and thus that range contains a duplicate.
	// If count <= mid, then there are n+1-count elements in the range mid+1 .. n. That is, at least n+1-mid elements 
	// in a range of size n-mid. Thus this range must contain a duplicate.
	// Time complexity: O(nlogn)
	public int findDuplicate_binarySearch(int[] nums) {
        int lo = 1, hi = nums.length; // lo starts from 1
        while (lo < hi) {
        	int mid = lo + (hi - lo) / 2;
        	int count = 0;  // count of numbers that are less than or equal to mid
        	for (int num : nums) {
        		if (num <= mid) count++;
        	}
    
        	if (count > mid) {  
        		hi = mid;  
        	}
        	else {
        		lo = mid + 1;
        	}
        }
		
		return lo;
    }
	
	// http://keithschwarz.com/interesting/code/?dir=find-duplicate
	public int findDuplicate_cycle(int[] nums) {
		// there is no 0 inside this array. You cannot access nums[0] from any nums[i]. 
		// So nums[0] is on the path that lead you to a loop, not inside a loop.
		int slow = nums[0], fast = nums[nums[0]];
		
		while (slow != fast) {
			System.out.println("step1: slow=" + slow + " fast=" + fast);
			slow = nums[slow];
			fast = nums[nums[fast]];
		}
		
		fast = 0;
		while (slow != fast) {
			fast = nums[fast];
			slow = nums[slow];
			System.out.println("step2: slow=" + slow + " fast=" + fast);
		}
		return slow;
	}
	
	
	public static void main(String[] args) {
		FindDuplicateNumber fdn = new FindDuplicateNumber();
		//int[] nums = {1, 1, 2};
		int[] nums = {3, 4, 5, 1, 2, 8, 7, 10, 11, 9, 8, 6};
		System.out.println(fdn.findDuplicate_cycle(nums));
	}
}
