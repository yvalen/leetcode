package leetcode.sort;

import leetcode.list.ListNode;
import leetcode.list.ListUtil;

public class ReorderList {

    /**
     * Given a singly linked list L: L0→L1→…→Ln-1→Ln, reorder it to:
     * L0→Ln→L1→Ln-1→L2→Ln-2→… You must do this in-place without altering the
     * nodes' values. For example, given {1,2,3,4}, reorder it to {1,4,2,3}.
     */
    public void reorderList(ListNode head) {
        if (head == null || head.next == null) {
            return;
        }

        // find the mid point
        ListNode mid = head, fast = head, prev = null;
        while (fast != null && fast.next != null) {
            prev = mid;
            mid = mid.next;
            fast = fast.next.next;
        }

        // split the list
        prev.next = null;

        // reverse the second half
        ListNode curr = mid, next = null, head2 = null;
        while (curr != null) {
            next = curr.next;
            curr.next = head2;
            head2 = curr;
            curr = next;
        }

        ListNode l1 = head, l2 = head2, n1 = null, n2 = null;
        while (l1 != null && l2 != null) {
            n1 = l1.next;
            n2 = l2.next;
            l1.next = l2;
            if (n1 != null)
                l2.next = n1;
            l1 = n1;
            l2 = n2;
        }

    }

    public static void main(String[] args) {
        ReorderList reorder = new ReorderList();

        int[] array = new int[] { 1, 2, 3 };
        ListNode list = ListUtil.createList(array);

        reorder.reorderList(list);
        ListUtil.printList(list);
    }

}
