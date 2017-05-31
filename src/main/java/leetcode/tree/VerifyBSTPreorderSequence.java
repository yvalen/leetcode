package leetcode.tree;

import java.util.Stack;

/*
 * Given an array of numbers, verify whether it is the correct preorder traversal sequence of a binary search tree.
 * You may assume each number in the sequence is unique.
 * Follow up: Could you do it using only constant space complexity?
 * 
 * Company: Zenefits
 */
public class VerifyBSTPreorderSequence {
	public boolean verifyPreorder_recursive(int[] preorder) {
        if (preorder == null || preorder.length == 0) return true;
		
		return isValidPreorder(preorder, 0, preorder.length-1, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }
	
	// Recursively examine every key in the array. For each BST node, its key must be greater than 
	// all keys in left subtree and less than keys in right subtree. Since given preorder sequence, 
	// the first element is always the root. Partition the array by the key of root, find the index 
	// of the first number greater than it.
	// Base case:
	// - start index exceeds end index, the array to be checked is empty, return true;
	// - root key is not within upper and lower boundaries, return false.
	private boolean isValidPreorder(int[] preorder, int start, int end, int min, int max) {
		// start index exceeds end index, the array to be checked is empty
		if (start > end) return true;
		
		// root key is not within upper and lower boundaries
		int root = preorder[start];
		if (root < min || root > max) return false;
		
		// find the index of the first number greater than root
		// to avoid special handling rightIdx starts from the beginning
		// need to check equals to root since starting from the beginning 
		// cannot start from start+1 since this will not handle the left empty tree
		int rightIdx = start;
		while (rightIdx <= end && preorder[rightIdx] < root) {
			rightIdx++;
		} 
		
		return isValidPreorder(preorder, start+1, rightIdx-1, min, root) &&
				isValidPreorder(preorder, rightIdx, end, root, max);
	}
	
	
	
	// A BST's left child is always < root and right child is always > root.
	private boolean helper1(int[] preorder, int start, int end) {
		if (start >= end) return true;
		int root = preorder[start];
		int rightIdx = -1;
		for (int i = start+1; i <= end; i++) {
			if (rightIdx == -1 && preorder[i] > root) {
				// found the first node that is greater than root
				rightIdx = i;
			}
			if (rightIdx != -1 && preorder[i] < root) {
				return false; // all nodes in right tree should be greater than root
			}
		}
		
		if (rightIdx == -1) { // has left child only
			return helper1(preorder, start+1, end);
		}
		
		return helper1(preorder, start+1, rightIdx-1) &&
				helper1(preorder, rightIdx, end);
	}
	
	// keeping a stack of nodes (just their values) of which we're still in the left subtree. 
	// If the next number is smaller than the last stack value, then we're still in the left 
	// subtree of all stack nodes, so just push the new one onto the stack. But before that, 
	// pop all smaller ancestor values, as we must now be in their right subtrees (or even further, 
	// in the right subtree of an ancestor). Also, use the popped values as a lower bound, since being 
	// in their right subtree means we must never come across a smaller number anymore.
	public boolean verifyPreorder_withStack(int[] preorder) {
        if (preorder == null || preorder.length == 0) return true;
		
        Stack<Integer> stack = new Stack<>();
        int lowerBound = Integer.MIN_VALUE;
        for (int val : preorder) {
        	if (val < lowerBound) return false;

        	while (!stack.isEmpty() && val > stack.peek()) {
        		lowerBound = stack.pop();
        	}
        	stack.push(val);
        }
        
		return true;
    }
	
	public static void main(String[] args) {
		VerifyBSTPreorderSequence  vpre = new VerifyBSTPreorderSequence();
		int[] preorder = {1, 2, 3};
		System.out.println(vpre.verifyPreorder_recursive(preorder));
	}

}
