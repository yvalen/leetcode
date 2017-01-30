package leetcode.dp;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import leetcode.tree.TreeNode;

public class UniqueBinarySearchTree {
	/*
	 * Given n, how many structurally unique BST's (binary search trees) that store values 1...n?
	 * For example, given n = 3, there are a total of 5 unique BST's.
	 * 1         3     3      2      1
	 *  \       /     /      / \      \
	 *   3     2     1      1   3      2
	 *  /     /       \                 \
	 * 2     1         2                 3
	 */
	public int numTrees(int n) {
        if (n < 0) return 0;
        
        if (n == 0) return 1;
        
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
        	for (int j = 0; j < i; j++) {
        		dp[i] += dp[j] * dp[i - j -1];
        	}
        }
		
		return dp[n];
    }
	
	/*
	 * Given an integer n, generate all structurally unique BST's (binary search trees) 
	 * that store values 1...n.
	 */
	public List<TreeNode> generateTrees(int n) {
       if (n == 0) return Collections.emptyList();
       
       return generateTrees_helper(1, n);
    }
	
	private List<TreeNode> generateTrees_helper(int start, int end) {
		List<TreeNode> result = new LinkedList<>();
		
		if (end < start) {
			result.add(null);
			return result;
		}
		
		for (int i = start; i<= end; i++) {
			List<TreeNode> left = generateTrees_helper(start, i -1);
			List<TreeNode> right = generateTrees_helper(i + 1, end);
			for (TreeNode l : left) {
				for (TreeNode r : right) {
					TreeNode node = new TreeNode(i);
					node.left = l;
					node.right = r;
					result.add(node);
				}
			}
		}
		
		return result;
	}
	
	public static void main(String[] args) {
		UniqueBinarySearchTree u = new UniqueBinarySearchTree();
		
		List<TreeNode> result = u.generateTrees(3);
		System.out.println(result);
		
	}
}
