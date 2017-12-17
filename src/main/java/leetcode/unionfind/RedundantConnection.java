package leetcode.unionfind;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RedundantConnection {
    /*
     * LEETCODE 684 In this problem, a tree is an undirected graph that is
     * connected and has no cycles. The given input is a graph that started as a
     * tree with N nodes (with distinct values 1, 2, ..., N), with one
     * additional edge added. The added edge has two different vertices chosen
     * from 1 to N, and was not an edge that already existed. The resulting
     * graph is given as a 2D-array of edges. Each element of edges is a pair
     * [u, v] with u < v, that represents an undirected edge connecting nodes u
     * and v. Return an edge that can be removed so that the resulting graph is
     * a tree of N nodes. If there are multiple answers, return the answer that
     * occurs last in the given 2D-array. The answer edge [u, v] should be in
     * the same format, with u < v. Example 1: Input: [[1,2], [1,3], [2,3]]
     * Output: [2,3] Explanation: The given undirected graph will be like this:
     * 1 / \ 2 - 3 Example 2: Input: [[1,2], [2,3], [3,4], [1,4], [1,5]] Output:
     * [1,4] Explanation: The given undirected graph will be like this: 5 - 1 -
     * 2 | | 4 - 3 Note: - The size of the input 2D-array will be between 3 and
     * 1000. - Every integer represented in the 2D-array will be between 1 and
     * N, where N is the size of the input array.
     * 
     * Company: Google Difficulty: medium Similar Questions: 721(AccountMerge)
     */
    public int[] findRedundantConnection_dfs(int[][] edges) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int i = 1; i <= edges.length; i++) {
            graph.put(i, new ArrayList<>());
        }
        for (int[] edge : edges) {
            if (hasCycle(graph, edge[0], edge[1], 0))
                return edge;
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }

        return null;
    }

    private boolean hasCycle(Map<Integer, List<Integer>> graph, int start, int end, int prev) {
        if (graph.get(start).contains(end))
            return true; // end has been visited via start already
        for (int neighbor : graph.get(start)) {
            if (neighbor == prev)
                continue; // skip if this is the node leads to start
            if (hasCycle(graph, neighbor, end, start))
                return true;

        }
        return false;
    }

    public int[] findRedundantConnection_unionFind(int[][] edges) {
        int[] result = new int[2];

        UnionFind uf = new UnionFind(edges.length + 1);
        for (int[] edge : edges) {
            if (!uf.union(edge[0], edge[1])) {
                result = edge;
                break;
            }
        }
        return result;
    }

    private static class UnionFind {
        private int[] ids;

        UnionFind(int n) {
            ids = new int[n + 1];
            for (int i = 1; i <= n; i++)
                ids[i] = i;
        }

        int find(int p) {
            while (p != ids[p])
                p = ids[p];
            return p;
        }

        boolean union(int p, int q) {
            int i = find(p), j = find(q);
            if (i == j)
                return false;
            ids[i] = j;
            return true;
        }
    }

    public static void main(String[] args) {
        RedundantConnection rc = new RedundantConnection();
        // int[][] edges = {{1,2}, {2,3}, {3,4}, {1,4}, {1,5}};
        // int[] result = rc.findRedundantConnection_unionFind(edges);

        int[][] edges = { { 1, 2 }, { 1, 3 }, { 2, 3 } };
        int[] result = rc.findRedundantConnection_dfs(edges);
        System.out.println(result[0] + ", " + result[1]);
    }

}
