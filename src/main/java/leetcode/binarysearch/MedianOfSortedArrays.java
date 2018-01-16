package leetcode.binarysearch;

/*
 * LEETCODE 4
 * There are two sorted arrays nums1 and nums2 of size m and n respectively.
 * Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).
 * Example 1: nums1 = [1, 3], nums2 = [2], the median is 2.0
 * Example 2: nums1 = [1, 2], nums2 = [3, 4],the median is (2 + 3)/2 = 2.5
 * 
 * Company: Google, Microsoft, Apple, Zenefits, Yahoo, Adobe, Dropbox
 * Difficulty: hard
 */
public class MedianOfSortedArrays {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length) return findMedianSortedArrays(nums2, nums1);
        
        int m = nums1.length, n = nums2.length;
        int lo = 0, hi = m;
        while (lo <= hi) {
            int partitionM = (lo+hi) / 2;
            // plus one to ensure left has one more element for odd length
            int partitionN = (m+n+1)/2 - partitionM;  
            
            // partitionM is zero means no element on the left, use -INF
            int maxLeftM = (partitionM == 0) ? Integer.MIN_VALUE : nums1[partitionM-1];
            // partitionM is the length of nums1 means no element on the right, use INF
            int minRightM = (partitionM == m) ? Integer.MAX_VALUE : nums1[partitionM];
            
            int maxLeftN = (partitionN == 0) ? Integer.MIN_VALUE : nums2[partitionN-1];
            int minRightN = (partitionN == n) ? Integer.MAX_VALUE : nums2[partitionN];
            
            if (maxLeftM <= minRightN && maxLeftN <= minRightM) {
                // arrays have been partitioned correctly
                if ((m+n) % 2 == 0) {
                    return (double) ((Math.max(maxLeftM, maxLeftN) + Math.min(minRightM, minRightN))/2.0);
                }
                return Math.max(maxLeftM, maxLeftN);
            }
            else if (maxLeftM > minRightN) {
                // too far right on nums1, move to left
                hi = partitionM-1;
            }
            else {
                // too far left, move to right
                lo = partitionM + 1;
            }
        }
        return 0;
    }
    
    public static void main(String[] args) {
        MedianOfSortedArrays msa = new MedianOfSortedArrays ();
        int[] nums1 = {1, 2}, nums2 = {3, 4};
        System.out.println(msa.findMedianSortedArrays(nums1, nums2));
    }

}
