package leetcode.list;

/**
 * Given a list, rotate the list to the right by k places, where k is non-negative.
 * For example: Given 1->2->3->4->5->NULL and k = 2, return 4->5->1->2->3->NULL.
 */
public class RotateList {
	

	public ListNode rotateRight(ListNode head, int k) {
		if (head == null) return null;
        
        ListNode current = head;
        int n = 0;
        while (current != null) {
            n++;
            current = current.next;
        }
        
        if (k > n) {
        	k = k % n;
        }
		
		ListNode fast = head, slow = head, slowPrev = null, fastPrev = null;
		for (int i = 0; i < k && fast != null; i++) {
			fast = fast.next;
		}
		
		while (fast != null) {
			slowPrev = slow;
			fastPrev = fast;
			fast = fast.next;
			slow = slow.next;
		}
		
		if (slowPrev != null) slowPrev.next = null;
		if (fastPrev != null) fastPrev.next = head;
		
		return slow;
    }

}
