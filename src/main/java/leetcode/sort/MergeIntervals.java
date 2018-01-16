package leetcode.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import leetcode.array.Interval;

/**
 * LEETCODE 56
 * Given a collection of intervals, merge all overlapping intervals. 
 * For example, given [1,3],[2,6],[8,10],[15,18], return [1,6],[8,10],[15,18].
 * 
 * Company: Google, Facebook, Microsoft, Bloomberg, LinkedIn, Twitter, Yelp
 * Difficulty: medium
 * Similar Questions: 252(Meeting Room), 253(Meeting Room II)
 */
public class MergeIntervals {
    public static class Interval {
        public int start;
        public int end;

        public Interval() {
            start = 0;
            end = 0;
        }

        public Interval(int s, int e) {
            start = s;
            end = e;
        }
    }

    // Time complexity: O(nlogn),  Space Complexity: O(1) in-place sort, O(n) merge sort
    public List<Interval> merge_sortByStart(List<Interval> intervals) {
        if (intervals == null || intervals.isEmpty() || intervals.size() == 1) {
            return intervals;
        }

        intervals.sort((i1, i2) -> i1.start - i2.start);
        List<Interval> result = new ArrayList<>(intervals.size());
        result.add(intervals.get(0));
        for (int i = 1, j = 0; i < intervals.size(); i++) {
            // i tracks the position in interval, j tracks the position in result
            if (intervals.get(i).start <= result.get(j).end) {
                result.get(j).end = Math.max(intervals.get(i).end, result.get(j).end);
            } else {
                result.add(intervals.get(i));
                j++;
            }
        }

        return result;
    }
    
    // For the result distinct Interval, the latter one’s start must > previous one’s end.
    public List<Interval> merge_sortByStartAndEnd(List<Interval> intervals) {
        if (intervals == null || intervals.isEmpty() || intervals.size() == 1) {
            return intervals;
        }
        
        int n = intervals.size();
        int[] start = new int[n];
        int[] end = new int[n];
        for (int i = 0; i < n; i++) {
            start[i] = intervals.get(i).start;
            end[i] = intervals.get(i).end;
        }
        
        Arrays.sort(start);
        Arrays.sort(end);
        List<Interval> result = new ArrayList<>(n);
        for (int i = 0, j = 0; i < n; i++) { 
            // j is the start of the interval
            if (i == n - 1 || start[i+1] > end[i]) {
                result.add(new Interval(start[j], end[i]));
                j = i + 1;
            }
        }

        return result;
    }

}
