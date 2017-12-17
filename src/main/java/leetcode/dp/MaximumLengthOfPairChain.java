package leetcode.dp;

import java.util.Arrays;

/*
 * LEETCODE 646
 * 
 * You are given n pairs of numbers. In every pair, the first number is always smaller than the second number. Now, we define a pair (c, d) 
 * can follow another pair (a, b) if and only if b < c. Chain of pairs can be formed in this fashion. Given a set of pairs, find the length 
 * longest chain which can be formed. You needn't use up all the given pairs. You can select pairs in any order.
 * Example 1: Input: [[1,2], [2,3], [3,4]] Output: 2
 * Explanation: The longest chain is [1,2] -> [3,4]
 * Note: The number of given pairs will be in the range [1, 1000].
 * 
 * Company: Amazon
 * Difficulty: medium
 * Similar Questions: 300(LongestIncreasingSubsequence)
 */
public class MaximumLengthOfPairChain {
    public int findLongestChain(int[][] pairs) {
        if (pairs == null || pairs.length == 0)
            return 0;

        Arrays.sort(pairs, (a, b) -> (a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]));

        int n = pairs.length;
        int maxLen = 1;
        int[] dp = new int[n];
        for (int i = 0; i < n; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (pairs[i][0] > pairs[j][1]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            maxLen = Math.max(maxLen, dp[i]);
        }
        return maxLen;
    }

    public int findLongestChain_greedy(int[][] pairs) {
        if (pairs == null || pairs.length == 0)
            return 0;

        // sort the second number in ascending order
        Arrays.sort(pairs, (a, b) -> a[1] - b[1]);

        int end = pairs[0][1];
        int maxLen = 1;
        for (int i = 1; i < pairs.length; i++) {
            if (pairs[i][0] > end) {
                maxLen++;
                end = pairs[i][1];
            }
        }
        return maxLen;
    }
}
