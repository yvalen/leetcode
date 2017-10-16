package leetcode.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.IntStream;

// http://www.sigmainfy.com/blog/summary-of-ksum-problems.html
public class Sum {
	/**
	 * LEETCODE 1
	 * Given an array of integers, return indices of the two numbers such that they add up to a specific target.
	 * You may assume that each input would have exactly one solution.
	 * Example: Given nums = [2, 7, 11, 15], target = 9, Because nums[0] + nums[1] = 2 + 7 = 9, return [0, 1].
	 * 
	 * Company: LinkedIn, Uber, Airbnb. Facebook, Amazon, Microsoft, Apple, Yahoo, Dropbox, Bloomberg, Yelp, Adobe
	 * Difficulty: easy
	 * Similar Questions: 15(3sum), 18(4sum), 167(Two Sum II, sorted input array), 653(TwoSumBST), 560(SubarraySumEqualsK),
	 * 170(TwoSum, data structure design)
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
		int[] result = new int[2];
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            if (map.containsKey(target-num)) {
                result[0] = i;
                result[1] = map.get(target-num);
                break;
            }
            map.put(num, i);
        }
        return result;
    }
	

	/**
	 * LEETCODE 167
	 * Given an array of integers that is already sorted in ascending order, 
	 * find two numbers such that they add up to a specific target number.
	 * The function twoSum should return indices of the two numbers such that 
	 * they add up to the target, where index1 must be less than index2. 
	 * Please note that your returned answers (both index1 and index2) are not zero-based.
	 * You may assume that each input would have exactly one solution and you may not use the same element twice.
	 * Input: numbers={2, 7, 11, 15}, target=9
	 * Output: index1=1, index2=2 
	 * 
	 * Company: Amazon
	 * Difficulty: easy
	 * Similar Questions: 1(Two Sum), 653(TwoSumBST)
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
	 * LEETCODE 15
	 * Given an array S of n integers, are there elements a, b, c in S such that a + b + c = 0? 
	 * Find all unique triplets in the array which gives the sum of zero.
	 * Note: The solution set must not contain duplicate triplets.
	 * For example, given array S = [-1, 0, 1, 2, -1, -4],
	 * A solution set is: [[-1, 0, 1], [-1, -1, 2]]
	 * 
	 * Company: Facebook, Microsoft, Bloomberg, Amazon, Adobe, Works Applications
	 * Difficulty: medium
	 * Similar Questions: 1(2sum), 16(3sum closest), 18(4sum), 259(ThreeSumSmaller)
	 */
	public List<List<Integer>> threeSum(int[] nums) {
		if (nums == null || nums.length < 3) return Collections.emptyList();
		
		List<List<Integer>> result = new ArrayList<>();
		Arrays.sort(nums);
		for (int i = 0; i < nums.length-2; i++) {
            if (i > 0 && nums[i] == nums[i-1]) continue;
            int target = 0 - nums[i];
            for (int j = i+1, k = nums.length-1; j < k;) {
            	// skip the same value to de-dup for the two pointers -2,0,0,2,2
                if (j > i+1 && nums[j] == nums[j-1])  { 
					j++;
					continue;
				}
				if (k < nums.length-1 && nums[k] == nums[k+1])  {
					k--;
					continue;
				}
                int sum = nums[j] + nums[k];
                if (sum == target) {
                    result.add(Arrays.asList(nums[i], nums[j], nums[k]));
                    // cannot break here as there might be other elements between the two pointer 
                    // that add up to sum, -1,0,1,2,-1,-4
                    j++;
                    k--;
                }
                else if (sum < target) j++;
                else k--;
            }
        }
        return result;
		/*
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
		*/
        
    }
	
	/**
	 * LEETCODE 16
	 * Given an array S of n integers, find three integers in S such that the sum is closest to a given number, target. 
	 * Return the sum of the three integers. You may assume that each input would have exactly one solution.
	 * For example, given array S = {-1 2 1 -4}, and target = 1.
	 * The sum that is closest to the target is 2. (-1 + 2 + 1 = 2)
	 * 
	 * Company: Bloomberg
	 * Difficulty: medium
	 * Similar Questions: 15(3sum), 259(ThreeSumSmaller)
	 */
	public int threeSumClosest(int[] nums, int target) {
		Arrays.sort(nums);
        int minDiff = Integer.MAX_VALUE, closestSum = target;
        for (int i = 0; i < nums.length-2; i++) {
            for (int j = i+1, k = nums.length-1; j < k;) {
                int sum = nums[i] + nums[j] + nums[k];
                if (sum == target) return target;
                else if (sum < target) j++;
                else k--;
                
                int diff = Math.abs(sum-target);
                if (diff < minDiff) { // update mindiff and closestsum along the way
                    minDiff = diff;
                    closestSum = sum;
                }
            }
        }
        return closestSum;
		/*
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
		*/
    }
	
	
	/**
	 * LEETCODE 18
	 * Given an array S of n integers, are there elements a, b, c, and d in S such that a + b + c + d = target? 
	 * Find all unique quadruplets in the array which gives the sum of target.
	 * 
	 * Difficulty: 1(2sum), 15(3sum), 454(4sum II)
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
	
	/**
	 * LEETCODE 454
	 * Given four lists A, B, C, D of integer values, compute how many tuples (i, j, k, l) there are such that A[i] + B[j] + C[k] + D[l] is zero.
	 * To make problem a bit easier, all A, B, C, D have same length of N where 0 ≤ N ≤ 500. All integers are in the range of -2^28 to 2^28 - 1 and 
	 * the result is guaranteed to be at most 2^31 - 1.
	 * Example:
	 * Input:
	 * A = [ 1, 2]
	 * B = [-2,-1]
	 * C = [-1, 2]
	 * D = [ 0, 2]
	 * Output:
	 * 2
	 * Explanation:
	 * The two tuples are:
	 * 1. (0, 0, 0, 1) -> A[0] + B[0] + C[0] + D[1] = 1 + (-2) + (-1) + 2 = 0
	 * 2. (1, 1, 0, 0) -> A[1] + B[1] + C[0] + D[0] = 2 + (-1) + (-1) + 0 = 0
	 * 
	 * Difficulty: medium
	 * Similar Questions: 18(4sum)
	 */
	// Time xomplexity: O(n^2)
	public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
		int n = A.length;
        Map<Integer, Integer> sumMap = new HashMap<>();
        
        for (int i = 0; i < n; i++) {
        	for (int j = 0; j < n; j++) {
        		int sum = A[i] + B[j];
        		sumMap.put(sum, sumMap.getOrDefault(sum, 0)+1);
        	}
        }
        
        int count = 0;
        for (int i = 0; i < n; i++) {
        	for (int j = 0; j < n; j++) {
        		int sum = C[i] + D[j];
        		count += sumMap.getOrDefault(0-sum, 0);
        	}
        }
        return count;
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
