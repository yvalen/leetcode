package leetcode.tree;

public class LowestCommonAncestor {

    /**
     * LEETCODE 235
     * Given a binary search tree (BST), find the lowest common ancestor 
     * (LCA) of two given nodes in the BST. 
     * 
     * Company: Facebook, Amazon, Twitter
     * Difficulty: easy
     * Similar Questions: 236(Lowest Common Ancestor of a Binary Tree)
     */
    // Time complexity: O(h)
    public TreeNode LCAForBST(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || p == null || q == null)
            return null;

        int min = Integer.min(p.val, q.val);
        int max = Integer.max(p.val, q.val);

        while (root.val > max || root.val < min) {
            if (root.val > max) {
                root = root.left;
            } else {
                root = root.right;
            }
        }

        return root;
    }

    /**
     * LEETCODE 236
     * Given a binary tree, find the lowest common ancestor (LCA) of two given
     * nodes in the BST.
     * 
     * Company: Facebook, Amazon, Microsoft, LinkedIn, Apple
     * Difficulty: easy
     * Similar Questions: 236(Lowest Common Ancestor of a Binary Tree)
     * 
     * with parent pointer:
     * http://articles.leetcode.com/lowest-common-ancestor-of-a-binary-tree-part-ii
     */
    // Time complexity: O(n)
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null)
            return null;

        if (root == p || root == q) {
            return root;
        }

        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left != null && right != null) {
            return root;
        }

        if (left != null) {
            return left;
        }

        return right;
    }
}
