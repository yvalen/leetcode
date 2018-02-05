package leetcode.math;

/* LEETCODE 69
 * Implement int sqrt(int x). Compute and return the square root of x.
 * x is guaranteed to be a non-negative integer.
 * Example 1:
 * Input: 4
 * Output: 2
 * Example 2:
 * Input: 8
 * Output: 2
 * Explanation: The square root of 8 is 2.82842..., and since we want to 
 * return an integer, the decimal part will be truncated.
 * 
 * A square root of a number a is a number y such that y^2 = a; in other words, 
 * a number y whose square (the result of multiplying the number by itself, y⋅y) 
 * is a. Every nonnegative real number a has a unique nonnegative square root, 
 * called the principal square root, which is denoted by √a, where √ is called the 
 * radical sign or radix. The term whose root is being considered is known as the 
 * radicand. The radicand is the number or expression underneath the radical sign, 
 * 
 * Company: Bloomberg, Facebook, Apple
 * Difficulty: easy
 * Similar Questions: 50(Pow)
 */
public class SquareRoot {
    public int mySqrt(int x) {
        int abs = Math.abs(x);
        int lo = 1, hi = abs, mid = 0;
        while (lo <= hi) {
            mid = lo + (hi - lo) / 2;
            if (mid > abs / mid) {
                hi = mid - 1;
            } else {
                if (mid + 1 > abs / (mid + 1))
                    break;
                lo = mid + 1;
            }
        }

        return (x > 0) ? mid : -mid;
    }

    public int mySqrt_useRight(int x) {
        int lo = 1, hi = x;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            // need to use division instead of multiplication 
            // as mid*mid may cause overflow
            if (mid == x / mid)
                return mid;
            else if (mid > x / mid)
                hi = mid - 1;
            else
                lo = mid + 1;
        }

        return hi;
    }
    
    public float mySqrt_float(int x) {
        float lo = 1.0f, hi = x;
        float mid = lo + (hi - lo) / 2;
        while (Math.abs(mid*mid-x) > 0.00001) {
            if (mid * mid < x ) lo = mid;
            else if (mid*mid > x) hi = mid;
            mid = lo + (hi - lo) / 2;
        }

        return mid;
    }

    public static void main(String[] args) {
        SquareRoot s = new SquareRoot();
        // int x = 2;
        // System.out.println(s.mySqrt(x));

        /*
        for (int i = 0; i <= 21; i++) {
            System.out.println("i=" + i + " sqrt=" + s.mySqrt_useRight(i));
        }*/

        int x = 5;
        System.out.println(s.mySqrt_float(x));
    }
}
