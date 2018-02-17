package leetcode.greedy;

import java.util.Arrays;

import com.sun.swing.internal.plaf.basic.resources.basic;

/*
 * LEETCODE 452
 * There are a number of spherical balloons spread in two-dimensional space. 
 * For each balloon, provided input is the start and end coordinates of the 
 * horizontal diameter. Since it's horizontal, y-coordinates don't matter and 
 * hence the x-coordinates of start and end of the diameter suffice. Start is 
 * always smaller than end. There will be at most 10^4 balloons.
 * An arrow can be shot up exactly vertically from different points along the 
 * x-axis. A balloon with xstart and xend bursts by an arrow shot at x if 
 * xstart ≤ x ≤ xend. There is no limit to the number of arrows that can be shot.
 * An arrow once shot keeps traveling up infinitely. The problem is to find the 
 * minimum number of arrows that must be shot to burst all balloons.
 * Example:
 * Input: [[10,16], [2,8], [1,6], [7,12]]
 * Output: 2
 * Explanation: One way is to shoot one arrow for example at x = 6 (bursting the 
 * balloons [2,8] and [1,6]) and another arrow at x = 11 (bursting the other two balloons).
 * 
 * Company: Microsoft 
 * Difficulty: medium
 * Similar Questions: 253(Meeting Rooms II), 435(NonOverlappingIntervals)
 */
public class MinimumNumberOfArrowsToBurstBallon {
    // Basically the idea is to sort by end points. This is because the end point 
    // decides how many balloons intersect, when you start moving towards right.
    // If there is no intersection with the next balloon, then this balloon needs 
    // an arrow to be burst. And if there is an intersection, we need to find how 
    // many balloons can intersect so that we can burst all balloons with one arrow.
    public int findMinArrowShots(int[][] points) {
        if(points == null || points.length == 0) return 0;
        
        Arrays.sort(points, (a,b)->a[1]-b[1]);
        int count = 1, end = points[0][1];
        for (int i = 1; i < points.length; i++) {
            if (end < points[i][0]) {
                count++;
                end = points[i][1];
            }
        }
        return count;
    }

}
