package leetcode.list;

/*
 * Given a singly linked list, determine if it is a palindrome.
 * Follow up: Could you do it in O(n) time and O(1) space?
 */
public class PalindromeLinkedList {
	public boolean isPalindrome(ListNode head) {
		if (head == null || head.next == null) return true;
		
        // break the list into two halves
		ListNode slow = head, fast = head, prev = null;
		while (fast != null && fast.next != null) {
			prev = slow;
			slow = slow.next;
			fast = fast.next.next;
		}
		
		// slow is the head of the second half
		prev.next = null;
		
		// reverse the second half
		ListNode tail = reverse(slow);
		
		// compare the second half with the first half
		ListNode l1 = head, l2 = tail;
		while (l1 != null && l2 != null) {
			if (l1.val != l2.val) return false;
			l1 = l1.next;
			l2 = l2.next;
		}
		
		return true;
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

	public static void main(String[] args) {
		PalindromeLinkedList p = new PalindromeLinkedList();
		
		int[] ary = {0,0};
		ListNode head = ListUtil.createList(ary);
		System.out.println(p.isPalindrome(head));
		
	}
}
