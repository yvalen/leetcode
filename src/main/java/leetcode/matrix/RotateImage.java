package leetcode.matrix;

import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class RotateImage {

    /*
     * clockwise rotate first reverse up to down, then swap the symmetry 1 2 3 7
     * 8 9 7 4 1 4 5 6 => 4 5 6 => 8 5 2 7 8 9 1 2 3 9 6 3
     */
    public void rotate(int[][] matrix) {
        if (matrix == null || matrix[0] == null)
            return;

        int m = matrix.length, n = matrix[0].length;

        // reverse upside down
        for (int i = 0; i < m / 2; i++) {
            for (int j = 0; j < n; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[m - 1 - i][j];
                matrix[m - 1 - i][j] = temp;
            }
        }

        // swap symmetrically
        for (int i = 0; i < m; i++) {
            for (int j = i + 1; j < n; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
    }

    /*
     * anticlockwise rotate first reverse left to right, then swap the symmetry
     * 1 2 3 3 2 1 3 6 9 4 5 6 => 6 5 4 => 2 5 8 7 8 9 9 8 7 1 4 7
     */
    public void antiRotate(int[][] matrix) {
        if (matrix == null || matrix[0] == null)
            return;

        int m = matrix.length, n = matrix[0].length;

        // reverse upside down
        for (int j = 0; j < n / 2; j++) {
            for (int i = 0; i < m; i++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[i][n - 1 - j];
                matrix[i][n - 1 - j] = temp;
            }
        }

        // swap symmetrically
        for (int i = 0; i < m; i++) {
            for (int j = i + 1; j < n; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
    }

    public static void main(String[] args) {
        RotateImage r = new RotateImage();

        int[][] matrix = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };

        r.antiRotate(matrix);
        MatrixUtil.print(matrix);

    }

}
