package leetcode.array;

import java.util.PriorityQueue;
import java.util.Random;

/*
 * LEETCODE 215
 * Find the kth largest element in an unsorted array. Note that it is the kth largest 
 * element in the sorted order, not the kth distinct element. 
 * For example, given [3,2,1,5,6,4] and k = 2, return 5.
 * Note: You may assume k is always valid, 1 ≤ k ≤ array's length.
 * 
 * Company: Facebook, Amazon, Microsoft, Apple, Bloomberg, Pocket Gem
 * Difficulty: medium
 * Similar Questions: 347(TopKFrequentElements)
 */
public class KthLargestElement {

    // O(nlogk) run time, O(k) space
    public int findKthLargest_withPriorityQueue(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k < 1 || k > nums.length) {
            throw new IllegalArgumentException("invalid input");
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>(k);
        for (int num : nums) {
            if (pq.size() < k) {
                pq.offer(num);
            }
            else if (pq.peek() < num) {
                pq.poll();
                pq.offer(num);
            }
        }

        return pq.peek();
    }

    // O(n) quick select algorithm
    // Space complexity: O(1)
    public int findKthLargest(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k < 1 || k > nums.length) {
            throw new IllegalArgumentException("invalid input");
        }

        shuffle(nums);
        int lo = 0, hi = nums.length - 1;
        k = nums.length - k;
        while (lo < hi) {
            int p = partition(nums, lo, hi);
            if (p < k) {
                lo = p + 1;
            } else if (p > k) {
                hi = p - 1;
            } else {
                break;
            }
        }
        return nums[k];
    }

    private int partition(int[] nums, int lo, int hi) {
        int i = lo, j = hi + 1;
        while (true) {
            while (i < hi && nums[++i] < nums[lo])
                ;
            while (j > lo && nums[lo] < nums[--j])
                ;
            ;
            if (i >= j)
                break;
            exch(nums, i, j);
        }
        exch(nums, lo, j);
        return j;
    }

    private void shuffle(int[] nums) {
        Random random = new Random();
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            int j = i + random.nextInt(len - i);
            exch(nums, i, j);
        }
    }

    private void exch(int[] nums, int a, int b) {
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }

    public static void main(String[] args) {
        KthLargestElement k = new KthLargestElement();

        int[] nums = { 1 };
        System.out.println(k.findKthLargest(nums, 1));

    }

}
