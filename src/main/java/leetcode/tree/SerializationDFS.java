package leetcode.tree;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SerializationDFS {
	public String serialize(TreeNode root) {
		if (root == null) return null;
		
        StringBuilder sb = new StringBuilder();
        buildString(root, sb);
        return sb.toString();
    }
	
	// pre order
	private void buildString(TreeNode root, StringBuilder sb) {
		if (root == null) {
			sb.append("null").append(",");
		} else {
			sb.append(root.val).append(",");
			buildString(root.left, sb);
			buildString(root.right, sb);
		}
	}

	public TreeNode deserialize(String data) {
		if (data == null) return null;
		Queue<String> dataList = new LinkedList<>();
		dataList.addAll(Stream.of(data.split(",")).collect(Collectors.toList()));
		return buildTree(dataList);
    }
	
	private TreeNode buildTree(Queue<String> dataList) {
		// remove the first element in each recursion
		String val = dataList.remove();
		if (val.equals("null")) return null;
		TreeNode node = new TreeNode(Integer.valueOf(val));
		node.left = buildTree(dataList);
		node.right = buildTree(dataList);
		return node;
	}

}
