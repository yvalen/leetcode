package leetcode.graph;
import java.util.*;

/*
 * In a room are N persons, and we will define two persons are friends if they are directly or indirectly friends.
 * If A is a friend with B, and B is a friend with C, then A is a friend of C too.
 * A group of friends is a group of persons where any two persons in the group are friends.
 * Given the list of persons that are directly friends, Find the smallest group of friends.
 * Example:
 * input:
 * N = 10
 * connections:
 * 1 6 
 * 2 7
 * 3 8
 * 4 9
 * 2 6
 * 3 5
 * groups:
 * 1-6-2-7
 * 3-8-5
 * 4-9
 * The number of people in smaller group is 2 i.e. 4-9. 
 */

public class SmallestGroup {

    public int findSmallestGroup(int n, int[][] connections) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int i = 0; i < n; i++) {
            graph.put(i, new ArrayList<>());
        }
        for (int[] connection : connections) {
            graph.get(connection[0]).add(connection[1]);
            graph.get(connection[1]).add(connection[0]);
        }
        System.out.println(graph);

        int minCount = Integer.MAX_VALUE;
        boolean[] visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (visited[i] || graph.get(i).size() == 0) continue;
            int[] count = new int[1];
            //dfs(graph, visited, count, i);
            bfs(graph, visited, count, i);
            minCount = Math.min(minCount, count[0]);
        }
       
        return minCount;
    }
    
    private void dfs(Map<Integer, List<Integer>> graph, boolean[] visited, int[] count, int start) {
        visited[start] = true;
        count[0]++;
        for (int neighbor : graph.get(start)) {
            if (!visited[neighbor]) dfs(graph, visited, count, neighbor);
        }
    }

    private void bfs(Map<Integer, List<Integer>> graph, boolean[] visited, int[] count, int start) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);      
        while(!queue.isEmpty()) {
            Integer current = queue.poll();
            visited[current] = true;
            count[0]++;
            for (int neighbor : graph.get(current)) {
                if (!visited[neighbor]) queue.offer(neighbor);
            }
        }
    }

    public static void main(String[] args) {
        SmallestGroup s = new SmallestGroup();
        int[][] relations= {
                {1, 6}, 
                {2, 7}, 
                {3, 8}, 
                {2, 6},
                {4, 9},
                {3, 5}
        };
        System.out.println(s.findSmallestGroup(10, relations));
    }
}

