package leetcode.bfsdfs;

/*
 * Given an integer matrix, find the length of the longest increasing path. From each cell, you can either move to four directions: 
 * left, right, up or down. You may NOT move diagonally or move outside of the boundary (i.e. wrap-around is not allowed).
 * Example 1:
 * 	nums = [
 * 		[9,9,4],
 * 		[6,6,8],
 * 		[2,1,1]
 * 	]
 * Return 4, the longest increasing path is [1, 2, 6, 9].
 * Example 2:
 * 	nums = [
 * 		[3,4,5],
 * 		[3,2,6],
 * 		[2,2,1]
 * 	]
 * Return 4, the longest increasing path is [3, 4, 5, 6]. Moving diagonally is not allowed.
 * 
 * Company: Google
 */
public class LongestIncreasingPathInMatrix {
    /*
     * To get max length of increasing sequences: - Do DFS from every cell -
     * Compare every 4 direction and skip cells that are out of boundary or
     * smaller - Get matrix max from every cell's max - Use matrix[x][y] <=
     * matrix[i][j] so we don't need a visited[m][n] array The key is to cache
     * the distance because it's highly possible to revisit a cell
     */

    private static final int[][] DIRS = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

    public int longestIncreasingPath(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
            return 0;

        int m = matrix.length, n = matrix[0].length;
        int[][] cache = new int[m][n];

        int maxLen = 1;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                int len = dfs(matrix, i, j, cache);
                maxLen = Math.max(maxLen, len);
            }
        }
        return maxLen;
    }

    private int dfs(int[][] matrix, int row, int col, int[][] cache) {
        if (cache[row][col] != 0)
            return cache[row][col];

        int maxLen = 1;
        for (int[] dir : DIRS) {
            int x = row + dir[0], y = col + dir[1];
            if (x < 0 || y < 0 || x >= matrix.length || y >= matrix[0].length || // out
                                                                                 // of
                                                                                 // boundary
                    matrix[x][y] <= matrix[row][col]) { // non increasing
                continue;
            }
            int len = 1 + dfs(matrix, x, y, cache);
            maxLen = Math.max(maxLen, len);
        }
        cache[row][col] = maxLen;
        return maxLen;
    }

}
