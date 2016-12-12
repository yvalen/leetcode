package leetcode.list;

/**
 * http://www.cnblogs.com/hiddenfox/p/3408931.html
 */
public class LinkListCycle {
	
	
	/**
	 * Given a linked list, determine if it has a cycle in it
	 */
	public boolean hasCycle(ListNode head) {
		ListNode slow = head, fast = head;
		while (fast != null && fast.next != null && slow != null) {
			slow = slow.next;
			fast = fast.next.next;
			if (slow == fast) {
				return true;
			}
		}
		
		return false;
    }
	
	/**
	 *  Given a linked list, return the node where the cycle begins. 
	 *  If there is no cycle, return null.
	 *  Note: Do not modify the linked list.
	 */
	public ListNode detectCycle(ListNode head) {
		ListNode slow = head, fast = head;
		boolean hasCycle = false;
		while (fast != null && fast.next != null && slow != null) {
			slow = slow.next;
			fast = fast.next.next;
			if (slow == fast) {
				hasCycle = true;
				break;
			}
		}
		
		if (!hasCycle) {
			return null;
		}
		
		slow = head;
		while (slow != fast) {
			slow = slow.next;
			fast = fast.next ;
		}
		
		return slow;
    }
	
	public static void main(String[] args) {
		ListNode n1 = new ListNode(1);
		ListNode n2 = new ListNode(2);
		n1.next = n2;
		n2.next = n1;
		
		LinkListCycle cycle = new LinkListCycle();
		ListNode c = cycle.detectCycle(n1);
		System.out.println(c.val);
	}

}
