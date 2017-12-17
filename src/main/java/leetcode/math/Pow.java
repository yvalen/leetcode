package leetcode.math;

/*
 * Implement pow(x, n). 
 * 
 * Company: LinkedIn, Google, Facebook, Bloomberg
 * Difficulty: medium
 */
public class Pow {
    public double myPow_On(double x, int n) { // TLE
        double result = 1.0;
        for (int i = 1; i <= Math.abs(n); i++) {
            result = n > 0 ? result * x : result / x;
        }
        return result;
    }

    // O(logn)
    public double myPow(double x, int n) {
        if (x == 0.0)
            return 0.0;
        if (n == 0)
            return 1.0;
        if (n % 2 == 0)
            return myPow(x * x, n / 2);
        return (n > 0 ? x : 1.0 / x) * myPow(x * x, n / 2);
    }
}
