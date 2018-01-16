package leetcode.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

// https://www.mathsisfun.com/combinatorics/combinations-permutations.html
public class Permutations {
    /*
     * LEETCODE 46 
     * Given a collection of distinct numbers, return all possible permutations. 
     * For example, [1,2,3] have the following permutations: [
     * [1,2,3], [1,3,2], [2,1,3], [2,3,1], [3,1,2], [3,2,1] ]
     * 
     * Company: Microsoft, LinkedIn 
     * Difficulty: medium 
     * Similar Questions: 47(Permutations II), 77(Combination), 31(NextPermutation),
     * 60(PermutationSequence)
     */
    // Time Complexity: T(n) = n*T(n-1)+O(1) ~ O(n!)
    // Space complexity: O(n)
    public List<List<Integer>> permute(int[] nums) {
        if (nums == null || nums.length == 0)
            return Collections.emptyList();
        List<List<Integer>> result = new ArrayList<>();
        // need to use LinkedList for backtracking. cannot use set, use an additional array 
        // to track which number has been used
        permute_helper(nums, result, new LinkedList<Integer>(), new boolean[nums.length]);
        return result;
    }

    private void permute_helper(int[] nums, List<List<Integer>> result, LinkedList<Integer> list, boolean[] used) {
        if (list.size() == nums.length) {
            System.out.println(list);
            // create a new array since list is changing all the time during backtracking
            result.add(new ArrayList<>(list)); 
            return;
        }

        for (int i = 0; i < nums.length; i++) { // always start from 0
            // Use an extra boolean array to indicate whether the value is added to list.
            // need to check whether this element has been used since we always start from the beginning
            if (used[i]) continue;

            // if (list.contains(nums[i])) continue;
            list.addLast(nums[i]);
            used[i] = true;
            permute_helper(nums, result, list, used);
            list.removeLast();
            used[i] = false;
        }
    }

    // add the nth number into the resulting List<List<Integer>> from the n-1 numbers, in every possible position.
    // For example, if the input num[] is {1,2,3}: First, add 1 into the initial List<List<Integer>> 
    // Then, 2 can be added in front or after 1. So we have to copy the List<Integer> in answer (it's just {1}), 
    // add 2 in position 0 of {1}, then copy the original {1} again, and add 2 in position 1. Now we have an answer 
    // of {{2,1},{1,2}}.
    // Then we have to add 3. first copy {2,1} and {1,2}, add 3 in position 0; then copy {2,1} and {1,2}, and add 3 
    // into position 1, then do the same thing for position 3. Finally we have 2*3=6 lists in answer, which is what we want.
    // Time complexity: for the first position you have n possible candidates, then for the second position you have 
    // n-1 possible candidates, for the 3rd position you have n-2 candidates, etc. Therefore the complexity is O(n!).
    public List<List<Integer>> permute_iterative(int[] nums) {
        if (nums == null || nums.length == 0)
            return Collections.emptyList();
        List<List<Integer>> result = new ArrayList<>();
        result.add(new ArrayList<>());
        for (int i = 0; i < nums.length; i++) { 
            // temp stores the result of current iteration
            List<List<Integer>> temp = new ArrayList<>();
            for (List<Integer> list : result) {
                for (int j = 0; j <= list.size(); j++) {
                    // insert nums[i] at different positions of the current list
                    List<Integer> newList = new ArrayList<>(list);
                    newList.add(j, nums[i]);
                    temp.add(newList);
                }
            }
            result = temp; // assign temp to result for next iteration
        }
        return result;
    }


    /*
     * LEETCODE 47 
     * Given a collection of numbers that might contain duplicates,
     * return all possible unique permutations. 
     * For example, [1,1,2] have the following unique permutations: 
     * [ [1,1,2], [1,2,1], [2,1,1] ]
     * 
     * Company: Microsoft, LinkedIn 
     * Similar Questions: 46(Permutations), 31(NextPermutation)
     */
    // Complexity: O(n^n) - time, O(n) - space
    public List<List<Integer>> permuteUnique(int[] nums) {
        if (nums == null || nums.length == 0)
            return Collections.emptyList();
        List<List<Integer>> result = new ArrayList<>();

        // Sort the input array to make sure we can skip the same value.
        Arrays.sort(nums);
        permuteUnique_helper(nums, result, new LinkedList<>(), new boolean[nums.length]);
        return result;
    }

    private void permuteUnique_helper(int[] nums, List<List<Integer>> result, LinkedList<Integer> list,
            boolean[] used) {
        if (list.size() == nums.length) {
            System.out.println("add to result:" + list);
            result.add(new ArrayList<>(list));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (used[i]) continue;

            // when a number has the same value as the previous number, 
            // we can use this number only if its previous is used
            if (i > 0 && nums[i] == nums[i - 1] && used[i - 1])
                continue;

            list.addLast(nums[i]);
            used[i] = true;
            System.out.println(list);
            permuteUnique_helper(nums, result, list, used);
            list.removeLast();
            used[i] = false;
        }
    }

    public List<List<Integer>> permuteUnique_iterative(int[] nums) {
        if (nums == null || nums.length == 0)
            return Collections.emptyList();
        List<List<Integer>> result = new ArrayList<>();
        result.add(new ArrayList<>());
        for (int i = 0; i < nums.length; i++) { 
            // temp stores the result of current iteration
            List<List<Integer>> temp = new ArrayList<>();
            for (List<Integer> list : result) {
                for (int j = 0; j <= list.size(); j++) {
                    List<Integer> newList = new ArrayList<>(list);
                    newList.add(j, nums[i]);
                    temp.add(newList);
                    // break here since we don't insert a number after its duplicate
                    if (j < list.size() && nums[i] == list.get(j)) break;
                }
            }
            result = temp; // assign temp to result for next iteration
        }
        return result;
    }

    public static void main(String[] args) {
        Permutations p = new Permutations();
        //int[] nums = {1, 2, 3};
        //System.out.println(p.permute_iterative(nums));
        //int[] nums = { 1, 1, 2 };
        int[] nums = { 1, 1, 2, 2};
        System.out.println(p.permuteUnique_iterative(nums));
    }
}
