package leetcode.matrix;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Given a n x n matrix where each of the rows and columns are sorted in ascending order, 
 * find the kth smallest element in the matrix.
 * Note that it is the kth smallest element in the sorted order, not the kth distinct element.
 * Example:
 * matrix = [
 * 	[ 1,  5,  9],
 * 	[10, 11, 13],
 * 	[12, 13, 15]
 * ],
 * k = 8, return 13.
 * Note: You may assume k is always valid, 1 ≤ k ≤ n2.
 * 
 * https://discuss.leetcode.com/topic/52865/my-solution-using-binary-search-in-c/15
 */
public class KthSmallestElement {

	// O(n + klogk)
	public int kthSmallest_priorityQueue(int[][] matrix, int k) {
		// create a max heap of size k
		Queue<Integer> pq = new PriorityQueue<Integer>(k, (Integer e1, Integer e2) -> e2 - e1);
        
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				pq.offer(matrix[i][j]);
				if (pq.size() > k) {
					pq.poll();
				}
			}
		}
		
		return pq.peek();
    }
	
	// O(nlog(N))
	public int kthSmallest_binarySearch(int[][] matrix, int k) {
		int n = matrix.length;
		
		int left = matrix[0][0], right = matrix[n-1][n-1];
		while (left < right) {
			int mid = (left + right) /2;
			int count = 0, j = n - 1;
			for (int i = 0; i < n; i++) {
				while (j>= 0 && matrix[i][j] > mid) {
					j--;
				}
				count += j + 1;
			}
			
			if (count < k) left = mid + 1;
			else right = mid;
		}
		
		return left;
	}
	
	public static void main(String[] args) {
		KthSmallestElement testObj = new KthSmallestElement();
		
		int[][] matrix = {{1,  5,  9},{10, 11, 13},{12, 13, 15}};
		int result = testObj.kthSmallest_priorityQueue(matrix, 8);
		System.out.println(result);
		
	}
}
