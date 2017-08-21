package leetcode.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
 * Given a non-empty binary tree, return the average value of the nodes on each level in the form of an array.
 * Example 1:
 * Input:
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * Output: [3, 14.5, 11]
 * Explanation: The average value of nodes on level 0 is 3,  on level 1 is 14.5, and on level 2 is 11. Hence return [3, 14.5, 11].
 * Note: The range of node's value is in the range of 32-bit signed integer.
 * 
 * Company: Facebook
 * Difficulty: easy
 */
public class AverageOfLevels {
	public List<Double> averageOfLevels(TreeNode root) {
		List<Double> result = new ArrayList<>();
		if (root == null) return result;
		
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
        	long sum = 0;
        	int size = queue.size();
        	for (int i = 0; i < size; i++) {
        		TreeNode node = queue.poll();
        		if (node.left != null) queue.offer(node.left);
        		if (node.right != null) queue.offer(node.right);
        		sum += node.val;
        	}
        	result.add(Double.valueOf(sum) / size);
        }
		
		return result;
    }

}
