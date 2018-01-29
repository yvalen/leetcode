package leetcode.dp;


/*
 * LEETCODE 309
 * Say you have an array for which the ith element is the price of a given stock on day i.
 * Design an algorithm to find the maximum profit. You may complete as many transactions 
 * as you like (ie, buy one and sell one share of the stock multiple times) with the 
 * following restrictions:
 * - You may not engage in multiple transactions at the same time (ie, you must sell the 
 * stock before you buy again).
 * - After you sell your stock, you cannot buy stock on next day. (ie, cool down 1 day)
 * Example: 
 * prices = [1, 2, 3, 0, 2]
 * maxProfit = 3
 * transactions = [buy, sell, cool down, buy, sell]
 * 
 * Company: Google
 * Difficulty: medium
 * Similar Question: 121(BuyAndSellStock I), 122(BuyAndSellStockII)
 */
public class BuyAndSellStockWithCoolDown {
    // 1. Define States
    // To represent the decision at index i:
    // - buy[i]: Max profit till index i. The series of transaction is ending
    // with a buy.
    // - sell[i]: Max profit till index i. The series of transaction is ending
    // with a sell.
    // To clarify:
    // Till index i, the buy / sell action must happen and must be the last
    // action. It may not happen at index i.
    // It may happen at i - 1, i - 2, ... 0. In the end n - 1, return sell[n -
    // 1]. Apparently we cannot finally end up with a buy.
    // In that case, we would rather take a rest at n - 1. For special case no
    // transaction at all, classify it as sell[i],
    // so that in the end, we can still return sell[n - 1].
    //
    // 2. Define Recursion
    // - buy[i]: To make a decision whether to buy at i, we either take a rest,
    // by just using the old decision at i - 1, or sell at/before i - 2,
    // then buy at i, We cannot sell at i - 1, then buy at i, because of cool
    // down.
    // - sell[i]: To make a decision whether to sell at i, we either take a
    // rest, by just using the old decision at i - 1, or buy at/before i - 1,
    // then sell at i.
    // So we get the following formula:
    // buy[i] = Math.max(buy[i - 1], sell[i - 2] - prices[i]);
    // sell[i] = Math.max(sell[i - 1], buy[i - 1] + prices[i]);
    //
    // 3. Optimize to O(1) Space
    // DP solution only depending on i - 1 and i - 2 can be optimized using O(1)
    // space.
    // Let b2, b1, b0 represent buy[i - 2], buy[i - 1], buy[i]
    // Let s2, s1, s0 represent sell[i - 2], sell[i - 1], sell[i]
    // Then arrays turn into Fibonacci like recursion:
    // b0 = Math.max(b1, s2 - prices[i]);
    // s0 = Math.max(s1, b1 + prices[i]);

    public int maxProfit_withArray(int[] prices) {
        if (prices == null || prices.length <= 1)
            return 0;

        int[] buy = new int[prices.length];
        int[] sell = new int[prices.length];

        // initial state i = 0
        // We can buy. The max profit at i = 0 ending with a buy is -prices[0].
        // We cannot sell. The max profit at i = 0 ending with a sell is 0.
        buy[0] = -prices[0];
        buy[1] = Math.max(buy[0], -prices[1]);
        sell[0] = 0;
        sell[1] = Math.max(0, buy[0] + prices[1]);

        for (int i = 2; i < prices.length; i++) {
            buy[i] = Math.max(buy[i - 1], sell[i - 2] - prices[i]);
            sell[i] = Math.max(sell[i - 1], buy[i - 1] + prices[i]);
        }

        return sell[prices.length - 1];
    }

    public int maxProfit(int[] prices) {
        if (prices == null || prices.length <= 1)
            return 0;
        
        int b0 = Integer.MIN_VALUE, b1 = Integer.MIN_VALUE;
        int s0 = 0, s1 = 0, s2 = 0;
        for (int i = 0; i < prices.length; i++) {
            s0 = Math.max(s1, b1+prices[i]);
            b0 = Math.max(b1, s2-prices[i]);
            s2 = s1;
            s1 = s0;
            b1 = b0;
        }
        return s0;
    }

    public static void main(String[] args) {
        
    }
}
