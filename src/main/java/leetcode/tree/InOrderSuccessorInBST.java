package leetcode.tree;

import java.util.Stack;

/*
 * Given a binary search tree and a node in it, find the in-order successor of that node in the BST.
 * Note: If the given node has no in-order successor in the tree, return null.
 * https://discuss.leetcode.com/topic/25076/share-my-java-recursive-solution
 * 
 * Company: Pocket Gems, Microsoft, Facebook
 */
public class InOrderSuccessorInBST {
    // Inorder successor of p is either p's parent or the left most node in 's
    // right sub tree

    // Time Complexity: O(h)
    public TreeNode inorderSuccessor_iterative(TreeNode root, TreeNode p) {
        if (root == null || p == null)
            return null;

        // If right subtree of p is not NULL,
        // successor is the leftmost child of its right subtree
        if (p.right != null) {
            TreeNode node = p.right;
            while (node.left != null)
                node = node.left;
            return node;
        }

        // if right subtree of p is null, traverse down the tree
        // until p is found
        TreeNode node = root, successor = null;
        while (node != null) {
            if (p.val < node.val) {
                // p is in the left subtree, current node could be the successor
                successor = node;
                node = node.left;
            } else if (p.val > node.val) {
                // p is in the right sub tree, keep searching
                node = node.right;
            } else {
                // find p, successor has been assgined already
                break;
            }
        }

        return successor;
    }

    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        if (root == null)
            return null;

        if (root.val <= p.val) {
            return inorderSuccessor(root.right, p);
        } else {
            TreeNode left = inorderSuccessor(root.left, p); // this call returns
                                                            // null if p doesn't
                                                            // have right
                                                            // subtree or the
                                                            // left most node in
                                                            // p's right subtree
            return (left != null) ? left : root;
        }
    }
}
