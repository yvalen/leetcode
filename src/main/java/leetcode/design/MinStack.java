package leetcode.design;

import java.util.Stack;

/**
 * Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.
 * 	push(x) -- Push element x onto stack.
 * 	pop() -- Removes the element on top of the stack.
 * 	top() -- Get the top element.
 * 	getMin() -- Retrieve the minimum element in the stack.
 */
public class MinStack {
	private Stack<Integer> stack;
	private Stack<Integer> minStack;
	
	public MinStack() {
        stack = new Stack<>();
        minStack = new Stack<>();
    }
    
    public void push(int x) {
        stack.push(x);
        if (minStack.isEmpty() || x <= minStack.peek()) {
        	minStack.push(x);
        }
    }
    
    public void pop() {
        int val = stack.pop();
        if (minStack.peek() == val) minStack.pop();
    }
    
    public int top() {
        return stack.peek();
    }
    
    public int getMin() {
    	return minStack.peek();
    }

}
