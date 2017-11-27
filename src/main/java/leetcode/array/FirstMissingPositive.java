package leetcode.array;

/*
 * LEETCODE 41
 * Given an unsorted integer array, find the first missing positive integer.
 * For example, Given [1,2,0] return 3, and [3,4,-1,1] return 2.
 * Your algorithm should run in O(n) time and uses constant space. 
 * 
 * Difficulty: hard
 * Similar Questions: 268(MissingNumber), 287(FindDuplicateNumber), 448(DisappearedNumbersInArray)
 */
public class FirstMissingPositive {
	// For any k positive numbers (duplicated allowed), the first missing number must be within [1, k+1]
	
	// Time Complexity: O(n) , O(1) space
	// consider position with A[i] = i+1 as a CORRECT SLOT.
	// - CORRECT SLOT will never be changed: for CORRECT SLOT, A[A[i] - 1] always equals to A[i], vice versa (1)
	// - For each swap, the number of CORRECT SLOT increases by at least 1: Position A[A[i] - 1] = A[i] becomes a CORRECT SLOT after swap, 
	// and according to (1), this must be a new CORRECT SLOT
	// - The maximum of CORRECT SLOT <= N
	public int firstMissingPositive(int[] nums) {
		for (int i = 0; i < nums.length; i++) {
			// put each positive number k in range of i to n in its position nums[k-1]
			int num = nums[i];
			while (num > 0 && num <= nums.length && nums[num-1] != nums[i]) { // use while loop here instead of if
				nums[i] = nums[num-1];
				nums[num-1] = num;
				num = nums[i]; // reset num for the next iteration in while loop
			}
			ArrayUtil.printArray(nums, " ");
		}
		
		// the first missing positive will be the position in which the number doesn't match its index
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] != i + 1) return i+1;
		}
		
		return nums.length+1;
    }
	
	public int firstMissingPositive_withPartition(int[] nums) {
		// partition nums such that all positive numbers are before k and negative numbers are in k to n-1
		int k = partition(nums, 0, nums.length-1);
		
		// first missing positive must be in [1, k+1]
		// set nums[i] to negative to indicate the existence of i+1
		for (int i = 0; i < k; i++) {
			int index = Math.abs(nums[i]) -1;
            if (index < nums.length && nums[index] > 0) nums[index] = -nums[index];
		}
		
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] > 0) return i+1;
		}
		return k+1;
	}
	
	private int partition(int[] nums, int start, int end) {
		while (start <= end) {
			while (start < nums.length && nums[start] > 0) start++; 
			while (end >= 0 && nums[end] <= 0) end--;
 			if (start <= end) {
				int temp = nums[start];
				nums[start] = nums[end];
				nums[end] = temp;	
			}
		}
		return start;
	}
	
	public static void main(String[] args) {
		FirstMissingPositive f = new FirstMissingPositive();
		int[] nums = {3, 4, -1, 1};
		//int[] nums = {0};
		//int[] nums = {};
		//System.out.println(f.firstMissingPositive_withPartition(nums));
		System.out.println(f.firstMissingPositive(nums));
		
	}
}
