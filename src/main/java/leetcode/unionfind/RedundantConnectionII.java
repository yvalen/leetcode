package leetcode.unionfind;

import leetcode.array.ArrayUtil;

/*
 * LEETCODE 685 
 * In this problem, a rooted tree is a directed graph such that, there is exactly one node (the root) 
 * for which all other nodes are descendants of this node, plus every node has exactly one parent, 
 * except for the root node which has no parents.
 * The given input is a directed graph that started as a rooted tree with N nodes (with distinct values 
 * 1, 2, ..., N), with one additional directed edge added. The added edge has two different vertices 
 * chosen from 1 to N, and was not an edge that already existed.
 * The resulting graph is given as a 2D-array of edges. Each element of edges is a pair [u, v] that 
 * represents a directed edge connecting nodes u and v, where u is a parent of child v.
 * Return an edge that can be removed so that the resulting graph is a rooted tree of N nodes. 
 * If there are multiple answers, return the answer that occurs last in the given 2D-array.
 * Example 1:
 * Input: [[1,2], [1,3], [2,3]]
 * Output: [2,3]
 * Explanation: The given directed graph will be like this:
 *   1
 *  / \
 * v   v
 * 2-->3
 * Example 2:
 * Input: [[1,2], [2,3], [3,4], [4,1], [1,5]]
 * Output: [4,1]
 * Explanation: The given directed graph will be like this:
 * 5 <- 1 -> 2
 *      ^    |
 *      |    v
 *      4 <- 3
 * Note:
 * - The size of the input 2D-array will be between 3 and 1000.
 * - Every integer represented in the 2D-array will be between 1 and N, 
 * where N is the size of the input array.
 * 
 * Company: Google
 * Difficulty: hard
 * Similar Questions: 684(RedundantConnection)
 */
public class RedundantConnectionII {
    /*
     * There are two cases for the tree structure to be invalid.
     * 1) A node having two parents: including corner case: e.g. [[4,2],[1,5],[5,2],[5,3],[2,4]]
     * 2) A circle exists
     * If we can remove exactly 1 edge to achieve the tree structure, a single node can have at 
     * most two parents. 
     * 1) Check whether there is a node having two parents. If so, store them as candidates A and B, 
     * and set the second edge invalid. 
     * 2) Perform normal union find.
     * - If the tree is now valid, simply return candidate B
     * - else if candidates not existing, we find a circle, return current edge; 
     * - else remove candidate A instead of B.
     */
    public int[] findRedundantDirectedConnection(int[][] edges) {
        int n = edges.length;
        int[] parents = new int[n+1];
        
        // step 1, check whether there is a node with two parents
        int[] candA = null, candB = null;
        for (int[] edge : edges) {
            if (parents[edge[1]] == 0) {
                parents[edge[1]] = edge[0];
            }
            else {
                candA = new int[] {parents[edge[1]], edge[1]};
                candB = new int[] {edge[0], edge[1]};
                edge[1] = 0;
            }
        }
        
        // step 2, union find
        UnionFind uFind = new UnionFind(n+1);
        for (int[] edge : edges) {
            if (edge[1] == 0) continue;
            if (!uFind.union(edge[0], edge[1])) {
                return candA == null ? edge : candA;
            }
        }
        
        return candB;
    }
    
    private static class UnionFind {
        private int[] ids;
        
        UnionFind(int n) {
            ids = new int[n];
            for (int i = 0; i < n; i++) ids[i] = i;
        }
        
        int find(int p) {
            while (ids[p] != p) p = ids[p];
            return p;
        }
        
        boolean union(int p, int q) {
            int i = find(p), j = find(q);
            if (i == j) return false;
            ids[j] = i;
            return true;
        }
    }

    public static void main(String[] args) {
        RedundantConnectionII rc = new RedundantConnectionII();
        int[][] edges = {
                {1, 2},
                {1, 3},
                {2, 3}
        };
        ArrayUtil.printArray(rc.findRedundantDirectedConnection(edges));
    }
}
