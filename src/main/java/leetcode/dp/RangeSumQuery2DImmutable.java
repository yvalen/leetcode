package leetcode.dp;

/*
 * LEETCODE 304
 * Given a 2D matrix matrix, find the sum of the elements inside the rectangle defined by 
 * its upper left corner (row1, col1) and lower right corner (row2, col2).
 * Given matrix = [
 * 	[3, 0, 1, 4, 2],
 * 	[5, 6, 3, 2, 1],
 * 	[1, 2, 0, 1, 5],
 * 	[4, 1, 0, 1, 7],
 * 	[1, 0, 3, 0, 5]
 * ]
 * 	sumRegion(2, 1, 4, 3) -> 8
 * 	sumRegion(1, 1, 2, 2) -> 11
 * 	sumRegion(1, 2, 2, 4) -> 12 
 * 
 * Difficulty: Medium
 * Similar Questions: 303(RangeSumQueryImmutable)
 */
public class RangeSumQuery2DImmutable {
    private int[][] sum;
    private int m, n;

    public RangeSumQuery2DImmutable(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return;
        }

        m = matrix.length;
        n = matrix[0].length;
        this.sum = new int[m + 1][n + 1];

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                sum[i][j] = sum[i - 1][j] + sum[i][j - 1] - sum[i - 1][j - 1] + matrix[i - 1][j - 1];
            }
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        int a = sum[row2 + 1][col2 + 1];
        int b = sum[row1][col2 + 1];
        int c = sum[row2 + 1][col1];
        int d = sum[row1][col1];

        return sum[row2 + 1][col2 + 1] - sum[row1][col2 + 1] - sum[row2 + 1][col1] + sum[row1][col1];
    }

    public static void main(String[] args) {
        int[][] matrix = { { 1 }, { -7 } };

        RangeSumQuery2DImmutable r = new RangeSumQuery2DImmutable(matrix);
        System.out.println(r.sumRegion(0, 0, 0, 0));
        System.out.println(r.sumRegion(1, 0, 1, 0));
        System.out.println(r.sumRegion(0, 0, 1, 0));
    }

}
