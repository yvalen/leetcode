package leetcode.bits;

/*
 * LEETCODE 371
 * Calculate the sum of two integers a and b, but you are not allowed to use the operator + and -.
 * Example: Given a = 1 and b = 2, return 3. 
 * 
 * Company: Hulu
 * Difficulty: easy
 * Similar Questions: 2(AddTwoNumbers)
 * 
 * http://stackoverflow.com/questions/9070937/adding-two-numbers-without-operator-clarification
 * https://discuss.leetcode.com/topic/50315/a-summary-how-to-use-bit-manipulation-to-solve-problems-easily-and-efficiently
 */
public class SumOfTwoIntegers {
    public int getSum_iterative(int a, int b) {
        while (b != 0) { // repeat the process until carry is 0
            // finds all bit positions with the case 1+1.
            int carry = a & b; 
            //  Handles case 0+1 and 1+0
            a = a ^ b; 
            // shift carry to next position
            b = carry << 1; 
        }
        return a;
    }

    public int getSum(int a, int b) {
        return b == 0 ? a : getSum(a ^ b, ((a & b) << 1));
    }

    // subtract two integer a-b
    public int getSubtract(int a, int b) {
        return b == 0 ? a : getSubtract(a ^ b, ((~a & b) << 1));
    }

    public int getSubtract_iterative(int a, int b) {
        while (b != 0) {
            int borrow = ~a & b;
            a = a ^ b;
            b = borrow << 1;
        }
        return a;
    }

    // negate a number
    public int negate(int x) {
        return ~x + 1;
    }
}
