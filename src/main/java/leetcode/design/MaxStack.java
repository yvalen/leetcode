package leetcode.design;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.TreeMap;

import javafx.scene.control.CheckBoxTreeItem.TreeModificationEvent;

/*
 * LEETCODE 716
 * Design a max stack that supports push, pop, top, peekMax and popMax.
 * push(x) -- Push element x onto stack.
 * pop() -- Remove the element on top of the stack and return it.
 * top() -- Get the element on the top.
 * peekMax() -- Retrieve the maximum element in the stack.
 * popMax() -- Retrieve the maximum element in the stack, and remove it. 
 * If you find more than one maximum elements, only remove the top-most one.
 * Example 1:
 * MaxStack stack = new MaxStack();
 * stack.push(5); 
 * stack.push(1);
 * stack.push(5);
 * stack.top(); -> 5
 * stack.popMax(); -> 5
 * stack.top(); -> 1
 * stack.peekMax(); -> 5
 * stack.pop(); -> 1
 * stack.top(); -> 5
 * Note:
 * - -1e7 <= x <= 1e7
 * - Number of operations won't exceed 10000.
 * - The last four operations won't be called when stack is empty.
 * 
 * Company: LinkedIn
 * Difficulty: hard
 * Similar Questions: 155(MinStack)
 */
public class MaxStack {
    /*
    private Stack<Integer> stack;
    private Stack<Integer> maxStack;
   
    public MaxStack() {
        stack = new Stack<>();
        maxStack = new Stack<>();
    }

    public void push(int x) {
        stack.push(x);
        // need to push element equals to current max to maxStack
        if (maxStack.isEmpty() || x >= maxStack.peek())
            maxStack.push(x);
    }

    public int pop() {
        int x = stack.pop();
        if (maxStack.peek() == x)
            maxStack.pop();
        return x;
    }

    public int top() {
        return stack.peek();
    }

    public int peekMax() {
        return maxStack.peek();
    }

    public int popMax() {
        int result = maxStack.pop();
        Stack<Integer> tmp = new Stack<>();
        while (stack.peek() != result) {
            tmp.push(stack.pop());
        }

        stack.pop();
        while (!tmp.isEmpty()) {
            // use MaxStack.push instead of stack.push, 
            // this will populate maxStack properly
            push(tmp.pop()); 
        }

        return result;
    }
    */
    
    private static class Node {
        private int val;
        private Node prev;
        private Node next;
        Node(int val) {
            this.val = val;
        }
    }
    
    private Node head;
    private Node tail;
    private TreeMap<Integer, List<Node>> map;
    
    public MaxStack() {
        head = new Node(0);
        tail = head;
        map = new TreeMap<>();
    }
    
    public void push(int x) {
        map.putIfAbsent(x, new ArrayList<>());
        Node node = new Node(x);
        node.prev = tail;
        tail.next = node;
        tail = node;
        map.get(x).add(tail);
    }
    
    public int pop() {
        int key = tail.val;
        map.get(key).remove(tail);
        if (map.get(key).isEmpty()) {
            map.remove(key);
        }
        removeNode(tail);
        return key;
    }
    
    public int top() {
        return tail.val;
    }

    public int peekMax() {
        return map.lastKey();
    }

    public int popMax() {
        Map.Entry<Integer, List<Node>> entry = map.lastEntry();
        int key = entry.getKey();
        List<Node> nodes = entry.getValue();
        Node nodeToRemove = nodes.get(nodes.size()-1);
        nodes.remove(nodes.size()-1);
        if (nodes.isEmpty()) {
            map.remove(key);
        }
        removeNode(nodeToRemove);
        return key;
    }
    
    private void removeNode(Node node) {
        node.prev.next = node.next;
        if (node.next != null) {
            node.next.prev = node.prev;
        }
        else {
            tail = node.prev;
        }
        node.next = null;
        node.prev = null;
    }
    
    public static void main(String[] args) {
        MaxStack maxStack = new MaxStack();
        maxStack.push(5);
        maxStack.push(1);
        maxStack.push(5);
        /*
         * maxStack.push(2); maxStack.push(2); maxStack.push(5);
         * maxStack.push(4); maxStack.push(3);
         */
        System.out.println(maxStack.popMax());
        System.out.println(maxStack.top());
    }

}
