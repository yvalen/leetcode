package leetcode.heap;

import java.util.PriorityQueue;

/*
 * Given a n x n matrix where each of the rows and columns are sorted in ascending order, find the kth smallest element in the matrix.
 * Note that it is the kth smallest element in the sorted order, not the kth distinct element.
 * Example:
 * matrix = 
 * [
 * 	[ 1,  5,  9],
 * 	[10, 11, 13],
 * 	[12, 13, 15]
 * ],
 * k = 8,
 * return 13.
 * Note: You may assume k is always valid, 1 <= k <= n2.
 * 
 * Company: Google, Twitter
 * Difficulty: medium
 */
public class KthSmallestElementInSortedMatrix {
	public int kthSmallest(int[][] matrix, int k) {
		int n = matrix.length;
		PriorityQueue<int[]> pq = new PriorityQueue<>(k, (x, y) -> x[0]-y[0]);
		
		for (int i = 0; i < n && i < k; i++) pq.offer(new int[] {matrix[i][0], i, 0}); // min heap, top element will be the result
		while (k-- > 1) {
			int[] curr = pq.poll();
			int i = curr[1], j = curr[2];
			if (j == n - 1) continue;
			pq.offer(new int[] {matrix[i][j+1], i, j+1});
		}
		return pq.poll()[0];
    }
	
	public static void main(String[] args) {
		KthSmallestElementInSortedMatrix sksm = new KthSmallestElementInSortedMatrix();
		/*
		int[][] matrix = {
				{1, 5, 9},
				{10, 11, 13},
				{12, 13, 15}
		};
		int k = 8;
		*/
		int[][] matrix = {
				{-5}
		};
		int k = 1;
		System.out.println(sksm.kthSmallest(matrix, k));
		
	}
}
