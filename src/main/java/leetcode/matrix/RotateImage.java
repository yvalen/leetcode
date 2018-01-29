package leetcode.matrix;

import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/*
 * LEETCODE 48
 * You are given an n x n 2D matrix representing an image. Rotate the image 
 * by 90 degrees (clockwise).
 * Note: You have to rotate the image in-place, which means you have to modify
 * the input 2D matrix directly. DO NOT allocate another 2D matrix and do the rotation.
 * Example 1: Given input matrix = 
 * [
 *  [1,2,3],
 *  [4,5,6],
 *  [7,8,9]
 * ],
 * rotate the input matrix in-place such that it becomes:
 * [
 *  [7,4,1],
 *  [8,5,2],
 *  [9,6,3]
 * ]
 * Example 2: Given input matrix =
 * [
 *  [ 5, 1, 9,11],
 *  [ 2, 4, 8,10],
 *  [13, 3, 6, 7],
 *  [15,14,12,16]
 * ], 
 * rotate the input matrix in-place such that it becomes:
 * [
 *  [15,13, 2, 5],
 *  [14, 3, 4, 1],
 *  [12, 6, 8, 9],
 *  [16, 7,10,11]
 * ]
 * 
 * Company: Microsoft, Amazon, Apple
 * Difficulty: medium
 */
public class RotateImage {

    /*
     * clockwise rotate
     * first reverse up to down, then swap the symmetry 
     * 1 2 3     7 8 9     7 4 1
     * 4 5 6  => 4 5 6  => 8 5 2
     * 7 8 9     1 2 3     9 6 3
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
     * counter clockwise rotate
     * first reverse left to right, then swap the symmetry
     * 1 2 3     3 2 1     3 6 9
     * 4 5 6  => 6 5 4  => 2 5 8
     * 7 8 9     9 8 7     1 4 7
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
