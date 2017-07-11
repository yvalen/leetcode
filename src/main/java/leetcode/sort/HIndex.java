package leetcode.sort;

import java.util.Arrays;
import java.util.Collections;
import java.util.stream.IntStream;

/*
 * Given an array of citations (each citation is a non-negative integer) of a researcher, write a function to compute the researcher's h-index.
 * According to the definition of h-index on Wikipedia: "A scientist has index h if h of his/her N papers have at least h citations each, and 
 * the other N − h papers have no more than h citations each." For example, given citations = [3, 0, 6, 1, 5], which means the researcher has 5 
 * papers in total and each of them had received 3, 0, 6, 1, 5 citations respectively. Since the researcher has 3 papers with at least 3 citations 
 * each and the remaining two with no more than 3 citations each, his h-index is 3.
 * Note: If there are several possible values for h, the maximum one is taken as the h-index. 
 * 
 * Comapny: Bloomberg, Google, Facebook
 */
public class HIndex {
	// Time Complexity: O(nlogn)
	public int hIndex_sortDescending(int[] citations) {
		if (citations == null || citations.length == 0) return 0;
		
		int n = citations.length;
		
		// sort citations in ascending order
		Arrays.sort(citations); // comparator doesn't work for primitive type
		// reverse in-place
		for (int i = 0; i < n / 2; i++) {
			int tmp = citations[i];
			citations[i] = citations[n-i-1];
			citations[n-i-1] = tmp;
		}
		//IntStream.of(citations).forEach(System.out::println);
		
		// find the last position i in which citations[i] >= i
		int i = 0;
		while (i < n && citations[i] > i) {
			i++;
		}
		
		return i;
	}
	
	public int hIndex_sortAscending(int[] citations) {
		if (citations == null || citations.length == 0) return 0;
		
		int n = citations.length;
		
		// sort citations 
		Arrays.sort(citations); 
		
		// find the last position i in which citations[i] >= i
		int i = 0;
		while (i < n && citations[n-i-1] > i) {
			i++;
		}
		
		return i;
	}
	
	// Time Complexity: O(n), Space: O(n)
	public int hIndex_countingSort(int[] citations) {
		if (citations == null || citations.length == 0) return 0;
		
		int n = citations.length;
		int[] papers = new int[n+1]; // count the number of papers that have been cited i times, 
		for (int citation : citations) {
			papers[citation > n ? n : citation] += 1; 
			//papers[Math.min(citation, n)]++;
		}
		
		int k = n;  // sum at k is the sum of all counts with citation >= k, or the number of papers have at least k citations
		for (int sum = papers[n]; k > sum; sum += papers[k]) {
			k--;			
		}
		
		return k;
	}
	
	/*
	 * Follow up for H-Index: What if the citations array is sorted in ascending order? Could you optimize your algorithm? 
	 * Hint: Expected runtime complexity is in O(log n) and the input is sorted.
	 * 
	 * Company: Facebook
	 */
	// Binary search, Time complexity: O(logN)
	public int hIndexII(int[] citations) {
		// The basic idea is to search for the first index from the sorted array such that
		// citations[index] >= length(citations) - index
		// And return (length - index) as the result.
		// 
		// Just binary search, each time check citations[mid]
		// case 1: citations[mid] == len-mid, then it means there are citations[mid] papers that have at least citations[mid] citations
		// case 2: citations[mid] > len-mid, then it means there are citations[mid] papers that have more than citations[mid] citations, 
		// so we should continue searching in the left half
		// case 3: citations[mid] < len-mid, we should continue searching in the right side
		// After iteration, it is guaranteed that right+1 is the one we need to find (i.e. len-(right+1) papars have at least len-(righ+1) citations)

		if (citations == null || citations.length == 0) return 0;
		
        int n = citations.length, lo = 0, hi = n - 1;
        while (lo <= hi) { // use lo <= hi to handle {0, 0}
        	int mid = lo + (hi - lo) / 2;
        	if (citations[mid] == n - mid) return n-mid;
        	
        	if (citations[mid] > n - mid) { // citation number is bigger, paper is not enough, go to left and find a smaller citation
        		hi = mid - 1;
        	}
        	else {
        		lo = mid + 1;
        	}
        	
        }
		
		return n - lo;
    }
	
	public static void main(String[] args) {
		HIndex hindex = new HIndex();
		//int[] citations = {3, 0, 6, 1, 5};
		int[] citations = {0};
		System.out.println(hindex.hIndex_sortDescending(citations));
	}
}
