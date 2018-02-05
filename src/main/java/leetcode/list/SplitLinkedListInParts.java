package leetcode.list;

/*
 * LEETCODE 725
 * Given a (singly) linked list with head node root, write a function to split 
 * the linked list into k consecutive linked list "parts". The length of each 
 * part should be as equal as possible: no two parts should have a size differing 
 * by more than 1. This may lead to some parts being null. The parts should be in 
 * order of occurrence in the input list, and parts occurring earlier should always 
 * have a size greater than or equal parts occurring later.
 * Return a List of ListNode's representing the linked list parts that are formed.
 * Examples 1->2->3->4, k = 5 // 5 equal parts [ [1], [2], [3], [4], null ]
 * Example 1:
 * Input: 
 * root = [1, 2, 3], k = 5
 * Output: [[1],[2],[3],[],[]]
 * Explanation:
 * The input and each element of the output are ListNodes, not arrays.
 * For example, the input root has root.val = 1, root.next.val = 2, root.next.next.val = 3, 
 * and root.next.next.next = null.
 * The first element output[0] has output[0].val = 1, output[0].next = null.
 * The last element output[4] is null, but it's string representation as a ListNode is [].
 * Example 2:
 * Input: 
 * root = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10], k = 3
 * Output: [[1, 2, 3, 4], [5, 6, 7], [8, 9, 10]]
 * Explanation: The input has been split into consecutive parts with size difference at most 1, 
 * and earlier parts are a larger size than the later parts.
 * Note:
 * - The length of root will be in the range [0, 1000].
 * - Each value of a node in the input will be an integer in the range [0, 999].
 * - k will be an integer in the range [1, 50].
 * 
 * Company: Amazon
 * Difficulty: medium
 * Similar Questions: 
 */
public class SplitLinkedListInParts {
	public static ListNode[] splitListToParts(ListNode root, int k) {
		ListNode[] result = new ListNode[k];

		if (root == null) return result;

		int n = getLength(root);
		int len = n / k, extra = n % k;
		ListNode current = root;
		for (int i = 0; i < k; i++) {
			ListNode start = current;
			for (int j = 0; j < len+(i<extra?1:0)-1; j++) {
				if (current != null ) current = current.next;
			}
			if (current != null) {
				ListNode next = current.next;
				current.next = null;
				current = next;
			}
			result[i] = start;
		}

		return result;
	}

	private static int getLength(ListNode node) {
		int len = 0;
		while (node != null) {
			len++;
			node = node.next;
		}
		return len;
	}

	public static void main(String[] args) {
		ListNode dummy = new ListNode(0);
		ListNode current = dummy;
		int n = 3;
		for (int i = 1; i <= n; i++) {
			current.next = new ListNode(i);
			current = current.next;
		}

		int k = 5;
		ListNode[] result = splitListToParts(dummy.next, k);
		for (ListNode node : result) {
			while (node != null) {
				System.out.println(node.val + ",");
			}
			System.out.println();
		}
	}
}
