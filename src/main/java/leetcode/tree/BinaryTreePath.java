package leetcode.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class BinaryTreePath {
	
	/**
	 *  Given a binary tree, return all root-to-leaf paths.
	 *  For example, given the following binary tree:
	 *     	  1
	 *      /   \
	 *     2     3
	 *     		  \
	 *             5
	 *  All root-to-leaf paths are: ["1->3->5", "1->2"]
	 *  (EASY)
	 */
	public List<String> binaryTreePaths(TreeNode root) {
        List<String> paths = new LinkedList<>();
        if (root == null) return paths;
		
        binaryTreePathsHelper(root, Integer.toString(root.val), paths);
        return paths;
	}
	
	private void binaryTreePathsHelper(TreeNode root, String path, List<String> paths) {
        if (root == null) return;
		
        if (root.left == null && root.right == null) {
        	paths.add(path);
        	return;
        }
        
        if (root.left != null) {
        	binaryTreePathsHelper(root.left, path + "->" + Integer.toString(root.left.val), paths);
        }
        
        if (root.right != null) {
        	binaryTreePathsHelper(root.right, path + "->" + Integer.toString(root.right.val), paths);
        }
	}
	
	/**
	 * Binary tree path, less optimal, create a list for every recursion
	 */
	public List<String> binaryTreePathsNewListEveryRecursion(TreeNode root) {
        List<String> paths = new LinkedList<>();
        
        if (root == null) return paths;
        List<String> leftPaths = binaryTreePathsNewListEveryRecursion(root.left);
        List<String> rightPaths = binaryTreePathsNewListEveryRecursion(root.right);
        
        String nodeVal = Integer.toString(root.val);
        if (leftPaths.isEmpty() && rightPaths.isEmpty()) {
        	paths.add(nodeVal);
        }
        
        for (String path : leftPaths) {
			paths.add(nodeVal + "->" + path); 
		}
        
        for (String path : rightPaths) {
			paths.add(nodeVal + "->" + path); 
		}
        
        return paths;
    }
	
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
        if (root == null) return result;
        
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
	
		if (root.left != null) {
			pathSumHelper(root.left, sum-root.val, path, result);
		}
		
		if (root.right != null) {
			pathSumHelper(root.right, sum-root.val, path, result);
		}
		
		path.pop();
	}
}
