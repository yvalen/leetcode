package leetcode.list;

/**
 * http://www.cnblogs.com/hiddenfox/p/3408931.html Floyd's cycle detection
 * algorithm, a.k.a Hare Tortoise algorithm:
 * https://en.wikipedia.org/wiki/Cycle_detection
 */
public class LinkListCycle {

    /**
     * LEETCODE 141 Given a linked list, determine if it has a cycle in it
     * http://codingfreak.blogspot.com/2012/09/detecting-loop-in-singly-linked-
     * list_22.html
     * 
     * Company: Amazon, Bloomberg, Microsoft, Yahoo Difficulty: easy Similar
     * Questions: 142(Linked List Cycle II)
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
     * LEETCODE 142 Given a linked list, return the node where the cycle begins.
     * If there is no cycle, return null. Note: Do not modify the linked list.
     * 
     * Difficulty: medium Similar Questions: 141(Linked List Cycle),
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
        boolean hasCycle = false;
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
