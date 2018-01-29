package leetcode.greedy;

import java.util.Arrays;

import leetcode.array.Interval;

/*
 * LEETCODE 435
 * Given a collection of intervals, find the minimum number of intervals you need 
 * to remove to make the rest of the intervals non-overlapping.
 * Note:
 * - You may assume the interval's end point is always bigger than its start point.
 * - Intervals like [1,2] and [2,3] have borders "touching" but they don't overlap 
 * each other.
 * Example 1:
 * Input: [ [1,2], [2,3], [3,4], [1,3] ]
 * Output: 1
 * Explanation: [1,3] can be removed and the rest of intervals are non-overlapping.
 * Example 2:
 * Input: [ [1,2], [1,2], [1,2] ]
 * Output: 2
 * Explanation: You need to remove two [1,2] to make the rest of intervals non-overlapping.
 * Example 3:
 * Input: [ [1,2], [2,3] ]
 * Output: 0
 * Explanation: You don't need to remove any of the intervals since they're already 
 * non-overlapping.
 * 
 * Difficulty: medium
 * Similar Questions: 
 */
public class NonOverlappingIntervals {
    public static int eraseOverlapIntervals(Interval[] intervals) {
        if (intervals == null || intervals.length == 0) return 0;
        
        int n = intervals.length;
        int[] start = new int[n];
        int[] end = new int[n];
        for (int i = 0; i < n; i++) {
            start[i] = intervals[i].start;
            end[i] = intervals[i].end;
        }
        
        Arrays.sort(start);
        Arrays.sort(end);
        
        int count = 0;
        for (int i = 1, j = 0; i < n; i++) {
            if (start[i] < end[j]) {
                count++;
            }
            else {
                j++;
            }
            
        }
        return count;
    }
    
    // the problem is the same as “Given a collection of intervals, 
    // find the maximum number of intervals that are non-overlapping.
    // Sorting Interval.end in ascending order is O(nlogn), then traverse 
    // intervals array to get the maximum number of non-overlapping 
    // intervals is O(n). Total is O(nlogn).
    // https://en.wikipedia.org/wiki/Interval_scheduling#Interval_Scheduling_Maximization
    public static int eraseOverlapIntervals_sortByEnd(Interval[] intervals) {
        if (intervals == null || intervals.length == 0) return 0;
        
        Arrays.sort(intervals, (a, b) -> a.end-b.end);
        
        int end = intervals[0].end;
        int count = 1; // number of non-overlapping intervals
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i].start >= end) {
                count++;
                end = intervals[i].end;
            }
        }
        
        return intervals.length - count;
    }
    
    public static int eraseOverlapIntervals_sortByStart(Interval[] intervals) {
        if (intervals == null || intervals.length == 0) return 0;
        
        int n = intervals.length, count = 0;
        Arrays.sort(intervals, (a, b) -> a.start-b.start);
        
        int prev = 0; // index of the previous interval
        for (int i = 1; i < n; i++) {
            if (intervals[i].start < intervals[prev].end) {
                if (intervals[i].end < intervals[prev].end) {
                    prev = i;
                }
                count++;
            }
            else {
                prev = i;
            }
        }
        
        return count;
    }
    
    public static void main(String[] args) {
        Interval[] intervals = {new Interval(0, 2), new Interval(1, 3), new Interval(2, 4), new Interval(3, 5), new Interval(4, 6)};
        System.out.println(eraseOverlapIntervals_sortByStart(intervals));
    }
}
