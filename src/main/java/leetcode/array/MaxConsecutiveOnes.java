package leetcode.array;

import java.util.LinkedList;
import java.util.Queue;

public class MaxConsecutiveOnes {
    /*
     * LEETCODE 485
     * Given a binary array, find the maximum number of consecutive 1s in this array.
     * Example 1: Input: [1,1,0,1,1,1] Output: 3
     * Explanation: The first two digits or the last three digits are consecutive 1s.
     * The maximum number of consecutive 1s is 3.
     * Note: 
     * - The input array will only contain 0 and 1. 
     * - The length of input array is a positive integer and will not exceed 10,000
     * 
     * Company: Google
     * Difficulty: easy
     * Similar Questions: 487(Max Consecutive Ones II)
     */
    public int findMaxConsecutiveOnes(int[] nums) {
        int max = 0, count = 0;
        for (int num : nums) {
            if (num == 0) {
                count = 0;
            } else {
                count++;
                max = Integer.max(max, count);
            }
        }

        return max;
    }
    
    /*
     * LEETCODE 487
     * Given a binary array, find the maximum number of consecutive 1s in this array 
     * if you can flip at most one 0.
     * Example 1: Input: [1,0,1,1,0] Output: 4
     * Explanation: Flip the first zero will get the the maximum number of consecutive 1s.
     * After flipping, the maximum number of consecutive 1s is 4.
     * Note:
     * - The input array will only contain 0 and 1.
     * - The length of input array is a positive integer and will not exceed 10,000
     * Follow up: What if the input numbers come in one by one as an infinite stream? In other 
     * words, you can't store all numbers coming from the stream as it's too large to hold in 
     * memory. Could you solve it efficiently? 
     * 
     * Company: Google
     * Difficulty: medium
     * Similar Questions: 485(Max Consecutive Ones)
     */
    // Time complexity: O(n), Space complexity: O(1)
    public static int findMaxConsecutiveOnesII_twoPointers(int[] nums) {
        int maxLen = 0, count = 0;
        int k = 1; // number of zeros to flip,c an be generalized to k
        for (int start = 0, end = 0; end < nums.length; end++) {
            if (nums[end] == 0) count++;
            while (count > k) {
                if (nums[start++] == 0) count--;
            }
            maxLen = Math.max(maxLen, end-start+1);
        }
        return maxLen;
    }
    
    // To deal with the follow up, we need to store up to k indexes of zero within the window 
    // [start, end] so that we know where to move start next when the window contains more than 
    // k zero. If the input stream is infinite, then the output could be extremely large because 
    // there could be super long consecutive ones. In that case we can use BigInteger for all 
    // indexes. For simplicity, here we will use int
    // Time: O(n) Space: O(k)
    public static int findMaxConsecutiveOnesII_storeZeroIndex(int[] nums) {
        int maxLen = 0, k=1;
        Queue<Integer> zeroIndex = new LinkedList<>();
        for (int start = 0, end = 0; end < nums.length; end++) {
            if (nums[end] == 0) zeroIndex.offer(end);
            while (zeroIndex.size() > k) {
                start = zeroIndex.poll()+1;
            }
            maxLen = Math.max(maxLen, end-start+1);
        }
        return maxLen;
    }
    
    
    public static void main(String[] args) {
        int[] nums = {1,0,1,1,0};
        System.out.println(findMaxConsecutiveOnesII_twoPointers(nums));
    }
}
