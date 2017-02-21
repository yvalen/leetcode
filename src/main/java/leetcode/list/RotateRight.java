package leetcode.list;

/**
 * Given a list, rotate the list to the right by k places, where k is non-negative.
 * For example:
 * Given 1->2->3->4->5->NULL and k = 2,
 * return 4->5->1->2->3->NULL.
 */
public class RotateRight {
	public ListNode rotateRight(ListNode head, int k) {
		ListNode p1 = head, p2 = head;
		for (int i = 0; i < k && p1 != null; i++) {
    	   p1 = p1.next;
		}
       
		if (p1 == null) return head;
       
		while (p1.next != null) {
			p1 = p1.next;
			p2 = p2.next;
		}
       
		p1.next = head;
		head = p2.next;
		p2.next = null;
       
		return head;
    }

}
