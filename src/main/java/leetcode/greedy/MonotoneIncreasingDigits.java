package leetcode.greedy;

/*
 * LEETCODE 738
 * Given a non-negative integer N, find the largest number that is less than 
 * or equal to N with monotone increasing digits. (Recall that an integer has 
 * monotone increasing digits if and only if each pair of adjacent digits x 
 * and y satisfy x <= y.)
 * Example 1: Input: N = 10 Output: 9
 * Example 2: Input: N = 1234 Output: 1234
 * Example 3: Input: N = 332 Output: 299
 * Note: N is an integer in the range [0, 10^9]. 
 * 
 * Company: Amazon
 * Difficulty: medium
 * Similar Questions: 402()
 */
public class MonotoneIncreasingDigits {
	public int monotoneIncreasingDigits(int N) {
        if (N < 10) return N;
        String s = String.valueOf(N);
        char[] chars = s.toCharArray();
        int marker = chars.length, n = chars.length;
        for (int i = n-1; i > 0; i--) {
            if (chars[i] < chars[i-1]) {
                marker = i;
                chars[i-1] -= 1;
            }
        }
        for (int i = marker; i < n; i++) {
            chars[i] = '9';
        }
        
        return Integer.parseInt(new String(chars));
    }

}
