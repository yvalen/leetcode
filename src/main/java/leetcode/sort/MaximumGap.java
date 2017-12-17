package leetcode.sort;

import java.util.Arrays;

/*
 * Given an unsorted array, find the maximum difference between the successive elements in its sorted form.
 * Try to solve it in linear time/space. Return 0 if the array contains less than 2 elements.
 * You may assume all elements in the array are non-negative integers and fit in the 32-bit signed integer range.
 */
public class MaximumGap {
    /*
     * Algorithm: - We choose a bucket size b such that 1<b≤(max−min)/(n−1).
     * Let's just choose b=(max−min)/(n−1). - Thus all the n elements would be
     * divided among k=(max−min)/b buckets. - Hence the i​th​​ bucket would hold
     * the range of values: [min+(i−1)∗b, min+i∗b) (1-based indexing). - The
     * index of the bucket to which a particular element belongs is given by
     * (num−min)/b (0-based indexing) where num is the element in question. -
     * Once all n elements have been distributed, we compare k−1 adjacent bucket
     * pairs to find the maximum gap.
     */
    // Time: O(n+b) -> O(n), b is the number of buckets
    // Space: O(2b) -> O(b)
    public int maximumGap_withBucketSort(int[] nums) {
        if (nums == null || nums.length < 2)
            return 0;

        int n = nums.length;

        // find the maximum and minimum element in the input
        int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
        for (int num : nums) {
            max = Integer.max(max, num);
            min = Integer.min(min, num);
        }

        int bucketSize = Integer.max(1, (max - min) / (n - 1)); // n-1 gaps
                                                                // between n
                                                                // elements
        int numOfBuckets = (max - min) / bucketSize + 1;

        // two arrays that store the min and max values for each bucket
        int[] minValues = new int[numOfBuckets];
        int[] maxValues = new int[numOfBuckets];
        Arrays.fill(minValues, Integer.MAX_VALUE);
        Arrays.fill(maxValues, Integer.MIN_VALUE);

        // update min and max value for each bucket
        for (int num : nums) {
            int bucketIdx = (num - min) / bucketSize;
            minValues[bucketIdx] = Integer.min(minValues[bucketIdx], num);
            maxValues[bucketIdx] = Integer.max(maxValues[bucketIdx], num);
        }

        int maxGap = 0, prevMax = maxValues[0];
        for (int i = 1; i < numOfBuckets; i++) {
            if (minValues[i] == Integer.MAX_VALUE) {
                // bucket is empty, skip it
                continue;
            }
            maxGap = Integer.max(maxGap, minValues[i] - prevMax);
            prevMax = maxValues[i]; // update prevMax
        }

        return maxGap;
    }

    // TODO
    public int maximumGap_withRadixSort(int[] nums) {
        if (nums == null || nums.length < 2)
            return 0;

        int n = nums.length;

        // find the maximum element in the input
        int max = Integer.MIN_VALUE;
        for (int num : nums) {
            max = Integer.max(max, num);
        }

        int maxGap = 0;

        return maxGap;
    }

}
