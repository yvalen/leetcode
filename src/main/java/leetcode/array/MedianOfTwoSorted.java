package leetcode.array;

/**
 * Median: In probability theory and statistics, a median is described as the number separating the higher half of a sample, 
 * a population, or a probability distribution, from the lower half. The median of a finite list of numbers can be found by 
 * arranging all the numbers from lowest value to highest value and picking the middle one. 
 * 
 * http://www.drdobbs.com/parallel/finding-the-median-of-two-sorted-arrays/240169222
 */
public class MedianOfTwoSorted {
	/*
	 * There are two sorted arrays nums1 and nums2 of size m and n respectively.
	 * Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).
	 * Example 1: nums1 = [1, 3], nums2 = [2], the median is 2.0
	 * Example 2: nums1 = [1, 2], nums2 = [3, 4], the median is (2 + 3)/2 = 2.5
	 * 
	 * https://discuss.leetcode.com/topic/4996/share-my-o-log-min-m-n-solution-with-explanation
	 */
	public double findMedianSortedArrays(int[] nums1, int[] nums2) {
		int m = nums1.length, n = nums2.length;
		if (m > n) {
			return findMedianSortedArrays(nums2, nums1);
		}
		
		int iMin = 0, iMax = m, halfLen = (m + n + 1) / 2, i = 0, j = 0;
		int maxOfLeft = 0, minOfRight = 0;
	 	while (iMin <= iMax) {
	 		i = iMin + (iMax - iMin) / 2;
	 		j = halfLen - i;
	 		if (i < m && nums2[j-1] > nums1[i]) {
	 			// i is too small
	 			iMin = i + 1;
	 		} else if (i > 0 && nums2[j] < nums1[i-1]) {
	 			// i is too large
	 			iMax = i - 1;
	 		} else {
	 			// found i
	 			break;
	 		}
	 	}
	 	
	 	if (i == 0) maxOfLeft = nums2[j - 1];
		else if (j == 0) maxOfLeft = nums1[i-1];
		else maxOfLeft = Integer.max(nums1[i-1], nums2[j-1]);
	 			
 		if ((m + n) % 2 == 1) return maxOfLeft;
 		
		if (i == m) minOfRight = nums2[j];
		else if (j == n) minOfRight = nums1[i];
		else minOfRight = Integer.min(nums1[i],  nums2[j]);
		
		return (double)(maxOfLeft + minOfRight) / 2;
	 	
    }
	
	public double findMedianSortedArrays_withFindkth(int[] nums1, int[] nums2) {
		int len = nums1.length + nums2.length;
		if (len % 2 == 0) {
			// even length
			return (double) ((findKthElement(nums1, nums2, 0, 0 , len/2) +
					findKthElement(nums1, nums2, 0, 0 , len/2+1)) / 2);
		}
		else {
			// odd length
			return (double) findKthElement(nums1, nums2, 0, 0 , len/2+1);
		}
			
	}
	
	private int findKthElement(int[] nums1, int[] nums2, int s1, int s2, int k) {
		if (s1 >= nums1.length) return nums2[s2+k-1];
		
		if (s2 >= nums2.length) return nums1[s1+k-1];
		
		// base case
		if (k == 1) return Integer.min(nums1[s1], nums2[s2]);
		
		int mid1 = s1 + k/2 -1;
		int mid2 = s2 + k/2 -1;
		
		int num1 = mid1 < nums1.length ? nums1[mid1] : Integer.MAX_VALUE;
		int num2 = mid2 < nums2.length ? nums2[mid2] : Integer.MAX_VALUE;
		
		if (num1 < num2) {
			return findKthElement(nums1, nums2, mid1+1, s2, k-k/2);
		}
		else {
			return findKthElement(nums1, nums2, s2, mid2+1, k-k/2);
		}
	}
	
	
	public static void main(String[] args) {
		MedianOfTwoSorted m = new MedianOfTwoSorted();
		int[] nums1 = {1, 3, 5, 7};
		int[] nums2 = {2, 4, 6, 8, 9, 10};
		System.out.println(m.findMedianSortedArrays(nums1, nums2));
		
	}
}
