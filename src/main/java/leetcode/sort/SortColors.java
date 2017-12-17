package leetcode.sort;

import java.util.stream.IntStream;

/*
 * Given an array with n objects colored red, white or blue, sort them so that objects of the same color are adjacent, 
 * with the colors in the order red, white and blue. Here, we will use the integers 0, 1, and 2 to represent the color 
 * red, white, and blue respectively.
 * Note:You are not suppose to use the library's sort function for this problem. 
 * 
 * Company: Pocket Gems, Microsoft, Facebook
 * Difficulty: Medium
 */
public class SortColors {
    // The solution requires the use of tracking 3 positions, the Low, Mid and
    // High.
    // We assume that the mid is the "Unknown" area that we must evaluate.
    // If we encounter a 0, we know that it will be on the low end of the array,
    // and if we encounter a 2, we know it will be on the high end of the array.
    // Example
    // 1 0 2 2 1 0
    // ^ ^
    // L H
    // M
    // Mid != 0 || 2
    // Mid++
    //
    // 1 0 2 2 1 0
    // ^ ^ ^
    // L M H
    // Mid == 0
    // Swap Low and Mid
    // Mid++
    // Low++
    // 0 1 2 2 1 0
    // ^ ^ ^
    // L M H
    // Mid == 2
    // Swap High and Mid
    // High--
    //
    // 0 1 0 2 1 2
    // ^ ^ ^
    // L M H
    // Mid == 0
    // Swap Low and Mid
    // Mid++
    // Low++
    //
    // 0 0 1 2 1 2
    // ^ ^ ^
    // L M H
    // Mid == 2
    // Swap High and Mid
    // High--
    //
    // 0 0 1 1 2 2
    // ^ ^
    // L M
    // H
    // Mid <= High is our exit case
    public void sortColors_withThreePointers(int[] nums) {
        if (nums == null || nums.length == 0)
            return;

        int lo = 0, mid = 0, hi = nums.length - 1;
        while (mid <= hi) {
            if (nums[mid] == 0) {
                // exch(nums, lo, mid); // no need to do swap because we know
                // the value to put at lo
                nums[mid] = nums[lo];
                nums[lo] = 0;
                mid++;
                lo++;
            } else if (nums[mid] == 2) {
                // exch(nums, mid, hi);
                // mid++; // cannot increment mid here because we need to
                // process the element that has just been swapped here
                nums[mid] = nums[hi];
                nums[hi] = 2;
                hi--;
            } else {
                mid++;
            }
        }
    }

    private void exch(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    public static void main(String[] args) {
        SortColors sc = new SortColors();
        // int[] nums = {1, 1};
        // int[] nums = {1, 0};
        int[] nums = { 1, 2, 0 };
        sc.sortColors_withThreePointers(nums);
        IntStream.of(nums).forEach(System.out::println);
    }

}
