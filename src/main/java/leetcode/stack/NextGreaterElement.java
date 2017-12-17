package leetcode.stack;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class NextGreaterElement {
    /*
     * You are given two arrays (without duplicates) nums1 and nums2 where
     * nums1’s elements are subset of nums2. Find all the next greater numbers
     * for nums1's elements in the corresponding places of nums2. The Next
     * Greater Number of a number x in nums1 is the first greater number to its
     * right in nums2. If it does not exist, output -1 for this number. Example
     * 1: Input: nums1 = [4,1,2], nums2 = [1,3,4,2]. Output: [-1,3,-1]
     * Explanation: - For number 4 in the first array, you cannot find the next
     * greater number for it in the second array, so output -1. - For number 1
     * in the first array, the next greater number for it in the second array is
     * 3. - For number 2 in the first array, there is no next greater number for
     * it in the second array, so output -1.
     * 
     * Example 2: Input: nums1 = [2,4], nums2 = [1,2,3,4]. Output: [3,-1]
     * Explanation - For number 2 in the first array, the next greater number
     * for it in the second array is 3. - For number 4 in the first array, there
     * is no next greater number for it in the second array, so output -1. Note:
     * - All elements in nums1 and nums2 are unique. - The length of both nums1
     * and nums2 would not exceed 1000.
     * 
     * Difficulty: easy
     */
    public int[] nextGreaterElement(int[] findNums, int[] nums) {
        Map<Integer, Integer> greaterElemMap = new HashMap<>(); // stores the
                                                                // next greater
                                                                // elem for
                                                                // every elem in
                                                                // nums if
                                                                // exists
        Stack<Integer> stack = new Stack<>(); // stores a decreasing
                                              // sub-sequence in nums
        for (int num : nums) {
            // whenever we see a number x greater than stack.peek() we pop all
            // elements
            // less than x and for all the popped ones, their next greater
            // element is x
            while (!stack.isEmpty() && stack.peek() < num) {
                greaterElemMap.put(stack.pop(), num);
            }
            stack.push(num);
        }

        int[] result = new int[findNums.length];
        for (int i = 0; i < findNums.length; i++) {
            result[i] = greaterElemMap.getOrDefault(findNums[i], -1);
        }

        return result;
    }

    /*
     * Given a circular array (the next element of the last element is the first
     * element of the array), print the Next Greater Number for every element.
     * The Next Greater Number of a number x is the first greater number to its
     * traversing-order next in the array, which means you could search
     * circularly to find its next greater number. If it doesn't exist, output
     * -1 for this number. Example 1: Input: [1,2,1] Output: [2,-1,2]
     * Explanation: - The first 1's next greater number is 2; - The number 2
     * can't find next greater number; - The second 1's next greater number
     * needs to search circularly, which is also 2.
     * 
     * Company: Google Difficulty: medium
     */
    public int[] nextGreaterElementsII(int[] nums) {
        if (nums == null || nums.length == 0)
            return nums;

        int n = nums.length;
        int[] result = new int[n];
        Stack<Integer> stack = new Stack<>();
        // put all indexes into the stack, smaller index on the top
        for (int i = n - 1; i >= 0; i--) {
            stack.push(i);
            result[i] = -1;
        }

        // start from end of the array look for the first element (index) in the
        // stack which is greater than the current one
        // Then put the current element (index) into the stack.
        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && nums[stack.peek()] <= nums[i]) {
                stack.pop();
            }
            if (!stack.isEmpty())
                result[i] = nums[stack.peek()];
            stack.push(i);
        }
        return result;
    }

}
