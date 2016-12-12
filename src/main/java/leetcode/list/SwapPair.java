package leetcode.list;

/**
 * Given a linked list, swap every two adjacent nodes and return its head.
 * For example, given 1->2->3->4, you should return the list as 2->1->4->3.
 * Your algorithm should use only constant space. 
 * You may not modify the values in the list, only nodes itself can be changed. 
 */
public class SwapPair {
	public ListNode swapPairs(ListNode head) {
		ListNode dummy = new ListNode(0);
        dummy.next = head;
		ListNode prev = dummy, curr = head;
		while (curr != null && curr.next != null) {
			ListNode next = curr.next, nextNext = curr.next.next;
			next.next = curr;
			curr.next = nextNext;
			prev.next = next;
			prev = curr;
			curr = nextNext;
		}
		
        return dummy.next;
    }

}
