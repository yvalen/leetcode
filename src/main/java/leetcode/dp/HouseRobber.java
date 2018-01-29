package leetcode.dp;

import java.util.LinkedList;
import java.util.Queue;

import leetcode.tree.SerializeDeserialize;
import leetcode.tree.TreeNode;

// 0-1 knapsack problem explanation:
// https://www.quora.com/Whats-an-intuitive-explanation-for-the-0-1-knapsack-problem-in-data-structures-and-algorithms

public class HouseRobber {

    /**
     * LEETCODE 198 
     * You are a professional robber planning to rob houses along a street. 
     * Each house has a certain amount of dp stashed, the only constraint stopping 
     * you from robbing each of them is that adjacent houses have security system 
     * connected and it will automatically contact the police if two adjacent houses 
     * were broken into on the same night. 
     * Given a list of non-negative integers representing the amount of money of each
     * house, determine the maximum amount of money you can rob tonight without
     * alerting the police.
     * 
     * Company: LinkedIn, Airbnb 
     * Difficulty: easy 
     * Similar Questions: 256(PaintHouse), 276(PaintFence), 213(House Robber II), 
     * 152(MaxProductArray), 213(House Robber II) 337(House Robber III)
     */
    public int rob_withDPArray(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;

        int n = nums.length;
        if (n == 1)
            return nums[0];

        int[] dp = new int[n];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]); // when processing 2nd element, need
                                            // to compare it with the 1st
        for (int i = 2; i < n; i++) {
            dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1]);
        }

        return dp[n - 1];
    }

    public int rob_spaceOptimization(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;

        int n = nums.length;
        if (n == 1)
            return nums[0];

        // max money up to previous house
        int prev = nums[0];

        // max money to current house
        int curr = Math.max(nums[0], nums[1]);

        for (int i = 2; i < n; i++) {
            int tmp = curr;
            curr = Math.max(prev + nums[i], curr);
            prev = tmp;
        }

        return curr;
    }

    public int rob(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;

        int robPrev = 0, notRobPrev = 0;
        for (int i : nums) {
            int temp = notRobPrev;
            notRobPrev = Math.max(notRobPrev, robPrev);
            robPrev = i + temp;
        }

        return Math.max(robPrev, notRobPrev);
    }

    /**
     * LEETCODE 213 
     * After robbing those houses on that street, the thief has found himself 
     * a new place for his thievery so that he will not get too much attention. 
     * This time, all houses at this place are arranged in a circle. That means 
     * the first house is the neighbor of the last one. Meanwhile, the security 
     * system for these houses remain the same as for those in the previous street. 
     * Given a list of non-negative integers representing the amount of money of 
     * each house, determine the maximum amount of money you can rob tonight without 
     * alerting the police.
     * 
     * Company: Microsoft 
     * Difficulty: medium 
     * Similar Questions: 256(PaintHouse), 276(PaintFence), 198(House Robber), 
     * 337(House Robber III)
     */
    // https://discuss.leetcode.com/topic/14375/simple-ac-solution-in-java-in-o-n-with-explanation
    public int robCircle(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;

        if (nums.length == 1)
            return nums[0];

        // return Math.max(robCircleHelper(nums, 0, nums.length-2),
        // robCircleHelper(nums, 1, nums.length-1));
        
        // break the circle by choosing rob the first or not rob the first
        return Math.max(robWithStartEnd(nums, 0, nums.length - 2), 
                robWithStartEnd(nums, 1, nums.length - 1));

    }

    private int robWithStartEnd(int[] nums, int start, int end) {
        int robPrev = 0, notRobPrev = 0;
        for (int i = start; i <= end; i++) {
            int temp = notRobPrev;
            notRobPrev = Math.max(robPrev, notRobPrev);
            robPrev = nums[i] + temp;
        }
        return Math.max(notRobPrev, robPrev);
    }

    private int robCircleHelper(int[] nums, int start, int end) {
        int prevYes = 0, prevNo = 0;
        for (int i = start; i <= end; i++) {
            int temp = prevNo;
            prevNo = Math.max(prevYes, prevNo);
            prevYes = temp + nums[i];
        }
        return Math.max(prevYes, prevNo);
    }

    /*
     * LEETCODE 337 
     * The thief has found himself a new place for his thievery again. There is 
     * only one entrance to this area, called the "root." Besides the root, each 
     * house has one and only one parent house. After a tour, the smart thief 
     * realized that "all houses in this place forms a binary tree". It will 
     * automatically contact the police if two directly-linked houses were broken 
     * into on the same night. Determine the maximum amount of money the thief can 
     * rob tonight without alerting the police. 
     * Example 1: 
     *      3
     *     / \
     *    2   3
     *     \   \ 
     *      3   1
     * Maximum amount of money the thief can rob = 3 + 3 + 1 = 7. 
     * Example 2:
     *         3
     *        / \
     *       4   5
     *      / \   \ 
     *     1   3   1
     * Maximum amount of money the thief can rob = 4 + 5 = 9.
     * 
     * Company: Uber 
     * Difficulty: medium 
     * Similar Questions: 198(House Robber), 213(House Robber II)
     */
    public int robTree_dfs(TreeNode root) {
        // bfs won't work here because you can rob elements from consecutive
        // levels as long as the elements are not linked
        int[] result = robTreeHelper(root);
        return Integer.max(result[0], result[1]);
    }

    // first element of the returned array contains the value if root is not robbed 
    // second element of the returned array contains the value if root is robbed
    private int[] robTreeHelper(TreeNode root) {
        int[] result = new int[2];
        if (root == null)
            return result;

        int[] left = robTreeHelper(root.left);
        int[] right = robTreeHelper(root.right);
        // root is not robbed, choose the max value from the result of left and right
        result[0] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
        // root is robbed, left and right cannot be robbed
        result[1] = root.val + left[0] + right[0];
        return result;
    }

    public static void main(String[] args) {
        HouseRobber h = new HouseRobber();
        // int[] nums = {2, 1, 1, 1};
        // System.out.println(h.robCircle(nums));
        // TreeNode root = SerializationBFS.deserialize("2,1,3,null,4");
        // System.out.println(h.robTree(root));

        String s = "2,1,3,null,4";
        TreeNode root = SerializeDeserialize.deserialize_bfs(s);
        System.out.println(h.robTree_dfs(root));

    }
}
