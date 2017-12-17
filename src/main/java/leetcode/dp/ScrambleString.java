package leetcode.dp;

/*
 * Given a string s1, we may represent it as a binary tree by partitioning it to two non-empty substrings recursively.
 * Below is one possible representation of s1 = "great":
 *     great
 *    /    \
 *   gr    eat
 *  / \    /  \
 * g   r  e   at
 * 			 / \
 * 			a   t
 * To scramble the string, we may choose any non-leaf node and swap its two children.
 * For example, if we choose the node "gr" and swap its two children, it produces a scrambled string "rgeat".
 *     rgeat
 *    /    \
 *   rg    eat
 *  / \    /  \
 * r   g  e   at
 * 			 / \
 * 			a   t
 * We say that "rgeat" is a scrambled string of "great".
 * Similarly, if we continue to swap the children of nodes "eat" and "at", it produces a scrambled string "rgtae".
 * 		rgtae
 *	   /    \
 * 	  rg    tae
 *   / \    /  \
 *  r   g  ta  e
 *  	  / \
 *  	 t   a
 *  We say that "rgtae" is a scrambled string of "great".
 *  Given two strings s1 and s2 of the same length, determine if s2 is a scrambled string of s1. 
 */
public class ScrambleString {
    public boolean isScramble_bottomUp(String s1, String s2) {
        if (s1 == null)
            return s2 == null;
        if (s2 == null)
            return s1 == null;
        if (s1.length() != s2.length())
            return false;

        int len = s1.length();

        // dp[k][i][j] indicates whether s1[i...i+k] can be transformed from
        // s2[j...j+k]
        boolean[][][] dp = new boolean[len + 1][len][len];
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                dp[1][i][j] = (s1.charAt(i) == s2.charAt(j));
            }
        }

        for (int k = 2; k <= len; k++) {
            for (int i = 0; i <= len - k; i++) {
                for (int j = 0; j <= len - k; j++) {
                    boolean isScramble = false;
                    for (int q = 1; q < k; q++) {
                        isScramble = (dp[q][i][j] && dp[k - q][i + q][j + q])
                                || (dp[q][i][j + k - q] && dp[k - q][i + q][j]);
                        if (isScramble)
                            break;
                    }
                    dp[k][i][j] = isScramble;
                }
            }
        }

        return dp[len][0][0];
    }

    public boolean isScramble_bottomUp2(String s1, String s2) {
        if (s2 == null)
            return s1 == null;
        if (s1.length() != s2.length())
            return false;

        int len = s1.length();

        // indicates whether the substring s1[i..i + k - 1] is a scramble of
        // s2[j..j + k - 1]
        // Since each of these substrings is a potential node in the tree, we
        // need to check for all possible cuts
        // F(i, j, k) = for some 1 <= q < k we have: (F(i, j, q) AND F(i + q, j
        // + q, k - q)) OR (F(i, j + k - q, q) AND F(i + q, j, k - q))
        boolean[][][] dp = new boolean[len][len][len + 1];

        // Base case is k = 1, where we simply need to check for s1[i] and s2[j]
        // to be equal
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                dp[i][j][1] = (s1.charAt(i) == s2.charAt(j));
            }
        }

        for (int k = 2; k <= len; k++) {
            for (int i = 0; i <= len - k; i++) {
                for (int j = 0; j <= len - k; j++) {
                    for (int q = 1; q < k && !dp[i][j][k]; q++) {
                        dp[i][j][k] = (dp[i][j][q] && dp[i + q][j + q][k - q])
                                || (dp[i][j + k - q][q] && dp[i + q][j][k - q]);
                    }
                }
            }
        }

        return dp[0][0][len];
    }

    public boolean isScramble_recursive(String s1, String s2) {
        if (s1 == null)
            return s2 == null;
        if (s2 == null)
            return s1 == null;
        if (s1.length() != s2.length())
            return false;

        if (s1.equals(s2))
            return true;

        int len = s1.length();
        int[] charCount = new int[256];
        for (int i = 0; i < len; i++) {
            charCount[s1.charAt(i) - 'a']++;
            charCount[s2.charAt(i) - 'a']--;
        }
        for (int count : charCount) {
            if (count != 0)
                return false;
        }

        for (int i = 1; i < len; i++) {
            if (isScramble_recursive(s1.substring(0, i), s2.substring(0, i))
                    && isScramble_recursive(s1.substring(i), s2.substring(i))) {
                return true;
            }

            if (isScramble_recursive(s1.substring(0, i), s2.substring(len - i))
                    && isScramble_recursive(s1.substring(i), s2.substring(0, len - i))) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        ScrambleString s = new ScrambleString();
        // String s1 = "abc", s2 = "acb";
        String s1 = "abc", s2 = "bac";
        // String s1 = "a", s2 = "a";
        // String s1 = "ab", s2 = "ba";
        // String s1 = "abcd", s2 = "bdac";
        System.out.println(s.isScramble_bottomUp2(s1, s2));
        // System.out.println(s.isScramble_recursive(s1, s2));
    }

}
