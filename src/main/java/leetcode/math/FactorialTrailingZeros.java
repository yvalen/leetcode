package leetcode.math;

/*
 * Given an integer n, return the number of trailing zeroes in n!.
 * 
 * If we knew how many times we could divide n! by 10 without causing it to no longer be an integer, we would have our answer.
 * 10's prime factors are 5 and 2. 
 * All trailing 0 is from factors 5 * 2. But sometimes one number may have several 5 factors, 
 * for example, 25 have two 5 factors, 125 have three 5 factors. In the n! operation, factors 2 is always ample. 
 * So we just count how many 5 factors in all number from 1 to n.
 */
public class FactorialTrailingZeros {
	public int trailingZeroes(int n) {
        return n == 0? 0 : n/5 + trailingZeroes(n/5);
    }
	
	public int trailingZeroes_iterative(int n) {
		int result = 0;
		while (n > 0) {
			result += n / 5;
			n = n/5;
		}
		return result;
	}
}
