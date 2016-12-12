package leetcode.array;


/**
 * A peak element is an element that is greater than its neighbors.
 * Given an input array where num[i] ≠ num[i+1], find a peak element and return its index.
 * The array may contain multiple peaks, in that case return the index to any one of the peaks is fine.
 * You may imagine that num[-1] = num[n] = -∞.
 * For example, in array [1, 2, 3, 1], 3 is a peak element and your function should return the index number 2.
 * 
 * https://discuss.leetcode.com/topic/48268/share-my-java-solution-with-detailed-explanation-binary-search/2
 */
public class PeakElement {
	public int findPeakElement(int[] nums) {
		if (nums == null || nums.length == 0) return -1;
		
		int lo = 0, hi = nums.length - 1;
        while ( lo < hi) {
        	int mid = lo + (hi - lo) / 2;
        	if (nums[mid] < nums[mid+1]) lo = mid + 1;
        	else hi = mid;
        }
        
        return lo;
    }
	
	public int findPeakElement_linear(int[] nums) {
		if (nums== null || nums.length == 0) return -1;
		
		int n = nums.length;
		if (n == 1) return 0;
		
		for (int i = 0; i < n; i++) {
			if (i == 0) {
			}
			
			if (i==0 && nums[i] > nums[i+1]) return 0;
			
			if(nums[i] > nums[i-1] && nums[i] > nums[i+1]) return i;
			
			if (i == n-1 && nums[i] > nums[i-1]) return n-1;
		}
		
		return -1;
	}
	
}
