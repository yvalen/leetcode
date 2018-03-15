package leetcode.dp;


/*
 * LEETCODE 361
 * Given a 2D grid, each cell is either a wall 'W', an enemy 'E' or empty '0' 
 * (the number zero), return the maximum enemies you can kill using one bomb.
 * The bomb kills all the enemies in the same row and column from the planted 
 * point until it hits the wall since the wall is too strong to be destroyed.
 * Note that you can only put the bomb at an empty cell.
 * Example: For the given grid
 * 0 E 0 0
 * E 0 W E
 * 0 E 0 0
 * return 3. (Placing a bomb at (1,1) kills 3 enemies)
 * 
 * Company: Google
 * Difficulty: medium
 */
public class BombEnemy {
    // brute-force algorithm: for each cell count how many enemies we can hit
    // on a row before we hit the wall, and do the same for the column.
    // Runtime is O(mn(m+n)) because we traverse one row and one column for each
    // cell visited. There is no need to recalculate until we hit a wall. 
    // Time complexity: O(3mn) ~ O(mn) each non-wall cell is counted exactly twice, 
    // once horizontally and once vertically.
    // Space complexity: O(n)
    public static int maxKilledEnemies(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) return 0;
        
        int m = grid.length, n = grid[0].length;
        int result = 0, rowHits = 0;
        int[] colHits = new int[n];
        // go through the matrix row by row
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // recalculate rowHits at the start of the row or 
                // when previous column at the same row is wall 
                if (j == 0 || grid[i][j-1] == 'W') {
                    rowHits = 0; // need to reset to 0
                    for (int k = j; k < n && grid[i][k] != 'W'; k++) {
                        rowHits += (grid[i][k] == 'E' ? 1 : 0);
                    }
                }
                if (i == 0 || grid[i-1][j] == 'W') {
                    colHits[j] = 0;
                    for (int k = i; k < m && grid[k][j] != 'W'; k++) {
                        colHits[j] += (grid[k][j] == 'E' ? 1 : 0);
                    }
                }
                if (grid[i][j] == '0') {
                    result = Math.max(result, rowHits+colHits[j]);
                }
            }
            
        }
        return result;
    }
    
    public static void main(String[] args) {
        char[][] grid = {
                {'0', 'E', '0', '0'},
                {'E', '0', 'W', 'E'},
                {'0', 'E', '0', '0'}
        };
        System.out.println(maxKilledEnemies(grid));
    }
}
