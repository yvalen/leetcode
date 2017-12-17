package leetcode.array;

/*
 * LEETCODE 670
 * Given a non-negative integer, you could swap two digits at most once to get the maximum valued number. Return the maximum valued number you could get.
 * Example 1: Input: 2736  Output: 7236
 * Explanation: Swap the number 2 and the number 7.
 * Example 2: Input: 9973  Output: 9973 
 * Explanation: No swap.
 * Note: The given number is in the range [0, 10^8]
 * 
 * Company: Facebook
 * Difficulty: medium
 * Similar Question: 321(CreateMaximumNumber)
 */
public class MaximumSwap {
    // Use buckets to record the last position of digit 0 ~ 9 in this num.
    // Loop through the num array from left to right. For each position,
    // check whether there exists a larger digit in this num (start from 9 to
    // current digit).
    // We also need to make sure the position of this larger digit is behind the
    // current one.
    // If we find it, simply swap these two digits and return the result.
    // Time complexity: O(n) where n is the number of digits in num
    public int maximumSwap(int num) {
        char[] digits = Integer.toString(num).toCharArray();

        // find the last position of each digit in num
        int[] lastPosition = new int[10];
        for (int i = 0; i < digits.length; i++) {
            lastPosition[digits[i] - '0'] = i;
        }

        for (int i = 0; i < digits.length; i++) {
            for (int j = 9; j > digits[i] - '0'; j--) {
                if (lastPosition[j] > i) {
                    char tmp = digits[i];
                    digits[i] = digits[lastPosition[j]];
                    digits[lastPosition[j]] = tmp;
                    return Integer.valueOf(new String(digits));
                }
            }
        }
        return num;
    }

}
