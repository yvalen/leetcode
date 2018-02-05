package leetcode.bfsdfs;

import java.util.LinkedList;
import java.util.Queue;

import leetcode.matrix.MatrixUtil;

/*
 * LEETCODE 200
 * Given a 2d grid map of '1's (land) and '0's (water), count the number of islands. An island is surrounded 
 * by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four 
 * edges of the grid are all surrounded by water.
 * Example 1:
 * 	11110
 * 	11010
 * 	11000
 * 	00000
 * Answer: 1
 * Example 2:
 * 	11000
 * 	11000
 * 	00100
 * 	00011
 * Answer: 3
 * 
 * Company: Amazon, Microsoft, Google, Facebook, Zenefits
 * Difficulty: medium
 * Similar Questions: 695(MaxAreaOfIsland)
 */
public class NumberOfIslands {
    private static final int[][] OFFSETS = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

    // DFS
    // - scan each cell in grid
    // - if the cell value is 1 explore the island
    // - mark the explored island cells with 0
    // - once finish exploring that island increment the island counts
    // Time complexity O(mn)
    // Space Complexity O(mn) in case grid is filled with land and dfs goes by mn deep
    public int numIslands_dfs(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0)
            return 0;

        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    dfs(grid, i, j);
                    System.out.println("i=" + i + " j=" + j);
                    MatrixUtil.print(grid);
                    count++;
                }
            }
        }
        return count;
    }

    private void dfs(char[][] grid, int row, int col) {
        if (row < 0 || col < 0 || row >= grid.length || col >= grid[0].length || grid[row][col] == '0') {
            return;
        }

        grid[row][col] = '0';
        for (int[] offset : OFFSETS) {
            dfs(grid, row + offset[0], col + offset[1]);
        }
    }

    //
    // BFS
    // Time complexity O(mn)
    // Space Complexity O(min(m, n)) in case grid is filled with land, size of queue can grow up to min(m, n)
    public int numIslands_bfs(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0)
            return 0;

        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    bfs(grid, i, j);
                    count++;
                }
            }
        }
        return count;
    }

    private void bfs(char[][] grid, int row, int col) {
        grid[row][col] = '0'; // mark the cell as visited
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[] { row, col }); // store an array in queue
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            for (int[] offset : OFFSETS) {
                int x = curr[0] + offset[0], y = curr[1] + offset[1];
                if (x < 0 || y < 0 || x >= grid.length || y >= grid[0].length || grid[x][y] == '0') {
                    continue;
                }
                queue.offer(new int[] { x, y });
                grid[x][y] = '0'; // need to mark {x, y} as visited here,
                                  // otherwise while loop will never end
            }
        }
    }

    //
    // union find
    // See unionfind

    public static void main(String[] args) {
        NumberOfIslands ni = new NumberOfIslands();

        char[][] grid = { { '1', '1', '0', '0', '0' }, { '1', '1', '0', '0', '0' }, { '0', '0', '1', '0', '0' },
                { '0', '0', '0', '1', '1' } };

        /*
         * char[][] grid = { {'1', '1', '1'}, {'0', '1', '0'}, {'1', '1', '1'},
         * };
         */
        System.out.println(ni.numIslands_bfs(grid));
    }
}
