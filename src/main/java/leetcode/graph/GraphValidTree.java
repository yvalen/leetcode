package leetcode.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
 * Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each edge is a pair of nodes), 
 * write a function to check whether these edges make up a valid tree.
 * For example:
 * Given n = 5 and edges = [[0, 1], [0, 2], [0, 3], [1, 4]], return true.
 * Given n = 5 and edges = [[0, 1], [1, 2], [2, 3], [1, 3], [1, 4]], return false.
 * Note: you can assume that no duplicate edges will appear in edges. Since all edges are undirected, [0, 1] is the 
 * same as [1, 0] and thus will not appear together in edges.
 * 
 * Company: Google, Facebook, Zenefits
 */
public class GraphValidTree {
	/*
	 * (1) A tree is an undirected graph in which any two vertices are connected by exactly one path.
	 * (2) Any connected graph who has n nodes with n-1 edges is a tree.
	 * (3) The degree of a vertex of a graph is the number of edges incident to the vertex.
	 * (4) A leaf is a vertex of degree 1. An internal vertex is a vertex of degree at least 2.
	 * (5) A path graph is a tree with two or more vertices that is not branched at all.
	 * (6) A tree is called a rooted tree if one vertex has been designated the root.
	 * (7) The height of a rooted tree is the number of edges on the longest downward path between root and a leaf.
	 */
	
	/*
	 * To tell whether a graph is a valid tree, we have to:
	 * - Make sure there is no cycle in the graph - this has to be a none-cyclic graph;
	 * - Make sure every node is reached - this has to be a connected graph;
	 */
	
	//
	// DFS
	//
	public boolean validTree_dfs(int n, int[][] edges) {
		if (edges.length != n-1) return false; // need to have n-1 edges to connect all vertices
		
		List<List<Integer>> graph = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			graph.add(new ArrayList<>());
		}
		for (int[] edge : edges) {
			// need to add to adjacent lists of both vertices because it is undirected graph
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
		return count == 1;
	}
	
	private void dfs(List<List<Integer>> graph, boolean[] visited, int start) {
		visited[start] = true;
		List<Integer> neighbors = graph.get(start);
		for (Integer neighbor : neighbors) {
			if (!visited[neighbor]) dfs(graph, visited, neighbor);
		}
	}
	
	//
	// BFS
	// 
	public boolean validTree_bfs(int n, int[][] edges) {
		if (edges.length != n-1) return false; // need to have n-1 edges to connect all vertices
		
		// build graph
		List<List<Integer>> graph = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			graph.add(new ArrayList<>());
		}
		for (int[] edge : edges) {
			graph.get(edge[0]).add(edge[1]);
			graph.get(edge[1]).add(edge[0]);
		}
		
		boolean[] visited = new boolean[n];
		Queue<Integer> queue = new LinkedList<>();
		int count = 0;
		for (int i = 0; i < n; i++) {
			if (visited[i]) continue;
			
			count++;
			queue.offer(i);
			while (!queue.isEmpty()) {
				int current = queue.poll();
				visited[current] = true; // mark current node as visited
				for (int neighbor : graph.get(current)) {
					if (!visited[neighbor]) {
						queue.offer(neighbor);
					}
				}
			}			
		}
		return count == 1;
    }
	
	//
	// Union Find
	// https://www.cs.princeton.edu/~rs/AlgsDS07/01UnionFind.pdf
	//
	private static class UnionFind {
		private int[] roots;
		private int count;
		
		UnionFind(int n) {
			roots = new int[n];
			for (int i = 0; i < n; i++) {
				roots[i] = i;
			}
			count = n;
		}
		
		int find(int p) {
			while (roots[p] != p) {
				roots[p] = roots[roots[p]]; // path compression
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
		
		int getCount() { return count;}
	}
	
	public boolean validTree_unionFind(int n, int[][] edges) {
		if (edges.length != n-1) return false; // need to have n-1 edges to connect all vertices
		
		UnionFind uf = new UnionFind(n);
		for (int[] edge : edges) {
			uf.union(edge[0], edge[1]);
		}
		
		return uf.getCount() == 1;
    }
	

	public static void main(String[] args) {
		GraphValidTree gvt = new GraphValidTree();
		int n = 5;
		int[][] edges = {{0,1},{0,2},{2,3},{2,4}};
		System.out.println(gvt.validTree_bfs(n, edges));
	}
	
}
