package leetcode.dp;

/*
 * LEETCODE 152
 * Find the contiguous subarray within an array (containing at least one number) which has the largest product.
 * For example, given the array [2,3,-2,4], the contiguous subarray [2,3] has the largest product = 6.
 * 
 * Company: LinkedIn
 * Difficulty: medium
 * Similar Questions: 53(MaximumSubArray), 198(House Robber), 238(ProductOfArrayExceptSelf)
 */
public class MaxProductSubarray {
	public int maxProduct(int[] nums) {
		int maxProduct = nums[0], minProduct = nums[0], result = nums[0];
		
		for (int i = 1; i < nums.length; i++) {
			if (nums[i] < 0) { // multiplied by a negative makes big number smaller, small number bigger
				int tmp = maxProduct;
				maxProduct = minProduct;
				minProduct = tmp;
			}
			maxProduct = Math.max(maxProduct * nums[i], nums[i]);
			minProduct = Math.min(minProduct * nums[i], nums[i]);
			result = Math.max(maxProduct, result);
		}
		
		return result;
	}
	
	public int maxProduct_withArray(int[] nums) {
		int n = nums.length;
		
        int[] maxProduct = new int[n]; // maximum cumulative product up to i
        int[] minProduct = new int[n]; // minimum cumulative product up to i
        maxProduct[0] = nums[0];
        minProduct[0] = nums[0];
        int max = nums[0];
        for (int i = 1; i < n; i++) {
            maxProduct[i] = Math.max(Math.max(maxProduct[i-1]*nums[i], minProduct[i-1]*nums[i]), nums[i]);
            minProduct[i] = Math.min(Math.min(minProduct[i-1]*nums[i], maxProduct[i-1]*nums[i]), nums[i]);
            max = Math.max(max, maxProduct[i]);
        }
        return max;
    }

}
