package leetcode.matrix;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
 * A group of two or more people wants to meet and minimize the total travel distance. You are given a 2D grid of values 0 or 1, 
 * where each 1 marks the home of someone in the group. The distance is calculated using Manhattan Distance, where 
 * distance(p1, p2) = |p2.x - p1.x| + |p2.y - p1.y|.
 * For example, given three people living at (0,0), (0,4), and (2,2):
 * 1 - 0 - 0 - 0 - 1
 * |   |   |   |   |
 * 0 - 0 - 0 - 0 - 0
 * |   |   |   |   |
 * 0 - 0 - 1 - 0 - 0
 * The point (0,2) is an ideal meeting point, as the total travel distance of 2+2+2=6 is minimal. So return 6.
 * 
 * Company: Twitter
 * Difficulty: hard
 */
public class BestMeetingPoint {

    // Time Complexity: O(mn), Space complexity: O(mn)
    public int minTotalDistance_median(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        List<Integer> row = new ArrayList<>(m); // row number for all homes
        List<Integer> col = new ArrayList<>(n); // col number for all homes

        /*
         * // Time complexity: O(mnlogmn) due to sorting, all mn points are home
         * for (int i = 0 ; i < m; i++) { for (int j = 0; j <n; j++) { if
         * (grid[i][j] == 1) { row.add(i); col.add(j); } } }
         * Collections.sort(col); // no need to sort row since it is already
         * sorted
         */

        // collect row and col separately so that they are in sorted order
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    row.add(i);
                }
            }
        }
        for (int j = 0; j < n; j++) {
            for (int i = 0; i < m; i++) {
                if (grid[i][j] == 1) {
                    col.add(j);
                }
            }
        }

        return getMinDistance(row) + getMinDistance(col);
    }

    private int getMinDistance(List<Integer> list) {
        int median = list.get(list.size() / 2);
        int dist = 0;
        for (Integer l : list) {
            dist += Math.abs(l - median);
        }
        return dist;
    }

    // Time complexity: O(m^2n^2), space complexity: O(mn)
    public int minTotalDistance_bfs(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int minDistance = Integer.MAX_VALUE;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // evaluate all possible meeting points in the grid
                int dist = bfs(grid, i, j);
                minDistance = Math.min(minDistance, dist);
            }
        }
        return minDistance;
    }

    private static final int[][] DELTAS = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

    // bfs calculates the distance from all homes to the given cell
    private int bfs(int[][] grid, int row, int col) {
        int m = grid.length, n = grid[0].length;
        boolean[][] visited = new boolean[m][n];
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[] { row, col });
        visited[row][col] = true;
        int depth = 0, dist = 0;
        while (!queue.isEmpty()) {
            depth++;
            int size = queue.size();
            while (size-- > 0) {
                int[] pos = queue.poll();
                for (int[] delta : DELTAS) {
                    int x = pos[0] + delta[0], y = pos[1] + delta[1];
                    if (x < 0 || x >= m || y < 0 || y >= n || visited[x][y]) {
                        continue;
                    }

                    if (grid[x][y] == 1) {
                        dist += depth;
                    }

                    visited[x][y] = true;
                    queue.offer(new int[] { x, y });
                }
            }
        }
        return dist;
    }

    // Time complexity: O(m^2n^2), space complexity: O(1)
    public int minTotalDistance_manhattanDistance(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int minDistance = Integer.MAX_VALUE;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // calculate the Manhattan distance from this cell to all homes
                int dist = manhattanDistance(grid, i, j);
                minDistance = Math.min(minDistance, dist);
            }
        }
        return minDistance;
    }

    private int manhattanDistance(int[][] grid, int row, int col) {
        int dist = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    dist += Math.abs(i - row) + Math.abs(j - col);
                }
            }
        }
        return dist;
    }

    public static void main(String[] args) {
        BestMeetingPoint bmp = new BestMeetingPoint();
        /*
         * int[][] grid = { {1, 0, 0, 0, 1}, {0, 0, 0, 0, 0}, {0, 0, 1, 0, 0} };
         */
        int[][] grid = { { 1, 1 } };

        System.out.println(bmp.minTotalDistance_bfs(grid));
    }
}
