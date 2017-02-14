package leetcode.tree;

import java.util.Stack;

/*
 * Find the sum of all left leaves in a given binary tree.
 * Example:
 * 		3
 * 	   / \
 *    9  20
 *      /  \
 *     15   7
 * There are two left leaves in the binary tree, with values 9 and 15 respectively. Return 24.
*/
public class SumOfLeftLeaves {
	
	
	public int sumOfLeftLeaves_recursive(TreeNode root) {
		if (root == null) return 0;
		return sumOfLeftLeaves_helper(root, null);
    }
	
	private int sumOfLeftLeaves_helper(TreeNode root, TreeNode parent) {
		if (root == null) return 0;
		
		if (root.left == null && root.right == null) {
			if (parent != null && parent.left == root) return root.val;
			return 0;
		}
		
		return sumOfLeftLeaves_helper(root.left, root) + sumOfLeftLeaves_helper(root.right, root);
	}
	
	
	public int sumOfLeftLeaves_iterative(TreeNode root) {
		if (root == null) return 0;
		
		int sum = 0;
		Stack<TreeNode> nodeStack = new Stack<>();
		nodeStack.push(root);
		while (!nodeStack.isEmpty()) {
			TreeNode current = nodeStack.pop();
			if (current.left != null) {
				if (current.left.left == null && current.left.right == null) {
					// add value to sum if left child is leaf node
					sum += current.left.val;
				}
				else {
					// push left child to stack for processing if it is not a leaf node
					nodeStack.push(current.left);
				}
			}
			
			if (current.right != null && 
					(current.right.left != null || current.right.right != null)) {
				// only push right child to stack for processing if it is not a leaf node
				nodeStack.push(current.right);
			}
		}
		
		return sum;
    }

}
