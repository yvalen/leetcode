package leetcode.design;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/*
 * LEETCODE 432
 * 
 * Implement a data structure supporting the following operations:
 * 1. Inc(Key) - Inserts a new key with value 1. Or increments an existing key by 1. Key is guaranteed to be a non-empty string.
 * 2. Dec(Key) - If Key's value is 1, remove it from the data structure. Otherwise decrements an existing key by 1. If the key 
 * does not exist, this function does nothing. Key is guaranteed to be a non-empty string.
 * 3. GetMaxKey() - Returns one of the keys with maximal value. If no element exists, return an empty string "".
 * 4. GetMinKey() - Returns one of the keys with minimal value. If no element exists, return an empty string "".
 * Challenge: Perform all these in O(1) time complexity. 
 * 
 * Company: Uber
 * Difficulty: hard
 */
public class AllOne {
    // Main idea is to maintain a list of Bucket's, each Bucket contains all
    // keys with the same count.
    // 1. head and tail can ensure both getMaxKey() and getMaxKey() be done in
    // O(1).
    // 2. keyCountMap maintains the count of keys, countBucketMap provides O(1)
    // access to a specific Bucket with given count.
    // Deleting and adding a Bucket in the Bucket list cost O(1), so both inc()
    // and dec() take strict O(1) time.

    // node of a doubly linked list
    private static class ListNode {
        @Override
        public String toString() {
            return "ListNode [count=" + count + ", keys=" + keys + "]";
        }

        int count;
        Set<String> keys;
        ListNode prev;
        ListNode next;

        public ListNode() {
        }

        public ListNode(int count) {
            this.count = count;
            keys = new HashSet<>();
        }

    }

    private final ListNode head;
    private final ListNode tail;
    private final Map<String, Integer> countMap; // stores the key and its
                                                 // corresponding count
    private final Map<Integer, ListNode> nodeMap; // stores the count and its
                                                  // corresponding node in the
                                                  // linked list

    public AllOne() {
        head = new ListNode();
        tail = new ListNode();
        // need to set both prev and next for head and tail
        head.next = tail;
        head.prev = tail;
        tail.prev = head;
        tail.next = head;
        countMap = new HashMap<>();
        nodeMap = new HashMap<>();
    }

    /**
     * Inserts a new key <Key> with value 1. Or increments an existing key by 1.
     */
    public void inc(String key) {
        if (countMap.containsKey(key)) {
            updateCount(key, 1);
        } else {
            countMap.put(key, 1);
            /*
             * if (head.next.count != 1) { insertNodeAfter(new ListNode(1),
             * head); nodeMap.put(1, head.next); } head.next.keys.add(key);
             */
            if (nodeMap.containsKey(1)) {
                nodeMap.get(1).keys.add(key);
            } else {
                ListNode newNode = new ListNode(1);
                newNode.keys.add(key);
                nodeMap.put(1, newNode);
                insertNodeAfter(newNode, head);
            }
        }
    }

    /**
     * Decrements an existing key by 1. If Key's value is 1, remove it from the
     * data structure.
     */
    public void dec(String key) {
        Integer count = countMap.get(key);
        if (count == null)
            return;

        if (count == 1) {
            removeKey(key, nodeMap.get(1));
            countMap.remove(key);
        } else {
            updateCount(key, -1);
        }
    }

    /** Returns one of the keys with maximal value. */
    public String getMaxKey() {
        return tail.prev == head ? "" : tail.prev.keys.iterator().next();
    }

    /** Returns one of the keys with Minimal value. */
    public String getMinKey() {
        return head.next == tail ? "" : head.next.keys.iterator().next();
    }

    private void insertNodeAfter(ListNode node, ListNode prev) {
        prev.next.prev = node;
        node.next = prev.next;
        prev.next = node;
        node.prev = prev;
    }

    private void removeNode(ListNode node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
        node.prev = null;
        node.next = null;
    }

    private void removeKey(String key, ListNode node) {
        node.keys.remove(key);
        if (node.keys.size() == 0) {
            removeNode(node);
            nodeMap.remove(node.count);
        }
    }

    private void updateCount(String key, int offset) {
        Integer count = countMap.get(key);
        ListNode node = nodeMap.get(count);
        Integer newCount = count + offset;
        if (nodeMap.containsKey(newCount)) {
            nodeMap.get(newCount).keys.add(key);
        } else {
            ListNode newNode = new ListNode(newCount);
            newNode.keys.add(key);
            insertNodeAfter(newNode, offset == 1 ? node : node.prev);
            nodeMap.put(newCount, newNode); // need to add the newCount to
                                            // nodeMap
        }
        countMap.put(key, newCount);
        removeKey(key, node);
    }

    private void printList() {
        ListNode node = head.next;
        while (node != tail) {
            System.out.println(node);
            node = node.next;
        }
    }

    public static void main(String[] args) {
        AllOne ao = new AllOne();
        /*
         * String key = "hello"; ao.inc(key);
         * System.out.println(ao.getMaxKey());
         * System.out.println(ao.getMinKey());
         */
        /*
         * ao.inc("hello"); ao.printList(); ao.inc("goodbye"); ao.printList();
         * ao.inc("hello"); ao.printList(); ao.inc("hello"); ao.printList();
         * System.out.println(ao.getMaxKey());
         * 
         * ao.inc("leet"); ao.inc("code"); ao.inc("leet"); ao.dec("hello");
         * ao.inc("leet"); ao.inc("code"); ao.inc("code");
         * System.out.println(ao.getMaxKey());
         */

        ao.inc("hello");
        ao.inc("world");
        ao.inc("leet");
        ao.inc("code");
        ao.inc("DS");
        ao.inc("leet");
        System.out.println(ao.getMaxKey());

        ao.inc("DS");
        ao.dec("leet");
        System.out.println(ao.getMaxKey());

        ao.dec("DS");
        ao.inc("hello");
        System.out.println(ao.getMaxKey());

        ao.inc("hello");
        ao.inc("hello");
        ao.dec("world");
        ao.dec("leet");
        ao.dec("code");
        ao.dec("DS");
        System.out.println(ao.getMaxKey());

        ao.inc("new");
        ao.inc("new");
        ao.inc("new");
        ao.inc("new");
        ao.inc("new");
        ao.inc("new");
        System.out.println(ao.getMaxKey());
        System.out.println(ao.getMinKey());

    }
}
