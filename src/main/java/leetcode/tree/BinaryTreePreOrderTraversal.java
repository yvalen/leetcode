package leetcode.tree;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/*
 * LEETCODE 144
 * Given a binary tree, return the preorder(root->left->right) traversal of its nodes' values.
 * For example:
 * Given binary tree [1,null,2,3],
 *    1
 *     \
 *     2
 *    /
 *   3
 * return [1,2,3]. 
 * 
 * Difficulty: medium
 * Similar Questions: 94(BinaryTreeInOrderTraversal), 255
 */
public class BinaryTreePreOrderTraversal {

    public List<Integer> preorderTraversal_recursive(TreeNode root) {
        List<Integer> result = new LinkedList<>();
        preorderTraversal_recursive_helper(root, result);
        return result;
    }

    private void preorderTraversal_recursive_helper(TreeNode root, List<Integer> result) {
        if (root == null)
            return;

        result.add(root.val);
        preorderTraversal_recursive_helper(root.left, result);
        preorderTraversal_recursive_helper(root.right, result);
    }

    public List<Integer> preorderTraversal_iterative(TreeNode root) {
        if (root == null)
            return Collections.emptyList();

        List<Integer> result = new LinkedList<>();
        Stack<TreeNode> nodeStack = new Stack<>();
        nodeStack.push(root);
        while (!nodeStack.isEmpty()) {
            TreeNode node = nodeStack.pop();
            result.add(node.val);
            if (node.right != null) {
                nodeStack.push(node.right);
            }
            if (node.left != null) {
                nodeStack.push(node.left);
            }
        }

        return result;
    }

    // http://www.cnblogs.com/AnnieKim/archive/2013/06/15/MorrisTraversal.html
    public List<Integer> preorderTraversal_morrisTraversal(TreeNode root) {
        List<Integer> result = new LinkedList<>();

        TreeNode temp = null, current = root;
        while (current != null) {
            if (current.left == null) {
                result.add(current.val);
                current = current.right;
            } else {
                temp = current.left;
                while (temp.right != null && temp.right != current) {
                    temp = temp.right;
                }

                if (temp.right == null) {
                    result.add(current.val);
                    temp.right = current;
                    current = current.left;
                } else {
                    temp.right = null;
                    current = current.right;
                }
            }
        }

        return result;
    }

}
