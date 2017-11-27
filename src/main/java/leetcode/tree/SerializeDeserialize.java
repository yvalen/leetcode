package leetcode.tree;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
 * LEETCODE 297
 * Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file or 
 * memory buffer, or transmitted across a network connection link to be reconstructed later in the same or another computer environment.
 * Design an algorithm to serialize and deserialize a binary tree. There is no restriction on how your serialization/deserialization 
 * algorithm should work. You just need to ensure that a binary tree can be serialized to a string and this string can be deserialized 
 * to the original tree structure. For example, you may serialize the following tree
 *     1
 *    / \
 *   2   3
 *  	/ \
 *     4   5
 * as "[1,2,3,null,null,4,5]", just the same as how LeetCode OJ serializes a binary tree. You do not necessarily need to follow this format, 
 * so please be creative and come up with different approaches yourself.
 * Note: Do not use class member/global/static variables to store states. Your serialize and deserialize algorithms should be stateless. 
 * 
 * Company: Google, Facebook, Microsoft, Amazon, Bloomberg, LinkedIn, Uber, Yahoo
 * Difficulty: hard
 * Similar Questions: 449(SerializationBST), 652(FindDuplicateSubtree)
 */
public class SerializeDeserialize {
	//
	// BFS
	//
	public static String serialize_bfs(TreeNode root) {
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

	public static TreeNode deserialize_bfs(String data) {
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
			if (i < vals.length-1 && !vals[++i].equals("null")) {
				TreeNode right = new TreeNode(Integer.valueOf(vals[i]));
				nodeQueue.offer(right);
				node.right = right;
			}
			
		}
		return root;
    }

	//
	// DFS preorder
	//
	public String serialize_dfs(TreeNode root) {
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

	public TreeNode deserialize_dfs(String data) {
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
	
	public static void main(String[] args) {
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		root.right = new TreeNode(3);
		root.right.left = new TreeNode(4);
		root.right.right = new TreeNode(5);
		SerializeDeserialize sd = new SerializeDeserialize();
		System.out.println(sd.serialize_dfs(root));
	}
}
