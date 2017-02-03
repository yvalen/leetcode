package leetcode.dp;

/*
 * Given a 2D binary matrix filled with 0's and 1's, find the largest square containing only 1's and return its area.
 * For example, given the following matrix:
 * 	1 0 1 0 0
 * 	1 0 1 1 1
 * 	1 1 1 1 1
 * 	1 0 0 1 0
 * Return 4. 
 */
public class MaximumSquare {
	public int maximalSquare(char[][] matrix) {
		if (matrix == null || matrix.length == 0) return 0;
		
		int m = matrix.length, n = matrix[0].length;
		int[][] dp = new int[m][n];
		int max = 0;
		
		// initialize the top row and left column with matrix value
		for (int i = 0; i < n; i++) {
			dp[0][i] = Character.getNumericValue(matrix[0][i]);
			if (max == 0 && dp[0][i] == 1) {
				max = 1;
			}
		}
		for (int j = 0; j < m; j++) {
			dp[j][0] = Character.getNumericValue(matrix[j][0]);
			if (max == 0 && dp[j][0] == 1) {
				max = 1;
			}
		}
		
		// dp[i][j] = min(dp[i][j-1], dp[i-1][j], dp[i-1][j-1]) + 1
		// the square formed before this point
		for (int i = 1; i < m; i++) {
			for (int j = 1; j < n; j++) {
				if (matrix[i][j] == '0') {
					dp[i][j] = 0;
				}
				else {					
					int min = Integer.min(dp[i-1][j], dp[i][j-1]);
					dp[i][j] = Integer.min(min, dp[i-1][j-1]) + 1;
					if (max < dp[i][j]) max = dp[i][j];
				}
			}
		}
		
		// find out the square which could include first row and first column
		//for (int i = 0; i < m; i++) {
		//	for (int j = 0; )
		//}
			
		return max * max;
    }
	
	public static void main(String[] args) {
		MaximumSquare s = new MaximumSquare();
		
		char[][] matrix = {{'1','1','1'},{'1','1','1'},{'1','1','1'}};
		//char[][] matrix = {{'0','0'},{'0','0'}};
		//char[][] matrix = {{'1','0','1','0', '0'},{'1','0','1','1','1'},{'1','1','1','1','1'},{'1','0','0','1','0'}};
		System.out.println(s.maximalSquare(matrix));
	}

}
