package leetcode.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Permutations {
	/*
	 * LEETCODE 46
	 * Given a collection of distinct numbers, return all possible permutations.
	 * For example, [1,2,3] have the following permutations:
	 * [
	 * 	[1,2,3],
	 * 	[1,3,2],
	 * 	[2,1,3],
	 * 	[2,3,1],
	 * 	[3,1,2],
	 * 	[3,2,1]
	 * ]
	 * 
	 * Company: Microsoft, LinkedIn
	 * Difficulty: medium
	 * Similar Questions: 47(Permutations II), 77(Combination), 31(NextPermutation), 60(PermutationSequence)
	 */
	// Complexity: O(n^n) - time, O(n) - space
	public List<List<Integer>> permute(int[] nums) {
		if (nums == null || nums.length == 0) return Collections.emptyList();
		List<List<Integer>> result = new ArrayList<>();
		permute_helper(nums, result, new LinkedList<Integer>(), new boolean[nums.length]);
		return result;
    }
	
	private void permute_helper(int[] nums, List<List<Integer>> result, LinkedList<Integer> list, boolean[] used) {
		if (list.size() == nums.length) {
			System.out.println(list);
			result.add(new ArrayList<>(list)); // create a new array since list is changing all the time
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
	
	/*
	 * LEETCODE 47
	 * Given a collection of numbers that might contain duplicates, return all possible unique permutations.
	 * For example, [1,1,2] have the following unique permutations:
	 * [
	 * 	[1,1,2],
	 * 	[1,2,1],
	 * 	[2,1,1]
	 * ]
	 * 
	 * Company: Microsoft, LinkedIn
	 * Similar Questions: 46(Permutations), 31(NextPermutation)
	 */
	// Complexity: O(n^n) - time, O(n) - space
	public List<List<Integer>> permuteUnique(int[] nums) {
		if (nums == null || nums.length == 0) return Collections.emptyList();
		List<List<Integer>> result = new ArrayList<>();
		
		
		// Sort the input array to make sure we can skip the same value.	
		Arrays.sort(nums);
		permuteUnique_helper(nums, result, new LinkedList<>(), new boolean[nums.length]);
		return result;
    }
	
	private void permuteUnique_helper(int[] nums, List<List<Integer>> result, LinkedList<Integer> list, boolean[] used) {
		if (list.size() == nums.length) {
			System.out.println("add to result:" + list);
			result.add(new ArrayList<>(list));
			return;
		}
		
		for (int i = 0; i < nums.length; i++) {
			if (used[i]) continue;
			// when a number has the same value with its previous, we can use this number only if its previous is used
			if (i > 0 && nums[i] == nums[i-1] && used[i-1]) continue;
			
			list.addLast(nums[i]);
			used[i] = true;
			System.out.println(list);
			permuteUnique_helper(nums, result, list, used);
			list.removeLast();
			used[i] = false;
		}
	}
	
	public static void main(String[] args) {
		Permutations p = new Permutations();
		//int[] nums = {1, 2, 3};
		int[] nums = {1, 1, 2};
		System.out.println(p.permuteUnique(nums));
	}
}
