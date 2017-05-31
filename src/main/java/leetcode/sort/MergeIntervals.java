package leetcode.sort;

import java.util.ArrayList;
import java.util.List;

import leetcode.array.Interval;

/**
 * Given a collection of intervals, merge all overlapping intervals.
 * For example, given [1,3],[2,6],[8,10],[15,18], return [1,6],[8,10],[15,18]. 
 */
public class MergeIntervals {
	public static class Interval {
		public int start;
		public int end;
		public Interval() { start = 0; end = 0; }
		public Interval(int s, int e) { start = s; end = e; }
	}
	
	public List<Interval> merge(List<Interval> intervals) {
		if (intervals == null || intervals.isEmpty() || intervals.size() == 1) {
			return intervals;
		}
		
		intervals.sort((i1, i2) -> i1.start - i2.start);
		
		List<Interval> result = new ArrayList<>(intervals.size());
		int start = intervals.get(0).start, end = intervals.get(0).end;
		for (int i = 1; i < intervals.size(); i++) {
			Interval interval = intervals.get(i);
			if (interval.start <= end ) {
				end = Math.max(end,  interval.end);
			}
			else {
				result.add(new Interval(start, end));
				start = interval.start;
				end = interval.end;
			}
		}
		
		return result;
		
	}

}
