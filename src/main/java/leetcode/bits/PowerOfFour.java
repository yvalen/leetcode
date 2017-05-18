package leetcode.bits;

/*
 * Given an integer (signed 32 bits), write a function to check whether it is a power of 4.
 * Example: Given num = 16, return true. Given num = 5, return false.
 * Follow up: Could you solve it without loops/recursion? 
 * 
 * Power of 4 numbers have those 3 common features:
 * - greater than 0
 * - only have one '1' bit in their binary notation, so we use x&(x-1) to delete the lowest '1',
 * and if then it becomes 0,it prove that there is only one '1' bit
 * - the only '1' bit should be locate at the odd location, we can use '0x55555555' to check if the '1' bit is in the right place
 * 0x55555555 -> 0101 0101 0101 0101 0101 0101 0101 0101
 */
public class PowerOfFour {
	public boolean isPowerOfFour(int num) {
        return (num > 0 && 
        		(num & (num-1)) == 0 && 
        		(num & 0x55555555) != 0);  // need to check !=0 here, cannot use ==1 since the result could be bigger than 1
    }
	
	public boolean isPowerOfFour_withInteger(int num) {
        return (num > 0 && Integer.bitCount(num) == 1 && Integer.numberOfTrailingZeros(num) % 2 == 0);
    }
	
}
