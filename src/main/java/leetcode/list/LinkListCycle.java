package leetcode.list;

import javax.xml.stream.events.StartDocument;

import leetcode.tree.TreeNode;

/**
 * http://www.cnblogs.com/hiddenfox/p/3408931.html Floyd's cycle detection
 * algorithm, a.k.a Hare Tortoise algorithm:
 * https://en.wikipedia.org/wiki/Cycle_detection
 */
public class LinkListCycle {

    /**
     * LEETCODE 141 
     * Given a linked list, determine if it has a cycle in it
     * http://codingfreak.blogspot.com/2012/09/detecting-loop-in-singly-linked-list_22.html
     * 
     * Company: Amazon, Bloomberg, Microsoft, Yahoo 
     * Difficulty: easy 
     * Similar Questions: 142(Linked List Cycle II)
     */
    // Time Complexity: O(n), Space Complexity: O(1)
    public boolean hasCycle(ListNode head) {
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                return true;
            }
        }

        return false;
    }

    /**
     * LEETCODE 142 
     * Given a linked list, return the node where the cycle begins. If there is 
     * no cycle, return null. 
     * Note: Do not modify the linked list.
     * 
     * Difficulty: medium 
     * Similar Questions: 141(Linked List Cycle),
     * 287(FindDuplicateNumber)
     */
    public ListNode detectCycle(ListNode head) {
        // L1 is defined as the distance between the head point and entry point
        // L2 is defined as the distance between the entry point and the meeting
        // point
        // C is defined as the length of the cycle
        // the total distance of the slow pointer traveled when encounter is L1
        // + L2
        // the total distance of the fast pointer traveled when encounter is L1
        // + L2 + C
        // Because the total distance the fast pointer traveled is twice as the
        // slow pointer, Thus:
        // 2(L1 + L2) = L1 + L2 + C, leading to L1 = C - L2.
        ListNode slow = head, fast = head, entry = head;
        while (fast != null && fast.next != null && slow != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                while (slow != entry) {
                    entry = entry.next;
                    slow = slow.next;
                }
                return entry;
            }
        }
        return null;
    }
    
    /*
     * Return the length of the linked list with cycle
     * 1. Use the standard fast and slow pointer algorithm to find the loop detection point
     * 2. Take two pointers P1 and P2 at loop detection point. Place pointer P1 at the same 
     * place and move P2 forward one step at a time. Repeat until both the pointers meet together. 
     * Keep a count variable incremented for every iteration which gives length of the loop. 
     * Let say the length is L1
     * 3. Again take two pointers P1 and P2. P1 at the head of the linked list and P2 at the loop 
     * detection point. Forward both the pointers one step at a time. Repeat until both the pointers 
     * meet together. This procedure is equivalent to the one we use to calculate node that causes 
     * loop in a linked list. Keep a count variable incremented for every iteration which gives the 
     * length of the list until the merging point. Let say the length is L2
     * 4. Now the length of the list that contains loop is L1+ L2
     * 
     * https://crackinterviewtoday.wordpress.com/2010/03/17/calculate-length-of-the-linked-list-that-contains-loop/
     */
    public static int getLength(ListNode head) {
    		ListNode slow = head, fast = head, crossPoint = null;
    		while (fast != null && fast.next != null) {
    			slow = slow.next;
    			fast = fast.next.next;
    			if (slow == fast) {
    				crossPoint = slow;
    				break;
    			}
    		}
    		
    		if (crossPoint == null) return 0;
    		
    		int len = 1;
    		ListNode p1 = crossPoint.next;
    		while (p1 != crossPoint) {
    			p1 = p1.next;
    			len++;
    		}
    		
    		ListNode p2 = head;
    		while (p1 != p2) {
    			p1 = p1.next;
    			p2 = p2.next;
    			len++;
    		}
    		
    		return len;
    }

    public static void main(String[] args) {
    		/*
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        n1.next = n2;
        n2.next = n1;

        LinkListCycle cycle = new LinkListCycle();
        ListNode c = cycle.detectCycle(n1);
        System.out.println(c.val);
        */
        
        ListNode head = new ListNode(1), current = head, start = null;
        for (int i = 2; i <=5; i++) {
        		current.next = new ListNode(i);
        		current = current.next;
        		if (i == 3) start = current;
        }
        
        current.next = new ListNode(6);
        current.next.next = start;
        System.out.println(getLength(head));
    }

}
