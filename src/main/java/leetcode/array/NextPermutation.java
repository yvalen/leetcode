package leetcode.array;

/*
 * LEETCODE 31
 * Implement next permutation, which rearranges numbers into the lexicographically 
 * next greater permutation of numbers. If such arrangement is not possible, it must 
 * rearrange it as the lowest possible order (ie, sorted in ascending order). The 
 * replacement must be in-place, do not allocate extra memory. Here are some examples. 
 * Inputs are in the left-hand column and its corresponding outputs are in the right-hand column.
 * 1,2,3 → 1,3,2
 * 3,2,1 → 1,2,3
 * 1,1,5 → 1,5,1
 * 
 * https://www.nayuki.io/page/next-lexicographical-permutation-algorithm
 * https://en.wikipedia.org/wiki/Permutation#Generation_in_lexicographic_order
 * 
 * Follow up: previous permutation
 * - find the longest non-decreasing suffix, index of the head of the suffix is i 
 * - find the rightmost element that is less than nums[i-1], j
 * - swap nums[i-1] with nums[j-1]
 * - reverse the suffix
 * 
 * Company: Google
 * Difficulty: medium
 * Similar Questions: 46(Permutations), 47(Permutations II), 60(PermutationSequence), 
 * 267(PalindromePermutationII)
 */
public class NextPermutation {
    // Time complexity: O(n)
    public void nextPermutation(int[] nums) {
        if (nums == null || nums.length == 0)
            return;

        // Find largest index i such that array[i − 1] < array[i],the longest non-increasing 
        // suffix. This suffix is already the highest permutation, so we can’t make a next 
        // permutation just by modifying it – we need to modify some element(s) to the left 
        // of it. (Note that we can identify this suffix in O(n) time by scanning the sequence 
        // from right to left. Also note that such a suffix has at least one element, because 
        // a single element substring is trivially non-increasing.)
        int i = nums.length - 1;
        while (i > 0 && nums[i] <= nums[i - 1]) {
            i--;
        }

        // current sequence is the largest permutation,i.e. the entire sequence is non-increasing.
        // reverse it to get the lowest order permutation
        if (i == 0) {
            reverse(nums, 0, nums.length - 1);
            return;
        }

        // Find largest index j such that j ≥ i and array[j] > array[i − 1]
        int j = nums.length - 1;
        // don't do j> i here as it can be ith element
        // need to find the smallest elemnt in suffix that is GREATER than nums[i-1]
        while (nums[j] <= nums[i - 1]) { 
            j--;
        }

        // Swap array[j] and array[i − 1].
        swap(nums, i - 1, j);

        // Reverse the suffix starting at array[i].
        reverse(nums, i, nums.length - 1);
    }

    private void reverse(int[] nums, int start, int end) {
        while (start < end) {
            swap(nums, start, end);
            start++;
            end--;
        }
    }

    private void swap(int[] nums, int idx1, int idx2) {
        int temp = nums[idx1];
        nums[idx1] = nums[idx2];
        nums[idx2] = temp;
    }

    public static void main(String[] args) {
        NextPermutation np = new NextPermutation();
        // int[] nums = {1, 1};
        //int[] nums = { 1, 2 };
        int[] nums = { 1, 5, 1 };
        np.nextPermutation(nums);
    }
}
