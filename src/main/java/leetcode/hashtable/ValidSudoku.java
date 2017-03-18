package leetcode.hashtable;

import java.util.HashSet;
import java.util.Set;

/*
 * Determine if a Sudoku is valid, according to: Sudoku Puzzles - The Rules
 *	- Each row must have the numbers 1-9 occuring just once.
 *	- Each column must have the numbers 1-9 occuring just once.
 *	- And the numbers 1-9 must occur just once in each of the 9 sub-boxes of the grid.
 * The Sudoku board could be partially filled, where empty cells are filled with the character '.'.
 * Note: A valid Sudoku board (partially filled) is not necessarily solvable. Only the filled cells need to be validated. 
 */
public class ValidSudoku {
	
	public boolean isValidSudoku(char[][] board) {
		if (board == null || board.length == 0 || board[0].length == 0) return true;
		
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				if (!isValidCell(board, i, j)) return false;
			}
		}
		
		return true;
    }
	
	
	private boolean isValidCell(char[][] board, int x, int y) {
		char cellValue = board[x][y];
		if (cellValue == '.') return true;
		if (!Character.isDigit(cellValue) ||
				Character.getNumericValue(cellValue) < 1 ||
				Character.getNumericValue(cellValue) > 9) {
			return false;
		}
		
		int m = board.length, n = board[0].length;
		
		// validate column
		for (int i = 0; i < m; i++) {
			if (board[i][y] == cellValue && i != x) {
				return false;
			}
		}
		
		// validate row
		for (int j = 0; j < n; j++) {
			if (board[x][j] == cellValue && j != y) {
				return false;
			}
		}
		
		// validate sub-box
		int startRow = (x / 3) * 3, startCol = (y / 3) * 3, endRow = startRow + 2, endCol = startCol + 2;
		for (int i = startRow; i <= endRow; i++) {
			for (int j = startCol; j < endCol; j++) {
				if (board[i][j] == cellValue && i != x && j != y) {
					return false;
				}
			}
		}
		
		return true;
	}
	
	
	public boolean isValidSudoku_withSet(char[][] board) {
		if (board == null || board.length == 0 || board[0].length == 0) return true;
		
		for (int i = 0; i < board.length; i++) {
			Set<Character> rowSet = new HashSet<>();
			Set<Character> colSet = new HashSet<>();
			Set<Character> blockSet = new HashSet<>();
			for (int j = 0; j < board[0].length; j++) {
				// check column
				if (board[i][j] != '.' && !colSet.add(board[i][j])) return false;
				
				// check row
				if (board[j][i] != '.' && !rowSet.add(board[j][i])) return false;
				
				// check block
				// For a block traversal, it goes the following way.
				// 0,0, 0,1, 0,2; < --- 3 Horizontal Steps followed by 1 Vertical step to next level.
				// 1,0, 1,1, 1,2; < --- 3 Horizontal Steps followed by 1 Vertical step to next level.
				// 2,0, 2,1, 2,2; < --- 3 Horizontal Steps.
				// And so on...
				// j iterates from 0 to 9, we need to stop after 3 horizontal steps and go down 1 step vertical.
				// Use % for horizontal traversal because % increments by 1 for each j : 0%3 = 0 , 1%3 = 1, 2%3 = 2, and resets back. 
				// So this covers horizontal traversal for each block by 3 steps.
				// Use / for vertical traversal because / increments by 1 after every 3 j: 0/3 = 0; 1/3 = 0; 2/3 =0; 3/3 = 1.
				// For a given block, you can traverse the whole block using just j.
				// But because j is just 0 to 9, it will stay only in the first block. 
				// To increment block, use i. To move horizontally to next block, use % again : ColIndex = 3 * i%3 
				// (Multiply by 3 so that the next block is after 3 columns. i.e. 0,0 is start of first block, second block is 0,3 (not 0,1);
				// Similarly, to move to next block vertically, use / and multiply by 3 as explained above.
				int x = 3 * (i / 3) + (j / 3);
				int y = 3 * (i % 3) + (j % 3);
				System.out.println("i=" + i + " j="+ j + " x=" + x + " y=" + y);
				if (board[x][y] != '.' && !blockSet.add(board[x][y])) return false;
			}
		}
		
		return true;
    }
	
	
	public static void main(String[] args) {
		ValidSudoku s = new ValidSudoku();
		
		/*
		char[][] board = {
				{'.', '8', '7', '6', '5', '4', '3', '2', '1'},
				{'2', '.', '.', '.', '.', '.', '.', '.', '.'},
				{'3', '.', '.', '.', '.', '.', '.', '.', '.'},
				{'4', '.', '.', '.', '.', '.', '.', '.', '.'},
				{'5', '.', '.', '.', '.', '.', '.', '.', '.'},
				{'6', '.', '.', '.', '.', '.', '.', '.', '.'},
				{'7', '.', '.', '.', '.', '.', '.', '.', '.'},
				{'8', '.', '.', '.', '.', '.', '.', '.', '.'},
				{'9', '.', '.', '.', '.', '.', '.', '.', '.'},
				
		};
		
		char[][] board = {
				{'.', '.', '4', '6', '5', '4', '6', '3', '.'},
				{'.', '.', '.', '.', '.', '.', '.', '.', '.'},
				{'5', '.', '.', '.', '.', '.', '.', '9', '.'},
				{'.', '.', '.', '5', '6', '.', '.', '.', '.'},
				{'4', '.', '3', '.', '.', '.', '.', '.', '1'},
				{'.', '.', '.', '7', '.', '.', '.', '.', '.'},
				{'.', '.', '.', '5', '.', '.', '.', '.', '.'},
				{'.', '.', '.', '.', '.', '.', '.', '.', '.'},
				{'.', '.', '.', '.', '.', '.', '.', '.', '.'},
				
		};
		*/
		
		char[][] board = {
				{'.', '.', '.', '.', '.', '.', '.', '.', '2'},
				{'.', '.', '.', '.', '.', '.', '6', '.', '.'},
				{'.', '.', '1', '4', '.', '.', '8', '.', '.'},
				{'.', '.', '.', '.', '.', '.', '.', '.', '.'},
				{'.', '.', '.', '.', '.', '.', '.', '.', '.'},
				{'.', '.', '.', '.', '3', '.', '.', '.', '.'},
				{'5', '.', '8', '6', '.', '.', '.', '.', '.'},
				{'.', '9', '.', '.', '.', '.', '4', '.', '.'},
				{'.', '.', '.', '.', '5', '.', '.', '.', '.'},
				
		};
		
		System.out.println(s.isValidSudoku_withSet(board));
	}
}
