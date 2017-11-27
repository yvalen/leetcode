package leetcode.list;

/**
 * LEETCODE 24
 * Given a linked list, swap every two adjacent nodes and return its head.
 * For example, given 1->2->3->4, you should return the list as 2->1->4->3.
 * Your algorithm should use only constant space. 
 * You may not modify the values in the list, only nodes itself can be changed. 
 * 
 * Company: Microsoft, Uber, Bloomberg
 * Difficulty: medium
 * Similar Questions: 25(ReverseNodesInKGroup)
 */
public class SwapPair {
	public ListNode swapPairs_recursive(ListNode head) {
        if (head == null || head.next == null) return head;
 
        ListNode newHead = head.next, next = head.next.next;
        head.next.next = null;
        newHead.next = head;
        head.next = swapPairs_recursive(next);
        return newHead;     
    }
	
	
	public ListNode swapPairs_iterative(ListNode head) {
		ListNode dummy = new ListNode(0);
        dummy.next = head;
		ListNode prev = dummy, curr = head;
		while (curr != null && curr.next != null) {
			ListNode next = curr.next, nextNext = curr.next.next;
			next.next = curr;
			curr.next = nextNext;
			prev.next = next;
			prev = curr; // prev should be assigned to curr instead of next here because next and curr haven been swapped already
			curr = nextNext;
		}
		
        return dummy.next;
    }

}
