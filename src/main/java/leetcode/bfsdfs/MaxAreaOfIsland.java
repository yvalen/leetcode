package leetcode.bfsdfs;

/*
 * LEETCODE 695
 * Given a non-empty 2D array grid of 0's and 1's, an island is a group of 1's (representing land) connected 4-directionally (horizontal or vertical.) 
 * You may assume all four edges of the grid are surrounded by water. Find the maximum area of an island in the given 2D array. If there is no island, 
 * the maximum area is 0.
 * Example 1:
 * [[0,0,1,0,0,0,0,1,0,0,0,0,0],
 * 	[0,0,0,0,0,0,0,1,1,1,0,0,0],
 * 	[0,1,1,0,1,0,0,0,0,0,0,0,0],
 * 	[0,1,0,0,1,1,0,0,1,0,1,0,0],
 * 	[0,1,0,0,1,1,0,0,1,1,1,0,0],
 * 	[0,0,0,0,0,0,0,0,0,0,1,0,0],
 * 	[0,0,0,0,0,0,0,1,1,1,0,0,0],
 * 	[0,0,0,0,0,0,0,1,1,0,0,0,0]]
 * Given the above grid, return 6. Note the answer is not 11, because the island must be connected 4-directionally.
 * Example 2:
 * [[0,0,0,0,0,0,0,0]]
 * Given the above grid, return 0.
 * Note: The length of each dimension in the given grid does not exceed 50. 
 * 
 * Company: Intuit
 * Difficulty: easy
 * Similar Questions: 200(NumberOfIslands), 463(IslandPerimeter)
 */
public class MaxAreaOfIsland {
    private static final int[][] DIRS = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

    public int maxAreaOfIsland(int[][] grid) {
        if (grid == null || grid.length == 0)
            return 0;

        boolean[][] visited = new boolean[grid.length][grid[0].length];

        int maxArea = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1 && !visited[i][j]) {
                    int[] area = new int[1]; // need to initialize area before
                                             // calculating each island
                    dfs(grid, visited, i, j, area);
                    maxArea = Math.max(maxArea, area[0]);
                }
            }
        }
        return maxArea;
    }

    private void dfs(int[][] grid, boolean[][] visited, int row, int col, int[] area) {
        if (visited[row][col])
            return;

        visited[row][col] = true;
        area[0]++;
        for (int[] dir : DIRS) {
            int x = row + dir[0], y = col + dir[1];
            if (x >= 0 && x < grid.length && y >= 0 && y < grid[0].length && grid[x][y] == 1 && !visited[x][y]) {
                dfs(grid, visited, x, y, area);
            }
        }
    }

    public static void main(String[] args) {
        MaxAreaOfIsland mai = new MaxAreaOfIsland();
        int[][] grid = { { 1, 1, 0, 0, 0 }, { 1, 1, 0, 0, 0 }, { 0, 0, 0, 1, 1 }, { 0, 0, 0, 1, 1 } };
        System.out.println(mai.maxAreaOfIsland(grid));
    }
}
