package leetcode.bfsdfs;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;


public class Maze {
	private static final int[][] DIRECTIONS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	
	/*
	 * LEETCODE 490
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
	 * LEETCODE 505
	 * Given the ball's start position, the destination and the maze, find the shortest distance for the ball to stop at the destination. 
	 * The distance is defined by the number of empty spaces traveled by the ball from the start position (excluded) to the destination (included). 
	 * If the ball cannot stop at the destination, return -1.
	 * Example 1
	 * Input 1: a maze represented by a 2D array
	 * 0 0 1 0 0
	 * 0 0 0 0 0
	 * 0 0 0 1 0
	 * 1 1 0 1 1
	 * 0 0 0 0 0
	 * Input 2: start coordinate (rowStart, colStart) = (0, 4)
	 * Input 3: destination coordinate (rowDest, colDest) = (4, 4)
	 * Output: 12
	 * Explanation: One possible way is : One shortest way is : left -> down -> left -> down -> right -> down -> right.
	 * The total distance is 1 + 1 + 3 + 1 + 2 + 2 + 2 = 12..
	 * Example 2
	 * Input 1: a maze represented by a 2D array
	 * 0 0 1 0 0
	 * 0 0 0 0 0
	 * 0 0 0 1 0
	 * 1 1 0 1 1
	 * 0 0 0 0 0
	 * Input 2: start coordinate (rowStart, colStart) = (0, 4)
	 * Input 3: destination coordinate (rowDest, colDest) = (3, 2)
	 * Output: -1
	 * Explanation: There is no way for the ball to stop at the destination.
	 * 
	 * Company: Google
	 * Difficulty: medium
	 */
	
	// Time complexity: O(m*n*max(m,n)) for every node, we can travel up to a maximum depth of max(m,n) in any direction.
	// Space complexity: O(mn)
	public int shortestDistance_dfs(int[][] maze, int[] start, int[] destination) {
		int m = maze.length, n = maze[0].length;
        int[][] distances = new int[m][n];
        for (int i = 0; i < m; i++) Arrays.fill(distances[i], Integer.MAX_VALUE); 
        distances[start[0]][start[1]] = 0; // need to set the distance of starting point to 0
        shortestDistance_dfs_helper(maze, distances, start);
        int x = destination[0], y = destination[1];
        return distances[x][y] == Integer.MAX_VALUE ? -1 : distances[x][y];
    }
	
	private void shortestDistance_dfs_helper(int[][] maze, int[][] distances, int[] start) {
		for (int[] dir : DIRECTIONS) {
			int x = start[0] + dir[0], y = start[1] + dir[1], count = 0;
			while (x >=0 && x < maze.length && y >=0 && y < maze[0].length && maze[x][y] == 0) {
				x += dir[0];
				y += dir[1];
				count++;
			}
			x -= dir[0];
			y -= dir[1];
			
			if (distances[start[0]][start[1]] + count < distances[x][y]) {
				distances[x][y] = distances[start[0]][start[1]] + count;
				shortestDistance_dfs_helper(maze, distances, new int[] {x, y});
			}
		}
	}
	
	public int shortestDistance_bfs(int[][] maze, int[] start, int[] destination) {
		int m = maze.length, n = maze[0].length;
        int[][] distances = new int[m][n];
        for (int i = 0; i < m; i++) Arrays.fill(distances[i], Integer.MAX_VALUE); 
        distances[start[0]][start[1]] = 0; // need to set the distance of starting point to 0
        
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(start);
        while (!queue.isEmpty()) {
        	int[] current = queue.poll();
        	for (int[] dir : DIRECTIONS) {
        		int x = current[0] + dir[0], y = current[1] + dir[1];
        		int count = 0;
        		while (x >=0 && x < m && y >=0 && y < n && maze[x][y] == 0) {
        			x += dir[0];
        			y += dir[1];
        			count++;

        		}
        		x -= dir[0];
        		y -= dir[1];

        		if (distances[current[0]][current[1]] + count < distances[x][y] ) { // only add to queue if the path is shorter
        			queue.offer(new int[] {x, y});
        			distances[x][y] = distances[current[0]][current[1]]  + count;
        		}
        	}
        }
        
        int x = destination[0], y = destination[1];
        return distances[x][y] == Integer.MAX_VALUE ? -1 : distances[x][y];
    }
	
	
	
