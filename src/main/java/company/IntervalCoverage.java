package company;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


import leetcode.array.Interval;

public class IntervalCoverage {
    private List<Interval> intervals;
    private int totalLen;
    
    public IntervalCoverage() {
        intervals = new ArrayList<>();
    }
    
    public void addInterval(int s, int e) {
        List<Interval> result = new ArrayList<>();
        Interval newInterval = new Interval(s, e);
       
        int i = 0, n = intervals.size(); 
        totalLen = 0;
        
        while (i < n && intervals.get(i).end < newInterval.start) {
            result.add(intervals.get(i));
            totalLen += intervals.get(i).end - intervals.get(i).start;
            i++;
        }
            
        while (i < n && intervals.get(i).start < newInterval.end) {
            newInterval.start = Math.min(intervals.get(i).start, newInterval.start);
            newInterval.end = Math.max(intervals.get(i).end, newInterval.end);
            i++;
        }
        result.add(newInterval);
        totalLen += newInterval.end - newInterval.start;

        while (i < n) {
            result.add(intervals.get(i));
            totalLen += intervals.get(i).end - intervals.get(i).start;
            i++;
        }
       
        intervals = result;
        
        //intervals.add(new Interval(s, e));
    }

    public int getTotalCoveredLength() {
        /*
        Collections.sort(intervals, (a,b)->a.start-b.start);
        
        Interval prev = intervals.get(0);
        int totalLen = prev.end-prev.start;
        for (int i = 1; i < intervals.size(); i++) {
            Interval interval = intervals.get(i);
            if (interval.start > prev.end) {
                totalLen += interval.end-interval.start;
                prev = interval;
            }
            else if (interval.end > prev.end) {
                totalLen += interval.end - prev.end;
                prev = interval;
            }
        }
        */
        return totalLen;
    }
    
    public static void main(String[] args) {
        IntervalCoverage ic = new IntervalCoverage();
        ic.addInterval(3, 6);
        ic.addInterval(8, 9);
        ic.addInterval(1, 5);
        System.out.println(ic.getTotalCoveredLength());
     }
}
