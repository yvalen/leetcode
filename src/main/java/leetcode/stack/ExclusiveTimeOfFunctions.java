package leetcode.stack;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

import leetcode.array.ArrayUtil;

/*
 * LEETCODE 636
 * Given the running logs of n functions that are executed in 
 * a non-preemptive single threaded CPU, find the exclusive time 
 * of these functions. Each function has a unique id, start from 
 * 0 to n-1. A function may be called recursively or by another 
 * function. A log is a string has this format: 
 * function_id:start_or_end:timestamp. 
 * For example, "0:start:0" means function 0 starts from the very 
 * beginning of time 0. "0:end:0" means function 0 ends to the very 
 * end of time 0. Exclusive time of a function is defined as the time 
 * spent within this function, the time spent by calling other functions 
 * should not be considered as this function's exclusive time. You should 
 * return the exclusive time of each function sorted by their function id.
 * Example 1: 
 * Input:
 * n = 2
 * logs = 
 * 	["0:start:0",
 * 	 "1:start:2",
 * 	 "1:end:5",
 * 	 "0:end:6"]
 * Output:[3, 4]
 * Explanation: Function 0 starts at time 0, then it executes 2 units of time 
 * and reaches the end of time 1. Now function 0 calls function 1, function 1 
 * starts at time 2, executes 4 units of time and end at time 5. Function 0 is 
 * running again at time 6, and also end at the time 6, thus executes 1 unit of 
 * time. So function 0 totally execute 2 + 1 = 3 units of time, and function 1 
 * totally execute 4 units of time.
 * Note:
 * - Input logs will be sorted by timestamp, NOT log id.
 * - Your output should be sorted by function id, which means the 0th element of 
 * your output corresponds to the exclusive time of function 0.
 * - Two functions won't start or end at the same time.
 * - Functions could be called recursively, and will always end.
 * - 1 <= n <= 100
 * 
 * Company: Facebook, Uber
 * Difficulty: medium
 */
public class ExclusiveTimeOfFunctions {
    // Complexity: O(n) - time, O(n) - space
    public int[] exclusiveTime(int n, List<String> logs) {
        if (logs == null || logs.isEmpty())
            return new int[] {};

        int[] result = new int[n];
        // use a stack to keep track of the function ids that have started
        Stack<Integer> stack = new Stack<>(); 
        int prev = 0; // represents the start of the interval
        for (String log : logs) {
            String[] ary = log.split(":");
            int timestamp = Integer.parseInt(ary[2]);
            if (!stack.isEmpty()) {
                result[stack.peek()] += timestamp - prev;
            }
            prev = timestamp;
            if (ary[1].equals("start")) {
                stack.push(Integer.parseInt(ary[0]));
            } else {
                // need to increment result as the timestamp represents 
                // the start of the interval
                result[stack.pop()]++;
                // need to increment prev here as the next interval 
                // starts after current end
                prev++; 
            }
        }
        return result;
    }

    public static void main(String[] args) {
        ExclusiveTimeOfFunctions etf = new ExclusiveTimeOfFunctions();

        List<String> logs = Arrays.asList("0:start:0", "1:start:2", "1:end:5", "0:end:6");
        int n = 2;

        /*
         * List<String> logs = Arrays.asList( "0:start:0", "0:start:2",
         * "0:end:5", "0:start:6", "0:end:6", "0:end:7" ); int n = 1;
         */
        /*
         * List<String> logs = Arrays.asList( "0:start:0", "0:start:2",
         * "0:end:5", "1:start:6", "1:end:6", "0:end:7" ); int n = 2;
         */
        ArrayUtil.printArray(etf.exclusiveTime(n, logs));
    }

}
