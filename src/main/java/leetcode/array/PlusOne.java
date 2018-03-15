package leetcode.array;

/*
 * LEETCODE 66
 * Given a non-negative integer represented as a non-empty array of digits, 
 * plus one to the integer. You may assume the integer do not contain any 
 * leading zero, except the number 0 itself. The digits are stored such that 
 * the most significant digit is at the head of the list.
 * 
 * Company: Google
 * Difficulty: easy
 * Similar Questions: 67(AddBinary), 43(MultiplyString), 369(PlusOneLinkedList)
 */
public class PlusOne {
    public int[] plusOne(int[] digits) {
        int n = digits.length;
        for (int i = n - 1; i >= 0; i--) {
            if (digits[i] < 9) {
                digits[i]++;
                return digits;
            }
            digits[i] = 0; // set the current value to if it is 9
        }
        int[] result = new int[n + 1];
        result[0] = 1;
        // no need to copy digits over because every element is 0
        return result;
    }

}
