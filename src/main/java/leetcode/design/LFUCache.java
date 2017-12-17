package leetcode.design;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

/*
 * LEETCODE 460
 * Design and implement a data structure for Least Frequently Used (LFU) cache. It should support the following operations: get and put.
 * get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
 * put(key, value) - Set or insert the value if the key is not already present. When the cache reaches its capacity, it should invalidate 
 * the least frequently used item before inserting a new item. For the purpose of this problem, when there is a tie (i.e., two or more keys 
 * that have the same frequency), the least recently used key would be evicted.
 * Follow up: could you do both operations in O(1) time complexity?
 * 
 * Company: Google, Amazon
 * Difficulty: hard
 * Similar Questions: 146(LRUCache), 
 */
public class LFUCache {
    /*
     * // hashmap + priority queue private static class Node implements
     * Comparable<Node> { private int key; private int value; private int count;
     * private long ts;
     * 
     * @Override public String toString() { return "Node [key=" + key +
     * ", value=" + value + ", count=" + count + ", ts=" + ts + "]"; }
     * 
     * Node(int key, int value, long ts) { this.key = key; this.value = value;
     * this.count = 1; this.ts = ts; }
     * 
     * @Override public int compareTo(Node other) { return this.count ==
     * other.count? (int)(this.ts - other.ts) : this.count - other.count; } }
     * 
     * private final Map<Integer, Node> map; private final PriorityQueue<Node>
     * minHeap; private long accessCounter; // global counter of when the node
     * is last accessed private final int capacity;
     * 
     * public LFUCache(int capacity) { map = new HashMap<>(); minHeap = new
     * PriorityQueue<>(); this.capacity = capacity; }
     * 
     * public int get(int key) { Node node = map.get(key); if (node == null)
     * return -1; minHeap.remove(node); // need to update both count and ts for
     * the node node.count++; node.ts = ++accessCounter; minHeap.add(node);
     * return node.value; }
     * 
     * public void put(int key, int value) { // need to check if capacity is 0
     * first if (capacity == 0) { return; }
     * 
     * Node node = map.get(key); accessCounter++; if (node == null) { if
     * (map.size() == capacity) { Node nodeToRemove = minHeap.poll();
     * map.remove(nodeToRemove.key); // need to remove the key from map } node =
     * new Node(key, value, accessCounter); map.put(key, node); } else {
     * minHeap.remove(node); node.value = value; node.count++; node.ts =
     * accessCounter; } minHeap.add(node); }
     */

    //
    // HashMap + double linked list + LinkedHashSet
    //
    // Node class keeps track of the frequency of the keys
    private static class Node {
        @Override
        public String toString() {
            return "Node [count=" + count + ", keys=" + keys + ", prev=" + prev + ", next=" + next + "]";
        }

        int count;
        Set<Integer> keys;
        Node prev, next;

        Node(int count) {
            this.count = count;
            this.keys = new LinkedHashSet<>(); // use LinkedHashSet to preserve
                                               // the insertion order
        }

    }

    private final Map<Integer, Integer> valueMap;
    private final Map<Integer, Node> nodeMap;
    private final int capacity;
    private Node head;

    public LFUCache(int capacity) {
        valueMap = new HashMap<>();
        nodeMap = new HashMap<>();
        this.capacity = capacity;
        this.head = new Node(0);
    }

    public int get(int key) {
        Integer value = valueMap.get(key);
        if (value == null)
            return -1;

        // found value, need to increase frequency count for key
        increaseFrequencyCount(key);

        return value;
    }

