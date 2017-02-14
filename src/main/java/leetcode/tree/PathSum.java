package leetcode.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class PathSum {
	/**
	 * Given a binary tree and a sum determine if the tree has a root-to-leaf path 
	 * such that adding up all the values along the path equals the given sum. 
	 * (EASY)
	 */
	public boolean hasPathSum(TreeNode root, int sum) {
		if (root == null) return false;
		
		if (root.left == null && root.right == null) {
			return root.val == sum;
		}
		
		return (hasPathSum(root.left, sum-root.val) || hasPathSum(root.right, sum-root.val));
    }
	
	
	/**
	 * Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum.
	 * (MEDIUM)
	 */
	public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> result = new LinkedList<>();
        Stack<Integer> path = new Stack<>();
        pathSumHelper(root, sum, path, result);
        return result;
    }
	
	private void pathSumHelper(TreeNode root, int sum, Stack<Integer> path, List<List<Integer>> result) {
		if (root == null) return;
		
		path.push(root.val);
		if (root.left == null && root.right == null && sum == root.val) {
			result.add(new ArrayList<Integer>(path));
		}
		else {
			pathSumHelper(root.left, sum-root.val, path, result);
			pathSumHelper(root.right, sum-root.val, path, result);
		}
		path.pop();
	}
	
	/**
	 * You are given a binary tree in which each node contains an integer value.
	 * Find the number of paths that sum to a given value. The path does not need 
	 * to start or end at the root or a leaf, but it must go downwards 
	 * (traveling only from parent nodes to child nodes).
	 * The tree has no more than 1,000 nodes and the values are in the range -1,000,000 to 1,000,000.
	 */
	public int pathSum_III(TreeNode root, int sum) {
        
		
		
		
		return 0;
    }
	
	/**
	 *  Given a binary tree, find the maximum path sum. For this problem, a path is defined as any sequence 
	 *  of nodes from some starting node to any node in the tree along the parent-child connections. 
	 *  The path must contain at least one node and does not need to go through the root.
	 *  For example: given the below binary tree,
	 *         1
	 *        / \
	 *       2   3
	 *	Return 6. 
	 */
	// global variable to record the maximum value 
	private int maxSum;
	
	public int maxPathSum(TreeNode root) {
		maxSum = Integer.MIN_VALUE;
		maxPathSum_helper(root);
		return maxSum;
    }

	private int maxPathSum_helper(TreeNode root) {
		if (root == null) return 0;
		
		int leftMaxSum = Integer.max(0,  maxPathSum_helper(root.left));
		int rightMaxSum = Integer.max(0,  maxPathSum_helper(root.right));
		maxSum = Integer.max(maxSum,  root.val + leftMaxSum + rightMaxSum);
		return Integer.max(leftMaxSum, rightMaxSum) + root.val;
	}
}
