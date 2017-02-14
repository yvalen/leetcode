package leetcode.tree;

import java.util.HashMap;
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

	public int[] findMode_withMap(TreeNode root) {
        if (root == null) return new int[0];
		
        Map<Integer, Integer> occurenceMap = new HashMap<>();
        Stack<TreeNode> nodeStack = new Stack<>();
        
		return null;
		
    }
	
	public int[] findMode(TreeNode root) {
		if (root == null) return new int[0];
		
		TreeNode current = root, prev = null;
		Stack<TreeNode> nodeStack = new Stack<>();
		Stack<Integer> resultStack = new Stack<Integer>();
		int count = 0, maxCount = Integer.MIN_VALUE;
		while (!nodeStack.isEmpty() || current != null) {
			if (current != null) {
				nodeStack.push(current);
				current = current.left;
			}
			else {
				current = nodeStack.pop();
				if (prev == null) {
					// first element
					count = 1;
				}
				else {
					if (prev.val == current.val) {
						count++;
					}
					else {
						if (maxCount < count) {
							if (!resultStack.isEmpty()) {
								resultStack.pop();
							}
							maxCount = count;
							resultStack.push(prev.val);
							count = 1;
						}
					}
				}
				prev = current;
				current = current.right;
			}
		}
		
		int[] ret = new int[resultStack.size()];
		while (!resultStack.isEmpty()) {
			ret[resultStack.size() - 1] = resultStack.pop();
		}
		
		return ret;
    }
	
	public static void main(String[] args) {
		FindModeInBST f = new FindModeInBST();
		
		TreeNode root = new TreeNode(1);
		root.right = new TreeNode(2);
		root.right.left = new TreeNode(2);
		
		int[] ret = f.findMode(root);
		
		Stream.of(ret).forEach(System.out::println);
	}
	
}
