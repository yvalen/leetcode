package leetcode.tree;

public class SerializationBST {
	public String serialize(TreeNode root) {
		if (root == null) return null;
        StringBuilder sb = new StringBuilder();
        serialize_helper(root, sb);
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
        int[] pos = new int[1];
        pos[0] = 0;
        return deserialize_helper(dataAry, pos, Long.MIN_VALUE, Long.MAX_VALUE);
    }
	
	private TreeNode deserialize_helper(String[] dataAry, int[] pos, long min, long max) {
        if (pos[0] == dataAry.length) return null;
		
        int val = Integer.valueOf(dataAry[pos[0]]);
        
        if (val < min || val > max) return null;
        
        TreeNode node = new TreeNode(val);
        pos[0]++;
        node.left = deserialize_helper(dataAry, pos, min, val);
        node.right = deserialize_helper(dataAry, pos, val, max);
        return node;
    }
}
