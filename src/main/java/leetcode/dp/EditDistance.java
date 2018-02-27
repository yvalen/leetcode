package leetcode.dp;

/*
 * LEETCODE 72
 * Given two words word1 and word2, find the minimum number of steps required 
 * to convert word1 to word2. (each operation is counted as 1 step.)
 * You have the following 3 operations permitted on a word:
 * 	a) Insert a character
 * 	b) Delete a character
 * 	c) Replace a character
 * 
 * Difficulty: hard
 * Similar Questions: 161(OneEditDistance)
 */
public class EditDistance {
    public int minDistance(String word1, String word2) {
        if (word1 == null)
            return word2 == null ? 0 : word2.length();
        if (word2 == null)
            return word1.length();

        // dp[i][j] represents the minimum number of operations to convert
        // word1[0..i - 1] to word2[0..j - 1].
        int[][] dp = new int[word1.length() + 1][word2.length() + 1];

        // boundary cases, convert string into empty string
        // need to check for <= here
        for (int i = 0; i <= word1.length(); i++) {
            dp[i][0] = i;
        }
        for (int j = 0; j <= word2.length(); j++) {
            dp[0][j] = j;
        }

        for (int i = 0; i < word1.length(); i++) {
            for (int j = 0; j < word2.length(); j++) {
                if (word1.charAt(i) == word2.charAt(j)) {
                    dp[i + 1][j + 1] = dp[i][j];
                } else {
                    // dp[i][j] - replace word1[i] with word2[j]
                    // dp[i][j+1] - delete word1[i], convert word1(0...i-1) into word2(0...j)
                    // dp[i+1][j] - insert at word1[i]
                    dp[i+1][j+1] = Math.min(dp[i][j], Math.min(dp[i][j+1], dp[i+1][j]))+1;
                }
            }
        }

        return dp[word1.length()][word2.length()];
    }

    public int minDistance_oneDArray(String word1, String word2) {
        if (word1 == null)
            return word2 == null ? 0 : word2.length();
        if (word2 == null)
            return word1.length();

        int[] dp = new int[word1.length() + 1];
        for (int i = 0; i <= word1.length(); i++)
            dp[i] = i;

        for (int j = 1; j <= word2.length(); j++) {
            int pre = dp[0]; // -> dp[i-1][j-1]
            dp[0] = j;
            for (int i = 1; i <= word1.length(); i++) {
                int temp = dp[i]; // -> dp[i-1][j]
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i] = pre;
                } else {
                    dp[i] = Integer.min(pre + 1, Integer.min(dp[i] + 1, dp[i - 1] + 1));
                }
                pre = temp;
            }
        }

        return dp[word1.length()];
    }

    public static void main(String[] args) {
        EditDistance e = new EditDistance();
        // String word1 = "a", word2 = "ab";
        String word1 = "", word2 = "a";
        System.out.println(e.minDistance_oneDArray(word1, word2));
    }

}
