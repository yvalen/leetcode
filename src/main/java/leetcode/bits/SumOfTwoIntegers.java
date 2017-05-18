package leetcode.bits;

/*
 * Calculate the sum of two integers a and b, but you are not allowed to use the operator + and -.
 * Example: Given a = 1 and b = 2, return 3. 
 * 
 * http://stackoverflow.com/questions/9070937/adding-two-numbers-without-operator-clarification
 * https://discuss.leetcode.com/topic/50315/a-summary-how-to-use-bit-manipulation-to-solve-problems-easily-and-efficiently
 */
public class SumOfTwoIntegers {
	public int getSum_iterative(int a, int b) {
		while (b != 0 ) {
			int carry  = a & b  << 1 ; // finds all bit positions with the case 1+1. 
			a = a ^ b; // Handles case 0+1 and 1+0, a will contain the simple case, all bit positions that add up to 1.
			b = carry << 1;  // shift carry to next po
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
