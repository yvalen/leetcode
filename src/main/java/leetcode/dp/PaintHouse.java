package leetcode.dp;

import java.util.stream.IntStream;

public class PaintHouse {
    /*
     * LEETCODE 256 There are a row of n houses, each house can be painted with
     * one of the three colors: red, blue or green. The cost of painting each
     * house with a certain color is different. You have to paint all the houses
     * such that no two adjacent houses have the same color. The cost of
     * painting each house with a certain color is represented by a n x 3 cost
     * matrix. For example, costs[0][0] is the cost of painting house 0 with
     * color red; costs[1][2] is the cost of painting house 1 with color green,
     * and so on... Find the minimum cost to paint all houses. Note: All costs
     * are positive integers.
     * 
     * Company: LinkedIn Difficulty: easy Similar Questions: 265(Paint House
     * II), 276(PaintFence), 198(House Robber), 213(House Robber II)
     */
    public int minCost_withDPArray(int[][] costs) {
        if (costs == null || costs.length == 0 || costs[0].length != 3)
            return 0;

        int n = costs.length;
        int[][] dp = new int[n][3];
        dp[0] = costs[0];
        for (int i = 1; i < n; i++) {
            dp[i][0] = Math.min(dp[i - 1][1], dp[i - 1][2]) + costs[i][0];
            dp[i][1] = Math.min(dp[i - 1][0], dp[i - 1][2]) + costs[i][1];
            dp[i][2] = Math.min(dp[i - 1][0], dp[i - 1][1]) + costs[i][2];
        }

        return Math.min(dp[n - 1][0], Math.min(dp[n - 1][1], dp[n - 1][2]));
    }

    public int minCost(int[][] costs) {
        if (costs == null || costs.length == 0 || costs[0].length != 3)
            return 0;

        int n = costs.length;
        int r = costs[0][0], b = costs[0][1], g = costs[0][2];
        for (int i = 1; i < n; i++) {
            int rr = r, bb = b, gg = g;
            r = Math.min(bb, gg) + costs[i][0];
            b = Math.min(rr, gg) + costs[i][1];
            g = Math.min(rr, bb) + costs[i][2];
        }

        return Math.min(r, Math.min(b, g));
    }

    /*
     * LEETCODE 265 There are a row of n houses, each house can be painted with
     * one of the k colors. The cost of painting each house with a certain color
     * is different. You have to paint all the houses such that no two adjacent
     * houses have the same color. The cost of painting each house with a
     * certain color is represented by a n x k cost matrix. For example,
     * costs[0][0] is the cost of painting house 0 with color 0; costs[1][2] is
     * the cost of painting house 1 with color 2, and so on... Find the minimum
     * cost to paint all houses. Note: All costs are positive integers. Follow
     * up: Could you solve it in O(nk) runtime?
     * 
     * Company: Facebook Difficulty: medium Similar Questions: 256(Paint House),
     * 276(PaintFence), 238(ProductOfArrayExceptSelf), 239(SlidingWindowMaximum)
     */
    public int minCostII_withDPArray(int[][] costs) {
        if (costs == null || costs.length == 0 || costs[0].length == 0)
            return 0;

        int n = costs.length, k = costs[0].length;
        int[][] dp = new int[n][k];
        dp[0] = costs[0];
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < k; j++) {
                int minCost = Integer.MAX_VALUE;
                for (int l = 0; l < k; l++) {
                    if (l != j)
                        minCost = Math.min(dp[i - 1][l], minCost);
                }
                dp[i][j] = minCost + costs[i][j];
            }
        }

        return IntStream.of(dp[n - 1]).min().getAsInt();
    }

    // Complexity: time - O(nk), space O(1)
    public int minCostII(int[][] costs) {
        if (costs == null || costs.length == 0 || costs[0].length == 0)
            return 0;

        // we need to know the min cost to the previous house of any color and
        // if the color j is used on previous house to get prev min cost
        // dp[currentHouse][currentColor] = (currentColor == prevMinColor?
        // prevSecondMin: prevMin) + costs[currentHouse][currentColor]
        int prevMin = 0, prevSecMin = 0, prevMinIdx = -1; // prevMin and
                                                          // prevSecMin should
                                                          // start from 0
        for (int i = 0; i < costs.length; i++) {
            int min = Integer.MAX_VALUE, secMin = Integer.MAX_VALUE, minIdx = -1;
            for (int j = 0; j < costs[0].length; j++) {
                int val = costs[i][j] + ((j == prevMinIdx) ? prevSecMin : prevMin);
                if (min > val) {
                    secMin = min;
                    min = val;
                    minIdx = j;
                } else if (secMin > val) {
                    secMin = val;
                }
            }
            prevMin = min;
            prevSecMin = secMin;
            prevMinIdx = minIdx;
        }

        return prevMin;
    }

    public static void main(String[] args) {
        PaintHouse ph = new PaintHouse();
        // int[][] costs = {
        // {20, 18, 4},
        // {9, 9, 10}
        // };
        /*
         * int[][] costs = { {5, 8, 6}, {19, 14, 13}, {7, 5, 12}, {14, 15, 17},
         * {3, 20, 10} };
         * 
         * System.out.println(ph.minCost(costs));
         */

        int[][] costs = { { 1, 5, 3 }, { 2, 9, 4 } };
        System.out.println(ph.minCostII(costs));

    }
}
