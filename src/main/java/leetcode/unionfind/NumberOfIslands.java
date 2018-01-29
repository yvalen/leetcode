package leetcode.unionfind;

/*
 * LEETCODE 200
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
 * 
 * Company: Amazon, Microsoft, Google, Facebook, Zenefits
 */
public class NumberOfIslands {
    private static class UnionFind {
        private int[] ids;
        private int count;

        UnionFind(char[][] grid) {
            int m = grid.length, n = grid[0].length;
            ids = new int[m * n];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (grid[i][j] == '1') {
                        count++;
                        // use row-major ordering to store 2-D 
                        // array into 1-D array
                        int id = i * n + j;
                        ids[id] = id;
                    }
                }
            }
        }

        void union(int p, int q) {
            int i = find(p); // need to use find here to get the root
            int j = find(q);
            if (i == j)
                return;

            ids[i] = j;
            count--;
        }

        int find(int p) {
            while (p != ids[p])
                p = ids[p];
            return p;
        }

        int getCount() {
            return this.count;
        }

    }

    // only need to go two directions. otherwise union will be performed
    // twice than it should be
    private static final int[][] OFFSETS = { { 1, 0 }, { 0, 1 } };
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0)
            return 0;

        UnionFind uf = new UnionFind(grid);
        int m = grid.length, n = grid[0].length;
        System.out.println("count=" + uf.count);
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1') {
                    int currentId = i * n + j;
                    for (int[] offset : OFFSETS) {
                        int x = i + offset[0], y = j + offset[1];
                        int neighborId = x * n + y;
                        if (x >= 0 && x < m && y >= 0 && y < n && grid[x][y] == '1') {
                            // should use neighborId as the 1st argument as the 2nd is the parent
                            uf.union(neighborId, currentId);
                            System.out.println("union currentId=" + currentId + " neighborId=" + neighborId + 
                                    " to=" + uf.ids[currentId] + " count=" + uf.count);
                        }
                    }
                }
            }
        }
        System.out.println("count=" + uf.count);
        return uf.getCount();
    }

    public static void main(String[] args) {
        NumberOfIslands ni = new NumberOfIslands();
        /*
         * char[][] grid = { {'1', '1', '0', '0', '0'}, {'1', '1', '0', '0',
         * '0'}, {'0', '0', '1', '0', '0'}, {'0', '0', '0', '1', '1'} };
         */

        //char[][] grid = { { '1', '1', '1' }, { '0', '1', '0' }, { '1', '1', '1' }, };
        //char[][] grid = { { '1', '1', '1' }, { '1', '0', '1' }, { '1', '1', '1' }, };
        
        char[][] grid = {
                {'1','1','1','1','1','1','1'},
                {'0','0','0','0','0','0','1'},
                {'1','1','1','1','1','0','1'},
                {'1','0','0','0','1','0','1'},
                {'1','0','1','0','1','0','1'},
                {'1','0','1','1','1','0','1'},
                {'1','1','1','1','1','1','1'}
        };

        System.out.println(ni.numIslands(grid));
    }

}
