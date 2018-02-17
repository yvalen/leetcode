package leetcode.matrix;

import java.util.ArrayList;
import java.util.List;

/*
 * LEETCODE 311
 * Given two sparse matrices A and B, return the result of AB. 
 * You may assume that A's column number is equal to B's row number.
 * Example:
 * A = [
 * 	[ 1, 0, 0],
 * 	[-1, 0, 3]
 * ]
 * B = [
 * 	[ 7, 0, 0 ],
 * 	[ 0, 0, 0 ],
 * 	[ 0, 0, 1 ]
 * ]
 *      |  1 0 0 |   | 7 0 0 |   |  7 0 0 |
 * AB = | -1 0 3 | x | 0 0 0 | = | -7 0 3 |
 *                   | 0 0 1 |
 * matrix multiplication: https://www.mathsisfun.com/algebra/matrix-multiplying.html
 * http://www.cs.cmu.edu/~scandal/cacm/node9.html
 * sparse matrix: https://en.wikipedia.org/wiki/Sparse_matrix
 * 
 * Company: LinkedIn, Facebook
 * Difficulty: medium
 */
public class SparseMatrixMultiplication {
    public int[][] multiply(int[][] A, int[][] B) {
        int m = A.length, n = B[0].length;
        int[][] result = new int[m][n];
        
        /*
        // brute force
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < A[0].length; k++) {
                    result[i][j] += A[i][k] * B[k][j];    
                }
            }
        }
        */
        
        // do not calculate the final result at once, takes each value from A, 
        // and calculate and partial sum and accumulate it into the final spot
        // if A[i][k] is 0, we skip a  for loop calculation, which is a loop 
        // iterating aCols times, 
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < B.length; j++) {
                if (A[i][j] != 0) { // only perform multiplication when the
                                    // element is non-zero
                    System.out.println("use A:" + A[i][j] + " i="+i+" j="+j);
                    for (int k = 0; k < n; k++) {
                        // no need to check B[j][k] here, it will incur 
                        // additional cost for the check
                        result[i][k] += A[i][j] * B[j][k];
                    }
                }
            }
        }

        return result;
    }

    // store the sparse matrix in list of lists(LIL), one list per row, with
    // each entry containing the column index and the value.
    public int[][] multiply_LIL(int[][] A, int[][] B) {
        int m = A.length, n = B[0].length;
        int[][] result = new int[m][n];

        // store A in list of lists
        List<List<int[]>> lil = new ArrayList<>(m);
        for (int i = 0; i < m; i++) {
            List<int[]> l = new ArrayList<>();
            for (int j = 0; j < B.length; j++) {
                if (A[i][j] != 0) {
                    l.add(new int[] { j, A[i][j] });
                }
            }
            lil.add(l);
        }

        for (int i = 0; i < m; i++) {
            for (int[] cv : lil.get(i)) {
                int col = cv[0], val = cv[1];
                for (int j = 0; j < n; j++) {
                    result[i][j] += val * B[col][j];
                }
            }
        }
        return result;
    }

    public static void main(String[] arg) {
        SparseMatrixMultiplication smm = new SparseMatrixMultiplication();
        int[][] A = { { 1, 0, 0 }, { -1, 0, 3 } };
        int[][] B = { { 7, 8, 9 }, { 0, 0, 0 }, { 0, 0, 1 } };
        int[][] result = smm.multiply(A, B);
        MatrixUtil.print(result);
    }
}
