package leetcode.array;

public class TrappingRainWater {
	/*
	 * Given n non-negative integers representing an elevation map where the width of each bar is 1, 
	 * compute how much water it is able to trap after raining.
	 * For example, given [0,1,0,2,1,0,1,3,2,1,2,1], return 6. 
	 */
	public int trap(int[] height) {
		int l = 0, r = height.length -1;
		int maxLeft = 0, maxRight = 0, max = 0;
		while (l < r) {
			maxLeft = Integer.max(maxLeft, height[l]);
			maxRight = Integer.max(maxRight,  height[r]);
			if (maxLeft < maxRight) {
				max += maxLeft - height[l];
				l++;
			}
			else {
				max += maxRight - height[r];
				r--;
			}
		}
		
		return max;
    }
	
	/*
	 * Given an m x n matrix of positive integers representing the height of each unit cell in a 2D elevation map, 
	 * compute the volume of water it is able to trap after raining.
	 * Note: Both m and n are less than 110. The height of each unit cell is greater than 0 and is less than 20,000.
	 * Example: given the following 3x6 height map:
	 * [
	 * 	[1,4,3,1,3,2],
	 * 	[3,2,1,3,2,4],
	 * 	[2,3,3,2,3,1]
	 * ]
	 * Return 4.
	 * http://www.cnblogs.com/grandyang/p/5928987.html
	 */
	public int trapRainWater(int[][] heightMap) {
        int max = 0;
		
		return max;
    }

}
