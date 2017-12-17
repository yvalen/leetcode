package leetcode.bfsdfs;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

import leetcode.matrix.MatrixUtil;

/*
 * Given a matrix consists of 0 and 1, find the distance of the nearest 0 for each cell. The distance between two adjacent cells is 1.
 * Example 1:
 * Input:
 * 	0 0 0
 * 	0 1 0
 * 	0 0 0
 * Output:
 * 	0 0 0
 * 	0 1 0
 * 	0 0 0
 * 
 * Example 2:
 * Input:
 * 	0 0 0
 * 	0 1 0
 * 	1 1 1
 * Output:
 * 	0 0 0
 * 	0 1 0
 * 	1 2 1
 * Note:
 * - The number of elements of the given matrix will not exceed 10,000.
 * - There are at least one 0 in the given matrix.
 * - The cells are adjacent in only four directions: up, down, left and right.
 * 
 * Company: Google
 * Difficulty: medium
 */
public class ZeroOneMatrix {
    private static final int[][] DELTAS = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

    public int[][] updateMatrix_bfs(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        int[][] result = new int[m][n];

        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0)
                    queue.offer(new int[] { i, j });
            }
        }

        while (!queue.isEmpty()) {
            int[] position = queue.poll();
            int row = position[0], col = position[1];
            for (int[] delta : DELTAS) {
                int x = row + delta[0], y = col + delta[1];
                if (x < 0 || x >= m || y < 0 || y >= n || matrix[x][y] == 0 || result[x][y] != 0) { // use
                                                                                                    // result
                                                                                                    // array
                                                                                                    // to
                                                                                                    // check
                                                                                                    // if
                                                                                                    // the
                                                                                                    // cell
                                                                                                    // has
                                                                                                    // been
                                                                                                    // visited
                    continue;
                }

                result[x][y] = result[row][col] + 1;
                queue.offer(new int[] { x, y });
            }
        }

        return result;
    }

    /*
     * public int[][] updateMatrix_dfs(int[][] matrix) { int m = matrix.length,
     * n = matrix[0].length; int[][] result = new int[m][n]; for (int i = 0; i <
     * m; i++) { for (int j = 0; j < n; j++) { if (matrix[i][j] == 1)
     * result[i][j] = Integer.MAX_VALUE; } } for (int i = 0; i < m; i++) { for
     * (int j = 0; j < n; j++) { if (matrix[i][j] == 0) { dfs(matrix, result, i,
     * j, 0); } } }
     * 
     * return result; }
     * 
     * private void dfs(int[][] matrix, int[][] result, int row, int col, int
     * distance) { if (row < 0 || row >= matrix.length || col < 0 || col >=
     * matrix[0].length || result[row][col] < distance) { return; }
     * 
     * result[row][col] = distance; for (int[] delta : DELTAS) { dfs(matrix,
     * result, row + delta[0], col + delta[1], distance + 1); } }
     */
    public static void main(String[] args) {
        ZeroOneMatrix zom = new ZeroOneMatrix();
        int[][] matrix = { { 0, 0, 0 }, { 0, 1, 0 }, { 1, 1, 1 } };
        int[][] result = zom.updateMatrix_bfs(matrix);
        MatrixUtil.print(result);
    }
}
