package leetcode.bfsdfs;

import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/*
 * LEETCODE 675
 * You are asked to cut off trees in a forest for a golf event. The forest is represented as a non-negative 2D map, in this map:
 * - 0 represents the obstacle can't be reached.
 * - 1 represents the ground can be walked through.
 * - The place with number bigger than 1 represents a tree can be walked through, and this positive number represents the tree's height.
 * You are asked to cut off all the trees in this forest in the order of tree's height - always cut off the tree with lowest height first. 
 * And after cutting, the original place has the tree will become a grass (value 1). You will start from the point (0, 0) and you should 
 * output the minimum steps you need to walk to cut off all the trees. If you can't cut off all the trees, output -1 in that situation. 
 * You are guaranteed that no two trees have the same height and there is at least one tree needs to be cut off.
 * Example 1:
 * Input: 
 * [
 * 	[1,2,3],
 * 	[0,0,4],
 * 	[7,6,5]
 * ]
 * Output: 6
 * Example 2:
 * Input: 
 * [
 * 	[1,2,3],
 * 	[0,0,0],
 * 	[7,6,5]
 * ]
 * Output: -1
 * Example 3:
 * Input: 
 * [
 * 	[2,3,4],
 * 	[0,0,5],
 * 	[8,7,6]
 * ]
 * Output: 6
 * Explanation: You started from the point (0,0) and you can cut off the tree in (0,0) directly without walking.
 * Hint: size of the given matrix will not exceed 50x50. 
 * 
 * Amazon
 * Difficulty: hard
 */
public class CutOffTreeForGolfEvent {
    private static final int[][] DIRS = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

    public int cutOffTree(List<List<Integer>> forest) {
        if (forest == null || forest.isEmpty())
            return 0;
        int m = forest.size(), n = forest.get(0).size();
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[2] - b[2]);
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (forest.get(i).get(j) > 1)
                    pq.offer(new int[] { i, j, forest.get(i).get(j) });
            }
        }

        int[] start = { 0, 0 };
        int minSteps = 0;
        while (!pq.isEmpty()) {
            int[] current = pq.poll();

            // calculate the min step from start to current
            int currentSteps = minSteps(forest, start, current, m, n);
            if (currentSteps == -1) {
                return -1; // cannot reach current from start
            }

            minSteps += currentSteps;
            // reset start to current
            start[0] = current[0];
            start[1] = current[1];
        }

        return minSteps;
    }

    // use BFS to calculate minimum steps to reach end from start
    private int minSteps(List<List<Integer>> forest, int[] start, int[] end, int m, int n) {
        boolean[][] visited = new boolean[m][n];
        visited[start[0]][start[1]] = true;
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(start);
        int steps = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                int[] current = queue.poll();
                if (current[0] == end[0] && current[1] == end[1])
                    return steps;

                for (int[] dir : DIRS) {
                    int x = current[0] + dir[0], y = current[1] + dir[1];
                    if (x >= 0 && x < m && y >= 0 && y < n && !visited[x][y] && forest.get(x).get(y) >= 1) {
                        queue.offer(new int[] { x, y });
                        visited[x][y] = true;
                    }
                }
            }
            steps++;
        }
        return -1; // no path to end
    }
}
