package leetcode.array;

/*
 * LEETCODE 665
 * Given an array with n integers, your task is to check if it could become 
 * non-decreasing by modifying at most 1 element. We define an array is 
 * non-decreasing if array[i] <= array[i + 1] holds for every i (1 <= i < n).
 * Example 1: Input: [4,2,3] Output: True
 * Explanation: You could modify the first 4 to 1 to get a non-decreasing array.
 * Example 2: Input: [4,2,1] Output: False
 * Explanation: You can't get a non-decreasing array by modify at most one element.
 * Note: The n belongs to [1, 10,000].
 * 
 * Company: Google
 * Difficulty: easy
 */
public class NonDecreasingArray {
    // When you find nums[i-1] > nums[i] for some i, you will prefer to change nums[i-1]'s 
    // value, since a larger nums[i] will give you more risks that you get inversion errors 
    // after position i. But if you also find nums[i-2] > nums[i], then you have to change 
    // nums[i]'s value instead, or else you need to change both of nums[i-2]'s and nums[i-1]'s values.
    public static boolean checkPossibility(int[] nums) {
        if (nums == null || nums.length == 0) return false;
        if (nums.length == 1) return true;
        
        boolean modified = false;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < nums[i-1]) {
                if (modified) return false;
                modified = true;
                if (i > 1 && nums[i] < nums[i-2]) {
                    nums[i] = nums[i-1];
                }
            }
        }
        return true;
    }
    
    public static void main(String[] args) {
        //int[] nums = {3, 4, 2, 3};
        int[] nums = {4, 2, 3};
        System.out.println(checkPossibility(nums));
    }
}
