package leetcode.dp;

/*
 * Given a string S and a string T, count the number of distinct subsequences of T in S.
 * A subsequence of a string is a new string which is formed from the original string by deleting some 
 * (can be none) of the characters without disturbing the relative positions of the remaining characters. 
 * (ie, "ACE" is a subsequence of "ABCDE" while "AEC" is not).
 * Here is an example: S = "rabbbit", T = "rabbit", return 3. 
 */
public class DistinctSubsequence {
	public int numDistinct(String s, String t) {
		if (s == null || t == null || s.isEmpty()) return 0;
        
		// dp[i+1][j+1] stores the number of times s(0...j) contains t(0...i) as distinct subsequence
		int[][] dp = new int[t.length()+1][s.length()+1];
		
		// empty string is a subsequence of any string, but only 1 time
		for (int j = 0; j <= s.length(); j++) {
			dp[0][j] = 1;
		}
		
		// empty string cannot contain non-empty string as a substring
		// dp [i][0] = 0
		
		for (int i = 0; i < t.length(); i++) { 
			for (int j = 0; j < s.length(); j++) {
				if (t.charAt(i) == s.charAt(j)) {
					// dp[i][j] - use the matching char in current subsequence
					// dp[i+1][j] - don't use the matching character in current subsequence
					dp[i+1][j+1] = dp[i][j] + dp[i+1][j];
				}
				else {
					// when not equal, we have the same number of distinct subsequences 
					// as we had without the new character
					dp[i+1][j+1] = dp[i+1][j];
				}		
			}
		}		
		return dp[t.length()][s.length()];
    }
	
	public static void main(String[] args) {
		DistinctSubsequence d = new DistinctSubsequence();
		String s = "b", t = "b";
		System.out.println(d.numDistinct(s, t));
	}

}
