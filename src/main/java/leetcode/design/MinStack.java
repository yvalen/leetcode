package leetcode.design;

import java.util.Stack;

/*
 * LEETCODE 155 
 * Design a stack that supports push, pop, top, and retrieving the minimum element 
 * in constant time. 
 * push(x) -- Push element x onto stack. 
 * pop() -- Removes the element on top of the stack. 
 * top() -- Get the top element.
 * getMin() -- Retrieve the minimum element in the stack. 
 * Example: 
 * MinStack minStack = new MinStack(); 
 * minStack.push(-2); 
 * minStack.push(0);
 * minStack.push(-3); 
 * minStack.getMin(); --> Returns -3. 
 * minStack.pop();
 * minStack.top(); --> Returns 0. 
 * minStack.getMin(); --> Returns -2.
 * 
 * Company: Google, Amazon, Uber, Bloomberg, Snapchat, Zenefits 
 * Difficulty: easy
 * Similar Questions: 239(SlidingWindowMaximum), 716(MaxStack)
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
        // need to check if minStack is empty for the first element
        // also should check for equal here to handle duplicate elements
        if (minStack.isEmpty() || x <= minStack.peek()) {
            minStack.push(x);
        }
    }

    public void pop() {
        int val = stack.pop();
        if (minStack.peek() == val)
            minStack.pop();
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return minStack.peek();
    }

}
