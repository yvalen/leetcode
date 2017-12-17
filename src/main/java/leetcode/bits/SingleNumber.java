package leetcode.bits;

// https://discuss.leetcode.com/topic/50315/a-summary-how-to-use-bit-manipulation-to-solve-problems-easily-and-efficiently
public class SingleNumber {
    /*
     * LEETCODE 136 Given an array of integers, every element appears twice
     * except for one. Find that single one. Note: Your algorithm should have a
     * linear runtime complexity. Could you implement it without using extra
     * memory?
     * 
     * Company: Airbnb, Palantir Difficulty: easy
     */
    public int singleNumber(int[] nums) {
        // A number XOR itself will become 0, any number XOR with 0 will stay
        // unchanged.
        int result = 0;
        for (int i = 0; i < nums.length; i++) {
            result ^= nums[i];
        }
        return result;
    }

    /*
     * Given an array of integers, every element appears three times except for
     * one. Find that single one. Note: Your algorithm should have a linear
     * runtime complexity. Could you implement it without using extra memory?
     */
    public int singleNumberII_withBitVector(int[] nums) {
        // use a 32, bit vector to store the module count of appearance of '1'
        // by K in every bit.
        int[] bitVector = new int[32];
        for (int num : nums) {
            for (int i = 0; i < 32; i++) {
                boolean hasBit = (num & (1 << i)) != 0; // 1 << 31 will be
                                                        // negative
                if (hasBit)
                    bitVector[i] = (bitVector[i] + 1) % 3;
            }
        }

        int result = 0;
        for (int i = 0; i < 32; i++) {
            if (bitVector[i] > 0)
                result |= (1 << i);
        }
        return result;
    }

    public int singleNumberII_with32BitCounter(int[] nums) {
        // if we sum the i-th bit of all numbers and mod 3, it must be either
        // ZERO or ONE due to the fact that each number
        // must appear either three times or once. This resulted bit is exactly
        // the ith bit of the single number we want.
        int result = 0;
        for (int i = 0; i < 32; i++) {
            int sum = 0;
            for (int num : nums) {
                if (((num >> i) & 1) == 1) { // check if the ith bit of nums[j]
                                             // is 1
                    sum++;
                    // sum %= 3;
                }
            }
            result |= (sum % 3) << i; // move the one bit to its original spot
        }
        return result;
    }

    /*
     * Given an array of numbers nums, in which exactly two elements appear only
     * once and all the other elements appear exactly twice. Find the two
     * elements that appear only once. For example: given nums = [1, 2, 1, 3, 2,
     * 5], return [3, 5]. Note: The order of the result is not important. So in
     * the above example, [5, 3] is also correct. Your algorithm should run in
     * linear runtime complexity. Could you implement it using only constant
     * space complexity?
     */
    // if some bit of the XOR result is 1, it means that the two target numbers
    // differ at that location. Let’s say the at the ith bit
    // the two desired numbers differ from each other, which means one number
    // has bit i as 0, the other number has bit i as 1.
    // all the numbers can be partitioned into two groups according to their
    // bits at location i.
    // the first group consists of all numbers whose bits at i is 0.
    // the second group consists of all numbers whose bits at i is 1.
    // if a duplicate number has bit i as 0, then, two copies of it will belong
    // to the first group.
    // Similarly, if a duplicate number has bit i as 1, then, two copies of it
    // will belong to the second group.
    // by XoRing all numbers in the first group, we can get the first number.
    // by XoRing all numbers in the second group, we can get the second number.
    // Complexity: O(n) - time, O(1) - space
    public int[] singleNumberIII(int[] nums) {
        int diff = 0;

        // get the XOR of the two number we want to find
        for (int num : nums) {
            diff ^= num;
        }

        // get the last set bit (rightmost set bit)
        diff &= -diff;
        int[] result = new int[2];
        for (int num : nums) {
            if ((num & diff) == 0) {
                // the bit is not set
                result[0] ^= num;
            } else {
                // the bit is set
                result[1] ^= num;
            }
        }

        return result;
    }
}
