package leetcode.array;

/*
 * Given n non-negative integers a1, a2, ..., an, where each represents a point at coordinate (i, ai). 
 * n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0). 
 * Find two lines, which together with x-axis forms a container, such that the container contains the most water.
 * Note: You may not slant the container and n is at least 2. 
 */
public class ContainerWithMostWater {
	
	// consider the area for every possible pair of the lines 
	// and find out the maximum area out of those.
	// Complexity: time - O(N^2), space O(1)
	public int maxArea_bruteForce(int[] height) {
		int maxArea = Integer.MIN_VALUE;
		for (int i = 0; i < height.length; i++) {
			for (int j = i + 1; j < height.length; j++) {
				maxArea = Integer.max(maxArea,  Integer.min(height[i], height[j]) * (j-i));
			}
		}
        return maxArea;
    }
	
	// Initially we consider the area constituting the exterior most lines. Now to maximize the area, 
	// we need to consider the area between the lines of larger lengths. If we try to move the pointer 
	// at the longer line inwards, we won't gain any increase in area, since it is limited by the shorter line.
	// But moving the shorter line's pointer could turn out to be beneficial, as per the same argument, despite the 
	// reduction in the width. This is done since a relatively longer line obtained by moving the shorter line's pointer 
	// might overcome the reduction in area caused by the width reduction.
	// Complexity: time - O(N) space - O(1)
	public int maxArea_twoPointers(int[] height) {
		int maxArea = Integer.MIN_VALUE;
		int l = 0, r = height.length - 1;
		while (l < r) {
			maxArea = Integer.max(maxArea, Integer.min(height[l], height[r]) * (r -l));
			if (height[l] < height[r]) l++;
			else r--;
		}
        return maxArea;
    }

}
