package leetcode.dp;

/*
 * LEETCODE 471
 * Given a non-empty string, encode the string such that its encoded length 
 * is the shortest. The encoding rule is: k[encoded_string], where the 
 * encoded_string inside the square brackets is being repeated exactly k times.
 * Note:
 * - k will be a positive integer and encoded string will not be empty or have 
 * extra space.
 * - You may assume that the input string contains only lower case English letters. 
 * The string's length is at most 160.
 * - If an encoding process does not make the string shorter, then do not encode it. 
 * If there are several solutions, return any of them is fine.
 * Example 1: Input: "aaa" Output: "aaa"
 * Explanation: There is no way to encode it such that it is shorter than the input 
 * string, so we do not encode it.
 * Example 2: Input: "aaaaa" Output: "5[a]"
 * Explanation: "5[a]" is shorter than "aaaaa" by 1 character.
 * Example 3: Input: "aaaaaaaaaa" Output: "10[a]"
 * Explanation: "a9[a]" or "9[a]a" are also valid solutions, both of them have the 
 * same length = 5, which is the same as "10[a]".
 * Example 4: Input: "aabcaabcd" Output: "2[aabc]d" 
 * Explanation: "aabc" occurs twice, so one answer can be "2[aabc]d".
 * Example 5: Input: "abbbabbbcabbbabbbc" Output: "2[2[abbb]c]"
 * Explanation: "abbbabbbc" occurs twice, but "abbbabbbc" can also be encoded to "2[abbb]c", 
 * so one answer can be "2[2[abbb]c]".
 * 
 * Company: Google
 * Difficulty: hard
 * Similar Questions: 394(DecodeString), 726
 */
public class EncodeStringWithShortestLength {
    
    // Time complexity: O(n^3)
    public static String encode(String s) {
        int n = s.length();
        
        // dp[i][j] is the string from index i to j in encoded form
        // dp[i][j] = min(dp[i][j], dp[i][k] + dp[k+1][j]) 
        String[][] dp = new String[n][n];
        for(int i = 0; i < n; i++) dp[i][i] = "" + s.charAt(i);
        
        for (int len = 1; len < n; len ++) {
            for (int i = 0; i < n-len; i++) {
                int j = i+len;
                for (int k = i; k < j; k++) {
                    int left = dp[i][k].length();
                    int right = dp[k+1][j].length();
                    // update shortest encoded string within (i, j)
                    if(dp[i][j] == null || left + right < dp[i][j].length()) {
                        dp[i][j] = dp[i][k] + dp[k+1][j];
                    }
                }
             
                // update string within (i, j), encode abcabc
                String substr = s.substring(i, j+1);
                int idx = (substr+substr).indexOf(substr, 1);
                if (idx < substr.length()) {
                    substr = (substr.length()/idx) + "[" + dp[i][i+idx-1] + "]";
                }
                if (dp[i][j] == null || dp[i][j].length() > substr.length()) {
                    dp[i][j] = substr;
                }
            }
        }
        
        
        return dp[0][n-1];
    }
    
    public static void main(String[] args) {
        //String s = "aaa";
        //String s = "aaaaa";
        //String s = "aaaaaaaaaa";
        String s = "aabcaabcd";
        System.out.println(encode(s));
    }

}
