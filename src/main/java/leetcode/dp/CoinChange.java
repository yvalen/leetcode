package leetcode.dp;

/*
 * You are given coins of different denominations and a total amount of money amount. 
 * Write a function to compute the fewest number of coins that you need to make up that amount. 
 * If that amount of money cannot be made up by any combination of the coins, return -1.
 * Example 1: coins = [1, 2, 5], amount = 11 return 3 (11 = 5 + 5 + 1)
 * Example 2: coins = [2], amount = 3 return -1.
 * Note: You may assume that you have an infinite number of each kind of coin. 
 */
public class CoinChange {
    public int coinChange(int[] coins, int amount) {
        if (amount <= 0 || coins == null || coins.length == 0)
            return 0;

        int[] dp = new int[amount + 1];
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            int min = Integer.MAX_VALUE;
            for (int j = 0; j < coins.length; j++) {
                if (coins[j] <= i) {
                    min = Integer.min(min, dp[i - coins[j]]);
                }
            }
            dp[i] = (min == Integer.MAX_VALUE) ? Integer.MAX_VALUE : min + 1;
        }

        return dp[amount] == Integer.MAX_VALUE ? -1 : dp[amount];
    }

    public static void main(String[] args) {
        CoinChange c = new CoinChange();

        int[] coins = { 2 };
        int amount = 3;
        System.out.println(c.coinChange(coins, amount));

    }
}
