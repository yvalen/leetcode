package leetcode.dp;

/**
 * A message containing letters from A-Z is being encoded to numbers using the following mapping:
 * 	'A' -> 1
 * 	'B' -> 2
 * 	...
 * 	'Z' -> 26
 * Given an encoded message containing digits, determine the total number of ways to decode it. 
 * For example, given encoded message "12", it could be decoded as "AB" (1 2) or "L" (12).
 * The number of ways decoding "12" is 2. 
 */
public class DecodeWays {
	public int numDecodings(String s) {
		if (s == null || s.length() == 0) return 0;
		
		// represents number of decodings for ith element
		int[] dp = new int[s.length() + 1];
		dp[0] = 1; // "" -> 1
		dp[1] = s.charAt(0) == '0' ? 0 : 1;
		for (int i = 2; i <= s.length(); i++) {
			int second = Integer.parseInt(s.substring(i-2, i));
			if (second >= 10 && second <= 26) {
				dp[i] = dp[i-2];
			}
			
			if (s.charAt(i-1) != '0') {
				dp[i] += dp[i-1];
			}
		}
		
		return dp[s.length()];
    }

}
