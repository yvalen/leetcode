package leetcode.tree;

import java.util.Stack;

/*
 * Given a complete binary tree, count the number of nodes.
 * Definition of a complete binary tree from Wikipedia:
 * In a complete binary tree every level, except possibly the last, 
 * is completely filled, and all nodes in the last level are as far left as possible. 
 * It can have between 1 and 2h nodes inclusive at the last level h.
 */
public class CountCompleteTreeNodes {
	public int countNodes_TEL(TreeNode root) {
		if (root == null) return 0;
		
		int leftDepth = 1, rightDepth = 1;
		TreeNode current = root;
		while (current.left != null) leftDepth++;
		current = root;
		while (current.right != null) rightDepth++;
		
		if (leftDepth == rightDepth) return (1 << leftDepth) - 1;
		
		return 1 +  countNodes_TEL(root.left) + countNodes_TEL(root.right); 
    }

	public int countNodes(TreeNode root) {
		int h = height(root);
		if (h < 0 ) return 0; // empty tree
		
		if (height(root.right) == h -1) {
			// height of the right sub tree is same as left sub tree, 
			// last row ends in right sub tree, left tree has 2^h-1 nodes
			return (1 << h) + countNodes(root.right);
		}
		
		// height of the left sub tree is greater than right sub tree
		// last row ends in left sub tree, right tree contains 2^(h-1)-1 nodes
		return (1<<(h-1)) + countNodes(root.left);
	}
	
	private int height(TreeNode root) {
		return root == null ? -1 : 1 + height(root.left);
	}
	
	public int countNodes_iterative(TreeNode root) {
		int count = 0, h = height(root);
		TreeNode node = root;
		while (node != null) {
			if (height(node.right) == h -1) {
				count += (1 << h);
				node = node.right;
			}
			else {
				count += (1 << (h-1));
				node = node.left;
			}
			h--;
		}
		return count;
	}
	
}
