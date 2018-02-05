package leetcode.greedy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
 * LEETCODE 763
 * A string S of lower case letters is given. We want to partition this string into 
 * as many parts as possible so that each letter appears in at most one part, and 
 * return a list of integers representing the size of these parts.
 * Example 1:
 * Input: S = "ababcbacadefegdehijhklij"
 * Output: [9,7,8]
 * Explanation:
 * The partition is "ababcbaca", "defegde", "hijhklij".
 * This is a partition so that each letter appears in at most one part. A partition 
 * like "ababcbacadefegde", "hijhklij" is incorrect, because it splits S into less parts.
 * Note:
 * - S will have length in range [1, 500].
 * - S will consist of lowercase letters ('a' to 'z') only.
 * 
 * Company: Amazon
 * Difficulty: medium
 * Similar Questions: 56(MergeIntervals)
 */
public class PartitionLables {
    // Complexity: Time O(n), Space O(n)
    public List<Integer> partitionLabels(String s) {
        if (s == null || s.isEmpty()) return Collections.emptyList();
    
        int[] lastIndex = new int[26];
        for (int i = 0; i < s.length(); i++) {
            lastIndex[s.charAt(i)-'a'] = i;
        }
        
        List<Integer> result = new ArrayList<>();
        for (int start = 0, end = 0, i = 0; i < s.length(); i++) {
            end = Math.max(end, lastIndex[s.charAt(i)-'a']);
            if (end == i) {
                result.add(end-start+1);
                start = end+1;
            }
        }
        
        return result;
    }

}
