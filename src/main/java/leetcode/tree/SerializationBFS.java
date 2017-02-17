package leetcode.tree;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SerializationBFS {
	public String serialize(TreeNode root) {
		if (root == null) return null;
		
        StringBuilder sb = new StringBuilder();
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        nodeQueue.offer(root);
        while (!nodeQueue.isEmpty()) {
        	TreeNode current = nodeQueue.poll();
        	if (current == null) {
        		sb.append("null").append(",");
        	}
        	else {
        		sb.append(current.val).append(",");
        		nodeQueue.offer(current.left);
        		nodeQueue.offer(current.right);
        	}
        }
        
        return sb.toString();
	}

	public TreeNode deserialize(String data) {
		if (data == null) return null;
		
		String[] vals = data.split(",");
		Queue<TreeNode> nodeQueue = new LinkedList<>();
		TreeNode root = new TreeNode(Integer.valueOf(vals[0]));
		nodeQueue.offer(root);
		for (int i = 1; i < vals.length; i++) {
			TreeNode node = nodeQueue.poll();
			if (!vals[i].equals("null")) {
				TreeNode left = new TreeNode(Integer.valueOf(vals[i]));
				nodeQueue.offer(left);
				node.left = left;
			}
			if (!vals[++i].equals("null")) {
				TreeNode right = new TreeNode(Integer.valueOf(vals[i]));
				nodeQueue.offer(right);
				node.right = right;
			}
			
		}
		return root;
    }

}
