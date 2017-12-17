package leetcode.list;

import java.util.HashMap;
import java.util.Map;

/**
 * LEETCODE 138 A linked list is given such that each node contains an
 * additional random pointer which could point to any node in the list or null.
 * Return a deep copy of the list.
 * http://fisherlei.blogspot.com/2013/11/leetcode-copy-list-with-random-pointer.
 * html
 * 
 * Company: Amazon, Microsoft, Bloomberg, Uber Difficulty: medium Similar
 * Questions: 133(CloneGraph)
 */
public class CopyWithRandomPointer {
    class RandomListNode {
        int label;
        RandomListNode next, random;

        RandomListNode(int x) {
            this.label = x;
        }
    };

    public RandomListNode copyRandomList_withAdditionalNode(RandomListNode head) {
        if (head == null)
            return null;

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
            if (next != null)
                current.next.next = next.next;
            current.next = null;
            current = next;
        }

        return newHead;
    }

    public RandomListNode copyRandomList_withMap(RandomListNode head) {
        if (head == null)
            return null;

        // create the copy for each node and store the mappings
        Map<RandomListNode, RandomListNode> map = new HashMap<>();
        for (RandomListNode current = head; current != null; current = current.next) {
            map.put(current, new RandomListNode(current.label));
        }

        // traverse the list and populate next and random pointers properly in
        // the copied nodes
        RandomListNode newHead = map.get(head);
        RandomListNode current = newHead;
        while (head != null) {
            current.next = map.get(head.next);
            current.random = map.get(head.random);
            head = head.next;
            current = current.next; // need to advance current too
        }
        return newHead;
    }

}
