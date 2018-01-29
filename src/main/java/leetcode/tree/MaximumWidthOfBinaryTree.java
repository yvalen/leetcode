package leetcode.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
 * LEETCODE 662
 * Given a binary tree, write a function to get the maximum width of the given tree. The width of a tree is the maximum width among all levels. 
 * The binary tree has the same structure as a full binary tree, but some nodes are null. The width of one level is defined as the length between 
 * the end-nodes (the leftmost and right most non-null nodes in the level, where the null nodes between the end-nodes are also counted into the 
 * length calculation.
 * Example 1:
 * Input: 
 *            1
 *          /   \
 *         3     2
 *        / \     \  
 *       5   3     9 
 * Output: 4
 * Explanation: The maximum width existing in the third level with the length 4 (5,3,null,9).
 * Example 2:
 * Input: 
 *        1
 *       /  
 *      3    
 *     / \       
 *    5   3     
 * Output: 2
 * Explanation: The maximum width existing in the third level with the length 2 (5,3).
 * Example 3:
 * Input: 
 *        1
 *       / \
 *      3   2
 *     /       
 *    5      
 * Output: 2
 * Explanation: The maximum width existing in the second level with the length 2 (3,2).
 * Example 4:
 * Input: 
 *        1
 *       / \
 *      3   2
 *     /     \  
 *    5       9 
 *   /         \
 *  6           7
 * Output: 8
 * Explanation:The maximum width existing in the fourth level with the length 8 (6,null,null,null,null,null,null,7).
 * Note: Answer will in the range of 32-bit signed integer. 
 * 
 * Company: Amazon
 * Difficulty: medium
 */
public class MaximumWidthOfBinaryTree {
    public int widthOfBinaryTree_bfs(TreeNode root) {
        if (root == null)
            return 0;

        Queue<TreeNode> nodeq = new LinkedList<>();
        Queue<Integer> indexq = new LinkedList<>();
        nodeq.offer(root);
        indexq.offer(0);
        int maxWidth = 1;
        while (!nodeq.isEmpty()) {
            int size = nodeq.size();
            int left = 0, right = 0;
            for (int i = 0; i < size; i++) {
                TreeNode node = nodeq.poll();
                int index = indexq.poll();
                if (i == 0)
                    left = index;
                if (i == size - 1)
                    right = index; // cannot use else if here, otherwise, right
                                   // won't be updated properly if queue size is
                                   // 1
                if (node.left != null) {
                    nodeq.offer(node.left);
                    indexq.offer(index * 2); // index of the left child is
                                             // parent_index*2
                }

                if (node.right != null) {
                    nodeq.offer(node.right);
                    indexq.offer(index * 2 + 1); // // index of the right child
                                                 // is parent_index*2+1
                }
            }
            maxWidth = Math.max(maxWidth, right - left + 1);
        }

        return maxWidth;
    }

    private int max = 0;
    private List<Integer> leftNodes = new ArrayList<>(); // store the index of
                                                         // the left-most node

    public int widthOfBinaryTree_dfs(TreeNode root) {
        if (root == null)
            return 0;
        dfs(root, 0, 0);
        return max;
    }

    private void dfs(TreeNode root, int index, int depth) {
        if (root == null)
            return;
        if (depth >= leftNodes.size()) { // depth of root is 0
            leftNodes.add(index);
        }
        max = Math.max(max, index - leftNodes.get(depth) + 1);
        dfs(root.left, index * 2, depth + 1);
        dfs(root.right, index * 2 + 1, depth + 1);
    }

    public static void main(String[] args) {
        MaximumWidthOfBinaryTree mw = new MaximumWidthOfBinaryTree();
        TreeNode root = new TreeNode(0);
        root.right = new TreeNode(0);
        root.right.right = new TreeNode(0);
        root.right.right.right = new TreeNode(0);
        root.right.right.right.left = new TreeNode(0);
        System.out.println(mw.widthOfBinaryTree_bfs(root));
    }
}
