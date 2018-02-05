package leetcode.tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/*
 * LEETCODE 742
 * Given a binary tree where every node has a unique value, and a target key k, 
 * find the value of the nearest leaf node to target k in the tree. Here, nearest 
 * to a leaf means the least number of edges traveled on the binary tree to reach 
 * any leaf of the tree. Also, a node is called a leaf if it has no children.
 * In the following examples, the input tree is represented in flattened form row 
 * by row. The actual root tree given will be a TreeNode object.
 * Example 1:
 * Input:
 * root = [1, 3, 2], k = 1
 * Diagram of binary tree:
 *           1
 *          / \
 *         3   2
 * Output: 2 (or 3)
 * Explanation: Either 2 or 3 is the nearest leaf node to the target of 1.
 * Example 2:
 * Input:
 * root = [1], k = 1
 * Output: 1
 * Explanation: The nearest leaf node is the root node itself.
 * Example 3:
 * Input:
 * root = [1,2,3,4,null,null,null,5,null,6], k = 2
 * Diagram of binary tree:
 *              1
 *             / \
 *            2   3
 *           /
 *          4
 *         /        
 *        5
 *       /
 *      6
 * Output: 3
 * Explanation: The leaf node with value 3 (and not the leaf node with value 6) 
 * is nearest to the node with value 2.
 * Note:
 * - root represents a binary tree with at least 1 node and at most 1000 nodes.
 * - Every node has a unique node.val in range [1, 1000].
 * - There exists some node in the given binary tree for which node.val == k.
 * 
 * Company: Amazon, databricks
 * Difficulty: medium
 */
public class ClosestLeafInBinaryTree {
	public int findClosestLeaf(TreeNode root, int k) {
		Map<Integer, List<Integer>> graph = new HashMap<>();
		Set<Integer> leaves = new HashSet<>();
		for (int i = 1; i <= 1000; i++) graph.put(i, new ArrayList<>());
		buildGraph(root, graph, leaves);
		
		boolean[] visited = new boolean[1001];
		Queue<Integer> queue = new LinkedList<>();
		queue.offer(k);
		visited[k] = true;
		while (!queue.isEmpty()) {
			int current = queue.poll();
			if (leaves.contains(current)) return current;
			for (int neighbor : graph.get(current)) {
				if (!visited[neighbor]) {
					queue.offer(neighbor);
					visited[neighbor] = true;
				}
			}
		}
		return 0;
    }
	
	private void buildGraph(TreeNode root, Map<Integer, List<Integer>> graph, Set<Integer> leaves) {
		if (root == null) return;
		
		if (root.left == null && root.right == null) {
			leaves.add(root.val);
			return;
		}
		
		if (root.left != null) {
			graph.get(root.val).add(root.left.val);
			graph.get(root.left.val).add(root.val);
		}
		if (root.right != null) {
			graph.get(root.val).add(root.right.val);
			graph.get(root.right.val).add(root.val);
		}
		buildGraph(root.left, graph, leaves);
		buildGraph(root.right, graph, leaves);
	}
}
