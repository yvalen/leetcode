package leetcode.binarysearch;

public class FindMinimumInRotatedSortedArray {
    /*
     * LEETCODE 153 
     * Suppose a sorted array is rotated at some pivot unknown to you beforehand. 
     * (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2). 
     * Find the minimum element. You may assume no duplicate exists in the array.
     * 
     * Company: Microsoft 
     * Difficulty: medium 
     * Similar Questions: 33(SearchInRotatedSortedArray), 
     * 154(Find Minimum in Rotated Sorted Array II)
     */
    // Time complexity: O(logn)
    public int findMin(int[] nums) {
        if (nums == null || nums.length == 0)
            return Integer.MIN_VALUE;

        int lo = 0, hi = nums.length - 1;
        // if the sorted array is not rotated, then nums[lo] < nums[hi] 
        // and we can return nums[lo] immediately
        // otherwise for a sorted array that was rotated at least one step,
        // nums[lo] is always greater than nums[hi]
        while (lo < hi && nums[lo] >= nums[hi]) { // don't need to check further
                                                  // if the array is not rotated
            int mid = lo + (hi - lo) / 2; // use lower median
            if (nums[mid] > nums[hi]) {
                // since nums[lo] > nums[hi], each element in the left half is
                // greater than nums[hi]
                // the minimum must be in the right half half is in increasing
                // order
                lo = mid + 1;
            } else {
                // since nums[mid] < nums[hi], elemetns in
                // nums[mid+1]...nums[hi] are all greater than nums[mid]
                // the minimum must be in [lo...mid].
                // cannot use hi=mid-1 since mid could be the minimum of the
                // left, e.g 4,5,6,0,1,2,3
                hi = mid;
            }
        }

        // now lo == hi
        return nums[lo];
    }

    /*
     * LEETCODE 154 
     * Follow up for "Find Minimum in Rotated Sorted Array": What if duplicates 
     * are allowed? Would this affect the run-time complexity? How and why?
     * 
     * Difficulty: hard 
     * Similar Questions: 153(Find Minimum in Rotated Sorted Array)
     */
    // Time complexity: worst case all elements are same, O(n)
    public int findMinWithDuplicate(int[] nums) {
        if (nums == null || nums.length == 0)
            return Integer.MIN_VALUE;

        int lo = 0, hi = nums.length - 1;
        while (lo < hi && nums[lo] >= nums[hi]) {
            int mid = lo + (hi - lo) / 2;
            if (nums[mid] > nums[hi]) {
                lo = mid + 1;
            } else if (nums[mid] < nums[lo]) {
                hi = mid;
            } else { // nums[mid]>=nums[lo], nums[mid]<=nums[hi],
                     // nums[lo]>=nums[hi] => nums[mid]=nums[lo]=nums[hi]
                lo++; // skip duplicate
            }
        }

        return nums[lo];

    }

    public static void main(String[] args) {
        FindMinimumInRotatedSortedArray fmrs = new FindMinimumInRotatedSortedArray();
        // int[] nums = {2, 0, 1,1,1,1,2};
        int[] nums = { 1, 1, 3, 1 };
        System.out.println(fmrs.findMinWithDuplicate(nums));
    }
}
