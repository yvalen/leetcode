package leetcode.binarysearch;

/**
 * Given a sorted array of integers, find the starting and ending position of a
 * given target value. Your algorithm's runtime complexity must be in the order
 * of O(log n). If the target is not found in the array, return [-1, -1]. For
 * example, given [5, 7, 7, 8, 8, 10] and target value 8, return [3, 4].
 */
public class SearchRange {
    public int[] searchRange(int[] nums, int target) {
        int[] result = { -1, -1 };

        int low = 0, high = nums.length - 1, idx0 = -1, idx1 = -1;
        while (low <= high) {
            int mid = (low + high) / 2;

            if (nums[mid] == target) {
                idx0 = mid;
                idx1 = mid;
                while (idx0 > 0 && nums[idx0 - 1] == target)
                    idx0--;
                while (idx1 < nums.length - 1 && nums[idx1 + 1] == target)
                    idx1++;

                result[0] = idx0;
                result[1] = idx1;
                return result;
            } else if (target > nums[mid])
                low = mid + 1;
            else
                high = mid - 1;
        }

        return result;

    }

    // https://discuss.leetcode.com/topic/16486/9-11-lines-o-log-n?show=51947
    // https://discuss.leetcode.com/topic/5891/clean-iterative-solution-with-two-binary-searches-with-explanation
    public int[] searchRange_twoBinarySearch(int[] nums, int target) {
        int[] result = { -1, -1 };
        int low = 0, high = nums.length - 1;

        // find the first occurrence of target
        while (low < high) {
            int mid = (low + high) / 2;
            if (nums[mid] >= target)
                high = mid;
            else
                low = mid + 1;
        }

        if (nums[low] != target)
            return result;

        result[0] = low;

        // reset high
        high = nums.length - 1;

        // find the first occurrence of target + 1
        while (low < high) {
            int mid = (low + high) / 2;
            if (nums[mid] >= target + 1)
                high = mid;
            else
                low = mid + 1;
        }
        result[1] = (nums[low] == target) ? low : low - 1;

        return result;

        //return new int[] {findPosition(nums, target, true), findPosition(nums, target, false)};
    }
    
    private int findPosition(int[] nums, int target, boolean first) {
        int pos = -1;
        int lo = 0, hi = nums.length-1;
        while (lo <= hi) {
            int mid = lo + (hi-lo)/2;
            if (nums[mid] == target) {
                pos = mid;
                if (first) hi = mid-1;
                else lo = mid+1;
            }
            else if (nums[mid] < target) lo = mid + 1;
            else hi = mid - 1;
        }
        return pos;
    }

    public int[] searchRange_divideAndConquer(int[] nums, int target) {
        return searchRangeHelper(nums, target, 0, nums.length - 1);
    }

    private int[] searchRangeHelper(int[] nums, int target, int low, int high) {
        if (nums[low] > target || nums[high] < target) {
            return new int[] { -1, -1 };
        }

        if (nums[low] == target && nums[high] == target) {
            return new int[] { low, high };
        }

        int mid = (low + high) / 2;
        int[] left = searchRangeHelper(nums, target, low, mid);
        int[] right = searchRangeHelper(nums, target, mid + 1, high);

        if (left[0] == -1)
            return right;
        if (right[0] == -1)
            return left;
        return new int[] { left[0], right[1] };
    }

    public static void main(String[] args) {
        SearchRange r = new SearchRange();

        int[] nums = { 1, 3 };
        int[] result = r.searchRange_twoBinarySearch(nums, 1);

        System.out.print("{" + result[0] + "," + result[1] + "}");

    }
}
