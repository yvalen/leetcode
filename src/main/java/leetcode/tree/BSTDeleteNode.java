package leetcode.tree;

/*
 * Given a root node reference of a BST and a key, delete the node with the given key in the BST. 
 * Return the root node reference (possibly updated) of the BST.
 * Basically, the deletion can be divided into two stages:
 * 	1. Search for a node to remove.
 * 	2. If the node is found, delete the node.
 * Note: Time complexity should be O(height of tree).
 */
public class BSTDeleteNode {
	
	// Complexity: O(h) every node along the path to be visited best case once (delete leaf), 
	// worst case twice (delete root).
	public TreeNode deleteNode(TreeNode root, int key) {
		if (root == null) return null;
		
		if (root.val > key) {
			root.left = deleteNode(root.left, key);
		}
		else if (root.val < key) {
			root.right = deleteNode(root.right, key);
		}
		else {
			// found node with key
		
			// return left child if node only has left sub tree
			if (root.right == null) return root.left;
			
			// return right child if node only has right sub tree
			if (root.left == null) return root.right;
			
			// find the minimum vale in the right sub tree
			TreeNode minNode = findMin(root.right);
			
			// set the current node value with the minimum value
			root.val = minNode.val;
			
			// delete the minimum value node
			root.right = deleteNode(root.right, minNode.val);
			
			/*
			TreeNode minNode = root.right;
			while (minNode.left != null) { 
				minNode = minNode.left;
			}
			minNode.left = root.left;
			return root.right;
			*/
		}
		
		return root;
    }
	
	private TreeNode findMin(TreeNode node) {
		while (node.left != null) {
			node = node.left;
		}
		return node;
	}
	
	private TreeNode deleteMin(TreeNode node) {
		if (node == null) return null;
		
		if (node.left == null) {
			return node.right;
		}
		node.left = deleteMin(node.left);
		return node;
	}
	
	public static void main(String[] args) {
		TreeNode root = new TreeNode(5);
		root.left = new TreeNode(3);
		root.right = new TreeNode(6);
		root.left.left = new TreeNode(2);
		root.left.right = new TreeNode(4);
		root.right.right = new TreeNode(7);
		
		BSTDeleteNode b = new BSTDeleteNode();
		b.deleteNode(root, 3);
		
		
		
	}
}
