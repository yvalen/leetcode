package leetcode.design;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/*
 * LEETCODE 251
 * Implement an iterator to flatten a 2d vector.
 * For example, Given 2d vector =
 * [
 * 	[1,2],
 * 	[3],
 * 	[4,5,6]
 * ]
 * By calling next repeatedly until hasNext returns false, the order of elements 
 * returned by next should be: [1,2,3,4,5,6].
 * Follow up: As an added challenge, try to code it using only iterators in C++ 
 * or iterators in Java. 
 * 
 * Company: Google, Twitter, Airbnb, Zenefits
 * Difficulty: medium
 * Similar Questions: 173(BSTIterator), 218(ZigZagIterator)
 */
public class Vector2D implements Iterator<Integer> {
	private Iterator<List<Integer>> listItr;
	private Iterator<Integer> itr;

	public Vector2D(List<List<Integer>> vec2d) {
		listItr = vec2d.iterator();
		advance();
	}

	@Override
	public boolean hasNext() {
		return (itr != null && itr.hasNext());	
		/*
		// need to use loop here to handle empty list element
		while ((itr == null || !itr.hasNext()) && listItr.hasNext()) {
			itr = listItr.next().iterator();
		}
		return itr == null ? false : itr.hasNext(); // need to do null check for j
		// for [] as input
		 * */
		 
	}

	@Override
	public Integer next() {
		Integer result = itr.next();
		advance();
		return result;
		/*
        // if (!hasNext()) throw new NoSuchElementException();
        hasNext(); // need to call hasNext() to initialize/update j
        return itr.next();
		 */
	}
	
	@Override
	public void remove() {
	    itr.remove();
	}

	private void advance() {
		// need to use loop here to handle empty list element
		while ((itr == null || !itr.hasNext()) && listItr.hasNext()) {
			itr = listItr.next().iterator();
		}
	}

	public static void main(String[] args) {
		// List<List<Integer>> vec2d = Arrays.asList(Arrays.asList(1, 2),
		// Arrays.asList(3), Arrays.asList(4, 5, 6));
		List<List<Integer>> vec2d = Arrays.asList(Collections.emptyList(), Collections.emptyList(), Arrays.asList(-1));
		Vector2D v2d = new Vector2D(vec2d);
		while (v2d.hasNext()) {
			System.out.print(v2d.next() + " ");
		}
	}

}
