package leetcode.design;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/*
 * LEETCODE 146
 * Design and implement a data structure for Least Recently Used (LRU) cache. 
 * It should support the following operations: get and put.
 * get(key) - Get the value (will always be positive) of the key if the key 
 * exists in the cache, otherwise return -1.
 * put(key, value) - Set or insert the value if the key is not already present. 
 * When the cache reached its capacity, it should invalidate the least recently 
 * used item before inserting a new item.
 * Follow up: Could you do both operations in O(1) time complexity?
 * 
 * Company: Google, Facebook, Amazon, Microsoft, Bloomberg, Uber, Twitter, Snapchat, 
 * Zenefits, Yahoo, Palantir
 * Difficulty: hard
 * Similar Questions: 604(CompressedStringIterator)
 * 
 * https://github.com/google/guava/blob/master/guava/src/com/google/common/cache/LocalCache.java
 * http://highscalability.com/blog/2016/1/25/design-of-a-modern-cache.html
 * Concurrency:
 * 1. single relock, low throughput
 * 2. separate read and write lock, read can be fast
 * 3. lock striping: split the cache into smaller regions, hot entries could cause some 
 * locks to be more contented than others. 
 * 4. commit log: store all the mutations into logs rather than update immediately. And 
 * then some background processes will execute all the logs asynchronously. 
 */
public class LRUCache {

    /*
     * // use LinkedHashMap private final Map<Integer, Integer> cache; public
     * LRUCache(int capacity) { cache = new LinkedHashMap<Integer,
     * Integer>(capacity, 0.75f, true) {
     * 
     * @Override protected boolean removeEldestEntry(Map.Entry<Integer, Integer>
     * eldest) { return size() > capacity; } }; }
     * 
     * public int get(int key) { if (cache.containsKey(key)) return
     * cache.get(key); return -1; }
     * 
     * public void put(int key, int value) { cache.put(key, value); }
     */

    // use HashMap and double linked list
    private static class Node {
        private int key;
        private int value;
        private Node prev;
        private Node next;

        Node() {
        }

        Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    private final Node head;
    private final Node tail;
    private final Map<Integer, Node> map;
    private final int capacity;

    public LRUCache(int capacity) {
        map = new HashMap<>();
        this.capacity = capacity;

        head = new Node();
        tail = new Node();
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        Node node = map.get(key);
        if (node == null)
            return -1;
        remove(node);
        addLast(node);
        return node.value;
    }

    public void put(int key, int value) {
        Node node = map.get(key);
        if (node == null) {
            if (map.size() >= capacity) {
                int keyToRemove = head.next.key;
                remove(head.next);
                map.remove(keyToRemove);
            }
            node = new Node(key, value);
            addLast(node);
            map.put(key, node);
        } else {
            remove(node);
            addLast(node);
            node.value = value;
        }
    }

    private void remove(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private void addLast(Node node) {
        tail.prev.next = node;
        node.prev = tail.prev;
        node.next = tail;
        tail.prev = node;
    }

    public static void main(String[] args) {
        LRUCache c = new LRUCache(2 /* capacity */ );

        c.put(1, 1);
        c.put(2, 2);
        System.out.print("get 1 " + c.get(1)); // returns 1
        c.put(3, 3); // evicts key 2
        System.out.print("get 2 " + c.get(2)); // returns -1 (not found)
        c.put(4, 4); // evicts key 1
        System.out.print("get 1 " + c.get(1)); // returns -1 (not found)
        System.out.print("get 3 " + c.get(3)); // returns 3
        System.out.print("get 4 " + c.get(4)); // returns 4
    }
}