	/*
	 * LEETCODE 499
	 * There is a ball in a maze with empty spaces and walls. The ball can go through empty spaces by rolling up (u), down (d), left (l) or right (r), 
	 * but it won't stop rolling until hitting a wall. When the ball stops, it could choose the next direction. There is also a hole in this maze. 
	 * The ball will drop into the hole if it rolls on to the hole.
	 * Given the ball position, the hole position and the maze, find out how the ball could drop into the hole by moving the shortest distance. 
	 * The distance is defined by the number of empty spaces traveled by the ball from the start position (excluded) to the hole (included). 
	 * Output the moving directions by using 'u', 'd', 'l' and 'r'. Since there could be several different shortest ways, you should output the 
	 * lexicographically smallest way. If the ball cannot reach the hole, output "impossible".
	 * Example 1
	 * Input 1: a maze represented by a 2D array
	 * 0 0 0 0 0
	 * 1 1 0 0 1
	 * 0 0 0 0 0
	 * 0 1 0 0 1
	 * 0 1 0 0 0
	 * Input 2: ball coordinate (rowBall, colBall) = (4, 3)
	 * Input 3: hole coordinate (rowHole, colHole) = (0, 1)
	 * Output: "lul"
	 * Explanation: There are two shortest ways for the ball to drop into the hole. The first way is left -> up -> left, represented by "lul".
	 * The second way is up -> left, represented by 'ul'. Both ways have shortest distance 6, but the first way is lexicographically smaller 
	 * because 'l' < 'u'. So the output is "lul".
	 * Example 2
	 * Input 1: a maze represented by a 2D array
	 * 0 0 0 0 0
	 * 1 1 0 0 1
	 * 0 0 0 0 0
	 * 0 1 0 0 1
	 * 0 1 0 0 0
	 * Input 2: ball coordinate (rowBall, colBall) = (4, 3)
	 * Input 3: hole coordinate (rowHole, colHole) = (3, 0)
	 * Output: "impossible"
	 * Explanation: The ball cannot reach the hole.
	 * Note:
	 * - There is only one ball and one hole in the maze.
	 * - Both the ball and hole exist on an empty space, and they will not be at the same position initially.
	 * - The given maze does not contain border (like the red rectangle in the example pictures), but you could assume the border of the maze are 
	 * all walls. The maze contains at least 2 empty spaces, and the width and the height of the maze won't exceed 30.
	 * 
	 * Company: Google
	 * Difficulty: hard
	 */
	
	private static final Map<String, int[]> DIRS = new HashMap<>();
	static {
		DIRS.put("u", new int[]{-1, 0});
		DIRS.put("l", new int[]{0, -1});
		DIRS.put("r", new int[]{0, 1});
		DIRS.put("d", new int[]{1, 0});
	}
	
	private static class Point implements Comparable<Point>{
		int x, y, length;
		String path;
		
		Point(int x, int y, int length, String path) {
			this.x = x;
			this.y = y;
			this.length = length;
			this.path = path;
		}
		
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
			this.length = Integer.MAX_VALUE;
			this.path = "";
		}
		
		@Override
		public int compareTo(Point o) {
			return length == o.length ? path.compareTo(o.path) : length - o.length;
		}
	}
	
	public String findShortestWay(int[][] maze, int[] ball, int[] hole) {
		int m = maze.length, n = maze[0].length;
		Point[][] points = new Point[m][n];
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) points[i][j] = new Point(i, j);
		}
		
		PriorityQueue<Point> pq = new PriorityQueue<>();
		pq.offer(new Point(ball[0], ball[1], 0, ""));
		while (!pq.isEmpty()) {
			Point current = pq.poll();
			int row = current.x, col = current.y;
			if (current.compareTo(points[row][col]) > 0) {
				// there is a shorter path already, skip this one
				continue;
			}
			
			points[row][col] = current;
			for (Map.Entry<String, int[]> dir : DIRS.entrySet()) {
				int x = row, y = col;
				int count = current.length;
				while (x >= 0 && x < m && y > 0 && y < n  && (x != hole[0] || y != hole[1]) && maze[x][y] == 0) {
					count++;
					x += dir.getValue()[0];
					y += dir.getValue()[1];
				}
				
				if (x != hole[0] || y != hole[1]) {
					x -= dir.getValue()[0];
					y -= dir.getValue()[1];
					count--;
				}
				// we can enqueue 
				pq.offer(new Point(x, y, count, current.path + dir.getKey())); 
			}
		}
		
        return points[hole[0]][hole[1]].length == Integer.MAX_VALUE ? "impossible" : points[hole[0]][hole[1]].path;
    }
	
	private void updatePath(String[][] paths, int x, int y, String newPath) {
		String oldPath = paths[x][y];
		for (int i = 0; i < Math.min(oldPath.length(), newPath.length()); i++) {
			if (newPath.charAt(i) < oldPath.charAt(i)) {
				paths[x][y] = newPath;
				return;
			}
		}
	}

	public static void main(String[] args) {
		Maze m = new Maze();
		/*
		int[][] maze = {
				{0, 0, 1, 0, 0},
				{0, 0, 0, 0, 0},
				{0, 0, 0, 1, 0},
				{1, 1, 0, 1, 1},
				{0, 0, 0, 0, 0}
		};
		int[] start = {0, 4}, destination = {4, 4};
		//int[] start = {0, 4}, destination = {1, 2}; // true, left->down->left->down->right->up
		//System.out.println(m.hasPath_bfs(maze, start, destination));
		System.out.println(m.shortestDistance_bfs(maze, start, destination));
		*/
		
		int[][] maze = {
				{0, 0, 0, 0, 0},
				{1, 1, 0, 0, 1},
				{0, 0, 0, 0, 0},
				{0, 1, 0, 0, 1},
				{0, 1, 0, 0, 0}
		};
		int[] ball = {4, 3}, hole = {0, 1};
		System.out.println(m.findShortestWay(maze, ball, hole));
	}
}
