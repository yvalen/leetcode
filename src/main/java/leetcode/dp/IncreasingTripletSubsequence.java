package leetcode.dp;

import java.util.ArrayList;
import java.util.List;

/*
 * LEETCODE 334
 * Given an unsorted array return whether an increasing subsequence of length 3 
 * exists or not in the array. Formally the function should: Return true if there 
 * exists i, j, k such that arr[i] < arr[j] < arr[k] given 0 ≤ i < j < k ≤ n-1 
 * else return false. 
 * Your algorithm should run in O(n) time complexity and O(1) space complexity.
 * Examples: 
 * Given [1, 2, 3, 4, 5], return true. 
 * Given [5, 4, 3, 2, 1], return false. 
 */
public class IncreasingTripletSubsequence {
    public boolean increasingTriplet_withList(int[] nums) {
        if (nums == null || nums.length < 3)
            return false;

        List<Integer> triplet = new ArrayList<>(3);
        for (int num : nums) {
            if (triplet.isEmpty() || num > triplet.get(triplet.size() - 1)) {
                triplet.add(num);
                if (triplet.size() == 3)
                    return true;
            }
            if (num < triplet.get(0))
                triplet.set(0, num);
            else
                triplet.set(1, num);
        }
        return false;
    }

    public boolean increasingTriplet(int[] nums) {
        if (nums == null || nums.length < 3)
            return false;

        int t1 = Integer.MAX_VALUE, t2 = Integer.MAX_VALUE;
        for (int num : nums) {
            if (num <= t1)
                t1 = num;
            else if (num <= t2)
                t2 = num;
            else
                return true;
        }
        return false;
    }

}
