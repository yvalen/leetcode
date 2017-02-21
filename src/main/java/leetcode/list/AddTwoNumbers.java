package leetcode.list;

/*
 * You are given two non-empty linked lists representing two non-negative integers. 
 * The digits are stored in reverse order and each of their nodes contain a single digit. 
 * Add the two numbers and return it as a linked list.
 * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 * Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
 * Output: 7 -> 0 -> 8
 */
public class AddTwoNumbers {
	public ListNode addTwoNumbers_andnullcheck(ListNode l1, ListNode l2) {
		if (l1 == null && l2 == null) return null;
		else if (l1 == null) return l2;
		else if (l2 == null) return l1;
		
		boolean addOne = false;
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;
        ListNode c1 = l1, c2 = l2;
        while (c1 != null && c2 != null) {
        	int val = c1.val + c2.val + (addOne ? 1 : 0);
        	if (val >= 10) {
        		val = val % 10;
        		addOne = true;
        	}
        	else {
        		addOne = false;
        	}
        	current.next = new ListNode(val);
        	current = current.next;
        	c1 = c1.next;
        	c2 = c2.next;
        }
        
        ListNode c = c1 == null ? c2 : c1;
        current.next = c;
        while (c != null && addOne) {
        	c.val++;
			if (c.val < 10) {
				addOne = false;
			}
			else {
				c.val = c.val % 10;
				c = c.next;
				current = current.next;
			}
        }
        
        if (addOne) {
        	current.next = new ListNode(1);
        }
      
        return dummy.next;
    }

	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		if (l1 == null && l2 == null) return null;
		else if (l1 == null) return l2;
		else if (l2 == null) return l1;

        ListNode dummy = new ListNode(0);
        ListNode current = dummy;
        ListNode c1 = l1, c2 = l2;
        int sum = 0;
        while (c1 != null || c2 != null) {
        	// previous sum, divided by 10 to get the number to add to the new sum
        	sum = sum / 10;
        	if (c1 != null) {
        		sum += c1.val;
        		c1 = c1.next;
        	}
        	if (c2 != null) {
        		sum += c2.val;
        		c2 = c2.next;
        	}
        	current.next = new ListNode(sum % 10);
        	current = current.next;
        }
        	
        // take care the overflow of the last digit
        if (sum / 10 > 0) {
        	current.next = new ListNode(1);
        }
      
        return dummy.next;
    }

	
	public static void main(String[] args) {
		AddTwoNumbers a = new AddTwoNumbers();
		
		int[] nums1 = {1, 8};
		int[] nums2 = {0};
		ListNode l1 = ListUtil.createList(nums1);
		ListNode l2 = ListUtil.createList(nums2);
		ListNode l = a.addTwoNumbers(l1, l2);
		ListUtil.printList(l);
	}
}
