package leetcode.array;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Pascal's triangle may be constructed in the following manner: 
 * In row 0 (the topmost row), there is a unique nonzero entry 1. 
 * Each entry of each subsequent row is constructed by adding the 
 * number above and to the left with the number above and to the right, 
 * treating blank entries as 0.
 * 
 * https://www.programiz.com/c-programming/examples/pyramid-pattern
 */
public class PascalTriangle {
    /**
     * LEETCODE 118
     * Given numRows, generate the first numRows of Pascal's triangle. 
     * For example, given numRows = 5, 
     * return 
     * [ 
     *      [1], 
     *     [1,1], 
     *    [1,2,1], 
     *   [1,3,3,1], 
     *  [1,4,6,4,1] 
     * ]
     * 
     * Company: Twitter, Apple
     * Difficulty: easy
     * Similar Questions: 119(Pascal's Triangle II)
     */
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> result = new ArrayList<>(numRows);
        if (numRows <= 0)
            return result;

        for (int i = 0; i < numRows; i++) {
            List<Integer> row = new ArrayList<>();
            result.add(row);
            row.add(0, 1);
            if (i == 0)
                continue;

            for (int j = 1; j < i; j++) {
                List<Integer> prevRow = result.get(i - 1);
                int value = prevRow.get(j - 1) + prevRow.get(j);
                row.add(value);
            }
            row.add(1);
        }

        return result;
    }

    /**
     * LEETCODE 119
     * Given an index k, return the kth row of the Pascal's triangle. 
     * For example, given k = 3, return [1,3,3,1]. 
     * Note: could you optimize your algorithm to use only O(k) extra space?
     * 
     * Company: Amazon
     * Difficulty: easy
     * Similar Questions: 118(Pascal's Triangle)
     */
    public List<Integer> getRow(int rowIndex) {
        List<Integer> result = new ArrayList<>();

        // rowIndex 0 is valid and should return 1
        if (rowIndex < 0) return Collections.emptyList();

        result.add(1);
        // i starts from 1 which is the 2nd row as the first row 
        // has been created outside the loop
        for (int i = 1; i <= rowIndex; i++) {
            for (int j = i-1; j >= 1; j--) {
                result.set(j, result.get(j-1) + result.get(j));
            }
            result.add(1);
        }

        return result;
    }

    public static void main(String[] args) {
        PascalTriangle triangle = new PascalTriangle();

        // List<List<Integer>> result = triangle.generate(5);
        // result.stream().forEach(System.out::println);

        List<Integer> row = triangle.getRow(3);
        System.out.println(row);
    }
}
