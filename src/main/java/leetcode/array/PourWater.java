package leetcode.array;

/*
 * LEETCODE 755
 * We are given an elevation map, heights[i] representing the height of the terrain 
 * at that index. The width at each index is 1. After V units of water fall at index 
 * K, how much water is at each index?
 * Water first drops at index K and rests on top of the highest terrain or water at 
 * that index. Then, it flows according to the following rules:
 * - If the droplet would eventually fall by moving left, then move left.
 * - Otherwise, if the droplet would eventually fall by moving right, then move right.
 * - Otherwise, rise at it's current position.
 * Here, "eventually fall" means that the droplet will eventually be at a lower level 
 * if it moves in that direction. Also, "level" means the height of the terrain plus 
 * any water in that column.
 * We can assume there's infinitely high terrain on the two sides out of bounds of the 
 * array. Also, there could not be partial water being spread out evenly on more than 
 * 1 grid block - each unit of water has to be in exactly one block. 
 * Example 1:
 * Input: heights = [2,1,1,2,1,2,2], V = 4, K = 3
 * Output: [2,2,2,3,2,2,2]
 * Example 2:
 * Input: heights = [1,2,3,4], V = 2, K = 2
 * Output: [2,3,3,4]
 * Explanation: The last droplet settles at index 1, since moving further left would 
 * not cause it to eventually fall to a lower height.
 * Example 3:
 * Input: heights = [3,1,3], V = 5, K = 1
 * Output: [4,4,4]
 * Note:
 * - heights will have length in [1, 100] and contain integers in [0, 99].
 * - V will be in range [0, 2000].
 * - K will be in range [0, heights.length - 1].
 * 
 * Company: Airbnb
 * Difficulty: hard
 * Similar Questions: 42(TrappingRainWater)
 */
public class PourWater {
    // Time complexity: O(VN), N is the length of heights
    // Space complexity: O(1)
    public int[] pourWater(int[] heights, int V, int K) {
        int index = K;
        while (V-- > 0) {
            index = K;
            for (int left = K-1; left >= 0; left--) {
                // should compare height at left with height at index instead of K
                // test case [1,2,3,4] -> [2,3,3,4] not [3,2,3,4]
                if (heights[left] > heights[index]) break;
                else if (heights[left] < heights[index]) index = left;
            }
            if (index != K) {
                heights[index]++;
                continue;
            }
            for (int right = K+1; right < heights.length; right++) {
                // should compare height at right with height at index instead of K
                if (heights[right] > heights[index]) break;
                else if (heights[right] < heights[index]) index = right;
            }
            // don't do check for index != K as we need to increment heights
            // at K if it cannot flow
            heights[index]++;
        }
        
        return heights;
    }

}
