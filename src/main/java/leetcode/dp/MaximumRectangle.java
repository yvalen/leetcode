package leetcode.dp;

import java.util.Arrays;
import java.util.Stack;

/*
 * Given a 2D binary matrix filled with 0's and 1's, find the largest rectangle containing only 1's and return its area.
 * For example, given the following matrix:
 * 	1 0 1 0 0
 * 	1 0 1 1 1
 * 	1 1 1 1 1
 * 	1 0 0 1 0
 * Return 6. 
 */
public class MaximumRectangle {
	// similar to largest rectangle in histogram
	// maintain a column length of integer array height, scan and update row by row to find out the largest rectangle of each row.
	// Complexity: O(mn) - time, O(n) - space
	public int maximalRectangle_withStack(char[][] matrix) {
		if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return 0;
		
		int m = matrix.length, n = matrix[0].length;
        int max = 0;
        int[] heights = new int[n];
        
        for (int i = 0; i < m; i++) {
        	// update height 
        	for (int j = 0; j < n; j++) {
        		heights[j] = matrix[i][j] == '1' ? heights[j] + 1 : 0;
        	}
        	
        	// calculate the largest rectangle for the current row
        	Stack<Integer> stack = new Stack<>();
        	for (int j = 0; j <= n; j++) {
        		int h = j == n ? 0 : heights[j]; // when all elements are examined set h to 0 to process the rest of the element on stack
        		if (stack.isEmpty() || h >= heights[stack.peek()]) {
        			stack.push(j);
        		}
        		else {
        			int top = stack.pop();
        			max = Integer.max(max, heights[top] * (stack.isEmpty() ? j : j - 1 - stack.peek()));
        			j--; // counteract i++ in the for loop so that the current element will be put onto the stack
        		}
        	}
        }
		return max;
    }
	
	// proceeds row by row, starting from the first row. Let the maximal rectangle area at row i and column j 
	// be computed by [right(i,j) - left(i,j)]*height(i,j).
	public int maximalRectangle_dp(char[][] matrix) {
		if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return 0;
		
		int m = matrix.length, n = matrix[0].length, max = 0;
        int[] height = new int[n]; // from top to this position, there are how many '1'
        int[] leftBoundary = new int[n]; // at current position, what is the index of left bound of the rectangle with height[j]. 0 means at this position, no rectangle. 
        int[] rightBoundary = new int[n]; //means the right bound index of this rectangle. 'n' means no rectangle.
        Arrays.fill(rightBoundary, n);
        
        for (int i = 0; i < m; i++) {
        	int left = 0, right = n;
        	
        	// compute height
        	for (int j = 0; j < n; j++) {
        		height[j] = matrix[i][j] == '1' ? height[j] + 1 : 0;
        	}
        	
        	// compute left boundary(from left to right)
        	for (int j = 0; j < n; j++) {
        		if (matrix[i][j] == '1') {
        			leftBoundary[j] = Integer.max(leftBoundary[j], left);
        		} 
        		else {
        			left = j + 1;
        			leftBoundary[j] = 0;
        		}
        	}
        	
        	// compute right boundary (from right to left)
        	for (int j = n-1; j>= 0; j--) {
        		if (matrix[i][j] == '1') {
        			rightBoundary[j] = Integer.min(rightBoundary[j], right);
        		} 
        		else {
        			right = j;  // right boundary is exclusive
        			rightBoundary[j] = n; 
        		}
        	}
        	
        	// compute the result for the current row
        	for (int j = 0; j < n; j++) {
        		max = Integer.max(max, height[j] * (rightBoundary[j] - leftBoundary[j]));
        	}
        }
        
        return max;
	}
	
	
	public static void main(String[] args) {
		MaximumRectangle r = new MaximumRectangle();
		/*
		char[][] matrix = {
				{'1', '0', '1', '0', '0'},	
				{'1', '0', '1', '1', '1'},
				{'1', '1', '1', '1', '1'},
				{'1', '0', '0', '1', '0'},
		};
		*/
		char[][] matrix = {
				{'0', '0', '0', '1', '0', '0', '0'},	
				{'0', '0', '1', '1', '1', '0', '0'},
				{'0', '1', '1', '1', '1', '1', '0'}
		};
		System.out.println(r.maximalRectangle_dp(matrix));
	}
	
	
}
