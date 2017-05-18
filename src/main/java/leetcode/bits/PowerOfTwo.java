package leetcode.bits;

/*
 * Given an integer, write a function to determine if it is a power of two.
 */
public class PowerOfTwo {
	public boolean isPowerOfTwo(int n) {
		if (n <= 0) return false;
		
		while (n > 1) {
			if (n % 2 != 0) return false;
			n = n / 2;
		}
		return true;
    }
	
	// a power of two in binary form has and only has one "1".
	// If n is the power of two:
	// n = 2 ^ 0 = 1 = 0b0000...00000001, and (n - 1) = 0 = 0b0000...0000.
	// n = 2 ^ 1 = 2 = 0b0000...00000010, and (n - 1) = 1 = 0b0000...0001.
	// n = 2 ^ 2 = 4 = 0b0000...00000100, and (n - 1) = 3 = 0b0000...0011.
	// n = 2 ^ 3 = 8 = 0b0000...00001000, and (n - 1) = 7 = 0b0000...0111.
	// we have n & (n-1) == 0b0000...0000 == 0
	// Otherwise, n & (n-1) != 0.
	public boolean isPowerOfTwo_withBit(int n) {
		if (n <= 0) return false;
		
		return (n & (n-1)) == 0;
    }
	
	public static void main(String[] args) {
		PowerOfTwo pt = new PowerOfTwo ();
		int n = 0;
		System.out.println(pt.isPowerOfTwo(n));
	}
}
