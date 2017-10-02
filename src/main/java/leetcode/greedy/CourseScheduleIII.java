package leetcode.greedy;

import java.util.Arrays;
import java.util.PriorityQueue;

/*
 * LEETCODE 630
 * There are n different online courses numbered from 1 to n. Each course has some duration(course length) t and closed on dth day. 
 * A course should be taken continuously for t days and must be finished before or on the dth day. You will start at the 1st day.
 * Given n online courses represented by pairs (t,d), your task is to find the maximal number of courses that can be taken.
 * Example: Input: [[100, 200], [200, 1300], [1000, 1250], [2000, 3200]] Output: 3
 * Explanation: There're totally 4 courses, but you can take 3 courses at most:
 * - First, take the 1st course, it costs 100 days so you will finish it on the 100th day, and ready to take the next course on the 101st day.
 * - Second, take the 3rd course, it costs 1000 days so you will finish it on the 1100th day, and ready to take the next course on the 1101st day. 
 * - Third, take the 2nd course, it costs 200 days so you will finish it on the 1300th day. 
 * - The 4th course cannot be taken now, since you will finish it on the 3300th day, which exceeds the closed date.
 * Note:
 * - The integer 1 <= d, t, n <= 10,000.
 * - You can't take two courses simultaneously.
 * 
 * Company: WAP
 * Difficulty: hard
 */
public class CourseScheduleIII {
	// 1. Sort all the courses by their ending time (in ascending order)
	// 2. Use a maxHeap to store the duration of courses that can be taken so far, and use time to store the current end time
	// 3. Iterate over the sorted course
	// 3.1 If the current course can be taken (time + duration) < end_time, add it to maxHeap and update time with its duration
	// 3.2 If the current course cannot be taken, we need to find the course taken so far that has the maximum duration. If that
	// course duration is greater than current course duration, we can replace it with current course by remove it from maxHeap
	// and add the current course to maxHeap. time also needs to be updated with the new duration. 
	// 4. At the end the number of elements in the maxHeap is the number of courses that can be taken
	public int scheduleCourse(int[][] courses) {
        Arrays.sort(courses, (a, b) -> a[1]-b[1]);
		
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b -a); // maxHeap, stores the duration of all course that can be taken so far
        int time = 0; // current end time
        for (int[] course : courses) {
        	if (time + course[0] <= course[1]) { // course can be taken
        		maxHeap.offer(course[0]);
        		time += course[0];
        	}
        	else if (!maxHeap.isEmpty() && maxHeap.peek() > course[0]) { // course cannot be taken
        		time += course[0] - maxHeap.poll();
        		maxHeap.offer(course[0]);
        	}
        }
        
		return maxHeap.size();
    }

}
