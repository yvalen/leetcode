package leetcode.array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*
 * LEETCODE 532
 * Given an array of integers and an integer k, you need to find the 
 * number of unique k-diff pairs in the array. Here a k-diff pair is 
 * defined as an integer pair (i, j), where i and j are both numbers 
 * in the array and their absolute difference is k.
 * Example 1: Input: [3, 1, 4, 1, 5], k = 2 Output: 2
 * Explanation: There are two 2-diff pairs in the array, (1, 3) and (3, 5).
 * Although we have two 1s in the input, we should only return the 
 * number of unique pairs.
 * Example 2: Input:[1, 2, 3, 4, 5], k = 1 Output: 4
 * Explanation: There are four 1-diff pairs in the array, 
 * (1, 2), (2, 3), (3, 4) and (4, 5).
 * Example 3:
 * Input: [1, 3, 1, 5, 4], k = 0 Output: 1
 * Explanation: There is one 0-diff pair in the array, (1, 1).
 * Note:
 * - The pairs (i, j) and (j, i) count as the same pair.
 * - The length of the array won't exceed 10,000.
 * - All the integers in the given input belong to the range: [-1e7, 1e7].
 * An variant of 2sum
 * 
 * Company: Amazon
 * Difficulty: easy
 * Similar Questions: 530(MinimumAbsoluteDifferenceInBST)
 */
public class KDiffPairsInArray {
    public int findPairs_twoPointers(int[] nums, int k) {
        if (nums == null || nums.length <= 1)
            return 0;
        Arrays.sort(nums);
        int count = 0;

        for (int i = 0, j = 1; i < nums.length-1 && j < nums.length;) {
        		int diff = nums[j]- nums[i];
        		if ((i > 0 && nums[i] == nums[i-1]) || diff > k) {
        			i++;
        		}
        		else if (diff < k || j <= i) j++;
        		else {
        			count++;
        			i++;
        		}
        }

        return count;
    }

    public int findPairs_withMap(int[] nums, int k) {
        if (nums == null || nums.length <= 1)
            return 0;
        if (k < 0)
            return 0; // absolute value is always non-negative.
        int count = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (k == 0) {
                // count how many elements in the array that appear more than
                // twice.
                if (entry.getValue() >= 2)
                    count++;
            } else {
                // k is absolute difference, which means k >= 0. We want to find
                // pair (a,b),
                // which satisfies a-b=diff. This implies a>=b, and a=k+b,
                // b=a-k.
                if (map.containsKey(entry.getKey() + k))
                    count++;
            }
        }

        return count;
    }
}
