package leetcode.array;

public class MaximumAverageSubarray {
	/*
	 * LEETCODE 643
	 *  Given an array consisting of n integers, find the contiguous subarray of given length k that has 
	 *  the maximum average value. And you need to output the maximum average value.
	 *  Example 1:
	 *  Input: [1,12,-5,-6,50,3], k = 4
	 *  Output: 12.75
	 *  Explanation: Maximum average is (12-5-6+50)/4 = 51/4 = 12.75
	 *  Note:
	 *  - 1 <= k <= n <= 30,000.
	 *  - Elements of the given array will be in the range [-10,000, 10,000].
	 *  
	 *  Company: Google
	 *  Difficulty: easy
	 *  Similar Questions: 644(Maximum Average Subarray II)
	 */
	// Time complexity: O(n), Space complexity: O(1)
	public double findMaxAverage(int[] nums, int k) {
		int sum = 0;
		for (int i = 0; i < k; i++) sum += nums[i];
		
		int max = sum;
		for (int i = k; i < nums.length; i++) {
			sum += nums[i] - nums[i-k];
			max = Math.max(max, sum);
		}
				
		return max / 1.0 / k ;  // need to divide 1.0 first to convert max into a double 
		
		/*
        double maxAvg = -10001;
        int sum = 0, i = 0, j = 0;
        
        while (j < k-1) {
        	sum += nums[j];
        	j++;
        }
        
        while (j < nums.length) {
        	sum += nums[j++];
        	double avg =  ((double) sum) / k;
        	maxAvg = Math.max(maxAvg, avg);
        	sum -= nums[i++];
        }
        return maxAvg;
        */
    }

	
	/*
	 * LEETCODE 644
	 * Given an array consisting of n integers, find the contiguous subarray whose length is greater than or equal to k 
	 * that has the maximum average value. And you need to output the maximum average value.
	 * Example 1:
	 * Input: [1,12,-5,-6,50,3], k = 4
	 * Output: 12.75
	 * Explanation: when length is 5, maximum average value is 10.8; when length is 6, maximum average value is 9.16667.
	 * Thus return 12.75.
	 * Note:
	 * - 1 <= k <= n <= 10,000.
	 * - Elements of the given array will be in range [-10,000, 10,000].
	 * - The answer with the calculation error less than 10-5 will be accepted.
	 * 
	 * Company: Google
	 * Difficulty: hard
	 * Similar Questions: 643(Maximum Average Subarray I) 
	 */
	public double findMaxAverageII(int[] nums, int k) {
        double maxAvg = -10001;  // Double.MIN_VALUE is a positive number
		return maxAvg;
    }
	
	
	public static void main(String[] args) {
		MaximumAverageSubarray mas = new MaximumAverageSubarray();
		//int[] nums = {1,12,-5,-6,50,3};
		//int k = 4;
		//int[] nums = {5};
		//int k = 1;
		int[] nums = {-1};
		int k = 1;
		System.out.println(mas.findMaxAverage(nums, k));
	}
}
