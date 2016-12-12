package leetcode.design;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class PeekingIterator implements Iterator<Integer> {
	private Integer next;
	private Iterator<Integer> iterator;

	public PeekingIterator(Iterator<Integer> iterator) {
	    // initialize any member here.
	    this.iterator = iterator;
	}

    // Returns the next element in the iteration without advancing the iterator.
	public Integer peek() {
		if (next == null && iterator.hasNext()) {
        	next = iterator.next();
        }
        
        return next;
	}

	// hasNext() and next() should behave the same as in the Iterator interface.
	// Override them if needed.
	@Override
	public Integer next() {
		Integer ret = next;
		if (next != null) {
			next = null;
		}
		else if (iterator.hasNext()) {
        	ret = iterator.next();
        }
		
		return ret;
	}

	@Override
	public boolean hasNext() {
	    return (iterator.hasNext() || next != null);
	}
	
	public static void main(String[] args) {
		List<Integer> list = Arrays.asList(1,2,3);
		
		PeekingIterator itr = new PeekingIterator(list.listIterator());
		System.out.println(itr.next());
		System.out.println(itr.peek());
		System.out.println(itr.next());
		System.out.println(itr.next());
		System.out.println(itr.hasNext());
	}

}
