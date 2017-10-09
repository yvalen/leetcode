package leetcode.bfsdfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
 * LEETCODE 694
 * Given a non-empty 2D array grid of 0's and 1's, an island is a group of 1's (representing land) connected 4-directionally (horizontal or vertical.) 
 * You may assume all four edges of the grid are surrounded by water. Count the number of distinct islands. An island is considered to be the same as 
 * another if and only if one island can be translated (and not rotated or reflected) to equal the other.
 * Example 1:
 * 11000
 * 11000
 * 00011
 * 00011
 * Given the above grid map, return 1.
 * Example 2:
 * 11011
 * 10000
 * 00001
 * 11011
 * Given the above grid map, return 3.
 * Notice that:
 * 11
 * 1
 * and
 * 	1
 * 11
 * are considered different island shapes, because we do not consider reflection / rotation.
 * Note: The length of each dimension in the given grid does not exceed 50. 
 * 
 * Company: Amazon
 * Difficulty: medium
 * Similar Questions: 200(NumberOfIslands)
 */
public class NumberOfDistinctIslands {
	private static final int[][] DIRS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	
	public int numDistinctIslands(int[][] grid) {
        if (grid == null|| grid.length == 0) return 0;
        
        // use a set to de-dup islands, set elements are offset of cells in an island 
        // with respect to the starting cell. list with same items in same order are equal lists.
        Set<List<List<Integer>>> islands = new HashSet<>();
        for (int i = 0 ; i < grid.length; i++) {
        	for (int j = 0; j < grid[0].length; j++) {
        		if (grid[i][j] == 1) {
        			List<List<Integer>> island = new ArrayList<>();
        			dfs(grid, i, j, i, j, island);
        			islands.add(island);
        		}
        	}
        }
	
		return islands.size();
    }
	
	private void dfs(int[][] grid, int startx, int starty, int row, int col, List<List<Integer>> island) {
		grid[row][col] = 0;
		island.add(Arrays.asList(row-startx, col-starty));
		for (int[] dir : DIRS) {
			int x = row + dir[0], y = col + dir[1];
			if (x >= 0 && x <grid.length && y >=0 && y < grid[0].length && grid[x][y] == 1) {
				dfs(grid, startx, starty, x, y, island);
			}
		}
	}

	public static void main(String[] args) {
		NumberOfDistinctIslands ndi = new NumberOfDistinctIslands();
		int[][] grid = {
				{1,1,0,0,0},
				{1,1,0,0,0},
				{0,0,0,1,1},
				{0,0,0,1,1}
		};
		System.out.println(ndi.numDistinctIslands(grid));
	}
}
