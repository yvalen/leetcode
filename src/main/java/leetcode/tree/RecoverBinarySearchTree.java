package leetcode.tree;

import java.util.Stack;

/*
 * LEETCODE 99
 * Two elements of a binary search tree (BST) are swapped by mistake.
 * Recover the tree without changing its structure.
 * Note: A solution using O(n) space is pretty straight forward. 
 * Could you devise a constant space solution?
 */
public class RecoverBinarySearchTree {

    // space usage for the stack: O(logN) best case, O(N) worst case
    public void recoverTree_inorder(TreeNode root) {
        if (root == null)
            return;

        Stack<TreeNode> nodeStack = new Stack<TreeNode>();
        TreeNode first = null, second = null, prev = null;

        TreeNode node = root;
        while (!nodeStack.isEmpty() || node != null) {
            if (node != null) {
                nodeStack.push(node);
                node = node.left;
            } else {
                node = nodeStack.pop();
                if (prev != null && node.val < prev.val) {
                    // compare the current node with its previous node
                    // first element is the previous node that is greater than
                    // the current node
                    // second element is the current node that is smaller than
                    // the previous node
                    if (first == null) {
                        first = prev;
                    }

                    // always assign node to second to handle a tree with 2
                    // elements
                    second = node;
                }
                prev = node;
                node = node.right;
            }
        }

        if (first != null && second != null) {
            int tmp = first.val;
            first.val = second.val;
            second.val = tmp;
        }
    }

    // space usage: O(1)
    // http://www.cnblogs.com/AnnieKim/archive/2013/06/15/MorrisTraversal.html
    public void recoverTree_morrisTraversal(TreeNode root) {
        if (root == null)
            return;

        TreeNode first = null, second = null, prev = null, temp = null;
        TreeNode current = root;
        while (current != null) {
            if (current.left == null) {
                if (prev != null && current.val < prev.val) {
                    if (first == null)
                        first = prev;
                    second = current;
                }
                prev = current;
                current = current.right;
            } else {
                temp = current.left;
                while (temp.right != null && temp.right != current) {
                    temp = temp.right;
                }

                if (temp.right == null) {
                    temp.right = current;
                    current = current.left;
                } else {
                    if (prev != null && current.val < prev.val) {
                        if (first == null)
                            first = prev;
                        second = current;
                    }

                    temp.right = null;
                    prev = current;
                    current = current.right;
                }
            }
        }

        if (first != null && second != null) {
            int tmp = first.val;
            first.val = second.val;
            second.val = tmp;
        }
    }

    public static void main(String[] args) {
        RecoverBinarySearchTree r = new RecoverBinarySearchTree();

        TreeNode root = new TreeNode(0);
        root.left = new TreeNode(1);

        r.recoverTree_inorder(root);

    }
}
