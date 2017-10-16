package leetcode.tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class PathSum {
	/**
	 * LEETCODE 112
	 * Given a binary tree and a sum determine if the tree has a root-to-leaf path 
	 * such that adding up all the values along the path equals the given sum. 
	 * 
	 * Company: Microsoft
	 * Difficulty: easy
	 * Similar Questions: 113(Path Sum II), 437(Path Sum III), 666(Path Sum IV), 129(SumRootToLeaf), 124(Binary Tree Max Path Sum)
	 */
	public boolean hasPathSum(TreeNode root, int sum) {
		if (root == null) return false;
		
		if (root.left == null && root.right == null) { // it needs to be a root-to-leaf path
			return root.val == sum;
		}
		
		return (hasPathSum(root.left, sum-root.val) || hasPathSum(root.right, sum-root.val));
    }
	
	
	/**
	 * LEETCODE 113
	 * Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum.
	 * For example: Given the below binary tree and sum = 22,
	 *              5
	 *             / \
	 *            4   8
	 *           /   / \
	 *         11  13  4
	 *        /  \    / \
	 *       7    2  5   1
	 * return
	 * [
	 * 	[5,4,11,2],
	 * 	[5,8,4,5]
	 * ]
	 * 
	 * Company: Bloomberg
	 * Difficulty: medium
	 * Similar Questions: 112(Path Sum), 437(Path Sum III), 666(PathSumIV), 257(BinaryTreePath)
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
		path.pop(); // need to do backtrack for both cases
	}
	
	/**
	 * LEETCODE 437
	 * You are given a binary tree in which each node contains an integer value.
	 * Find the number of paths that sum to a given value. The path does not need 
	 * to start or end at the root or a leaf, but it must go downwards 
	 * (traveling only from parent nodes to child nodes).
	 * The tree has no more than 1,000 nodes and the values are in the range -1,000,000 to 1,000,000.
	 * Example: root = [10,5,-3,3,2,null,11,3,-2,null,1], sum = 8
	 *       10
	 *      /  \
	 *     5   -3
	 *    / \    \
	 *   3   2   11
	 *  / \   \
	 * 3  -2   1
	 * Return 3. The paths that sum to 8 are:
	 * 1.  5 -> 3
	 * 2.  5 -> 2 -> 1
	 * 3. -3 -> 11
	 * 
	 * Difficulty: easy
	 * Similar Questions: 687(LongestUnivaluePath)
	 */
	// DFS, check all the paths from the current node, then use its children as root and restart
	// Complexity: O(n^2) worst case, O(nlogn) for balanced tree
	public int pathSumIII_dfs(TreeNode root, int sum) {
        if (root == null) return 0;
        /*
        System.out.println("root="+root.val);
        int p = pathSumIII_dfs_helper(root, sum);
        System.out.println("p="+p);
        int l = pathSumIII_dfs_helper(root.left, sum);
        System.out.println("l="+l);
        int r = pathSumIII_dfs_helper(root.right , sum);
        System.out.println("r="+r);
        return p + l + r;
        */
        
		return pathSumIII_dfs_helper(root, sum) + 
				pathSumIII_dfs(root.left, sum) + // apply the function instead of the helper so that each child can be used as a starting node
				pathSumIII_dfs(root.right , sum);
    }
	
	// helper function recursively calculate the result from the starting node
	private int pathSumIII_dfs_helper(TreeNode root, int sum) {
		if (root == null) return 0;
		/*
		int count =  (root.val == sum ? 1 : 0) +
				pathSumIII_dfs_helper(root.left, sum - root.val) +
				pathSumIII_dfs_helper(root.right, sum - root.val);
		System.out.println("   current=" + root.val + " sum=" + sum + " count=" + count);
		return count;
		*/
		return (root.val == sum ? 1 : 0) +
				pathSumIII_dfs_helper(root.left, sum - root.val) +
				pathSumIII_dfs_helper(root.right, sum - root.val);
		/*
        int result = 0;
        if (root == null) return result;
        if (root.val == sum) result++;
        result += pathSumIII_dfs_helper(root.left, sum - root.val);
        result += pathSumIII_dfs_helper(root.right, sum - root.val);
		return result;
		*/
    }
	
	
    // 1. The prefix stores the sum from the root to the current node in the recursion
    // 2. The map stores <prefix sum, frequency> pairs before getting to the current node. We can imagine a path from the root 
	// to the current node. The sum from any node in the middle of the path to the current node = the difference between the sum 
	// from the root to the current node and the prefix sum of the node in the middle.
    // 3. We are looking for some consecutive nodes that sum up to the given target value, which means the difference discussed in 2. 
	// should equal to the target value. In addition, we need to know how many differences are equal to the target value. 
	// 4. Here comes the map. The map stores the frequency of all possible sum in the path to the current node. If the difference between 
	// the current sum and the target value exists in the map, there must exist a node in the middle of the path, such that from this node 
	// to the current node, the sum is equal to the target value.
    // 5. Note that there might be multiple nodes in the middle that satisfy what is discussed in 4. The frequency in the map is used to help with this.
    // 6. Therefore, in each recursion, the map stores all information we need to calculate the number of ranges that sum up to target. 
	// Note that each range starts from a middle node, ended by the current node.
    // 7. To get the total number of path count, we add up the number of valid paths ended by EACH node in the tree.
    // 8. Each recursion returns the total count of valid paths in the subtree rooted at the current node. And this sum can be divided into three parts:
	// - the total number of valid paths in the subtree rooted at the current node's left child
    // - the total number of valid paths in the subtree rooted at the current node's right child
    // - the number of valid paths ended by the current node
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
		// get this before updating prefixSumMap
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
	 * LEETCODE 124
	 * Given a binary tree, find the maximum path sum. For this problem, a path is defined as any sequence 
	 * of nodes from some starting node to any node in the tree along the parent-child connections. 
	 * The path must contain at least one node and does not need to go through the root.
	 * For example: given the below binary tree,
	 *         1
	 *        / \
	 *       2   3
	 * Return 6. 
	 * 
	 * Company: Microsoft, Baidu
	 * Difficulty: hard
	 * Similar Questions: 129(SumRootToLeaf), 687(LongestUnivaluePath)
	 */
	// Time complexity: O(n)
	public int maxPathSum(TreeNode root) {
		maxSum = Integer.MIN_VALUE;
		maxPathSum_helper(root);
		return maxSum;
    }
	
	// global variable to record the maximum value 
	private int maxSum;
	private int maxPathSum_helper(TreeNode root) {
		if (root == null) return 0;
		
		int leftMaxSum = Math.max(0,  maxPathSum_helper(root.left)); // don't use max from left if it is negative as it will make the sum smaller
		int rightMaxSum = Math.max(0,  maxPathSum_helper(root.right));
		maxSum = Integer.max(maxSum,  root.val + leftMaxSum + rightMaxSum); // compare the new sum with the max sum obtained so far
		return Integer.max(leftMaxSum, rightMaxSum) + root.val; // return the branch with higher sum, prune the branch with lower sum
	}
	
	public static void main(String[] args) {
		PathSum p = new PathSum();
		
		//String s = "1,null,2,null,3,null,4,null,5";
		//int sum = 3;
		String s = "1,-2,-3,1,3,-2,null,-1";
		int sum = -1;
		TreeNode root = SerializeDeserialize.deserialize_bfs(s);
		
		System.out.println(p.pathSumIII_dfs(root, sum));
		
		
	}
}
