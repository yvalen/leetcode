package leetcode.matrix;

/*
 * LEETCODE 661
 * Given a 2D integer matrix M representing the gray scale of an image, you need to design a smoother to make the gray scale of each cell 
 * becomes the average gray scale (rounding down) of all the 8 surrounding cells and itself. If a cell has less than 8 surrounding cells, 
 * then use as many as you can.
 * Example 1:
 * Input:
 * [[1,1,1],
 * 	[1,0,1],
 * 	[1,1,1]]
 * Output:
 * [[0, 0, 0],
 * 	[0, 0, 0],
 * 	[0, 0, 0]]
 * Explanation:
 * - For the point (0,0), (0,2), (2,0), (2,2): floor(3/4) = floor(0.75) = 0
 * - For the point (0,1), (1,0), (1,2), (2,1): floor(5/6) = floor(0.83333333) = 0
 * - For the point (1,1): floor(8/9) = floor(0.88888889) = 0
 * Note:
 * - The value in the given matrix is in the range of [0, 255].
 * - The length and width of the given matrix are in the range of [1, 150].
 * 
 * Company: Amazon
 * Difficulty: easy
 */
public class ImageSmoother {
    public int[][] imageSmoother(int[][] M) {
        int m = M.length, n = M[0].length;
        int[][] result = new int[m][n];

        int[][] dirs = new int[][] { { 0, 0 }, { 0, 1 }, { 1, 0 }, { 1, 1 }, { -1, 0 }, { 0, -1 }, { -1, -1 },
                { 1, -1 }, { -1, 1 } };
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int count = 0, sum = 0;
                for (int[] dir : dirs) {
                    int row = i + dir[0], col = j + dir[1];
                    if (row >= 0 && col >= 0 && row < m && col < n) {
                        count++;
                        sum += M[row][col];
                    }
                }
                result[i][j] = sum / count;
            }
        }
        return result;
    }

}
