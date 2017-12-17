package leetcode.dp;

/*
 * Implement wildcard pattern matching with support for '?' and '*'.
 * 	'?' Matches any single character.
 * 	'*' Matches any sequence of characters (including the empty sequence).
 * The matching should cover the entire input string (not partial).
 * The function prototype should be:
 * 	bool isMatch(const char *s, const char *p)
 * Some examples:
 * 	isMatch("aa","a") → false
 * 	isMatch("aa","aa") → true
 *	isMatch("aaa","aa") → false
 *	isMatch("aa", "*") → true
 *	isMatch("aa", "a*") → true
 *	isMatch("ab", "?*") → true
 *	isMatch("aab", "c*a*b") → false
 */
public class WildcardMatching {

    /*
     * If(p[j-1]!='*') f(i, j) = f(i-1, j-1) && (s[i-1]==p[j-1] || p[j-1]=='?')
     * If(p[j-1]=='*') f(i, j) = f(i, j-1) || f(i-1, j) f(i,j-1) is true:
     * s[0:i-1] matches p[0:j-2] and * is not used here f(i-1,j) is true:
     * s[0:i-2] matches p[0:j-1] and * is used to match s[i-1].
     */
    public boolean isMatch_dp(String s, String p) {
        if (s == null || p == null)
            return false;

        // dp[i][j] indicates whether s(0...i) matches p(0...j)
        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
        dp[0][0] = true;
        for (int j = 0; j < p.length(); j++) {
            if (p.charAt(j) == '*') {
                dp[0][j + 1] = dp[0][j];
            }
        }

        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; j < p.length(); j++) {
                if (p.charAt(j) == '*') {
                    dp[i + 1][j + 1] = (dp[i + 1][j] || dp[i][j + 1]);
                } else if (isCharMatch(s, p, i, j)) {
                    dp[i + 1][j + 1] = dp[i][j];
                }
            }
        }
        return dp[s.length()][p.length()];
    }

    // greedy solution with idea of DFS
    // starj stores the position of last * in p
    // last_match stores the position of the previous matched char in s after a
    // *
    // e.g.
    // s: a c d s c d
    // p: * c d
    // after the first match of the *, starj = 0 and last_match = 1
    // when we come to i = 3 and j = 3, we know that the previous match of * is
    // actually wrong,
    // (the first branch of DFS we take is wrong)
    // then it resets j = starj + 1
    // since we already know i = last_match will give us the wrong answer
    // so this time i = last_match+1 and we try to find a longer match of *
    // then after another match we have starj = 0 and last_match = 4, which is
    // the right solution
    // since we don't know where the right match for * ends, we need to take a
    // guess (one branch in DFS),
    // and store the information(starj and last_match) so we can always backup
    // to the last correct place and take another guess.
    // Complexity O(m*n): s=aaaab p=*ab
    public boolean isMatch_dfsGreedy(String s, String p) {
        if (s == null || p == null)
            return false;

        int i = 0, j = 0;
        int lastStar = -1, lastMatch = -1;
        while (i < s.length()) {
            if (j < p.length() && isCharMatch(s, p, i, j)) {
                // advance both pointers when (both characters match) or ('?'
                // found in pattern)
                // note that p will not advance beyond its length
                i++;
                j++;
            } else if (j < p.length() && p.charAt(j) == '*') {
                // * found in pattern, track index of *, only advancing pattern
                // pointer
                lastStar = j;
                j++;
                lastMatch = i;
            } else if (lastStar != -1) {
                // current characters didn't match, last pattern pointer was *,
                // current pattern pointer is not *
                // only advancing pattern pointer
                j = lastStar + 1;
                i = ++lastMatch;
            } else {
                return false;
            }
        }

        // check for remaining characters in pattern
        while (j < p.length() && p.charAt(j) == '*')
            j++;

        return j == p.length();
    }

    private boolean isCharMatch(String s, String p, int i, int j) {
        return s.charAt(i) == p.charAt(j) || p.charAt(j) == '?';
    }

    public static void main(String[] args) {
        WildcardMatching w = new WildcardMatching();

        String s = "aaaab", p = "*ab";
        System.out.println(w.isMatch_dfsGreedy(s, p));

    }
}
