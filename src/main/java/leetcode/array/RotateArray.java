package leetcode.array;

/*
 * LEETCODE 189
 * Rotate an array of n elements to the right by k steps.
 * For example, with n = 7 and k = 3, the array [1,2,3,4,5,6,7] is rotated to [5,6,7,1,2,3,4]. 
 * 
 * Company: Microsoft, Amazon, Bloomberg
 * Difficulty: easy
 * Similar Questions: 61(RotateList), 186(Reverse Words in a String II)
 */      
public class RotateArray {
	
	public void rotate(int[] nums, int k) {
		int n = nums.length;
		if (n<=1 || k== 0 || k == n) return;
		if (k > n) k = k%n;
        reverse(nums, 0, n-k-1);
        reverse(nums, n-k, n-1);
		reverse(nums, 0, n-1);
    }
	
	private void reverse(int[] nums, int start, int end) {
		while (start < end) {
			int temp = nums[start];
			nums[start] = nums[end];
			nums[end] = temp;
			start++;
			end--;
		}
	}
	

	
	public static void main(String[] args) {	
		RotateArray r = new RotateArray();
		int[] nums = new int[] {2, 2, 2, 2, 0, 1, 1, 2};
		
		/*
		int[] nums = new int[] {1, 2, 3, 4, 5, 6};
		r.rotate(nums, 2);
		ArrayUtil.printArray(nums);
		 */
		
		/*
		int min = r.findMin_binarySearch(nums);
		System.out.println(min);
		*/
		
		/*
		boolean found = r.search_withDuplicate(nums, 1);
		System.out.println(found);
		*/
		
	}
}
