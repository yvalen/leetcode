package leetcode.tree;

/*
 * LEETCODE 110
 * Given a binary tree, determine if it is height-balanced. For this problem, a height-balanced binary tree 
 * is defined as a binary tree in which the depth of the two subtrees of every node never differ by more than 1. 
 * 
 * Company: Bloomberg
 * Difficulty: easy
 * Similar Questions: 104(Maximum Depth of Binary Tree)
 */
public class BalancedBinaryTree {
    /*
     * An empty tree is height-balanced. A non-empty binary tree T is balanced
     * if 1. Left subtree of T is balanced 2. Right subtree of T is balanced 3.
     * The difference between heights of left subtree and right subtree is not
     * more than 1.
     */
    public boolean isBalanced(TreeNode root) {
        if (root == null)
            return true;
        return (Math.abs(maxDepth(root.left) - maxDepth(root.right)) <= 1 && isBalanced(root.left)
                && isBalanced(root.right));
    }

    private int maxDepth(TreeNode root) {
        if (root == null)
            return 0;
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }
}
