package leetcode.list;

import java.util.List;

public class ReverseList {
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
		if (head == null || head.next == null) return head;
		
		ListNode first = head;
		ListNode reversedHead = reverseListRecursive(head.next);
		
		first.next.next = first;
		first.next = null;
		
        return reversedHead;
    }
	
	
	/**
	 * Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.
	 * If the number of nodes is not a multiple of k then left-out nodes in the end should remain as it is.
	 * You may not alter the values in the nodes, only nodes itself may be changed. Only constant memory is allowed.
	 * For example, given this linked list: 1->2->3->4->5
	 * For k = 2, you should return: 2->1->4->3->5
	 * For k = 3, you should return: 3->2->1->4->5 
	 */
	public ListNode reverseKGroup(ListNode head, int k) {
		if (head == null || head.next == null || k ==1) return head;
		
		ListNode dummy = new ListNode(0);
		dummy.next = head;
		
		ListNode prev = dummy, last = head;
		int i = 0;
		while (last != null) {
			i++;
			if (i % k == 0) {
				prev = reverseSubGroup(prev, last.next);
				last = prev.next;
			}
			else {
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
		int[] ary = {1, 2, 3, 4};
		ListNode head = ListUtil.createList(ary);
		
		ReverseList r = new ReverseList();
		ListNode result = r.reverseKGroup(head, 2);
		ListUtil.printList(result);
	}

}
