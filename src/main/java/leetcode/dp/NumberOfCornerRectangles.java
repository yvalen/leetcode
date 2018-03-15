package leetcode.dp;

/*
 * LEETCODE 750
 * Given a grid where each entry is only 0 or 1, find the number of corner rectangles.
 * A corner rectangle is 4 distinct 1s on the grid that form an axis-aligned rectangle. 
 * Note that only the corners need to have the value 1. Also, all four 1s used must be 
 * distinct.
 * Example 1:
 * Input: grid = 
 * [
 *  [1, 0, 0, 1, 0],
 *  [0, 0, 1, 0, 1],
 *  [0, 0, 0, 1, 0],
 *  [1, 0, 1, 0, 1]
 * ]
 * Output: 1
 * Explanation: There is only one corner rectangle, with corners grid[1][2], grid[1][4], 
 * grid[3][2], grid[3][4].
 * Example 2:
 * Input: grid = 
 * [[1, 1, 1],
 *  [1, 1, 1],
 *  [1, 1, 1]]
 * Output: 9
 * Explanation: There are four 2x2 rectangles, four 2x3 and 3x2 rectangles, and one 3x3 
 * rectangle.
 * Example 3:
 * Input: grid = [[1, 1, 1, 1]]
 * Output: 0
 * Explanation: Rectangles must have four distinct corners.
 * Note:
 * - The number of rows and columns of grid will each be in the range [1, 200].
 * - Each grid[i][j] will be either 0 or 1.
 * - The number of 1s in the grid will be at most 6000.
 * https://leetcode.com/problems/number-of-corner-rectangles/discuss/110200/Summary-of-three-solutions-based-on-three-different-ideas
 * 
 * Company: Facebook
 * Difficulty: medium
 */
public class NumberOfCornerRectangles {
    // For each pair of 1s in the new row (say at new_row[i] and new_row[j]), we could create 
    // more rectangles where that pair forms the base. The number of new rectangles is the 
    // number of times some previous row had row[i] = row[j] = 1.
    // Let's maintain a count count[i, j], the number of times we saw row[i] = row[j] = 1. 
    // When we process a new row, for every pair new_row[i] = new_row[j] = 1, we add count[i, j] 
    // to the answer, then we increment count[i, j].
    // Time complexity: O(m*n^2)  Space complexity: O(n^2)
    public int countCornerRectangles_dp(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[][] dp = new int[n][n];
        int result = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) continue;
                for (int k = j+1; k < n; k++) {
                    if (grid[i][k] == 0) continue;
                    result += dp[j][k]++;
                }
            }
        }
        
        return result;
    }
}
