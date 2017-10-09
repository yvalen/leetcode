package leetcode.array;

import java.util.Arrays;
import java.util.List;

/*
 * LEETCODE 624 
 * Given m arrays, and each array is sorted in ascending order. Now you can pick up two integers from two DIFFERENT 
 * arrays (each array picks one) and calculate the distance. We define the distance between two integers a and b to 
 * be their absolute difference |a-b|. Your task is to find the maximum distance.
 * Example 1:
 * Input: 
 * [[1,2,3],
 * 	[4,5],
 * 	[1,2,3]
 * ]
 * Output: 4
 * Explanation: One way to reach the maximum distance 4 is to pick 1 in the first or third array and pick 5 in the second array.
 * Note:
 * - Each given array will have at least 1 number. There will be at least two non-empty arrays.
 * - The total number of the integers in all the m arrays will be in the range of [2, 10000].
 * - The integers in the m arrays will be in the range of [-10000, 10000].
 * 
 * Company: Yahoo
 * Difficulty: easy
 */
public class MaximumDistanceInArrays {
	public int maxDistance(List<List<Integer>> arrays) {
        int min = arrays.get(0).get(0), max = arrays.get(0).get(arrays.get(0).size()-1);
		int result = Integer.MIN_VALUE;
		for (int i = 1; i < arrays.size(); i++) {
			int currentMin = arrays.get(i).get(0), currentMax = arrays.get(i).get(arrays.get(i).size()-1);
			result = Math.max(result, currentMax-min);
			result = Math.max(result, max-currentMin);
			min = Math.min(min, currentMin);
			max = Math.max(max, currentMax);
		}
		return result;
		/*
        int[] firstMin = {-1, 10000}, secondMin = {-1, 10000};
        int[] firstMax = {-1, -10000}, secondMax = {-1, -10000};
        for (int i = 0; i < arrays.size(); i++) {
        	int currentMin = arrays.get(i).get(0), currentMax = arrays.get(i).get(arrays.get(i).size()-1);
        	if (firstMin[1] > currentMin) {
        		secondMin[0] = firstMin[0];
        		secondMin[1] = firstMin[1];
        		firstMin[0] = i;
        		firstMin[1] = currentMin;
        	}
        	else if (secondMin[1] > currentMin) {
        		secondMin[0] = i;
        		secondMin[1] = currentMin;
        	}
        	if (firstMax[1] < currentMax) {
        		secondMax[0] = firstMax[0];
        		secondMax[1] = firstMax[1];
        		firstMax[0] = i;
        		firstMax[1] = currentMax;
        	}
        	else if (secondMax[1] < currentMax) {
        		secondMax[0] = i;
        		secondMax[1] = currentMax;
        	}
        }
        
        if (firstMin[0] != firstMax[0]) return firstMax[1] - firstMin[1];
        
        return Math.max(firstMax[1]-secondMin[1], secondMax[1]-firstMin[1]);
        */
    }
	
	public static void main(String[] args) {
		MaximumDistanceInArrays mda = new MaximumDistanceInArrays ();
		List<List<Integer>> arrays = Arrays.asList(
				Arrays.asList(1, 4),
				Arrays.asList(0, 5) 
		); // expected output should be 4 instead of 5
		System.out.println(mda.maxDistance(arrays));
	}
}
