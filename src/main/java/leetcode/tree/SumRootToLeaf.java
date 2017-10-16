package leetcode.tree;

/*
 * LEETCODE 129
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
 * 
 * Difficulty: medium
 * Similar Questions: 112(Path Sum), 124(Binary Tree Max Path Sum)
 */
public class SumRootToLeaf {
	public int sumNumbers(TreeNode root) {
		return preorder(root, 0);
    }
	
	public int preorder(TreeNode root, int sum) {
		if (root == null) return 0;
		
		sum = sum * 10 + root.val;
		if (root.left == null && root.right == null) { // base case, need to return sum here; otherwise the result will 0 for a single node tree
        	return sum;
        }
		
		return preorder(root.left, sum) +
				preorder(root.right, sum);
    }
	
}
