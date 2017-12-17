package leetcode.tree;

import java.util.HashMap;
import java.util.Map;

/*
 * Given a binary tree with n nodes, your task is to check if it's possible to partition the tree to two trees 
 * which have the equal sum of values after removing exactly one edge on the original tree.
 * Example 1:
 * Input:     
 *     5
 *    / \
 *   10 10
 *     /  \ 
 *    2   3
 * Output: True
 * Explanation: 
 *     5
 *    / 
 *   10
 * Sum: 15
 *    10
 *   /  \
 *  2    3
 * Sum: 15
 * Example 2:
 * Input:     
 *     1
 *    / \
 *   2  10
 *     /  \
 *    2   20
 * Output: False
 * Explanation: You can't split the tree into two trees with equal sum after removing exactly one edge on the tree.
 * Note:
 * - The range of tree node value is in the range of [-100000, 100000].
 * - 1 <= n <= 10000
 * 
 * Company: Amazon
 * Difficulty: medium
 */
public class EqualTreePartition {
    // The idea is to use a hash table to record all the different sums of each
    // subtree in the tree.
    // If the total sum of the tree is sum, we just need to check if the hash
    // table constains sum/2.
    public boolean checkEqualTree(TreeNode root) {
        Map<Integer, Integer> sumMap = new HashMap<>();
        int sum = getSum(root, sumMap);

        // special handling for root=0, sum of left=-k, sum of right=k
        // sum%0==0 and there should exist another subtree whose sum equals to 0
        if (sum == 0)
            return sumMap.getOrDefault(0, 0) > 1;

        return (sum % 2 == 0) && sumMap.containsKey(sum / 2);
    }

    private int getSum(TreeNode root, Map<Integer, Integer> sumMap) {
        if (root == null)
            return 0;
        int sum = root.val + getSum(root.left, sumMap) + getSum(root.right, sumMap);
        sumMap.put(sum, sumMap.getOrDefault(sum, 0) + 1);
        return sum;
    }
}
