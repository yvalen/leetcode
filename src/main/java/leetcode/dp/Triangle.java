package leetcode.dp;

import java.util.Arrays;
import java.util.List;

/*
 * Given a triangle, find the minimum path sum from top to bottom. Each step you may move to adjacent numbers on the row below.
 * For example, given the following triangle
 * [
 * 	  [2],
 *   [3,4],
 *  [6,5,7],
 * [4,1,8,3]
 * ]
 * The minimum path sum from top to bottom is 11 (i.e., 2 + 3 + 5 + 1 = 11). 
 * 
 * dp[k][i] = min(dp[k+1][i], dp[k+1][i+1]) + triangle[k][i];
 * O(n) space: dp[i] = min(dp[i], dp[i+1]) + triangle[k][i]; 
 */
public class Triangle {
	
	public int minimumTotal(List<List<Integer>> triangle) {
		if (triangle == null || triangle.isEmpty()) return 0;
		
		int n = triangle.size();
		int[] dp = new int[n];
		
		// the shortest distance from each element on layer n-1 to the bottom
		// is obviously this element itself. No computation needed for this layer.
		for (int i = 0; i < n; i++) {
			dp[i] = triangle.get(n-1).get(i);
		}
		
		for(int i = n - 2; i >= 0; i--) {
			for (int j = 0; j <= i; j++) {
				dp[j] = Integer.min(dp[j], dp[j+1]) + triangle.get(i).get(j);
			}
		}
	
		return dp[0];
    }
	
	public static void main(String[] args) {
		Triangle t = new Triangle();
		
		List<List<Integer>> triangle = Arrays.asList(Arrays.asList(1), Arrays.asList(2,3));
		System.out.println(t.minimumTotal(triangle));
		
		
	}
}
