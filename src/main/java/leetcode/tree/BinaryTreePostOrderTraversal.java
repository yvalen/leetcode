package leetcode.tree;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/*
 * LEETCODE 145 
 * Given a binary tree, return the postorder(left->right->root) traversal of its nodes' values.
 * For example:
 * Given binary tree {1,#,2,3},
 *    1
 *     \
 *     2
 *    /
 *   3
 * return [3,2,1].
 * 
 * Difficulty: hard
 * Similar Questions: 94(BinaryTreeInOrderTraversal)
 */
// http://articles.leetcode.com/binary-tree-post-order-traversal/
public class BinaryTreePostOrderTraversal {
    public List<Integer> postorderTraversal_recursive(TreeNode root) {
        List<Integer> result = new LinkedList<>();
        postorderTraversal_recursive_helper(root, result);
        return result;
    }

    private void postorderTraversal_recursive_helper(TreeNode root, List<Integer> result) {
        if (root == null)
            return;

        postorderTraversal_recursive_helper(root.left, result);
        postorderTraversal_recursive_helper(root.right, result);
        result.add(root.val);
    }

    /*
     * As we go down the tree to the left, check the previously visited node. If
     * the current node is the left or right child of the previous node, then
     * keep going down the tree, and add left/right node to stack when
     * applicable. When there is no children for current node, i.e., the current
     * node is a leaf, pop it from the stack. Then the previous node become to
     * be under the current node for next loop.
     */
    public List<Integer> postorderTraversal_iterative_withOneStack(TreeNode root) {
        if (root == null)
            return Collections.emptyList();

        List<Integer> result = new LinkedList<>();
        Stack<TreeNode> nodeStack = new Stack<>();
        nodeStack.push(root);
        TreeNode prev = null;
        while (!nodeStack.isEmpty()) {
            TreeNode current = nodeStack.peek();

            if (prev == null || prev.left == current || prev.right == current){ 
                // traverse down if prev is current's parent or current is root
                if (current.left != null) {
                    // keep going down the left sub tree
                    nodeStack.push(current.left);
                } else if (current.right != null) {
                    // keep going down the right sub tree
                    nodeStack.push(current.right);
                } else {
                    // leaf node, pop it off the stack and visit it
                    current = nodeStack.pop();
                    result.add(current.val);
                }
            } else if (current.left == prev) {
                // prev is current's left child, traversing up from the left
                if (current.right != null) {
                    // traversing down the right child of current
                    nodeStack.push(current.right);
                } else {
                    // visit the parent node
                    current = nodeStack.pop();
                    result.add(current.val);
                }
            } else if (current.right == prev) {
                // prev is current's right child, traversing up from the right
                // visit the parent node
                current = nodeStack.pop();
                result.add(current.val);
            }

            prev = current;
        }

        return result;
    }

    /*
     * Doing a reversed pre-order traversal. That is, the order of traversal is
     * a node, then its right child followed by its left child. This yields
     * post-order traversal in reversed order. Using a second stack, we could
     * reverse it back to the correct order.
     * 
     * 1. Push the root node to the first stack. 2. Pop a node from the first
     * stack, and push it to the second stack. 3. Push its left child followed
     * by its right child to the first stack. 4. Repeat step 2) and 3) until the
     * first stack is empty. Once done, the second stack would have all the
     * nodes ready to be traversed in post-order. Pop off the nodes from the
     * second stack one by one and you’re done.
     */
    public List<Integer> postorderTraversal_iterative_withTwoStacks(TreeNode root) {
        if (root == null)
            return Collections.emptyList();

        Stack<TreeNode> s1 = new Stack<>();
        Stack<TreeNode> s2 = new Stack<>();
        s1.push(root);
        while (!s1.isEmpty()) {
            TreeNode current = s1.pop();
            s2.push(current);
            if (current.left != null) {
                s1.push(current.left);
            }
            if (current.right != null) {
                s1.push(current.right);
            }
        }

        List<Integer> result = new LinkedList<>();
        while (!s2.isEmpty()) {
            result.add(s2.pop().val);
        }

        return result;
    }

    /*
     * pre-order traversal is root-left-right, and post order is
     * left-right-root. modify the code for pre-order to make it
     * root-right-left, and then reverse the output so that we can get
     * left-right-root .
     */
    public List<Integer> postorderTraversal(TreeNode root) {
        if (root == null) return Collections.emptyList();

        List<Integer> result = new LinkedList<>();
        Stack<TreeNode> nodeStack = new Stack<>();
        nodeStack.push(root);
        while (!nodeStack.isEmpty()) {
            TreeNode current = nodeStack.pop();
            result.add(current.val);
            if (current.left != null) {
                // left will be visited after right
                nodeStack.push(current.left);
            }
            if (current.right != null) {
                nodeStack.push(current.right);
            }
        }

        Collections.reverse(result);
        return result;
    }
}
