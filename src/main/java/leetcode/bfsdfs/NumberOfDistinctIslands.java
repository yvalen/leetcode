package leetcode.bfsdfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

public class NumberOfDistinctIslands {
    private static final int[][] DIRS = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

    /*
     * LEETCODE 694 
     * Given a non-empty 2D array grid of 0's and 1's, an island is a group of 1's 
     * (representing land) connected 4-directionally (horizontal or vertical.) 
     * You may assume all four edges of the grid are surrounded by water. Count the 
     * number of distinct islands. An island is considered to be the same as another 
     * if and only if one island can be translated (and not rotated or reflected) to 
     * equal the other. 
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
     *  1
     * 11 
     * are considered different island shapes, because we do not consider reflection and 
     * rotation. 
     * Note: The length of each dimension in the given grid does not exceed 50.
     * 
     * Company: Amazon 
     * Difficulty: medium 
     * Similar Questions: 200(NumberOfIslands)
     */
    public int numDistinctIslands(int[][] grid) {
        if (grid == null || grid.length == 0)
            return 0;

        // use a set to de-dup islands, set elements are offset of cells in an
        // island with respect to the starting cell. 
        // list with same items in same order are equal lists.
        //Set<List<List<Integer>>> islands = new HashSet<>();
        Set<List<Integer>> islands = new HashSet<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    //List<List<Integer>> island = new ArrayList<>();
                    List<Integer> island = new ArrayList<>();
                    dfs(grid, i, j, i, j, island);
                    islands.add(island);
                }
            }
        }

        return islands.size();
    }

    private void dfs(int[][] grid, int startx, int starty, int row, int col, List<Integer> island) {
    //private void dfs(int[][] grid, int startx, int starty, int row, int col, List<List<Integer>> island) {
        grid[row][col] = 0;
        //island.add(Arrays.asList(row - startx, col - starty));
        // col-starty>=0 is not guaranteed, it could be anywhere in the interval
        // [-numCols+1, numCols-1], row-startx>=0 is guarantted as we traverse 
        // from top to bottom, left to right
        island.add((row-startx)*2*grid[0].length + (col-starty));
        for (int[] dir : DIRS) {
            int x = row + dir[0], y = col + dir[1];
            if (x >= 0 && x < grid.length && y >= 0 && y < grid[0].length && grid[x][y] == 1) {
                dfs(grid, startx, starty, x, y, island);
            }
        }
    }

    /*
     * LEETCODE 711 
     * Given a non-empty 2D array grid of 0's and 1's, an island is a group of 1's 
     * (representing land) connected 4-directionally (horizontal or vertical.) 
     * You may assume all four edges of the grid are surrounded by water. Count the 
     * number of distinct islands. An island is considered to be the same as another 
     * if they have the same shape, or have the same shape after rotation (90, 180, 
     * or 270 degrees only) or reflection (left/right direction or up/down direction). 
     * Example 1: 
     * 11000 
     * 10000 
     * 00001
     * 00011 
     * Given the above grid map, return 1. 
     * Notice that: 
     * 11 
     * 1 
     * and 
     *  1 
     * 11 
     * are considered same island shapes. Because if we make a 180 degrees clockwise
     * rotation on the first island, then two islands will have the same shapes.
     * Example 2: 
     * 11100 
     * 10001 
     * 01001 
     * 01110 
     * Given the above grid map, return 2.
     * Here are the two distinct islands: 
     * 111 
     * 1 
     * and
     * 1 
     * 1 
     * Notice that: 
     * 111 
     * 1 
     * and 
     * 1
     * 111 are considered same island shapes. Because if we flip the first array
     * in the up/down direction, then they have the same shapes. 
     * Note: The length of each dimension in the given grid does not exceed 50.
     * 
     * Company: Amazon 
     * Difficulty: medium 
     * Similar Questions: 694(Number of Distinct Islands)
     */
    public int numDistinctIslands2(int[][] grid) {
        if (grid == null || grid.length == 0)
            return 0;

        Set<String> islands = new HashSet<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    List<int[]> island = new ArrayList<>();
                    dfs2(grid, i, j, i, j, island);
                    islands.add(normalize(island, grid.length+grid[0].length));
                }
            }
        }

        return islands.size();
    }
    
    private void dfs2(int[][] grid, int startRow, int startCol, int row, int col, List<int[]> cells) {
        grid[row][col] = 0;
        cells.add(new int[] {row, col});
        for (int[] dir : DIRS) {
            int x = row + dir[0], y = col + dir[1];
            if (x >= 0 && x < grid.length && y >= 0 && y < grid[0].length && grid[x][y] == 1) {
                dfs2(grid, startRow, startCol, x, y, cells);
            }
        }
    }
    
    private String normalize(List<int[]> cells, int factor) {
        String result = "";
        int[] xs = new int[cells.size()];
        int[] ys = new int[cells.size()];
        int[] out = new int[cells.size()];
        
        // there are 8 possible rotations and reflections
        for (int c = 0 ; c < 8; c++) {
            int idx = 0;
            // perform the transformation for each cell
            for (int[] cell : cells) {
                int x = cell[0], y = cell[1];
                //x y, x -y, -x y, -x -y
                //y x, y -x, -y x, -y -x
                xs[idx] = c<=1 ? x : c<=3 ? -x : c<=5 ? y : -y;
                ys[idx++] = c<=3 ? (c%2==0 ? y : -y) : (c%2==0 ? x : -x);
            }
            
            System.out.println("c=" + c + " xs=" + Arrays.toString(xs));
            System.out.println("c=" + c + " ys=" + Arrays.toString(ys));
            //System.out.println("---");
            int minX = xs[0], minY = ys[0];
            for (int x : xs) minX = Math.min(minX, x);
            for (int y : ys) minY = Math.min(minY, y);
            
            for (int i = 0; i < cells.size(); i++) {
                out[i] = (xs[i]-minX) * factor + (ys[i]-minY);
            }
            
            Arrays.sort(out);
            System.out.println("minX="+minX+" minY="+minY + " out=" + Arrays.toString(out));
            System.out.println("---");
            
            String candidate = Arrays.toString(out);
            if (result.compareTo(candidate) < 0) result = candidate;
        }
        System.out.println("ans="+result);
        return result;
    }

    
    
    public static void main(String[] args) {
        NumberOfDistinctIslands ndi = new NumberOfDistinctIslands();
        //int[][] grid = { { 1, 1, 0, 0, 0 }, { 1, 1, 0, 0, 0 }, { 0, 0, 0, 1, 1 }, { 0, 0, 0, 1, 1 } };
        //System.out.println(ndi.numDistinctIslands(grid));
        
        int[][] grid = { { 1, 1, 0, 0, 0 }, { 1, 0, 0, 0, 0 }, { 0, 0, 0, 0, 1 }, { 0, 0, 0, 1, 1 } };
        System.out.println(ndi.numDistinctIslands2(grid));
    }
}
