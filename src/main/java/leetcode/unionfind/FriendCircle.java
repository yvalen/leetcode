package leetcode.unionfind;

import java.util.LinkedList;
import java.util.Queue;

import leetcode.matrix.MatrixUtil;

/*
 * There are N students in a class. Some of them are friends, while some are not. Their friendship is transitive in nature. 
 * For example, if A is a direct friend of B, and B is a direct friend of C, then A is an indirect friend of C. And we defined 
 * a friend circle is a group of students who are direct or indirect friends. Given a N*N matrix M representing the friend 
 * relationship between students in the class. If M[i][j] = 1, then the ith and jth students are direct friends with each other, 
 * otherwise not. And you have to output the total number of friend circles among all the students.
 * Example 1: Input: 
 *	[
 *		[1,1,0],
 * 	 	[1,1,0],
 * 	 	[0,0,1]
 * 	]
 * Output: 2
 * Explanation:The 0th and 1st students are direct friends, so they are in a friend circle. 
 * The 2nd student himself is in a friend circle. So return 2.
 * Example 2: Input: 
 * 	[
 * 		[1,1,0],
 * 		[1,1,1],
 * 		[0,1,1]
 * 	]
 * Output: 1
 * Explanation:The 0th and 1st students are direct friends, the 1st and 2nd students are direct friends, 
 * so the 0th and 2nd students are indirect friends. All of them are in the same friend circle, so return 1.
 * Note:
 * - N is in range [1,200].
 * - M[i][i] = 1 for all students.
 * - If M[i][j] = 1, then M[j][i] = 1.
 * 
 * Company: TwoSigma
 * Difficulty: medium
 */
public class FriendCircle {
	public int findCircleNum_dfs(int[][] M) {
        if (M == null || M.length == 0) return 0;
        
        int n = M.length;
        int count = 0;
        boolean[] visited = new boolean[n]; // track whether this student has been visited
        for (int i = 0; i < n; i++) {
        	if (!visited[i]) { // if the student hasn't been visited, use it as a start point for DFS
        		bfs(M, visited, i);
        		count++;
        	}
        }
        
        return count;
    }
	
	//
	// DFS
	// Time complexity: O(n^2), Space complexity: O(n)
	private void dfs(int[][] M, boolean[] visited, int i) {
		visited[i] = true;
		for (int j = 0; j < M.length; j++) {
			if (M[i][j] == 1 && !visited[j]) {
				dfs(M, visited, j);
			}
		}
	}
	
	//
	// BFS
	// Time complexity: O(n^2), Space complexity: O(n)
	private void bfs(int[][] M, boolean[] visited, int i) {
		Queue<Integer> queue = new LinkedList<>();
		queue.offer(i);
		visited[i] = true;
		while (!queue.isEmpty()) {
			int curr = queue.poll();
			for (int j = 0; j < M.length; j++) {
				if (M[curr][j] == 1 && !visited[j]) {
					visited[j] = true;
					queue.offer(j);
				}
			}
		}
	}
	
	//
	// Union Find
	// Time complexity: O(n^3), Space complexity: O(n)
	private static class UnionFind {
		private int[] ids;
		private int count;
		
		UnionFind(int n) {
			ids = new int[n];
			for (int i = 0; i < n; i++) {
				ids[i] = i;
			}
			count = n;
		}
		
		public int find(int p) {
			while (p != ids[p]) p = ids[p];
			return p;
		}
		
		public void union(int p, int q) {
			int i = find(p);
			int j = find(q);
			if (i == j) return;
			ids[i] = j;
			count--;
		}
		
		public int getCount() {
			return count;
		}
	}
	
	public int findCircleNum_unionFind(int[][] M) {
        if (M == null || M.length == 0) return 0;
        
        int n = M.length;
        UnionFind uf = new UnionFind(n);
        for (int i = 0; i < n; i++) {
        	for (int j = 0; j < n; j++) {
        		if (i != j && M[i][j] == 1) {
        			uf.union(i, j);
        		}
        	}
        }
        
        return uf.getCount();
    }
	
	
	public static void main(String[] args) {
		FriendCircle fc = new FriendCircle();
		int[][] M = {
				{1, 1, 0},
				{1, 1, 0},
				{0, 0, 1}
		};
		System.out.println(fc.findCircleNum_unionFind(M));
	}
}
