package leetcode.tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
 * LEETCODE 314
 * Given a binary tree, return the vertical order traversal of its nodes' values. 
 * (i.e, from top to bottom, column by column). If two nodes are in the same row 
 * and column, the order should be from left to right.
 * Examples: 
 * Given binary tree [3,9,20,null,null,15,7],
 *        3
 *       / \
 *      9  20
 *        /  \
 *       15   7
 * return its vertical order traversal as:
 * [
 * 	[9],
 *	[3,15],
 * 	[20],
 * 	[7]
 * ]
 * Given binary tree [3,9,8,4,0,1,7],
 *        3
 *       / \
 *      9   20 
 *    /  \ /  \
 *   4   0 1   7
 * return its vertical order traversal as:
 * [
 * 	[4],
 * 	[9],
 * 	[3,0,1],
 * 	[8],
 * 	[7]
 * ]
 * Given binary tree [3,9,8,4,0,1,7,null,null,null,2,5] 
 * (0's right child is 2 and 1's left child is 5),
 *        3
 *       / \
 *      9   20 
 *    /  \ /  \
 *   4   0 1   7
 *      /  \
 *     5   2
 * return its vertical order traversal as:
 * [
 * 	[4],
 * 	[9,5],
 * 	[3,0,1],
 * 	[8,2],
 * 	[7]
 * ]
 * 
 * Company: Google, Snapchat, Facebook
 * Difficulty: medium
 * Similar Questions: 102(Binary Tree Level Order Traversal)
 */
public class BinaryTreeVerticalOrderTraversal {
    // BFS, put node, col into queue at the same time
    // Every left child access col - 1 while right child col + 1
    // This maps node into different col buckets
    // Get col boundary min and max on the fly
    // Retrieve result from cols
    public List<List<Integer>> verticalOrder(TreeNode root) {
        if (root == null)
            return Collections.emptyList();

        List<List<Integer>> result = new ArrayList<>();
        Queue<TreeNode> nodeq = new LinkedList<>();
        Queue<Integer> colq = new LinkedList<>();
        nodeq.offer(root);
        colq.offer(0);
        // minCol and maxCol is the range of the current col numbers,
        // use them to decide whether to prepend to the list or append to the list
        int minCol = 0, maxCol = 0; 
        result.add(new ArrayList<>()); // add a list to result for col 0
        while (!nodeq.isEmpty()) {
            TreeNode node = nodeq.poll();
            int col = colq.poll();
            if (col < minCol) {
                result.add(0, new ArrayList<>());
                minCol = col;
            }
            else if (col > maxCol) {
                result.add(new ArrayList<>());
                maxCol = col;
            }
            result.get(col-minCol).add(node.val);

            if (node.left != null) {
                nodeq.offer(node.left);
                colq.offer(col - 1);
            }

            if (node.right != null) {
                nodeq.offer(node.right);
                colq.offer(col + 1);
            }
        }

        return result;
    }
}
