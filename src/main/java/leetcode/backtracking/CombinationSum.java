package leetcode.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class CombinationSum {
    /*
     * LEETCODE 39 
     * Given a set of candidate numbers (C) (without duplicates) and a target number (T), 
     * find all unique combinations in C where the candidate numbers sums to T. 
     * The same repeated number may be chosen from C unlimited number of times. 
     * Note: 
     * - All numbers (including target) will be positive integers. 
     * - The solution set must not contain duplicate combinations. 
     * For example, given candidate set [2, 3, 6, 7] and target 7,
     * a solution set is: [ [7], [2, 2, 3]]
     *
     * Company: SnapChat, Uber 
     * Difficulty: medium 
     * Similar Questions: 17(LetterCombinationOfPhoneNumber), 40(Combination Sum II),
     * 216(Combination Sum III), 77(Combination), 254(FactorCombinations), 377(CombinationSumIV)
     */
    // Time complexity: O(n!)
    public List<List<Integer>> combinationSum1(int[] candidates, int target) {
        if (candidates == null || candidates.length == 0)
            return Collections.emptyList();
        List<List<Integer>> result = new ArrayList<>();
        helper1(candidates, target, result, new LinkedList<>(), 0);
        return result;
    }

    private void helper1(int[] candidates, int target, List<List<Integer>> result, 
            LinkedList<Integer> list, int start) {
        if (target < 0) {
            // need to exit when target is negative, otherwise will be in infinite loop
            return;
        }
 
        if (target == 0) {
            result.add(new ArrayList<>(list));
            return;
        }

        for (int i = start; i < candidates.length; i++) { 
            // i should start from start instead of 0,
            // otherwise there will be duplicate combinations
            list.addLast(candidates[i]);
            // start should be from i because the same number can be resued
            helper1(candidates, target - candidates[i], result, list, i); 
            list.removeLast();
        }
    }

    /*
     * LEETCODE 40 
     * Given a collection of candidate numbers (C) and a target number (T), 
     * find all unique combinations in C where the candidate numbers sums to T. 
     * Each number in C may only be used once in the combination.
     * Note: All numbers (including target) will be positive integers. The
     * solution set must not contain duplicate combinations. 
     * For example, given candidate set [10, 1, 2, 7, 6, 1, 5] and target 8, 
     * a solution set is: [[1, 7], [1, 2, 5], [2, 6], [1, 1, 6] ]
     * 
     * Company: SnapChat 
     * Difficulty: medium 
     * Similar Questions: 39(CombinationSum)
     */
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        if (candidates == null || candidates.length == 0)
            return Collections.emptyList();
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(candidates);
        helper2(candidates, target, result, new LinkedList<>(), 0);
        return result;
    }

    private void helper2(int[] candidates, int target, List<List<Integer>> result, 
            LinkedList<Integer> list, int start) {
        if (target == 0) {
            System.out.println(list);
            result.add(new ArrayList<>(list));
            return;
        }

        for (int i = start; i < candidates.length; i++) {
            if (target - candidates[i] < 0) {
                // since candidates is sorted, no need to check further if
                // candidate is greater than target
                break;
            }
            
            if (i > start && candidates[i] == candidates[i - 1]) {
                // skip duplicates since the one at start position has been used
                // without this check, duplicate combinations will be generated
                // e.g. [10,1,2,7,6,1,5], 8 => [[1,1,6],[1,2,5],[1,7],[1,2,5],[1,7],[2,6]]
                continue;
            }
          
            list.addLast(candidates[i]);
            // should start from i+1 in recursion since each number can only be used once
            helper2(candidates, target - candidates[i], result, list, i + 1); 
            list.removeLast();
        }
    }

    /*
     * LEETCODE 216 
     * Find all possible combinations of k numbers that add up to a number n, 
     * given that only numbers from 1 to 9 can be used and each combination 
     * should be a unique set of numbers. 
     * Example 1: 
     * Input: k = 3, n = 7 
     * Output: [[1,2,4]] 
     * Example 2: 
     * Input: k = 3, n = 9 
     * Output: [[1,2,6], [1,3,5], [2,3,4]] 
     * Example 3: 
     * Input: k = 3, n = 15 
     * Output: [[1,5,9],[1,6,8],[2,4,9],[2,5,8],[2,6,7],[3,4,8],[3,5,7],[4,5,6]]
     * 
     * Difficulty: medium 
     * Similar Questions: 39(Combination Sum)
     */
    public List<List<Integer>> combinationSum3(int k, int n) {
        if (k <= 0 || n <= 0)
            return Collections.emptyList();
        List<List<Integer>> result = new ArrayList<>();
        helper3(k, n, 1, new LinkedList<>(), result);
        return result;
    }

    private void helper3(int k, int target, int start, LinkedList<Integer> combo, List<List<Integer>> result) {
        if (target == 0) {
            if (combo.size() == k) { // only add to result if size is k
                result.add(new ArrayList<>(combo));
            }
            return;
        }

        for (int i = start; i <= 9; i++) {
            if (i > target) break;
            combo.addLast(i);
            // each combination should be a unique set of numbers means no duplicate in the combinations
            // so we cannot reuse the current number in the recursion and need to start from the next element
            helper3(k, target - i, i + 1, combo, result);
            combo.removeLast();
        }
    }

    public static void main(String[] args) {
        CombinationSum c = new CombinationSum();
        // int[] candidates = {2, 3, 6, 7};
        // int target = 7;
        // System.out.println(c.combinationSum1(candidates, target));

        // int[] candidates = {10, 1, 2, 7, 6, 1, 5};
        // int target = 8;
        // int[] candidates = {1, 2, 2, 3};
        // int target = 5;
        // System.out.println(c.combinationSum2(candidates, target));

        // int k = 3, n = 15;
        // System.out.println(c.combinationSum3(k, n));

    }
}
