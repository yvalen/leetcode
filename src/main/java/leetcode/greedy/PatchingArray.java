package leetcode.greedy;

/*
 * Given a sorted positive integer array nums and an integer n, add/patch elements to the array such that any number in range [1, n] 
 * inclusive can be formed by the sum of some elements in the array. Return the minimum number of patches required.
 * Example 1: nums = [1, 3], n = 6, return 1.
 * Combinations of nums are [1], [3], [1,3], which form possible sums of: 1, 3, 4.
 * Now if we add/patch 2 to nums, the combinations are: [1], [2], [3], [1,3], [2,3], [1,2,3].
 * Possible sums are 1, 2, 3, 4, 5, 6, which now covers the range [1, 6]. So we only need 1 patch.
 * Example 2: nums = [1, 5, 10], n = 20, return 2. The two patches can be [2, 4].
 * Example 3: nums = [1, 2, 2], n = 5, return 0.
 * 
 * Company: Google
 * Difficulty: hard
 */
public class PatchingArray {
    public int minPatches(int[] nums, int n) {
        int patches = 0, i = 0;
        long miss = 1; // use long to prevent overflow
        while (miss <= n) {
            if (i < nums.length && nums[i] <= miss) {
                miss += nums[i++];
            } else {
                miss += miss;
                patches++;
            }
        }
        return patches;
    }

    public static void main(String[] args) {
        PatchingArray pa = new PatchingArray();
        int[] nums = { 1, 2, 31, 33 };
        int n = 2147483647;
        System.out.println(pa.minPatches(nums, n));
    }
}
