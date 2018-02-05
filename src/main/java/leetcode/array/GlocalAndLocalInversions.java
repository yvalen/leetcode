package leetcode.array;

/*
 * LEETCODE 775
 * We have some permutation A of [0, 1, ..., N - 1], where N is the length of A.
 * The number of (global) inversions is the number of i < j with 0 <= i < j < N 
 * and A[i] > A[j]. The number of local inversions is the number of i with 
 * 0 <= i < N and A[i] > A[i+1]. Return true if and only if the number of global 
 * inversions is equal to the number of local inversions.
 * Example 1:
 * Input: A = [1,0,2]
 * Output: true
 * Explanation: There is 1 global inversion, and 1 local inversion.
 * Example 2:
 * Input: A = [1,2,0]
 * Output: false
 * Explanation: There are 2 global inversions, and 1 local inversion.
 * Note:
 * - A will be a permutation of [0, 1, ..., A.length - 1].
 * - A will have length in range [1, 5000].
 * 
 * Company: Amazon
 * Difficulty: medium
 */
public class GlocalAndLocalInversions {
	// every local inversion is also a global inversion
	// the count of local should <= count of global, all we care is when local < global happens.
	// The difference between local and global is global also include non adjacent i and j, 
	// so simplify the question to for every i, find in range 0 to i-2, see if there is a element 
	// larger than A[i], if it exist, we can return false directly. and we can maintain a variable 
	// max for the linear implementation.
	public boolean isIdealPermutation(int[] A) {
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < A.length-2; i++) {
			max = Math.max(max, A[i]);
			if (max > A[i+2]) return false;
		}
		return true;
    }
}
