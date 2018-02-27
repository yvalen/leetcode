package company.airbnb;

import static org.junit.Assert.assertArrayEquals;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/*
 * Given: An array of strings where L indicates land and W indicates water, 
 * and a coordinate marking a starting point in the middle of the ocean.
 * Challenge: Find and mark the ocean in the map by changing appropriate Ws 
 * to Os. An ocean coordinate is defined to be the initial coordinate if a W, 
 * and any coordinate directly adjacent to any other ocean coordinate.
 * void findOcean(String[] map, int row, int column);
 * String[] map = new String[] 
 * { 
 *  "WWWLLLW", 
 *  "WWLLLWW", 
 *  "WLLLLWW"
 *  }; 
 *  printMap(map);
 *  STDOUT: 
 *  WWWLLLW 
 *  WWLLLWW 
 *  WLLLLWW
 *  findOcean(map, 0, 1); 
 *  printMap(map);
 * STDOUT:
 *  OOOLLLW
 *  OOLLLWW
 *  OLLLLWW
 */
public class FindOcean {
    private static final int[][] DIRS = {{ 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 }};
    
    public static void findOcean(String[] map, int row, int col) {
        int m = map.length, n = map[0].length();
        char[][] grid = new char[m][n];
        for (int i = 0; i < m; i++) {
            grid[i] = map[i].toCharArray();
        }
        
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[] {row, col});
        grid[row][col] = 'O';
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            for (int[] dir : DIRS) {
                int x = current[0] + dir[0], y = current[1] + dir[1];
                if (x >= 0 && x < m && y >= 0 && y < n && grid[x][y] == 'W') {
                    grid[x][y] = 'O';
                    queue.offer(new int[] {x, y});
                }
            }
        }
        
        for (int i = 0; i < m; i++) {
            map[i] = new String(grid[i]);
        }
    } 

    public static void main(String[] args) {
        String[] map = {
            "WWWLLLW",
            "WWLLLWW", 
            "WLLLLWW"
        };
        
        findOcean(map, 0, 1);
        
        System.out.println(Arrays.toString(map));
    }
}
