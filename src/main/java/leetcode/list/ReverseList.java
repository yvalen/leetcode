package leetcode.list;

import java.util.List;

public class ReverseList {
    /*
     * LEETCODE 206 Reverse a singly linked list.
     * 
     * Company: Facebook, Microsoft, Bloomberg, Amazon, Uber, Twitter, Apple,
     * Snapchat, Zenefits, Yelp, Yahoo, Adobe Difficulty: easy Similar
     * Questions: 92(Reverse Linked List II)
     */
    public ListNode reverseListIterative(ListNode head) {
        ListNode current = head, prev = null, next = null;
        while (current != null) {
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }

        return prev;
    }

    public ListNode reverseListRecursive(ListNode head) {
        if (head == null || head.next == null)
            return head;

        ListNode reversedHead = reverseListRecursive(head.next);
        head.next.next = head;
        head.next = null; // need to set head.next to null here, otherwise will
                          // get into infinite loop

        return reversedHead;
    }

    /*
     * LEETCODE 92 Reverse a linked list from position m to n. Do it in-place
     * and in one-pass. 
     * For example: Given 1->2->3->4->5->NULL, m = 2 and n = 4,
     * return 1->4->3->2->5->NULL. 
     * Note: Given m, n satisfy the following condition: 1 ≤ m ≤ n ≤ length of list.
     * 
     * Difficulty: medium 
     * Similar Questions: 206(Reverse Linked List)
     */
    public ListNode reverseBetween(ListNode head, int m, int n) {
        if (head == null || head.next == null || m == n)
            return head;

        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode prev = dummy;
        for (int i = 0; i < m - 1; i++)
            prev = prev.next;

        ListNode start = prev.next;
        for (int i = m; i < n; i++) { // need to check for < n here
            ListNode next = start.next;
            start.next = next.next;
            next.next = prev.next; // bubble the element to the beginning of the
                                   // reversed list
            prev.next = next;
        }

        return dummy.next;
    }

    public ListNode reverseBetween_twoPass(ListNode head, int m, int n) {
        if (head == null || head.next == null)
            return head;

        int pos = 1;
        ListNode start = head, end = head, before = null;
        while (pos < m) {
            before = start;
            start = start.next;
            end = end.next;
            pos++;
        }

        while (pos < n) {
            end = end.next;
            pos++;
        }
        ListNode after = end.next;

        end.next = null;
        if (before != null)
            before.next = null; // need to check for null here as we could
                                // reverse from the beginning
        ListNode current = start, prev = null, next = null;
        while (current != null) {
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }

        if (before != null)
            before.next = prev;
        start.next = after;

        return (before == null) ? prev : head;
    }

    /*
     * LEETCODE 25
     * Given a linked list, reverse the nodes of a linked list k at a time and
     * return its modified list. If the number of nodes is not a multiple of k
     * then left-out nodes in the end should remain as it is. You may not alter
     * the values in the nodes, only nodes itself may be changed. Only constant
     * memory is allowed. For example, 
     * given this linked list: 1->2->3->4->5 
     * For k = 2, you should return: 
     * 2->1->4->3->5 
     * For k = 3, you should return:
     * 3->2->1->4->5
     * 
     * Company: Facebook, Microsoft
     * Difficulty: hard
     * Similar Questions: 24(SwapPair)
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || head.next == null || k == 1)
            return head;

        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode prev = dummy, last = head;
        int i = 0;
        while (last != null) {
            i++;
            if (i % k == 0) {
                prev = reverseSubGroup(prev, last.next);
                last = prev.next;
            } else {
                last = last.next;
            }
        }

        return dummy.next;
    }

    private ListNode reverseSubGroup(ListNode beforeHead, ListNode afterTail) {
        ListNode current = beforeHead.next, previous = afterTail;
        while (current != afterTail) {
            ListNode next = current.next;
            current.next = previous;
            previous = current;
            current = next;
        }
        ListNode newTail = beforeHead.next;
        beforeHead.next = previous;
        return newTail;
    }

    public static void main(String[] args) {
        int[] ary = { 1, 2, 3, 4 };
        ListNode head = ListUtil.createList(ary);

        ReverseList r = new ReverseList();
        ListNode result = r.reverseKGroup(head, 2);
        ListUtil.printList(result);
    }

}
