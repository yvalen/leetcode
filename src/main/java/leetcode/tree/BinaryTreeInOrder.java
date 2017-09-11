package leetcode.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * InOrder: left -> root -> right
 *
 */
public class BinaryTreeInOrder {
	/**
	 * Iterative with stack, algorithm
	 * 1) Create an empty stack S.
	 * 2) Initialize current node as root
	 * 3) Push the current node to S and set current = current->left until current is NULL
	 * 4) If current is NULL and stack is not empty then 
	 * 		a) Pop the top item from stack.
	 * 		b) Print the popped item, set current = popped_item->right 
	 * 		c) Go to step 3.
	 * 5) If current is NULL and stack is empty then we are done.
	 */
	public static List<Integer> inorderTraversalIterative(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Stack<TreeNode> nodeStack = new Stack<>();
        TreeNode node = root;
        while (!nodeStack.isEmpty() || node != null) {
        	if (node != null) {
        		nodeStack.push(node);
        		node = node.left;
        	}
        	else {
        		node = nodeStack.pop();
        		result.add(node.val);
        		node = node.right; // last traversed node must not have a right child
        	}
        }
        
        return result;
    }
	
	/**
	 * Morris traversal algorithm: use the right child pointer to thread the tree
	 * 1. Initialize current as root 
	 * 2. While current is not NULL
	 * If current does not have left child
	 * 		a) Print current’s data
	 * 		b) Go to the right, i.e., current = current->right
	 * Else
	 * 		a) Make current as right child of the rightmost node in current's left subtree
	 * 		b) Go to this left child, i.e., current = current->left
	 * Although the tree is modified through the traversal, 
	 * it is reverted back to its original shape after the completion.
	 * 
	 * Threaded tree: https://en.wikipedia.org/wiki/Threaded_binary_tree
	 * A binary tree is threaded by making all right child pointers that would normally be null point 
	 * to the inorder successor of the node (if it exists), and all left child pointers that would 
	 * normally be null point to the inorder predecessor of the node.
	 * http://www.cnblogs.com/AnnieKim/archive/2013/06/15/MorrisTraversal.html
	 */
	public List<Integer> inorderMorrisTraversal(TreeNode root) {
		List<Integer> result = new ArrayList<>();
        TreeNode current = root, prev;
        while (current != null) {
        	if (current.left == null) {
        		result.add(current.val);
        		current= current.right;
        	}
        	else {
        		// find the inorder predecessor of current (rightmost child of the left subtree)
        		prev = current.left;
        		while (prev.right != null && prev.right != current) {
        			prev = prev.right;
        		}
        		
        		if (prev.right == null) {
        			// find the rightmost child of the left subtree, make current as its right child 
        			prev.right = current;
        			
        			// start process the left subtree
        			current = current.left;
        		}
        		else {
        			// the whole left subtree is processed,
        			// we are at the rightmost child of the left subtree
        			// revert the change of the thread, restore the tree
        			prev.right = null;
        			
        			result.add(current.val);
        			
        			// start to process the right subtree
        			current = current.right;
        		}
        	}
        }
        
        return result;
	}
	
	/**
	 * Recursive inorder traversal
	 */
	public List<Integer> inorderTraversalRecursive(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        inorderTraversalRecursiveHelper(root, result);
        return result;
    }
	
	private void inorderTraversalRecursiveHelper(TreeNode root, List<Integer> result) {
		if (root == null) return;
		
		inorderTraversalRecursiveHelper(root.left, result);
		result.add(root.val);
		inorderTraversalRecursiveHelper(root.right, result);
	}
}
