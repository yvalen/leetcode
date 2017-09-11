package leetcode.greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/*
 * Given a char array representing tasks CPU need to do. It contains capital letters A to Z where different letters represent different tasks.
 * Tasks could be done without original order. Each task could be done in one interval. For each interval, CPU could finish one task or just be idle.
 * However, there is a non-negative cooling interval n that means between two same tasks, there must be at least n intervals that CPU are doing different 
 * tasks or just be idle. You need to return the least number of intervals the CPU will take to finish all the given tasks.
 * Example 1: Input: tasks = ['A','A','A','B','B','B'], n = 2 Output: 8
 * Explanation: A -> B -> idle -> A -> B -> idle -> A -> B.
 * Note:
 * - The number of tasks is in the range [1, 10000].
 * - The integer n is in the range [0, 100].
 * 
 * Company: Facebook
 * Difficulty: medium
 */
public class TaskScheduler {
	/*
	 * Assume the most frequent letter(s) is with count k, then there are two cases:
	 * - When the whole frame is "loose" -- when there is still unused idle time in each chunk, 
	 * result = (c[25] - 1) * (n + 1) + 25 - i where "25 - i" simply counts how many letters are with count k and 
	 * n + 1 is the length of each chunk. Since we have k (in this case k == c[25]) chunks, the total length of the 
	 * first k - 1 chunks == (k - 1) * (n + 1). For the last chunk, we don't need the "whole chunk" (i.e., if the chunk is "ABXXX", 
	 * we only will need the "AB". Because there are no more "less frequent letters" left so we can remove the last three spaces)
	 * - When the frame is "dense" (fully filled), the length of each chunk > n + 1. In this case it could be: a) initially the number 
	 * of letters with count k is > n + 1 so the frame is already "fully filled without space left", or 2) the length of each chunk 
	 * is > n + 1 after insertion. We still calculate the total length by adding up all the chunks, and it will == the length of the task at last.
	 */
	public int leastInterval(char[] tasks, int n) {
        int[] taskCount = new int[26];
        for (char c : tasks) {
        	taskCount[c - 'A']++;
        }
        
        Arrays.sort(taskCount);
		
        // count how many tasks have the max occurrence -> 25 -i
		int i = 25;
		while(i >= 0 && taskCount[i] == taskCount[25]) i--;
        
		// there should be taskCount[25] frames, among which taskCounr[25]-1 frames will have length of n+1
		// 25 - i is the length of the last frame
        return Math.max(tasks.length, (taskCount[25]-1)*(n+1)+(25-i));
    }
	
	
	public int leastInterval_priorityQueue(char[] tasks, int n) {
		int[] taskCount = new int[26];
        for (char c : tasks) {
        	taskCount[c - 'A']++;
        }
        
        // pq stores the number of instances left to be executed in descending order
        PriorityQueue<Integer> pq = new PriorityQueue<>(26, Collections.reverseOrder());
        for (int count : taskCount) {
        	if (count > 0) pq.offer(count);
        }
        
        int count = 0;
        while (!pq.isEmpty()) {
        	int k = n + 1;
        	List<Integer> list = new ArrayList<>(n+1); // stores the remaining count 
        	while (k > 0 && !pq.isEmpty()) {
        		list.add(pq.poll()- 1);
        		k--;
        		count++;
        	}
        	
        	// add remaining count back to queue
        	for (Integer i : list) {
        		if (i > 0) pq.offer(i);
        	}
        	
        	if (pq.isEmpty()) break; // no more instance to process
        	
        	count += k; // if k > 0 it means we need to add some idle interval
        }
        return count;
	}
	
	public static void main(String[] args) {
		TaskScheduler ts = new TaskScheduler();
		char[] tasks = {'A', 'A', 'A', 'B', 'B', 'B'};
		int n = 2;
		System.out.println(ts.leastInterval_priorityQueue(tasks, n));
	}

}
