package leetcode.tree;

public class BinaryTreeMedium {
	/**
	 * Given a binary tree, find the length of the longest consecutive sequence path.
	 * The path refers to any sequence of nodes from some starting node to any node in the tree along the parent-child connections. 
	 * The longest consecutive path need to be from parent to child (cannot be the reverse).
	 * For example,
	 * 		1
	 *	 	 \
	 *		  3
	 *		 / \
	 *		2   4
	 *        	 \
	 *        	  5
	 * Longest consecutive sequence path is 3-4-5, so return 3.
	 * 		2
	 * 		 \
	 * 		  3
	 * 		 / 
	 * 		2    
	 * 	   /
	 * 	  1
	 * Longest consecutive sequence path is 2-3,not 3-2-1, so return 2.
	 */
	public int longestConsecutive(TreeNode root) {
		if (root == null) return 0;
        return longestConsecutiveHelper(root, 0, root.val-1);
    }
	
	public int longestConsecutiveHelper(TreeNode current, int len, int parentVal) {
		if (current == null) {
			return len;
		}
		
		int currentLen = (parentVal + 1 == current.val) ? len + 1 : 1;
		return Math.max(currentLen, 
				Math.max(
						longestConsecutiveHelper(current.left,  currentLen, current.val),
						longestConsecutiveHelper(current.right, currentLen, current.val)));
	}

}
