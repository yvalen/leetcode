package leetcode.greedy;

/**
 * Say you have an array for which the ith element is the price of a given stock on day i.
 * Design an algorithm to find the maximum profit. You may complete as many transactions 
 * as you like (ie, buy one and sell one share of the stock multiple times). 
 * However, you may not engage in multiple transactions at the same time 
 * (ie, you must sell the stock before you buy again).
 * http://www.sigmainfy.com/blog/leetcode-best-time-to-buy-and-sell-stock-ii.html
 * https://leetcode.com/articles/best-time-buy-and-sell-stock-ii/
 */
public class BuyAndSellStockII {
	public int maxProfit(int[] prices) {
		if (prices == null || prices.length == 0) return 0;
		
		int maxProfit = 0;
		for (int i = 1; i < prices.length; i++) {
			if (prices[i] > prices[i-1]) maxProfit += prices[i] - prices[i-1];
		}
		return maxProfit;
    }
	
	// The key point is we need to consider every peak immediately following a valley to maximize the profit. 
	// In case we skip one of the peaks (trying to obtain more profit), we will end up losing the profit over 
	// one of the transactions leading to an overall lesser profit.
	public int maxProfit_peakValley(int[] prices) {
		if (prices == null || prices.length == 0) return 0;
		
		int maxProfit = 0, i = 0;
		int valley = prices[0], peak = prices[0];
		while (i < prices.length - 1) {
			while (i < prices.length-1 && prices[i] >= prices[i+1]) i++;
			valley = prices[i];
			
			while (i < prices.length-1 && prices[i] < prices[i+1]) i++;
			peak = prices[i];
			
			maxProfit += peak - valley;
		}
		return maxProfit;
    }
}
