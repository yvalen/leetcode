package leetcode.design;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

/*
 * Design and implement a data structure for Least Frequently Used (LFU) cache. It should support the following operations: get and put.
 * get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
 * put(key, value) - Set or insert the value if the key is not already present. When the cache reaches its capacity, it should invalidate 
 * the least frequently used item before inserting a new item. For the purpose of this problem, when there is a tie (i.e., two or more keys 
 * that have the same frequency), the least recently used key would be evicted.
 * Follow up: could you do both operations in O(1) time complexity?
 */
public class LFUCache {
	
	// hashmap + priority queue
	/*
	private static class Node implements Comparable<Node> {
		int key;
		int val;
		int count;
		long ts;
		
		public Node(int key, int val, int count, long ts) {
			super();
			this.key = key;
			this.val = val;
			this.count = count;
			this.ts = ts;
		}

		@Override
		public int compareTo(Node o) {
			if (this.count == o.count) {
				return (int)(this.ts - o.ts);
			}
			return this.count - o.count;
		}
	}
	
	private final Map<Integer, Node> map;
	private final PriorityQueue<Node> minHeap;
	private final int capacity;
	private long accessCounter; // global counter of when the node is last accessed
	public LFUCache(int capacity) {
        map = new HashMap<>();
        minHeap = new PriorityQueue<>();
        this.capacity = capacity;
    }
    
    public int get(int key) {
    	Node node = map.get(key);
    	if (node == null) return -1;
    	minHeap.remove(node);
    	node.count++;
    	node.ts = accessCounter++;
    	minHeap.add(node);
    	return node.val;
    }
    
    public void put(int key, int value) {
    	if (capacity == 0) {
            return;
        }
    	
    	Node node = map.get(key);
    	long now = System.currentTimeMillis();
    	if (node == null) {
    		node = new Node(key, value, 1, accessCounter++);
    		if (map.size() == capacity) {
    			Node head = minHeap.poll();
    			map.remove(head.key);
    		}
    	}
    	else {
    		minHeap.remove(node);
    		node.val = value;
    		node.count++;
    		node.ts = accessCounter++;
    		
    	}
    	minHeap.offer(node);
		map.put(key, node);
    }
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
			this.keys = new LinkedHashSet<>(); // use LinkedHashSet to preserve the insertion order
		}
		
	}
	
	final Map<Integer, Integer> valueMap;
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
		if (value == null) return -1;
		
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
		}
		else {
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
				if (next != null) next.prev = node;
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
			node.next = new Node(node.count+1);
			node.next.prev = node;
		}
		else if (node.next.count != node.count+1) {
			// count gap between node
			Node temp = new Node(node.count+1);
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
		if (node.next != null) node.next.prev = node.prev;
		node.next = null;
		node.prev = null;
	}
	
	public static void main(String[] args) {
		int capacity = 2;
		//int capacity = 1;
		//int capacity = 3;
		LFUCache c = new LFUCache(capacity);
		
	
		c.put(1, 1);
		System.out.println(c.valueMap);
		c.put(2,  2);
		System.out.println(c.valueMap);
		int v1 = c.get(1);
		System.out.println("v1=" + v1);
		c.put(3,  3);
		System.out.println(c.valueMap);
		int v2 = c.get(2);
		System.out.println("v2=" + v2);
		int v3 = c.get(3);
		System.out.println("v3=" + v3);
		c.put(4,  4);
		System.out.println(c.valueMap);
		int v4 = c.get(1);
		System.out.println("v4=" + v4);
		int v5 = c.get(3);
		System.out.println("v5=" + v5);
		int v6 = c.get(4);
		System.out.println("v6=" + v6);
	
		
		/*
		c.put(2, 1);
		System.out.println(c.valueMap);
		int v1 = c.get(2);
		System.out.println("v1=" + v1);
		c.put(3,  2);
		int v2 = c.get(2);
		System.out.println("v2=" + v2);
		int v3 = c.get(3);
		System.out.println("v3=" + v3);
		*/
		
		/*
		c.put(2,  2);
		System.out.println(c.valueMap);
		c.put(1, 1);
		System.out.println(c.valueMap);
		int v1 = c.get(2);
		System.out.println("v1=" + v1);
		int v2 = c.get(1);
		System.out.println("v2=" + v2);
		int v3 = c.get(2);
		System.out.println("v3=" + v3);
		c.put(3,  3);
		System.out.println(c.valueMap);
		c.put(4,  4);
		System.out.println(c.valueMap);
		int v4 = c.get(3);
		System.out.println("v4=" + v4);
		int v5 = c.get(2);
		System.out.println("v5=" + v5);
		int v6 = c.get(1);
		System.out.println("v6=" + v6);
		int v7 = c.get(4);
		System.out.println("v7=" + v7);
		*/
	}
}
