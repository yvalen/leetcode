package leetcode.dp;

import java.util.Arrays;

/*
 * LEETCODE 764
 * In a 2D grid from (0, 0) to (N-1, N-1), every cell contains a 1, except those cells 
 * in the given list mines which are 0. What is the largest axis-aligned plus sign of 1s 
 * contained in the grid? Return the order of the plus sign. If there is none, return 0.
 * An "axis-aligned plus sign of 1s of order k" has some center grid[x][y] = 1 along with 
 * 4 arms of length k-1 going up, down, left, and right, and made of 1s. This is demonstrated 
 * in the diagrams below. Note that there could be 0s or 1s beyond the arms of the plus sign, 
 * only the relevant area of the plus sign is checked for 1s.
 * Examples of Axis-Aligned Plus Signs of Order k:
 * Order 1:
 * 000
 * 010
 * 000
 * Order 2:
 * 00000
 * 00100
 * 01110
 * 00100
 * 00000
 * Order 3:
 * 0000000
 * 0001000
 * 0001000
 * 0111110
 * 0001000
 * 0001000
 * 0000000
 * 
 * Example 1:
 * Input: N = 5, mines = [[4, 2]]
 * Output: 2
 * Explanation:
 * 11111
 * 11111
 * 11111
 * 11111
 * 11011
 * In the above grid, the largest plus sign can only be order 2.  
 * Example 2:
 * Input: N = 2, mines = []
 * Output: 1
 * Explanation: There is no plus sign of order 2, but there is of order 1.
 * Example 3:
 * Input: N = 1, mines = [[0, 0]]
 * Output: 0
 * Explanation: There is no plus sign, so return 0.
 * Note:
 * - N will be an integer in the range [1, 500].
 * - mines will have length at most 5000.
 * - mines[i] will be length 2 and consist of integers in the range [0, N-1].
 * 
 * Company: Facebook
 * Difficulty: medium
 * Similar Questions: 221(MaximumSquare)
 */
public class LargestPlusSign {
    // Algorithms: For each position (i, j) of the grid matrix, we try to extend in each 
    // of the four directions (left, right, up, down) as long as possible, then take the 
    // minimum length of 1's out of the four directions as the order of the largest 
    // axis-aligned plus sign centered at position (i, j).
    
    // Time complexity: O(n^2)  Space complexity: O(n)
    public int orderOfLargestPlusSign(int N, int[][] mines) {
        int[][] matrix = new int[N][N];
        for (int i = 0; i < N; i++) {
            Arrays.fill(matrix[i], 5);
        }
        for (int[] mine : mines) {
            matrix[mine[0]][mine[1]] = 0;
        }
        
        for (int i = 0; i < N; i++) {
            for (int j = 0, l = 0; j < N; j++) {
                // j is a column index, iterate from left to right
                // every time check how far left it can reach.
                // if grid[i][j] is 0, l needs to start over from 0 again, otherwise increment
                matrix[i][j] = Math.min(matrix[i][j], l = (matrix[i][j] == 0 ? 0 : l+1));
            }
            for (int k = N-1, r = 0; k >= 0; k--) {
                // k is a column index, iterate from right to left
                // every time check how far right it can reach.
                // if grid[i][k] is 0, r needs to start over from 0 again, otherwise increment
                matrix[i][k] = Math.min(matrix[i][k], r = (matrix[i][k] == 0 ? 0 : r+1));
            }
            for (int j = 0, u = 0; j < N; j++) {
                // j is a row index, iterate from top to bottom
                // every time check how far up it can reach.
                // if grid[j][i] is 0, u needs to start over from 0 again, otherwise increment
                matrix[j][i] = Math.min(matrix[j][i], u = (matrix[j][i] == 0 ? 0 : u+1));
            }
            for (int k = N-1, d = 0; k >= 0; k--) {
                // k is a row index, iterate from bottom to top
                // every time check how far down it can reach.
                // if grid[k][i] is 0, d needs to start over from 0 again, otherwise increment
                matrix[k][i] = Math.min(matrix[k][i], d = (matrix[k][i] == 0 ? 0 : d+1));
            }
            System.out.println(Arrays.toString(matrix[i]));
        }
        
        int result = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                result = Math.max(result, matrix[i][j]);
            }
        }
        
        return result;
    }
    
    // Time complexity: O(n^2)  Space complexity: O(n^2)
    public int orderOfLargestPlusSign_fiveMatrixes(int N, int[][] mines) {
        int[][] matrix = new int[N][N];
        for (int i = 0; i < N; i++) {
            Arrays.fill(matrix[i], 1);
        }
        for (int[] mine : mines) {
            matrix[mine[0]][mine[1]] = 0;
        }
        
        // use 4 matrices for storing largest streak of ones on left, right top and bottom
        int[][] top = new int[N][N];
        int[][] bottom = new int[N][N];
        int[][] left = new int[N][N];
        int[][] right = new int[N][N];
        int result = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (matrix[i][j] == 1) {
                    top[i][j] = (i == 0 ? 1 : top[i-1][j] + 1);
                    left[i][j] = (j == 0 ? 1 : left[i][j-1] + 1);
                }
            }
        }
        
        for (int i = N-1; i >= 0; i--) {
            for (int j = N-1; j >= 0; j--) {
                if (matrix[i][j] == 1) {
                    bottom[i][j] = (i == N-1 ? 1 : bottom[i+1][j] + 1);
                    right[i][j] = (j == N-1 ? 1 : right[i][j+1] + 1);
                }
                
                result = Math.max(result, Math.min(top[i][j], Math.min(bottom[i][j], Math.min(left[i][j], right[i][j]))));
            }
        }
        
        return result;
    }
    
    public static void main(String[] args) {
        LargestPlusSign lps = new LargestPlusSign();
        int N = 5;
        int[][] mines = {
                {4,2}
        };
        System.out.println(lps.orderOfLargestPlusSign(N, mines));
    }
}
