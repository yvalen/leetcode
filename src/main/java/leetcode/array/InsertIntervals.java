package leetcode.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * LEETCODE 57 
 * Given a set of non-overlapping intervals, insert a new interval into the 
 * intervals (merge if necessary). You may assume that the intervals were 
 * initially sorted according to their start times. 
 * Example
 * 1: Given intervals [1,3],[6,9], insert and merge [2,5] in as [1,5],[6,9].
 * Example 2: Given [1,2],[3,5],[6,7],[8,10],[12,16], insert and merge [4,9]
 * in as [1,2],[3,10],[12,16]. This is because the new interval [4,9]
 * overlaps with [3,5],[6,7],[8,10].
 * 
 * Company: Google, Facebook, LinkedIn 
 * Difficulty: hard
 * Similar Questions: 56(Merge Interval),
 */
public class InsertIntervals {
    
    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        if (intervals == null) {
            return Collections.singletonList(newInterval);
        }

        int n = intervals.size();
        List<Interval> result = new ArrayList<>(n);
        int i = 0;

        // add all intervals ends before the newinterval.start
        while (i < n && intervals.get(i).end < newInterval.start) {
            result.add(intervals.get(i));
            i++;
        }

        // add all intervals overlaps with newInterval, an overlap is
        // interval.end >= newInterval.start && interval.start <= newInterval.end
        // we should update newInterval with the current min and max
        while (i < n && intervals.get(i).start <= newInterval.end) {
            newInterval.start = Math.min(newInterval.start, intervals.get(i).start);
            newInterval.end = Math.max(newInterval.end, intervals.get(i).end);
            i++;
        }
        result.add(newInterval); // need to add merged interval to result

        // add the rest
        if (i < n)
            result.addAll(intervals.subList(i, n));

        return result;
    }

    public List<Interval> insert_inPlace(List<Interval> intervals, Interval newInterval) {
        if (intervals == null) {
            return Collections.singletonList(newInterval);
        }

        int i = 0;

        // skip intervals ends before newInterval.start
        while (i < intervals.size() && intervals.get(i).end < newInterval.start) {
            i++;
        }

        // remove overlap intervals and update newInterval start/end
        while (i < intervals.size() && intervals.get(i).start <= newInterval.end) {
            newInterval.start = Math.min(newInterval.start, intervals.get(i).start);
            newInterval.end = Math.max(newInterval.end, intervals.get(i).end);
            intervals.remove(i);
        }

        intervals.add(i, newInterval);

        return intervals;
    }

  

    public static void main(String[] args) {
        InsertIntervals test = new InsertIntervals();
        /*
         * List<Interval> intervals = Stream.of( new Interval(1, 3), new
         * Interval(2, 6), new Interval(8, 10), new Interval(15, 18))
         * .collect(Collectors.toList());
         */
        List<Interval> intervals = Stream.of(new Interval(1, 2), new Interval(3, 5), new Interval(6, 7),
                new Interval(8, 10), new Interval(12, 16)).collect(Collectors.toList());

        // Interval newInterval = new Interval(0, 10);
        // List<Interval> result = test.insert(intervals, newInterval);
        // List<Interval> result = test.insert_inPlace(intervals, newInterval);

        // List<Interval> result = test.merge(intervals);
        // result.stream().forEach(System.out::print);

        Interval newInterval = new Interval(4, 9);
        System.out.println(test.insert(intervals, newInterval));
    }
}
