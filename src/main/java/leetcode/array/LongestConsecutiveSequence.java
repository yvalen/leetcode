package leetcode.array;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/*
 * Given an unsorted array of integers, find the length of the longest consecutive elements sequence.
 * For example, given [100, 4, 200, 1, 3, 2], the longest consecutive elements sequence is [1, 2, 3, 4]. 
 * Return its length: 4. Your algorithm should run in O(n) complexity. 
 */
public class LongestConsecutiveSequence {
	public int longestConsecutive_withSet(int[] nums) {
        // store all numbers in a set
		Set<Integer> numSet = new HashSet<>();
		for (Integer num : nums) {
			numSet.add(num);
		}
		
		int len = 0;
		for (Integer num : nums) {
			// check if num is the start of the streak
			if (!numSet.contains(num-1)) {
				int m = num + 1;
				while (numSet.contains(m)) m++;
				len = Integer.max(len, m - num);
			}
		}
		return len;
    }
	
	// keep track of the sequence length and store that in the boundary points of the sequence. 
	// For example, as a result, for sequence {1, 2, 3, 4, 5}, map.get(1) and map.get(5) should both return 5
	public int longestConsecutive_withMap(int[] nums) {
		Map<Integer, Integer> map = new HashMap<>();
		
		int maxLen = 0;
		for (Integer num : nums) {
			if (!map.containsKey(num)) {
				int l = map.getOrDefault(num-1, 0); // length of the consecutive sequence on the left side of n
				int r = map.getOrDefault(num+1, 0); // length of the consecutive sequence on the right side of n
				int len = l + r + 1; // length of the sequence num is in
				map.put(num,  len); // add num to map to avoid duplicate, only new number should extend the boundary
				maxLen = Integer.max(maxLen, len);
				
				// update the length in boundary
				map.put(num-l, len);
				map.put(num+r, len);
			}
		}
		return maxLen;
    }
	
	public static void main(String[] args) {
		LongestConsecutiveSequence l = new LongestConsecutiveSequence();
		//int[] nums = {4,3,2,1};
		int[] nums = {100, 4, 200, 1, 3, 2};
		System.out.println(l.longestConsecutive_withMap(nums));
	}
}
