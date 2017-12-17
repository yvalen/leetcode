package leetcode.design;

import java.util.ArrayDeque;
import java.util.Queue;

//https://leetcode.com/articles/implement-stack-using-queues/
public class QueueStack {
    private Queue<Integer> q = new ArrayDeque<>();

    // Push element x onto stack.
    public void push(int x) {
        int size = q.size();
        q.add(x);
        while (size-- > 0) {
            q.offer(q.poll());
        }

        /*
         * Queue<Integer> tmp = new ArrayDeque<>(); tmp.offer(x);
         * 
         * while (!q.isEmpty()) { tmp.offer(q.poll()); }
         * 
         * q = tmp;
         */
    }

    // Removes the element on top of the stack.
    public void pop() {
        if (q.isEmpty())
            return;

        q.poll();
    }

    // Get the top element.
    public int top() {
        return q.peek();
    }

    // Return whether the stack is empty.
    public boolean empty() {
        return q.isEmpty();
    }

    public static void main(String[] args) {
        QueueStack qs = new QueueStack();

        qs.push(1);
        qs.push(2);
        qs.push(3);
        System.out.println(qs.top());
        qs.pop();
        System.out.println(qs.top());
        qs.pop();
        System.out.println(qs.top());
        System.out.println(qs.empty());
        qs.pop();
        System.out.println(qs.empty());

    }
}
