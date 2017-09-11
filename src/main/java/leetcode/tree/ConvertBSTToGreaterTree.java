package leetcode.tree;

import java.util.Stack;

/*
 * Given a Binary Search Tree (BST), convert it to a Greater Tree such that every key of the original BST is changed to 
 * the original key plus sum of all keys greater than the original key in BST.
 * Example:
 * Input: The root of a Binary Search Tree like this:
 *            5
 *          /   \
 *         2     13
 * Output: The root of a Greater Tree like this
 *            18
 *          /   \
 *        20     13
 *        
 * Company: Amazon
 * Difficulty: easy
 */
public class ConvertBSTToGreaterTree {
	public TreeNode convertBST_recursive(TreeNode root) {
        convert(root, 0);
        return root;
    }
	
	private int convert(TreeNode root, int sum) {
		if (root == null) return sum;  // need to return sum here 
		int right = convert(root.right, sum);
		root.val += right;
		int left = convert(root.left, root.val);
		return left;
	}
	
	// reverse inorder
	public TreeNode convertBST_iterative(TreeNode root) {
        if (root == null) return root;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;
        int sum = 0;
        while (!stack.isEmpty() || node != null) {
        	if (node != null) {
        		stack.push(node);
        		node = node.right;
        	}
        	else {
        		node = stack.pop();
        		sum += node.val;
        		node.val = sum;
        		node = node.left;
        	}
        }
        return root;
    }
}
