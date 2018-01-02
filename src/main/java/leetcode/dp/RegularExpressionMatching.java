package leetcode.dp;

/*
 * LEETCODE 10
 * Implement regular expression matching with support for '.' and '*'.
 * '.' Matches any single character.
 * '*' Matches zero or more of the preceding element.
 * The matching should cover the entire input string (not partial).
 * Some examples:
 * 	isMatch("aa","a") → false
 * 	isMatch("aa","aa") → true
 * 	isMatch("aaa","aa") → false
 * 	isMatch("aa", "a*") → true
 * 	isMatch("aa", ".*") → true
 * 	isMatch("ab", ".*") → true
 * 	isMatch("aab", "c*a*b") → true
 * 
 * Company: Google, Facebook, Uber, Twitter, Airbnb
 * Difficulty: hard
 * Similar Questions: 44(WildcardMatching) 
 */
public class RegularExpressionMatching {

    /*
     * 1, If p.charAt(j) == s.charAt(i) : dp[i][j] = dp[i-1][j-1]; 
     * 2, If p.charAt(j) == '.' : dp[i][j] = dp[i-1][j-1]; 
     * 3, If p.charAt(j) == '*': here are two sub conditions: 
     * (1) if p.charAt(j-1) != s.charAt(i) : 
     * dp[i][j] = dp[i][j-2] //in this case, a* only counts as empty 
     * (2) if p.charAt(j-1) == s.charAt(i) or p.charAt(j-1) == '.': 
     * dp[i][j] = dp[i-1][j] //in this case, a* counts as multiple a or 
     * dp[i][j] = dp[i][j-1] // in this case, a* counts as single a or 
     * dp[i][j] = dp[i][j-2] // in this case, a* counts as empty
     * https://xiaokangstudynotes.com/2017/01/21/dynamic-programming-regular-expression-matching/
     */
    // Time complexity: O(mn): m - length of s, n - length of p
    // Space complexity: O(mn)
    public boolean isMatch_dp(String s, String p) {
        if (s == null || p == null)
            return false;

        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
        // two empty strings is a match
        dp[0][0] = true;

        // s is empty string while p is not
        for (int j = 0; j < p.length(); j++) {
            if (p.charAt(j) == '*') {
                // each * can eliminate the character before it
                dp[0][j + 1] = dp[0][j - 1];
            }
        }

        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; j < p.length(); j++) {
                if (p.charAt(j) == s.charAt(i) || p.charAt(j) == '.') {
                    dp[i + 1][j + 1] = dp[i][j];
                } else if (p.charAt(j) == '*') {
                    if (p.charAt(j - 1) == s.charAt(i) || p.charAt(j - 1) == '.') {
                        dp[i + 1][j + 1] = 
                                dp[i][j + 1] || // a* is counted as multiple a
                                dp[i + 1][j] || // a* is counted as a single a
                                dp[i + 1][j - 1]; // a* is counted as empty
                    } else {
                        dp[i + 1][j + 1] = dp[i + 1][j - 1]; // a* is counted as empty
                    }
                }
            }
        }

        return dp[s.length()][p.length()];
    }

    public boolean isMatch_recursive(String s, String p) {
        if (s == null || p == null)
            return false;
        return isMatch_recursiveHelper(s, p, 0, 0);
    }

    public boolean isMatch_recursiveHelper(String s, String p, int sIndex, int pIndex) {
        // all chars in pattern has been checked
        if (pIndex == p.length())
            return sIndex == s.length();

        // at last character of pattern
        if (pIndex == p.length() - 1) {
            return (sIndex == s.length() - 1) && isMatchAt(s, p, sIndex, pIndex);
        }

        // next char in pattern is not *
        if (pIndex < p.length() && p.charAt(pIndex + 1) != '*') {
            if (sIndex == s.length())
                return false; // all chars in s has been checked

            if (isMatchAt(s, p, sIndex, pIndex)) {
                return isMatch_recursiveHelper(s, p, sIndex + 1, pIndex + 1);
            }

            return false;
        }

        // * is counted as empty
        if (isMatch_recursiveHelper(s, p, sIndex, pIndex + 2))
            return true;

        // * is counted as 1 or more characters
        for (int i = sIndex; i < s.length(); i++) {
            if (!isMatchAt(s, p, i, pIndex))
                return false;
            if (isMatch_recursiveHelper(s, p, i + 1, pIndex + 2)) { // use i+1
                                                                    // to match
                                                                    // one or
                                                                    // more
                return true;
            }
        }

        return false;
    }

    private boolean isMatchAt(String s, String p, int i, int j) {
        return s.charAt(i) == p.charAt(j) || p.charAt(j) == '.';
    }

    public static void main(String[] args) {
        RegularExpressionMatching r = new RegularExpressionMatching();

        //String s = "aa", p = "a*";
        String s = "", p = ".*";
        System.out.println(r.isMatch_recursive(s, p));
    }

}
