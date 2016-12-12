package leetcode.matrix;

public class SetZeroes {
	/**
	 * Given a m x n matrix, if an element is 0, set its entire row and column to 0. Do it in place. 
	 */
	public void setZeroes(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        
        boolean firstRowZero = false, firstColZero = false;
        // check if the first rpw has zero value
        for (int i = 0; i < n; i++) {
        	if (matrix[0][i] == 0) firstRowZero = true;
        }
        
        // check if the first column has zero value
        for (int i = 0; i < m; i++) {
        	if (matrix[i][0] == 0) firstColZero = true;
        }
        
        // check each cell of the rest of the matrix
        // set the corresponding first row/col element to zero if it is zero
        for (int i = 1; i < m; i++) {
        	for (int j = 1; j < n; j++) {
        		if (matrix[i][j] == 0) {
        			matrix[i][0] = 0;
        			matrix[0][j] = 0;
        		}
        	}
        }
        
        // go through the cells again, set it to zero
        // the corresponding first row/col element is zero
        for (int i = 1; i < m; i++) {
        	for (int j = 1; j < n; j++) {
        		if (matrix[i][0] == 0 || matrix[0][j] == 0) {
        			matrix[i][j] = 0;
        		}
        	}
        }
        
        // set the first row and column based on the flags
        if (firstRowZero) {
        	for (int i = 0; i < n; i ++) {
        		matrix[0][i] = 0;
        	}
        }
        if (firstColZero) {
        	for (int i = 0; i < m; i ++) {
        		matrix[i][0] = 0;
        	}
        }
    }

}
