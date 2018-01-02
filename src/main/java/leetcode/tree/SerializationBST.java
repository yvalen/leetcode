package leetcode.tree;

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
        if (root == null)
            return null;
        StringBuilder sb = new StringBuilder();
        serialize_helper(root, sb);
        return sb.toString();
    }

    // pre order
    private void serialize_helper(TreeNode root, StringBuilder sb) {
        if (root == null)
            return;
        sb.append(root.val).append(",");
        serialize_helper(root.left, sb);
        serialize_helper(root.right, sb);
    }

    public TreeNode deserialize(String data) {
        if (data == null)
            return null;
        String[] dataAry = data.split(",");
        int[] pos = new int[1];
        pos[0] = 0;
        return deserialize_helper(dataAry, pos, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private TreeNode deserialize_helper(String[] dataAry, int[] pos, long min, long max) {
        if (pos[0] == dataAry.length)
            return null;

        int val = Integer.valueOf(dataAry[pos[0]]);

        if (val < min || val > max)
            return null;

        TreeNode node = new TreeNode(val);
        pos[0]++;
        node.left = deserialize_helper(dataAry, pos, min, val);
        node.right = deserialize_helper(dataAry, pos, val, max);
        return node;
    }
}
