package leetcode.list;

public class LinkListEasy {

	/**
	 *  Write a function to delete a node (except the tail) in a singly linked list, given only access to that node.
	 *  Supposed the linked list is 1 -> 2 -> 3 -> 4 and you are given the third node with value 3, 
	 *  the linked list should become 1 -> 2 -> 4 after calling your function. 
	 */
	public void deleteNode(ListNode node) {
        if (node == null || node.next == null) {
        	throw new IllegalArgumentException("invalid node");
        }
        
        node.val = node.next.val;
        node.next = node.next.next;
    }
	
	
	/**
	 * Remove all elements from a linked list of integers that have value val.
	 * Example
	 * Given: 1 --> 2 --> 6 --> 3 --> 4 --> 5 --> 6, val = 6
	 * Return: 1 --> 2 --> 3 --> 4 --> 5 
	 */
	public ListNode removeElements(ListNode head, int val) {
		ListNode current = head, prev = null, newHead = null;
		while (current != null) {
			if (current.val == val) {
				if (prev != null) {
					prev.next = current.next;
				}
			}
			else {
				if (newHead == null) newHead = current;
				prev = current;
				
			}
			current = current.next;	
		}
		
		return (prev==null) ? null : newHead;
    }
	
	public ListNode removeElementsWithDummyNode(ListNode head, int val) {
		ListNode dummy = new ListNode(0);
		dummy.next = head;
		ListNode current = dummy;
		while (current.next != null) {
			if (current.next.val == val) {
				current.next = current.next.next;
			}
			else {
				current = current.next;	
			}
		}
		
		return dummy.next;
    }
	
	
	/**
	 * Given a linked list, remove the nth node from the end of list and return its head.
	 * For example, given linked list: 1->2->3->4->5, and n = 2.
	 * After removing the second node from the end, the linked list becomes 1->2->3->5.
	 * Note: Given n will always be valid.Try to do this in one pass. 
	 */
	public ListNode removeNthFromEnd(ListNode head, int n) {
		ListNode p1 = head, p2 = head;
		
		for (int i = 0; i < n; i++) {
			p1 = p1.next;
		}
		
		// special handling for removing the first node
		if (p1 == null) {
			head = head.next;
			return head;
		}
		
		while (p1.next != null) {
			p2 = p2.next;
			p1 = p1.next;
		}
		p2.next = p2.next.next;
		
		return head;
    }
	
	public ListNode removeNthFromEndWithDummy(ListNode head, int n) {
		if (head == null) return head;
		
		ListNode dummy = new ListNode(0);
		dummy.next = head;
		ListNode p1 = head, p2 = dummy;
		
		for (int i = 0; i < n - 1; i++) {
			p1 = p1.next;
		}
		
		while (p1.next != null) {
			p2 = p2.next;
			p1 = p1.next;
		}
		
		p2.next = p2.next.next;
		
		return dummy.next;
    }
	
	
	/**
	 * Given a sorted linked list, delete all duplicates such that each element appear only once.
	 * For example,
	 * Given 1->1->2, return 1->2.
	 * Given 1->1->2->3->3, return 1->2->3. 
	 */
	public ListNode deleteDuplicates(ListNode head) {
		if (head == null || head.next == null) return head;
		
		ListNode current = head, next = head.next;
		while (next != null) {
			if (current.val == next.val) {
				next = next.next;
			}
			else {
				current.next = next;
				current = current.next;
				next = next.next;
			}
		}
		current.next = null;
		
		return head;
    }
	
	public ListNode getSecondHalf(ListNode head) {
		if (head == null || head.next == null) return head;
		
		ListNode slow = head, fast = head;
		while (fast != null && fast.next != null) {
			slow = slow.next;
			fast = fast.next.next;
		}
		return slow;
	}
	
	
	public static void main(String[] args) {
		LinkListEasy testObj = new LinkListEasy();
		
		int[] ary1 = {1,3,5,7,9,11,13,15,17,19,21};
		int[] ary2 = {2};
		ListNode list1 = ListUtil.createList(ary1);
		ListNode list2 = ListUtil.createList(ary2);
		
		//ListNode intersection = testObj.getIntersectionNode(list1, list2);
		//ListUtil.printList(intersection);
		
		int[] ary3 = {1, 2, 3};
		ListNode list3 = ListUtil.createList(ary3);
		ListNode secondHalf = testObj.getSecondHalf(list3);
		ListUtil.printList(secondHalf);
	}
}
