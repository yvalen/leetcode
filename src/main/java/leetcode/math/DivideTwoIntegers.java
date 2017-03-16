package leetcode.math;

/*
 * Divide two integers without using multiplication, division and mod operator.
 * If it is overflow, return MAX_INT. 
 * 15 = 3 * 4 + 3
 */
public class DivideTwoIntegers {
	public int divide(int dividend, int divisor) {
		// there are two cases may cause overflow
		// divisor = 0;
	    // dividend = INT_MIN and divisor = -1 (because abs(INT_MIN) = INT_MAX + 1).
		if (divisor == 0 || (dividend == Integer.MIN_VALUE && divisor == -1)) return Integer.MAX_VALUE;	
		
		// use absolute value and take care of sign at the end
		int sign = 1;
		if ((dividend > 0 && divisor < 0) || (dividend < 0 && divisor > 0)) {
			sign = -1;
		}
		// convert into long since dividend and divisor could be Integer.MIN_VALUE
		// abs(Integer.MIN_VALUE) == Integer.MIN_VALUE
		long dividendLong = Math.abs((long)dividend);
		long divisorLong = Math.abs((long)divisor);
		
		int result = 0;
		while (dividendLong >= divisorLong) {
			long temp = divisorLong, multiple = 1;
			while (dividendLong >= (temp << 1)) {
				temp <<=  1; // temp=temp*2
				multiple <<= 1;
			}
			dividendLong -= temp;
			result += multiple;
		}
		
		return sign > 0 ? result : -result;
    }
	
	public int divide_doubleDivisor(int dividend, int divisor) {
		// there are two cases may cause overflow
		// divisor = 0;
	    // dividend = INT_MIN and divisor = -1 (because abs(INT_MIN) = INT_MAX + 1).
		if (divisor == 0 || (dividend == Integer.MIN_VALUE && divisor == -1)) return Integer.MAX_VALUE;	
		
		// use absolute value and take care of sign at the end
		int sign = 1;
		if ((dividend > 0 && divisor < 0) || (dividend < 0 && divisor > 0)) {
			sign = -1;
		}
		// convert into long since dividend and divisor could be Integer.MIN_VALUE
		// abs(Integer.MIN_VALUE) == Integer.MIN_VALUE
		long dividendLong = Math.abs((long)dividend);
		long divisorLong = Math.abs((long)divisor);
		
		long resultLong = divide_helper(dividendLong, divisorLong);
		
		if (resultLong > Integer.MAX_VALUE) {
			return (sign > 1) ? Integer.MAX_VALUE : Integer.MIN_VALUE;
		}
		
		return (int) (sign * resultLong);
	}
	
	private long divide_helper(long dividendLong, long divisorLong) {
		if (dividendLong < divisorLong) return 0;
		
		//  Find the largest multiple such that (divisor * multiple <= dividend), 
		//  whereas we are moving with stride 1, 2, 4, 8, 16...2^n for performance reason.
		//  Think this as a binary search.
		long sum = divisorLong, multiple = 1;
		while ((sum + sum) <= dividendLong) {
			sum += sum;
			multiple += multiple;
		}
		
		//Look for additional value for the multiple from the reminder (dividend - sum) recursively.
		return multiple + divide_helper(dividendLong - sum, divisorLong);
		
	}
	
	public static void main(String[] args) {
		DivideTwoIntegers d = new DivideTwoIntegers();
		int dividend = -1, divisor = 1;
		System.out.println(d.divide(dividend, divisor));
	}
}
