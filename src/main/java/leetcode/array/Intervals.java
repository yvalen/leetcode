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

public class Intervals {
	/**
	 * Given a set of non-overlapping intervals, insert a new interval into the intervals (merge if necessary).
	 * You may assume that the intervals were initially sorted according to their start times.
	 * Example 1: Given intervals [1,3],[6,9], insert and merge [2,5] in as [1,5],[6,9].
	 * Example 2: Given [1,2],[3,5],[6,7],[8,10],[12,16], insert and merge [4,9] in as [1,2],[3,10],[12,16].
	 * This is because the new interval [4,9] overlaps with [3,5],[6,7],[8,10]. 
	 */
	public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
		if (intervals == null) {
			return Collections.singletonList(newInterval);
		}
		
		int n = intervals.size();
		List<Interval> result = new ArrayList<>(n);
		int i = 0;
		
		// add all intervals ends before the enwinterval.start
		while (i < n && intervals.get(i).end < newInterval.start) {
			result.add(intervals.get(i));
			i++;
		}
		
		// add all intervals overlaps with newInterval
		while (i < n && intervals.get(i).start <= newInterval.end) {
			newInterval.start = Math.min(newInterval.start, intervals.get(i).start);
			newInterval.end = Math.max(newInterval.end, intervals.get(i).end);
			i++;
		}
		result.add(newInterval);		
		
		// add the rest
		while (i < n) {
			result.add(intervals.get(i));
			i++;
		}
		
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

	
	/**
	 * Given a collection of intervals, merge all overlapping intervals.
	 * For example, given [1,3],[2,6],[8,10],[15,18], return [1,6],[8,10],[15,18]. 
	 */
	public List<Interval> merge(List<Interval> intervals) {
		if (intervals == null || intervals.isEmpty() || intervals.size() == 1) {
			return intervals;
		}
		
		Collections.sort(intervals, (Interval i1, Interval i2) -> i1.start - i2.start);
		
		List<Interval> result = new ArrayList<>(intervals.size());
		result.add(intervals.get(0));
		for (int i = 1, j = 0; i < intervals.size(); i++) {
			if (intervals.get(i).start > result.get(j).end) {
				result.add(intervals.get(i));
				j++;
			}
			else {
				result.get(j).end = Math.max(result.get(j).end, intervals.get(i).end);
			}
		}
		
		/*
		Interval merged = intervals.get(0);
		for (int i = 1; i < intervals.size(); i++) {
			if (intervals.get(i).start > merged.end) {
				// disjoint element, add merged to result set and reset merged
				result.add(new Interval(merged.start, merged.end));
				merged = intervals.get(i);
			}
			else {
				// update end of merged
				merged.end = Math.max(merged.end, intervals.get(i).end);
			}
		}
		result.add(merged);
		*/
		
		return result;
    }

	
	
	public static void main(String[] args) {
		Intervals test = new Intervals();
		
		List<Interval> intervals = Stream.of(
				new Interval(1, 3),
				new Interval(2, 6),
				new Interval(8, 10), 
				new Interval(15, 18))
				.collect(Collectors.toList());
		
		Interval newInterval = new Interval(0, 10);
		//List<Interval> result = test.insert(intervals, newInterval);
		//List<Interval> result = test.insert_inPlace(intervals, newInterval);
		
		List<Interval> result = test.merge(intervals);
		result.stream().forEach(System.out::print);
	}
}
