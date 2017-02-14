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
        
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        boolean leftToRight = true;
        while (!q.isEmpty()) {
        	int size = q.size();
        	LinkedList<Integer> level = new LinkedList<>();
        	for (int i = 0; i < size; i++) {
        		TreeNode node = q.poll();
        		if (leftToRight) {
        			level.addLast(node.val);
        		}
        		else {
        			level.addFirst(node.val);
        		}
        		if (node.left != null) {
        			q.offer(node.left);
        		}
        		if (node.right != null) {
        			q.offer(node.right);
        		}
        	}
        	leftToRight = !leftToRight;
        	result.add(level);
        }
        
        return result;
    }

}
