package leetcode.array;

import java.util.Arrays;

/**
 * Assume you have an array of length n initialized with all 0's and are given k
 * update operations. Each operation is represented as a triplet: [startIndex,
 * endIndex, inc] which increments each element of subarray A[startIndex ...
 * endIndex] (startIndex and endIndex inclusive) with inc. Return the modified
 * array after all k operations were executed. Example: Given: length = 5,
 * updates = [ [1, 3, 2], [2, 4, 3], [0, 2, -2] ] Output: [-2, 0, 3, 5, 3]
 *
 */
public class RangeAddition {

    /**
     * Iterate through the k update operations and "somehow" mark them in the
     * [0, 0, 0, 0, 0] array (using length 5 for example), for each operation,
     * only update startIndex and endIndex + 1. this is O(k) in total. iterate
     * through the marked array and "somehow" transforms it to the final result
     * array. this is O(n) in total (n = length). All in all it is O(n + k).
     * 
     * Put inc at startIndex allows the inc to be carried to the next index
     * starting from startIndex when we do the sum accumulation. Put -inc at
     * endIndex + 1 simply means cancel out the previous carry from the next
     * index of the endIndex, because the previous carry should not be counted
     * beyond endIndex.
     */
    public int[] getModifiedArray(int length, int[][] updates) {
        int[] result = new int[length + 1];

        for (int[] update : updates) {
            result[update[0]] += update[2];
            result[update[1] + 1] -= update[2];
        }

        for (int i = 1; i <= length; i++) {
            result[i] += result[i - 1];
        }

        return Arrays.copyOf(result, length);
    }
}
