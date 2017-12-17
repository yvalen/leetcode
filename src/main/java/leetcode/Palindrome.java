package leetcode;

import leetcode.list.ListNode;

public class Palindrome {

    public boolean isPalindromeList(ListNode head) {
        if (head == null || head.next == null)
            return true;

        // find the mid point of the list
        ListNode slow = head, fast = head, tail1 = null;
        while (fast != null && fast.next != null) {
            tail1 = slow;
            slow = slow.next;
            fast = fast.next.next;
        }

        // terminate the first half
        tail1.next = null;

        // reverse the second half
        // prev will be the head of the second half
        ListNode current = slow, prev = null, next;
        while (current != null) {
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }

        // compare two halves
        ListNode p1 = head, p2 = prev;
        while (p1 != null && p2 != null) {
            if (p1.val != p2.val)
                return false;
            p1 = p1.next;
            p2 = p2.next;
        }

        return true;
    }

}
