package leetcode.math;

/*
 *  Given a non-negative integer num, repeatedly add all its digits until the result has only one digit.
 *  For example: given num = 38, the process is like: 3 + 8 = 11, 1 + 1 = 2. Since 2 has only one digit, return it.
 *  Follow up: could you do it without any loop/recursion in O(1) runtime? 
 *  
 *  https://en.wikipedia.org/wiki/Digital_root#Congruence_formula
 *  The digital root (aka repeated digital sum) of a non-negative integer is the (single digit) value obtained by an 
 *  iterative process of summing digits, on each iteration using the result from the previous iteration to compute a digit sum. 
 *  The process continues until a single-digit number is reached.
 */
public class AddDigits {
	public int addDigits(int num) {
        while (num >= 10) { // need to check for equal as well
        	num = getSum(num);        	
        }
		return num;
    }
	
	private int getSum(int num) {
		int sum = 0;
		while (num != 0) {
			sum += num % 10;
			num /= 10;
		}
		return sum;
	}
	
	/*
	 * https://en.wikipedia.org/wiki/Digital_root#Congruence_formula
	 * The digital root (aka repeated digital sum) of a non-negative integer is the (single digit) value obtained by an 
	 * iterative process of summing digits, on each iteration using the result from the previous iteration to compute a digit sum. 
	 * The process continues until a single-digit number is reached.
	 * 
	 * For base b (decimal case b = 10), the digit root of an integer is:
	 * dr(n) = 0 if n == 0
	 * dr(n) = (b-1) if n != 0 and n % (b-1) == 0
	 * dr(n) = n mod (b-1) if n % (b-1) != 0
	 * or
	 * dr(n) = 1 + (n - 1) % 9
	 */
	public int addDigits_digitalRoot(int num) {
        return 1 + (num-1) % 9;
    }
}
