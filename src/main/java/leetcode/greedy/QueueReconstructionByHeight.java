package leetcode.greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * LEETCODE 406
 * Suppose you have a random list of people standing in a queue. 
 * Each person is described by a pair of integers (h, k), where
 * h is the height of the person and k is the number of people in 
 * front of this person who have a height greater than or equal to h. 
 * Write an algorithm to reconstruct the queue.
 * Note: The number of people is less than 1,100.
 * Example
 * Input: [[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]
 * Output: [[5,0], [7,0], [5,2], [6,1], [4,4], [7,1]]
 * 
 * Company: Google
 * Difficulty: medium
 * Similar Questions: 315(CountSmallerNumbersAfterSelf)
 */
public class QueueReconstructionByHeight {
    public int[][] reconstructQueue(int[][] people) {
        // sort the people to make them stand from the highest to shortest. 
        // For people with same height, sort them according to the count of 
        // people before them from low to high
        Arrays.sort(people, (a, b) -> (a[0]==b[0] ? a[1]-b[1] : b[0]-a[0]));
        
        int n = people.length;
        List<int[]> list = new ArrayList<>();
        for (int[] p : people) {
            list.add(p[1], p);
        }
 
        int[][] result = new int[n][2];
        for (int i = 0; i < n; i++) {
            result[i][0] = list.get(i)[0];
            result[i][1] = list.get(i)[1];
        }
        return result;
    }

}
