package leetcode.bfsdfs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/*
 * LEETCODE 582
 * Given n processes, each process has a unique PID (process id) and its PPID (parent process id).
 * Each process only has one parent process, but may have one or more children processes. This is 
 * just like a tree structure. Only one process has PPID that is 0, which means this process has no 
 * parent process. All the PIDs will be distinct positive integers.
 * We use two list of integers to represent a list of processes, where the first list contains PID 
 * for each process and the second list contains the corresponding PPID.
 * Now given the two lists, and a PID representing a process you want to kill, return a list of PIDs 
 * of processes that will be killed in the end. You should assume that when a process is killed, 
 * all its children processes will be killed. No order is required for the final answer.
 * Example 1:
 * Input: pid =  [1, 3, 10, 5] ppid = [3, 0, 5, 3] kill = 5
 * Output: [5,10]
 * Explanation: 
 *            3
 *          /   \
 *         1     5
 *              /
 *            10
 * Kill 5 will also kill 10.
 * Note:
 * - The given kill id is guaranteed to be one of the given PIDs.
 * - n >= 1.
 * 
 * Company: Bloomberg
 * Difficulty: medium
 */
public class KillProcess {
    public List<Integer> killProcess_bfs(List<Integer> pid, List<Integer> ppid, int kill) {
        if (pid.size() != ppid.size())
            return Collections.emptyList();

        Map<Integer, List<Integer>> adjList = new HashMap<>(pid.size());
        for (int i = 0; i < pid.size(); i++) {
            adjList.putIfAbsent(ppid.get(i), new ArrayList<>());
            adjList.get(ppid.get(i)).add(pid.get(i));
        }

        List<Integer> result = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(kill);
        while (!queue.isEmpty()) {
            Integer current = queue.poll();
            if (adjList.containsKey(current))
                queue.addAll(adjList.get(current));
            result.add(current);
        }

        return result;
    }

    public List<Integer> killProcess_dfs(List<Integer> pid, List<Integer> ppid, int kill) {
        if (pid.size() != ppid.size())
            return Collections.emptyList();

        Map<Integer, List<Integer>> adjList = new HashMap<>(pid.size());
        for (int i = 0; i < pid.size(); i++) {
            adjList.putIfAbsent(ppid.get(i), new ArrayList<>());
            adjList.get(ppid.get(i)).add(pid.get(i));
        }

        List<Integer> result = new ArrayList<>();
        result.add(kill);
        dfs(adjList, result, kill);
        return result;
    }

    private void dfs(Map<Integer, List<Integer>> adjList, List<Integer> result, int kill) {
        if (!adjList.containsKey(kill))
            return;

        for (Integer i : adjList.get(kill)) {
            result.add(i);
            dfs(adjList, result, i);
        }
    }

}
