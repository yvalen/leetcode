package leetcode.tree;

public class BinaryTreeEasy {

 
    /**
     * Two binary trees are considered equal if they are structurally identical
     * and the nodes have the same value.
     */
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }

        if ((p == null & q != null) || (p != null && q == null)) {
            return false;
        }

        return p.val == q.val && isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }

    public boolean isSymmetric(TreeNode root) {
        if (root == null)
            return true;

        return isMirror(root.left, root.right);
    }

    public boolean isMirror(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }

        if ((p == null & q != null) || (p != null && q == null)) {
            return false;
        }

        return p.val == q.val && isMirror(p.left, q.right) && isMirror(p.right, q.left);
    }

}
