package leetcode.list;

import java.util.PriorityQueue;
import java.util.Queue;

public class MergeList {

    /**
     * LEETCODE 21 Merge two sorted linked lists and return it as a new list.
     * The new list should be made by splicing together the nodes of the first
     * two lists.
     * 
     * Company: Microsoft, Amazon, LinkedIn, Apple Difficulty: easy Similar
     * Questions: 244(WordDistiance)
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null)
            return l2;
        if (l2 == null)
            return l1;

        ListNode dummy = new ListNode(0);
        ListNode current = dummy;
        while (l1 != null || l2 != null) {
            if (l1 == null) {
                current.next = l2;
                break; // no need to go further
            } else if (l2 == null) {
                current.next = l1;
                break; // no need to go further
            } else if (l1.val < l2.val) {
                current.next = l1;
                l1 = l1.next;
            } else {
                current.next = l2;
                l2 = l2.next;
            }
            current = current.next;
        }

        return dummy.next;
    }

    public ListNode mergeTwoListsRecursive(ListNode l1, ListNode l2) {
        if (l1 == null)
            return l2;
        if (l2 == null)
            return l1;

        ListNode head;
        if (l1.val < l2.val) {
            head = l1;
            head.next = mergeTwoListsRecursive(l1.next, l2);
        } else {
            head = l2;
            head.next = mergeTwoListsRecursive(l1, l2.next);
        }

        return head;
    }

    /**
     * LEETCODE 23 Merge k sorted linked lists and return it as one sorted list.
     * Analyze and describe its complexity.
     * 
     * Company: Google, Facebook, Microsoft, Amazon, Uber, LinkedIn, Twitter,
     * Airbnb, IXL Difficulty: hard Similar Questions: 21(Merge Two Sorted
     * Lists)
     */
    // Time complexity: O(nlogk)
    public ListNode mergeKLists_withMinHeap(ListNode[] lists) {
        if (lists == null || lists.length == 0)
            return null;

        ListNode dummy = new ListNode(0);
        ListNode current = dummy;

        Queue<ListNode> minHeap = new PriorityQueue<>(lists.length, (ListNode o1, ListNode o2) -> o1.val - o2.val);

        // insert the first element of each list into priority queue
        // elements in priority queue are ordered according to their natural
        // ordering
        // natural ordering for primitive type is ascending order
        for (ListNode list : lists) {
            if (list != null) { // need to check for null here because the list
                                // element could be null, e.g. [[]]
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

    // Divide and Conquer
    // 1. If there is a single list, return it as the result
    // 2. If there are two lists, return their merged lists as the result
    // 3. Split the list into two halves, recursively merge the lower half and
    // the upper half,
    // and then return the merge of the two halves as the result
    public ListNode mergeKLists_divideConquer(ListNode[] lists) {
        if (lists == null)
            return null;
        return mergeLists(lists, 0, lists.length - 1);
    }

    private ListNode mergeLists(ListNode[] lists, int lo, int hi) {
        if (lo > hi)
            return null;
        if (lo == hi)
            return lists[lo]; // single list
        if (lo == hi - 1)
            return mergeTwoLists(lists[lo], lists[hi]); // two lists

        int mid = lo + (hi - lo) / 2;
        ListNode l1 = mergeLists(lists, lo, mid);
        ListNode l2 = mergeLists(lists, mid + 1, hi);
        return mergeTwoLists(l1, l2);
    }
}
