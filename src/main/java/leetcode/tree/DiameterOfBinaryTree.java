package leetcode.tree;

/*
 * Given a binary tree, you need to compute the length of the diameter of the tree. The diameter of a binary tree is the length 
 * of the longest path between any two nodes in a tree. This path may or may not pass through the root.
 * Example: Given a binary tree
 *           1
 *          / \
 *         2   3
 *        / \     
 *       4   5    
 * Return 3, which is the length of the path [4,2,1,3] or [5,2,1,3].
 * Note: The length of path between two nodes is represented by the number of edges between them. 
 * 
 * Company: Google, Facebook
 * Difficulty: easy
 */
public class DiameterOfBinaryTree {
	private int max = 0;
	public int diameterOfBinaryTree(TreeNode root) {
        maxDepth(root);
		return max;
    }
	
	private int maxDepth(TreeNode root) {
		if (root == null) return 0;
		int leftDepth = maxDepth(root.left);
		int rightDepth = maxDepth(root.right);
		
		// For every node, length of longest path which pass it is
		// MaxDepth of its left subtree + MaxDepth of its right subtree.
		max = Math.max(max, leftDepth+rightDepth);
		return Math.max(leftDepth, rightDepth) + 1;
	}
	
	public static void main(String[] args) {
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		root.right = new TreeNode(3);
		root.left.left = new TreeNode(4);
		root.left.right = new TreeNode(5);
		
		DiameterOfBinaryTree  dia = new DiameterOfBinaryTree ();
		System.out.println(dia.diameterOfBinaryTree(root));
	}

}
