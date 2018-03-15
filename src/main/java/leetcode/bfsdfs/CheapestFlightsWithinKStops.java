package leetcode.bfsdfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

/*
 * LEETCODE 787
 * There are n cities connected by m flights. Each fight starts from city u and 
 * arrives at v with a price w. Now given all the cities and fights, together 
 * with starting city src and the destination dst, your task is to find the cheapest 
 * price from src to dst with up to k stops. If there is no such route, output -1.
 * Example 1:
 * Input: n = 3, edges = [[0,1,100],[1,2,100],[0,2,500]] src = 0, dst = 2, k = 1
 * Output: 200
 * Explanation: The graph looks like this:
 *          0
 *   100   /  \ 500
 *        /    \
 *       1 -----2 
 *         100
 * The cheapest price from city 0 to city 2 with at most 1 stop costs 200.
 * The cheapest price from city 0 to city 2 with at most 0 stop costs 500
 * Note:
 * - The number of nodes n will be in range [1, 100], with nodes labeled from 0 to n - 1.
 * - The size of flights will be in range [0, n * (n - 1) / 2].
 * - The format of each flight will be (src, dst, price).
 * - The price of each flight will be in the range [1, 10000].
 * - k is in the range of [0, n - 1].
 * - There will not be any duplicated flights or self cycles.
 * 
 * Company: Airbnb
 * Difficulty: medium
 * Similar Questions: 568()
 */
public class CheapestFlightsWithinKStops {
   
    // Time complexity: O(E+nlogn), E - length of flights
    // Space complexity: O(n)
    public int findCheapestPrice_dijkstras(int n, int[][] flights, int src, int dst, int K) {
        int[][] graph = new int[n][n];
        for (int[] flight :flights) {
            graph[flight[0]][flight[1]] = flight[2];
        }
        
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> a[2] - b[2]);
        minHeap.offer(new int[] {src, 0, 0});
        Map<Integer, Integer> minCostTo = new HashMap<>();
        while (!minHeap.isEmpty()) {
            printPQ(minHeap);
            int[] current = minHeap.poll();
            int city = current[0], stops = current[1], cost = current[2];
            if (stops > K+1 ) {
                continue;
            }
            if (city == dst) return cost;
            
            int[] nextCities = graph[city];
            for (int i = 0; i < n; i++) {
                if (nextCities[i] != 0) { // there is a flight from current to i
                    int nextCost = cost + nextCities[i];
                    if (nextCost < minCostTo.getOrDefault((stops+1)*1000+i, Integer.MAX_VALUE)) {
                        minHeap.offer(new int[] {i, stops+1, nextCost});
                        //minCostTo.put((stops+1)*1000+city, nextCost);
                        minCostTo.put((stops+1)*1000+i, nextCost);
                    }
                }
            }
        }
        return -1;
    }
    
    // Bellman-ford
    // dp[k][v] min cost from src to v with up to k flights
    // dp[k][v] = min(dp[k-1][u]+cost[u][v], dp[k-1][v])
    // Time O(kE) ~ O(kn^2), Space O(kn)
    // use this instead of Integer.MAX_VALUE to avoid overflow during add operation
    private static final int MAX_COST = 1000000000; 
    public int findCheapestPrice_twoDDP(int n, int[][] flights, int src, int dst, int K) {
        int[][] dp = new int[K+2][n];
        Arrays.fill(dp[0], MAX_COST);
        dp[0][src] = 0;
        for (int i = 1; i < K+2; i++) {
            Arrays.fill(dp[i], MAX_COST);
            dp[i][src] = 0;
            for (int[] flight : flights) {
                int start = flight[0], end = flight[1], cost = flight[2];
                dp[i][end] = Math.min(dp[i][end], dp[i-1][start]+cost);
            }
        }
        return dp[K+1][dst] == MAX_COST ? -1 : dp[K+1][dst];
    }
    
    public int findCheapestPrice_oneDDP(int n, int[][] flights, int src, int dst, int K) {
        int[] dp = new int[n];
        Arrays.fill(dp, MAX_COST);
        dp[src] = 0;
        for (int i = 0; i <= K; i++) {
            // save the state of dp array
            int[] tmp = Arrays.copyOf(dp, dp.length);
            for (int[] flight : flights) {
                int start = flight[0], end = flight[1], cost = flight[2];
                tmp[end] = Math.min(tmp[end], dp[start]+cost); // use dp[start] here
            }
            dp =  Arrays.copyOf(tmp, dp.length);
        }
        return dp[dst] == MAX_COST ? -1 : dp[dst];
    }
    
    private void printPQ(PriorityQueue<int[]> pq) {
        System.out.print("pq: [");
        for (int[] elem : pq) {
            System.out.print(Arrays.toString(elem) + ",");
        }
        System.out.print("]");
        System.out.println();
    }
    
   public static void main(String[] args) {
       CheapestFlightsWithinKStops cfks = new CheapestFlightsWithinKStops();
       int[][] flights = {{0,1,100},{1,2,100},{0,2,500}};
       int src = 0, dst = 2, K = 1, n = 3;
       //int[][] flights = {{0,1,1},{0,2,5},{1,2,1},{2,3,1}};
       //int src = 0, dst = 3, K = 1, n = 4;
       //System.out.println(cfks.findCheapestPrice_dijkstras(n, flights, src, dst, K));
       System.out.println(cfks.findCheapestPrice_twoDDP(n, flights, src, dst, K));
   }
}
