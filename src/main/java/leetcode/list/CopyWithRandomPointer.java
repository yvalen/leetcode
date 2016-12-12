package leetcode.list;

/**
 * A linked list is given such that each node contains an additional random pointer 
 * which could point to any node in the list or null. Return a deep copy of the list. 
 * http://fisherlei.blogspot.com/2013/11/leetcode-copy-list-with-random-pointer.html
 */
public class CopyWithRandomPointer {
	class RandomListNode {
		int label;
		RandomListNode next, random;
		RandomListNode(int x) { this.label = x; }
	};
	
	public RandomListNode copyRandomList(RandomListNode head) {
		if (head == null) return null;
		
        // insert a copy after each node 
		RandomListNode current = head;
		while (current != null) {
			RandomListNode copy = new RandomListNode(current.label);
			copy.next = current.next;
			current.next = copy;
			current = copy.next;
		}
		
		// copy the random pointers
		current = head;
		while (current != null) {
			if (current.random != null) {
				current.next.random = current.random.next;
			}
			current = current.next.next;
		}
		
		// break the copy of list
		current = head;
		RandomListNode newHead = head.next, next = null;
		while (current != null) {
			next = current.next.next;
			if (next != null) current.next.next = next.next;
			current.next = null;
			current = next;
		}
	
		return newHead;
    }

}
