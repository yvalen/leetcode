package leetcode.tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
 * You need to find the largest value in each row of a binary tree.
 * Example: input: 
 * 		 1
 * 	 	/ \
 *     3   2
 *    / \   \  
 *   5   3   9 
 * output: [1, 3, 9]
 */
public class LargestValueInEachTreeRow {
	public List<Integer> largestValues_bfs(TreeNode root) {
		if (root == null) return Collections.emptyList();
		
		List<Integer> result = new LinkedList<>();
		Queue<TreeNode> nodeQueue = new LinkedList<>();
		nodeQueue.offer(root);
		while (!nodeQueue.isEmpty()) {
			int size = nodeQueue.size();
			int max = Integer.MIN_VALUE;
			for (int i = 0; i < size; i++) {
				TreeNode current = nodeQueue.poll();
				max = Integer.max(max,  current.val);
				if (current.left != null) {
					nodeQueue.offer(current.left);
				}
				if (current.right != null) {
					nodeQueue.offer(current.right);
				}
			}
			result.add(max);
		}
        return result;
    }

	// pre order traversal
	public List<Integer> largestValues_dfs(TreeNode root) {
		if (root == null) return Collections.emptyList();
		List<Integer> result = new ArrayList<>();
		largestValues_dfs_helper(root, result, 0);
		return result;
	}
	
	private void largestValues_dfs_helper(TreeNode root, List<Integer> result, int depth) {
		if (root == null) return;
		
		// use depth to expand list size
		if (depth == result.size()) {
			result.add(root.val);
		}
		else {
			result.set(depth, Integer.max(result.get(depth), root.val));
		}
		
		largestValues_dfs_helper(root.left, result, depth+1);
		largestValues_dfs_helper(root.right, result, depth+1);
	}
}
