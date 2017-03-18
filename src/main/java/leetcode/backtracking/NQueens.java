package leetcode.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

public class NQueens {
	/*
	 * The n-queens puzzle is the problem of placing n queens on an n×n chess board such that no two queens 
	 * attack each other. Thus a solution requires that no two queens share the same row, column, or diagonal. 
	 * Given an integer n, return all distinct solutions to the n-queens puzzle. Each solution contains a 
	 * distinct board configuration of the n-queens' placement, where 'Q' and '.' 
	 * indicate a queen and an empty space respectively.
	 * For example, there exist two distinct solutions to the 4-queens puzzle:
	 * [
	 * 	[".Q..",  // Solution 1
	 * 	 "...Q",
	 * 	 "Q...",
	 * 	 "..Q."],
	 * 	["..Q.",  // Solution 2
	 * 	 "Q...",
	 * 	 "...Q",
	 * 	 ".Q.."]
	 * ]
	 */
	public List<List<String>> solveNQueens_withTwoDArray(int n) {
		List<List<String>> result = new ArrayList<>();
		char[][] board = new char[n][n];
		for (int i = 0; i < board.length; i++) {
			Arrays.fill(board[i], '.');
		}
		twoDHelper(board, result, 0);
		return result;
    }
	

	private void twoDHelper(char[][] board, List<List<String>> result, int row) {
		if (row == board.length) {
			result.add(boardToList(board));
			return;
		}
		
		for (int i = 0; i < board.length; i++) {
			if (canPlace(board, row, i)) {
				board[row][i] = 'Q';
				twoDHelper(board, result, row+1);
				board[row][i] = '.';
			}
		}
	}
	
	private boolean canPlace(char[][] board, int row, int col) {
		// check the same column of previous rows
		for (int i = 0; i < row; i++) {
			if (board[i][col] == 'Q') return false;
			
		}

		// check left upper diagonal
		for (int i=row-1,j=col-1; i>=0 && j>=0; i--, j--) {
			if (board[i][j] == 'Q') return false;
		}
		
		// check right upper diagonal
		for (int i=row-1,j=col+1; i>=0 && j < board.length; i--, j++) {
			if (board[i][j] == 'Q') return false;
		}
		
		return true;
	}
	
	
	private List<String> boardToList(char[][] board) {
		List<String> result = new ArrayList<>(board.length);
		for (int i = 0; i < board.length; i++) {
			StringBuilder sb = new StringBuilder();
			for (int j = 0; j < board[0].length; j++) {
				sb.append(board[i][j]);
			}
			result.add(sb.toString());
		}
		return result;
	}
	
	
	public List<List<String>> solveNQueens_withOneDArray(int n) {
		List<List<String>> result = new ArrayList<>();
		// position array stores the column index where queen is placed on each row
		int[] position = new int[n];
		oneDHelper(result, position, 0);
		return result;
		
    }
	
	private void oneDHelper(List<List<String>> result, int[] position, int row) {
		int n = position.length;
		if (row == n) {
			result.add(generateResultList(position));
			return;
		}
		
		for (int col = 0; col < n; col++) {
			// try every column
			position[row] = col;
			if (isValid(position, row)) {
				oneDHelper(result, position, row+1);
			}
		}
	}
	
	private List<String> generateResultList(int[] position) {
		int n = position.length;
		List<String> result = new ArrayList<>(n);
		for (int i = 0; i < n; i++) {
			StringBuilder sb = new StringBuilder();
			for (int j = 0; j < n; j++) {
				if (position[i] == j) sb.append('Q');
				else sb.append('.');
			}
			result.add(sb.toString());
		}
		return result;
	}
	
	private boolean isValid(int[] position, int row) {
		for (int i = 0; i < row; i++) { // check the rows that has been processed so far
			if (position[i] == position[row]  || // check column  
					Math.abs(position[i]-position[row]) == row -i // check diagonal, diagonal has row==col 
					) {
				return false;
			}
		}
		return true;
	}
	
	
	/*
	 * Follow up for N-Queens problem. Instead outputting board configurations
	 * return the total number of distinct solutions.
	 */
	private int total = 0;
	
	public int totalNQueens(int n) {
		char[][] board = new char[n][n];
		for (int i = 0; i < board.length; i++) {
			Arrays.fill(board[i], '.');
		}
		total_twoDHelper(board, 0);
		return total;
    }
	
	private void total_twoDHelper(char[][] board, int row) {
		if (row == board.length) {
			total++;
		}
		
		for (int i = 0; i < board.length; i++) {
			if (canPlace(board, row, i)) {
				board[row][i] = 'Q';
				total_twoDHelper(board, row+1);
				board[row][i] = '.';
			}
		}
	}
	
	public int totalNQueens_withBooleanArrays(int n) {
		boolean[] cols = new boolean[n]; 
		boolean[] diag1 = new boolean[2*n]; // diagonal \ 
		boolean[] diag2 = new boolean[2*n]; // diagonal /
		withBooleanArraysHelper(cols, diag1, diag2, 0);
		return total;
    }
	
	private void withBooleanArraysHelper(boolean[] cols, boolean[] diag1, boolean[] diag2, int row) {
		int n = cols.length;
		if (row == n) {
			total++;
			return;
		}
		
		// Start row by row, and loop through column
		for (int i = 0; i < n; i++) {
			// trick: to detect whether 2 positions sit on the same diagonal:
			// if delta(col, row) equals, same diagnol1;
			// if sum(col, row) equals, same diagnal2.
			int d1 = i - row + n; // add n to void negative number
			int d2 = i + row;
			
			// skip unsafe positions 
			if (cols[i] || diag1[d1] || diag2[d2]) continue;
			
			cols[i] = true;
			diag1[d1] = true;
			diag2[d2] = true;
			withBooleanArraysHelper(cols, diag1, diag2, row+1);
			cols[i] = false;
			diag1[d1] = false;
			diag2[d2] = false;
		}
	}
	
	public static void main(String[] args) {
		NQueens nq = new NQueens();
		
		System.out.println(nq.solveNQueens_withTwoDArray(4));
		
		//System.out.println(nq.totalNQueens_withBooleanArrays(4));
		
	}
}
