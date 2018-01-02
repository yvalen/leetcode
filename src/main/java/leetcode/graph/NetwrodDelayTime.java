package leetcode.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;


/*
 * LEETCODE 743
 * There are N network nodes, labeled 1 to N. Given times, a list of travel times as directed edges 
 * times[i] = (u, v, w), where u is the source node, v is the target node, and w is the time it takes 
 * for a signal to travel from source to target. 
 * Now, we send a signal from a certain node K. How long will it take for all nodes to receive the signal? 
 * If it is impossible, return -1.
 * Note:
 * - N will be in the range [1, 100].
 * - K will be in the range [1, N].
 * - The length of times will be in the range [1, 6000].
 * - All edges times[i] = (u, v, w) will have 1 <= u, v <= N and 1 <= w <= 100.
 * 
 * Company: Akuna Capital
 * Difficulty: medium
 */
public class NetwrodDelayTime {
    // Time complexity: O(ElogE) - E is the length of times
    // Space complexity: O(N+E), O(E) for graph, O(N) for other objects
    public int networkDelayTime(int[][] times, int N, int K) {
        // build graph
        Map<Integer, List<int[]>> graph = new HashMap<>();
        for (int[] time : times) {
            graph.putIfAbsent(time[0], new ArrayList<>());
            graph.get(time[0]).add(new int[] {time[1], time[2]});        
        }
        
        // use a map for distances, this also tracks which node has been visited
        // we cannot put K in the map here. it will be added in the loop
        Map<Integer, Integer> distances = new HashMap<>();
       
        PriorityQueue<int[]> pq = new PriorityQueue<>((t1, t2)-> t1[1]-t2[1]); 
        pq.offer(new int[] {K, 0});
        while (!pq.isEmpty()) {
            int[] current = pq.poll();
            int v = current[0], d = current[1];
            if (distances.containsKey(v)) {
                // skip if the distance has been calculated already
                continue;
            }
            // add the distance to map, since the top element of pq is the smallest
            // this distance will be the shortest. 
            distances.put(v, d); 
            if (graph.containsKey(v)) {
                for (int[] edge : graph.get(v)) {
                    int w = edge[0];
                    if (!distances.containsKey(w)) {
                        // only put on pq if distance hasn't been calculated
                        // pq may have multiple entries for w, but only the
                        // top one will be stored in the distance map, the rest
                        // will be ignored once the shortest one is added to map
                        pq.offer(new int[] {w, edge[1]+d});
                    }
                }
            }
        }
        
        if (distances.size() != N) return -1; // some nodes cannot be reached
        
        int maxDelay = 0;
        for (int d : distances.values()) {
            maxDelay = Math.max(maxDelay, d);
        }
        return maxDelay;
    }
    
    public static void main(String[] args) {
        NetwrodDelayTime netwrodDelayTime = new NetwrodDelayTime ();
        int[][] times = {
                {2, 1, 1},
                {2, 3, 1},
                {3, 4, 1}
        };
        int N = 4, K = 2;
        System.out.println(netwrodDelayTime.networkDelayTime(times, N, K));
    }
}
