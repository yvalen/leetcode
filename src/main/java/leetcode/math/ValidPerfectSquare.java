package leetcode.math;

/*
 * Given a positive integer num, write a function which returns True if num is a perfect square else False.
 * Note: Do not use any built-in library function such as sqrt.
 * Example 1: Input: 16 Returns: True
 * Example 2: Input: 14 Returns: False
 * 
 * Company: LinkedIn
 * Difficulty: easy
 */
public class ValidPerfectSquare {
	
	// The square of n-​th​​ positive integer can be represented as a sum of first n odd positive integers.
	// n^2 = 1 + 3 + 5 + ... (2*n-1)
	// https://leetcode.com/articles/sum-of-square-numbers/#approach-2-better-brute-force-time-limit-exceeded
	// Time complexity: O(sqrt(n))
	public boolean isPerfectSquare(int num) {
        int i = 1, sum = 0;  // use long to prevent overflow
        while (sum < num) {
        	sum += i;
        	i += 2;
        }
        return (sum == num);
		
    }
	
	public boolean isPerfectSquare_binarySearch(int num) {
        long lo = 1, hi = num; // use long to prevent overflow
        while (lo <= hi) {
        	long mid = lo + (hi - lo) / 2;
        	long square = mid * mid;
        	if (square == num) return true;
        	else if (square < num) lo = mid + 1;
        	else hi = mid - 1;
        }
		return false;
    }
	
	public static void main(String[] args) {
		ValidPerfectSquare  vps = new ValidPerfectSquare ();
		//int num = 2;
		int num = Integer.MAX_VALUE;
		System.out.println(vps.isPerfectSquare_binarySearch(num));
	}
}
