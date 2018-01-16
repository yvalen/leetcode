package leetcode.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;


public class SerializeDeserializeNaryTree {
    private static class TreeNode {
        private int val;
        private List<TreeNode> children;
        TreeNode(int val) {
            this.val = val;
            children = new ArrayList<>();
        }
    }
    
    // Each root is serialized as (root serialize(child1) serialize(child2) ...serialize(childn))
    public String serialize(TreeNode root) {
        if (root == null) return null;
        StringBuilder sb = new StringBuilder();
        buildString(root, sb);
        return sb.toString();
    }
    
    private void buildString(TreeNode root, StringBuilder sb) {
        if (root == null) return;
        
        sb.append("(").append(root.val);
        for (TreeNode child : root.children) {
            buildString(child, sb);
        }
        sb.append(")");
    }
    
    // Read charcter
    // If it is ''(", new node is added to current parent in the tree. Push it to a stack. 
    // The newly inserted node becomes current parent.
    // If it is '')", pop from the stack. Parent is updated.
    public TreeNode deserialize(String data) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode root = null, parent = null;
        int pos = 0;
        while (pos < data.length()) {
            if (data.charAt(pos) == '(') {
                int num = 0;
                pos++;
                while (Character.isDigit(data.charAt(pos))) {
                    num = num * 10 + (data.charAt(pos) - '0');
                    pos++;
                }
                TreeNode node = new TreeNode(num);
                if (parent == null) {
                    root = node;
                }
                else {
                    parent.children.add(node);
                }
                stack.push(node);
                parent = node;
            }
            else if (data.charAt(pos) == ')') {
                stack.pop();
                if (!stack.isEmpty()) {
                    parent = stack.peek();
                }
                pos++;
            }
        }
        return root;
    }
 
    public static void main(String[] args) {
        SerializeDeserializeNaryTree codec = new SerializeDeserializeNaryTree();
        String data = "(14(20)(3(5)(61))(42(7)))";
        System.out.println(codec.serialize(codec.deserialize(data)));
    }
    
}
