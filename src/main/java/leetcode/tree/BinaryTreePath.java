package leetcode.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * LEETCODE 257 
 * Given a binary tree, return all root-to-leaf paths. 
 * For example, given the following binary tree:
 *      1 
 *     / \ 
 *    2   3 
 *    \ 5 
 * All root-to-leaf paths are: ["1->3->5", "1->2"]
 * 
 * Company: Google, Facebook, Apple 
 * Difficulty: easy 
 * Similar Questions: 113(Path Sum II)
 */
public class BinaryTreePath {

    public List<String> binaryTreePaths(TreeNode root) {
        List<String> paths = new LinkedList<>();
        if (root == null)
            return paths;

        binaryTreePathsHelper(root, Integer.toString(root.val), paths);
        return paths;
    }

    private void binaryTreePathsHelper(TreeNode root, String path, List<String> paths) {
        if (root == null)
            return;

        if (root.left == null && root.right == null) {
            paths.add(path);
            return;
        }

        if (root.left != null) {
            binaryTreePathsHelper(root.left, path + "->" + Integer.toString(root.left.val), paths);
        }

        if (root.right != null) {
            binaryTreePathsHelper(root.right, path + "->" + Integer.toString(root.right.val), paths);
        }
    }

    // Complexity: O(n + e), n is the number of nodes, and e is the number of
    // edges, e = n-1 here, so its O(n))
    // Using StringBuilder, we could make sure that String allocation and copying only happens 
    // when we are at a leaf. That means, the cost would be now sum of length of all root-to-leaf 
    // paths rather than sum of length of all root-to-node paths. This is assuming that 
    // StringBuilder.append and StringBuilder.setLength can work in O(1) or at least less than O(N) time.
    // he best case is when all nodes lies on the same side, i.e. when they form a linked list. 
    // And the time complexity will be O(N) * 1 = O(N).
    // The worst case is a balanced binary tree. The time complexity is O(lgN) * O(N) = O(N lgN). 
    // So it will be O(N) - O(N lgN) time complexity and also space complexity. T(N) = T(N/2) + (N/2) * lgN
    public List<String> binaryTreePaths_backtrack(TreeNode root) {
        List<String> paths = new LinkedList<>();
        StringBuilder sb = new StringBuilder();
        binaryTreePaths_backtrack_helper(root, paths, sb);
        return paths;
    }

    private void binaryTreePaths_backtrack_helper(TreeNode root, List<String> paths, StringBuilder sb) {
        if (root == null)
            return;

        // record the length of StringBuilder before appending anything to it
        int len = sb.length();

        sb.append(root.val);
        if (root.left == null && root.right == null) {
            paths.add(sb.toString());
        } else {
            // only append -> for non leaf node
            sb.append("->");
            binaryTreePaths_backtrack_helper(root.left, paths, sb);
            binaryTreePaths_backtrack_helper(root.right, paths, sb);
        }
        // set the length back after recursion
        sb.setLength(len);
    }

    /**
     * Binary tree path, less optimal, create a list for every recursion
     */
    public List<String> binaryTreePathsNewListEveryRecursion(TreeNode root) {
        List<String> paths = new LinkedList<>();

        if (root == null)
            return paths;
        List<String> leftPaths = binaryTreePathsNewListEveryRecursion(root.left);
        List<String> rightPaths = binaryTreePathsNewListEveryRecursion(root.right);

        String nodeVal = Integer.toString(root.val);
        if (leftPaths.isEmpty() && rightPaths.isEmpty()) {
            paths.add(nodeVal);
        }

        for (String path : leftPaths) {
            paths.add(nodeVal + "->" + path);
        }

        for (String path : rightPaths) {
            paths.add(nodeVal + "->" + path);
        }

        return paths;
    }

}
