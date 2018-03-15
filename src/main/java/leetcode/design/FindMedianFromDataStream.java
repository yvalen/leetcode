package leetcode.design;

import java.util.Collections;
import java.util.PriorityQueue;

/*
 * LEETCODE 295
 * Median is the middle value in an ordered integer list. If the size of the 
 * list is even, there is no middle value. So the median is the mean of the 
 * two middle value.
 * Examples:
 * [2,3,4] , the median is 3
 * [2,3], the median is (2 + 3) / 2 = 2.5
 * Design a data structure that supports the following two operations:
 * 	void addNum(int num) - Add a integer number from the data stream to the data structure.
 * 	double findMedian() - Return the median of all elements so far.
 * For example:
 * 	addNum(1)
 * 	addNum(2)
 * 	findMedian() -> 1.5
 * 	addNum(3) 
 * 	findMedian() -> 2
 * 
 * Company: Google
 * Difficulty: hard
 * Similar Questions: 480(SlidingWindowMedian)
 */
public class FindMedianFromDataStream {
    // store the larger half
    private final PriorityQueue<Integer> minHeap; 
    // store smaller half, will contain at most one more element than minHeap
    private final PriorityQueue<Integer> maxHeap; 

    public FindMedianFromDataStream() {
        minHeap = new PriorityQueue<>();
        maxHeap = new PriorityQueue<>(Collections.reverseOrder());
    }

    // Time complexity: O(logn) Space complexity: O(n)
    // In the worst case there are three heap insertions and two heap deletions from the top.
    public void addNum(int num) {
        maxHeap.add(num); // always add to maxHeap

        // new element could be greater than the top element of minHeap
        // balance minHeap first
        minHeap.add(maxHeap.poll());

        // maintain the size property
        if (maxHeap.size() < minHeap.size()) {
            maxHeap.add(minHeap.poll());
        }

    }

    // Time complexity: O(1)
    public double findMedian() {
        if (maxHeap.size() > minHeap.size())
            return Double.valueOf(maxHeap.peek());

        return (maxHeap.peek() + minHeap.peek()) * 0.5;
    }

    public static void main(String[] args) {
        FindMedianFromDataStream fm = new FindMedianFromDataStream();
        // fm.addNum(-1);
        // fm.addNum(-2);
        // fm.addNum(-3);
        fm.addNum(1);
        fm.addNum(2);
        fm.addNum(3);
        System.out.println(fm.findMedian());
    }
}
