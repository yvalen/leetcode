package leetcode.dp;

/*
 * LEETCODE 375
 * We are playing the Guess Game. The game is as follows: I pick a number from 1 to n. 
 * You have to guess which number I picked. Every time you guess wrong, I'll tell you 
 * whether the number I picked is higher or lower. However, when you guess a particular 
 * number x, and you guess wrong, you pay $x. You win the game when you guess the number I picked.
 * Example: n = 10, I pick 8.
 * First round:  You guess 5, I tell you that it's higher. You pay $5.
 * Second round: You guess 7, I tell you that it's higher. You pay $7.
 * Third round:  You guess 9, I tell you that it's lower. You pay $9.
 * Game over. 8 is the number I picked. You end up paying $5 + $7 + $9 = $21.
 * Given a particular n ≥ 1, find out how much money you need to have to guarantee a win.
 * 
 * Company: Google
 * Difficulty: medium
 */
public class GuessNumberHigherOrLowerII {
    /*
     * range -> best strategy cost suppose the range is [start, end], the
     * strategy here is to iterate through all number possible and select it as
     * the starting point. For any k between start and end, the worst cost for
     * this is: k+max(DP( start, k-1 ), DP(k+1, end )). The goal is minimize the
     * cost, so you need the minimum one among all those k between start and end
     */
    public int getMoneyAmount(int n) {
        return getMoneyAmount(1, n, new int[n + 1][n + 1]);
    }

    private int getMoneyAmount(int start, int end, int[][] dp) {
        if (start >= end)
            return 0;
        if (dp[start][end] != 0)
            return dp[start][end];

        int minAmount = Integer.MAX_VALUE;
        for (int i = start; i <= end; i++) {
            int amount = Math.max(i + getMoneyAmount(start, i - 1, dp), i + getMoneyAmount(i + 1, end, dp));
            minAmount = Math.min(minAmount, amount);
        }
        dp[start][end] = minAmount;
        return minAmount;
    }

}
