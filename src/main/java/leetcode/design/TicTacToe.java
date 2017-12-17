package leetcode.design;

/*
 * LEETCODE 348
 * Design a Tic-tac-toe game that is played between two players on a n x n grid.
 * You may assume the following rules:
 * - A move is guaranteed to be valid and is placed on an empty block.
 * - Once a winning condition is reached, no more moves is allowed.
 * - A player who succeeds in placing n of their marks in a horizontal, vertical, or diagonal row wins the game.
 * Example: Given n = 3, assume that player 1 is "X" and player 2 is "O" in the board.
 * TicTacToe toe = new TicTacToe(3);
 * toe.move(0, 0, 1); -> Returns 0 (no one wins)
 * |X| | |
 * | | | |    // Player 1 makes a move at (0, 0).
 * | | | |
 * toe.move(0, 2, 2); -> Returns 0 (no one wins)
 * |X| |O|
 * | | | |    // Player 2 makes a move at (0, 2).
 * | | | |
 * toe.move(2, 2, 1); -> Returns 0 (no one wins)
 * |X| |O|
 * | | | |    // Player 1 makes a move at (2, 2).
 * | | |X|
 * toe.move(1, 1, 2); -> Returns 0 (no one wins)
 * |X| |O|
 * | |O| |    // Player 2 makes a move at (1, 1).
 * | | |X|
 * toe.move(2, 0, 1); -> Returns 0 (no one wins)
 * |X| |O|
 * | |O| |    // Player 1 makes a move at (2, 0).
 * |X| |X|
 * toe.move(1, 0, 2); -> Returns 0 (no one wins)
 * |X| |O|
 * |O|O| |    // Player 2 makes a move at (1, 0).
 * |X| |X|
 * toe.move(2, 1, 1); -> Returns 1 (player 1 wins)
 * |X| |O|
 * |O|O| |    // Player 1 makes a move at (2, 1).
 * |X|X|X|
 * Follow up: Could you do better than O(n2) per move() operation? 
 * 
 * Company: Microsoft, Google
 * Difficulty: medium
 */
public class TicTacToe {
    // In order to win Tic-Tac-Toe you must have the entire row or column.
    // Thus, we don't need to keep track of an entire n^2 board. We only need to
    // keep a count for each row and column, the diagonal and anti-diagonal.
    // Increment the count by 1 for player and decrement the count by 1 for
    // player 2.
    // Each time a player places a piece we just need to check the count of that
    // row, column, diagonal and anti-diagonal. If at any time a row or column
    // matches the size of the board then that player has won.

    private int[] rowCount;
    private int[] colCount;
    private int diagCount;
    private int antidiagCount;
    private int n;

    public TicTacToe(int n) {
        rowCount = new int[n];
        colCount = new int[n];
        this.n = n;
    }

    /**
     * Player {player} makes a move at ({row}, {col}).
     * 
     * @param row
     *            The row of the board.
     * @param col
     *            The column of the board.
     * @param player
     *            The player, can be either 1 or 2.
     * @return The current winning condition, can be either: 0: No one wins. 1:
     *         Player 1 wins. 2: Player 2 wins.
     */
    public int move(int row, int col, int player) {
        int valToAdd = player == 1 ? 1 : -1;
        rowCount[row] += valToAdd;
        colCount[col] += valToAdd;

        if (row == col)
            diagCount += valToAdd; // need to use valToAdd here as well
        if (row + col == n - 1)
            antidiagCount += valToAdd;

        if (Math.abs(rowCount[row]) == n || Math.abs(colCount[col]) == n || Math.abs(diagCount) == n
                || Math.abs(antidiagCount) == n) {
            return player;
        }

        return 0;
    }

    public static void main(String[] args) {
        TicTacToe ticTacToe = new TicTacToe(3);
        System.out.println(ticTacToe.move(0, 0, 1));
        System.out.println(ticTacToe.move(0, 2, 2));
        System.out.println(ticTacToe.move(2, 2, 1));
        System.out.println(ticTacToe.move(1, 1, 2));
        System.out.println(ticTacToe.move(2, 0, 1));
        System.out.println(ticTacToe.move(1, 0, 2));
        System.out.println(ticTacToe.move(2, 1, 1));
    }

}
