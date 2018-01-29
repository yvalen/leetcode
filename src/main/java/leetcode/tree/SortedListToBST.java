package leetcode.tree;

import leetcode.list.ListNode;

/*
 * LEETCODE 109
 * Given a singly linked list where elements are sorted in ascending order, 
 * convert it to a height balanced BST. For this problem, a height-balanced 
 * binary tree is defined as a binary tree in which the depth of the 
 * two subtrees of every node never differ by more than 1.
 * Example:
 * One possible answer is: [0,-3,9,-10,null,5], which represents the following height balanced BST:
 *       0
 *      / \
 *    -3   9
 *    /   /
 *  -10  5
 *  
 * Company: Zenefits
 * Difficulty: medium
 * Similar Questions: 108(SortedArrayToBinarySearchTree)
 */
public class SortedListToBST {
    
    /*
     * Given a singly linked list where elements are sorted in ascending order,
     * convert it to a height balanced BST.
     */
    public TreeNode sortedListToBST(ListNode head) {
        if (head == null)
            return null;

        return sortedListToBST_helper(head, null);
    }

    private TreeNode sortedListToBST_helper(ListNode head, ListNode tail) {
        if (head == tail)
            return null;

        ListNode slow = head, fast = head;

        // compare with tail instead of null here since we didn't break up the list
        while (fast != tail && fast.next != tail) {
            slow = slow.next;
            fast = fast.next.next;
        }

        TreeNode node = new TreeNode(slow.val);
        node.left = sortedListToBST_helper(head, slow);
        node.right = sortedListToBST_helper(slow.next, tail);
        return node;
    }

}
