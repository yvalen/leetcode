package leetcode.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
 * Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each edge is a pair of nodes), 
 * write a function to find the number of connected components in an undirected graph.
 * Example 1:
 * 0          3
 * |          |
 * 1 --- 2    4
 * Given n = 5 and edges = [[0, 1], [1, 2], [3, 4]], return 2.
 * Example 2:
 * 0           4
 * |           |
 * 1 --- 2 --- 3
 * Given n = 5 and edges = [[0, 1], [1, 2], [2, 3], [3, 4]], return 1.
 * Note: You can assume that no duplicate edges will appear in edges. Since all edges are undirected, 
 * [0, 1] is the same as [1, 0] and thus will not appear together in edges.
 * https://discuss.leetcode.com/category/403/number-of-connected-components-in-an-undirected-graph
 * 
 * Company: Google, Twitter
 */
public class NumberOfConnectedComponentsInUndirectedGraph {
	//
	// Union Find
	//
	private static final class UnionFind {
		private int[] roots;
		private int count;
		
		UnionFind(int n) {
			roots = new int[n];
			for (int i = 0; i < n; i++) roots[i] = i;
			count = n;
		}
		
		int find(int p) {
			// path compression
			while (p != roots[p]) {
				roots[p] = roots[roots[p]];
				p = roots[p];
			}
			return p;
		}
		
		void union(int p, int q) {
			int i = find(p), j = find(q);
			if (i == j) return;
			roots[i] = j;
			count--;
		}
	}
	
	public int countComponents_unionFind(int n, int[][] edges) {
		if (n <= 0) return 0;
		
		// return n if no edge is provided
		if (edges == null || edges.length == 0) return n;
		
		UnionFind uf = new UnionFind(n);
		for (int[] edge : edges) {
			uf.union(edge[0], edge[1]);
		}
		return uf.count;
    }
	
	// 
	// BFS
	//
	public int countComponents_bfs(int n, int[][] edges) {
		if (n <= 0) return 0;
		
		// return n if no edge is provided
		if (edges == null || edges.length == 0) return n;
		
		// build graph
		List<List<Integer>>graph = new ArrayList<>(n);
		for (int i = 0; i < n; i++) graph.add(new ArrayList<>());
		for (int[] edge : edges) {
			graph.get(edge[0]).add(edge[1]);
			graph.get(edge[1]).add(edge[0]);
		}
		
		boolean[] visited = new boolean[n];
		Queue<Integer> queue = new LinkedList<>();
		int count = 0;
		for (int i = 0; i < n; i++) { // start from an arbitrary node
			if (visited[i]) continue;
			
			count++; 
			queue.offer(i);
			while (!queue.isEmpty()) {
				Integer current = queue.poll();
				visited[current] = true; 
				for (int neighbor : graph.get(current)) {
					if (!visited[neighbor]) {
						queue.offer(neighbor);
					}
				}
			}
		}
		
		return count;
    }

	// 
	// DFS
	//
	public int countComponents_dfs(int n, int[][] edges) {
		if (n <= 0) return 0;
		
		// return n if no edge is provided
		if (edges == null || edges.length == 0) return n;
		
		// build graph
		List<List<Integer>> graph = new ArrayList<>(n);
		for (int i = 0; i < n; i++) graph.add(new ArrayList<>());
		for (int[] edge : edges) {
			graph.get(edge[0]).add(edge[1]);
			graph.get(edge[1]).add(edge[0]);
		}
		
		boolean[] visited = new boolean[n];
		int count = 0;
		for (int i = 0; i < n; i++) { 
			if (visited[i]) continue;
			
			count++;
			dfs(graph, visited, i);
		}
		
		return count;
    }
	
	private void dfs(List<List<Integer>> graph, boolean[] visited, int start) {
		if (visited[start]) return;
		
		visited[start] = true;
		List<Integer> neighbors = graph.get(start);
		for (Integer neighbor : neighbors) {
			if (!visited[neighbor]) dfs(graph, visited, neighbor);
		}
	}
	
	public static void main(String[] args) {
		NumberOfConnectedComponentsInUndirectedGraph ncc = new NumberOfConnectedComponentsInUndirectedGraph();
		int n = 5;
		//int[][] edges = {{0,1},{1,2},{3,4}};
		int[][] edges = {{0,1},{1,2},{2,3},{3,4}};
		//int n = 1;
		//int[][] edges = {};
		//int n = 2;
		//int[][] edges = {{1,0}};
		System.out.println(ncc.countComponents_dfs(n, edges));
	}
}