    public void put(int key, int value) {
        // need to check if capacity is 0 first
        if (capacity == 0) {
            return;
        }

        if (valueMap.containsKey(key)) {
            // key exists, replace value and increase frequency count of the key
            valueMap.put(key, value);
            increaseFrequencyCount(key);
        } else {
            if (valueMap.size() == this.capacity) {
                // max capacity reached, delete the lease frequently used
                deleteLeastFrequentlyUsed();
            }

            // create frequency count 1 node if it doesn't exist
            Node next = head.next;
            if (next == null || next.count > 1) {
                Node node = new Node(1);
                node.prev = head;
                node.next = next;
                head.next = node;
                if (next != null)
                    next.prev = node;
            }

            // add the key to frequency count 1 node
            head.next.keys.add(key);

            // add key to both maps
            nodeMap.put(key, head.next);
            valueMap.put(key, value);
        }
    }

    private void increaseFrequencyCount(Integer key) {
        // find frequency node for key
        Node node = nodeMap.get(key);

        // create next frequency node if it doesn't exist
        if (node.next == null) {
            node.next = new Node(node.count + 1);
            node.next.prev = node;
        } else if (node.next.count != node.count + 1) {
            // count gap between node
            Node temp = new Node(node.count + 1);
            temp.next = node.next;
            node.next.prev = temp;
            temp.prev = node;
            node.next = temp;
        }

        // add key to the next frequency node
        node.next.keys.add(key);

        // update nodeMap
        nodeMap.put(key, node.next);

        // remove key from current frequency node
        node.keys.remove(key);

        // delete the frequency node if it doesn't contain any key
        if (node.keys.isEmpty()) {
            deleteNode(node);
        }
    }

    private void deleteLeastFrequentlyUsed() {
        // first node is the least frequently used
        Node node = head.next;

        // find the key to remove
        Integer keyToRemove = node.keys.iterator().next();

        // remove key from frequency node
        node.keys.remove(keyToRemove);

        // delete the frequency node if it doesn't contain any key
        if (node.keys.isEmpty()) {
            deleteNode(node);
        }

        // remove key from both maps
        nodeMap.remove(keyToRemove);
        valueMap.remove(keyToRemove);
    }

    private void deleteNode(Node node) {
        node.prev.next = node.next;
        if (node.next != null)
            node.next.prev = node.prev;
        node.next = null;
        node.prev = null;
    }

    public static void main(String[] args) {
        int capacity = 2;
        // int capacity = 1;
        // int capacity = 3;
        LFUCache c = new LFUCache(capacity);

        c.put(1, 1);
        // System.out.println(c.valueMap);
        c.put(2, 2);
        // System.out.println(c.valueMap);
        int v1 = c.get(1);
        System.out.println("v1=" + v1);
        c.put(3, 3);
        // System.out.println(c.valueMap);
        int v2 = c.get(2);
        System.out.println("v2=" + v2);
        int v3 = c.get(3);
        System.out.println("v3=" + v3);
        c.put(4, 4);
        // System.out.println(c.valueMap);
        int v4 = c.get(1);
        System.out.println("v4=" + v4);
        int v5 = c.get(3);
        System.out.println("v5=" + v5);
        int v6 = c.get(4);
        System.out.println("v6=" + v6);

        /*
         * c.put(2, 1); System.out.println(c.valueMap); int v1 = c.get(2);
         * System.out.println("v1=" + v1); c.put(3, 2); int v2 = c.get(2);
         * System.out.println("v2=" + v2); int v3 = c.get(3);
         * System.out.println("v3=" + v3);
         */

        /*
         * c.put(2, 2); System.out.println(c.valueMap); c.put(1, 1);
         * System.out.println(c.valueMap); int v1 = c.get(2);
         * System.out.println("v1=" + v1); int v2 = c.get(1);
         * System.out.println("v2=" + v2); int v3 = c.get(2);
         * System.out.println("v3=" + v3); c.put(3, 3);
         * System.out.println(c.valueMap); c.put(4, 4);
         * System.out.println(c.valueMap); int v4 = c.get(3);
         * System.out.println("v4=" + v4); int v5 = c.get(2);
         * System.out.println("v5=" + v5); int v6 = c.get(1);
         * System.out.println("v6=" + v6); int v7 = c.get(4);
         * System.out.println("v7=" + v7);
         */
    }
}
