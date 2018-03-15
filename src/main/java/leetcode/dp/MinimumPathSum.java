package leetcode.dp;

/*
 * LEETCODE 64
 * Given a m x n grid filled with non-negative numbers, find a path from top
 * left to bottom right which minimizes the sum of all numbers along its path.
 * Note: You can only move either down or right at any point in time.
 * Example 1:
 * [[1,3,1],
 *  [1,5,1],
 *  [4,2,1]]
 * Given the above grid map, return 7. Because the path 1→3→1→1→1 minimizes the sum. 
 * 
 * Difficulty: medium
 * Similar Questions: 62(UniquePaths), 174(DungeonGame), 741
 */
public class MinimumPathSum {
    
    // Time complexity: O(mn), Space complexity: O(n)
    public int minPathSum_oneDArray(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[] dp = new int[n];
        dp[0] = grid[0][0];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j > 0) {
                    dp[j] = dp[j-1] + grid[i][j];
                }
                else if (j == 0 && i > 0) {
                    dp[j] = dp[j] + grid[i][j];
                }
                else if (i > 0 && j > 0) {
                    dp[j] = Math.min(dp[j], dp[j-1]) + grid[i][j];
                }
            }
        }
        
        return dp[n-1];
    }
    
    // Time complexity: O(mn), Space complexity: O(mn)
    public int minPathSum_twoDArray(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[][] dp = new int[m][n];
        
        dp[0][0] = grid[0][0];
        for (int i = 1; i < m; i++) {
            dp[i][0] = dp[i-1][0] + grid[i][0];
        }
        for (int j = 1; j < n; j++) {
            dp[0][j] = dp[0][j-1] + grid[0][j];
        }
        
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = Math.min(dp[i-1][j], dp[i][j-1]) + grid[i][j];
            }
        }
        return dp[m-1][n-1];
    }

}
