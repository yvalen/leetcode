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
 */
public class GraphValidTree {
	public boolean validTree(int n, int[][] edges) {
		if (n <= 0 || edges == null || edges.length == 0) return false;
		
		// build graph
		List<List<Integer>> graph = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			graph.add(new ArrayList<>());
		}
		int[] incomingEdgeCount = new int[n];
		for (int[] edge : edges) {
			graph.get(edge[0]).add(edge[1]);
			incomingEdgeCount[edge[1]]++;
		}
		
		Queue<Integer> queue = new LinkedList<>();
		for (int i = 0; i < n; i++) {
			if (incomingEdgeCount[i] == 0) queue.offer(i);
		}
		
		if (queue.size() > 1) { // more than one root
			return false;
		}
		
		int edgeCount = edges.length;
		while (!queue.isEmpty()) {
			int current = queue.poll();
			for (int neighbor : graph.get(current)) {
				if (--incomingEdgeCount[neighbor] == 0) {
					queue.offer(neighbor);
					edgeCount--;
				}
			}
		}
        
		return edgeCount == 0;
    }

	private void bfs() {
		Queue<Integer> queue = new LinkedList<>();
	}
	
	
	private List<List<Integer>> buildGraph(int n, int[][] edges, List<List<Integer>> grap) {
		List<List<Integer>> graph = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			graph.add(new ArrayList<>());
		}
		int[] incomingEdgeCount = new int[n];
		for (int[] edge : edges) {
			graph.get(edge[0]).add(edge[1]);
			incomingEdgeCount[edge[1]]++;
		}
		
		return graph;
	}
	
}
