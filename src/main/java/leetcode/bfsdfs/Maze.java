package leetcode.bfsdfs;

import java.util.LinkedList;
import java.util.Queue;


public class Maze {
	private static final int[][] DIRECTIONS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	
	/*
	 * There is a ball in a maze with empty spaces and walls. The ball can go through empty spaces by rolling up, down, left or right, 
	 * but it won't stop rolling until hitting a wall. When the ball stops, it could choose the next direction. Given the ball's start 
	 * position, the destination and the maze, determine whether the ball could stop at the destination. The maze is represented by a 
	 * binary 2D array. 1 means the wall and 0 means the empty space. You may assume that the borders of the maze are all walls. The start 
	 * and destination coordinates are represented by row and column indexes.
	 * Example 1
	 * Input 1: a maze represented by a 2D array
	 * 0 0 1 0 0
	 * 0 0 0 0 0
	 * 0 0 0 1 0
	 * 1 1 0 1 1
	 * 0 0 0 0 0
	 * Input 2: start coordinate (rowStart, colStart) = (0, 4)
	 * Input 3: destination coordinate (rowDest, colDest) = (4, 4)
	 * Output: true
	 * Explanation: One possible way is : left -> down -> left -> down -> right -> down -> right.
	 * Example 2
	 * Input 1: a maze represented by a 2D array
	 * 0 0 1 0 0
	 * 0 0 0 0 0
	 * 0 0 0 1 0
	 * 1 1 0 1 1
	 * 0 0 0 0 0
	 * Input 2: start coordinate (rowStart, colStart) = (0, 4)
	 * Input 3: destination coordinate (rowDest, colDest) = (3, 2)
	 * Output: false
	 * Explanation: There is no way for the ball to stop at the destination.
	 * Note:
	 * - There is only one ball and one destination in the maze.
	 * - Both the ball and the destination exist on an empty space, and they will not be at the same position initially.
	 * - The given maze does not contain border (like the red rectangle in the example pictures), but you could assume the border of the maze are all walls.
	 * - The maze contains at least 2 empty spaces, and both the width and height of the maze won't exceed 100.
	 * 
	 * Company: Google
	 * Difficulty: medium
	 */
	public boolean hasPath_bfs(int[][] maze, int[] start, int[] destination) {
		int m = maze.length, n = maze[0].length;
		boolean[][] visited = new boolean[m][n];
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(start);
        visited[start[0]][start[1]] = true; 
        
		while (!queue.isEmpty()) {
			int[] position = queue.poll();
			int row = position[0], col = position[1];
			if (row == destination[0] && col == destination[1]) {
				return true;
			}
			
			for (int[] direction : DIRECTIONS) {
				int x = row + direction[0], y = col + direction[1];
				
				// rolling in the same direction until hits a wall or out of boundary 
				while (x >= 0 && x < m && y >= 0 && y < n && maze[x][y] == 0) {
					x += direction[0];
					y += direction[1];
				}
				
				// back off to the last valid position, this will be a new start point
				x -= direction[0];
				y -= direction[1];
				
				if (!visited[x][y]) { // the start point has not been checked already
					visited[x][y] = true;
					queue.offer(new int[] {x, y});
				}
			}	
		}
        
		return false;
    }
	
	public boolean hasPath_dfs(int[][] maze, int[] start, int[] destination) {
		boolean[][] visited = new boolean[maze.length][maze[0].length];
		return dfs(maze, visited, start[0], start[1], destination[0], destination[1]);
	}
	
	private boolean dfs(int[][] maze, boolean[][] visited, int startx, int starty, int destx, int desty) {
		if (startx == destx && starty == desty) return true;
		if (visited[startx][starty]) return false; // if this start point has been checked, it means dest cannot be reached from here
		
		visited[startx][starty] = true; // Keep track of places that you already started at in case you roll back to that point.
		for (int[] direction : DIRECTIONS) {
			int x = startx + direction[0], y = starty + direction[1];
			// Search in the four possible directions when coming to a stopping point (i.e. a new starting point).
			while (x >= 0 && x < maze.length && y >= 0 && y < maze[0].length && maze[x][y] == 0) {
				x += direction[0];
				y += direction[1];
			}
			
			x -= direction[0];
			y -= direction[1];
			
			if (dfs(maze, visited, x, y, destx, desty)) return true;
		}
		
		
		return false;
	}
	
	
	/*
	 * Given the ball's start position, the destination and the maze, find the shortest distance for the ball to stop at the destination. 
	 * The distance is defined by the number of empty spaces traveled by the ball from the start position (excluded) to the destination (included). 
	 * If the ball cannot stop at the destination, return -1.
	 */
	public int shortestDistance(int[][] maze, int[] start, int[] destination) {
        
		return -1;
    }

	public static void main(String[] args) {
		Maze m = new Maze();
		int[][] maze = {
				{0, 0, 1, 0, 0},
				{0, 0, 0, 0, 0},
				{0, 0, 0, 1, 0},
				{1, 1, 0, 1, 1},
				{0, 0, 0, 0, 0}
		};
		//int[] start = {0, 4}, destination = {4, 4};
		int[] start = {0, 4}, destination = {1, 2}; // true, left->down->left->down->right->up
		System.out.println(m.hasPath_bfs(maze, start, destination));
	}
}
