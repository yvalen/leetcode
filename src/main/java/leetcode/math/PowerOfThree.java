package leetcode.math;

/*
 * Given an integer, write a function to determine if it is a power of three.
 * Follow up: Could you do it without using any loop / recursion? 
 */
public class PowerOfThree {
    public boolean isPowerOfThree(int n) {
        if (n <= 0)
            return false;

        while (n % 3 == 0)
            n = n / 3;
        return n == 1;
    }

    // Find the maximum integer that is a power of 3 and check if it is a
    // multiple of the given input.
    // only works when the base is prime. For example, we cannot use this
    // algorithm to check if a
    // number is a power of 4 or 6 or any other composite number.
    public boolean isPowerOfThree_withoutLoop(int n) {
        // 1162261467 is 3^19, 3^20 is bigger than int
        return (n > 0 && 1162261467 % n == 0);
    }
}
