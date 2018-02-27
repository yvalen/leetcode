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

    /*
     * public void addNum(int num) { if (maxHeap.isEmpty()) maxHeap.add(num);
     * else if (minHeap.isEmpty()) { if (num < maxHeap.peek()) {
     * minHeap.add(maxHeap.poll()); maxHeap.add(num); } else { minHeap.add(num);
     * } } else { int left = maxHeap.peek(), right = minHeap.peek(); boolean
     * sameSize = maxHeap.size() == minHeap.size(); if (num > right) { if
     * (sameSize) { maxHeap.add(minHeap.poll()); } minHeap.add(num); } else if
     * (num < left) { if (!sameSize) { minHeap.add(maxHeap.poll()); }
     * maxHeap.add(num); } else { if (sameSize) { maxHeap.add(num); } else {
     * minHeap.add(num); } } } }
     */
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
