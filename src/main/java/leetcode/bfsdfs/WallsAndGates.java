package leetcode.bfsdfs;

import java.util.LinkedList;
import java.util.Queue;

import leetcode.matrix.MatrixUtil;

/*
 * You are given a m x n 2D grid initialized with these three possible values.
 * -1 - A wall or an obstacle.
 * 0 - A gate.
 * INF - Infinity means an empty room. We use the value 2^31 - 1 = 2147483647 to represent INF as you may assume 
 * that the distance to a gate is less than 2147483647.
 * Fill each empty room with the distance to its nearest gate. If it is impossible to reach a gate, it should be filled with INF.
 * For example, given the 2D grid:
 * INF  -1  0  INF
 * INF INF INF  -1
 * INF  -1 INF  -1
 *  0   -1 INF INF
 * After running your function, the 2D grid should be:
 * 3  -1   0   1
 * 2   2   1  -1
 * 1  -1   2  -1
 * 0  -1   3   4
 * 
 * Company: Google, Facebook
 * Difficulty: Medium
 */
public class WallsAndGates {
	// Initiate breadth-first search (BFS) from all gates at the same time. 
	// Since BFS guarantees that we search all rooms of distance d before searching rooms of distance d + 1, 
	// the distance to an empty room must be the shortest.
	// Time complexity: O(mn), Space complexity: O(mn)
	private static final int[][] DIRECTIONS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	public void wallsAndGates_bfs(int[][] rooms) {
		if (rooms == null || rooms.length == 0 ) return;
		
		int m = rooms.length, n = rooms[0].length;
		// find all gates
		Queue<int[]> queue = new LinkedList<>();
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (rooms[i][j] == 0) queue.offer(new int[] {i, j});
			}
		}
		
		// start BFS from each of the found Gates, expanding outward
		while (!queue.isEmpty()) {
			int[] position = queue.poll();
			int row = position[0], col = position[1];
			for (int[] direction : DIRECTIONS) {
				int x = row + direction[0], y = col + direction[1];
				if (x < 0 || x >= m || y < 0 || y >= n || 
						rooms[x][y] != Integer.MAX_VALUE) { // if the value is set the room has been reached quicker via other gate. gates are in the from of the queue 
					continue;
				}
				rooms[x][y] = rooms[row][col] + 1;
				queue.offer(new int[]{x, y});
			}
		}
    }
	
	//
	// DFS
	// 
	public void wallsAndGates_dfs(int[][] rooms) {
		if (rooms == null || rooms.length == 0 ) return;
		
		int m = rooms.length, n = rooms[0].length;
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (rooms[i][j] == 0) dfs(rooms, i, j, 0);
			}
		}
	}
	
	private void dfs(int[][] rooms, int row, int col, int distance) {
		if (row < 0 || row >= rooms.length || col < 0 || col >= rooms[0].length ||
				rooms[row][col] < distance) {
			return;
		}
		rooms[row][col] = distance; // assign distance
		for (int[] direction : DIRECTIONS) {
			dfs(rooms, row + direction[0], col + direction[1], distance+1);
		}
	}
	
	static int INF = Integer.MAX_VALUE;
	public static void main(String[] args) {
		int[][] rooms = {
			{INF,  -1,   0, INF},
			{INF, INF, INF,  -1},
			{INF,  -1, INF,  -1},
			{  0,  -1, INF, INF}
		};
		WallsAndGates wg = new WallsAndGates();
		wg.wallsAndGates_dfs(rooms);
		MatrixUtil.print(rooms);
	}
}
