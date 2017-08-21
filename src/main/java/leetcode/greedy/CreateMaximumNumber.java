package leetcode.greedy;

import java.util.Stack;

import leetcode.array.ArrayUtil;

/*
 * Given two arrays of length m and n with digits 0-9 representing two numbers. Create the maximum number of length k <= m + n from digits of the two. 
 * The relative order of the digits from the same array must be preserved. Return an array of the k digits. You should try to optimize your time and 
 * space complexity.
 * Example 1:
 * nums1 = [3, 4, 6, 5]
 * nums2 = [9, 1, 2, 5, 8, 3]
 * k = 5
 * return [9, 8, 6, 5, 3]
 * Example 2:
 * nums1 = [6, 7]
 * nums2 = [6, 0, 4]
 * k = 5
 * return [6, 7, 6, 0, 4]
 * Example 3:
 * nums1 = [3, 9]
 * nums2 = [8, 9]
 * k = 3
 * return [9, 8, 9] 
 * http://blog.csdn.net/u010025211/article/details/50527279
 * 
 * Company: Google
 * Difficulty: hard
 */
public class CreateMaximumNumber {
	
	public int[] maxNumber(int[] nums1, int[] nums2, int k) {
        int n1 = nums1.length, n2 = nums2.length;
        int[] result = new int[k];
        // divide the k digits required into two parts, i and k-i. then find the maximum number 
        // of length i in one array and the maximum number of length k-i in the other array
        for (int i = Math.max(0,  k-n2); i <= k && i < n1; i++) {
        	int[] r = merge(getMaxNumber(nums1, i), getMaxNumber(nums2, k-i), k);
        	if(greater(r, 0, result, 0)) result = r;
        }
        return result;
    }
	
	
	private int[] merge(int[] nums1, int[] nums2, int k) {
		int[] result = new int[k];
		for (int i = 0, j = 0, l = 0; l < k; l++) {
			result[l] = greater(nums1, i, nums2, j) ? nums1[i++] : nums2[j++];
		}
		return result;
	}
	
	// Time complexity: O(mn)
	private boolean greater(int[] nums1, int i, int[] nums2, int j) {
		while (i < nums1.length && j < nums2.length && nums1[i] == nums2[j]) {
			i++;
			j++;
		}
		return (j == nums2.length) || (i < nums1.length && nums1[i] > nums2[j]);
	}
	
	// Given one array of length n, create the maximum number of length k.
	// stack size is fixed as k, we can use an array as stack
	private int[] getMaxNumber(int[] nums, int k) {
		if (nums.length <= k) return nums;
		int[] ans = new int[k];
		for (int i = 0, j = 0; i < nums.length; i++) { // j is the number of elements in stack
			while (j > 0 && ans[j-1] < nums[i] &&  j+nums.length-i > k) {
				j--;
			}
			if (j < k) ans[j++] = nums[i]; 
		}
		
		return ans;
	}
	
	// Given one array of length n, create the maximum number of length k.
	// - Initialize a empty stack
	// - Loop through the array nums, pop the top of stack if it is smaller than nums[i] until stack is empty 
	// or the digits left is not enough to fill the stack to size k. if stack size < k push nums[i]
    // Return stack
	int[] getMaxNumberWithKDigitsWithStack(int[] nums, int k) {
		if (nums.length <= k) return nums;
		Stack<Integer> stack = new Stack<>();
		for (int i = 0; i < nums.length; i++) {
			while (!stack.isEmpty() && stack.peek() < nums[i] && 
					stack.size()+(nums.length-i) > k) {
				stack.pop();
			}
			if (stack.size() < k ) stack.push(nums[i]); // only add to stack when stack size is less than k
		}
		
		int[] result = new int[k];
		for (int i = k-1; i >= 0; i--) {
			result[i] = stack.pop();
		}
		return result;
	}
	
	public static void main(String[] args) {
		CreateMaximumNumber cmn = new CreateMaximumNumber();
		//int[] nums = {1, 2, 3, 6, 5};
		//int k = 4;
		//int[] nums = {2, 3, 9, 8, 2, 6};
		//int k =3;
		int[] nums = {9, 1, 2, 5, 8, 3};
		int k = 2;
		//ArrayUtil.printArray(cmn.getMaxNumberWithKDigitsWithStack(nums, k));
		ArrayUtil.printArray(cmn.getMaxNumber(nums, k));
		
		//int[] nums1 = {6, 7};
		//int[] nums2 = {6, 0, 4};
		//int k = 5;
		//ArrayUtil.printArray(cmn.maxNumber(nums1, nums2, k), ",");
	}

}
