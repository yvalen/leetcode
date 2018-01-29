package leetcode.array;

import java.util.ArrayList;
import java.util.List;

/*
 * LEETCODE 731
 * Implement a MyCalendarTwo class to store your events. A new event 
 * can be added if adding the event will not cause a triple booking.
 * Your class will have one method, book(int start, int end). Formally, 
 * this represents a booking on the half open interval [start, end), 
 * the range of real numbers x such that start <= x < end. A triple booking 
 * happens when three events have some non-empty intersection (ie., there 
 * is some time that is common to all 3 events.)
 * For each call to the method MyCalendar.book, return true if the event can 
 * be added to the calendar successfully without causing a triple booking. 
 * Otherwise, return false and do not add the event to the calendar.
 * Your class will be called like this: 
 * MyCalendar cal = new MyCalendar(); 
 * MyCalendar.book(start, end)
 * Example 1:
 * MyCalendar();
 * MyCalendar.book(10, 20); // returns true
 * MyCalendar.book(50, 60); // returns true
 * MyCalendar.book(10, 40); // returns true
 * MyCalendar.book(5, 15); // returns false
 * MyCalendar.book(5, 10); // returns true
 * MyCalendar.book(25, 55); // returns true
 * Explanation: 
 * The first two events can be booked.  The third event can be double booked.
 * The fourth event (5, 15) can't be booked, because it would result in a triple booking.
 * The fifth event (5, 10) can be booked, as it does not use time 10 which is already double booked.
 * The sixth event (25, 55) can be booked, as the time in [25, 40) will be double booked with 
 * the third event; the time [40, 50) will be single booked, and the time [50, 55) will be double 
 * booked with the second event.
 * Note:
 * - The number of calls to MyCalendar.book per test case will be at most 1000.
 * - In calls to MyCalendar.book(start, end), start and end are integers in the range [0, 10^9].
 * 
 * Company: Google
 * Difficulty: medium
 * Similar Questions: 729(My Calendar I)
 */
public class MyCalendarII {

    private List<int[]> bookings;
    private List<int[]> doubleBookings;
    public MyCalendarII() {
        bookings = new ArrayList<>();
        doubleBookings = new ArrayList<>();
    }
    
    // Maintain a list of bookings and a list of double bookings. When booking a 
    // new event [start, end), if it conflicts with a double booking, it will have 
    // a triple booking and be invalid. Otherwise, parts that overlap the calendar 
    // will be a double booking.
    // Time complexity: O(n^2)
    public boolean book_bruteforce(int start, int end) {
        for (int i = 0; i < doubleBookings.size(); i++) {
            int[] event = doubleBookings.get(i);
            if (event[0] < end && start < event[1]) {
                return false;
            }
        }
        
        for (int i = 0; i < bookings.size(); i++) {
            int[] event = bookings.get(i);
            if (event[0] < end && start < event[1]) {
                // use max for start and min for end to figure the range for double booking
                doubleBookings.add(new int[] {Math.max(start, event[0]), Math.min(end, event[1])});
            }
        }
        bookings.add(new int[] {start, end});
        
        return true;
    }
}
