package leetcode.hashtable;

import java.util.HashMap;
import java.util.Map;

/*
 * Given a binary array, find the maximum length of a contiguous subarray with equal number of 0 and 1.
 * Example 1: Input: [0,1] Output: 2
 * Explanation: [0, 1] is the longest contiguous subarray with equal number of 0 and 1.
 * Example 2: Input: [0,1,0] Output: 2
 * Explanation: [0, 1] (or [1, 0]) is a longest contiguous subarray with equal number of 0 and 1.
 * Note: The length of the given binary array will not exceed 50,000. 
 * 
 * Company: Facebook
 * Difficulty: medium
 */
public class ContiguousArray {

    // use a count to store the relative number of ones and zeros encountered so
    // far while traversing the array.
    // increment count by 1 for every one encountered, decrement it by 1 for
    // every zero encountered
    // If at any moment count becomes zero it means we have encountered equal
    // number of zeroes and ones from
    // the beginning till the current index. If we have encounter the same count
    // twice , it means that the number
    // of zeros and ones are equal between the indices corresponding to the
    // equal count values
    // Time complexity: O(n), Space complexity: O(n)
    public int findMaxLength(int[] nums) {
        int maxLen = 0, count = 0;
        Map<Integer, Integer> map = new HashMap<>(); // store the count and the
                                                     // first index
        map.put(0, -1); // [0, 1], count is 0 before processing
        for (int i = 0; i < nums.length; i++) {
            count += nums[i] == 0 ? -1 : 1;
            if (map.containsKey(count)) {
                maxLen = Math.max(maxLen, i - map.get(count));
            } else {
                map.put(count, i); // put the first index so that we calculate
                                   // the distance that farthest apart
            }
        }
        return maxLen;
    }

    public static void main(String[] args) {
        ContiguousArray ca = new ContiguousArray();
        int[] nums = { 0, 1 };
        System.out.println(ca.findMaxLength(nums));
    }
}
