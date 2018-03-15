package leetcode.binarysearch;

import java.util.Arrays;

/**
 * LEETCODE 35 
 * Given a sorted array and a target value, return the index if the target is found. 
 * If not, return the index where it would be if it were inserted in order. You may 
 * assume no duplicates in the array. Here are few examples:
 * [1,3,5,6], 5 → 2 
 * [1,3,5,6], 2 → 1 
 * [1,3,5,6], 7 → 4 
 * [1,3,5,6], 0 → 0
 * 
 * Difficulty: easy
 * Similar Questions: 278(FirstBadVersion)
 */
public class SearchInsertPosition {

    public int searchInsert(int[] nums, int target) {
        if (nums == null || nums.length == 0)
            return 0;
        if (nums[0] >= target)
            return 0;
        if (nums[nums.length - 1] < target)
            return nums.length;
        int start = 0, end = nums.length - 1;
        while (start < end) { // we can use < here because we have the boundary
                              // check before this. otherwise we should use <=
                              // to handle single element in nums
            int mid = start + (end - start) / 2;
            if (nums[mid] == target)
                return mid;
            if (nums[mid] < target)
                start = mid + 1;
            else
                end = mid - 1;
            System.out.println("mid=" + mid + " start=" + start);
        }
        return start;
        /*
         * if (target > nums[nums.length - 1]) return nums.length;
         * 
         * if (target < nums[0]) return 0;
         * 
         * int low = 0, high = nums.length - 1; while (low < high) { int mid =
         * (low + high) >>> 1;
         * 
         * if (nums[mid] == target) return mid;
         * 
         * if (target < nums[mid]) high = mid - 1; else low = mid + 1; }
         * 
         * return low;
         */
    }

    // check boundary before each binary search
    public int searchInsert_faster(int[] nums, int target) {
        if (target > nums[nums.length - 1])
            return nums.length;

        if (target < nums[0])
            return 0;

        int low = 0, high = nums.length - 1;
        while (low <= high) {
            int mid = (low + high) / 2;

            if (nums[mid] == target)
                return mid;

            if (target < nums[mid]) {
                high = mid - 1;
                if (nums[high] < target)
                    return high + 1;
            } else {
                low = mid + 1;
                if (nums[low] > target)
                    return low;
            }
        }

        return low;
    }

    // return the index of the first element which is greater or equal to target
    public int lowerbound(int[] nums, int target) {
        int start = 0, end = nums.length - 1;

        if (nums[start] >= target)
            return start;
        if (nums[end] < target)
            return end;
        while (start < end) {
            int mid = (start + end) >>> 1;
            if (nums[mid] < target)
                start = mid + 1;
            else
                end = mid;
        }
        return start;
    }

    public static void main(String[] arg) {
        SearchInsertPosition s = new SearchInsertPosition();

        // int[] nums = {1, 3};

        // int result = s.searchInsert(nums, 4);
        // System.out.println(result);

        // int[] nums = {2,2,2,2,3};
        // int target = 2;
        int[] nums = { 1 };
        int target = 2;
        // System.out.println(Arrays.binarySearch(nums, target));
        System.out.println(s.searchInsert(nums, target));
    }

}
