package leetcode.array;

public class SubArray {
	
	/**
	 * Find the contiguous subarray within an array (containing at least one number) 
	 * which has the largest sum. For example, given the array [-2,1,-3,4,-1,2,1,-5,4], 
	 * the contiguous subarray [4,-1,2,1] has the largest sum = 6. 
	 */
	public int maxSubArray_withDPArray(int[] nums) {
		int n = nums.length;
		
		int[][] dp = new int[n][2];
		dp[0][0] = nums[0];
		dp[0][1] = nums[0];
		for (int i = 1; i < n; i++) {
			dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1]);
			dp[i][1] = Math.max(dp[i-1][1] + nums[i], nums[i]);
		}

		return Math.max(dp[n-1][0], dp[n-1][1]);
	}
	
	// DP Kandane's algorithm  O(n)
	// https://en.wikipedia.org/wiki/Maximum_subarray_problem
	public int maxSubArray(int[] nums) {
        int result = nums[0], resultWithCurrent = result;
        for (int i = 1; i < nums.length; i++) {
        	resultWithCurrent = Math.max(nums[i] + resultWithCurrent, nums[i]);
        	result = Math.max(resultWithCurrent,  result);
        }
		return result;
    }
	
	// Divide and Conquer O(nlgn)
	public int maxSubArray_divideAndConquer(int[] nums) {
		if (nums == null || nums.length == 0) return Integer.MIN_VALUE;
		
		return maxSubArray_divideAndConquerHelper(nums, 0, nums.length - 1);
    }
	
	private int maxSubArray_divideAndConquerHelper(int[] nums, int low, int high) {
		if (low == high) {
			return nums[low];
		}
		
		int mid = (low + high) / 2;
		
		int leftMax = maxSubArray_divideAndConquerHelper(nums, low, mid);
		int rightMax = maxSubArray_divideAndConquerHelper(nums, mid + 1, high);
		int crossingMax = maxSubArrayCrossing(nums, low, mid, high);
		
		int result = Math.max(leftMax, rightMax);
		return Math.max(result, crossingMax);
    }
	
	private int maxSubArrayCrossing(int[] nums, int low, int mid, int high) {
		int leftMax = nums[mid], rightMax = nums[mid + 1];
		int leftSum = nums[mid], rightSum = nums[mid+1];
		for (int i = mid - 1; i >=low; i--) {
			leftSum += nums[i];
			leftMax = Math.max(leftSum, leftMax);
		}
		
		for (int i = mid + 2; i <= high; i++) {
			rightSum += nums[i];
			rightMax = Math.max(rightSum, rightMax);
		}
		
		return leftMax + rightMax;
    }
	
	/**
	 * Given an array of n positive integers and a positive integer s, 
	 * find the minimal length of a subarray of which the sum ≥ s. 
	 * If there isn't one, return 0 instead.
	 * For example, given the array [2,3,1,2,4,3] and s = 7,
	 * the subarray [4,3] has the minimal length under the problem constraint. 
	 */
	// O(n)
	public int minSubArrayLen_linear(int s, int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        
        int minLen = Integer.MAX_VALUE, sum = 0;
        for (int i = 0, j = 0; j < nums.length; j++) {
        	sum += nums[j];
        	while (sum >= s && i <= j) {
        		minLen = Math.min(minLen, j-i+1);
        		sum -= nums[i];
        		i++;
        	}
        	
        }
		return minLen == Integer.MAX_VALUE ? 0 : minLen;
    }
	
	/**
	 *  Find the contiguous subarray within an array (containing at least one number) 
	 *  which has the largest product.For example, given the array [2,3,-2,4],
	 *  the contiguous subarray [2,3] has the largest product = 6.
	 */
	public int maxProduct(int[] nums) {
        int result = nums[0], max = nums[0], min = nums[0];
        for (int i = 1; i < nums.length; i++) {
        	if (nums[i] < 0) {
        		int temp = max;
        		max = min;
        		min = temp;
        	}
        	
        	max = Math.max(nums[i], nums[i] * max);
        	min = Math.min(nums[i], nums[i] * min);
        	result = Math.max(result, max);
        }
        return result;
    }
	
	public static void  main(String[] args) {
		SubArray s = new SubArray();
		
		/*
		int[] nums = {-2,1,-3,4,-1,2,1,-5,4};
		int maxSubArray = s.maxSubArray_divideAndConquer(nums);
		System.out.println(maxSubArray);
		*/
		
		int[] nums = {2,3,1,2,4,3};
		int minLen = s.minSubArrayLen_linear(7, nums);
		System.out.println(minLen);
	}

}
