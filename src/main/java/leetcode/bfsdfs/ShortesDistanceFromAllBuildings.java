package leetcode.bfsdfs;

import java.util.LinkedList;
import java.util.Queue;

import leetcode.matrix.MatrixUtil;

/*
 * You want to build a house on an empty land which reaches all buildings in the shortest amount of distance. 
 * You can only move up, down, left and right. You are given a 2D grid of values 0, 1 or 2, where:
 * - Each 0 marks an empty land which you can pass by freely.
 * - Each 1 marks a building which you cannot pass through.
 * - Each 2 marks an obstacle which you cannot pass through.
 * For example, given three buildings at (0,0), (0,4), (2,2), and an obstacle at (0,2):
 * 1 - 0 - 2 - 0 - 1
 * |   |   |   |   |
 * 0 - 0 - 0 - 0 - 0
 * |   |   |   |   |
 * 0 - 0 - 1 - 0 - 0
 * The point (1,2) is an ideal empty land to build a house, as the total travel distance of 3+3+1=7 is minimal. So return 7.
 * Note: There will be at least one building. If it is not possible to build such house according to the above rules, return -1.
 * 
 * Company: Google, Zenefits
 * Difficulty: hard
 */
public class ShortesDistanceFromAllBuildings {
	private static final int[][] DIRECTIONS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	public int shortestDistance(int[][] grid) {
		if (grid == null || grid.length == 0) return -1;
		
		int m = grid.length, n = grid[0].length;
		int[][] distances = new int[m][n]; // stores the sum of the shortest distances from this cell to all reachable buildings
		int[][] reachableBuildings = new int[m][n]; // stores the number of buildings reachable from this cell
		int numOfBuildings = 0;
		
		// for each building, use BFS to calculate the shortest distance from each 0 to it 
		// O(kmn) where k is the number of buildings, worst case O(m^2n^2)
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (grid[i][j] == 1) {
					bfs(grid, i, j, distances, reachableBuildings);
					numOfBuildings++;
					MatrixUtil.print(distances);
					System.out.println();
				}
			}
		}
		
		// traverse distances to get the shortest
		int minDistance = Integer.MAX_VALUE;
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (reachableBuildings[i][j] == numOfBuildings) {
					minDistance = Math.min(minDistance, distances[i][j]);
				}
			}
		}
		
		return minDistance == Integer.MAX_VALUE ? -1 : minDistance;
    }

	// BFS/DFS complexity: O(V + E) - O(mn)
	// number of vertex: m*n, number of edges: each vertex has 4 edges E~4V 
	private void bfs(int[][] grid, int row, int col, int[][] distances, int[][] reachableBuildings) {
		boolean[][] visited = new boolean[grid.length][grid[0].length]; // whether the cell has been visited in this traversal
		Queue<int[]> queue = new LinkedList<>();
		queue.offer(new int[] {row, col});
		int level = 1; 
		while(!queue.isEmpty()) {
			// need to keep track of level locally
			// can not use the distances of the current cell because it is updated for each traversal
			int size = queue.size(); 
			while (size-- > 0) {
				int[] position = queue.poll();
				for (int[] direction : DIRECTIONS) {
					int x = position[0] + direction[0], y = position[1] + direction[1];
					if (x < 0 || x >= grid.length || y < 0 || y >= grid[0].length || grid[x][y] != 0 || visited[x][y]) { 
						// visited is local for each iteration. If it is true it means this cell has been checked already for this building
						continue;
					}
					reachableBuildings[x][y]++;
					visited[x][y] = true;
					distances[x][y] += level;  // need to add on top of distances[x][y] to keep track of distance to all buildings
					queue.offer(new int[] {x, y});
				}
			}
			level++;
		}
	}
	
	// Don't use visited and reachableBuildings 
	// In BFS walk only onto the cells that were reachable from all previous buildings. 
	// From the first building only walk onto cells where grid is 0, and make them -1. 
	// From the second building only walk onto cells where grid is -1, and make them -2. And so on.
	// Time complexity: O(kmn), k is the number of buildings. Space comlexity: O(mn)
	public int shortestDistance_spaceOptimized(int[][] grid) {
		if (grid == null || grid.length == 0) return -1;
		
		int m = grid.length, n = grid[0].length;
		int minDistance = -1;
		int walk = 0;
		Queue<int[]> queue = new LinkedList<>();
		int[][] distances = new int[m][n]; 
		
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (grid[i][j] == 1) {
					minDistance = -1;  // need to reset minDistance for each building
					int depth = 0;
					queue.offer(new int[]{i, j});
					while (!queue.isEmpty()) {
						depth++;
						int size = queue.size();
						while (size-- > 0) {
							int[] position = queue.poll();
							for (int[] direction : DIRECTIONS) {
								int x = position[0] + direction[0], y = position[1] + direction[1];
								if (x < 0 || x >= m || y < 0 || y >= n || grid[x][y] != walk) {
									continue;
								}
								distances[x][y] += depth;  // +=
								grid[x][y]--;
								if (minDistance < 0 || minDistance > distances[x][y]) {
									minDistance = distances[x][y];
								}
								queue.offer(new int[]{x, y});
							}
						}
					}
					walk--; // decrement walk inside if
					MatrixUtil.print(distances);
					System.out.println();
				} // end if (grid[i][j] == 1)
				
			}
		}
		
		return minDistance;
    }

	
	
	public static void main(String[] args) {
		ShortesDistanceFromAllBuildings sdab = new ShortesDistanceFromAllBuildings();
		int[][] grid = {
				{1, 0, 2, 0, 1},
				{0, 0, 0, 0, 0},
				{0, 0, 1, 0, 0}
		};
		/*
		int row = 0, col =0, m = 3, n = 5;
		int[][] distances = new int[m][n];
		int[][] reachableBuildings = new int[m][n];
		sdab.bfs(grid, row, col, distances, reachableBuildings);
		MatrixUtil.print(distances);
		System.out.println();
		MatrixUtil.print(reachableBuildings);
		*/
		//System.out.println(sdab.shortestDistance(grid));
		System.out.println(sdab.shortestDistance_spaceOptimized(grid));
	}
}
