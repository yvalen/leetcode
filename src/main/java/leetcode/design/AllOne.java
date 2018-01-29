package leetcode.design;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/*
 * LEETCODE 432
 * 
 * Implement a data structure supporting the following operations:
 * 1. Inc(Key) - Inserts a new key with value 1. Or increments an existing key by 1. 
 * Key is guaranteed to be a non-empty string.
 * 2. Dec(Key) - If Key's value is 1, remove it from the data structure. Otherwise 
 * decrements an existing key by 1. If the key does not exist, this function does nothing. 
 * Key is guaranteed to be a non-empty string.
 * 3. GetMaxKey() - Returns one of the keys with maximal value. If no element exists, 
 * return an empty string "".
 * 4. GetMinKey() - Returns one of the keys with minimal value. If no element exists, 
 * return an empty string "".
 * Challenge: Perform all these in O(1) time complexity. 
 * 
 * Company: Uber
 * Difficulty: hard
 */
public class AllOne {
    private static final class Node {
        private int count;
        private Set<String> keys;
        private Node prev;
        private Node next;
        Node() {}
        Node(int count) {
            this.count = count;
            keys = new HashSet<>();
        }
    }
    
    private final Map<String, Node> nodeMap;
    private final Node head, tail;

    /** Initialize your data structure here. */
    public AllOne() {
        nodeMap = new HashMap<>();
        head = new Node();
        tail = new Node();
        head.next = tail;
        tail.prev = head;
    }
    
    /** Inserts a new key <Key> with value 1. Or increments an existing key by 1. */
    public void inc(String key) {
        Node node = nodeMap.get(key);
        if (node == null) {
            if (head.next.count != 1) {
                insertAfter(new Node(1), head);
            }
            head.next.keys.add(key);
            nodeMap.put(key, head.next);
        }
        else {
            if (node.next.count != node.count+1) {
                insertAfter(new Node(node.count+1), node);
            }
            node.next.keys.add(key);
            nodeMap.put(key, node.next);
            node.keys.remove(key);
            if (node.keys.isEmpty()) removeNode(node);
        }
    }
    
    /** Decrements an existing key by 1. If Key's value is 1, remove it from the data structure. */
    public void dec(String key) {
        if (!nodeMap.containsKey(key)) return;
        Node node = nodeMap.get(key);
        if (node.count == 1) {
            nodeMap.remove(key);
        } else {
            if (node.prev.count != node.count-1) {
                insertAfter(new Node(node.count-1), node.prev);
            }
            node.prev.keys.add(key);
            nodeMap.put(key, node.prev);
        }
        node.keys.remove(key);
        if (node.keys.isEmpty()) removeNode(node);
    }
    
    /** Returns one of the keys with maximal value. */
    public String getMaxKey() {
        return (tail.prev == head) ? "" : tail.prev.keys.iterator().next();
    }
    
    /** Returns one of the keys with Minimal value. */
    public String getMinKey() {
        return (head.next == tail) ? "" : head.next.keys.iterator().next();
    }
    
    private void removeNode(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
        node.next = null;
        node.prev = null;    
    }
    
    private void insertAfter(Node node, Node prev) {
        prev.next.prev = node;
        node.next = prev.next;
        prev.next = node;
        node.prev = prev;
    }
    
    public static void main(String[] args) {
        AllOne ao = new AllOne();
        String key = "hello"; 
        ao.inc(key);
        ao.inc(key);
        System.out.println(ao.getMaxKey());
        System.out.println(ao.getMinKey());
        ao.inc("leet");
        System.out.println(ao.getMaxKey());
        System.out.println(ao.getMinKey());
    }

}
