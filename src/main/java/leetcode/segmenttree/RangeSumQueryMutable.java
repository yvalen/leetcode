package leetcode.segmenttree;

import java.util.stream.IntStream;

/*
 * Given an integer array nums, find the sum of the elements between indices i and j (i ≤ j), inclusive.
 * The update(i, val) function modifies nums by updating the element at index i to val.
 * Example: Given nums = [1, 3, 5]
 * sumRange(0, 2) -> 9
 * update(1, 2)
 * sumRange(0, 2) -> 8
 * Note:
 * - The array is only modifiable by the update function.
 * - You may assume the number of calls to update and sumRange function is distributed evenly.
 * 
 * Company: 
 * Difficulty: medium
 */
public class RangeSumQueryMutable {
	/*
	private final int n;
	private final int[] tree;
	
	// iterative
	// https://leetcode.com/articles/range-sum-query-mutable/#approach-3-segment-tree-accepted
	public RangeSumQueryMutable(int[] nums) {
		if (nums.length > 0) {
			n = nums.length;
			tree = new int[2*n]; // root is at index 1
			buildTree(nums);
		}
		else {
			n = 0;
			tree = new int[0];
		}
	}
	
	// Time complexity: O(n)
	private void buildTree(int[] nums) {
		// populate leaf nodes, tree has n-1 internal nodes, leaf node starts at n
		for (int i = n, j = 0; i < 2*n; i++, j++) {
			tree[i] = nums[j];
		}
		
		// populate internal nodes from bottom up 
		// left and right child are stored at index 2i and 2i+1 respectively
		for (int i = n - 1; i >= 0; i--) {
			tree[i] = tree[2*i] + tree[2*i+1];
		}
	}
	
	// Time complexity: O(logn)
	public void update(int i, int val) {
		// update the leaf node that stores a[i]. From there follow the path up to the 
		// root updating the value of each parent as a sum of its children values.
		
		// calculate the position in tree and update the element
		int pos = i + n;
		tree[pos] = val;
		
		while (pos > 0) {
			int left = pos, right = pos;
			if (left % 2 == 0) {
				right = pos + 1;
			}
			else {
				left = pos - 1;
			}
			tree[pos/2] = tree[left] + tree[right];
			pos /= 2;
		}
	}
  
    public int sumRange(int i, int j) {
    	// find the leaf node
        int start = i + n, end = j + n;
        int sum = 0;
        while (start <= end) {
        	if (start % 2 == 1) {
        		// start is the right child of its parent P, P contains another child which is outside the query range.
        		// we don't need the parent sum. Add tree[start] to sum and set start to point to the right of P on parent level
        		sum += tree[start];
        		start++;
        	} // else start is left child, P contains sum of range that is inside query range
        	if (end % 2 == 0) {
        		// end is the left child of P, P contains another child which is outside the query range
        		// we don't need the parent sum. add tree[end] to sum and point end to the left of P on parent level
        		sum += tree[end];
        		end--;
        	}
        	start /= 2;
        	end /= 2;
        }
        return sum;
    }
	*/
	
	
	/*
	// recursive solution
	//https://www.hackerearth.com/practice/notes/segment-tree-and-lazy-propagation/
	public RangeSumQueryMutable(int[] nums) {
		if (nums.length > 0) {
			n = nums.length;
			int height = (int) Math.ceil(Math.log(n) / Math.log(2)); // height of the tree
			int treeSize = 2 * (int) Math.pow(2, height) - 1; // size of the balanced tree
			tree = new int[treeSize];
			buildSegmentTree(nums, 0, 0, n-1);
		}
		else {
			n = 0;
			tree = new int[0];
		}
    }
    
    public void update(int i, int val) {
        update(i, val, 0, 0, n-1);
    }
    
    public int sumRange(int i, int j) {
        return sumRange(i, j, 0, 0, n-1);
    }

    // Time Complexity: O(n)
    private void buildSegmentTree(int[] nums, int treeIdx, int lo, int hi) {
    	if (lo == hi) {
    		// leaf node will have a single element
    		tree[treeIdx] = nums[lo];
    		return;
    	}
    	
    	int mid = lo + (hi - lo) / 2;
    	// recurse on the left child
    	buildSegmentTree(nums, 2*treeIdx+1, lo, mid);
    	// recurse on the right child
    	buildSegmentTree(nums, 2*treeIdx+2, mid+1, hi);
    	// merge the result, internal node will have the sum of its two child nodes
    	tree[treeIdx] = tree[2*treeIdx+1] + tree[2*treeIdx+2];
    }
    
    //  look at the interval in which the element is and recurse accordingly on the left or the right child.
    // Time complexity: O(logn)
    private void update(int arrayIdx, int val, int treeIdx, int lo, int hi) {
    	if (lo == hi) {
    		// locate the leaf node and update its value
    		System.out.println("treeIndex =" + treeIdx);
    		tree[treeIdx] = val;
    		return;
    	}
    	
    	int mid = lo + (hi - lo) / 2;
    	if (arrayIdx <= mid) {
    		// element is in the left child
    		update(arrayIdx, val, 2*treeIdx+1, lo, mid);
    	}
    	else {
    		// element is in the righr child
    		update(arrayIdx, val, 2*treeIdx+2, mid+1, hi);
    	}
    	// update the internal node
    	tree[treeIdx] = tree[2*treeIdx+1] + tree[2*treeIdx+2];
    }
    
    private int sumRange(int i, int j, int treeIdx, int lo, int hi) {
    	if (j < lo || i > hi) {
    		// segment is completely outside of query range
    		return 0;
    	}
    	
    	if (i <= lo && j >= hi) {
    		// segment is completely inside of query range
    		return tree[treeIdx];
    	}
    	
    	int mid = lo + (hi - lo) / 2;
    	int sumLeft = sumRange(i, j, 2*treeIdx+1, lo, mid);
    	int sumRight = sumRange(i, j, 2*treeIdx+2, mid+1, hi);
    	return sumLeft + sumRight;
    }
    */
    
