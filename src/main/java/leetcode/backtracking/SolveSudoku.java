package leetcode.backtracking;

/*
 * LEETCODE 37
 * Write a program to solve a Sudoku puzzle by filling the empty cells.
 * Empty cells are indicated by the character '.'.
 * You may assume that there will be only one unique solution. 
 * 
 * Company: Uber, Snapchat
 * Difficulty: hard
 * Similar Questions: 36(ValidSudoku)
 */
public class SolveSudoku {
    public void solveSudoku(char[][] board) {
        if (board == null || board[0].length == 0)
            return;
        solve(board);
    }

    // try 1 through 9 for each empty cell
    // Time complexity: O(9^m), m is the number of empty cells
    public boolean solve(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] != '.') continue;
                for (char v = '1'; v <= '9'; v++) {
                    if (canFillCellWithValue(board, i, j, v)) {
                        board[i][j] = v;

                        if (solve(board)) {
                            return true;
                        } else {
                            board[i][j] = '.';
                        }
                    }
                }
                return false;
            }
        }
        return true;
    }

    private boolean canFillCellWithValue(char[][] board, int x, int y, char value) {
        int blockRow = 3 * (x/3), blockCol = 3 * (y /3);
        for (int i = 0; i < board.length; i++) {
            int blockX = blockRow + i / 3;
            int blockY = blockCol + i % 3;
            // need to check board[i][y] != '.' here because we can fill value in empty cell
            if (board[i][y] != '.' && board[i][y] == value)
                return false;
            if (board[x][i] != '.' && board[x][i] == value)
                return false;
            if (board[blockX][blockY] != '.' && board[blockX][blockY] == value)
                return false;

        }
        return true;
    }

    public static void main(String[] args) {
        SolveSudoku s = new SolveSudoku();

        char[][] board = { { '.', '.', '.', '.', '.', '.', '.', '.', '2' },
                { '.', '.', '.', '.', '.', '.', '6', '.', '.' }, { '.', '.', '1', '4', '.', '.', '8', '.', '.' },
                { '.', '.', '.', '.', '.', '.', '.', '.', '.' }, { '.', '.', '.', '.', '.', '.', '.', '.', '.' },
                { '.', '.', '.', '.', '3', '.', '.', '.', '.' }, { '5', '.', '8', '6', '.', '.', '.', '.', '.' },
                { '.', '9', '.', '.', '.', '.', '4', '.', '.' }, { '.', '.', '.', '.', '5', '.', '.', '.', '.' },

        };

        System.out.println(s.solve(board));
    }

}
