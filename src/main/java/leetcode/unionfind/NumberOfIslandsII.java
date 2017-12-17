package leetcode.unionfind;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * LEETCODE 305
 * A 2d grid map of m rows and n columns is initially filled with water. We may perform an addLand operation which 
 * turns the water at position (row, col) into a land. Given a list of positions to operate, count the number of islands 
 * after each addLand operation. An island is surrounded by water and is formed by connecting adjacent lands horizontally 
 * or vertically. You may assume all four edges of the grid are all surrounded by water.
 * Example: given m = 3, n = 3, positions = [[0,0], [0,1], [1,2], [2,1]].
 * Initially, the 2d grid grid is filled with water. (Assume 0 represents water and 1 represents land).
 * 0 0 0
 * 0 0 0
 * 0 0 0
 * Operation #1: addLand(0, 0) turns the water at grid[0][0] into a land.
 * 
 * 1 0 0
 * 0 0 0   Number of islands = 1
 * 0 0 0
 * Operation #2: addLand(0, 1) turns the water at grid[0][1] into a land.
 * 
 * 1 1 0
 * 0 0 0   Number of islands = 1
 * 0 0 0
 * Operation #3: addLand(1, 2) turns the water at grid[1][2] into a land.
 * 
 * 1 1 0
 * 0 0 1   Number of islands = 2
 * 0 0 0
 * Operation #4: addLand(2, 1) turns the water at grid[2][1] into a land.
 * 
 * 1 1 0
 * 0 0 1   Number of islands = 3
 * 0 1 0
 * We return the result as an array: [1, 1, 2, 3]
 * Challenge: can you do it in time complexity O(k log mn), where k is the length of the positions?
 * 
 * Company: Google
 * Difficult: hard
 * Similar Questions: 200(NumberOfIslands)
 */
public class NumberOfIslandsII {
    private static final class UnionFind {
        private int[] ids;

        private int count;
        private int m;
        private int n;

        UnionFind(int m, int n) {
            ids = new int[m * n];
            Arrays.fill(ids, -1);
            this.m = m;
            this.n = n;
        }

        void union(int p, int q) {
            int i = find(p), j = find(q);
            if (i == j)
                return;
            ids[i] = j;
            count--;
        }

        int find(int p) {
            while (p != ids[p] && ids[p] != -1)
                p = ids[p];
            return p;
        }

        void add(int x, int y) {
            int id = x * n + y;
            ids[id] = id;
            count++;
        }

        int getCount() {
            return this.count;
        }

        int getId(int x, int y) {
            return ids[x * n + y];
        }
    }

    private static final int[][] OFFSETS = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

    public List<Integer> numIslands2(int m, int n, int[][] positions) {
        List<Integer> result = new ArrayList<>();

        if (m <= 0 || n <= 0 || positions == null || positions.length == 0)
            return result;

        // UnionFind uf = new UnionFind(m, n);
        WeightedUnionFind wuf = new WeightedUnionFind(m, n);
        for (int[] position : positions) {
            int x = position[0], y = position[1];
            // uf.add(x, y);
            wuf.add(x, y);
            for (int[] offset : OFFSETS) {
                int i = x + offset[0], j = y + offset[1];
                if (i >= 0 && i < m && j >= 0 && j < n && wuf.getId(i, j) != -1) {
                    // uf.union(x*n+y, i*n+j);
                    wuf.union(x * n + y, i * n + j);
                }
            }
            // result.add(uf.getCount());
            result.add(wuf.getCount());
            System.out.println(result);
        }

        return result;
    }

    private static final class WeightedUnionFind {
        private int[] ids;
        private int[] sizes;

        private int count;
        private int m;
        private int n;

        WeightedUnionFind(int m, int n) {
            ids = new int[m * n];
            Arrays.fill(ids, -1);
            sizes = new int[m * n];
            Arrays.fill(sizes, 0);
            this.m = m;
            this.n = n;
        }

        void union(int p, int q) {
            int i = find(p), j = find(q);
            if (i == j)
                return;
            if (sizes[i] > sizes[j]) {
                ids[j] = i;
                sizes[i] += sizes[j];
            } else {
                ids[i] = j;
                sizes[j] += sizes[i];
            }
            count--;
        }

        int find(int p) {
            while (p != ids[p] && ids[p] != -1)
                p = ids[p];
            return p;
        }

        void add(int x, int y) {
            int id = x * n + y;
            ids[id] = id;
            sizes[id] = 1;
            count++;
        }

        int getCount() {
            return this.count;
        }

        int getId(int x, int y) {
            return ids[x * n + y];
        }
    }

    public static void main(String[] args) {
        NumberOfIslandsII ni2 = new NumberOfIslandsII();
        int m = 3, n = 3;
        int[][] positions = { { 0, 0 }, { 0, 1 }, { 1, 2 }, { 2, 1 } };
        System.out.println(ni2.numIslands2(m, n, positions));
    }

}