	/*
    // Segment Tree
	// with SegmentTreeNode
	private static class SegmentTreeNode {
		int start, end; // range
		int sum;  
		SegmentTreeNode left, right;
		SegmentTreeNode(int start, int end) {
			this.start = start;
			this.end = end;
		}
		SegmentTreeNode(int start, int end, int val) {
			this.start = start;
			this.end = end;
			this.sum = val;
		}
	}
	
	private final SegmentTreeNode root;
	public RangeSumQueryMutable(int[] nums) {
		this.root = buildTree(nums, 0, nums.length-1);
	}
	
	public void update(int i, int val) {
		update(root, i, val);
	}

	public int sumRange(int i, int j) {
		return sumRange(root, i, j);
	}
	
	private SegmentTreeNode buildTree(int[] nums, int start, int end) {
		if (start > end) return null;
		
		if (start == end) return new SegmentTreeNode(start, end, nums[start]);
		
		SegmentTreeNode node = new SegmentTreeNode(start, end);
		int mid = start + (end - start) / 2;
		node.left = buildTree(nums, start, mid);
		node.right = buildTree(nums, mid+1, end);
		node.sum = node.left.sum + node.right.sum;
		return node;
	}
	
	private void update(SegmentTreeNode node, int i, int val) {
		if (node.start == node.end && node.start == i) {
			node.sum = val;
			return;
		}
		
		int mid = node.start + (node.end - node.start) / 2;
		if (i <= mid) update(node.left, i, val);
		else update(node.right, i, val);
		node.sum = node.left.sum + node.right.sum;
	}
	
	private int sumRange(SegmentTreeNode node, int i, int j) {
		if (node == null || j < node.start || i > node.end) return 0;	
		if (i <= node.start && j >= node.end) return node.sum;
		return sumRange(node.left, i, j) + sumRange(node.right, i, j);
	
		
		//if (node.start == i && node.end == j) return node.sum;
		
		//int mid = node.start + (node.end - node.start) / 2;
		//if (j <= mid) return sumRange(node.left, i, j);
		//else if (i > mid)  return sumRange(node.right, i, j);
		
		//return sumRange(node.left, i, mid) + sumRange(node.right, mid+1, j);
		
	}
   */
   
    // Binary Indexed Tree
	// https://www.hackerearth.com/practice/notes/binary-indexed-tree-or-fenwick-tree/
	// http://theoryofprogramming.com/2014/12/24/binary-indexed-tree-or-fenwick-tree/
	// https://www.topcoder.com/community/data-science/data-science-tutorials/binary-indexed-trees/
	int[] BIT;
	private int[] nums;
	private int n;
	public RangeSumQueryMutable(int[] nums) {
		n = nums.length;
		this.nums = new int[n];
		BIT = new int[n + 1];
		for (int i = 0; i < n; i++) {
			update(i, nums[i]);
		}
	}
	
	public void update(int i, int val) {
		int delta = val - nums[i]; 
		nums[i] = val; // need to update nums with the new value
		for (int j = i + 1; j <= n; j += (j & -j)) {
			BIT[j] += delta;
		}
	}

	public int sumRange(int i, int j) {
		return getSum(j) - getSum(i-1);
	}
	
	private int getSum(int idx) {
		int sum = 0;
		idx++;
		while (idx > 0) {
			sum += BIT[idx];
			idx -= (idx & -idx);
		}
		return sum;
	}
	
    public static void main(String[] args) {
    	//int[] nums = {-28, -39, 53, 65, 11, -56, -65, -59, -43, 97};
    	//int[] nums = {9, -8};
    	int[] nums = {7, 2, 7, 2, 0};
    	RangeSumQueryMutable rsqm = new RangeSumQueryMutable(nums);
    	//rsqm.update(0,  3);
    	//System.out.println(rsqm.sumRange(1, 1));
    	IntStream.of(rsqm.BIT).forEach(System.out::print);
		System.out.println();
    	rsqm.update(4, 6);
    	IntStream.of(rsqm.BIT).forEach(System.out::print);
		System.out.println();
		rsqm.update(0, 2);
    	IntStream.of(rsqm.BIT).forEach(System.out::print);
		System.out.println();
		rsqm.update(0, 9);
    	IntStream.of(rsqm.BIT).forEach(System.out::print);
		System.out.println();
		rsqm.update(3, 8);
    	IntStream.of(rsqm.BIT).forEach(System.out::print);
		System.out.println();
		System.out.println(rsqm.sumRange(0, 4));
    }
}
