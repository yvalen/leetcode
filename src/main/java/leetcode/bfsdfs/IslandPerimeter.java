package leetcode.bfsdfs;

/*
 * LEETCODE 463
 * You are given a map in form of a two-dimensional integer grid where 
 * 1 represents land and 0 represents water. Grid cells are connected 
 * horizontally/vertically (not diagonally). The grid is completely 
 * surrounded by water, and there is exactly one island (i.e., one or 
 * more connected land cells). The island doesn't have "lakes" (water 
 * inside that isn't connected to the water around the island). One cell 
 * is a square with side length 1. The grid is rectangular, width and 
 * height don't exceed 100. Determine the perimeter of the island.
 * Example:
 * [[0,1,0,0],
 * 	[1,1,1,0],
 * 	[0,1,0,0],
 * 	[1,1,0,0]]
 * Answer: 16
 * Explanation: The perimeter is the 16 yellow stripes in the image below
 * 
 * Company: Google
 * Difficulty: easy
 * Similar Questions: 695(MaxAreaOfIsland), 733(FloodFill)
 */
public class IslandPerimeter {
    // 1. loop over the matrix and count the number of land cells
    // 2. if the current cell is land, count if it has any right neighbor or down neighbor
    // 3. the result is lands * 4 - neighbors * 2
    // Each land cell has 4 sides and to get the total potential number of sides
    // you would need to multiply the total number of lands by 4.
    // A side which is not included in the perimeter count is a side that
    // belongs to two islands.
    // skip left or up neighbor because they must have been counted by other
    // cells as their right or down neighbors,
    public int islandPerimeter(int[][] grid) {
        if (grid == null || grid.length == 0)
            return 0;

        int lands = 0, neighbors = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    lands++;
                    if (i < grid.length - 1 && grid[i + 1][j] == 1)
                        neighbors++; // count down neighbor
                    if (j < grid[0].length - 1 && grid[i][j + 1] == 1)
                        neighbors++; // count right neighbors
                }
            }
        }

        return lands * 4 - neighbors * 2;
    }
    
    private static final int[][] DIRS = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
    private int perimeter;
    public int islandPerimeter_dfs(int[][] grid) {
        perimeter = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    dfs(grid, i, j);
                    return perimeter;
                }
            }
        }
        return perimeter;
    }
    
    private void dfs(int[][] grid, int row, int col) {
        if (row < 0 || row >= grid.length || col < 0 || col >= grid[0].length || grid[row][col] != 1) {
            return;
        }
        perimeter += ((col == grid[0].length-1 || grid[row][col+1] == 0) ? 1 : 0) + // right
                ((col == 0 || grid[row][col-1] == 0) ? 1 : 0) + // left
                ((row == grid.length-1 || grid[row+1][col] == 0) ? 1 : 0) + // down
                ((row == 0 || grid[row-1][col] == 0) ? 1 : 0); // up
        grid[row][col] = -1;
        for (int [] dir : DIRS) {
            int x = row + dir[0], y = col + dir[1];
            dfs(grid, x, y);
        }
    }
}
