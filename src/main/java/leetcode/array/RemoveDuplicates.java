package leetcode.array;

public class RemoveDuplicates {

    /**
     * LEETCODE 26
     * Given a sorted array, remove the duplicates in place such that each
     * element appear only once and return the new length. Do not allocate extra
     * space for another array, you must do this in place with constant memory.
     * For example, given input array nums = [1,1,2], your function should
     * return length = 2, with the first two elements of nums being 1 and 2
     * respectively. It doesn't matter what you leave beyond the new length.
     * 
     * Company: Facebook, Microsoft,Bloomberg
     * Difficulty: easy
     * Similar Questions: 27(RemoveElement)
     */
    public int removeDuplicates(int[] nums) {
        if (nums == null)
            return 0;

        int i = 0;
        for (int j = 1; j < nums.length; j++) {
            if (nums[i] != nums[j]) {
                nums[++i] = nums[j];
            }
        }

        return i + 1;
    }

    /**
     * LEETCODE 80
     * Follow up for "Remove Duplicates": What if duplicates are allowed at most
     * twice? For example, Given sorted array nums = [1,1,1,2,2,3], your
     * function should return length = 5, with the first five elements of nums
     * being 1, 1, 2, 2 and 3. It doesn't matter what you leave beyond the new
     * length.
     * 
     * Company: Facebook
     * Difficulty: medium
     */
    public int removeDuplicates2_withCounter(int[] nums) {
        if (nums == null)
            return 0;
        
        int i = 1, count = 1;
        for (int j = 1; j < nums.length; j++) {
            if (nums[j] != nums[j-1]) {
                count = 1;
                nums[i++] = nums[j];
            }
            else {
                if (count < 2) {
                    nums[i++] = nums[j];
                    count++;
                }
            }
        }
        return i;
    }
    
    public int removeDuplicates2(int[] nums) {
        if (nums == null)
            return 0;

        // i and j points to the 1st non-duplicate element
        int i = 0, j = 0;
        for (int k = 1; k < nums.length; k++) {
            if (nums[j] != nums[k]) {
                nums[++j] = nums[k];
                // now i and j points the next non-duplicate element
                i = j;
            } else if (j == i) {
                // advance j by 1 so that j points to the 2nd non-duplicate
                // element
                // need to set the jth element with kth element
                nums[++j] = nums[k];
            }
        }

        return j + 1;
    }
    
    
}
