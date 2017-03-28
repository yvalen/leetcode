package leetcode.array;

import java.util.Stack;

/*
 * Given n non-negative integers representing the histogram's bar height where the width of each bar is 1, 
 * find the area of largest rectangle in the histogram. For example, given heights = [2,1,5,6,2,3], return 10.
 */
public class LargestRectangleHistogram {
	// Complexity: O(n^2) - time, O(1) - space
	public int largestRectangleArea_bruteForce(int[] heights) {
		if (heights == null || heights.length == 0) return 0;
        int n = heights.length, maxArea = 0;
        for (int i = 0; i < n; i++) {
        	int minHeight = heights[i];
        	for (int j = i; j < n; j++) { // j should start from i to include the individual bar
        		minHeight = Integer.min(minHeight, heights[j]);
        		maxArea = Integer.max(maxArea, minHeight * (j-i+1));
        	}
        }
		return maxArea;
    }

	// The rectangle with maximum area will be the maximum of:
	// - the widest possible rectangle with height equal to the height of the shortest bar.
	// - the largest rectangle confined to the left of the shortest bar(subproblem).
	// - the largest rectangle confined to the right of the shortest bar(subproblem).
	// Time complexity: O(nlogn) average O(n^2)  worst case when array is sorted
	// Space complexity: O(n) for recursion
	// can be optimized with segment tree https://discuss.leetcode.com/topic/45822/segment-tree-solution-just-another-idea-o-n-logn-solution/2
	public int largestRectangleArea_divideAndConquer(int[] heights) {
		return divideAndConquerHelper(heights, 0, heights.length-1);
    }
	
	private int divideAndConquerHelper(int[] heights, int start, int end) {
		if (start > end) return 0;
		
		// find the minimum height between start and end
		int minIdx = start;
		for (int i = start+1; i <= end; i++) {
			if (heights[i] < heights[minIdx]) minIdx = i;
		}
		
		return Integer.max(heights[minIdx] * (end-start+1), 
				Integer.max(
						divideAndConquerHelper(heights, start, minIdx-1), 
						divideAndConquerHelper(heights, minIdx+1, end)
						));
	}
	
	// - For every bar ‘x’, we calculate the area with ‘x’ as the smallest bar in the rectangle.
	// - the key is that we are maintaing an increasing bars in the stack	
	// - when we meet a height, say h, that is lower than the stack's top ==> all the bars stored in the stack that has 
	// height >= h will be right bounded by h (Remember we are calculating the area with ‘x’ as the smallest bar in the rectangle.
	// - At that moment, since in the stack, we are maintaining a increasing height in the stack, every time we check a bar that 
	// is right bounded by h, will also be left bounded by the height that is previous stored in the stack. So the width would go 
	// from stack[-1] + 1 to i - 1 included, which is i - stack[-1] - 1
	// - If we are examing the last element in the stack, this means he is the lowest till the leftmost of the histogram, so the width 
	// would become index 0 through index i-1 included, which is i
	public int largestRectangleArea_withStack(int[] heights) {
		if (heights == null || heights.length == 0) return 0;
        int n = heights.length, maxArea = 0;
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);
        for (int i = 0; i < n; i++) {
        	while (stack.peek() != -1 && heights[stack.peek()] >= heights[i]) {
        		maxArea = Integer.max(maxArea, heights[stack.pop()] * (i-stack.peek()-1));
        	}
        	stack.push(i);
        }
         
        int lastIndex = stack.peek();
        while (stack.peek() != -1) {
        	maxArea = Integer.max(maxArea, heights[stack.pop()] * (lastIndex - stack.peek()));
        }
        
        return maxArea;
	}
	
	
	public static void main(String[] args) {
		LargestRectangleHistogram l = new LargestRectangleHistogram();
		//int[] heights = {};
		//int[] heights = {1};
		//int[] heights = {0, 9};
		//int[] heights = {2,1,5,6,2,3};
		//int[] heights = {6,7,5,2,4,5,9,3};
		int[] heights = {2,1,5,6,2,3};
		System.out.println(l.largestRectangleArea_withStack(heights));
		
	}
	
	
}
