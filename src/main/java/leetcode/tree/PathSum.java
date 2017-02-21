package leetcode.tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class PathSum {
	/**
	 * Given a binary tree and a sum determine if the tree has a root-to-leaf path 
	 * such that adding up all the values along the path equals the given sum. 
	 * (EASY)
	 */
	public boolean hasPathSum(TreeNode root, int sum) {
		if (root == null) return false;
		
		if (root.left == null && root.right == null) {
			return root.val == sum;
		}
		
		return (hasPathSum(root.left, sum-root.val) || hasPathSum(root.right, sum-root.val));
    }
	
	
	/**
	 * Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum.
	 * (MEDIUM)
	 */
	public List<List<Integer>> pathSumII(TreeNode root, int sum) {
        List<List<Integer>> result = new LinkedList<>();
        Stack<Integer> path = new Stack<>();
        pathSumIIHelper(root, sum, path, result);
        return result;
    }
	
	private void pathSumIIHelper(TreeNode root, int sum, Stack<Integer> path, List<List<Integer>> result) {
		if (root == null) return;
		
		path.push(root.val);
		if (root.left == null && root.right == null && sum == root.val) {
			result.add(new ArrayList<Integer>(path));
		}
		else {
			pathSumIIHelper(root.left, sum-root.val, path, result);
			pathSumIIHelper(root.right, sum-root.val, path, result);
		}
		path.pop();
	}
	
	/**
	 * You are given a binary tree in which each node contains an integer value.
	 * Find the number of paths that sum to a given value. The path does not need 
	 * to start or end at the root or a leaf, but it must go downwards 
	 * (traveling only from parent nodes to child nodes).
	 * The tree has no more than 1,000 nodes and the values are in the range -1,000,000 to 1,000,000.
	 */
	// DFS, check all the paths from the current node, then use its children as root and restart
	// Complexity: O(n^2) worst case, O(nlogn) for balanced tree
	public int pathSumIII_dfs(TreeNode root, int sum) {
        if (root == null) return 0;
		return pathSumIII_dfs_helper(root, sum) + 
				pathSumIII_dfs(root.left, sum) + // apply the function instead of the helper for its children
				pathSumIII_dfs(root.right , sum);
    }
	
	// helper function recursively calculate the result from root
	private int pathSumIII_dfs_helper(TreeNode root, int sum) {
        int result = 0;
        if (root == null) return result;
        if (root.val == sum) result++;
        result += pathSumIII_dfs_helper(root.left, sum - root.val);
        result += pathSumIII_dfs_helper(root.right, sum - root.val);
		return result;
    }
	
	// Complexity: O(n)
	public int pathSumIII_prefixSumMap(TreeNode root, int sum) {
		Map<Integer, Integer> prefixSumMap = new HashMap<>();
		prefixSumMap.put(0, 1);
		return prefixSumHelper(root, 0, sum, prefixSumMap);
	}
	
	private int prefixSumHelper(TreeNode current, int currentSum, int sum, Map<Integer, Integer> prefixSumMap) {
		if (current == null) return 0;
		
		// add current value to currentSum
		currentSum += current.val;
		
		// get the number of valid paths end at current
		// sum of any node in the middle of the path to the current node is the difference 
		// between the sum from the root to the current node and the prefix sum of the node in the middle.
		int result = prefixSumMap.getOrDefault(currentSum - sum, 0);
		
		// update map with current sum for the next recursion
		prefixSumMap.put(currentSum, prefixSumMap.getOrDefault(currentSum, 0) + 1);
		
		// add result starting from current's children
		result += prefixSumHelper(current.left, currentSum, sum, prefixSumMap) +
				prefixSumHelper(current.right, currentSum, sum, prefixSumMap);
		
		// restore the map, as the recursion goes from the bottom to the top
		prefixSumMap.put(currentSum, prefixSumMap.get(currentSum) - 1);
		
		return result;
	}
	
	
	/**
	 *  Given a binary tree, find the maximum path sum. For this problem, a path is defined as any sequence 
	 *  of nodes from some starting node to any node in the tree along the parent-child connections. 
	 *  The path must contain at least one node and does not need to go through the root.
	 *  For example: given the below binary tree,
	 *         1
	 *        / \
	 *       2   3
	 *	Return 6. 
	 */
	// global variable to record the maximum value 
	private int maxSum;
	
	public int maxPathSum(TreeNode root) {
		maxSum = Integer.MIN_VALUE;
		maxPathSum_helper(root);
		return maxSum;
    }

	private int maxPathSum_helper(TreeNode root) {
		if (root == null) return 0;
		
		int leftMaxSum = Integer.max(0,  maxPathSum_helper(root.left));
		int rightMaxSum = Integer.max(0,  maxPathSum_helper(root.right));
		maxSum = Integer.max(maxSum,  root.val + leftMaxSum + rightMaxSum);
		return Integer.max(leftMaxSum, rightMaxSum) + root.val;
	}
	
	public static void main(String[] args) {
		PathSum p = new PathSum();
		
		String s = "1,null,2,null,3,null,4,null,5";
		TreeNode root = SerializationBFS.deserialize(s);
		int sum = 3;
		System.out.println(p.pathSumIII_dfs(root, sum));
		
		
	}
}
