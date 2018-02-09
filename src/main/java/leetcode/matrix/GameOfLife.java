package leetcode.matrix;

/*
 * LEETCODE 289
 * According to the Wikipedia's article: "The Game of Life, also known simply as Life, 
 * is a cellular automaton devised by the British mathematician John Horton Conway in 1970." 
 * Given a board with m by n cells, each cell has an initial state live (1) or dead (0). 
 * Each cell interacts with its eight neighbors (horizontal, vertical, diagonal) using the 
 * following four rules (taken from the above Wikipedia article):
 * - Any live cell with fewer than two live neighbors dies, as if caused by under-population.
 * - Any live cell with two or three live neighbors lives on to the next generation.
 * - Any live cell with more than three live neighbors dies, as if by over-population..
 * - Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.
 * Write a function to compute the next state (after one update) of the board given its current state.
 * Follow up: 
 * - Could you solve it in-place? Remember that the board needs to be updated at the same time: 
 * You cannot update some cells first and then use their updated values to update other cells.
 * - In this question, we represent the board using a 2D array. In principle, the board is infinite, 
 * which would cause problems when the active area encroaches the border of the array. How would you 
 * address these problems?
 * 
 * Company: Dropbox, Google, Two Sigma, Snapchat
 * Difficulty: medium
 */
public class GameOfLife {
    // use 2 bits to store 2 states:
    // [2nd bit, 1st bit] = [next state, current state]
    // - 00 dead (next) <- dead (current)
    // - 01 dead (next) <- live (current)
    // - 10 live (next) <- dead (current)
    // - 11 live (next) <- live (current)
    // Since every 2nd state is by default dead, only two transition to check
    // - Transition 01 -> 11: when board == 1 and lives >= 2 && lives <= 3.
    // - Transition 00 -> 10: when board == 0 and lives == 3.
    public void gameOfLife_inplace(int[][] board) {
        if (board == null || board.length == 0)
            return;

        int m = board.length, n = board[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // count the number of live cell for a given cell's neighbors and itself
                int count = 0;
                // check max for start and min for end
                for (int ii = Math.max(0, i - 1); ii < Math.min(m, i + 2); ii++) {
                    for (int jj = Math.max(0, j - 1); jj < Math.min(n, j + 2); jj++) {
                    		// current state is board[i][j] & 1
                        count += board[ii][jj] & 1; 
                    }
                }
                /*
                 * if (count == 3 || count - board[i][j] == 3) { board[i][j] |=
                 * 2; }
                 */

                count -= board[i][j];
                if (board[i][j] == 1 && (count == 2 || count == 3)) {
                    board[i][j] = 3;
                }

                if (board[i][j] == 0 && count == 3) {
                    board[i][j] = 2;
                }

            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] >>= 1;
            }
        }
    }
    
    public void gameOfLife_withArray(int[][] board) {
        if (board == null || board.length == 0) return;
        
        int m = board.length, n = board[0].length;
        int[][] result = new int[m][n];
        for (int i = 0; i < m; i ++) {
            for (int j = 0; j < n; j++) {
                int liveNeighbors = 0;
                for (int ii = Math.max(0, i-1); ii <= Math.min(m-1, i+1); ii++) {
                    for (int jj = Math.max(0, j-1); jj <= Math.min(n-1, j+1); jj++) {
                        if ((ii != i || jj != j) && board[ii][jj] == 1) {
                            liveNeighbors++;
                        }
                    }
                }
                //System.out.println("i=" +i + " j=" + j + " cell=" + board[i][j] + " liveNeighbors=" + liveNeighbors);
                if (board[i][j] == 1 && (liveNeighbors == 2 || liveNeighbors == 3)) {
                    result[i][j] = 1;
                } 
                else if (board[i][j] == 0 && liveNeighbors == 3) {
                    result[i][j] = 1;
                }
                
            }
        }
        
        for (int i = 0; i < m; i ++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = result[i][j];
            }
        }
    }

    public static void main(String[] args) {
        GameOfLife gol = new GameOfLife();
        // int[][] board = {
        // {0}
        // };
        int[][] board = { { 1, 1 } };
        gol.gameOfLife_inplace(board);
        MatrixUtil.print(board);
    }
}
