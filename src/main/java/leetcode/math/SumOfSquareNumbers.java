package leetcode.math;

/*
 * Given a non-negative integer c, your task is to decide whether there're two integers a and b such that a2 + b2 = c.
 * Example 1: Input: 5 Output: True
 * Explanation: 1 * 1 + 2 * 2 = 5
 * Example 2: Input: 3 Output: False
 * 
 * Company: LinkedIn
 * Difficulty: easy
 */
public class SumOfSquareNumbers {
	
	// a and b can only lie within the range of (0, sqrt(c)​​​)
	// Time complexity: O(sqrt(c)
	public boolean judgeSquareSum_twopointers(int c) {
		int a = 0, b = (int) Math.sqrt(c);
		while (a <= b) {
			int sum = a * a + b* b;
			if (sum == c) return true;
			else if (sum < c) a++;
			else b--;
		}
		return false;
    }
	
	public static void main(String[] args) {
		SumOfSquareNumbers ssn = new SumOfSquareNumbers();
		int c = 2;
		System.out.println(ssn.judgeSquareSum_twopointers(c));
	}
}
