package leetcode.bits;

/*
 * Write a function that takes an unsigned integer and returns the number of ’1' bits it has (also known as the Hamming weight).
 * For example, the 32-bit integer ’11' has binary representation 00000000000000000000000000001011, so the function should return 3.
 */
public class NumberOfOneBits {
	// you need to treat n as an unsigned value
	public int hammingWeight(int n) {
		int count = 0;
		for (int i = 0; i < 32; i++) {
			if (((n >> i) & 1) == 1) count++;
		}
        return count;
    }
	
	public int hammingWeight2(int n) {
		int count = 0;
		while (n != 0) { // need to check !=0 instead of >0 due to the overflow, 2147483648 would correspond to -2147483648 in java
			count = count + (n & 1);
			n = n >>> 1; // use unsigned shift
		}
        return count;
    }
	
	// Brian Kernighan's algorithm of counting set bits: every time performs a bitwise AND operation between 
	// input integer n and n-1 and keep c incrementing by 1 until n becomes zero. 
	// Decrementing by one flips the lowest bit and every bit up to the first set bit (first one) 
	// So if we subtract a number by 1 and do bitwise & with itself (n & (n-1)), we unset the righmost set bit. 
	// In this way we can unset 1s one by one from right to left in loop. The number of times the loop iterates 
	// is equal to the number of set bits.
	// O(logn) - n is represented by logn bits. Conversely, k bits can represent numbers as high as 2^k 
	public int hammingWeight_BrianKernighanAlgorithm(int n) {
		int count = 0;
		while (n != 0) {
			n = n & (n-1);
			count++;
		}
		return count;
    }
}
