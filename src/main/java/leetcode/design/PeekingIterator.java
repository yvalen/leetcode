package leetcode.design;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/*
 * LEETCODE 284
 * Given an Iterator class interface with methods: next() and hasNext(), 
 * design and implement a PeekingIterator that support the peek() operation 
 * -- it essentially peek() at the element that will be returned by the next 
 * call to next().
 * Here is an example. Assume that the iterator is initialized to the beginning 
 * of the list: [1, 2, 3]. Call next() gets you 1, the first element in the list. 
 * Now you call peek() and it returns 2, the next element. Calling next() after 
 * that still return 2. You call next() the final time and it returns 3, the last 
 * element. Calling hasNext() after that should return false.
 * Follow up: How would you extend your design to be generic and work with all types, 
 * not just integer?
 * 
 * Company: Google, Apple, Yahoo
 * Difficulty: medium
 * Similar Questions: 
 */
public class PeekingIterator implements Iterator<Integer> {
    private Integer next;
    private Iterator<Integer> iterator;

    public PeekingIterator(Iterator<Integer> iterator) {
        // initialize any member here.
        this.iterator = iterator;
        advance();
    }

    // Returns the next element in the iteration without advancing the iterator.
    public Integer peek() {
    		/*
        if (next == null && iterator.hasNext()) {
            next = iterator.next();
        }
    		 */
        return next;
    }

    // hasNext() and next() should behave the same as in the Iterator interface.
    // Override them if needed.
    @Override
    public Integer next() {
        Integer ret = next;
        advance();
        /*
        if (next != null) {
            next = null;
        } else if (iterator.hasNext()) {
            ret = iterator.next();
        }
        */

        return ret;
    }

    @Override
    public boolean hasNext() {
    		return next != null;
        //return (iterator.hasNext() || next != null);
    }
    
    private void advance() {
        if (iterator.hasNext()) next = iterator.next();
        else next = null;
    }

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3);

        PeekingIterator itr = new PeekingIterator(list.listIterator());
        System.out.println(itr.next());
        System.out.println(itr.peek());
        System.out.println(itr.next());
        System.out.println(itr.next());
        System.out.println(itr.hasNext());
    }

}
