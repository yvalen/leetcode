package leetcode.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

// http://www.sigmainfy.com/blog/summary-of-ksum-problems.html
public class Sum {
	/**
	 * Given an array of integers, return indices of the two numbers such that they add up to a specific target.
	 * You may assume that each input would have exactly one solution.
	 */
	public int[] twoSum_twoPasses(int[] nums, int target) {
		int[] result = {-1, -1}; 
		
        Map<Integer, Integer> map = new HashMap<>(nums.length);
        
        for (int i = 0; i < nums.length; i++) {
        	map.put(nums[i], i);
        }
        
        for (int i = 0; i < nums.length; i++) {
        	Integer valToFind = target - nums[i];
        	Integer idxToFind = map.get(valToFind);
        	if (idxToFind != null && idxToFind != i) {
        		result[0] = i;
        		result[1] = map.get(valToFind);
        		break;
        	}
        }
        
        return result;
    }
	public int[] twoSum_onePass(int[] nums, int target) {
		int[] result = {-1, -1}; 
        Map<Integer, Integer> map = new HashMap<>(nums.length);
        
        for (int i = 0; i < nums.length; i++) {
        	Integer valToFind = target - nums[i];
        	if (map.containsKey(valToFind)) {
        		result[0] = i;
        		result[1] = map.get(valToFind);
        		break;
        	}
        	map.put(nums[i], i);
        }
        
        return result;
    }
	

	/**
	 * Given an array of integers that is already sorted in ascending order, 
	 * find two numbers such that they add up to a specific target number.
	 */
	public int[] twoSum_sorted(int[] nums, int target) {
		int[] result = {-1, -1}; 
        
        for (int i = 0, j = nums.length - 1; i < j;) {
        	int sum = nums[i] + nums[j];
        	if (sum == target) {
        		result[0] = i + 1;
        		result[1] = j + 1;
        		break;
        	}
        	else if (sum < target) {
        		i++;
        	}
        	else {
        		j--;
        	}
        }
        
        return result;
    }
	
	// using binary search 
	public int[] twoSum_sortedBinarySearch(int[] nums, int target) {
		int[] result = {-1, -1}; 
        
        for (int i = 0; i < nums.length; i++) {
        	Integer valToFind = target - nums[i];
        	int idx = binarySearch(nums, i+1, nums.length-1, valToFind);
        	if (idx != -1) {
        		result[0] = i + 1;
        		result[1] = idx + 1;
        		break;
        	}
        }
        
        return result;
    }
	
	private int binarySearch(int nums[], int low, int high, int value) {
		while (low <= high) {
			int mid = (high + low) / 2;
			if (nums[mid] == value) return mid;
			
			if (nums[mid] > value) high = mid - 1;
			else low = mid + 1;
		}
		
		return -1;
	}
	
	
	/**
	 * Given an array S of n integers, are there elements a, b, c in S such that a + b + c = 0? 
	 * Find all unique triplets in the array which gives the sum of zero.
	 */
	public List<List<Integer>> threeSum(int[] nums) {
		List<List<Integer>> result = new ArrayList<>();
		Arrays.sort(nums);
		int n = nums.length;
		for (int i = 0; i < n; i++) {
			if (i > 0 && nums[i] == nums[i-1]) continue;
		
			int j = i + 1, k = n - 1;
			while (j < k) {
				if (j > i+1 && nums[j] == nums[j-1])  {
					j++;
					continue;
				}
			
				if (k < n-1 && nums[k] == nums[k+1])  {
					k--;
					continue;
				}
				
				int sum = nums[i] + nums[j] + nums[k];
				if (sum == 0) {
					result.add(Arrays.asList(nums[i], nums[j], nums[k]));
					j++;
					k--;
				}
				else if (sum < 0) {
					j++;
				}
				else {
					k--;
				}
			}
		}
		
		return result;
        
    }
	
	/**
	 * Given an array S of n integers, find three integers in S such that the sum is closest to a given number, target. 
	 * Return the sum of the three integers. You may assume that each input would have exactly one solution.
	 * For example, given array S = {-1 2 1 -4}, and target = 1.
	 * The sum that is closest to the target is 2. (-1 + 2 + 1 = 2)
	 */
	public int threeSumClosest(int[] nums, int target) {
		int minDiff = Integer.MAX_VALUE, closetSum = target, n = nums.length;
		Arrays.sort(nums);
		
		for (int i = 0; i < n; i++) {
			if (i > 0 && nums[i] == nums[i-1]) continue;
		
			int j = i + 1, k = n - 1;
			while (j < k) {
				if (j > i+1 && nums[j] == nums[j-1])  {
					j++;
					continue;
				}
			
				if (k < n-1 && nums[k] == nums[k+1])  {
					k--;
					continue;
				}
				
				int diff = nums[i] + nums[j] + nums[k] -target;
				if (diff == 0) {
					return target;
				}
				
				if (diff < 0) {
					j++;
				}
				else {
					k--;
				}
				
				if (minDiff > Math.abs(diff)) {
					minDiff = Math.abs(diff);
					closetSum = diff + target;
				}
			}
		}
		
		return closetSum;
    }
	
	
	/**
	 * Given an array S of n integers, are there elements a, b, c, and d in S such that a + b + c + d = target? 
	 * Find all unique quadruplets in the array which gives the sum of target.
	 */
	public List<List<Integer>> fourSum(int[] nums, int target) {
		List<List<Integer>> result = new ArrayList<>();
		Arrays.sort(nums);
		int n = nums.length;
        for (int i = 0; i < n; i++) {
        	if (i > 0 && nums[i] == nums[i-1]) continue;
        	
        	for (int j = i + 1; j < n; j++) {
        		if (j > i+1 && nums[j] == nums[j-1]) continue;
        		
        		int k = j + 1, l = n - 1;
        		while (k < l) {
        			if (k > j + 1 && nums[k] == nums[k-1]) {
        				k++;
        				continue;
        			}
        			
        			if (l < n - 1 && nums[l] == nums[l+1]) {
        				l--;
        				continue;
        			}
        			
        			int sum = nums[i] + nums[j] + nums[k] + nums[l];
        			if (sum == target) {
        				result.add(Arrays.asList(nums[i], nums[j], nums[k], nums[l]));
        				k++;
        				l--;
        			}
        			else if (sum < target) {
        				k++;
        			}
        			else {
        				l--;
        			}
        		}
        	}
        }
		
		return result;
    }
	
	
	public static void main(String[] args) {
		Sum s = new Sum();
		int[] nums = {1, 1, 1, 0};
		/*
		int[] result = s.twoSum_sorted(nums, 9);
		IntStream.of(result).forEach(System.out::println);
		System.out.println(result);
		*/
		
		/*
		List<List<Integer>> threeSum = s.threeSum(nums);
		System.out.println(threeSum);
		*/
		
		int threeSumCloset = s.threeSumClosest(nums, 100);
		System.out.println(threeSumCloset);
	}

}
