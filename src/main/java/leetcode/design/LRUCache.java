package leetcode.design;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/*
 * Design and implement a data structure for Least Recently Used (LRU) cache. It should support the following operations: get and put.
 * get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
 * put(key, value) - Set or insert the value if the key is not already present. When the cache reached its capacity, it should invalidate 
 * the least recently used item before inserting a new item.
 * Follow up: Could you do both operations in O(1) time complexity?
 */
public class LRUCache {
	
	
	// use LinkedHashMap
	/*
	private final Map<Integer, Integer> cache;
	public LRUCache(int capacity) {
        cache = new LinkedHashMap<Integer, Integer>(capacity, 0.75f, true) {
        	@Override
            protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
               return size() > capacity;
            }
        };
    }
    
    public int get(int key) {
    	if (cache.containsKey(key)) return cache.get(key);
    	return -1;
    }
    
    public void put(int key, int value) {
        cache.put(key, value);
    }
    */

	// use HashMap and double linked list
	/*
	private static class Node {
		int key;
		int value;
		Node prev;
		Node next;
		Node(int key, int value) {
			this.key = key;
			this.value = value;
		}
	}
	
	private Node head, tail;
	private final Map<Integer, Node> cache;
	private final int capacity;
	public LRUCache(int capacity) {
		this.capacity = capacity;
		cache = new HashMap<>(capacity);
		head = new Node(0, 0);
		tail = new Node(0, 0);
		// initially head and tail point to each other
		head.next = tail;
		tail.prev = head;
		
	}
	
	public int get(int key) {
    	if (cache.containsKey(key)) {
    		Node node = cache.get(key);
    		deleteNode(node);
    		addToTail(node);
    		return node.value;
    	}
    	return -1;
    }
	
	public void put(int key, int value) {
		if (cache.containsKey(key)) {
    		Node node = cache.get(key);
    		node.value = value;
    		deleteNode(node);
    		addToTail(node);
    	}
		else {
			if (cache.size() == capacity) {
				cache.remove(head.next.key);
				deleteNode(head.next);
			}
			Node node = new Node(key, value);
			addToTail(node);
			cache.put(key,  node);
		}
	}
	
	private void deleteNode(Node node) {
		node.prev.next = node.next;
		node.next.prev = node.prev;
		node.prev = null;
		node.next = null;
	}
	
	private void addToTail(Node node) {
		tail.prev.next = node;
		node.prev = tail.prev;
		node.next = tail;
		tail.prev = node;
	}*/
	
	private class Node {
        int key;
        int val;
        Node pre;
        Node next;
    }
    private Node head;
    private Node tail;
    private HashMap<Integer, Node> map;
    private int capacity;
    public LRUCache(int capacity) {
        map = new HashMap<>();
        head = new Node();
        tail = new Node();
        
        head.pre = null;
        head.next = tail;
        tail.pre = head;
        tail.next = null;
        this.capacity = capacity;
    }
    
    public int get(int key) {
        Node node = map.get(key);
        if(node == null) return -1;
        else {
            remove(node);
            addLast(node);
            return node.val;
        }
    }
    
    public void put(int key, int value) {
        Node node = map.get(key);
        if(node == null) {
            if(map.size() >= capacity) {
                Node h = head.next;
                remove(h);
                map.remove(h.key);
            }
            node = new Node();
            node.key = key;
            node.val = value;
            addLast(node);
            map.put(key,node);
        }
        else {
            remove(node);
            addLast(node);
            node.val = value;
        }
    }
    
    private void remove(Node node) {
        node.pre.next = node.next;
        node.next.pre = node.pre;
    }
    
    private void addLast(Node node) {
        tail.pre.next = node;
        node.pre = tail.pre;
        node.next = tail;
        tail.pre = node;
    }
	
	public static void main(String[] args) {
		LRUCache c = new LRUCache( 2 /* capacity */ );

		c.put(1, 1);
		c.put(2, 2);
		System.out.print("get 1 " + c.get(1));       // returns 1
		c.put(3, 3);    // evicts key 2
		System.out.print("get 2 " + c.get(2));       // returns -1 (not found)
		c.put(4, 4);    // evicts key 1
		System.out.print("get 1 " + c.get(1));       // returns -1 (not found)
		System.out.print("get 3 " + c.get(3));       // returns 3
		System.out.print("get 4 " + c.get(4));       // returns 4
	}
}
