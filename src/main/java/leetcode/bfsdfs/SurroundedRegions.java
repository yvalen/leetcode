package leetcode.bfsdfs;

import java.util.LinkedList;
import java.util.Queue;

import leetcode.array.ArrayUtil;

/*
 * LEETCODE 130
 * Given a 2D board containing 'X' and 'O' (the letter O), capture all regions surrounded by 'X'.
 * A region is captured by flipping all 'O's into 'X's in that surrounded region. For example,
 * X X X X
 * X O O X
 * X X O X
 * X O X X
 * After running your function, the board should be:
 * X X X X
 * X X X X
 * X X X X
 * X O X X
 */
public class SurroundedRegions {
    private static final int[][] DIRS = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

    class Point {
        int x;
        int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "Point [x=" + x + ", y=" + y + "]";
        }
    }

    /*
     * Find all 'O's on the edge, and do BFS from these 'O's. Keep adding 'O's
     * into the queue in the BFS, and mark it as '+'. Since these 'O's are found
     * by doing BFS from the 'O's on the edge, it means they are connected to
     * the edge 'O's. so they are the 'O's that will remain as 'O' in the
     * result.
     */
    public void solve_bfs(char[][] board) {
        if (board == null || board.length == 0 || board[0].length == 0)
            return;

        int m = board.length, n = board[0].length;
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < m; i++) {
            enqueueO(board, i, 0, queue);
            enqueueO(board, i, n-1, queue);
        }
        for (int j = 0; j < n; j++) {
            enqueueO(board, 0, j, queue);
            enqueueO(board, m-1, j, queue);
        }

        System.out.println("before bfs\n");
        ArrayUtil.printTwoDCharArray(board);

        // bfs, change all cells that connects to O in edge to +
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            for (int[] dir : DIRS) {
                int x = current[0]+dir[0], y = current[1]+dir[1];
                if (x > 0 && x <m-1 && y > 0 && y < n-1 && board[x][y] == 'O') {
                    board[x][y] = '+';
                    queue.offer(new int[] {x, y});
                }
            }
        }

        // System.out.println("after bfs\n");
        // ArrayUtil.printTwoDCharArray(board);

        // update board, O -> X, + -> O
        updateBoard(board);
    }

    private void enqueueO(char[][] board, int row, int col, Queue<int[]> queue) {
        if (board[row][col] == 'O') {
            queue.offer(new int[] {row, col});
            board[row][col] = '+';
        }
    }

    private void updateBoard(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 'O')
                    board[i][j] = 'X';
                else if (board[i][j] == '+')
                    board[i][j] = 'O';
            }
        }
    }

    public void solve_dfs(char[][] board) {
        if (board == null || board.length == 0 || board[0].length == 0)
            return;

        int m = board.length, n = board[0].length;
        for (int i = 0; i < m; i++) {
            dfsHelper(board, i, 0);
            dfsHelper(board, i, n - 1);
        }

        for (int j = 0; j < n; j++) {
            dfsHelper(board, 0, j);
            dfsHelper(board, m - 1, j);
        }

        updateBoard(board);
    }

    private void dfsHelper(char[][] board, int i, int j) {
        if (board[i][j] != 'O')
            return;

        board[i][j] = '+';
        if (i > 1)
            dfsHelper(board, i - 1, j);
        if (i < board.length - 1)
            dfsHelper(board, i + 1, j);
        if (j > 1)
            dfsHelper(board, i, j - 1);
        if (j < board[0].length - 1)
            dfsHelper(board, i, j + 1);
    }

    public static void main(String[] args) {
        SurroundedRegions s = new SurroundedRegions();
        String[] strAry = { "XOXOXOOOXO", "XOOXXXOOOX", "OOOOOOOOXX", "OOOOOOXOOX", "OOXXOXXOOO", "XOOXXXOXXO",
                "XOXOOXXOXO", "XXOXXOXOOX", "OOOOXOXOXO", "XXOXXXXOOO" };
        char[][] board = new char[strAry.length][strAry[0].length()];
        for (int i = 0; i < strAry.length; i++) {
            board[i] = strAry[i].toCharArray();
        }
        ArrayUtil.printTwoDCharArray(board);
        s.solve_dfs(board);
        System.out.println("after solve\n");
        ArrayUtil.printTwoDCharArray(board);

    }

}
