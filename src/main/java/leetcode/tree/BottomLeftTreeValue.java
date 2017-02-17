package leetcode.tree;

import java.util.LinkedList;
import java.util.Queue;

/*
 * Given a binary tree, find the leftmost value in the last row of the tree.
 * Example 1: input:
 * 		2
 *     / \
 *    1   3
 * Output: 1
 * Example 2: input:
 * 		1
 *     / \
 *    2   3
 *   /   / \
 *  4   5   6
 *     /
 *    7
 * Output: 7
 * Note: You may assume the tree (i.e., the given root node) is not NULL. 
 */
public class BottomLeftTreeValue {
	public int findBottomLeftValue_bfs(TreeNode root) {
		int result = root.val;
		Queue<TreeNode> nodeQueue = new LinkedList<>();
		nodeQueue.offer(root);
		while (!nodeQueue.isEmpty()) {
			int size = nodeQueue.size();
			for (int i = 0; i < size; i++) {
				TreeNode current = nodeQueue.poll();
				if (i == 0) result = current.val;
				if (current.left != null) nodeQueue.offer(current.left);
				if (current.right != null) nodeQueue.offer(current.right);
			}
		}
		return result;
    }

	public int findBottomLeftValue_dfs(TreeNode root) {
		maxDepth = Integer.MIN_VALUE;
		findBottomLeftValue_dfs_helper(root, 1);
		return ans;
    }
	
	private void findBottomLeftValue_dfs_helper(TreeNode root, int depth) {
		if (root == null) return;
		
		if (maxDepth < depth) {
			ans = root.val;
			maxDepth = depth;		
		}
		findBottomLeftValue_dfs_helper(root.left, depth + 1);
		findBottomLeftValue_dfs_helper(root.right, depth + 1);
    }
	
	private int ans; 
	private int maxDepth; // tracks the maximum height of the tree
	
}
