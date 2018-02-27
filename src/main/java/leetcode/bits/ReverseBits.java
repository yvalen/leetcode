package leetcode.bits;

/*
 * LEETCODE 190
 * Reverse bits of a given 32 bits unsigned integer. For example, given input 43261596
 * (represented in binary as 00000010100101000001111010011100), return 964176192 
 * (represented in binary as 00111001011110000010100101000000).
 * Follow up: If this function is called many times, how would you optimize it? 
 * https://discuss.leetcode.com/topic/9764/java-solution-and-optimization/2
 */
public class ReverseBits {
    public int reverseBits(int n) {
        int result = 0;

        for (int i = 0; i < 32; i++) { // cannot use n>0 here because we need to
                                       // count the leading zeros, have to check
                                       // all 32 bits
            result = result << 1;
            // result += (n & 1);
            result |= (n & 1); // bitwise is faster than addition
            n = n >> 1;
            // System.out.println("result=" + result + " n=" + n);
        }

        return result;
    }

    public static void main(String[] args) {
        ReverseBits rb = new ReverseBits();
        int n = 43261596;
        System.out.println(rb.reverseBits(n));
    }
}
