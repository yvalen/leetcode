package leetcode.string;


public class LongestPalindrome {
    /*
     * LEETCODE 5
     * Given a string s, find the longest palindromic substring in s. 
     * You may assume that the maximum length of s is 1000. 
     * Example: 
     * Input: "babad"
     * Output: "bab" Note: "aba" is also a valid answer. 
     * Example: 
     * Input: "cbbd"
     * Output: "bb"
     * 
     * Company: Amazon, Microsoft, Bloomberg 
     * Difficulty: medium
     */
    // Time complexity: worst case O(n^2)
    public String longestPalindrome(String s) {
        // use to track the start and end position of the longest palindrome
        int start = 0, end = 0; 
        for (int i = 0; i < s.length(); i++) {
            // handle odd length
            int len1 = getLengthOfPalindrome(s, i, i);
            // handle even length, center is between two letters, e.g. abba
            int len2 = getLengthOfPalindrome(s, i, i + 1); 
            int len = Integer.max(len1, len2);
            // update start and end if the last one is shorter
            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        return s.substring(start, end + 1);
    }
    
    private int getLengthOfPalindrome(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        // should minus 1 since left and right are beyond boundary
        return (right - left - 1); 

    }

    // Time complexity: O(n^2), Space complexity: O(n^2)
    public String longestPalindrome_dp(String s) {
        if (s == null || s.isEmpty())
            return s;

        int n = s.length(), maxLength = 0, start = 0;

        // dp[i][j] indicates whether substring starting at i and 
        // ending at j is palindrome
        boolean[][] dp = new boolean[n][n];

        for (int i = n - 1; i >= 0; i--) { // check every start point
            for (int j = i; j < n; j++) { // check every end point, j starts
                                          // from i so that single char will be
                                          // checked
                dp[i][j] = s.charAt(i) == s.charAt(j) && 
                        // if window size is less than 3, just the end 
                        // character should match
                        (j - i < 3 || 
                        dp[i + 1][j - 1] // if window size is greater than 3
                                         // s[i+1][j-1] should be palindrome
                );

                // update max length ans start position
                if (dp[i][j] && (j - i + 1) > maxLength) {
                    maxLength = j - i + 1;
                    start = i;
                }
            }
        }

        // get the sub string
        return s.substring(start, start + maxLength);
    }

    /*
     * LEETCODE 516
     * Given a string s, find the longest palindromic subsequence's length in s.
     * You may assume that the maximum length of s is 1000. A longest
     * palin­dromic sub­se­quence is a sequence that appears in the same
     * rel­a­tive order, but not nec­es­sar­ily contiguous(not sub­string) and
     * palin­drome in nature( means the sub­se­quence will read same from the
     * front and back. 
     * Example 1: Input: "bbbab" 
     * Output: 4 
     * One possible longest palindromic subsequence is "bbbb". 
     * Example 2: Input: "cbbd" 
     * Output: 2 
     * One possible longest palindromic subsequence is "bb".
     * 
     * Company: Amazon, Uber
     * Difficulty: medium
     * Similar Questions: 5(Longest Palindromic Substring)
     * 
     * http://algorithms.tutorialhorizon.com/longest-palindromic-subsequence/
     */
    public int longestPalindromeSubseq(String s) {
        if (s == null || s.isEmpty())
            return 0;

        int n = s.length();
        // dp[i][j] stores the length of the longest palindrome subsequence 
        // in s(i, j)
        int[][] dp = new int[n][n];
        for (int i = n - 1; i >= 0; i--) {
            dp[i][i] = 1;
            for (int j = i + 1; j < n; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                } else {
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i + 1][j]);
                }
            }
        }
        return dp[0][n - 1];
    }

    public static void main(String[] args) {
        LongestPalindrome p = new LongestPalindrome();
        //String s = "bbbab";
        String s = "bb";
        System.out.println(p.longestPalindromeSubseq(s));

    }
}
