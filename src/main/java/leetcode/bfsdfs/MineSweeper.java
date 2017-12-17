package leetcode.bfsdfs;

import java.util.LinkedList;
import java.util.Queue;

/*
 * Let's play the minesweeper game, https://en.wikipedia.org/wiki/Minesweeper_(video_game)
 * You are given a 2D char matrix representing the game board. 'M' represents an unrevealed mine, 'E' represents an unrevealed empty square, 
 * 'B' represents a revealed blank square that has no adjacent (above, below, left, right, and all 4 diagonals) mines, digit ('1' to '8') 
 * represents how many mines are adjacent to this revealed square, and finally 'X' represents a revealed mine.
 * Now given the next click position (row and column indices) among all the unrevealed squares ('M' or 'E'), return the board after revealing 
 * this position according to the following rules:
 * - If a mine ('M') is revealed, then the game is over - change it to 'X'.
 * - If an empty square ('E') with no adjacent mines is revealed, then change it to revealed blank ('B') and all of its adjacent unrevealed 
 * squares should be revealed recursively.
 * - If an empty square ('E') with at least one adjacent mine is revealed, then change it to a digit ('1' to '8') representing the number of 
 * adjacent mines.
 * - Return the board when no more squares will be revealed.
 * Example 1:
 * Input: 
 * [['E', 'E', 'E', 'E', 'E'],
 * 	['E', 'E', 'M', 'E', 'E'],
 * 	['E', 'E', 'E', 'E', 'E'],
 * 	['E', 'E', 'E', 'E', 'E']]
 * Click : [3,0]
 * Output: 
 * [['B', '1', 'E', '1', 'B'],
 * 	['B', '1', 'M', '1', 'B'],
 * 	['B', '1', '1', '1', 'B'],
 * 	['B', 'B', 'B', 'B', 'B']]
 * Example 2
 * Input: 
 * [['B', '1', 'E', '1', 'B'],
 * 	['B', '1', 'M', '1', 'B'],
 * 	['B', '1', '1', '1', 'B'],
 * 	['B', 'B', 'B', 'B', 'B']]
 * Click : [1,2]
 * Output: 
 * [['B', '1', 'E', '1', 'B'],
 * 	['B', '1', 'X', '1', 'B'],
 * 	['B', '1', '1', '1', 'B'],
 * 	['B', 'B', 'B', 'B', 'B']]
 * Note:
 * - The range of the input matrix's height and width is [1,50].
 * - The click position will only be an unrevealed square ('M' or 'E'), which also means the input board contains at least one clickable square.
 * - The input board won't be a stage when game is over (some mines have been revealed).
 * - For simplicity, not mentioned rules should be ignored in this problem. For example, you don't need to reveal all the unrevealed mines when 
 * the game is over, consider any cases that you will win the game or flag any squares.
 * 
 * Company: Amazon
 * Difficulty: medium
 */
public class MineSweeper {
    //
    // 1. If click on a mine ('M'), mark it as 'X', stop further search.
    // 2. If click on an empty cell ('E'), depends on how many surrounding mine:
    // 2.1 Has surrounding mine(s), mark it with number of surrounding mine(s),
    // stop further search.
    // 2.2 No surrounding mine, mark it as 'B', continue search its 8 neighbors.
    //

    private static final int[][] DIRS = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 }, { -1, -1 }, { -1, 1 }, { 1, -1 },
            { 1, 1 } };

    public char[][] updateBoard_dfs(char[][] board, int[] click) {
        int row = click[0], col = click[1];
        int m = board.length, n = board[0].length;

        if (board[row][col] == 'M') { // stop after revealing a mine
            board[row][col] = 'X';
            return board;
        }

        // empty cell, count the number of mines in the surrounding cells
        int count = 0;
        for (int[] dir : DIRS) {
            int x = row + dir[0], y = col + dir[1];
            if (x >= 0 && x < m && y >= 0 && y < n) {
                if (board[x][y] == 'M' || board[x][y] == 'X')
                    count++; // increment count if surrounding cell has either a
                             // revealed or a unrevealed mine
            }
        }

        if (count > 0) { // stop when there are surrounding mines
            board[row][col] = (char) (count + '0');
            return board;
        }

        board[row][col] = 'B'; // mark the cell as a revealed empty cell

        // perform dfs for unrevealed surrounding cells
        for (int[] dir : DIRS) {
            int x = row + dir[0], y = col + dir[1];
            if (x >= 0 && x < m && y >= 0 && y < n && board[x][y] == 'E') { // only
                                                                            // perform
                                                                            // dfs
                                                                            // for
                                                                            // unrevealed
                                                                            // cell
                updateBoard_dfs(board, new int[] { x, y });
            }
        }
        return board;
    }

    public char[][] updateBoard_bfs(char[][] board, int[] click) {
        int m = board.length, n = board[0].length;
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(click);
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int row = current[0], col = current[1];
            if (board[row][col] == 'M') { // stop after revealing a mine
                board[row][col] = 'X';
                continue;
            }
            int count = 0;
            for (int[] dir : DIRS) {
                int x = row + dir[0], y = col + dir[1];
                if (x >= 0 && x < m && y >= 0 && y < n && (board[x][y] == 'M' || board[x][y] == 'X'))
                    count++;
            }
            if (count > 0) {
                board[row][col] = (char) (count + '0');
                continue;
            }

            board[row][col] = 'B';
            for (int[] dir : DIRS) {
                int x = row + dir[0], y = col + dir[1];
                if (x >= 0 && x < m && y >= 0 && y < n && board[x][y] == 'E') {
                    queue.offer(new int[] { x, y });
                    board[x][y] = 'B'; // prevent this cell to be added again
                }
            }
        }
        return board;
    }

}
