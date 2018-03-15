package leetcode.backtracking;


/*
 * LEETCODE 351
 * Given an Android 3x3 key lock screen and two integers m and n, where 1 ≤ m ≤ n ≤ 9, 
 * count the total number of unlock patterns of the Android lock screen, which consist 
 * of minimum of m keys and maximum n keys.
 * Rules for a valid pattern:
 * - Each pattern must connect at least m keys and at most n keys.
 * - All the keys must be distinct.
 * - If the line connecting two consecutive keys in the pattern passes through any other 
 * keys, the other keys must have previously selected in the pattern. No jumps through 
 * non selected key is allowed.
 * - The order of keys used matters.
 * Explanation:
 * | 1 | 2 | 3 |
 * | 4 | 5 | 6 |
 * | 7 | 8 | 9 |
 * Invalid move: 4 - 1 - 3 - 6
 * Line 1 - 3 passes through key 2 which had not been selected in the pattern.
 * Invalid move: 4 - 1 - 9 - 2
 * Line 1 - 9 passes through key 5 which had not been selected in the pattern.
 * Valid move: 2 - 4 - 1 - 3 - 6
 * Line 1 - 3 is valid because it passes through key 2, which had been selected in the pattern
 * Valid move: 6 - 5 - 4 - 1 - 9 - 2
 * Line 1 - 9 is valid because it passes through key 5, which had been selected in the pattern.
 * Example: Given m = 1, n = 1, return 9. 
 * 
 * Company: Google
 * Difficulty: medium
 */
public class AndroidUnlockPatterns {
    public int numberOfPatterns(int m, int n) {
        int[][] skip = new int[10][10];
        skip[1][3] = skip[3][1] = 2;
        skip[1][7] = skip[7][1] = 4;
        skip[7][9] = skip[9][7] = 8;
        skip[3][9] = skip[9][3] = 6;
        skip[1][9] = skip[9][1] = skip[3][7] = skip[7][3] = skip[4][6] = skip[6][4] = skip[2][8] = skip[8][2] = 5;
        boolean[] visited = new boolean[10];
        int result = 0;
        for (int i = m; i <= n; i++) {
            result += dfs(visited, skip, 1, i-1) * 4; // 1, 3, 7, 9 are symmetric
            result += dfs(visited, skip, 2, i-1) * 4; // 2, 4, 6, 8 are symmetric
            result += dfs(visited, skip, 5, i-1);  // 5
        }
        return result;
    }
    
    private int dfs(boolean[] visited, int[][] skip, int current, int remain) {
        if (remain < 0) return 0;
        if (remain == 0) return 1;
        int result = 0;
        visited[current] = true;
        for (int i = 1; i <= 9; i++) {
            if (!visited[i] && // i has not been used
                    (skip[current][i] == 0 || visited[skip[current][i]])) {
                result += dfs(visited, skip, i, remain-1);
            }
        }
        visited[current] = false; // need to backtrack
        return result;
    }
}
