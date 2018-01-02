package leetcode.tree;

import java.util.Stack;

/*
 * LEETCODE 230
 * Given a binary search tree, write a function kthSmallest to find the kth smallest element in it.
 * Note: You may assume k is always valid, 1 ≤ k ≤ BST's total elements.
 * Follow up: What if the BST is modified (insert/delete operations) often and you need to find the 
 * kth smallest frequently? How would you optimize the kthSmallest routine?
 * https://leetcode.com/problems/kth-smallest-element-in-a-bst/discuss/63659
 * add a count field in the BST node class, it will take O(n) time when we calculate the count value 
 * for the whole tree, but after that, it will take O(logn) time when insert/delete a node or calculate 
 * the kth smallest element.
 * 
 * Compant: Google, Uber, Bloomberg
 * Difficulty: medium
 * Similar Questions: 94(BinaryTreeInOrderTraversal), 671(SecondMinimumNode)
 */
public class KthSmallestElementInBST {
    // Time complexity: O(n) 
    public int kthSmallest_inorderIterative(TreeNode root, int k) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;
        while (!stack.isEmpty() || node != null) {
            if (node != null) {
                stack.push(node);
                node = node.left;
            } else {
                node = stack.pop();
                if (--k == 0)
                    break;
                node = node.right;
            }
        }
        return node.val;
    }

    private int count = 0;

    public int kthSmallest_inorderRecursive(TreeNode root, int k) {
        TreeNode kthNode = inorder(root, k);
        return kthNode.val;
    }

    private TreeNode inorder(TreeNode node, int k) {
        if (node == null)
            return null;
        TreeNode left = inorder(node.left, k);
        if (left != null)
            return left;
        if (++count == k)
            return node;
        return inorder(node.right, k);
    }

    public int kthSmallest_binarySearch(TreeNode root, int k) {
        int count = countNodes(root.left);
        if (k <= count) {
            return kthSmallest_binarySearch(root.left, k);
        } else if (k > count + 1) {
            return kthSmallest_binarySearch(root.right, k - count - 1); // i is counted as
                                                           // current node
        }
        return root.val;
    }

    private int countNodes(TreeNode node) {
        if (node == null)
            return 0;
        return 1 + countNodes(node.left) + countNodes(node.right);
    }

    public static void main(String[] args) {
        KthSmallestElementInBST ks = new KthSmallestElementInBST();
        TreeNode root = new TreeNode(1);
        int k = 1;
        System.out.println(ks.kthSmallest_inorderIterative(root, k));
    }
}
