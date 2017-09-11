package leetcode.binarysearch;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

/*
 * Given a sorted array, two integers k and x, find the k closest elements to x in the array. The result should also be sorted in ascending order. 
 * If there is a tie, the smaller elements are always preferred.
 * Example 1: Input: [1,2,3,4,5], k=4, x=3 Output: [1,2,3,4]
 * Example 2: Input: [1,2,3,4,5], k=4, x=-1 Output: [1,2,3,4]
 * Note:
 * - The value k is positive and will always be smaller than the length of the sorted array.
 * - Length of the given array is positive and will not exceed 10^4
 * - Absolute value of elements in the array and x will not exceed 10^4
 * 
 * Company: Google
 * Difficulty: medium
 */
public class FindKClosestElements {
	public List<Integer> findClosestElements(List<Integer> arr, int k, int x) {
		int idx = findClosetElementIndex(arr, x);
		
		int l = idx, r = idx+1;
		while (r - l < k ) {
			if (l == 0) r++;
			else if (r == arr.size()) l--;
			else {
				int left =  Math.abs(arr.get(l) -x);
				int right =  Math.abs(arr.get(r)-x);
				if (right < left && r < arr.size() -1) r++;
				else  l--;
			}
		}
		
		return arr.subList(l, r);
    }
	
	private int findClosetElementIndex(List<Integer> arr, int x) {
		int lo = 0, hi = arr.size() - 1;
		while (lo <= hi) {
			int mid = lo + (hi-lo)/2;
			int val = arr.get(mid);
			if (val == x) return mid;
			else if (val > x) hi = mid -1;
			else lo = mid + 1;
		}
		
		if (hi < 0) return lo;
		if (lo > arr.size() - 1) return hi;		
		return Math.abs(arr.get(lo)-x) <= Math.abs(arr.get(hi)-x) ? lo : hi;
	}
	
	public static void main(String[] args) {
		FindKClosestElements fce = new FindKClosestElements();
		//List<Integer> arr = Arrays.asList(1, 2, 3, 4, 5);
		//int k = 4, x = 6;
		//List<Integer> arr = Arrays.asList(0,0,1,2,3,3,4,7,7,8);
		//int k = 3, x = 5;
		//List<Integer> arr = Arrays.asList(0,1,2,2,2,3,6,8,8,9);
		//int k = 5, x = 9;
		List<Integer> arr = Arrays.asList(0,2,2,3,4,6,7,8,9,9);
		int k = 4, x = 5;
		System.out.println(fce.findClosestElements(arr, k, x));
	}
}
