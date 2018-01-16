package leetcode.dp;

import java.util.Arrays;

import leetcode.array.ArrayUtil;

// https://discuss.leetcode.com/topic/97310/most-consistent-ways-of-dealing-with-the-series-of-stock-problems
public class BuyAndSellStock {

    /**
     * LEETCODE 121 
     * Say you have an array for which the ith element is the price of a given stock on day i. 
     * If you were only permitted to complete at most one transaction (ie, buy one and sell 
     * one share of the stock), design an algorithm to find the maximum profit. 
     * Example 1: Input: [7, 1, 5, 3, 6, 4] Output: 5 
     * max. difference = 6-1 = 5 (not 7-1 = 6, as selling price needs to be larger than buying price) 
     * Example 2: Input: [7, 6, 4, 3, 1] Output: 0 
     * In this case, no transaction is done, i.e. max profit = 0.
     * http://www.sigmainfy.com/blog/leetcode-stocki.html
     * 
     * Company: Facebook, Microsoft, Amazon, Bloomberg, Uber 
     * Difficulty: easy
     * Similar Questions: 122(BuyAndSellStockII), 123, 188, 53(MaximumSubArray),
     * 309(BuyAndSellStockWithCoolDown)
     */
    // Time complexity: O(n) Space complexity: O(1)
    public int maxProfit1(int[] prices) {
        if (prices == null || prices.length == 0)
            return 0;

        int profit = 0, buyPrice = prices[0];
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] < buyPrice) {
                buyPrice = prices[i];
            } else if (prices[i] - buyPrice > profit) {
                profit = prices[i] - buyPrice;
            }
        }
        return profit;
    }

    // dp[i] denotes the max profit on ith day. We should get the max profit on
    // (i + 1)th day from
    // - profit from previous days, or
    // - profit gained on this day (current price - minimum price before)
    // And only after this, we can update the minimum price.
    public int maxProfit1_dp(int[] prices) {
        if (prices == null || prices.length == 0)
            return 0;
        int minPrice = Integer.MAX_VALUE, maxProfit = 0;
        for (int i = 0; i < prices.length; i++) {
            minPrice = Math.min(minPrice, prices[i]);
            maxProfit = Math.max(maxProfit, prices[i] - minPrice);
        }
        return maxProfit;

        /*
         * int maxProfit = 0, minPrice = prices[0]; for (int i = 1; i <
         * prices.length; i++) { maxProfit = Math.max(maxProfit, prices[i] -
         * minPrice); minPrice = Math.min(minPrice, prices[i]); } return
         * maxProfit;
         */
    }

    /**
     * LEETCODE 123 Say you have an array for which the ith element is the price
     * of a given stock on day i. Design an algorithm to find the maximum
     * profit. You may complete at most two transactions. You may not engage in
     * multiple transactions at the same time (ie, you must sell the stock
     * before you buy again).
     * 
     * Difficulty: hard Similar Questions: 122(BuyAndSellStockII), 121, 188,
     * 689(MaxSumOfNonOverlappingSubArrays)
     */
    public int maxProfit3(int[] prices) {
        if (prices == null || prices.length == 0)
            return 0;

        // First assume that we have no money, so buy1 means that we have to
        // borrow money from others,
        // we want to borrow less so that we have to make our balance as max as
        // we can(because this is negative).
        // sell1 means we decide to sell the stock, after selling it we have
        // price[i] money and we have to give back
        // the money we owed, so we have price[i] - |buy1| = prices[i ] + buy1,
        // we want to make this max.
        // buy2 means we want to buy another stock, we already have sell1 money,
        // so after buying stock2 we have
        // buy2 = sell1 - price[i] money left, we want more money left, so we
        // make it max
        // sell2 means we want to sell stock2, we can have price[i] money after
        // selling it, and we have buy2 money left before,
        // so sell2 = buy2 + prices[i], we make this max.
        // So sell2 is the most money we can have.

        // buy1 and sell1 are for the first transaction, buy2 and sell2 are for
        // the second transaction.
        // Transition relation:
        // buy1[i] = max( - prices[i], buy1[i - 1])
        // sell1[i] = max(buy1[i - 1] + price[i], sell1[i - 1])
        // buy2[i] = max( sell1[i -1] - prices[i], buy2[i - 1])
        // sell2[i] = max(buy2[i - 1] + price[i], sell2[i - 1])

        int buy1 = Integer.MIN_VALUE, buy2 = Integer.MIN_VALUE;
        int sell1 = 0, sell2 = 0;
        for (int price : prices) {
            sell2 = Integer.max(sell2, buy2 + price);
            buy2 = Integer.max(buy2, sell1 - price);
            sell1 = Integer.max(sell1, buy1 + price);
            buy1 = Integer.max(buy1, -price);
        }

        return sell2;
    }

    /**
     * LEETCODE 188 Say you have an array for which the ith element is the price
     * of a given stock on day i. Design an algorithm to find the maximum
     * profit. You may complete at most k transactions. Note: You may not engage
     * in multiple transactions at the same time (ie, you must sell the stock
     * before you buy again).
     * 
     * Difficulty: hard Similar Questions: 121(Best Time to Buy and Sell Stock),
     * 123(Best Time to Buy and Sell Stock III), 122(BuyAndSellStockII)
     */
    // dp[k][i]: max profit up to day i (included) with at most k transactions
    // (global optimal objective)
    // g[k][i]: max profit up to day i (included) with at most k transactions
    // AND we sell at day i
    // (local optimal objective, why local? think about it!)
    // DP recursive formula
    // 1. dp[k][i] = max ( dp[k][i-1], g[k][i] )
    // 2. g[k][i] = max_{j=0,...,i-1} (p[i] - p[j] + f[k-1][j-1])
    // This means if we don't sell at day i, then dp[k][i] is just dp[k][i-1];
    // otherwise dp[k][i] will be the max profit that we can achieve if we sell
    // at day i
    // Since we will sell at day i anyway, that means we need to buy at a
    // certain previous day,
    // for a particular j, the best we can have is p[i] - p[j] + f[k-1][j-1].
    // dp[0][j] = 0 - 0 transaction makes 0 profit
    // dp[i][0] = 0 - cannot make any transaction if there is only one price
    public int maxProfit4_twoDArray(int k, int[] prices) {
        if (k <= 0 || prices == null || prices.length <= 1)
            return 0;

        if (k >= prices.length / 2) {
            // we can do as many transactions as we want. Sell stock II case
            int maxProfit = 0;
            for (int i = 1; i < prices.length; i++) {
                // as long as there is a price gap, we gain a profit.
                maxProfit += Math.max(prices[i] - prices[i - 1], 0);
            }
            return maxProfit;
        }

        int[][] dp = new int[k + 1][prices.length];
        for (int i = 1; i <= k; i++) {
            int profit = -prices[0];
            for (int j = 1; j < prices.length; j++) {
                // dp[i][j-1] - no transaction at jth day
                // prices[j] + localMax -> prices[j]-prices[jj]+dp[i-1][jj] for
                // (jj in 0...j-1)
                dp[i][j] = Integer.max(dp[i][j - 1], prices[j] + profit);
                // update the max profit of doing i-1 transaction at j-1 day and
                // buy the stock at prices[j], this will be used in the next
                // iteration
                // localMax = Math.max(localMax, dp[i-1][j] - prices[j]); //
                // allow buy and sell on same day
                profit = Math.max(profit, dp[i - 1][j - 1] - prices[j]); // not
                                                                         // allow
                                                                         // buy
                                                                         // and
                                                                         // sell
                                                                         // on
                                                                         // same
                                                                         // day,
                                                                         // buy
                                                                         // stock
                                                                         // at
                                                                         // jth
                                                                         // day,
                                                                         // this
                                                                         // will
                                                                         // be
                                                                         // used
                                                                         // to
                                                                         // calculate
                                                                         // the
                                                                         // profit
                                                                         // in
                                                                         // the
                                                                         // next
                                                                         // iteration
            }
        }

        return dp[k][prices.length - 1];
    }

    public int maxProfit4_oneDArray(int k, int[] prices) {
        if (k <= 0 || prices == null || prices.length <= 1)
            return 0;

        if (k >= prices.length / 2) {
            // we can do as many transactions as we want. Sell stock II case
            int maxProfit = 0;
            for (int i = 1; i < prices.length; i++) {
                // as long as there is a price gap, we gain a profit.
                maxProfit += Math.max(prices[i] - prices[i - 1], 0);
            }
            return maxProfit;
        }

        // Every trade has once sell and once buy.Buy goes first and sell goes
        // after buy.
        // So the sell result is dependent on the buy,and the buy is dependent
        // on the sell in last trade(j-1th)
        int[] buy = new int[k + 1];
        int[] sell = new int[k + 1];
        Arrays.fill(buy, Integer.MIN_VALUE);
        for (int i = 0; i < prices.length; i++) {
            for (int j = 1; j <= k; j++) {
                sell[j] = Integer.max(sell[j], buy[j] + prices[i]); // whether
                                                                    // to sell
                                                                    // at
                                                                    // prices[i]
                buy[j] = Integer.max(buy[j], sell[j - 1] - prices[i]); // whether
                                                                       // to buy
                                                                       // at
                                                                       // prices[i]
            }
            System.out.println("i=" + i);
            System.out.print("sell: ");
            ArrayUtil.printArray(sell, ", ");
            System.out.print("buy: ");
            ArrayUtil.printArray(buy, ", ");
        }

        return sell[k];
    }

    public static void main(String[] args) {
        BuyAndSellStock s = new BuyAndSellStock();

        // int[] prices = {1, 2};
        // int k = 1;
        int[] prices = { 3, 2, 6, 5, 0, 3 };
        int k = 2;
        System.out.println(s.maxProfit4_oneDArray(k, prices));
    }
}
