package leetcode.bfsdfs;

import java.util.LinkedList;
import java.util.Queue;
/*
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
 */
public class NumberOfIslands {
	
	// DFS
	// - scan each cell in grid
	// - if the cell value is 1 explore the island
	// - mark the explored island cells with 0
	// - once finish exploring that island increment the island counts
	// Time complexity O(mn)
	public int numIslands_dfs(char[][] grid) {
		if (grid == null || grid.length == 0 || grid[0].length == 0) return 0;
		
		int count = 0;
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				if (grid[i][j] == '1') {
					dfs(grid, i, j);
					count++;
				}
			}
		}
		return count;
    }

	private void dfs(char[][] grid, int i, int j) {
		if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length ||
				grid[i][j] != '1') {
			return;
		}
			
		// mark current cell as visited
		grid[i][j] = '0';
		dfs(grid, i-1, j);
		dfs(grid, i+1, j);
		dfs(grid, i, j-1);
		dfs(grid, i, j+1);
	}
	
	//
	// BFS
	// Time complexity O(mn)
	public int numIslands_bfs(char[][] grid) {
		if (grid == null || grid.length == 0 || grid[0].length == 0) return 0;
		
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
	
	private void bfs(char[][] grid, int x, int y) {
		// mark the cell as visited
		grid[x][y] = '0';
		
		int m = grid.length, n = grid[0].length;
		Queue<int[]> queue = new LinkedList<>();
		queue.offer(new int[] {x, y}); // store an array in queue
		while(!queue.isEmpty()) {
			int[] curr = queue.poll();
			int i = curr[0], j = curr[1];
			if (i > 0 && grid[i-1][j] == '1') { //up
				queue.offer(new int[]{i-1, j});
				grid[i-1][j] = '0';
			}
			if (i < m-1 && grid[i+1][j] == '1') { // down
				queue.offer(new int[]{i+1, j});
				grid[i+1][j] = '0';
			}
			if (j > 0 && grid[i][j-1] == '1') { // left
				queue.offer(new int[]{i, j-1});
				grid[i][j-1] = '0';
			}
			if (j < n-1 && grid[i][j+1] == '1') { // right
				queue.offer(new int[]{i, j+1});
				grid[i][j+1] = '0';
			}
		}
	}
	
	//
	// union find
	//
	
	
	public static void main(String[] args) {
		NumberOfIslands ni = new NumberOfIslands();
		/*
		char[][] grid = {
				{'1', '1', '0', '0', '0'},
				{'1', '1', '0', '0', '0'},
				{'0', '0', '1', '0', '0'},
				{'0', '0', '0', '1', '1'}
		};
		*/
		char[][] grid = {
				{'1', '1', '1'},
				{'0', '1', '0'},
				{'1', '1', '1'},
		};
		System.out.println(ni.numIslands_bfs(grid));		
	}
}
