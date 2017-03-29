package leetcode.dp;

public class BuyAndSellStock {

	
	/**
	 * Say you have an array for which the ith element is the price of a given stock on day i.
	 * If you were only permitted to complete at most one transaction (ie, buy one and sell one share of the stock), 
	 * design an algorithm to find the maximum profit.
	 * Example 1: Input: [7, 1, 5, 3, 6, 4] Output: 5
	 * 	max. difference = 6-1 = 5 (not 7-1 = 6, as selling price needs to be larger than buying price)
	 * Example 2: Input: [7, 6, 4, 3, 1] Output: 0 
	 * 	In this case, no transaction is done, i.e. max profit = 0.
	 * http://www.sigmainfy.com/blog/leetcode-stocki.html
	 */
	public int maxProfit1(int[] prices) {
		if (prices == null || prices.length == 0) return 0;
		
		int profit = 0, buyPrice = prices[0];
		for (int i = 1; i < prices.length; i++) {
			if (prices[i] < buyPrice) {
				buyPrice = prices[i];
			}
			else if (prices[i] - buyPrice > profit) {
				profit = prices[i] - buyPrice;
			}
		}
		return profit;
    }
	
	// dp[i] denotes the max profit on ith day. We should get the max profit on (i + 1)th day from
	// - profit from previous days, or
	// - profit gained on this day (current price - minimum price before)
	// And only after this, we can update the minimum price.
	public int maxProfit1_dp(int[] prices) {
		if (prices == null || prices.length == 0) return 0;
		
		int maxProfit = 0, minPrice = prices[0];
		for (int i = 1; i < prices.length; i++) {
			maxProfit = Math.max(maxProfit, prices[i] - minPrice);
			minPrice = Math.min(minPrice, prices[i]);
		}
		return maxProfit;
    }
	
	/**
	 * Say you have an array for which the ith element is the price of a given stock on day i.
	 * Design an algorithm to find the maximum profit. You may complete at most two transactions.
	 * You may not engage in multiple transactions at the same time 
	 * (ie, you must sell the stock before you buy again).
	 */
	public int maxProfit3(int[] prices) {
		if (prices == null || prices.length == 0) return 0;
		
		int profit = 0;
		for (int i = 1; i < prices.length; i++) {
			profit += Math.max(prices[i]-prices[i-1], 0);
		}
		return profit;
    }
	
	/**
	 * Say you have an array for which the ith element is the price of a given stock on day i.
	 * Design an algorithm to find the maximum profit. You may complete at most k transactions.
	 * Note: You may not engage in multiple transactions at the same time 
	 * (ie, you must sell the stock before you buy again).
	 */
	// dp[k][i]: max profit up to day i (included) with at most k transactions (global optimal objective)
	// g[k][i]: max profit up to day i (included) with at most k transactions AND we sell at day i 
	// (local optimal objective, why local? think about it!)
	// DP recursive formula
	// 1. dp[k][i] = max ( dp[k][i-1], g[k][i] )
	// 2. g[k][i] = max_{j=0,...,i-1} (p[i] - p[j] + f[k-1][j-1])
	// This means if we don't sell at day i, then dp[k][i] is just dp[k][i-1]; 
	// otherwise dp[k][i] will be the max profit that we can achieve if we sell at day i
	// Since we will sell at day i anyway, that means we need to buy at a certain previous day, 
	// for a particular j, the best we can have is p[i] - p[j] + f[k-1][j-1].
	// dp[0][j] = 0 - 0 transaction makes 0 profit
	// dp[i][0] = 0 - cannot make any transaction if there is only one price
	public int maxProfit4_twoDArray(int k, int[] prices) {
		if (k <= 0 || prices == null || prices.length <= 1) return 0;
		
		if (k >= prices.length / 2) {
			//  we can do as many transactions as we want. Sell stock II case
			int maxProfit = 0;
			for (int i = 1; i < prices.length; i++) {
				// as long as there is a price gap, we gain a profit.
				maxProfit += Math.max(prices[i]-prices[i-1], 0);
			}
			return maxProfit;
		}

		int[][] dp = new int[k+1][prices.length];
		for (int i = 1; i <=k; i ++) {
			int localMax = dp[i-1][0] - prices[0];
			for (int j = 1; j < prices.length; j++) {
				// dp[i][j-1] - no transaction at jth day
				// prices[j] + localMax -> prices[j]-prices[jj]+dp[i-1][jj] for (jj in 0...j-1)
				dp[i][j] = Integer.max(dp[i][j-1], prices[j] + localMax);
				// update the max profit of doing i-1 transaction at j-1 day and buy the stock at prices[j], this will be used in the next iteration
				//localMax = Math.max(localMax, dp[i-1][j] - prices[j]); // allow buy and sell on same day
				localMax = Math.max(localMax, dp[i-1][j-1] - prices[j]); // not allow buy and sell on same day
			}
		}
		
		return dp[k][prices.length-1];
    }
	
	public int maxProfit4_oneDArray(int k, int[] prices) {
		if (k <= 0 || prices == null || prices.length <= 1) return 0;
		
		if (k >= prices.length / 2) {
			//  we can do as many transactions as we want. Sell stock II case
			int maxProfit = 0;
			for (int i = 1; i < prices.length; i++) {
				// as long as there is a price gap, we gain a profit.
				maxProfit += Math.max(prices[i]-prices[i-1], 0);
			}
			return maxProfit;
		}

		// balance - the balance with at most j transactions with item 0 to i
	    // profit - the profit with at most j transactions with item 0 to i
		int[] balance = new int[k+1];
		int[] profit = new int[k+1];

		for (int i = 0; i < prices.length; i++) {
			for (int j = 1; j <= k; j++) {
				balance[j] = Integer.max(balance[j], profit[j-1] - prices[i]); // whether to buy at prices[i]
				profit[j] = Integer.max(profit[j], balance[j]+prices[i]);   // whether to sell at prices[i]
			}
		}
		
		return profit[k];
    }
	
	
	public static void main(String[] args) {
		BuyAndSellStock s = new BuyAndSellStock();
		
		//int[] prices = {1, 2};
		//int k = 1;
		int[] prices = {3, 2,6,5,0,3};
		int k = 2;
		System.out.println(s.maxProfit4_oneDArray(k, prices));
	}
}

