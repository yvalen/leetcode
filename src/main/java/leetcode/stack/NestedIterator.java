package leetcode.stack;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Stack;

/*
 * LEETCODE 341
 * Given a nested list of integers, implement an iterator to flatten it. 
 * Each element is either an integer, or a list whose elements may also 
 * be integers or other lists.
 * Example 1: Given the list [[1,1],2,[1,1]],
 * By calling next repeatedly until hasNext returns false, the order of 
 * elements returned by next should be: [1,1,2,1,1].
 * Example 2: Given the list [1,[4,[6]]],
 * By calling next repeatedly until hasNext returns false, the order of 
 * elements returned by next should be: [1,4,6]. 
 * 
 * Company: Google, Facebook, Twitter
 * Difficulty: medium
 * Similar Questions: 218(ZigZagIterator), 251(Vector2D), 385(MiniParser),
 * 565(ArrayNesting), 
 */
public class NestedIterator implements Iterator<Integer> {
    private Stack<NestedInteger> stack;

    /*
    public NestedIterator(List<NestedInteger> nestedList) {
        stack = new Stack<>();
        // need to iterate through from the back
        for (int i = nestedList.size() - 1; i >= 0; i--) {
            stack.push(nestedList.get(i));
        }
    }

    @Override
    public Integer next() {
        return stack.pop().getInteger();
    }

    @Override
    public boolean hasNext() {
        // use loop here because we want to keep flattening the 
        // first element of the stack if it is a list
        while (!stack.isEmpty() && !stack.peek().isInteger()) {
            List<NestedInteger> list = stack.pop().getList();
            for (int i = list.size() - 1; i >= 0; i--) {
                stack.push(list.get(i));
            }
        }
        return !stack.isEmpty();
    }*/
    
    public NestedIterator(List<NestedInteger> nestedList) {
        stack = new Stack<>();
        // use a ListIterator to iterate from the end
        // use nestedList.size() as the starting index 
        // as we always call itr.previous
        // this guarantees O(n) iteration regardless of the list implementation
        ListIterator<NestedInteger> itr = nestedList.listIterator(nestedList.size());
        while (itr.hasPrevious()) stack.push(itr.previous());
        // need to flatten the first element here
        advanceToNextInteger();
    }

    @Override
    public Integer next() {
        Integer result = stack.pop().getInteger();
        advanceToNextInteger();
        return result;
    }

    @Override
    public boolean hasNext() {
        // hasNext should not change state
        return !stack.isEmpty();
    }
    
    private void advanceToNextInteger() {
        while (!stack.isEmpty() && !stack.peek().isInteger()) {
            List<NestedInteger> list = stack.pop().getList();
            ListIterator<NestedInteger> itr = list.listIterator(list.size());
            while (itr.hasPrevious()) stack.push(itr.previous());
        }
    }
}
