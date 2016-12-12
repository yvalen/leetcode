package leetcode.matrix;

public class SearchMatrix {
	/** 
	 * Write an efficient algorithm that searches for a value in an m x n matrix. 
	 * This matrix has the following properties: integers in each row are sorted from left to right.
	 * The first integer of each row is greater than the last integer of the previous row.
	 * For example, consider the following matrix:
	 * 	[
	 * 		[1,   3,  5,  7],
	 * 		[10, 11, 16, 20],
	 * 		[23, 30, 34, 50]
	 * 	]
	 * Given target = 3, return true.
	 */
	public boolean searchMatrix(int[][] matrix, int target) {
		int rows = matrix.length, cols = matrix[0].length;
		
        // locate the row by doing binary search on the first row
		int i = 0, j = rows - 1;
		while (i <= j) {
			int mid = (i + j) / 2;
			if (matrix[mid][0] == target) return true;
			
			if (target > matrix[mid][0]) i = mid + 1;
			else j = mid -1;
		}
		
		// j is the row that contains that could contain the element
		// doing a binary search on this row to find the element
		int row = (j < 0) ? 0 : j;
		i = 0; 
		j = cols -1;
		while (i <= j) {
			int mid = (i + j) / 2;
			if (matrix[row][mid] == target) return true;
			
			if (target > matrix[row][mid]) i = mid + 1;
			else j = mid -1;
		}
		
		return false;
    }
	
	
	
	/**
	 * Write an efficient algorithm that searches for a value in an m x n matrix. 
	 * This matrix has the following properties:
	 * 	- Integers in each row are sorted in ascending from left to right.
	 * 	- Integers in each column are sorted in ascending from top to bottom.
	 * For example, consider the following matrix:
	 * [
	 * 	[1,   4,  7, 11, 15],
	 * 	[2,   5,  8, 12, 19],
	 * 	[3,   6,  9, 16, 22],
	 * 	[10, 13, 14, 17, 24],
	 * 	[18, 21, 23, 26, 30]
	 * ]
	 * Given target = 5, return true.
	 * Given target = 20, return false.
	 */
	public boolean searchMatrixII(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0) return false;
        
		return searchMatrixHelper(matrix, target, 0, matrix.length - 1, 0, matrix[0].length - 1);
    }
	
	private boolean searchMatrixHelper(int[][] matrix, int target, int rowStart, int rowEnd, int colStart, int colEnd) {
		if (rowStart > rowEnd || colStart > colEnd) return false;
		
        int rowMid = (rowStart + rowEnd) / 2;
        int colMid = (colStart + colEnd) / 2;
    
        if (target == matrix[rowMid][colMid]) {
        	return true;
        }
        
        if (target > matrix[rowMid][colMid]) {
        	return (searchMatrixHelper(matrix, target, rowMid+1, rowEnd, colStart, colMid) ||
        			searchMatrixHelper(matrix, target, rowStart, rowEnd, colMid+1, colEnd));
        }
        else {
        	return (searchMatrixHelper(matrix, target, rowStart, rowMid-1, colStart, colEnd) ||
        			searchMatrixHelper(matrix, target, rowMid, rowEnd, colStart, colMid-1) );
        }
    }
	
	
	public static void main(String[] args) {
		SearchMatrix s = new SearchMatrix();
		int[][] matrix = {{1}, {3}};
		
		boolean found = s.searchMatrix(matrix , 2);
		System.out.println(found);
	}
}
