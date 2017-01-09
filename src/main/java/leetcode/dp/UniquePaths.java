package leetcode.dp;

public class UniquePaths {
	/**
	 * A robot is located at the top-left corner of a m x n grid. The robot can only move either 
	 * down or right at any point in time. The robot is trying to reach the bottom-right corner 
	 * of the grid. How many possible unique paths are there?
	 */
	
	public int uniquePaths_topDown(int m, int n) {
		if (m <= 0 || n <= 0) return 0;
		int[][] paths = new int[m][n];
		return uniquePathsHelper(0, 0, m, n, paths);
    }
	
	private int uniquePathsHelper(int startM, int startN, int endM, int endN, int[][] paths) {
		if (paths[startM][startN] != 0 ) return paths[startM][startN];
		
		if (startM == endM && startN == endN) {
			paths[startM][startN] = 1;
		}
		else if (startM == endM) {
			paths[startM][startN] = uniquePathsHelper(startM, startN+1, endM, endN, paths);
		}
		else if (startN == endN) {
			paths[startM][startN] = uniquePathsHelper(startM+1, startN, endM, endN, paths);
		}
		else {
			paths[startM][startN] = uniquePathsHelper(startM, startN+1, endM, endN, paths) +
					uniquePathsHelper(startM+1, startN, endM, endN, paths);
		}
		
		return paths[startM][startN];
    }
	
	public int uniquePaths_bottomUp(int m, int n) {
		if (m <= 0 || n <= 0) return 0;
		
		// stores the unique paths to get to point m,n
		int[][] paths = new int[m][n];
		
		// populate the edges 
		for (int i = 0; i < m; i++) {
			paths[i][0] = 1;
		}
		for (int j = 0; j < n; j++) {
			paths[0][j] = 1;
		}
		
		for (int i = 1; i < m; i++) {
			for (int j = 1; j < n; j++) {
				paths[i][j] = paths[i-1][j] + paths[i][j-1];
			}
		}
		
		return paths[m-1][n-1];
    }
	
	
	
	/**
	 * Follow up for "Unique Paths"
	 * Now consider if some obstacles are added to the grids. How many unique paths would there be?
	 * An obstacle and empty space is marked as 1 and 0 respectively in the grid.
	 */
	public int uniquePathsWithObstacles(int[][] obstacleGrid) {
		int m = obstacleGrid.length, n = obstacleGrid[0].length;
		
		if (m == 0 || n == 0) return 0;
		
		int[][] paths = new int[m][n];
		
		for (int i = 0; i < m && obstacleGrid[i][0] == 0; i++) {
			paths[i][0] = 1;
		}
		
		for (int j = 0; j < n && obstacleGrid[0][j] == 0; j++) {
			paths[0][j] = 1;
		}
		
		for (int i = 1; i < m; i++) {
			for (int j = 1; j < n; j++) {
				if (obstacleGrid[i][j] == 0) {
					paths[i][j] = paths[i-1][j] + paths[i][j-1];
				}
			}
			
		}
		
		return paths[m-1][n-1];
    }
	
	
	/**
	 * Given a m x n grid filled with non-negative numbers, find a path from top left 
	 * to bottom right which minimizes the sum of all numbers along its path.
	 * Note: You can only move either down or right at any point in time.
	 */
	public int minPathSum(int[][] grid) {
		if (grid.length == 0 || grid[0].length == 0) return 0;
		
        int m = grid.length, n = grid[0].length;
        int[][] sum = new int[m][n];
        
        sum[0][0] = grid[0][0];
        for (int i = 1; i < m; i++) {
        	sum[i][0] = grid[i][0] + sum[i-1][0];
        }
        
        for (int j = 1; j < n; j++) {
        	sum[0][j] = grid[0][j] + sum[0][j-1];
        }
        
        for (int i = 1; i < m; i++) {
        	for (int j = 1; j < n; j++) {
        		sum[i][j] = Math.min(sum[i-1][j], sum[i][j-1]) + grid[i][j];
        	}
        }
        
        return sum[m-1][n-1];
    }
	
	
	
	public static void main(String[] args) {
		UniquePaths u = new UniquePaths();
		/*
		int m = 1, n = 2;
		int paths = u.uniquePaths_bottomUp(m, n);
		System.out.println(paths);
		*/
		
		int[][] grid = {{1,2},{1,1}};
		int s = u.minPathSum(grid);
		System.out.println(s);
	}
}
