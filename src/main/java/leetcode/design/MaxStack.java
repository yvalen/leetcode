package leetcode.design;

import java.util.Stack;

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
 * Difficulty: easy
 * Similar Questions: 155(MinStack)
 */
public class MaxStack {
    private Stack<Integer> stack;
    private Stack<Integer> maxStack;
    private Stack<Integer> tmp;

    public MaxStack() {
        stack = new Stack<>();
        maxStack = new Stack<>();
        tmp = new Stack<>();
    }

    public void push(int x) {
        stack.push(x);
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

    public static void main(String[] args) {
        MaxStack maxStack = new MaxStack();
        maxStack.push(5);
        maxStack.push(1);
        /*
         * maxStack.push(2); maxStack.push(2); maxStack.push(5);
         * maxStack.push(4); maxStack.push(3);
         */
        System.out.println(maxStack.popMax());
        System.out.println(maxStack.peekMax());
    }

}
