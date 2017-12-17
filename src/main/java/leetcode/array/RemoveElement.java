package leetcode.array;

/**
 * Given an array and a value, remove all instances of that value in place and
 * return the new length. Do not allocate extra space for another array, you
 * must do this in place with constant memory. The order of elements can be
 * changed. It doesn't matter what you leave beyond the new length. Example:
 * Given input array nums = [3,2,2,3], val = 3 Your function should return
 * length = 2, with the first two elements of nums being 2.
 */
public class RemoveElement {
    public int removeElement(int[] nums, int val) {
        if (nums == null || nums.length == 0)
            return 0;

        int i = 0;
        while (i < nums.length) {
            if (nums[i] == val) {
                int j = i + 1;
                while (j < nums.length && nums[j] == val) {
                    j++;
                }
                if (j == nums.length)
                    break;
                nums[i] = nums[j];
                nums[j] = val;
            }
            i++;
        }

        return i;
    }

    /**
     * When we encounter nums[i]=valnums[i] = valnums[i]=val, we can swap the
     * current element out with the last element and dispose the last one. This
     * essentially reduces the array's size by 1.
     */
    public int removeElement_swapWithLast(int[] nums, int val) {
        int i = 0, n = nums.length;
        while (i < n) {
            if (nums[i] == val) {
                nums[i] = nums[n - 1];
                n--;
            } else {
                i++;
            }
        }

        return n;
    }

    public int removeElement_copy(int[] nums, int val) {
        int i = 0;
        for (int j = 0; j < nums.length; j++) {
            if (nums[j] != val) {
                nums[i] = nums[j];
                i++;
            }
        }

        return i;
    }

}
