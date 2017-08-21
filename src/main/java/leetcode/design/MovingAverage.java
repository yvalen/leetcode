package leetcode.design;

import java.util.ArrayDeque;
import java.util.Deque;

/*
 * Given a stream of integers and a window size, calculate the moving average of all integers in the sliding window.
 * For example,
 * MovingAverage m = new MovingAverage(3);
 * m.next(1) = 1
 * m.next(10) = (1 + 10) / 2
 * m.next(3) = (1 + 10 + 3) / 3
 * m.next(5) = (10 + 3 + 5) / 3
 * 
 * Company: Google
 * Difficulty: easy
 */
public class MovingAverage {
	private final int size;
	private final Deque<Integer> deque;
	private double sum;
	
	private final int[] window;
	private int count;
	private int index;

	public MovingAverage(int size) {
        this.size = size;
        this.deque = new ArrayDeque<>(size);
        
        this.window = new int[size];
    }
    
    public double next(int val) {
    	if (count < size) count++;
    	sum -= window[index];
    	sum += val;
    	window[index] = val;
    	index = (index + 1) % size;
    	return sum / count;
    	
    	/*
        if (deque.size() == this.size) {
        	sum -= deque.removeFirst();
        }
        deque.addLast(val);
        sum += val;
        return sum / deque.size();
        */
    }
}
