package leetcode.tree;

import java.util.LinkedList;
import java.util.Queue;

public class PopulateNextRightPointer {

    /*
     * LEETCODE 116 Given a binary tree struct TreeLinkNode { TreeLinkNode
     * *left; TreeLinkNode *right; TreeLinkNode *next; } Populate each next
     * pointer to point to its next right node. If there is no next right node,
     * the next pointer should be set to NULL. Initially, all next pointers are
     * set to NULL. Note: - You may only use constant extra space. - You may
     * assume that it is a perfect binary tree (ie, all leaves are at the same
     * level, and every parent has two children). For example, given the
     * following perfect binary tree, 1 / \ 2 3 / \ / \ 4 5 6 7 After calling
     * your function, the tree should look like: 1 -> NULL / \ 2 -> 3 -> NULL /
     * \ / \ 4->5->6->7 -> NULL
     * 
     * Company: Microsoft Difficulty: medium
     */
    public void connect_perfect_tree_recursive(TreeLinkNode root) {
        if (root == null || root.left == null)
            return;

        root.left.next = root.right;
        // use root.next to populate next pointer of the right sub tree
        root.right.next = (root.next == null) ? null : root.next.left;

        connect_perfect_tree_recursive(root.left);
        connect_perfect_tree_recursive(root.right);
    }

    public void connect_perfect_tree_iterative(TreeLinkNode root) {
        if (root == null)
            return;

        // leftNode is the left most node on each level
        TreeLinkNode leftNode = root;
        while (leftNode.left != null) {
            TreeLinkNode current = leftNode;
            // use next pointer to do level order traversal
            while (current != null) {
                // populate the next pointer for each node in the next level
                current.left.next = current.right;
                if (current.next != null)
                    current.right.next = current.next.left;
                current = current.next;
            }
            leftNode = leftNode.left;
        }
    }

    /*
     * LEETCODE 117 Follow up for problem
     * "Populating Next Right Pointers in Each Node". What if the given tree
     * could be any binary tree? Would your previous solution still work? Note:
     * You may only use constant extra space. For example, given the following
     * binary tree, 1 / \ 2 3 / \ \ 4 5 7 After calling your function, the tree
     * should look like: 1 -> NULL / \ 2 -> 3 -> NULL / \ \ 4-> 5 -> 7 -> NULL
     * 
     * Company: Facebook, Microsoft, Bloomberg Difficulty: medium
     */
    // O(N) space
    public void connect_non_perfect_tree_levelOrder(TreeLinkNode root) {
        if (root == null)
            return;

        Queue<TreeLinkNode> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                TreeLinkNode node = q.poll();
                // need to check the size as the queue has next level elements
                node.next = (i == size - 1) ? null : q.peek();
                if (node.left != null) {
                    q.offer(node.left);
                }
                if (node.right != null) {
                    q.offer(node.right);
                }
            }
        }
    }

    public void connect_non_perfect_tree_iterative(TreeLinkNode root) {
        if (root == null)
            return;

        // leftNode is the left most node on each level
        TreeLinkNode leftNode = root;
        while (leftNode != null) {
            TreeLinkNode current = leftNode, next = null;
            // use next pointer to do level order traversal
            while (current != null) {
                next = findNext(current.next);
                if (current.right != null) {
                    current.right.next = next;
                    next = current.right;
                }
                if (current.left != null) {
                    current.left.next = next; // cannot use current.right here
                                              // because it could be null
                }
                current = current.next;
            }

            // find the proper element in the next level to start
            leftNode = findNext(leftNode);
        }
    }

    private TreeLinkNode findNext(TreeLinkNode node) {
        while (node != null) { // need to check all nodes at the same level
                               // since some middle nodes could have null
                               // children
            if (node.left != null)
                return node.left;
            if (node.right != null)
                return node.right;
            node = node.next;
        }
        return null;
    }

    public void connect_non_perfect_tree_with_dummy_node(TreeLinkNode root) {
        if (root == null)
            return;

        TreeLinkNode dummy = new TreeLinkNode(0);
        TreeLinkNode current = root;
        while (current != null) { // loop each level
            TreeLinkNode prev = dummy; // prev and dummy are the same node

            while (current != null) { // loop the current level
                if (current.left != null) {
                    prev.next = current.left;
                    prev = prev.next;
                }
                if (current.right != null) {
                    prev.next = current.right;
                    prev = prev.next;
                }
                current = current.next;
            }
            // current points to the head of the next level
            current = dummy.next;
            // reset next link in dummy so that the loop can terminate
            dummy.next = null;
        }
    }

    public static void main(String[] args) {
        PopulateNextRightPointer p = new PopulateNextRightPointer();

        TreeLinkNode root = new TreeLinkNode(1);
        root.left = new TreeLinkNode(2);
        p.connect_non_perfect_tree_with_dummy_node(root);

    }

}
