package leetcode.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * LEETCODE 163 
 * Given a sorted integer array where the range of elements are in the 
 * inclusive range [lower, upper], return its missing ranges.
 * For example, given [0, 1, 3, 50, 75], lower = 0 and upper = 99, 
 * return ["2", "4->49", "51->74", "76->99"]. 
 * 
 * Company: Google
 * Difficulty: medium
 * Similar Questions: 228(SummaryRanges)
 */
public class MissingRange {

    public List<String> findMissingRanges_long(int[] nums, int lower, int upper) {
        List<String> result = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        if (nums == null || nums.length == 0) {
            sb.append(lower);
            if (upper > lower)
                sb.append("->").append(upper);
            result.add(sb.toString());
            return result;
        }

        if (lower < nums[0]) {
            sb.append(lower);
            if (nums[0] > lower + 1)
                sb.append("->").append(nums[0] - 1);
            result.add(sb.toString());
            sb.setLength(0);
        }

        long lowerLong = nums[0];
        for (int i = 1; i < nums.length; i++) {
            long numLong = nums[i];
            if (numLong == lowerLong + 2) {
                result.add(String.valueOf(lowerLong + 1));
            } else if (numLong > lowerLong + 2) {
                sb.append(lowerLong + 1).append("->").append(nums[i] - 1);
                result.add(sb.toString());
                sb.setLength(0);
            }
            lowerLong = numLong;
        }

        if (nums[nums.length - 1] < upper) {
            if (upper > nums[nums.length - 1] + 1)
                sb.append(nums[nums.length - 1] + 1).append("->");
            sb.append(upper);
            result.add(sb.toString());
        }

        return result;
    }

    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
        List<String> result = new ArrayList<>();
        for (int num : nums) {
            if (num == Integer.MIN_VALUE) { // handle lower bound overflow
                lower = num + 1;
                continue;
            }
            if (lower == num - 1) {
                result.add(String.valueOf(lower));
            }
            else if (lower < num - 1) {
                result.add(String.valueOf(lower) + "->" + String.valueOf(num - 1));
            }
            // need to check for Integer.MAX_VALUE after checking lower, to handle case
            // [2147483647] 0 2147483647
            if (num == Integer.MAX_VALUE) {
                return result; // result when reaching max value
            }
            lower = num + 1; // make lower bigger than num
        }

        if (lower == upper)
            result.add(String.valueOf(lower));
        else if (lower < upper)
            result.add(String.valueOf(lower) + "->" + String.valueOf(upper));
        return result;
    }

    public static void main(String[] args) {
        MissingRange mr = new MissingRange();
        // int[] nums = {0, 1, 3, 50, 75};
        // int lower = 0, upper = 99;
        // int[] nums = {};
        // int lower = 1, upper = 1;
        // int[] nums = {-1};
        // int lower = -2, upper = -1;
        // int lower = -1, upper = 0;
        // int[] nums = {2};
        // int lower = 0, upper = 9;
        int[] nums = { -2147483648, -2147483648, 0, 2147483647, 2147483647 };
        int lower = -2147483648, upper = 2147483647;
        // int[] nums = {-1};
        // int lower = -1, upper = -1;
        System.out.println(mr.findMissingRanges(nums, lower, upper));
    }

}
