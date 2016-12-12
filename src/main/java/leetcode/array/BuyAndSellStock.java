package leetcode.array;

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
	
	/**
	 * Say you have an array for which the ith element is the price of a given stock on day i.
	 * Design an algorithm to find the maximum profit. You may complete as many transactions 
	 * as you like (ie, buy one and sell one share of the stock multiple times). 
	 * However, you may not engage in multiple transactions at the same time 
	 * (ie, you must sell the stock before you buy again).
	 * http://www.sigmainfy.com/blog/leetcode-best-time-to-buy-and-sell-stock-ii.html
	 */
	public int maxProfit2(int[] prices) {
		if (prices == null || prices.length == 0) return 0;
		
		int profit = 0;
		for (int i = 1; i < prices.length; i++) {
			profit += Math.max(prices[i]-prices[i-1], 0);
		}
		return profit;
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
}

