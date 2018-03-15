package leetcode.string;

import java.util.HashSet;
import java.util.Set;

import com.sun.org.apache.bcel.internal.generic.NEW;

import javafx.scene.shape.Ellipse;

/*
 * LEETCODE 681
 * Given a time represented in the format "HH:MM", form the next closest time 
 * by reusing the current digits. There is no limit on how many times a digit 
 * can be reused. You may assume the given input string is always valid. 
 * For example, "01:34", "12:09" are all valid. "1:34", "12:9" are all invalid.
 * Example 1: Input: "19:34" Output: "19:39"
 * Explanation: The next closest time choosing from digits 1, 9, 3, 4, is 19:39, 
 * which occurs 5 minutes later.  It is not 19:33, because this occurs 23 hours 
 * and 59 minutes later.
 * Example 2: Input: "23:59" Output: "22:22"
 * Explanation: The next closest time choosing from digits 2, 3, 5, 9, is 22:22. 
 * It may be assumed that the returned time is next day's time since it is smaller 
 * than the input time numerically.
 * 
 * Company: Google
 * Difficulty: medium
 */
public class NextClosestTime {
    // use dfs to search all the next time.
    // it will search 4 * 4 * 4 * 4 = 256 times.
    private int h;
    private int m;
    private int[] result = new int[2];
    private int diff = Integer.MAX_VALUE;
    public String nextClosestTime_dfs(String time) {
        String[] strs = time.split(":");
        int hour = Integer.parseInt(strs[0]);
        int minute = Integer.parseInt(strs[1]);
        this.h = hour;
        this.m = minute;
        
        int[] digits = new int[4];
        digits[0] = hour / 10;
        digits[1] = hour % 10;
        digits[2] = minute / 10;
        digits[3] = minute % 10;
        dfs(digits, 0, new int[4]);
        
        return String.format("%02d:%02d", result[0], result[1]);
    }
    
    private void dfs(int[] digits, int start, int[] ans) {
        if (start == 4) {
            int hour = ans[0] * 10 + ans[1];
            int minute = ans[2] * 10 + ans[3];
            if (hour >= 0 && hour <= 23 && minute >= 0 && minute <= 59) {
                int df = diff(hour, minute);
                if (df < this.diff) {
                    diff = df;
                    result[0] = hour;
                    result[1] = minute;
                }
            }
        }
        else {
            for (int i = 0; i < 4; i++) { // digits can be reused, always start from 0
                ans[start] = digits[i];
              
                // pruning, only do dfs for valid digit
                if (start == 1) {
                    int hour = ans[0] * 10 + ans[1];
                    if (hour >= 0 && hour <=23) dfs(digits, start+1, ans);
                }
                else if (start == 3) {
                    int minute = ans[2] * 10 + ans[3];
                    if (minute >= 0 && minute <=59) dfs(digits, start+1, ans);
                }
                else {
                    dfs(digits, start+1, ans);
                }
            }
        }
    }
    
    private int diff(int hour, int minute) {
        int c2o = 60*60 - h*60 - m;
        int n2o = 60*60 - hour*60 - minute;
        return n2o < c2o ? c2o-n2o : c2o-n2o+3600;
    }
    
    // increase the minute and the hour one by one. If all these four digits of the 
    // next time is in hash set, we find it and output because these four digits are 
    // all reused.
    // Time complexity: try up to 24∗60=1440 possible times until we find the correct time.
    public String nextClosestTime_increment(String time) {
        String[] strs = time.split(":");
        int hour = Integer.parseInt(strs[0]);
        int minute = Integer.parseInt(strs[1]);
        
        Set<Integer>set = new HashSet<>();
        addToSet(set, hour);
        addToSet(set, minute);
        
        int[] times = new int[] {hour, minute};
        getNext(times);
        
        while (!inSet(times, set)) {
            getNext(times);
        }
        return String.format("%02d:%02d", times[0], times[1]);
    }
    
    private void addToSet(Set<Integer> set, int val) {
        set.add(val / 10);
        set.add(val % 10);
    }
    
    private void getNext(int[] times) {
        int hour = times[0], minute = times[1];
        minute++;
        if (minute == 60) {
            minute = 0;
            hour++;
            if (hour == 24) hour = 0;
        }
        times[0] = hour;
        times[1] = minute;
    }
    
    private boolean inSet(int[] times, Set<Integer> set) {
        return set.contains(times[0]/10) && set.contains(times[0]%10) &&
                set.contains(times[1]/10) && set.contains(times[1]%10);
    }
    
}
