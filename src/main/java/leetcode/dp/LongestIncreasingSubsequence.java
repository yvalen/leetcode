package leetcode.dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/*
 * LEETCODE 300
 * Given an unsorted array of integers, find the length of longest 
 * increasing subsequence.
 * For example, given [10, 9, 2, 5, 3, 7, 101, 18], the longest 
 * increasing subsequence is [2, 3, 7, 101], therefore the length is 4. 
 * Note that there may be more than one LIS combination, it is only 
 * necessary for you to return the length. Your algorithm should run in 
 * O(n^2) complexity.
 * Follow up: Could you improve it to O(n log n) time complexity? 
 * 
 * Company: Microsoft
 * Difficulty: medium
 */
public class LongestIncreasingSubsequence {
    // Time complexity: O(n^2) Space complexity: O(n)
    // dp[i] represents the length of the longest increasing subsequence possible 
    // considering the array elements up to the i​th​​ index only ,by necessarily 
    // including the i​th​​ element. In order to find out dp[i], we need to try to 
    // append the current element(nums[i]) in every possible increasing subsequences 
    // up to the (i−1)th​​ index(including the (i−1)th index), such that the new sequence 
    // formed by adding the current element is also an increasing subsequence. Thus, we 
    // can easily determine dp[i]dp[i]dp[i] using: dp[i]=max(dp[j])+1, 0≤j<i.
    // At the end, the maximum out of all the dp[i]dp[i]dp[i]'s to determine the final result.
    // LISlength=max(dp[i]),∀0≤i<n
    public int lengthOfLIS(int[] nums) {
        if (nums == null)
            return 0;

        int n = nums.length;
        if (n <= 1)
            return n;
        int[] dp = new int[n];
        int maxLen = 1;
        for (int i = 0; i < n; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            maxLen = Math.max(maxLen, dp[i]);
        }

        return maxLen;
    }

    // Complexity: O(nlogn)
    // 1. traverse from 0 to len-1, the DP array keep the longest sequence.
    // 2. if val is bigger than largest in the dp array (last element), add it
    // to the end;
    // 3. if it is among the sequence, return the pos of the smallest element
    // which is bigger than val
    // 4. update the array with value at this position
    public int lengthOfLIS_binarySearchWithList(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;

        int n = nums.length;
        List<Integer> dp = new ArrayList<>(n);
        dp.add(nums[0]);
        for (int i = 1; i < n; i++) {
            if (nums[i] > dp.get(dp.size() - 1)) {
                // add element to list if it greater than the last element
                dp.add(nums[i]);
            } else {
                int pos = Collections.binarySearch(dp, nums[i]);
                if (pos < 0)
                    dp.set(-(pos + 1), nums[i]);
            }
        }

        return dp.size();
    }

    public int lengthOfLIS_binarySearchWithArray(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;

        // dp[i] store the increasing subsequence formed by including ith element
        int[] dp = new int[nums.length];
        int len = 0;
        for (int num : nums) {
            // Searches a range of the specified array of ints for the specified
            // value using the binary search algorithm.
            // return index of the search key, if it is contained in the array
            // within the specified range;
            // otherwise, (-(insertion point) - 1). The insertion point is
            // defined as the point at which the key would be
            // inserted into the array: the index of the first element in the
            // range greater than the key, or toIndex if all
            // elements in the range are less than the specified key.
            int pos = Arrays.binarySearch(dp, 0, len, num);
            if (pos < 0)
                pos = -(pos + 1);
            dp[pos] = num;
            if (pos == len)
                len++; // increment length if the element is greater than the
                       // last element
        }

        return len;
    }

    // source code for Arrays.binarySearch
    private static int binarySearch0(int[] a, int fromIndex, int toIndex, int key) {
        int low = fromIndex;
        int high = toIndex - 1;

        while (low <= high) {
            // unsigned shift, equivalent to low+(high-low)/2
            int mid = (low + high) >>> 1;
            int midVal = a[mid];

            if (midVal < key)
                low = mid + 1;
            else if (midVal > key)
                high = mid - 1;
            else
                return mid; // key found
        }
        return -(low + 1); // key not found.
    }

    public static void main(String[] args) {
        LongestIncreasingSubsequence l = new LongestIncreasingSubsequence();
        int[] nums = { 10, 9, 2, 5, 3, 7, 101, 18 };

        System.out.println(l.lengthOfLIS_binarySearchWithArray(nums));
    }

}
