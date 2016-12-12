package leetcode.sort;

import java.util.Arrays;

import leetcode.array.Interval;

public class MeetingRoom {
	
	
	/**
	 * Given an array of meeting time intervals consisting of start and end times 
	 * [[s1,e1],[s2,e2],...] (si < ei), determine if a person could attend all meetings.
	 * For example, given [[0, 30],[5, 10],[15, 20]], return false.
	 */
	public boolean canAttendMeetings(Interval[] intervals) {
        Arrays.sort(intervals, (Interval i1, Interval i2) -> i1.start - i2.start);
		
        for (int i = 1; i < intervals.length; i++) {
        	if (intervals[i].start < intervals[i-1].end) {
        		return false;
        	}
        }
        
		return true;
    }
	
	/**
	 * Given an array of meeting time intervals consisting of start and end times 
	 * [[s1,e1],[s2,e2],...] (si < ei), find the minimum number of conference rooms required.
	 * For example, given [[0, 30],[5, 10],[15, 20]], return 2.
	 */
	public int minMeetingRooms(Interval[] intervals) {
		Arrays.sort(intervals, (Interval i1, Interval i2) -> i1.start - i2.start);
		
		int count = 1;
		for (int i = 1; i < intervals.length; i++) {
        	if (intervals[i].start < intervals[i-1].end) {
        		count++;
        	}
        }
		
		return count;
    }

	
	public static void main(String[] args) {
		MeetingRoom m = new MeetingRoom();
		
		Interval[] intervals = {new Interval(0, 30), new Interval(5, 10), new Interval(15, 20)};
		
		boolean canAttend = m.canAttendMeetings(intervals);
		System.out.println(canAttend);
		
		
	}
}
