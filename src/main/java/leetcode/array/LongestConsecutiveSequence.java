package leetcode.array;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/*
 * LEETCODE 128
 * Given an unsorted array of integers, find the length of the longest 
 * consecutive elements sequence.
 * For example, given [100, 4, 200, 1, 3, 2], the longest consecutive 
 * elements sequence is [1, 2, 3, 4]. Return its length: 4. 
 * Your algorithm should run in O(n) complexity. 
 * 
 * Company: Google, Facebook
 * Difficulty: hard
 */
public class LongestConsecutiveSequence {
    // Time complexity: O(n+n) ~ O(n)
    // Space complexity: O(1)
    public int longestConsecutive_withSet(int[] nums) {
        // store all numbers in a set
        // Set<Integer> numSet =
        // IntStream.of(nums).boxed().collect(Collectors.toSet());
        Set<Integer> numSet = new HashSet<>();
        for (int num : nums) {
            numSet.add(num);
        }

        int len = 0;
        // loop through set instead of nums to avoid checking duplicate 
        // start number multiple times
        for (Integer num : numSet) { 
            // check if num is the start of the streak, i.e.
            // num-1 is not in set. only check element in descending order
            if (!numSet.contains(num - 1)) { 
                int m = num + 1;
                while (numSet.contains(m))
                    m++; // check if num+1, num+2,... are in set, stop at the
                         // first number m that is not in set
                len = Integer.max(len, m - num); // the length of the streak is
                                                 // m-num
            }
        }
        return len;
    }

    // keep track of the sequence length and store that in the boundary points
    // of the sequence.
    // For example, as a result, for sequence {1, 2, 3, 4, 5}, map.get(1) and
    // map.get(5) should both return 5
    // Whenever a new element n is inserted into the map, do two things:
    // 1. See if n - 1 and n + 1 exist in the map, and if so, it means there is
    // an existing sequence next to n.
    // Variables left and right will be the length of those two sequences, while
    // 0 means there is no sequence and
    // n will be the boundary point later. Store (left + right + 1) as the
    // associated value to key n into the map.
    // 2. Use left and right to locate the other end of the sequences to the
    // left and right of n respectively,
    // and replace the value with the new length.
    public int longestConsecutive_withMap(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();

        int maxLen = 0;
        for (Integer num : nums) {
            if (!map.containsKey(num)) {
                int l = map.getOrDefault(num - 1, 0); // length of the
                                                      // consecutive sequence on
                                                      // the left side of n
                int r = map.getOrDefault(num + 1, 0); // length of the
                                                      // consecutive sequence on
                                                      // the right side of n
                int len = l + r + 1; // length of the sequence num is in
                map.put(num, len); // add num to map to avoid duplicate, only
                                   // new number should extend the boundary
                maxLen = Integer.max(maxLen, len);

                // update the length in boundary
                map.put(num - l, len);
                map.put(num + r, len);
            }
        }
        return maxLen;
    }

    public static void main(String[] args) {
        LongestConsecutiveSequence l = new LongestConsecutiveSequence();
        // int[] nums = {4,3,2,1};
        int[] nums = { 100, 4, 200, 1, 3, 2 };
        System.out.println(l.longestConsecutive_withMap(nums));
    }
}
