package leetcode.tree;

public class BinaryTreeEasy {

	public int minDepth(TreeNode root) {
        if (root == null) return 0;
        
        int leftDepth = minDepth(root.left);
        int rightDepth = minDepth(root.right);
        if (leftDepth != 0 && rightDepth != 0) {
        	return Integer.min(leftDepth, rightDepth) + 1;
        }
        
        if (leftDepth != 0) {
        	return leftDepth + 1;
        }
        
        return rightDepth + 1;
    }
	
	public int maxDepth(TreeNode root) {
        if (root == null) return 0;
        return Integer.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }
	
	/**
	 * An empty tree is height-balanced. A non-empty binary tree T is balanced if
	 * 1. Left subtree of T is balanced
	 * 2. Right subtree of T is balanced
	 * 3. The difference between heights of left subtree and right subtree is not more than 1. 
	 */
	public boolean isBalanced(TreeNode root) {
		if (root == null) return true;
		return (Math.abs(maxDepth(root.left) - maxDepth(root.right)) <= 1 &&
				isBalanced(root.left) && isBalanced(root.right));
    }
	
	/**
	 * Two binary trees are considered equal if they are structurally identical and the nodes have the same value. 
	 */
	public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
        	return true;
        }
        
        if ((p == null & q != null) || (p != null && q == null)) {
        	return false;
        }
        
        return p.val == q.val && isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
	
	public TreeNode invertTree(TreeNode root) {
        if (root == null) return root;
        
        TreeNode left = invertTree(root.left);
        TreeNode right = invertTree(root.right);
        root.left = right;
        root.right = left;
        
        return root;
    }
	
	public boolean isSymmetric(TreeNode root) {
		if (root == null) return true;	
		
		return isMirror(root.left, root.right);
	}
	
	public boolean isMirror(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
        	return true;
        }
        
        if ((p == null & q != null) || (p != null && q == null)) {
        	return false;
        }
        
        return p.val == q.val && isMirror(p.left, q.right) && isMirror(p.right, q.left);
    }
	
	
}
