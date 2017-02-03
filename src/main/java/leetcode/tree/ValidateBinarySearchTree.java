package leetcode.tree;

public class ValidateBinarySearchTree {

	/**
	 *  Given a binary tree, determine if it is a valid binary search tree (BST).
	 *  Assume a BST is defined as follows:
	 *  	The left subtree of a node contains only nodes with keys less than the node's key.
	 *  	The right subtree of a node contains only nodes with keys greater than the node's key.
	 *  	Both the left and right subtrees must also be binary search trees.
	 *  
	 *  This solution also goes to the left subtree first. If the violation occurs close to the root 
	 *  but on the right subtree, the method still cost O(n). 
	 */
	public boolean isValidBST(TreeNode root) {
		return isValidBSTHelper(root, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY);
    }
	
	public boolean isValidBSTHelper(TreeNode root, double min, double max) {
		if (root == null) return true;
		
		// All values on the left sub tree must be less than root, 
		// and all values on the right sub tree must be greater than root.
		if (root.val <= min || root.val >= max) {
			return false;
		}
		
		return isValidBSTHelper(root.left, min, root.val) 
				&& isValidBSTHelper(root.right, root.val, max);
    }
	
}
