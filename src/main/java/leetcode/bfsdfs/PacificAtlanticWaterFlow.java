package leetcode.bfsdfs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
 * LEETCODE 417
 * Given an m x n matrix of non-negative integers representing the height of each
 * unit cell in a continent, the "Pacific ocean" touches the left and top edges of 
 * the matrix and the "Atlantic ocean" touches the right and bottom edges. 
 * Water can only flow in four directions (up, down, left, or right) from a cell to 
 * another one with height equal or lower. Find the list of grid coordinates where 
 * water can flow to both the Pacific and Atlantic ocean.
 * Note:
 * - The order of returned grid coordinates does not matter.
 * - Both m and n are less than 150.
 * Example:
 * Given the following 5x5 matrix:
 * 	  Pacific ~   ~   ~   ~   ~ 
 * 		   ~  1   2   2   3  (5) *
 *         ~  3   2   3  (4) (4) *
 *         ~  2   4  (5)  3   1  *
 *         ~ (6) (7)  1   4   5  *
 *         ~ (5)  1   1   2   4  *
 *         	  *   *   *   *   * Atlantic
 * Return: [[0, 4], [1, 3], [1, 4], [2, 2], [3, 0], [3, 1], [4, 0]] 
 * (positions with parentheses in above matrix).
 * 
 * Company: Google
 * Difficulty: medium
 */
public class PacificAtlanticWaterFlow {
    private static final int[][] DIRS = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };

    // Time complexity: O(mn)
    public List<int[]> pacificAtlantic_bfs(int[][] matrix) {
        if (matrix == null || matrix.length == 0)
            return Collections.emptyList();

        int m = matrix.length, n = matrix[0].length;
        Queue<int[]> queueP = new LinkedList<>();
        Queue<int[]> queueA = new LinkedList<>();
        boolean[][] visitedP = new boolean[m][n];
        boolean[][] visitedA = new boolean[m][n];

        for (int i = 0; i < m; i++) {
            queueP.offer(new int[] { i, 0 });
            visitedP[i][0] = true;
            queueA.offer(new int[] { i, n - 1 });
            visitedA[i][n - 1] = true;
        }
        for (int j = 0; j < n; j++) {
            queueP.offer(new int[] { 0, j });
            visitedP[0][j] = true;
            queueA.offer(new int[] { m - 1, j });
            visitedA[m - 1][j] = true;
        }

        bfs(matrix, queueP, visitedP);
        bfs(matrix, queueA, visitedA);

        // collect all coordinates that can flow to both oceans
        // these are the points which have visited as true in both arrays
        List<int[]> result = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (visitedP[i][j] && visitedA[i][j])
                    result.add(new int[] { i, j });
            }
        }
        return result;
    }

    private void bfs(int[][] matrix, Queue<int[]> queue, boolean[][] visited) {
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            for (int[] dir : DIRS) {
                int x = current[0] + dir[0], y = current[1] + dir[1];
                if (x > 0 && x < matrix.length - 1 && y > 0 && y < matrix[0].length - 1 && !visited[x][y]
                        && matrix[x][y] >= matrix[current[0]][current[1]]) {
                    visited[x][y] = true;
                    queue.offer(new int[] { x, y });
                }
            }
        }
    }

    public List<int[]> pacificAtlantic_dfs(int[][] matrix) {
        if (matrix == null || matrix.length == 0)
            return Collections.emptyList();

        int m = matrix.length, n = matrix[0].length;
        boolean[][] visitedP = new boolean[m][n];
        boolean[][] visitedA = new boolean[m][n];

        for (int i = 0; i < m; i++) {
            dfs(matrix, visitedP, new int[] { i, 0 });
            dfs(matrix, visitedA, new int[] { i, n - 1 });
        }

        for (int j = 0; j < n; j++) {
            dfs(matrix, visitedP, new int[] { 0, j });
            dfs(matrix, visitedA, new int[] { m - 1, j });
        }

        // collect all coordinates that can flow to both oceans
        // these are the points which have visited as true in both arrays
        List<int[]> result = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (visitedP[i][j] && visitedA[i][j])
                    result.add(new int[] { i, j });
            }
        }
        return result;
    }

    private void dfs(int[][] matrix, boolean[][] visited, int[] start) {
        int row = start[0], col = start[1];
        visited[row][col] = true;
        for (int[] dir : DIRS) {
            int x = row + dir[0], y = col + dir[1];
            if (x >= 0 && x < matrix.length && y >= 0 && y < matrix[0].length 
                    && !visited[x][y] 
                    && matrix[x][y] >= matrix[row][col]) { // starting point
                                                           // should be the
                                                           // lowest on height
                dfs(matrix, visited, new int[] { x, y });
            }
        }
    }

    public static void main(String[] args) {
        PacificAtlanticWaterFlow pawf = new PacificAtlanticWaterFlow();
        int[][] matrix = { { 1, 2, 2, 3, 5 }, { 3, 2, 3, 4, 4 }, { 2, 4, 5, 3, 1 }, { 6, 7, 1, 4, 5 },
                { 5, 1, 1, 2, 4 } };

        pawf.pacificAtlantic_dfs(matrix);
    }
}
