package leetcode.greedy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

import leetcode.array.Interval;

/*
 * LEETCODE 759
 * We are given a list schedule of employees, which represents the working time 
 * for each employee. Each employee has a list of non-overlapping Intervals, and 
 * these intervals are in sorted order. 
 * Return the list of finite intervals representing common, positive-length free 
 * time for all employees, also in sorted order.
 * Example 1:
 * Input: schedule = [[[1,2],[5,6]],[[1,3]],[[4,10]]]
 * Output: [[3,4]]
 * Explanation: There are a total of three employees, and all common free time 
 * intervals would be [-inf, 1], [3, 4], [10, inf]. We discard any intervals that 
 * contain inf as they aren't finite.
 * Example 2:
 * Input: schedule = [[[1,3],[6,7]],[[2,4]],[[2,5],[9,12]]]
 * Output: [[5,6],[7,9]]
 * (Even though we are representing Intervals in the form [x, y], the objects inside 
 * are Intervals, not lists or arrays. For example, schedule[0][0].start = 1, 
 * schedule[0][0].end = 2, and schedule[0][0][0] is not defined.)
 * Also, we wouldn't include intervals like [5, 5] in our answer, as they have zero length.
 * Note:
 * - schedule and schedule[i] are lists with lengths in range [1, 50].
 * - 0 <= schedule[i].start < schedule[i].end <= 10^8.
 * 
 * Company: Airbnb
 * Difficulty: hard
 * Similar Questions: 56(MergeIntervals)
 */
public class EmployeeFreeTime {
    // add all the intervals to the priority queue. (NOTE that it is not matter how many 
    // different people are there for the algorithm. because we just need to find a gap 
    // in the time line.
    // priority queue - sorted by start time
    // Every time you poll from priority queue, just make sure it doesn’t intersect with 
    // previous interval. This mean that there is no common interval. Everyone is free time.
    public List<Interval> employeeFreeTime_priorityQueue(List<List<Interval>> schedule) {
        List<Interval> result = new ArrayList<>();
        PriorityQueue<Interval> pq = new PriorityQueue<>((a, b) -> a.start - b.start);
        for (List<Interval> intervals : schedule) {
            pq.addAll(intervals);
        }
        
        Interval prev = pq.poll();
        while (!pq.isEmpty()) {
            if (prev.end < pq.peek().start) {
                // no intersect
                result.add(new Interval(prev.end, pq.peek().start));
                prev = pq.poll();
            }
            else {
                // intersect or sub merged
                prev.end = Math.max(prev.end, pq.peek().end);
                pq.poll();
            }
        }
        return result;
    }
    
    // Time complexity: O(nlogn)
    public List<Interval> employeeFreeTime_sortByStartEnd(List<List<Interval>> schedule) {
        List<Integer> start = new ArrayList<>();
        List<Integer> end = new ArrayList<>();
        for (List<Interval> intervals : schedule) {
            for (Interval interval : intervals) {
                start.add(interval.start);
                end.add(interval.end);
            }
        }
        Collections.sort(start);
        Collections.sort(end);
        
        List<Interval> result = new ArrayList<>();
        int busy = 0;
        for (int i = 0, j = 0; i < start.size() && j < end.size(); ) {
            if (start.get(i) <= end.get(j)) {
                busy++;
                i++;
            }
            else {
                busy--;
                if (busy == 0) {
                    result.add(new Interval(end.get(j), start.get(i)));
                }
                j++;
            }
        }
        
        return result;
    }
}
