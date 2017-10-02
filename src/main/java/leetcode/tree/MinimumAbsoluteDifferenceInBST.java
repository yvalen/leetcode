package leetcode.tree;

import java.util.Stack;

/*
 * LEETCODE 530
 * Given a binary search tree with non-negative values, find the minimum absolute difference between values of any two nodes.
 * Example:
 * Input:
 *    1
 *     \
 *      3
 *     /
 *    2
 * Output: 1
 * Explanation: The minimum absolute difference is 1, which is the difference between 2 and 1 (or between 2 and 3).
 * Note: There are at least two nodes in this BST. 
 * 
 * Company: Google
 * Difficulty: easy
 * Similar Question: 532(KDiffPairsInArray)
 */
public class MinimumAbsoluteDifferenceInBST {
	// Time complexity: O(n)  Space complexity: O(1)
	public int getMinimumDifference(TreeNode root) {
        int min = Integer.MAX_VALUE;
        
        Stack<TreeNode> stack = new Stack<>();
        TreeNode prev = null, current = root;
        while (!stack.isEmpty() || current != null) {
        	if (current != null) {
        		stack.push(current);
        		current = current.left;
        	}
        	else {
        		current = stack.pop();
        		if (prev != null) min = Math.min(min,  current.val - prev.val);
        		prev = current;
        		current = current.right;
        	}
        }
		return min;
    }
	
}
