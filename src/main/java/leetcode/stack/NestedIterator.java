package leetcode.stack;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/*
 * Given a nested list of integers, implement an iterator to flatten it. Each element is either an integer, or a list 
 * whose elements may also be integers or other lists.
 * Example 1: Given the list [[1,1],2,[1,1]],
 * By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1,1,2,1,1].
 * Example 2: Given the list [1,[4,[6]]],
 * By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1,4,6]. 
 * 
 * Company: Google, Facebook, Twitter
 * Difficulty: medium
 */
public class NestedIterator implements Iterator<Integer> {
    private Stack<NestedInteger> stack;

    public NestedIterator(List<NestedInteger> nestedList) {
        stack = new Stack<>();
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
        while (!stack.isEmpty() && !stack.peek().isInteger()) {
            List<NestedInteger> list = stack.pop().getList();
            for (int i = list.size() - 1; i >= 0; i--) {
                stack.push(list.get(i));
            }
        }
        return !stack.isEmpty();
    }

    /*
     * flatten first private List<Integer> flattenedList; private int current,
     * size; public NestedIterator(List<NestedInteger> nestedList) {
     * this.flattenedList = new LinkedList<>(); if (nestedList == null) {
     * return; } flatten(nestedList); size = flattenedList.size(); }
     * 
     * @Override public Integer next() { Integer result = null; if (hasNext()) {
     * result = flattenedList.get(current++); }
     * 
     * return result;
     * 
     * }
     * 
     * @Override public boolean hasNext() { return (current < size);
     * 
     * }
     * 
     * private void flatten(List<NestedInteger> nestedList) { for (NestedInteger
     * itr : nestedList) { if (itr.isInteger()) {
     * this.flattenedList.add(itr.getInteger()); } else {
     * flatten(itr.getList()); } } }
     */
}
