package leetcode.tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class BinaryTreeLevelOrder {
	/**
	 * Given a binary tree, return the level order traversal of its nodes' values. (ie, from left to right, level by level).
	 * For example: Given binary tree {3,9,20,#,#,15,7},
	 *         3
	 *        / \
	 *       9  20
	 *         /  \
	 *        15   7
	 * return its level order traversal as:
	 * [
	 *	[3],
	 *	[9,20],
	 *	[15,7]
	 *]
	 */
	public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        
		Queue<TreeNode> queue = new LinkedList<>();
		queue.add(root);
		while (!queue.isEmpty()) {
			int queueSize = queue.size();
			List<Integer> level = new ArrayList<>();
			for (int i = 0 ; i < queueSize; i++) {
				TreeNode node = queue.poll();
				level.add(node.val);
				if (node.left != null) {
					queue.add(node.left);
				}
			
				if (node.right != null) {
					queue.add(node.right);
				}
			}
			result.add(level);
		}
		
		return result;
    }

	/**
	 * Given a binary tree, return the bottom-up level order traversal of its nodes' values. 
	 * (ie, from left to right, level by level from leaf to root).
	 * For example, given binary tree {3,9,20,#,#,15,7}, return [[15,7],[9, 20],[3]]
	 */
	public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> result = new Stack<>();
        if (root == null) {
            return result;
        }
        
		Queue<TreeNode> queue = new LinkedList<>();
		queue.add(root);
		while (!queue.isEmpty()) {
			int queueSize = queue.size();
			List<Integer> level = new ArrayList<>();
			for (int i = 0 ; i < queueSize; i++) {
				TreeNode node = queue.poll();
				level.add(node.val);
				if (node.left != null) {
					queue.add(node.left);
				}
			
				if (node.right != null) {
					queue.add(node.right);
				}
			}
			result.add(0, level);
		}
		
		return result;
    }
	
	// TODO
	public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
		List<List<Integer>> result = new LinkedList<>();
        if (root == null) {
            return result;
        }
		
		Stack<TreeNode> s1 = new Stack<>();
		Stack<TreeNode> s2 = new Stack<>();
		s1.push(root);
		List<Integer> level = new ArrayList<>();
		while (!s1.isEmpty() || !s2.isEmpty()) {
			while (!s1.isEmpty()) {
				TreeNode current = s1.pop();
				level.add(current.val);
				if (current.left != null) s2.push(current.left);
				if (current.right != null) s2.push(current.right);
			}

			if (!level.isEmpty()) {
				result.add(new ArrayList<>(level));
				level.clear();
			}
			
			while (!s2.isEmpty()) {
				TreeNode current = s2.pop();
				level.add(current.val);
				if (current.right != null) s1.push(current.right);
				if (current.left != null) s1.push(current.left);
			}
			
			if (!level.isEmpty()) {
				result.add(new ArrayList<>(level));
				level.clear();
			}
		}
		
		return result;
        
    }
	

}
