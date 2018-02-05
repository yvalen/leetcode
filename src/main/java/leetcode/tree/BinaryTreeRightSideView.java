package leetcode.tree;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
 * LEETCODE 199 
 * Given a binary tree, imagine yourself standing on the right side of it, 
 * return the values of the nodes you can see ordered from top to bottom.
 * For example: given the following binary tree,
 *    1            <---
 *  /   \
 * 2     3         <---
 *  \     \
 *   5     4       <---
 * You should return [1, 3, 4]. 
 * 
 * Company: Amazon
 * Difficulty: medium
 * Similar Questions: 116(PopulateNextRightPointer), 545(BoundaryOfBinaryTree)
 */
public class BinaryTreeRightSideView {
    public List<Integer> rightSideView_bfs(TreeNode root) {
        if (root == null)
            return Collections.emptyList();

        List<Integer> result = new LinkedList<>();
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        nodeQueue.offer(root);
        while (!nodeQueue.isEmpty()) {
            int size = nodeQueue.size();
            for (int i = 0; i < size; i++) {
                TreeNode current = nodeQueue.poll();
                if (current.left != null)
                    nodeQueue.offer(current.left);
                if (current.right != null)
                    nodeQueue.offer(current.right);
                if (i == size - 1)
                    result.add(current.val);
            }
        }
        return result;
    }
   
    // pre order, right first then left
    public List<Integer> rightSideView_dfs(TreeNode root) {
        List<Integer> result = new LinkedList<>();
        rightSideView_dfs_helper(root, result, 1);
        return result;
    }

    public void rightSideView_dfs_helper(TreeNode root, List<Integer> result, int depth) {
        if (root == null)
            return;

        if (result.size() < depth) {
            result.add(root.val);
        }
        rightSideView_dfs_helper(root.right, result, depth + 1);
        rightSideView_dfs_helper(root.left, result, depth + 1);
    }
}
