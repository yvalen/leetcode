package leetcode.bits;

/*
 * Given a range [m, n] where 0 <= m <= n <= 2147483647, return the bitwise AND of all numbers in this range, inclusive.
 * For example, given the range [5, 7], you should return 4. 
 */
public class BitwiseAndOfNumberRange {
	
	
	//
	//  reduce n by removing the rightest '1' bit until n<=m;
	public int rangeBitwiseAnd_BrianKernighanAlgorithm(int m, int n) {
        while (m < n) {
        	n = n & (n-1);
        }
        return n;
    }
	
	// The operator & for two numbers is keeping those bits which is set in both number.
	// For several numbers, the operator & is keeping those bits which is 1 in every number.
	// In other word, a bit is 0 in any number will result in 0 in the answer's corresponding bit.
	// The bitwise and of the range is keeping the common bits of m and n from left to right until 
	// the first bit that they are different, padding zeros for the rest.
	// This problem that can be reduced to find the same prefix of the numbers in this range.
	//
	// If n>m, the final digit will never match, since the final digit in the binary representation changes on 
	// every increment. Thus we shift both numbers down to the right one slot, and consider if those final digits match, 
	public int rangeBitwiseAnd_shift(int m, int n) {
		int count = 0;
		while (m != n) {
			m >>= 1;
        	n >>= 1;
        	count++;
		}
		return n << count;
	}

}
