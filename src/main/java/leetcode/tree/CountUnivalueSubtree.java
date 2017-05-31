package leetcode.tree;

/*
 * Given a binary tree, count the number of uni-value subtrees.
 * A Uni-value subtree means all nodes of the subtree have the same value.
 * For example: Given binary tree,
 *               5
 *              / \
 *             1   5
 *            / \   \
 *           5   5   5
 * return 4. Three leaf node 5 plus 5->5
 * A tree is usually considered to be a subtree of itself. 
 */
public class CountUnivalueSubtree {
	public int countUnivalSubtrees(TreeNode root) {
		if (root == null) return 0;
		
		int[] count = new int[1];
		postOrder(root, count);
		return count[0];
    }
	
	private boolean postOrder(TreeNode root, int[] count) {
		if (root == null) return true;
		
		boolean left = postOrder(root.left, count);
		boolean right = postOrder(root.right, count);
		if (left && right) {
			if (root.left != null && root.val != root.left.val) return false;
			if (root.right != null && root.val != root.right.val) return false;
			count[0]++;
			return true;
		}
		return false;
	}
}
