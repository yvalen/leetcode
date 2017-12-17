package leetcode.sort;

import leetcode.list.ListNode;
import leetcode.list.ListUtil;

/*
 * Sort a linked list in O(n log n) time using constant space complexity.
 * http://www.cprogramming.com/tutorial/computersciencetheory/sortcomp.html
 * 
 */
public class SortList {
    // top down recursion, O(logN) call stack space
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null)
            return head;

        // find the mid point
        ListNode slow = head, fast = head, prev = null;
        while (fast != null && fast.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        prev.next = null; // need to separate two sub lists

        ListNode l1 = sortList(head);
        ListNode l2 = sortList(slow);
        return merge(l1, l2);
    }

    private ListNode merge(ListNode n1, ListNode n2) {
        ListNode dummy = new ListNode(0), current = dummy;
        while (n1 != null && n2 != null) {
            if (n1.val < n2.val) {
                current.next = n1;
                n1 = n1.next;
            } else {
                current.next = n2;
                n2 = n2.next;
            }
            current = current.next; // need to advance current
        }

        if (n1 != null)
            current.next = n1;
        else
            current.next = n2;

        return dummy.next;
    }

    // bottom up O(1) space
    public ListNode sortList_bottomUp(ListNode head) {
        if (head == null || head.next == null)
            return head;

        // get the length of the list
        int len = 0;
        ListNode current = head;
        while (current != null) {
            len++;
            current = current.next;
        }

        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode left, right, tail;
        for (int sz = 1; sz < len; sz <<= 1) {
            current = dummy.next;
            tail = dummy; // first time append to dummy
            while (current != null) {
                left = current;
                right = split(current, sz);
                current = split(right, sz);
                tail = merge(left, right, tail);
            }
        }
        return dummy.next;
    }

    // divide the list into two parts
    // first part contains number of size nodes
    // second list contains the rest
    // head points to the tail of the first part after the call
    private ListNode split(ListNode head, int size) {
        while (head != null && size > 1) { // compare size with 1 since head
                                           // should be the last element of the
                                           // first part
            head = head.next;
            size--;
        }

        if (head == null)
            return null;
        ListNode second = head.next;
        head.next = null; // terminate the first part
        return second;
    }

    // merge l1 and l2, append result to head
    // return tail of the merged list
    private ListNode merge(ListNode l1, ListNode l2, ListNode head) {
        ListNode current = head;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                current.next = l1;
                l1 = l1.next;
            } else {
                current.next = l2;
                l2 = l2.next;
            }
            current = current.next;
        }
        current.next = (l1 != null) ? l1 : l2;

        while (current.next != null)
            current = current.next; // advance current to the end

        return current;
    }

    public static void main(String[] args) {
        SortList sl = new SortList();
        ListNode head = ListUtil.createList(new int[] { 2, 1 });
        ListUtil.printList(sl.sortList_bottomUp(head));
    }
}
