package leetcode.dp;

/*
 * LEETCODE 673 
 * Given an unsorted array of integers, find the number of longest increasing subsequence.
 * Example 1: Input: [1,3,5,4,7] Output: 2
 * Explanation: The two longest increasing subsequence are [1, 3, 4, 7] and [1, 3, 5, 7].
 * Example 2: Input: [2,2,2,2,2] Output: 5
 * Explanation: The length of longest continuous increasing subsequence is 1, and there are 5 subsequences' length is 1, so output 5.
 * Note: Length of the given array will be not exceed 2000 and the answer is guaranteed to be fit in 32-bit signed int. 
 * 
 * Company: Facebook
 * Difficulty: medium
 * Similar Questions: 300(LongestIncreasingSubsequence), 674(LongestContinuousIncreasingSubsequence)
 */
public class NumberOfLongestIncreasingSubsequence {
	public int findNumberOfLIS(int[] nums) {
        if (nums == null) return 0;
        
        int n = nums.length;
        if (n <= 1) return n;
        
        int[] len = new int[n]; // len[i] is the length of the longest increasing subsequence up to ith element
        int[] count = new int[n]; // count[i] is the count of the longest increasing subsequences up to ith element
        int maxLen = 1, result = 0;
        
        // len[k+1] = max(len[k+1], len[i]+1) for all i <= k and nums[i] < nums[k+1];
        // count[k+1] = sum(count[i]) for all i <= k and nums[i] < nums[k+1] and len[i] = len[k+1]-1;
        for (int i = 0; i < n; i++) {
            len[i] = 1;
            count[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                	if (len[i] == len[j] + 1) {  // found another subsequence with the same length of LIS which ends at nums[i].
                		count[i] += count[j]; 
                	}
                	else if (len[i] < len[j] + 1) { // a new LIS, update both len and count
                		len[i] = len[j] + 1;
                		count[i] = count[j];
                	}
                }
            }
            
            if (maxLen == len[i]) {
            	result += count[i];
            }
            else if (maxLen < len[i]) {
            	result = count[i];
            	maxLen = len[i];
            }
        }
        
        return result;
    }
	
	public static void main(String[] args) {
		NumberOfLongestIncreasingSubsequence  nlis = new NumberOfLongestIncreasingSubsequence();
		int[] nums = {1, 2};
		System.out.println(nlis.findNumberOfLIS(nums));
	}
}
