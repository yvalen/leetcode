package leetcode.list;

import java.util.PriorityQueue;
import java.util.Queue;

public class MergeList {
	
	/**
	 * Merge two sorted linked lists and return it as a new list. 
	 * The new list should be made by splicing together the nodes of the first two lists.
	 */
	public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
		ListNode dummy = new ListNode(0);
		ListNode current = dummy;
		while (l1 != null || l2 != null) {
			if (l1 == null) {
				current.next = l2;
				break;
			}
			else if (l2 == null) {
				current.next = l1;
				break;
			}
			else if (l1.val < l2.val) {
				current.next = l1;
				l1 = l1.next;
			}
			else {
				current.next = l2;
				l2 = l2.next;
			}
			current = current.next;
		}
	
		return dummy.next;
    }

	public ListNode mergeTwoListsRecursive(ListNode l1, ListNode l2) {
		if (l1 == null) return l2;
		if (l2 == null) return l1;
		
		ListNode head;
		if (l1.val < l2.val) {
			head = l1;
			head.next = mergeTwoListsRecursive(l1.next, l2);
		}
		else {
			head = l2;
			head.next = mergeTwoListsRecursive(l1, l2.next);
		}

		return head;
	}
	
	/**
	 * Merge k sorted linked lists and return it as one sorted list. 
	 * Analyze and describe its complexity. 
	 */
	public ListNode mergeKLists_withMinHeap(ListNode[] lists) {
		if (lists == null || lists.length == 0) return null;
		
		ListNode dummy = new ListNode(0);
		ListNode current = dummy;
		
		Queue<ListNode> minHeap = new PriorityQueue<>(lists.length,
				(ListNode o1, ListNode o2) -> o1.val - o2.val);
        
		// insert the first element of each list into priority queue
		// elements in priority queue are ordered according to their natural ordering
		// natural ordering for primitive type is ascending order 
		for (ListNode list : lists) {
			if (list != null) {
				minHeap.add(list);
			}
		}
		
		// remove the first element from minHeap which is the smallest
		// and inserts its next element into priority queue if it is available. 
		// repeat this until priority queue is empty
		while (!minHeap.isEmpty()) {
			ListNode elem = minHeap.poll();
			current.next = elem;
			if (elem.next != null) {
				minHeap.add(elem.next);
			}
			current = current.next;
		}
		
		return dummy.next;
    }
}
