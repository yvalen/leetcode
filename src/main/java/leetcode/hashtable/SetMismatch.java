package leetcode.hashtable;

import java.util.HashMap;
import java.util.Map;

/*
 * LEETCODE 645
 * The set S originally contains numbers from 1 to n. But unfortunately, due to the data error, one of the numbers in the set 
 * got duplicated to another number in the set, which results in repetition of one number and loss of another number.
 * Given an array nums representing the data status of this set after the error. Your task is to firstly find the number occurs 
 * twice and then find the number that is missing. Return them in the form of an array.
 * Example 1: Input: nums = [1,2,2,4] Output: [2,3]
 * Note:
 * - The given array size will in the range [2, 10000].
 * - The given array's numbers won't have any order.
 * 
 * Company: Amazon
 * Difficulty: easy
 * Similar Questions: 287(FindDuplicateNumber)
 */
public class SetMismatch {
    public int[] findErrorNums_withMap(int[] nums) {
        int[] result = new int[2];
        Map<Integer, Integer> map = new HashMap<>();
        for (Integer num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        for (int i = 1; i <= nums.length; i++) {
            Integer num = map.get(i);
            if (num == null)
                result[1] = i;
            else if (num == 2)
                result[0] = i;
        }

        return result;
    }

    public int[] findErrorNums_withArray(int[] nums) {
        int[] result = new int[2];
        int n = nums.length;
        int[] count = new int[n + 1];
        for (int num : nums) {
            count[num]++;
        }

        for (int i = 1; i <= n; i++) {
            if (count[i] == 2)
                result[0] = i;
            else if (count[i] == 0)
                result[1] = i;
        }

        return result;
    }

    public int[] findErrorNums_noExtraSpace(int[] nums) {
        if (nums == null || nums.length == 0)
            return new int[0];

        int[] result = new int[2];
        for (int num : nums) {
            int index = Math.abs(num) - 1;
            if (nums[index] > 0)
                nums[index] = -nums[index];
            else
                result[0] = index + 1;
        }

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0)
                result[1] = i + 1;
        }

        return result;
    }
}
