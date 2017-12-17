package leetcode.array;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/*
 * LEETCODE 442
 * Given an array of integers, 1 ≤ a[i] ≤ n (n = size of array), some elements appear twice and others appear once.
 * Find all the elements that appear twice in this array. Could you do it without extra space and in O(n) runtime?
 * Example: 
 * 	Input:[4,3,2,7,8,2,3,1]
 * 	Output:[2,3]
 * 
 * Company: Pocket Gem
 * Difficulty: medium
 * Similar Questions: 448(FindAllDisappearedNumbersInArray)
 */
public class FindAllDuplicatesInArray {
    public List<Integer> findDuplicates(int[] nums) {
        if (nums == null || nums.length == 0)
            return Collections.emptyList();

        List<Integer> result = new LinkedList<>();
        for (int num : nums) {
            // when find a number i, flip the number at position i-1 to
            // negative.
            // if the number at position i-1 is already negative, i is the
            // number that occurs twice.
            int idx = Math.abs(num) - 1;
            if (nums[idx] > 0) {
                nums[idx] = -nums[idx];
            } else {
                result.add(idx + 1);
            }
        }

        return result;
    }

}
