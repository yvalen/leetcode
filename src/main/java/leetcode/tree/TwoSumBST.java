package leetcode.tree;

import java.util.HashSet;
import java.util.Set;

/*
 * Given a Binary Search Tree and a target number, return true if there exist two elements in the BST such that their sum is equal to the given target.
 * Example 1:
 * Input: 
 *     5
 *    / \
 *   3   6
 *  / \   \
 * 2   4   7
 * Target = 9
 * Output: True
 * Example 2:
 * Input: 
 *     5
 *    / \
 *   3   6
 *  / \   \
 * 2   4   7
 * Target = 28
 * Output: False
 * 
 * Company: Facebook, Samsung
 * Difficulty: easy
 */
public class TwoSumBST {
	
	// Time complexity: O(n)  Space complexity: O(n)
	public boolean findTarget(TreeNode root, int k) {
        return dfs(root, k, new HashSet<>());
    }

	private boolean dfs(TreeNode root, int k, Set<Integer> set) {
		if (root == null) return false;
		if (set.contains(k-root.val)) return true;
		set.add(root.val);
		return dfs(root.left, k, set) || dfs(root.right, k, set);
	}
	
	
	public static void main(String[] args) {
		TwoSumBST tsb = new TwoSumBST ();
		TreeNode root = new TreeNode(2);
		root.left = new TreeNode(1);
		root.right = new TreeNode(3);
		int k = 4;
		System.out.println(tsb.findTarget(root, k));
	}
}
