package leetcode.math;

/*
 * Given an integer n, count the total number of digit 1 appearing in all non-negative integers less than or equal to n.
 * For example: given n = 13, return 6, because digit 1 occurred in the following numbers: 1, 10, 11, 12, 13. 
 */
public class NumberOfDigitOne {
	
	
	// Go through the digit positions one at a time, find out how often a "1" appears at each position, and sum those up.
	public int countDigitOne(int n) {
        int result = 0;
        for (long m = 1; m <= n; m *= 10) { // need to use long t prevent overflow, m*10 could overflow
        	long a = n / m, b = n % m;
        	result += ((a + 8) / 10) * m + ((a % 10 == 1) ? (b + 1) : 0);
        	System.out.println("m=" + m + " a =" + a + " b=" + b + " result=" + result);
        }
		
		return result;
    }

	public static void main(String[] args) {
		NumberOfDigitOne ndo = new NumberOfDigitOne();
		int n = 1410065408;
		System.out.println(ndo.countDigitOne(n));
	}
}
