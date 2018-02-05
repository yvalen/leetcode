package leetcode.tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/*
 * LEETCODE 545
 * Given a binary tree, return the values of its boundary in anti-clockwise direction starting from root. Boundary includes left boundary, 
 * leaves, and right boundary in order without duplicate nodes. Left boundary is defined as the path from root to the left-most node. Right 
 * boundary is defined as the path from root to the right-most node. If the root doesn't have left subtree or right subtree, then the root 
 * itself is left boundary or right boundary. Note this definition only applies to the input binary tree, and not applies to any subtrees.
 * The left-most node is defined as a leaf node you could reach when you always firstly travel to the left subtree if exists. If not, travel 
 * to the right subtree. Repeat until you reach a leaf node. The right-most node is also defined by the same way with left and right exchanged.
 * Example 1
 * Input:
 *   1
 *    \
 *     2
 *    / \
 *   3   4
 * Output: [1, 3, 4, 2]
 * Explanation: The root doesn't have left subtree, so the root itself is left boundary. The leaves are node 3 and 4. The right boundary are 
 * node 1,2,4. Note the anti-clockwise direction means you should output reversed right boundary. So order them in anti-clockwise without 
 * duplicates and we have [1,3,4,2].
 * Example 2
 * Input:
 *	      ____1_____
 *  	 	 /          \
 *  	    2            3
 *	   / \          / 
 *	  4   5        6   
 *       / \      / \
 *      7   8    9  10  
 * Output: [1,2,4,7,8,9,10,6,3]
 * Explanation: The left boundary are node 1,2,4 (4 is the left-most node according to definition). The leaves are node 4,7,8,9,10. The right 
 * boundary are node 1,3,6,10. (10 is the right-most node). So order them in anti-clockwise without duplicate nodes we have [1,2,4,7,8,9,10,6,3].
 * 
 * Company: Amazon
 * Difficulty: medium
 * Similar Questions: 199(BinaryTreeRightSideView), 116(PopulateNextRightPointer)
 */
public class BoundaryOfBinaryTree {
    // divide this problem into three subproblems- left boundary, leaves and
    // right boundary
    // Time complexity: O(n), one complete traversal for leaves and two
    // traversals up to depth of binary tree for left and right boundary.
    // Space complexity: O(n)
    public List<Integer> boundaryOfBinaryTree(TreeNode root) {
        if (root == null)
            return Collections.emptyList();

        List<Integer> result = new ArrayList<>();
        result.add(root.val);
        addLeftBoundary(root.left, result);
        // add leaves separately for left and right
        // cannot use add leaves for root here as we 
        // need to add root before everything else
        addLeaves(root.left, result);
        addLeaves(root.right, result);
        addRightBoundary(root.right, result);
        return result;
    }

    private void addLeaves(TreeNode root, List<Integer> result) {
        if (root == null)
            return;
        if (root.left == null && root.right == null) {
            result.add(root.val);
            return;
        }
        addLeaves(root.left, result);
        addLeaves(root.right, result);
    }

    // keep on traversing the tree towards the left and keep on adding the nodes
    // in the res array,
    // provided the current node isn't a leaf node. If at any point, we can't
    // find the left child
    // of a node, but its right child exists, we put the right child in the res
    // and continue the process.
    private void addLeftBoundary(TreeNode root, List<Integer> result) {
        if (root == null || (root.left == null && root.right == null))
            return;
        result.add(root.val);
        if (root.left != null) {
            addLeftBoundary(root.left, result);
        } else {
            addLeftBoundary(root.right, result);
        }
    }

    // perform the same process as the left boundary. But traverse towards the
    // right. If the right child doesn't exist,
    // move towards the left child. Also, instead of putting the traversed nodes
    // in the res array, we push them over a
    // stack during the traversal. After the complete traversal is done, we pop
    // the element from over the stack and append
    // them to the res array.
    private void addRightBoundary(TreeNode root, List<Integer> result) {
        if (root == null || (root.left == null && root.right == null))
            return;

        if (root.right != null) {
            addRightBoundary(root.right, result);
        } else {
            addRightBoundary(root.left, result);
        }
        result.add(root.val);
    }
    
    public static void main(String[] args) {
    		TreeNode root = new TreeNode(1);
    		root.right = new TreeNode(2);
    		root.right.left = new TreeNode(3);
    		root.right.right = new TreeNode(4);
    		
    		BoundaryOfBinaryTree bbt = new BoundaryOfBinaryTree();
    		System.out.println(bbt.boundaryOfBinaryTree(root));
    }
}
