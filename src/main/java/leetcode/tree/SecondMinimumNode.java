package leetcode.tree;

import java.util.Stack;

/*
 * LEETCODE 671
 * Given a non-empty special binary tree consisting of nodes with the non-negative value, 
 * where each node in this tree has exactly two or zero sub-node. If the node has two sub-nodes, 
 * then this node's value is the smaller value among its two sub-nodes.
 * Given such a binary tree, you need to output the second minimum value in the set made of all 
 * the nodes' value in the whole tree. If no such second minimum value exists, output -1 instead.
 * Example 1:
 * Input: 
 *     2
 *    / \
 *   2   5
 *      / \
 *     5   7
 * Output: 5
 * Explanation: The smallest value is 2, the second smallest value is 5.
 * Example 2:
 * Input: 
 *     2
 *    / \
 *   2   2
 * Output: -1
 * Explanation: The smallest value is 2, but there isn't any second smallest value.
 * 
 * Company: LinkedIn
 * Difficulty: easy
 * Similar Questions: 230(KthSmallestElementInBST)
 */
public class SecondMinimumNode {
    
    public int findSecondMinimumValue(TreeNode root) {
        if (root == null) return -1;
        
        int left = (root.left != null && root.left.val != root.val) ? root.left.val : findSecondMinimumValue(root.left);
        int right = (root.right != null && root.right.val != root.val) ? root.right.val : findSecondMinimumValue(root.right);
        
        if (left == -1 || right == -1) return Math.max(left, right);
        return Math.min(left, right);
        
        /*
        if (root == null || (root.left == null && root.right == null)) return -1;
        
        int left = root.left.val;
        if (left == root.val) { // if value same as root value, need to find the next candidate
            left = findSecondMinimumValue(root.left);
        }
        int right = root.right.val;
        if (right == root.val) {
            right = findSecondMinimumValue(root.right);
        }
       
        if (left != -1 && right != -1) return Math.min(left, right);
        if (left == -1) return right;
        return left;     
        */
        
        //return findMin(root, root.val);
    }
    
    // If the root value of a subtree == k, keep searching its children
    // else, return the root value because it is the minimum of current subtree.
    private int findMin(TreeNode root, int min) {
        if (root == null)
            return -1;
        if (root.val != min)
            return root.val;

        // second min could be in either left or right subtree, need to check both
        int left = findMin(root.left, min);
        int right = findMin(root.right, min);
        if (left == -1)
            return right;
        if (right == -1)
            return left;
        return Math.min(left, right);
    }
}
