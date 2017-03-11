package leetcode.hashtable;

import java.util.HashSet;
import java.util.Set;

public class ValidSudoku {
	/*
	 * Determine if a Sudoku is valid, according to: Sudoku Puzzles - The Rules
	 *	- Each row must have the numbers 1-9 occuring just once.
	 *	- Each column must have the numbers 1-9 occuring just once.
	 *	- And the numbers 1-9 must occur just once in each of the 9 sub-boxes of the grid.
	 * The Sudoku board could be partially filled, where empty cells are filled with the character '.'.
	 * Note: A valid Sudoku board (partially filled) is not necessarily solvable. Only the filled cells need to be validated. 
	 */
	public boolean isValidSudoku(char[][] board) {
		if (board == null || board.length == 0 || board[0].length == 0) return true;
		
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				if (!isValidCell(board, i, j)) return false;
			}
		}
		
		return true;
    }
	
	/*
	 * Write a program to solve a Sudoku puzzle by filling the empty cells.
	 * Empty cells are indicated by the character '.'.
	 * You may assume that there will be only one unique solution. 
	 */
	public void solveSudoku(char[][] board) {
        
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
		
		System.out.println(s.isValidSudoku(board));
	}
}
