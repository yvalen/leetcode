package leetcode.matrix;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SpiralMatrix {
    /**
     * LEETCODE 56
     * Given a matrix of m x n elements (m rows, n columns), return all elements
     * of the matrix in spiral order. 
     * For example, given the following matrix: 
     * [
     *  [ 1, 2, 3 ], 
     *  [ 4, 5, 6 ], 
     *  [ 7, 8, 9 ]
     * ] 
     * You should return [1,2,3,6,9,8,7,4,5].
     * 
     * Company: Google, Uber, Microsoft
     * Difficulty: medium
     * Similar Questions: 59(Spiral Matrix II)
     */
    public List<Integer> spiralOrder_revursive(int[][] matrix) {
        List<Integer> result = new LinkedList<>();
        if (matrix.length == 0)
            return result;

        sprialOrderHelper(matrix, 0, matrix.length - 1, 0, matrix[0].length - 1, result);
        return result;
    }

    public List<Integer> spiralOrder_nonRevursive(int[][] matrix) {
        List<Integer> result = new LinkedList<>();
        if (matrix.length == 0)
            return result;

        int rowStart = 0, rowEnd = matrix.length - 1, colStart = 0, colEnd = matrix[0].length - 1;
        while (rowStart <= rowEnd && colStart <= colEnd) {
            for (int i = colStart; i <= colEnd; i++) {
                result.add(matrix[rowStart][i]);
            }

            if (rowStart == rowEnd)
                break;

            for (int i = rowStart + 1; i <= rowEnd; i++) {
                result.add(matrix[i][colEnd]);
            }

            if (colStart == colEnd)
                break;

            for (int i = colEnd - 1; i >= colStart; i--) {
                result.add(matrix[rowEnd][i]);
            }

            for (int i = rowEnd - 1; i > rowStart; i--) {
                result.add(matrix[i][colStart]);
            }

            rowStart++;
            rowEnd--;
            colStart++;
            colEnd--;
        }

        return result;
    }

    private void sprialOrderHelper(int[][] matrix, int rowStart, int rowEnd, int colStart, int colEnd,
            List<Integer> result) {
        if (rowStart > rowEnd || colStart > colEnd)
            return;

        for (int i = colStart; i <= colEnd; i++) {
            result.add(matrix[rowStart][i]);
        }

        if (rowStart == rowEnd) {
            return;
        }

        for (int i = rowStart + 1; i <= rowEnd; i++) {
            result.add(matrix[i][colEnd]);
        }

        if (colStart == colEnd) {
            return;
        }

        for (int i = colEnd - 1; i >= colStart; i--) {
            result.add(matrix[rowEnd][i]);
        }

        for (int i = rowEnd - 1; i > rowStart; i--) {
            result.add(matrix[i][colStart]);
        }

        // recurse to the next layer
        sprialOrderHelper(matrix, rowStart + 1, rowEnd - 1, colStart + 1, colEnd - 1, result);
    }

    /**
     * LEETCODE 59
     * Given an integer n, generate a square matrix filled with elements from 1
     * to n2 in spiral order. For example, given n = 3, you should return the
     * following matrix: [ [ 1, 2, 3 ], [ 8, 9, 4 ], [ 7, 6, 5 ] ]
     * 
     * Difficulty: medium
     * Similar Questions: 54(Spiral Matrix)
     */
    public int[][] generateMatrix(int n) {
        if (n <= 0) {
            int[][] result = {};
            return result;
        }

        int[][] matrix = new int[n][n];

        int rowStart = 0, rowEnd = n - 1, colStart = 0, colEnd = n - 1;
        int value = 0;
        while (rowStart <= rowEnd || colStart <= colEnd) {
            for (int i = colStart; i <= colEnd; i++) {
                matrix[rowStart][i] = ++value;
            }

            if (rowStart == rowEnd)
                break;

            for (int i = rowStart + 1; i <= rowEnd; i++) {
                matrix[i][colEnd] = ++value;
            }

            if (colStart == colEnd)
                break;

            for (int i = colEnd - 1; i >= colStart; i--) {
                matrix[rowEnd][i] = ++value;
            }

            for (int i = rowEnd - 1; i > rowStart; i--) {
                matrix[i][colStart] = ++value;
            }

            rowStart++;
            rowEnd--;
            colStart++;
            colEnd--;
        }

        return matrix;
    }

    public static void main(String[] args) {
        SpiralMatrix s = new SpiralMatrix();

        int[][] matrix = { { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 }, { 11, 12, 13, 14, 15, 16, 17, 18, 19, 20 } };
        List<Integer> result = s.spiralOrder_nonRevursive(matrix);
        System.out.println(result);
    }

}
