package leetcode.graph;

/*
 * LEETCDE 785
 * Given a graph, return true if and only if it is bipartite. Recall that a graph 
 * is bipartite if we can split it's set of nodes into two independent subsets A 
 * and B such that every edge in the graph has one node in A and another node in B.
 * The graph is given in the following form: graph[i] is a list of indexes j for 
 * which the edge between nodes i and j exists.  Each node is an integer between 0 
 * and graph.length - 1.  There are no self edges or parallel edges: graph[i] does 
 * not contain i, and it doesn't contain any element twice.
 * Example 1:
 * Input: [[1,3], [0,2], [1,3], [0,2]]
 * Output: true
 * Explanation: The graph looks like this:
 * 0----1
 * |    |
 * |    |
 * 3----2
 * We can divide the vertices into two groups: {0, 2} and {1, 3}.
 * Example 2:
 * Input: [[1,2,3], [0,2], [0,1,3], [0,2]]
 * Output: false
 * Explanation: 
 * The graph looks like this:
 * 0----1
 * | \  |
 * |  \ |
 * 3----2
 * We cannot find a way to divide the set of nodes into two independent subsets.
 * Note:
 * - graph will have length in range [1, 100].
 * - graph[i] will contain integers in range [0, graph.length - 1].
 * - graph[i] will not contain i or duplicate values.
 * 
 * Company: Facebook
 * Difficulty: medium
 */
public class BipartiteGraph {
    public boolean isBipartite(int[][] graph) {
        int n = graph.length;
        boolean[] visited = new boolean[n];
        boolean[] color = new boolean[n];
        for (int v = 0; v < n; v++) {
            if (!visited[v]) {
                if (!dfs(graph, v, visited, color)) {
                    return false;
                }
            }
        }
        return true;
    }
    
    private boolean dfs(int[][] graph, int v, boolean[] visited, boolean[] color) {
        visited[v] = true;
        for (int w : graph[v]) {
            if (!visited[w]) {
                color[w] = !color[v];
                if (!dfs(graph, w, visited, color)) return false;
            }
            else if (color[w] == color[v]) {
                return false;
            }
        }
        return true;
    }
    
    public static void main(String[] args) {
        BipartiteGraph bg = new BipartiteGraph();
        int[][] graph = {{1,3},{0,2},{1,3},{0,2}};
        System.out.println(bg.isBipartite(graph));
    }

}
