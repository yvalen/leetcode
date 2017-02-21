package leetcode.tree;

import java.util.Stack;

public class ValidateBST {
	public boolean isValidBST(TreeNode root) {
		return isValidBST_helper(root, Long.MIN_VALUE, Long.MAX_VALUE);
	}
	
	private boolean isValidBST_helper(TreeNode root, long min, long max) {
		if (root == null) return true;
		return (root.val > min && root.val < max) && 
				isValidBST_helper(root.left, min, root.val) &&
				isValidBST_helper(root.right, root.val, max);
		
	}
	
	public boolean isValidBST_inorder(TreeNode root) {
		if (root == null) return true;
		
		TreeNode prev = null;
		Stack<TreeNode> nodeStack = new Stack<>();
		TreeNode current = root;
		while (!nodeStack.isEmpty() || current != null) {
			if (current != null) {
				nodeStack.push(current);
				current = current.left;
			}
			else {
				current = nodeStack.pop();
				if (prev != null && prev.val >= current.val) return false;
				prev = current;
				current = current.right;
			}
		}
		return true;	
	}
}
