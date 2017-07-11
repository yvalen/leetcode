package leetcode.matrix;

public class SetZeroes {
	/**
	 * Given a m x n matrix, if an element is 0, set its entire row and column to 0. Do it in place. 
	 * Follow up:
	 * - Did you use extra space?
	 * - A straight forward solution using O(mn) space is probably a bad idea.
	 * - A simple improvement uses O(m + n) space, but still not the best solution.
	 * - Could you devise a constant space solution?
	 * 
	 * Company: Microsoft, Amazon
	 * Difficulty: medium
	 */
	public void setZeroes(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        
        boolean firstRowZero = false, firstColZero = false;
        // check if the first row has zero value
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
	
	// store states of each row in the first of that row, and store states of each column in the first of that column. 
	// Because the state of row0 and the state of column0 would occupy the same cell matrix[0]0], use it for the state 
	// of row0, and use another variable "col0" for column0. In the first phase, use matrix elements to set states in 
	// a top-down way. In the second phase, use states to set matrix elements in a bottom-up way.
	public void setZeroes_twoPass(int[][] matrix) {
		if (matrix == null || matrix.length == 0) return;
		
		boolean col0 = false;
		int m = matrix.length, n = matrix[0].length;
		
		for (int i = 0; i < m; i++) {
			if (matrix[i][0] == 0) col0 = true;
			for (int j = 1; j < n; j++) {
				if (matrix[i][j] == 0) {
					matrix[i][0] = 0;
					matrix[0][j] = 0;
				}
			}
		}
		
		for (int i = m-1; i >= 0; i--) {
			for (int j = n -1; j > 0; j--) {
				if (matrix[i][0] == 0 || matrix[0][j] == 0) {
					matrix[i][j] = 0;
				}
			}
			if (col0) matrix[i][0] = 0;
		}
	}

}
