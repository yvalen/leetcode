package leetcode.list;

/*
 * LEETCODE 369
 * Given a non-negative integer represented as non-empty a singly linked list of digits, plus one to the integer.
 * You may assume the integer do not contain any leading zero, except the number 0 itself.
 * The digits are stored such that the most significant digit is at the head of the list.
 * Example:
 * Input: 1->2->3
 * Output: 1->2->4
 * 
 * Company: Google
 * Difficulty: medium
 * Similar Questions: 66(PlusOne)
 */
public class PlusOneLinkedList {
    public ListNode plusOne(ListNode head) {
        if (head == null) return null;

        head = reverse(head);
        ListNode current = head, prev = null;
        while (current != null) {
            if (current.val < 9) {
                current.val++;
                break;
            }
            current.val = 0;
            prev = current;
            current = current.next;
        }
        if (current == null) prev.next = new ListNode(1);

        return reverse(head);
    }

    private ListNode reverse(ListNode head) {
        ListNode current = head, prev = null, next = null;
        while (current != null) {
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        return prev;
    }
}
