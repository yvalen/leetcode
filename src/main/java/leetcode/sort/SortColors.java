package leetcode.sort;

import java.util.stream.IntStream;

/*
 * LEETCODE 75
 * Given an array with n objects colored red, white or blue, sort them so that 
 * objects of the same color are adjacent, with the colors in the order red, 
 * white and blue. Here, we will use the integers 0, 1, and 2 to represent the 
 * color red, white, and blue respectively.
 * Note:You are not suppose to use the library's sort function for this problem. 
 * 
 * Follow up: A rather straight forward solution is a two-pass algorithm using 
 * counting sort. First, iterate the array counting number of 0's, 1's, and 2's, 
 * then overwrite array with total number of 0's, then 1's and followed by 2's.
 * Could you come up with an one-pass algorithm using only constant space?
 * 
 * Company: Pocket Gems, Microsoft, Facebook
 * Difficulty: Medium
 * Similar Questions: 280(Wiggle Sort), 324(Wiggle Sort II)
 */
public class SortColors {
    
    /*
    // The solution requires the use of tracking 3 positions, the Low, Mid and High.
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
                // no need to do swap because we know the value to put at lo
                nums[mid] = nums[lo];
                nums[lo] = 0;
                mid++;
                lo++;
            } else if (nums[mid] == 2) {
                // cannot increment mid here because we need to
                // process the element that has just been swapped here
                nums[mid] = nums[hi];
                nums[hi] = 2;
                hi--;
            } else {
                mid++;
            }
        }
    }
    */
    
    // sweep all 0s to the left and all 2s to the right, then all 1s are left in the middle.
    // this algorithm is bounded by O(2n), meaning that at most each element will be seen 
    // and operated twice (in the case of all 0s). 
    public void sortColors(int[] nums) {
        int lo = 0, hi = nums.length-1;
        for (int i = 0; i <= hi; i++) {
            while (nums[i] == 2 && i < hi) { // use while loop here
                exch(nums, i, hi);
                hi--;
            }
            while (nums[i] == 0 && i > lo) {
                exch(nums, i, lo);
                lo++;
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
        sc.sortColors(nums);
        IntStream.of(nums).forEach(System.out::println);
    }

}
