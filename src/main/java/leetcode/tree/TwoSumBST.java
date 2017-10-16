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
 * Similar Questions: 1(2sum), 167(Two Sum II, sorted input array), 170(TwoSum, data structure design)
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
	
	//
	// binary search: For each node, we check if k - node.val exists in this BST.
	// Time complexity: O(nlogn)  Space complexity: O(logn) recursion
	public boolean findTarget_binarySearch(TreeNode root, int k) {
        return find(root, root, k);
    }
    private boolean find(TreeNode root, TreeNode current, int k) { 
        if (root == null || current == null) return false;
        return search(root, current, k-current.val) ||  // should subtract current.val from k instead of root.val as current is the node checking right now
        		find(root, current.left, k) ||  // should always check from root consider a case [2,1,3] k=4
        		find(root, current.right,  k);
    }
    private boolean search(TreeNode root, TreeNode current, int target) {
        if (root == null) return false;
        
        if (root.val == target && root != current) return true; // need to check if root is the current node
        
        if (target < root.val) return search(root.left, current, target);
        else return search(root.right, current, target);
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
