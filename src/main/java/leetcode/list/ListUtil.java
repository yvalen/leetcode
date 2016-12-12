package leetcode.list;

public class ListUtil {

	public static ListNode createList(int[] values) {
		int len = values.length;
		if (len == 0) return null;
		
		ListNode head = new ListNode(values[0]);
		ListNode current = head;
		for (int i = 1; i < len; i++) {
			current.next =  new ListNode(values[i]);
			current = current.next;
		}
		
		return head;
	}
	
	public static void printList(ListNode head) {
		if (head == null) {
			System.out.println("null");
			return;
		}
		
		StringBuilder sb = new StringBuilder();
		for (ListNode current = head; current != null; current = current.next) {
			sb.append(current.val).append(",");
		}
		sb.setCharAt(sb.length()-1, '\n');
		System.out.print(sb.toString());
	}
}
