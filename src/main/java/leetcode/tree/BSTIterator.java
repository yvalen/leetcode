package leetcode.tree;

import java.util.Stack;

/**
 * Implement an iterator over a binary search tree (BST). Your iterator will be
 * initialized with the root node of a BST. Note: next() and hasNext() should
 * run in average O(1) time and uses O(h) memory, where h is the height of the
 * tree.
 */
public class BSTIterator {
    private Stack<TreeNode> nodeStack;
    private TreeNode current;

    public BSTIterator(TreeNode root) {
        nodeStack = new Stack<>();
        current = root;
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return (!nodeStack.isEmpty() || current != null);
    }

    /** @return the next smallest number */
    public int next() {
        while (current != null) {
            nodeStack.push(current);
            current = current.left;
        }

        current = nodeStack.pop();
        int val = current.val;
        current = current.right;

        return val;
    }

}
