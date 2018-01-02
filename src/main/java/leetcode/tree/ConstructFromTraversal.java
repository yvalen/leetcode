package leetcode.tree;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class ConstructFromTraversal {
    /**
     * Given preorder and inorder traversal of a tree, construct the binary
     * tree.
     * 
     * Company: Bloomberg Difficulty: medium
     */
    public TreeNode buildTree_preorderInOrder(int[] preorder, int[] inorder) {
        if (preorder == null || inorder == null || preorder.length != inorder.length || preorder.length == 0) {
            return null;
        }

        int n = inorder.length;
        Map<Integer, Integer> inorderMap = new HashMap<Integer, Integer>();
        for (int i = 0; i < n; i++) {
            inorderMap.put(inorder[i], i);
        }

        return buildTree_preorderInOrder_helper(preorder, 0, inorder, 0, n - 1, inorderMap);
    }

    private TreeNode buildTree_preorderInOrder_helper(int[] preorder, int preStart, int[] inorder, int inStart,
            int inEnd, Map<Integer, Integer> inorderMap) {
        if (inStart > inEnd) {
            return null;
        }

        TreeNode root = new TreeNode(preorder[preStart]);
        int rootIdx = inorderMap.get(root.val);
        root.left = buildTree_preorderInOrder_helper(preorder, preStart + 1, inorder, inStart, rootIdx - 1, inorderMap);
        root.right = buildTree_preorderInOrder_helper(preorder, preStart + rootIdx - inStart + 1, inorder, rootIdx + 1,
                inEnd, inorderMap);

        return root;
    }

    /**
     * Given inorder and postorder traversal of a tree, construct the binary
     * tree.
     * 
     * Company: Microsoft Difficulty: medium
     */
    public TreeNode buildTree_postorderInOrder(int[] inorder, int[] postorder) {
        if (postorder == null || inorder == null || postorder.length != inorder.length || inorder.length == 0) {
            return null;
        }

        int n = inorder.length;
        Map<Integer, Integer> inorderMap = new HashMap<Integer, Integer>();
        for (int i = 0; i < n; i++) {
            inorderMap.put(inorder[i], i);
        }

        return buildTree_postorderInOrder_helper(inorder, 0, n - 1, postorder, 0, n - 1, inorderMap);
    }

    private TreeNode buildTree_postorderInOrder_helper(int[] inorder, int inStart, int inEnd, int[] postorder,
            int postStart, int postEnd, Map<Integer, Integer> inorderMap) {
        if (postStart > postEnd || inStart > inEnd) {
            return null;
        }

        TreeNode root = new TreeNode(postorder[postEnd]);
        int rootIdx = inorderMap.get(root.val);
        int numsInRight = inEnd - rootIdx;

        root.left = buildTree_postorderInOrder_helper(inorder, inStart, rootIdx - 1, postorder, postStart,
                postEnd - numsInRight - 1, inorderMap);
        root.right = buildTree_postorderInOrder_helper(inorder, rootIdx + 1, inEnd, postorder, postEnd - numsInRight,
                postEnd - 1, inorderMap);

        return root;
    }

    public static void main(String[] args) {
        ConstructFromTraversal c = new ConstructFromTraversal();

        int[] preorder = { 1, 2, 3, 4 };
        int[] inorder = { 1, 2, 3, 4 };
        TreeNode root = c.buildTree_preorderInOrder(preorder, inorder);

        System.out.println(BinaryTreeInOrderTraversal.inorderTraversalIterative(root));
    }
}
