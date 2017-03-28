package leetcode.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Subset {
	/*
	 * Given a set of distinct integers, return all possible subsets.
	 * Note: The solution set must not contain duplicate subsets.
	 * For example, if nums = [1,2,3], a solution is:
	 * [
	 * 	[3],
	 * 	[1],
	 * 	[2],
	 * 	[1,2,3],
	 * 	[1,3],
	 * 	[2,3],
	 * 	[1,2],
	 * 	[]
	 * ]
	 */
	public List<List<Integer>> subsets(int[] nums) {
		List<List<Integer>> result = new ArrayList<>();
		subsets_helper(nums, result, new LinkedList<>(), 0); 
		return result;
    }

	private void subsets_helper(int[] nums, List<List<Integer>> result, LinkedList<Integer> list, int start) {
		// System.out.println(list);
		// always add to result  
		result.add(new ArrayList<>(list));
		
		for (int i = start; i < nums.length; i++) {
			list.addLast(nums[i]);
			subsets_helper(nums, result, list, i+1); // use i+1 as start only scan element after the current element
			list.removeLast();
		}
	}
	
	public List<List<Integer>> subsets_iterative(int[] nums) {
		List<List<Integer>> result = new ArrayList<>();
		result.add(new ArrayList<>()); // add empty list
		
		for (int i = 0; i < nums.length; i++) {
			int resultSize = result.size(); // need to get the result size before adding more elements to it
			for (int j = 0; j < resultSize; j++) {
				List<Integer> list = new ArrayList<>(result.get(j));
				list.add(nums[i]);
				result.add(list);
			}
		}
		return result;
    }
	
	/*
	 * Given a collection of integers that might contain duplicates, return all possible subsets.
	 * Note: The solution set must not contain duplicate subsets.
	 * For example, if nums = [1,2,2], a solution is:
	 * [
	 * 	[2],
	 * 	[1],
	 * 	[1,2,2],
	 * 	[2,2],
	 * 	[1,2],
	 * 	[]
	 * ]
	 */
	public List<List<Integer>> subsetsWithDup(int[] nums) {
		List<List<Integer>> result = new ArrayList<>();
		Arrays.sort(nums);
		subsetsWithDup_helper(nums, result, new LinkedList<>(), 0); 
		return result;
    }

	private void subsetsWithDup_helper(int[] nums, List<List<Integer>> result, LinkedList<Integer> list, int start) {
		//System.out.println(list);
		result.add(new ArrayList<>(list));
		for (int i = start; i < nums.length; i++) {
			if (i > start && nums[i] == nums[i-1]) {
				continue; // i > start ensures the duplicate element will be used the first time
			}
			list.addLast(nums[i]);
			subsetsWithDup_helper(nums, result, list, i+1); // use i+1 as start only scan element after the current element
			list.removeLast();
		}
    }
	
	
	public static void main(String[] args) {
		Subset s = new Subset();
		int[] nums = {1, 2, 3};
		System.out.println(s.subsets_iterative(nums));
		
		//int[] nums = {1, 2, 2};
		//System.out.println(s.subsetsWithDup(nums));
	}
}
