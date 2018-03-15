package leetcode.tree;

import java.util.Currency;
import java.util.Stack;

/*
 * LEETCODE 783
 * Given a Binary Search Tree (BST) with the root node root, return the 
 * minimum difference between the values of any two different nodes in the tree.
 * Example :
 * Input: root = [4,2,6,1,3,null,null]
 * Output: 1
 * Explanation: Note that root is a TreeNode object, not an array.
 * The given tree [4,2,6,1,3,null,null] is represented by the following diagram:
 *           4
 *         /   \
 *        2      6
 *       / \   
 *      1   3  
 * while the minimum difference in this tree is 1, it occurs between node 1 and 
 * node 2, also between node 3 and node 2.
 * Note:
 * - The size of the BST will be between 2 and 100.
 * - The BST is always valid, each node's value is an integer, and each node's 
 * value is different.
 * 
 * Company: Google
 * Difficulty: easy
 * Similar Questions: 94(BinaryTreeInOrderTraversal)
 */
public class MinimumDistanceBetweenBSTNodes {
    public int minDiffInBST(TreeNode root) {
        int minDiff = Integer.MAX_VALUE;
        TreeNode prev = null, current = root;
        Stack<TreeNode> stack = new Stack<>();
        while (!stack.isEmpty() || current != null) {
            if (current != null) {
                stack.push(current);
                current = current.left;
            }
            else {
                current = stack.pop();
                if (prev != null) {
                    minDiff = Math.min(minDiff, current.val-prev.val);
                }
                prev = current;
                current = current.right;
            }
        }
        return minDiff;
    }

}
