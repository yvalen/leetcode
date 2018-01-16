package leetcode.sort;

import java.util.Arrays;
import java.util.PriorityQueue;

import jdk.internal.org.objectweb.asm.tree.IntInsnNode;
import leetcode.array.Interval;

public class MeetingRoom {
    /**
     * LEETCODE 252 
     * Given an array of meeting time intervals consisting of start and end times 
     * [[s1,e1],[s2,e2],...] (si < ei), determine if a person could attend all meetings. 
     * For example, given [[0, 30],[5, 10],[15, 20]], return false.
     * 
     * Company: Facebook 
     * Difficulty: easy 
     * Similar Questions: 253(Meeting Room II), 56(Merge Interval)
     */
    public boolean canAttendMeetings(Interval[] intervals) {
        Arrays.sort(intervals, (i1, i2) -> i1.start - i2.start);

        for (int i = 1; i < intervals.length; i++) {
            // don't check for equal here, as a meeting can start at the end time of last meeting
            if (intervals[i].start < intervals[i - 1].end) { 
                return false;
            }
        }

        return true;
    }

    /**
     * LEETCODE 253 
     * Given an array of meeting time intervals consisting of start and end times 
     * [[s1,e1],[s2,e2],...] (si < ei), find the minimum number of conference rooms required. 
     * For example, given [[0, 30],[5, 10],[15, 20]], return 2.
     * 
     * Company: Google, Faceboo, Snapchat, Uber
     * Difficulty: medium 
     * Similar Questions: 252(Meeting Room), 56(Merge Interval), 452
     */
    public int minMeetingRooms(Interval[] intervals) {
        if (intervals == null || intervals.length == 0)
            return 0;

        int n = intervals.length;
        int[] start = new int[n];
        int[] end = new int[n];
        for (int i = 0; i < n; i++) {
            start[i] = intervals[i].start;
            end[i] = intervals[i].end;
        }

        Arrays.sort(start);
        Arrays.sort(end);

        // whenever there is a start meeting, we need to add one room. 
        // But before adding rooms, we check to see if any previous meeting 
        // ends, which is why we check start with the first end. When the start 
        // is bigger than end, it means at this time one of the previous meeting 
        // ends, and it can take and reuse that room. Then the next meeting need to 
        // compare with the second end because the first end's room is already taken.
        // One thing is also good to know: meetings start is always smaller than end.
        // Whenever we pass a end, one room is released.
        int count = 1;
        for (int i = 1, j = 0; i < n; i++) {
            if (start[i] < end[j])
                count++;
            else
                j++;
        }

        return count;
    }

    public int minMeetingRooms_withHeap(Interval[] intervals) {
        if (intervals == null || intervals.length == 0)
            return 0;

        // sort intervals by start time
        Arrays.sort(intervals, (i1, i2) -> (i1.start - i2.start));

        // use min heap to track the end time of merged interval
        PriorityQueue<Interval> heap = new PriorityQueue<>((i1, i2) -> (i1.end - i2.end));

        heap.offer(intervals[0]);
        for (int i = 1; i < intervals.length; i++) {
            // cannot use peek here because the end time of the top element
            // may be changed, thus it may change its position in the heap
            Interval interval = heap.poll(); 
            if (intervals[i].start < interval.end) {
                // add to heap if there is an overlap
                heap.offer(intervals[i]);
            } else {
                // merge the interval if there is no overlap
                interval.end = intervals[i].end;
            }

            heap.offer(interval); // need to put interval back to the heap
        }

        return heap.size();
    }

    
    public Interval intervalWithMostMeetings(Interval[] intervals) {
        int n = intervals.length;
        int[] start = new int[n];
        int[] end = new int[n];
        for (int i = 0; i < n; i++) {
            start[i] = intervals[i].start;
            end[i] = intervals[i].end;            
        }
        Arrays.sort(start);
        Arrays.sort(end);
        
        int maxCount = 1, count = 1;
        Interval interval = new Interval(start[0], end[0]);
        for (int i = 1, j = 0; i < n;) {
            if (start[i] < end[j]) {
                count++;  // increment room count for each start
                if (maxCount < count) {
                    interval.start = start[i];
                    interval.end = end[j];
                }
                i++;
            }
            else {
                count--; // decrement the room count when meeting ends
                j++;
            }
        }
        
        
        return interval;
    }
    
    
    public static void main(String[] args) {
        MeetingRoom m = new MeetingRoom();

        // Interval[] intervals = {new Interval(0, 30), new Interval(5, 10), new
        // Interval(15, 20)};
        // boolean canAttend = m.canAttendMeetings(intervals);
        // System.out.println(canAttend);

        // Interval[] intervals = {new Interval(9, 10), new Interval(4, 9), new
        // Interval(4, 17)};

        //Interval[] intervals = { new Interval(2, 15), new Interval(36, 45), new Interval(9, 29), new Interval(16, 23),
        //        new Interval(4, 9) };
        Interval[] intervals = { new Interval(1, 10), new Interval(2, 3), new Interval(5, 8), new Interval(4, 7)};
        //System.out.println(m.minMeetingRooms(intervals));
        System.out.println(m.intervalWithMostMeetings(intervals));
    }
}
