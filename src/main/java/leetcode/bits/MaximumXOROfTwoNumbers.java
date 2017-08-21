package leetcode.bits;

import java.util.HashSet;
import java.util.Set;

/*
 * Given a non-empty array of numbers, a0, a1, a2, … , an-1, where 0 ≤ ai < 2^31. Find the maximum result of ai XOR aj, where 0 ≤ i, j < n.
 * Could you do this in O(n) runtime?
 * Example: Input: [3, 10, 5, 25, 2, 8] Output: 28
 * Explanation: The maximum result is 5 ^ 25 = 28.
 * 
 * Company: Google
 * Difficulty: medium
 */
public class MaximumXOROfTwoNumbers {
	
	public int findMaximumXOR(int[] nums) {
		int max = 0;  // largest XOR we got so far
		int mask = 0;
	
		// start from the left (31st bits), check every bit
		for (int i = 31; i >= 0; i--) {
			mask |= 1 << i; // mask will grow like  100..000 , 110..000, 111..000,  then 1111...111
			Set<Integer> set = new HashSet<>();
			for (int num : nums) {
				set.add(num & mask); // only check the bits up to i
			}
			
			// now find if there is two bits with different i-th bit
			// if there is the new max should be the current max with i-th bit set to 1
			int candidate = max | (1 << i);
			for (int prefix : set) {
				// if a ^ b = c, then a ^ c = b and b ^ c = a
				if (set.contains(prefix ^ candidate)) {
					max = candidate;
					break;
				}
			}
		}
		
		return max;
    }
	
	
	public static void main(String[] args) {
		MaximumXOROfTwoNumbers mxtn = new MaximumXOROfTwoNumbers();
		int[] nums = {3, 10, 5, 25, 2, 8};
		//int[] nums = {14, 11, 7, 2};
		System.out.println(mxtn.findMaximumXOR(nums));
	}
}
