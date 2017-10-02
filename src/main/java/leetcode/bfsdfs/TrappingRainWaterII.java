package leetcode.bfsdfs;

import java.util.PriorityQueue;

/*
 * LEETCODE 407
 * Given an m x n matrix of positive integers representing the height of each unit cell in a 2D elevation map, 
 * compute the volume of water it is able to trap after raining.
 * Note: Both m and n are less than 110. The height of each unit cell is greater than 0 and is less than 20,000.
 * Example: given the following 3x6 height map:
 * [
 * 	[1,4,3,1,3,2],
 * 	[3,2,1,3,2,4],
 * 	[2,3,3,2,3,1]
 * ]
 * Return 4.
 * http://www.cnblogs.com/grandyang/p/5928987.html
 * 
 * Company: Google, Twitter
 * Difficulty: hard
 * Similar Questions, 42(TrappingRainWater)
 */
public class TrappingRainWaterII {
	private static final int[][] DIRS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	
	// Time complexity: O(m*n*log(m+n))  Space complexity: O(m*n)
	public int trapRainWater(int[][] heightMap) {
		if (heightMap == null || heightMap.length == 0) return 0;
		
		int m = heightMap.length, n = heightMap[0].length;
		PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> a[2] - b[2]);
		boolean[][] visited = new boolean[m][n];
		
		// add all boundary points to minHeap since they cannot trap water 
		for (int i = 0; i < m; i++) {
			minHeap.offer(new int[] {i, 0, heightMap[i][0]});
			minHeap.offer(new int[] {i, n-1, heightMap[i][n-1]});
			visited[i][0] = true;
			visited[i][n-1] = true;
		}
		for (int j = 1; j < n; j++) {
			minHeap.offer(new int[] {0, j, heightMap[0][j]});
			minHeap.offer(new int[] {n-1, j, heightMap[m-1][j]});
			visited[0][j] = true;
			visited[m-1][j] = true;
		}
		
        int result = 0, currentHeight = 0;
        while (!minHeap.isEmpty()) {
        	int[] current = minHeap.poll();
        	currentHeight = Math.max(currentHeight, current[2]);
        	for (int[] dir : DIRS) {
        		int x = current[0] + dir[0], y = current[1] + dir[1];
        		if (x >0 && x < m-1 && y > 0 && y < n-1 && !visited[x][y]) {
        			if (heightMap[x][y] < currentHeight) result += currentHeight - heightMap[x][y];
        			visited[x][y] = true;
        			minHeap.offer(new int[] {x, y, heightMap[x][y]});
        		}
        	}
        }
        
        return result;
    }
}
