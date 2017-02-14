package leetcode.tree;

/*
 * Given a binary tree containing digits from 0-9 only, each root-to-leaf path could represent a number.
 * An example is the root-to-leaf path 1->2->3 which represents the number 123.
 * Find the total sum of all root-to-leaf numbers.
 * For example,
 *     1
 *    / \
 *   2   3
 * The root-to-leaf path 1->2 represents the number 12.
 * The root-to-leaf path 1->3 represents the number 13.
 * Return the sum = 12 + 13 = 25. 
 */
public class SumRootToLeaf {
	public int sumNumbers(TreeNode root) {
		return sumNumbers_helper(root, 0);
    }
	
	public int sumNumbers_helper(TreeNode root, int sum) {
		if (root == null) return 0;
		
		sum = sum * 10 + root.val;
		if (root.left == null && root.right == null) {
        	return sum;
        }
		
		return sumNumbers_helper(root.left, sum) +
				sumNumbers_helper(root.right, sum);
    }
	
}
