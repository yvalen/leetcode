package leetcode.list;

/**
 * LEETCODE 160 Write a program to find the node at which the intersection of
 * two singly linked lists begins. For example, the following two linked lists:
 *
 * A: a1 → a2 ↘ c1 → c2 → c3 ↗ B: b1 → b2 → b3 begin to intersect at node c1.
 * Notes: - If the two linked lists have no intersection at all, return null. -
 * The linked lists must retain their original structure after the function
 * returns. - You may assume there are no cycles anywhere in the entire linked
 * structure. - Your code should preferably run in O(n) time and use only O(1)
 * memory.
 * 
 * Company: Microsoft, Amazon, Bloomberg, Airbnb Difficulty: easy Similar
 * Questions:
 *
 * http://www.geeksforgeeks.org/write-a-function-to-get-the-intersection-point-
 * of-two-linked-lists/
 */
public class IntersectionOfTwoLinkedList {

    // We can use two iterations. In the first iteration, we will reset the
    // pointer of one linked list
    // to the head of another after it reaches the tail node. In the second
    // iteration, we will move two
    // pointers until they points to the same node. Our operations in first
    // iteration will help us counteract
    // the difference. So if two linked list intersects, the meeting point in
    // second iteration must be the
    // intersection point. If the two linked lists have no intersection at all,
    // then the meeting pointer in
    // second iteration must be the tail node of both lists, which is null
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null)
            return null;

        ListNode a = headA, b = headB;
        while (a != b) {
            a = (a == null) ? headB : a.next;
            b = (b == null) ? headA : b.next;
        }
        return a;
    }

    // 1. Get the length of the two lists.
    // 2 Align them to the same start point.
    // 3. Move them together until finding the intersection point, or the end
    // null
    public ListNode getIntersectionNode_withLength(ListNode headA, ListNode headB) {
        int lenA = length(headA), lenB = length(headB);

        while (lenA > lenB) {
            headA = headA.next;
            lenA--;
        }
        while (lenB > lenA) {
            headB = headB.next;
            lenB--;
        }

        while (headA != headB) {
            headA = headA.next;
            headB = headB.next;
        }
        return headA == null ? headA : headB;

        /*
         * int lenA = 0, lenB = 0;
         * 
         * // find the length of A for (ListNode a = headA; a != null; a =
         * a.next) { lenA++; }
         * 
         * // find the length of B for (ListNode b = headB; b != null; b =
         * b.next) { lenB++; }
         * 
         * if (lenA == 0 || lenB == 0) return null;
         * 
         * ListNode a = headA, b = headB; int diff = 0, i = 0; // move to the
         * proper position in the long list if (lenA > lenB) { diff = lenA -
         * lenB; while (i < diff) { i++; a = a.next; } } else { diff = lenB -
         * lenA; while (i < diff) { i++; b = b.next; } }
         * 
         * while (a != null) { if (a.val == b.val) return a; a = a.next; b =
         * b.next; }
         * 
         * return null;
         */
    }

    private int length(ListNode node) {
        int len = 0;
        while (node != null) {
            len++;
            node = node.next;
        }
        return len;
    }

    public static void main(String[] args) {
        IntersectionOfTwoLinkedList ill = new IntersectionOfTwoLinkedList();

        ListNode c = new ListNode(4);
        c.next = new ListNode(5);
        ListNode headA = new ListNode(1);
        headA.next = new ListNode(3);
        headA.next.next = c;
        ListNode headB = new ListNode(2);
        headB.next = c;

        System.out.println(ill.getIntersectionNode_withLength(headA, headB));
    }

}
