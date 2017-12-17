package leetcode.sort;

import leetcode.list.ListNode;
import leetcode.list.ListUtil;

/*
 * Sort a linked list using insertion sort.
 * 
 * Advantage of insertion sort:
 * - simple implementation
 * - efficient for small data set
 * - more efficient in practice in practice than other O(n^2) algorithms such as selection sort and bubble sort
 * - adaptive, efficient for data sets that are already sustantially sorted: O(nk) when each element in the input is no more than k place away from its sorted position
 * - stable, doesn't change the relative order of elements with equal keys
 * - in-place, only requires constant amount O(1) of additional memory space
 * - online, can sort a list as it receives it
 */
public class InsertionSortList {
    // starts with an initially empty (and therefore trivially sorted) list. The
    // input items are taken off the list one at a time,
    // and then inserted in the proper place in the sorted list. When the input
    // list is empty, the sorted list has the desired result.
    // Time complexity O(n^2), space complexity: O(1)
    public ListNode insertionSortList_withTailPointer(ListNode head) {
        // zero or one element in list, no sort needed
        if (head == null || head.next == null)
            return head;

        ListNode newHead = null;
        while (head != null) {
            ListNode current = head;
            head = head.next; // move head to the next node first
            if (newHead == null || current.val < newHead.val) {
                // insert into the head or as the first element
                current.next = newHead;
                newHead = current;
            } else {
                // find proper position to insert current
                ListNode p = newHead;
                while (p.next != null && p.next.val < current.val) {
                    p = p.next;
                }
                current.next = p.next;
                p.next = current;
            }
        }

        return newHead;
    }

    public ListNode insertionSortList_withDummyHead(ListNode head) {
        ListNode dummy = new ListNode(0);
        ListNode current = head, prev = dummy, next = null;
        while (current != null) {
            next = current.next; // save the next node first
            while (prev.next != null && prev.next.val < current.val) { // use
                                                                       // prev.next
                                                                       // to
                                                                       // find
                                                                       // the
                                                                       // position
                prev = prev.next;
            }
            current.next = prev.next;
            prev.next = current;
            current = next;
            prev = dummy; // reset prev to the beginning
        }

        return dummy.next;
    }

    public static void main(String[] args) {
        InsertionSortList isl = new InsertionSortList();
        ListNode head = ListUtil.createList(new int[] { 3, 1, 4, 2 });
        ListNode sorted = isl.insertionSortList_withDummyHead(head);
        ListUtil.printList(sorted);
    }

}
