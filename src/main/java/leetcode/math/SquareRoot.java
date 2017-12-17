package leetcode.math;

/*
 * A square root of a number a is a number y such that y2 = a; in other words, a number y whose square (the result of multiplying the number by itself, y⋅y) is a. 
 * Every nonnegative real number a has a unique nonnegative square root, called the principal square root, which is denoted by √a, where √ is called the radical sign or radix.
 * The term whose root is being considered is known as the radicand. The radicand is the number or expression underneath the radical sign, 
 * 
 * Company: Bloomberg, Facebook, Apple
 * Difficulty: easy
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
            if (mid == x / mid)
                return mid;
            else if (mid > x / mid)
                hi = mid - 1;
            else
                lo = mid + 1;
        }

        return hi;
    }

    public static void main(String[] args) {
        SquareRoot s = new SquareRoot();
        // int x = 2;
        // System.out.println(s.mySqrt(x));

        for (int i = 0; i <= 21; i++) {
            System.out.println("i=" + i + " sqrt=" + s.mySqrt_useRight(i));
        }

    }
}
