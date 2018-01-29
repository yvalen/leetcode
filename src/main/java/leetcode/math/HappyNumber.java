package leetcode.math;

import java.util.HashSet;
import java.util.Set;

/*
 * LEETCODE 202
 * Write an algorithm to determine if a number is "happy". A happy number is a 
 * number defined by the following process: Starting with any positive integer, 
 * replace the number by the sum of the squares of its digits, and repeat the 
 * process until the number equals 1 (where it will stay), or it loops endlessly 
 * in a cycle which does not include 1. Those numbers for which this process ends 
 * in 1 are happy numbers.
 * Example: 19 is a happy number
 * 		1^2 + 9^2 = 82
 * 		8^2 + 2^2 = 68
 * 		6^2 + 8^2 = 100
 * 		1^2 + 0^2 + 0^2 = 1
 * 
 * Company: Uber, Twitter, Airbnb
 * Difficulty: easy
 * Similar Questions: 258(AddDigits), 263(Ugly Number)
 */
public class HappyNumber {
    // Starting from a number I, if some value - say a - appears again during
    // the process after k steps,
    // the initial number I cannot be a happy number. Because a will
    // continuously become a after every k steps.
    // Therefore, as long as we can show that there is a loop after running the
    // process continuously, the number is not a happy number.
    // for any non-happy number, all outcomes during the process are bounded by
    // some large but finite integer N. If all outcomes can only
    // be in a finite set (2,N], and since there are infinitely many outcomes
    // for a non-happy number, there has to be at least one duplicate

    // Floyd Cycle detection algorithm
    public boolean isHappy(int n) {
        int slow = n, fast = n;
        do {
            slow = calculateSquareSum(slow);
            fast = calculateSquareSum(calculateSquareSum(fast));
            if (fast == 1)
                return true;
        } while (slow != fast);

        return false;
    }

    private int calculateSquareSum(int n) {
        int sum = 0;
        while (n != 0) {
            int mod = n % 10;
            n = n / 10;
            sum += mod * mod;
        }
        return sum;
    }

    public boolean isHappy_withSet(int n) {
        Set<Integer> set = new HashSet<>();
        while (set.add(n)) { // add to set returns true if set doesn't contain
                             // the element
            if (n == 1)
                return true;
            n = calculateSquareSum(n);
            System.out.println(n);
        } // once a repeated number is detected the number is not a happy number
          // because it will never end

        return false;
    }

    public static void main(String[] args) {
        HappyNumber hn = new HappyNumber();
        int n = 7;
        System.out.println(hn.isHappy_withSet(n));
    }
}
