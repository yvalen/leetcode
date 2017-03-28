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
}
