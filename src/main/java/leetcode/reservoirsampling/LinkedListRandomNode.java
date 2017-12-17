package leetcode.reservoirsampling;

import java.util.Random;

import leetcode.list.ListNode;

/**
 * Reservoir sampling: https://en.wikipedia.org/wiki/Reservoir_sampling Randomly
 * choosing k samples from a list of n items, O(n) 1) Create an array
 * reservoir[0..k-1] and copy first k items of stream[] to it. 2) Now one by one
 * consider all items from (k+1)th item to nth item. a) Generate a random number
 * from 0 to i where i is index of current item in stream[]. Let the generated
 * random number is j. b) If j is in range 0 to k-1, replace reservoir[j] with
 * arr[i]
 * 
 */

/*
 * Given a singly linked list, return a random node's value from the linked
 * list. Each node must have the same probability of being chosen. Follow up:
 * What if the linked list is extremely large and its length is unknown to you?
 * Could you solve this efficiently without using extra space? Example: // Init
 * a singly linked list [1,2,3]. ListNode head = new ListNode(1); head.next =
 * new ListNode(2); head.next.next = new ListNode(3); Solution solution = new
 * Solution(head);
 * 
 * // getRandom() should return either 1, 2, or 3 randomly. Each element should
 * have equal probability of returning. solution.getRandom();
 * 
 * Company: Google Difficulty: medium
 */
public class LinkedListRandomNode {
    private ListNode head;
    private final Random random;

    public LinkedListRandomNode(ListNode head) {
        this.head = head;
        random = new Random();
    }

    /**
     * 1. Initialize result as first node result = head->key 2. Initialize n = 2
     * 3. Now one by one consider all nodes from 2nd node onward. a. Generate a
     * random number j from 0 to n-1. b. If j is equal to 0 replace result with
     * current node. c. n = n+1 d. current = current->next
     */
    /*
     * public int getRandom() { int result = head.val;
     * 
     * int len = 2; ListNode current = head.next; while (current != null) { if
     * (random.nextInt(len) == 0) { result = current.val; } current =
     * current.next; len++; }
     * 
     * return result; }
     */

    /*
     * Suppose we see a sequence of items, one at a time. We want to keep a
     * single item in memory, and we want it to be selected at random from the
     * sequence. If we know the total number of items (n), then the solution is
     * easy: select an index i between 1 and n with equal probability, and keep
     * the i-th element. The problem is that we do not always know n in advance.
     * A possible solution is the following: Keep the first item in memory. When
     * the i-th item arrives (for i > 1): - with probability 1 / i, keep the new
     * item (discard the old one) - with probability 1 − 1 / i, keep the old
     * item (ignore the new one) So: - when there is only one item, it is kept
     * with probability 1; - when there are 2 items, each of them is kept with
     * probability 1/2; - when there are 3 items, the third item is kept with
     * probability 1/3, and each of the previous 2 items are also kept with
     * probability (1/2)(1-1/3) = (1/2)(2/3) = 1/3; - by induction, it is easy
     * to prove that when there are n items, each item is kept with probability
     * 1/n.
     */
    public int getRandom() {
        int result = head.val;
        int i = 0;
        ListNode current = head.next;
        while (current != null) {
            if (i == random.nextInt(i + 1))
                result = current.val;
            current = current.next;
            i++;
        }

        return result;
    }
}
