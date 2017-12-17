package leetcode.tree;

import java.util.HashMap;
import java.util.Map;

/*
 * LEETCODE 666
 * If the depth of a tree is smaller than 5, then this tree can be represented by a list of three-digits integers.
 * For each integer in this list:
 * - The hundreds digit represents the depth D of this node, 1 <= D <= 4.
 * - The tens digit represents the position P of this node in the level it belongs to, 1 <= P <= 8. The position is the same as that in a full binary tree.
 * - The units digit represents the value V of this node, 0 <= V <= 9.
 * Given a list of ascending three-digits integers representing a binary with the depth smaller than 5. You need to return the sum of all paths from the root towards the leaves.
 * Example 1:
 * Input: [113, 215, 221] Output: 12
 * Explanation: The tree that the list represents is:
 *     3
 *    / \
 *   5   1
 * The path sum is (3 + 5) + (3 + 1) = 12.
 * Example 2: Input: [113, 221] Output: 4
 * Explanation: The tree that the list represents is: 
 *     3
 *      \
 *       1
 * The path sum is (3 + 1) = 4.
 * 
 * Company: Alibaba
 * Difficulty: medium
 * Similar Questions: 112(Path Sum), 113(Path Sum II), 437(Path Sum III), 124(Binary Tree Max Path Sum)
 */
public class PathSumIV {
    // each tree node is represented by a number. 1st digits is the level, 2nd
    // is the position in that level
    // (note that it starts from 1 instead of 0). 3rd digit is the value.
    // we can form a tree using a HashMap. The key is first two digits which
    // marks the position of a node in the tree.
    // The value is value of that node. Thus, we can easily find a node's left
    // and right children using math.
    // Formula: For node xy (x is the depth, y is the position), its left child
    // is (x+1)(y*2-1) and right child is (x+1)(y*2)

    public int pathSum(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;

        // map represents the tree, key is the node position, value is node's
        // value
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num / 10, num % 10);
        }
        dfs(map, nums[0] / 10, 0);
        return sum;
    }

    private int sum;

    private void dfs(Map<Integer, Integer> map, int node, int currentSum) {
        int level = node / 10, pos = node % 10;
        int left = (level + 1) * 10 + (2 * pos - 1);
        int right = (level + 1) * 10 + (2 * pos);

        currentSum += map.get(node);
        if (!map.containsKey(left) && !map.containsKey(right)) { // leaf node,
                                                                 // add to sum
            sum += currentSum;
        } else {
            if (map.containsKey(left))
                dfs(map, left, currentSum);
            if (map.containsKey(right))
                dfs(map, right, currentSum);
        }
    }
}
