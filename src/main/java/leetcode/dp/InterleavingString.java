package leetcode.dp;

/*
 * Given s1, s2, s3, find whether s3 is formed by the interleaving of s1 and s2.
 * For example, given: s1 = "aabcc", s2 = "dbbca",
 * when s3 = "aadbbcbcac", return true.
 * when s3 = "aadbbbaccc", return false. 
 * https://leetcode.com/articles/interleaving-strings/
 */
public class InterleavingString {
	
	// Complexity: O(2^(m+n)) - time, O(m+n) - space
	public boolean isInterleave_bruteForce(String s1, String s2, String s3) {
        return bruteForceHelper(s1, s2, s3, 0, 0, "");
    }
	
	private boolean bruteForceHelper(String s1, String s2, String s3, int i, int j, String result) {
		if (result.equals(s3) && i == s1.length() && j == s2.length()) return true;
		
		if (i < s1.length()) {
			if (bruteForceHelper(s1, s2, s3, i+1, j, result+s1.charAt(i))) return true;
		}
		
		if (j < s2.length()) {
			if (bruteForceHelper(s1, s2, s3, i, j+1, result+s2.charAt(j))) return true;
		}
		
		return false;
	}
	
	// Complexity: O(m*n) - time, O(m*n) - space
	public boolean isInterleave_dpTwoD(String s1, String s2, String s3) {
		if (s3.length() != s1.length() + s2.length()) return false;
		
		int len1 = s1.length(), len2 = s2.length();
		
		// dp table represents if s3 is interleaving at (i+j)th position when s1 is at ith position and s2 is at jth position
		boolean[][] dp = new boolean[s1.length()+1][s2.length()+1];
		dp[0][0] = true; // 0th position means empty string, empty and empty matches empty
		
		// s2 is empty, it is interleaving when previous position is interleaving and current position of s1 and s3 matches
		for (int i = 1; i <= len1; i++) {
			dp[i][0] = dp[i-1][0] &&  (s1.charAt(i-1) == s3.charAt(i-1)); 
		}
		
		// s1 is empty, it is interleaving when previous position is interleaving and current position of s2 and s3 matches
		for (int j = 1; j <= len2; j++) {
			dp[0][j] = dp[0][j-1] && (s2.charAt(j-1) == s3.charAt(j-1));
		}
		
		for (int i = 1; i <= len1; i++) {
			for (int j = 1; j <= len2; j++) {
				dp[i][j] = (dp[i-1][j] && s1.charAt(i-1)==s3.charAt(i+j-1)) ||
						(dp[i][j-1] && s2.charAt(j-1) == s3.charAt(i+j-1));
			}
		}
		
        return dp[len1][len2];
    }

	// Complexity: O(m*n) - time, O(n) - space
		public boolean isInterleave_dpOneD(String s1, String s2, String s3) {
			if (s3.length() != s1.length() + s2.length()) return false;
			
			int len1 = s1.length(), len2 = s2.length();
			
			// 1-d array to store the results of the prefixes of the strings processed so far. 
			// using dp[i−1] and the previous value of dp[i] to update dp[i]
			boolean[] dp = new boolean[s2.length()+1];
			
			for (int i = 0; i <= len1; i++) {
				for (int j = 0; j <= len2; j++) {
					if (i == 0 && j == 0) {
						dp[j] = true;
					}
					else if (i == 0) {
						dp[j] = dp[j-1] && s2.charAt(j-1) == s3.charAt(j)-1;
					}
					else if (j == 0) {
						dp[j] = dp[j] && s1.charAt(i-1) == s3.charAt(i -1); 
					}
					else {
						dp[j] = (dp[j] && s1.charAt(i-1) == s3.charAt(j+i-1)) ||
								(dp[j-1] && s2.charAt(j-1) == s3.charAt(i+j-1));
					}
				}
			}
			
	        return dp[len2];
	    }

	
	public static void main(String[] args) {
		InterleavingString i = new InterleavingString();
		String s1 = "aabcc", s2 = "dbbca";
		String s3 = "aadbbcbcac";
		//String s3 = "aadbbbaccc";
		System.out.println(i.isInterleave_dpTwoD(s1, s2, s3));
		
	}
}
