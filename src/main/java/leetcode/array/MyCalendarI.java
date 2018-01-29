package leetcode.array;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/*
 * LEETCODE 729
 * Implement a MyCalendar class to store your events. A new event 
 * can be added if adding the event will not cause a double booking.
 * Your class will have the method, book(int start, int end). 
 * Formally, this represents a booking on the half open interval 
 * [start, end), the range of real numbers x such that start <= x < end.
 * A double booking happens when two events have some non-empty 
 * intersection (ie., there is some time that is common to both events.)
 * For each call to the method MyCalendar.book, return true if the event 
 * can be added to the calendar successfully without causing a double booking. 
 * Otherwise, return false and do not add the event to the calendar. Your 
 * class will be called like this: 
 * MyCalendar cal = new MyCalendar(); 
 * MyCalendar.book(start, end)
 * Example 1:
 * MyCalendar();
 * MyCalendar.book(10, 20); // returns true
 * MyCalendar.book(15, 25); // returns false
 * MyCalendar.book(20, 30); // returns true
 * Explanation: 
 * The first event can be booked.  The second can't because time 15 is already 
 * booked by another event.The third event can be booked, as the first event 
 * takes every time less than 20, but not including 20.
 * Note
 * - The number of calls to MyCalendar.book per test case will be at most 1000.
 * - In calls to MyCalendar.book(start, end), start and end are integers in the 
 * range [0, 10^9].
 * 
 * Company: Google
 * Difficulty: medium
 * Similar Questions: 731(My Calendar II)
 */
public class MyCalendarI {
    private List<int[]> events;
    private TreeMap<Integer, Integer> calendar;
    
    
    public MyCalendarI() {
        events = new ArrayList<>();
        calendar = new TreeMap<>();
    }
  
    // two events [s1, e1) and [s2, e2) do not conflict if and only if one of them 
    // starts after the other one ends: either e1 <= s2 OR e2 <= s1. 
    // By De Morgan's laws, this means the events conflict when s1 < e2 AND s2 < e1.
    // Time complexity: O(n^2)
    public boolean book_bruteforce(int start, int end) {
        for (int i = 0; i < events.size(); i++) {
            int[] event = events.get(i);
            if (event[0] < end && start < event[1]) {
                return false;
            }
        }
        events.add(new int[] {start, end});
        return true;
    }

    // Use TreeMap, where the keys are the start of each interval, and the values are 
    // the ends of those intervals
    // Time complexity O(nlogn)
    public boolean book(int start, int end) {
        Integer prev = calendar.floorKey(start), next = calendar.ceilingKey(start);
        if ((prev == null || calendar.get(prev) <= start) && 
                (next == null || next >= end)) {
            calendar.put(start, end);
            return true;
        }
        return false;
    }
}
