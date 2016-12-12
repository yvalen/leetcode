package leetcode.design;
import java.util.Stack;

/**
 * Implement the following operations of a queue using stacks.
 *	push(x) -- Push element x to the back of queue.
 *	pop() -- Removes the element from in front of queue.
 *	peek() -- Get the front element.
 *	empty() -- Return whether the queue is empty.
 * Notes: You must use only standard operations of a stack -- which means only push to top, 
 * peek/pop from top, size, and is empty operations are valid.
 * Depending on your language, stack may not be supported natively. You may simulate a stack 
 * by using a list or deque (double-ended queue), as long as you use only standard operations of a stack.
 * You may assume that all operations are valid (for example, no pop or peek operations will be called on an empty queue).
 */
public class StackQueue {
	private Stack<Integer> s = new Stack<>();
	
	// Push element x to the back of queue.
    public void push(int x) {
    	s.push(x);
    }

    // Removes the element from in front of queue.
    public void pop() {
    	if (s.isEmpty()) return;
    	
    	Stack<Integer> to = new Stack<>();
    	while (!s.isEmpty()) {
    		to.push(s.pop());
    	}
    	
    	to.pop();
    	
    	while(!to.isEmpty()) {
    		s.push(to.pop());
    	}
    }

    // Get the front element.
    public int peek() {
    	Stack<Integer> to = new Stack<>();
    	while (!s.isEmpty()) {
    		to.push(s.pop());
    	}
    	
    	int val = to.peek();
    	
    	while(!to.isEmpty()) {
    		s.push(to.pop());
    	}
    	
    	return val;
        
    }

    // Return whether the queue is empty.
    public boolean empty() {
        return s.isEmpty();
    }

}
