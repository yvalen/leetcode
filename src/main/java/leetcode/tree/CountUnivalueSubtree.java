package leetcode.tree;

/*
 * LEETCODE 250
 * Given a binary tree, count the number of uni-value subtrees.
 * A Uni-value subtree means all nodes of the subtree have the same value.
 * For example: Given binary tree,
 *               5
 *              / \
 *             1   5
 *            / \   \
 *           5   5   5
 * return 4. Three leaf node 5 plus 5->5
 * A tree is usually considered to be a subtree of itself. 
 * 
 * Difficulty: medium
 * Similar Questions: 687(LongestUnivaluePath), 572(SubtreeOfAnotherTree)
 */
public class CountUnivalueSubtree {
    public int countUnivalSubtrees(TreeNode root) {
        int[] count = new int[1];
        postorder(root, count);
        return count[0];
    }

    private boolean postorder(TreeNode root, int[] count) {
        if (root == null)
            return true;

        boolean left = postorder(root.left, count);
        boolean right = postorder(root.right, count);
        if (left && right) { // sub trees of left and right should have the same
                             // value
            if (root.left != null && root.val != root.left.val)
                return false;
            if (root.right != null && root.val != root.right.val)
                return false;
            count[0]++;
            return true;
        }
        return false;
    }

    /*
     * this is incorrect because the whole sub tree should have the same value
     * private void postorder(TreeNode root, int[] count) { if (root == null)
     * return;
     * 
     * postorder(root.left, count); postorder(root.right, count); if ((root.left
     * == null && root.right ==null) || (root.left == null && root.val ==
     * root.right.val) || (root.right == null && root.val == root.left.val) ||
     * (root.left != null && root.right != null && root.val == root.left.val &&
     * root.val == root.right.val) ) { count[0]++; } }
     */
    public static void main(String[] args) {
        CountUnivalueSubtree cuvs = new CountUnivalueSubtree();

        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(1);
        root.right.right = new TreeNode(2);
        System.out.println(cuvs.countUnivalSubtrees(root));

    }
}
