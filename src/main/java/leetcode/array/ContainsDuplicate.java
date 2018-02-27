package leetcode.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class ContainsDuplicate {
    /*
     * LEETCODE 217
     * Given an array of integers, find if the array contains any duplicates.
     * Your function should return true if any value appears at least twice in
     * the array, and it should return false if every element is distinct.
     * 
     * Company: Airbnb, Palantir
     * Difficulty: easy
     * Similar Questons: 219(Contains Duplicate II), 220(Contains Duplicate III)
     */
    public boolean containsDuplicate(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            if (!set.add(num)) return true;
        }

        return false;
    }

    /*
     * LEETCODE 219
     * Given an array of integers and an integer k, find out whether there are
     * two distinct indices i and j in the array such that nums[i] = nums[j] and
     * the difference between i and j is at most k.
     * 
     * Company: Airbnb, Palantir
     * Difficulty: easy
     * Similar Questions: 217(Contains Duplicate), 220(Contains Duplicate III)
     */
    public boolean containsNearbyDuplicate_withMap(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                if (i - map.get(nums[i]) <= k)
                    return true;
            }
            map.put(nums[i], i);
        }
        return false;
    }
    
    // It iterates over the array using a sliding window. The front of the window is at i, 
    // the rear of the window is k steps back. The elements within that window are maintained 
    // using a Set. While adding new element to the set, if add() returns false, it means the 
    // element already exists in the set. At that point, we return true. If the control reaches 
    // out of for loop, it means that inner return true never executed, meaning no such 
    // duplicate element was found.
    public boolean containsNearbyDuplicate_slidingWindow(int[] nums, int k) {
        Set<Integer> Set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (i > k) Set.remove(nums[i-k-1]);
            if (!Set.add(nums[i])) return true;
        }
        return false;
    }

    /*
     * LEETCODE 220
     * Given an array of integers, find out whether there are two distinct
     * indices i and j in the array such that the difference between nums[i] and
     * nums[j] is at most t and the difference between i and j is at most k.
     * 
     * Company: Airbnb, Palantir
     * Difficulty: easy
     * Similar Questions: 217(Contains Duplicate), 219(Contains Duplicate II)
     */
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        if (nums == null || nums.length == 0 || k <= 0)
            return false;
        // use long to prevent overflow when element in nums 
        // is Integer.MAX_VALUE or Integer.MIN_VALUE
        TreeSet<Long> treeSet = new TreeSet<>();
        for (int i = 0; i < nums.length; i++) {
            Long val = Long.valueOf(nums[i]);

            // floor returns the greatest element less than or equal to the
            // given element
            // value - nums[i] <= t => value <= nums[i] + t
            // value needs to be greater than or equal to nums[i]
            Long floor = treeSet.floor(val + t);

            // ceiling returns the least element greater than or equal to the
            // given element
            // nums[i] - value <= t => value >= nums[i] - t
            // value needs to be less than or equal to nums[i]
            Long ceiling = treeSet.ceiling(val - t);

            // need to compare floor and ceiling against nums[i] to make sure
            // floor is greater than nums[i] or ceiling is less than nums[i]
            if ((floor != null && floor >= val) || (ceiling != null && ceiling <= val)) {
                // find an element before index i
                return true;
            }

            treeSet.add(Long.valueOf(nums[i]));

            // remove the element whose index is out of range
            if (i >= k) {
                treeSet.remove(Long.valueOf(nums[i - k]));
            }
        }

        return false;
    }

    public static void main(String[] args) {
        ContainsDuplicate c = new ContainsDuplicate();

        int[] nums = { -3, 3 };
        boolean result = c.containsNearbyAlmostDuplicate(nums, 2, 4);
        System.out.println(result);
    }

}
