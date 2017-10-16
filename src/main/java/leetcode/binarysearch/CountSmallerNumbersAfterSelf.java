package leetcode.binarysearch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import leetcode.array.ArrayUtil;

/*
 * LEETCODE 315
 * You are given an integer array nums and you have to return a new counts array. The counts array 
 * has the property where counts[i] is the number of smaller elements to the right of nums[i].
 * Example: Given nums = [5, 2, 6, 1]
 * To the right of 5 there are 2 smaller elements (2 and 1).
 * To the right of 2 there is only 1 smaller element (1).
 * To the right of 6 there is 1 smaller element (1).
 * To the right of 1 there is 0 smaller element.
 * Return the array [2, 1, 1, 0]. 
 * http://www.cnblogs.com/grandyang/p/5078490.html
 * 
 * Company: Google
 * Difficulty: hard
 * Similar Questions:
 */
public class CountSmallerNumbersAfterSelf {
	
	// Binary Search
	// Traverse from the back to the beginning of the array, maintain an sorted array of numbers have been visited.
	// Use findInsertPosition() to find the FIRST element in the sorted array which is larger or EQUAL to target number.
	// For example, [5,2,3,6,1], when we reach 2, we have a sorted array[1,3,6], findIndex() returns 1, which is the 
	// index where 2 should be inserted and is also the number smaller than 2. Then we insert 2 into the sorted array 
	// to form [1,2,3,6].
	// Time complexity: O(n^2) list inserstion is O(n)
	public List<Integer> countSmaller_binarySearch(int[] nums) {
        if (nums == null || nums.length == 0) return Collections.emptyList();
        
        LinkedList<Integer> result = new LinkedList<>();
        List<Integer> sorted = new ArrayList<>();
        for (int i = nums.length-1; i >= 0; i--) {
        	int idx = findInsertPosition(sorted, nums[i]);
        	sorted.add(idx, nums[i]);
        	result.addFirst(idx);
        }
        
        return result;
    }
	
	// find the smallest index in list with which value >= target
	private int findInsertPosition(List<Integer> sorted, int target) {
		if (sorted.isEmpty()) return 0;
		
		int start = 0, end = sorted.size()-1;
		if(sorted.get(start) >= target) return start;  // should check for equal here because we need to return smallest index
		if (sorted.get(end) < target) return end + 1; 
		while (start < end) { // find the first occurrence of target
			int mid = start + (end - start) / 2; 
			if (sorted.get(mid) < target) start = mid + 1;
			else end = mid;
		}
		return start;
	}
	
	// Binary Search Tree
	// build a binary search tree, Node class contains the value as well as how many elements are less than that number
	// iterate through the nums array from right to left, every node will remember all lesser values we have already processed. 
	// Thus, by a result of your tree traversal while you insert, you can build the result you want to return. 
	// Time Complexity: O(nlogn) avaerage case, o(n^2) worst case when nums is sorted
	private static class Node {
		int val;
		int lessThan;
		Node left;
		Node right;
		Node(int val) {this.val = val;}
	}
	
	public List<Integer> countSmaller_binarySearchTree(int[] nums) {
        if (nums == null || nums.length == 0) return Collections.emptyList();
        
        Integer[] result = new Integer[nums.length];
        Node root = null;
        for (int i = nums.length-1; i >= 0; i--) {
        	root = insert(root, nums[i], i, 0, result);
        }
        return Arrays.asList(result);
    }
	
	private Node insert(Node node, int val, int index, int count, Integer[] result) { // count is the current count of elements smaller than 
		if (node == null) {
			node = new Node(val);
			result[index] = count;
		}
		else if (val < node.val) { // insert to the left
			node.lessThan++;
			node.left = insert(node.left, val, index, count, result);
		}
		else { // insert to the right
			node.right = insert(node.right, val, index, count+node.lessThan+(node.val==val?0:1), result); // only add 1 to count if node.val is greater than val, don't add for dup
		}
		return node;
	}
	
	
	// merge sort
	// The smaller numbers on the right are exactly those that jump from its right to its left during a stable sort. 
	// do mergesort with added tracking of those right-to-left jumps. 
	// during merge
	private static class ValueIndex {
		int val;
		int index;
		ValueIndex(int val, int index) {
			this.val = val;
			this.index = index;
		}
		ValueIndex(ValueIndex another) {
			this.val = another.val;
			this.index = another.index;
		}
		@Override
		public String toString() {
			return "ValueIndex [val=" + val + ", index=" + index + "]";
		}
		
	}
	
	public List<Integer> countSmaller_mergeSort(int[] nums) {
        if (nums == null || nums.length == 0) return Collections.emptyList();
 
        ValueIndex[] sorted = new ValueIndex[nums.length];
        for (int i = 0; i < nums.length; i++) sorted[i] = new ValueIndex(nums[i], i);
        
        int[] count = new int[nums.length];
        mergesort(new ValueIndex[nums.length], sorted, count, 0, nums.length-1);
        
        List<Integer> result = new ArrayList<>(nums.length);
        for (int c : count) {
        	result.add(c);
        }
        return result;
    }
	
	private void mergesort(ValueIndex[] aux, ValueIndex[] sorted, int[] count, int lo, int hi) {
		if (hi <= lo) return;

		int mid = lo + (hi - lo) / 2;
		mergesort(aux, sorted, count, lo, mid);
		mergesort(aux, sorted, count, mid+1, hi);
		
		for (int k = lo; k <= hi; k++) aux[k] = new ValueIndex(sorted[k]);
		
		for (int i = lo, j = mid+1, k = lo; k <= hi; k++) {
    		if (j > hi || (i <= mid && aux[i].val <= aux[j].val)) { // need to check for equal here so that we don't increment for dups
    			// Each time we choose a left to the merged array, we want to know how many numbers 
    			// from the right are moved before this number.
				sorted[k] = aux[i];
				count[aux[i].index] += j - (mid+1);
				i++;
			}
			else { // only take item from right if it is smaller, for equal items take from left to preserve the order so that it is stable sort
				sorted[k] = aux[j];
				j++;
			}
		}
	}
	
	
	public static void main(String[] args) {
		CountSmallerNumbersAfterSelf cmns = new CountSmallerNumbersAfterSelf();
		//int[] nums = {5,2,6,1};
		int[] nums = {-1, -1};
		System.out.println(cmns.countSmaller_mergeSort(nums));
	}
}
