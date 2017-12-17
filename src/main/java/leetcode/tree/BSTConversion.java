package leetcode.tree;

import leetcode.list.ListNode;

public class BSTConversion {
    /*
     * Given an array where elements are sorted in ascending order, convert it
     * to a height balanced BST.
     */
    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums == null)
            return null;

        return sortedArrayToBST_helper(nums, 0, nums.length - 1);
    }

    private TreeNode sortedArrayToBST_helper(int[] nums, int start, int end) {
        if (start > end)
            return null;

        if (start == end)
            return new TreeNode(nums[start]);

        int mid = start + (end - start) / 2;
        TreeNode node = new TreeNode(nums[mid]);
        if (start < mid) {
            node.left = sortedArrayToBST_helper(nums, start, mid - 1);
        }

        if (mid < end) {
            node.right = sortedArrayToBST_helper(nums, mid + 1, end);
        }

        return node;
    }

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
