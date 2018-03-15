package leetcode.design;

import java.util.Collections;
import java.util.PriorityQueue;

import leetcode.array.ArrayUtil;

/*
 * LEETCODE 480
 * Median is the middle value in an ordered integer list. If the size of the list is even, 
 * there is no middle value. So the median is the mean of the two middle value.
 * Examples:
 * [2,3,4] , the median is 3
 * [2,3], the median is (2 + 3) / 2 = 2.5
 * Given an array nums, there is a sliding window of size k which is moving from the very 
 * left of the array to the very right. You can only see the k numbers in the window. Each time 
 * the sliding window moves right by one position. Your job is to output the median array for 
 * each window in the original array.
 * For example, Given nums = [1,3,-1,-3,5,3,6,7], and k = 3.
 * Window position                Median
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       1
 *  1 [3  -1  -3] 5  3  6  7       -1
 *  1  3 [-1  -3  5] 3  6  7       -1
 *  1  3  -1 [-3  5  3] 6  7       3
 *  1  3  -1  -3 [5  3  6] 7       5
 *  1  3  -1  -3  5 [3  6  7]      6
 * Therefore, return the median sliding window as [1,-1,-1,3,5,6].
 * Note: You may assume k is always valid, ie: k is always smaller than input array's size for non-empty array.
 * 
 * Company: Google
 * Difficulty: hard
 * Similar Questions: 295(FindMedianFromDataStream)
 */
public class SlidingWindowMedian {
    public double[] medianSlidingWindow(int[] nums, int k) {
        double[] result = new double[nums.length-k+1];
        
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        for (int i = 0, j = 0; j < nums.length; j++) {
            maxHeap.add(nums[j]);
            minHeap.add(maxHeap.poll());
            if (maxHeap.size()<minHeap.size()) maxHeap.add(minHeap.poll());
            if (j - i == k-1) {
                // need to cast each element to double to avoid overflow
                result[i] = (maxHeap.size()>minHeap.size() ? (double)maxHeap.peek() : ((double)maxHeap.peek()+(double)minHeap.peek())/2.0);
                if (nums[i] <= result[i]) maxHeap.remove(nums[i]);
                else minHeap.remove(nums[i]);
                // need to adjust the heap size after removing the element
                if (maxHeap.size()<minHeap.size()) maxHeap.add(minHeap.poll());
                else if (minHeap.size() < maxHeap.size()-1) minHeap.add(maxHeap.poll());
                i++;
            }
        }
        return result;
    }
    
    public static void main(String[] args) {
        SlidingWindowMedian swm = new SlidingWindowMedian();
       // int[] nums = {1,3,-1,-3,5,3,6,7};
       // int k = 3;
        int[] nums= {2147483647,2147483647};
        int k =2;
        ArrayUtil.printArray(swm.medianSlidingWindow(nums, k), ", ");
    }
    

}
