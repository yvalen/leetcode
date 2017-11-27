package leetcode.math;

/*
 * LEETCODE 7
 * Reverse digits of an integer.
 * Example1: x = 123, return 321
 * Example2: x = -123, return -321 
 * Note: The input is assumed to be a 32-bit signed integer. Your function should return 0 when the reversed integer overflows. 
 * 
 * Hint: Here are some good questions to ask before coding. Bonus points for you if you have already thought through this!
 * - If the integer's last digit is 0, what should the output be? ie, cases such as 10, 100.
 * - Did you notice that the reversed integer might overflow? Assume the input is a 32-bit integer, then the reverse of 1000000003 overflows. 
 * How should you handle such cases?
 * - For the purpose of this problem, assume that your function returns 0 when the reversed integer overflows.
 * 
 * Company: Apple, Bloomberg
 * Difficulty: easy
 * Similar Questions: 8(StringToInteger)
 */
public class ReverseInteger {
	private static int MAX_DIV = Integer.MAX_VALUE / 10;
	
    public int reverse(int x) {
        boolean isNegative = (x < 0);
        x = Math.abs(x);
        
        int num = 0;
        while (x > 0) {
            if (num > MAX_DIV) return 0;
            num = num * 10 + x % 10;
            x /= 10;
        }
        
        return num * (isNegative ? -1 : 1);
    }
	
	public int reverse_withLong(int x) {
		long ret = 0;
		while (x != 0) {
			ret = ret * 10 + x % 10;
			x = x / 10;
		}
		
		if (ret < Integer.MIN_VALUE || ret > Integer.MAX_VALUE) {
			return 0;
		}
		
		return (int) ret;
	}
	
	public static void main(String[] args) {
		ReverseInteger r = new ReverseInteger();
		int x = 1534236469;
		int i = r.reverse(x);
		System.out.println(i);
	}
	
}
