package leetcode.tree;

import java.util.Stack;

/*
 * LEETCODE 636
 * Given two non-empty binary trees s and t, check whether tree t has exactly 
 * the same structure and node values with a subtree of s. A subtree of s is a 
 * tree consists of a node in s and all of this node's descendants. The tree s 
 * could also be considered as a subtree of itself.
 * Example 1:
 * 	Given tree s:
 * 		3
 *     / \
 *    4   5
 *   / \
 *  1   2
 * 	Given tree t:
 * 		4 
 *     / \
 *    1   2
 *	Return true, because t has the same structure and node values with a subtree of s.
 * Example 2:
 * 	Given tree s:
 * 	     3
 *      / \
 *     4   5
 *    / \
 *   1   2
 *      /
 *     0
 *	Given tree t:
 *		4
 *	   / \
 *    1   2
 *	Return false. 
 *
 * Company: Facebook, eBay
 * Difficulty: easy
 * Similar Questions: 250(CountUnivalueSubtree)
 */
public class SubtreeOfAnotherTree {
    // Time complexity: O(mn)
    public boolean isSubtree(TreeNode s, TreeNode t) {
        if (s == null)
            return t == null;
        if (t == null)
            return true;

        // For each node during pre-order traversal of s, use a recursive
        // function isSame to
        // validate if sub-tree started with this node is the same with t.
        Stack<TreeNode> stack = new Stack<>();
        stack.push(s);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if (isSameTree(node, t))
                return true;
            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
        }
        return false;
    }

    private boolean isSameTree(TreeNode s, TreeNode t) {
        if (s == null)
            return t == null;
        if (t == null)
            return false;

        if (s.val != t.val)
            return false;

        return (s.val == t.val) && isSameTree(s.left, t.left) && isSameTree(s.right, t.right);
    }
}
