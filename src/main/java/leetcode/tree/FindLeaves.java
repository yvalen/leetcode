package leetcode.tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
 * LEETCODE 366
 * Given a binary tree, collect a tree's nodes as if you were doing this: 
 * Collect and remove all leaves, repeat until the tree is empty.
 * Example: Given binary tree
 *           1
 *          / \   
 *         2   3 
 *        / \     
 *       4   5    
 * Returns [4, 5, 3], [2], [1].
 * Explanation:
 * 1. Removing the leaves [4, 5, 3] would result in this tree:
 *           1
 *          / 
 *         2          
 * 2. Now removing the leaf [2] would result in this tree:
 *           1          
 * 3. Now removing the leaf [1] would result in the empty tree:
 * 			[]         
 * Returns [4, 5, 3], [2], [1]. 
 * 
 * Company: LinkedIn
 * Difficulty: medium
 */
public class FindLeaves {
    // find the height of each node, the leaf node has height of 0
    public List<List<Integer>> findLeaves_useHeight(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        height(root, result);
        return result;
    }

    private int height(TreeNode root, List<List<Integer>> result) {
        if (root == null)
            return -1;

        int h = 1 + Math.max(height(root.left, result), height(root.right, result));
        if (result.size() == h)
            result.add(new ArrayList<>());
        result.get(h).add(root.val);
        return h;
    }

    //
    // DFS
    //
    public List<List<Integer>> findLeaves_dfs(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();

        while (root != null) {
            List<Integer> leaves = new ArrayList<>();
            root = dfs(root, leaves);
            result.add(leaves);
        }

        return result;
    }

    private TreeNode dfs(TreeNode root, List<Integer> leaves) {
        if (root == null)
            return null;

        if (root.left == null && root.right == null) {
            leaves.add(root.val);
            return null;
        }

        root.left = dfs(root.left, leaves);
        root.right = dfs(root.right, leaves);

        return root;
    }
}
