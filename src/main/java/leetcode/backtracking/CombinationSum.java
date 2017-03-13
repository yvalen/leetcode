package leetcode.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class CombinationSum {
	/*
	 * Given a set of candidate numbers (C) (without duplicates) and a target number (T), 
	 * find all unique combinations in C where the candidate numbers sums to T.
	 * The same repeated number may be chosen from C unlimited number of times.
	 * Note: All numbers (including target) will be positive integers.
	 * The solution set must not contain duplicate combinations.
	 * For example, given candidate set [2, 3, 6, 7] and target 7, a solution set is:
	 * [
	 *  [7],
	 *	[2, 2, 3]
	 *]
	 */
	public List<List<Integer>> combinationSum1(int[] candidates, int target) {
		if (candidates == null || candidates.length == 0) return Collections.emptyList();
		List<List<Integer>> result = new ArrayList<>();
		//Arrays.sort(candidates);
		helper1(candidates, target, result, new LinkedList<>(), 0);
		return result;
    }
	
	private void helper1(int[] candidates, int target, List<List<Integer>> result, LinkedList<Integer> list, int start) {
		if (target < 0) return;
		
		if (target == 0) {
			result.add(new ArrayList<>(list));
			return;
		}
		
		for (int i = start; i < candidates.length; i++) {
			//if (candidates[i] > target) break;
			list.addLast(candidates[i]);
			helper1(candidates, target - candidates[i], result, list, i); // not i+1 because we can reuse the same element
			list.removeLast();
		}
	}
	
	/*
	 * Given a collection of candidate numbers (C) and a target number (T), 
	 * find all unique combinations in C where the candidate numbers sums to T.
	 * Each number in C may only be used once in the combination.
	 * Note: All numbers (including target) will be positive integers.
	 * The solution set must not contain duplicate combinations.
	 * For example, given candidate set [10, 1, 2, 7, 6, 1, 5] and target 8, a solution set is:
	 * [
	 * 	[1, 7],
	 * 	[1, 2, 5],
	 * 	[2, 6],
	 * 	[1, 1, 6]
	 * ]
	 */
	public List<List<Integer>> combinationSum2(int[] candidates, int target) {
		if (candidates == null || candidates.length == 0) return Collections.emptyList();
		List<List<Integer>> result = new ArrayList<>();
		Arrays.sort(candidates);
		helper2(candidates, target, result, new LinkedList<>(), 0);
		return result;
    }
	
	private void helper2(int[] candidates, int target, List<List<Integer>> result, LinkedList<Integer> list, int start) {
		if (target == 0) {
			System.out.println(list);
			result.add(new ArrayList<>(list));
			return;
		}
		
		for (int i = start; i < candidates.length; i++) {
			if (target - candidates[i] < 0 ) break;
			if (i > start && candidates[i] == candidates[i-1]) continue; // skip duplicates since the one at start position has been used
			list.addLast(candidates[i]);
			helper2(candidates, target - candidates[i], result, list, i+1); 
			list.removeLast();
		}
	}
	
	/*
	 * Find all possible combinations of k numbers that add up to a number n, 
	 * given that only numbers from 1 to 9 can be used and each combination should be a unique set of numbers.
	 * Example 1: Input: k = 3, n = 7 Output: [[1,2,4]]
	 * Example 2: Input: k = 3, n = 9 Output: [[1,2,6], [1,3,5], [2,3,4]]
	 * Example 2: Input: k = 3, n = 15 Output: [[1,5,9],[1,6,8],[2,4,9],[2,5,8],[2,6,7],[3,4,8],[3,5,7],[4,5,6]]
	 */
	public List<List<Integer>> combinationSum3(int k, int n) {
		if (k <= 0 || n <= 0) return Collections.emptyList();
		List<List<Integer>> result = new ArrayList<>();
        helper3(k, n, 1, new LinkedList<>(), result);
		return result;
    }
	
	private void helper3(int k, int n, int start, LinkedList<Integer> list, List<List<Integer>> result) {
		if (n < 0) return;
		
		if (n == 0 && list.size() == k) {
			result.add(new ArrayList<>(list));
			return;
		}
		
		for (int i = start; i <= 9; i++) {
			list.addLast(i);
			helper3(k, n - i, i+1, list, result);
			list.removeLast();
		}
	}
	
	public static void main(String[] args) {
		CombinationSum c = new CombinationSum();
		//int[] candidates = {2, 3, 6, 7};
		//int target = 7;
		//System.out.println(c.combinationSum1(candidates, target));
		
		//int[] candidates = {10, 1, 2, 7, 6, 1, 5};
		//int target = 8;
		//int[] candidates = {1, 2, 2, 3};
		//int target = 5;
		//System.out.println(c.combinationSum2(candidates, target));
		
		int k = 3, n = 15;
		System.out.println(c.combinationSum3(k, n));
	}
}
