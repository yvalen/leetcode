package leetcode.design;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.stream.Stream;

/*
 * Given two 1d vectors, implement an iterator to return their elements alternately.
 * For example, given two 1d vectors:
 * v1 = [1, 2]
 * v2 = [3, 4, 5, 6]
 * By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1, 3, 2, 4, 5, 6].
 * Follow up: What if you are given k 1d vectors? How well can your code be extended to such cases?
 * Clarification: The "Zigzag" order is not clearly defined and is ambiguous for k > 2 cases. If "Zigzag" does not look right to you, 
 * replace "Zigzag" with "Cyclic". For example, given the following input:
 * [1,2,3]
 * [4,5,6,7]
 * [8,9]
 * It should return [1,4,8,2,5,9,3,6,7]. 
 */
public class ZigZagIterator implements Iterator<Integer> {
	private final Queue<Iterator<Integer>> queue;
	
	public ZigZagIterator(List<Integer> v1, List<Integer> v2) {
		queue = new LinkedList<>();
		if (!v1.isEmpty()) queue.offer(v1.iterator()); // only add to list when the incoming list is not empty 
		if (!v2.isEmpty()) queue.offer(v2.iterator());
	}
	
	@Override
	public boolean hasNext() {
		return !queue.isEmpty();
	}

	@Override
	public Integer next() {
		Iterator<Integer> itr = queue.poll();
		Integer next = itr.next();
		if (itr.hasNext()) queue.offer(itr);
		return next;
	}
	
	/*
	private final List<Iterator<Integer>> itrs;
	private final int size;
	private int current;
	
	public ZigZagIterator(List<Integer> v1, List<Integer> v2) {
		this.itrs = new ArrayList<>(2);
		itrs.add(v1.iterator());
		itrs.add(v2.iterator());
		size = itrs.size();
	}

	@Override
	public boolean hasNext() {
		int count = 0;
		while (!itrs.get(current).hasNext() && count < size) {
			current = (current == size -1) ? 0 : current+1;
			count++;
		}
		
		return itrs.get(current).hasNext();
	}

	@Override
	public Integer next() {
		Integer next = itrs.get(current).next();
		current = (current == size -1) ? 0 : current+1;
		return next;
	}*/
	
	public static void main(String[] args) {
		List<Integer> v1 = Arrays.asList(1, 2);
		List<Integer> v2 = Arrays.asList(3, 4, 5, 6);
		ZigZagIterator zitr = new ZigZagIterator(v1, v2);
		while (zitr.hasNext()) {
			System.out.print(zitr.next() + " ");
		}
	}

	

}
