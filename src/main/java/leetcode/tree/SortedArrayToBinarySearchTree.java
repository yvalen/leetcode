package leetcode.tree;

public class SortedArrayToBinarySearchTree {
	
	public TreeNode sortedArrayToBST(int[] nums) {
        if (nums == null || nums.length == 0) return null;
		
		return sortedArrayToBST_helper(nums, 0, nums.length - 1);
    }

	private TreeNode sortedArrayToBST_helper(int[] nums, int start, int end) {
		if (end < start) return null;
		
		int mid = start + (end-start) / 2;
		TreeNode root = new TreeNode(nums[mid]);
		root.left = sortedArrayToBST_helper(nums, start, mid - 1);
		root.right = sortedArrayToBST_helper(nums, mid + 1, end);
		return root;
	}
}
