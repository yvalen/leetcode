package leetcode.tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/*
 * LEETCODE 449
 * Serialization is the process of converting a data structure or object into a sequence of bits 
 * so that it can be stored in a file or memory buffer, or transmitted across a network connection 
 * link to be reconstructed later in the same or another computer environment.
 * Design an algorithm to serialize and deserialize a binary search tree. There is no restriction on 
 * how your serialization/deserialization algorithm should work. You just need to ensure that a binary 
 * search tree can be serialized to a string and this string can be deserialized to the original structure.
 * The encoded string should be as compact as possible.
 * Note: Do not use class member/global/static variables to store states. 
 * Your serialize and deserialize algorithms should be stateless.
 * 
 * Company: Amazon
 * Difficulty: medium
 * Similar Question: 297(SerializeDeserialize), 652(FindDuplicateSubtree)
 */
public class SerializationBST {
    // Time complexity: O(nlogn) - average,  O(n^2) - worst case unbalanced tree
    public String serialize(TreeNode root) {
        if (root == null) return null;
        StringBuilder sb = new StringBuilder();
        
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            sb.append(node.val).append(",");
            if (node.right != null) stack.push(node.right);
            if (node.left != null) stack.push(node.left);
        }
        
        //serialize_helper(root, sb);
        return sb.toString();
    }

    // pre order
    private void serialize_helper(TreeNode root, StringBuilder sb) {
        if (root == null) return;
        sb.append(root.val).append(",");
        serialize_helper(root.left, sb);
        serialize_helper(root.right, sb);
    }

    public TreeNode deserialize(String data) {
        if (data == null) return null;
        String[] dataAry = data.split(",");
        Queue<Integer> queue = new LinkedList<>();
        for (String d : dataAry) queue.offer(Integer.parseInt(d));
        return buildTree(queue);
    }
    
    // Time complexity: O(nlogn) average, O(n^2) for unbalanced tree
    private TreeNode buildTree(Queue<Integer> queue) {
        if (queue.isEmpty()) return null;
        
        TreeNode root = new TreeNode(queue.poll());
        Queue<Integer> left = new LinkedList<>();
        while (!queue.isEmpty() && queue.peek() < root.val) left.offer(queue.poll());
        root.left = buildTree(left);
        root.right = buildTree(queue);
        return root;
    }
}
