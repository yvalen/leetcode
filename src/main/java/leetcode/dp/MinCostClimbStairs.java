package leetcode.dp;

/*
 * LEETCODE 746
 * On a staircase, the i-th step has some non-negative cost cost[i] assigned (0 indexed).
 * Once you pay the cost, you can either climb one or two steps. You need to find minimum 
 * cost to reach the top of the floor, and you can either start from the step with index 0, 
 * or the step with index 1.
 * Example 1:
 * Input: cost = [10, 15, 20]
 * Output: 15
 * Explanation: Cheapest is start on cost[1], pay that cost and go to the top.
 * Example 2:
 * Input: cost = [1, 100, 1, 1, 1, 100, 1, 1, 100, 1]
 * Output: 6
 * Explanation: Cheapest is start on cost[0], and only step on 1s, skipping cost[3].
 * Note:
 * - cost will have a length in the range [2, 1000].
 * - Every cost[i] will be an integer in the range [0, 999].
 * 
 * Company: Amazon
 * Difficulty: easy
 * Similar Questions: 70(ClimbStairs)
 */
public class MinCostClimbStairs {
    public int minCostClimbingStairs(int[] cost) {
        int n = cost.length;
        if (n <= 0) return 0;
        if (n == 2) return cost[0];
        
        /*
        int[] dp = new int[n+1];
        dp[0] = 0;
        dp[1] = 0;
        dp[2] = Math.min(cost[0], cost[1]);
        for (int i = 3; i <= n; i++) {
            dp[i] = Math.min(dp[i-2]+cost[i-2], dp[i-1]+cost[i-1]);
        }
        return dp[n];
        */
        
        int c1 = 0, c2 = 0, minCost = 0;
        for (int i = 2; i <= n; i++) {
            minCost = Math.min(c1+cost[i-2], c2+cost[i-1]);
            c1 = c2;
            c2 = minCost;
        }
        return minCost;
       
    }   
    
    public static void main(String[] args) {
        MinCostClimbStairs mc = new MinCostClimbStairs();
        int[] cost = {10, 15, 20};
        //int[] cost = {1, 100, 1, 1, 1, 100, 1, 1, 100, 1};
        //int[] cost = {1, 0, 0, 1};
        System.out.println(mc.minCostClimbingStairs(cost));
    }

}
