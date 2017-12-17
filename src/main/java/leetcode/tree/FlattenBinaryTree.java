package leetcode.tree;

public class FlattenBinaryTree {

    public void flatten_recursive(TreeNode root) {
        if (root == null)
            return;

        TreeNode left = root.left;
        TreeNode right = root.right;

        flatten_recursive(root.left);
        flatten_recursive(root.right);

        root.left = null;
        root.right = left;
        TreeNode current = root;
        while (current.right != null)
            current = current.right;
        current.right = right;
    }

    // Complexity: O(N), every node is visited by current exactly once and by
    // prev at most once
    public void flatten_morrisTraversal(TreeNode root) {
        if (root == null)
            return;

        TreeNode current = root, prev;
        while (current != null) {
            if (current.left != null) {
                // find the right most element of the left sub tree
                prev = current.left;
                while (prev.right != null)
                    prev = prev.right;

                // point the rightmost of left sub tree to current's right sub
                // tree
                prev.right = current.right;

                current.right = current.left;
                current.left = null;
            }

            // move to the next element which is current's right sub tree
            current = current.right;
        }
    }

}
