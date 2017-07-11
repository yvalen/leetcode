package leetcode.segmenttree;

/*
 * Given a 2D matrix matrix, find the sum of the elements inside the rectangle defined by its 
 * upper left corner (row1, col1) and lower right corner (row2, col2).
 * Example:
 * Given matrix = [
 * 	[3, 0, 1, 4, 2],
 * 	[5, 6, 3, 2, 1],
 * 	[1, 2, 0, 1, 5],
 * 	[4, 1, 0, 1, 7],
 * 	[1, 0, 3, 0, 5]
 * ]
 * sumRegion(2, 1, 4, 3) -> 8
 * update(3, 2, 2)
 * sumRegion(2, 1, 4, 3) -> 10
 * Note:
 * - The matrix is only modifiable by the update function
 * - You may assume the number of calls to update and sumRegion function is distributed evenly.
 * - You may assume that row1 ≤ row2 and col1 ≤ col2.
 * 
 * Company: Google
 * Difficulty: hard
 */
public class RangeSum2DMutable {
	private int[][] BIT;
	private int[][] matrix;
	private int m ,n;
	
	public RangeSum2DMutable(int[][] matrix) {
        this.m = matrix.length;
        if (m == 0) return; // need to check for empty matrix
        this.n = matrix[0].length;
        
        if (m == 0 || n == 0) return;
        this.matrix = new int[m][n];
        this.BIT = new int[m+1][n+1];
        for (int i = 0; i < m; i++) {
        	for (int j = 0; j < n; j++) {
        		update(i, j, matrix[i][j]);
        	}
        }
    }
    
    public void update(int row, int col, int val) {
    	if (m == 0 || n == 0) return;
    	int delta = val - matrix[row][col];
    	this.matrix[row][col] = val;
    	for (int i = row + 1; i <= m; i += (i & -i)) {
    		for (int j = col + 1; j <= n; j += (j & -j)) {
    			BIT[i][j] += delta;
    		}
    	}
    }
    
    public int sumRegion(int row1, int col1, int row2, int col2) {
    	if (m == 0 || n == 0) return 0;
        return getSum(row2, col2) - getSum(row1-1, col2) - getSum(row2, col1-1) + getSum(row1-1, col1-1);
    }
    
    private int getSum(int row, int col) {
    	int sum = 0;
    	for (int i = row + 1; i > 0; i -= (i & -i)) { // need to check against greater than 0
    		for (int j = col + 1; j > 0; j -= (j & -j)) {
    			sum += BIT[i][j];
    		}
    	}
    	return sum;
    }
}
