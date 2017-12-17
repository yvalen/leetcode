package leetcode.tree;

/*
 * LEETCODE 687
 * Given a binary tree, find the length of the longest path where each node in the path has the same value. This path may or may not pass through the root.
 * Note: The length of path between two nodes is represented by the number of edges between them.
 * Example 1:
 * Input:
 *            5
 *           / \
 *          4   5
 *         / \   \
 *        1   1   5
 * Output: 2
 * Example 2:
 * Input:
 *            1
 *           / \
 *          4   5
 *         / \   \
 *        4   4   5
 * Output: 2
 * Note: The given binary tree has not more than 10000 nodes. The height of the tree is not more than 1000.
 * 
 * Company: Google
 * Difficulty: easy
 * Similar Questions: 250(CountUnivalueSubtree), 437(Path Sum III), 572(SubtreeOfAnotherTree)
 */
public class LongestUnivaluePath {
    public int longestUnivaluePath(TreeNode root) {
        int[] result = new int[1]; // global max
        postorder(root, result);
        return result[0];
    }

    private int postorder(TreeNode root, int[] result) {
        if (root == null)
            return 0;

        int left = postorder(root.left, result);
        int right = postorder(root.right, result);
        int resultl = root.left != null && root.left.val == root.val ? left + 1 : 0;
        int resultr = root.right != null && root.right.val == root.val ? right + 1 : 0;
        result[0] = Math.max(result[0], resultl + resultr);
        return Math.max(resultl, resultr);
    }
}
