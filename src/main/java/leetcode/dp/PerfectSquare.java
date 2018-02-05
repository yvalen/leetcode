package leetcode.dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/*
 * LEETCODE 279
 * Given a positive integer n, find the least number of perfect 
 * square numbers (for example, 1, 4, 9, 16, ...) which sum to n.
 * For example, given n = 12, return 3 because 12 = 4 + 4 + 4; 
 * given n = 13, return 2 because 13 = 4 + 9. 
 * 
 * Company: Google
 * Difficulty: medium
 * Similar Questions: 203(CountPrimes), 264(Ugly Number II)
 */
public class PerfectSquare {

    public int numSquares_dp(int n) {
        if (n <= 0)
            return 0;

        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j * j <= i; j++) {
                // for each i it must be the sum of some number (i-j*j)
                // and a perfect square number (j*j)
                dp[i] = Integer.min(dp[i], dp[i - j * j] + 1);
            }
        }
        return dp[n];
    }

    // Consider a graph which consists of number 0, 1,...,n as
    // its nodes. Node j is connected to node i via an edge if
    // and only if either j = i + (a perfect square number) or
    // i = j + (a perfect square number). Starting from node 0,
    // do the breadth-first search. If we reach node n at step
    // m, then the least number of perfect square numbers which
    // sum to n is m. Here since we have already obtained the
    // perfect square numbers, we have actually finished the
    // search at step 1.
    public int numSquares_bfs(int n) {
        if (n <= 0)
            return 0;

        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        queue.offer(0); // start from 0
        int depth = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            depth++;
            while (size-- > 0) {
                int current = queue.poll();
                for (int i = 1; i * i <= n; i++) {
                    int value = current + i * i;
                    if (value == n)
                        return depth;
                    if (value > n)
                        break;

                    // enqueue value if it hasn't been processed before
                    if (!visited.contains(value)) {
                        queue.offer(value);
                        visited.add(value);
                    }
                }
            }
        }
        return depth;
    }

    public static void main(String[] args) {
        PerfectSquare ps = new PerfectSquare();
        int n = 12;
        System.out.println(ps.numSquares_bfs(n));

    }
}
