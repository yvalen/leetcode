package leetcode.tree;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/*
 * Given a binary search tree (BST) with duplicates, find all the mode(s) (the most frequently occurred element) 
 * in the given BST. Assume a BST is defined as follows:
 * 	The left subtree of a node contains only nodes with keys less than or equal to the node's key.
 * 	The right subtree of a node contains only nodes with keys greater than or equal to the node's key.
 * 	Both the left and right subtrees must also be binary search trees.
 * For example:
 * 	Given BST [1,null,2,2],
 *    1
 *     \
 *     2
 *    /
 *   2
 *  return [2].
 *  Note: If a tree has more than one mode, you can return them in any order. 
 *  Follow up: Could you do that without using any extra space? (Assume that the implicit stack space incurred due to recursion does not count). 
 */
public class FindModeInBST {
	private int maxCount = 0;
	private int count = 0;
	private Integer prevVal = null;
	private int modeCount = 0;
	private int[] modes;
	private int prev = 0;
	
	List<Integer> result = new LinkedList<>();
	
	public int[] findMode_withList(TreeNode root) {
		if (root == null) return new int[0];
		count = 1;
		findModeHelper(root);
		return result.stream().mapToInt(Integer::intValue).toArray();
    }
	
	private void findModeHelper(TreeNode root) {
		if (root == null) return;
		
		findModeHelper(root.left);
		
		if (prevVal != null) {
			if (prevVal != root.val) {
				count = 1;
			}
			else {
				count++;
			}
		}
			
		if (count > maxCount) {
			result.clear();
			result.add(root.val);
			maxCount = count;
		}
		else if (count == maxCount) {
			result.add(root.val);
		}
		prevVal = root.val;
		
		findModeHelper(root.right);
	}
	
	public int[] findMode(TreeNode root) {
		if (root == null) return new int[0];
		inorder(root);
		modes = new int[modeCount];
		// reset counter
		modeCount = 0;
		count = 0;
		inorder(root);
		return modes;
    }
	
	private void inorder(TreeNode root) {
		TreeNode current = root;
		while (current != null) {
			if (current.left == null) {
				visit(current);
				current = current.right;
			} 
			else {
				TreeNode prev = current.left;
				while (prev.right != null && prev.right != current) {
					prev = prev.right;
				}
				if (prev.right == null) {
					prev.right = current;
					current = current.left;
				}
				else {
					prev.right = null;
					visit(current);
					current = current.right;
				}
			}			
		}
	}
	
	private void visit(TreeNode node) {
		// reset counter for a new value
		if (node.val != prev) {
			count = 0;
			prev = node.val;
		}
		count++;
		if (count > maxCount) {
			maxCount = count;
			modeCount = 1;
		}
		else if (count == maxCount) {
			if (modes != null) {
				modes[modeCount] = node.val;
			}
			modeCount++;
		}
	}
	
	
	public static void main(String[] args) {
		FindModeInBST f = new FindModeInBST();
		
		TreeNode root = new TreeNode(1);
		//root.left = new TreeNode(1);
		root.right = new TreeNode(2);
		root.right.left = new TreeNode(2);
		
		int[] ret = f.findMode(root);
		
		IntStream.of(ret).forEach(System.out::println);
	}
	
}
