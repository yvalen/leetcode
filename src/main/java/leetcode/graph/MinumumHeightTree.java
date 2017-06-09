package leetcode.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
 * For a undirected graph with tree characteristics, we can choose any node as the root. The result graph is then a rooted tree. 
 * Among all possible rooted trees, those with minimum height are called minimum height trees (MHTs). Given such a graph, write 
 * a function to find all the MHTs and return a list of their root labels.
 * Format: The graph contains n nodes which are labeled from 0 to n - 1. You will be given the number n and a list of undirected 
 * edges (each edge is a pair of labels). You can assume that no duplicate edges will appear in edges. Since all edges are undirected, 
 * [0, 1] is the same as [1, 0] and thus will not appear together in edges.
 * Example 1: Given n = 4, edges = [[1, 0], [1, 2], [1, 3]]
 *         0
 *         |
 *         1
 *        / \
 *       2   3
 *	return [1]
 * Example 2: Given n = 6, edges = [[0, 3], [1, 3], [2, 3], [4, 3], [5, 4]]
 * 		0  1  2
 *  	 \ | /
 *  	   3
 *  	   |
 *  	   4
 *  	   |
 *  	   5
 * 	return [3, 4]
 * Note:
 * (1) According to the definition of tree on Wikipedia: “a tree is an undirected graph in which any two vertices are connected by 
 * exactly one path. In other words, any connected graph without simple cycles is a tree.”
 * (2) The height of a rooted tree is the number of edges on the longest downward path between the root and a leaf. 
 * 
 * Company: Google
 */
public class MinumumHeightTree {
	
	// construct the graph, for each vertex as a root of the tree, calculate the height.
	// compare the height with the minimum height, update the minimum if necessary.
	// Time complexity: O(n^2), getHeight - O(n)
	public List<Integer> findMinHeightTrees_bruteForce(int n, int[][] edges) {
		//if (edges == null || edges.length == 0) return InStream.;
        
		// build graph
		List<List<Integer>> graph = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			graph.add(new ArrayList<>());
		}
		for (int[] edge : edges) {
			graph.get(edge[0]).add(edge[1]);
			graph.get(edge[1]).add(edge[0]);
		}
		
		List<Integer> result = new ArrayList<>();
		int minHeight = Integer.MAX_VALUE;
		boolean[] visited = new boolean[n];
		for (int i = 0; i < n; i++) {
			int height = getHeight(graph, visited, i);
			if (height < minHeight) {
				result.clear();
				result.add(i);
				minHeight = height;
			}
			else if (height == minHeight) {
				result.add(i);
			}
		}
		return result;
    }
	
	private int getHeight(List<List<Integer>> graph, boolean[] visited, int root) {
		visited[root] = true;
		int height = 0;
		for (int neighbor : graph.get(root)) {
			if (!visited[neighbor]) {
				// height of each individual tree is the length of the path to the deepest leaf
				height = Math.max(height, getHeight(graph, visited, neighbor));
			}
		}
		visited[root] = false; // backtrack for next iteration
		return height + 1;
	}
	
	// start from vertex with degree of 1 (leaf node). Remove the leaves, update the degrees of inner vertexes. 
	// Then remove the new leaves. Doing so level by level until there are 2 or 1 nodes left. 
	// Time complexity - O(n), space complexity - O(n)
	public List<Integer> findMinHeightTrees(int n, int[][] edges) {
		if (n <= 0) return Collections.emptyList();
		
		if (n == 1 && edges.length == 0) return Arrays.asList(0);
        
		// build graph
		List<List<Integer>> graph = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			graph.add(new ArrayList<>());
		}
		for (int[] edge : edges) {
			graph.get(edge[0]).add(edge[1]);
			graph.get(edge[1]).add(edge[0]);
		}
		
		// find leaf nodes
		List<Integer> leaves = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			if (graph.get(i).size() == 1) {
				leaves.add(i);
			}
		}
	
		while (n > 2) {
			n -= leaves.size();
			List<Integer> newLeaves = new ArrayList<>();
			for (Integer leaf : leaves) {
				// remove leaf node from its neighbor's adjacent list
				// leaf node only has one neighbor
				int neighbor = graph.get(leaf).iterator().next();
				graph.get(neighbor).remove(leaf);  // cannot use primitive type for leaf since it will be treated as remove at index
				if (graph.get(neighbor).size() == 1) {
					newLeaves.add(neighbor);
				}
			}
			leaves = newLeaves;
		}
		
		return leaves;
    }
	
	public static void main(String[] args) {
		MinumumHeightTree mht = new MinumumHeightTree();
		int n = 4;
		int[][] edges = {{1, 0}, {1, 2}, {1, 3}};
		//int n = 6;
		//int[][] edges = {{0, 3}, {1, 3}, {2, 3}, {4, 3}, {5, 4}};
		//int n = 1;
		//int[][] edges = {};
		System.out.println(mht.findMinHeightTrees(n, edges));
	}

}
