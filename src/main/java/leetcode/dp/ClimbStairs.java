package leetcode.dp;

/**
 * You are climbing a stair case. It takes n steps to reach to the top. Each
 * time you can either climb 1 or 2 steps. In how many distinct ways can you
 * climb to the top?
 */
public class ClimbStairs {

    public int climbStairs_topDown(int n) {
        if (n <= 0)
            return 0;

        int[] ways = new int[n + 1]; // n is the n+1 element in the array
        return climbStairsHelper(n, ways);
    }

    private int climbStairsHelper(int n, int[] ways) {
        if (n <= 0)
            return 0;

        if (ways[n] > 0)
            return ways[n];

        if (n == 1)
            ways[n] = 1; // base case 1
        else if (n == 2)
            ways[n] = 2; // base case 2
        else
            ways[n] = climbStairsHelper(n - 1, ways) + climbStairsHelper(n - 2, ways);

        return ways[n];
    }

    public int climbStairs_bottomUp(int n) {
        if (n <= 0)
            return 0;

        int[] ways = new int[n + 1];

        // base case
        ways[1] = 1;
        if (n > 1) {
            ways[2] = 2;
        }

        for (int i = 3; i <= n; i++) {
            ways[i] = ways[i - 1] + ways[i - 2];
        }

        return ways[n];
    }

    public int climbStairs_bottomUp_spaceOptimization(int n) {
        if (n <= 0)
            return 0;
        if (n == 1)
            return 1;
        if (n == 2)
            return 2;

        int n1 = 2;
        int n2 = 1;
        int ret = 0;
        for (int i = 3; i <= n; i++) {
            ret = n1 + n2;
            n2 = n1;
            n1 = ret;
        }

        return ret;
    }
}
