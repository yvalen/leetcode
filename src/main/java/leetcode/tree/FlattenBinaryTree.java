package leetcode.tree;

/*
 * LEETCODE 114
 * Given a binary tree, flatten it to a linked list in-place.
 * For example,
 * Given
 *          1
 *         / \
 *        2   5
 *       / \   \
 *      3   4   6
 * The flattened tree should look like:
 *    1
 *     \
 *      2
 *       \
 *        3
 *         \
 *          4
 *           \
 *            5
 *             \
 *              6
 * Hints: If you notice carefully in the flattened tree, each node's 
 * right child points to the next node of a pre-order traversal.
 * 
 * Company: Microsoft
 * Difficulty: medium
 */
public class FlattenBinaryTree {
	private TreeNode prev;
    public void flatten(TreeNode root) {
        if (root == null) return;
        
        flatten(root.right);
        flatten(root.left);
        root.right = prev;
        root.left = null;
        prev = root;
    }

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
