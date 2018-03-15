package leetcode.array;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * LEETCODE 792
 * Given string S and a dictionary of words words, find the number of words[i] 
 * that is a subsequence of S.
 * Example :
 * Input: S = "abcde" words = ["a", "bb", "acd", "ace"]
 * Output: 3
 * Explanation: There are three words in words that are a subsequence of S: 
 * "a", "acd", "ace".
 * Note:
 * - All words in words and S will only consists of lower case letters.
 * - The length of S will be in the range of [1, 50000].
 * - The length of words will be in the range of [1, 5000].
 * - The length of words[i] will be in the range of [1, 50].
 * 
 * Company: Google
 * Difficulty: medium
 * Similar Questions: 392(IsSubsequence)
 */
public class NumberOfMatchingSubsequences {
    public int numMatchingSubseq(String S, String[] words) {
        Map<Character, List<Integer>> indexMap = new HashMap<>();
        for (int i = 0; i < S.length(); i++) {
            indexMap.putIfAbsent(S.charAt(i), new ArrayList<>());
            indexMap.get(S.charAt(i)).add(i);
        }
        
        int count = 0;
        for (String word : words) {
            if (isSubsequence(indexMap, word)) count++;
        }
        return count;
    }
    
    private boolean isSubsequence(Map<Character, List<Integer>> indexMap, String word) {
        int prev = -1;
        for (char c : word.toCharArray()) {
            if (!indexMap.containsKey(c)) return false;
            prev = search(indexMap.get(c), prev);
            if (prev < 0) return false;
            prev++;
        }
        return true;
    }
    
    private int search(List<Integer> list, int target) {
        int lo = 0, hi = list.size()-1;
        while (lo <= hi) {
            int mid = (lo+hi)/2;
            if (list.get(mid) < target) lo = mid+1;
            else hi = mid - 1;
        }
        return (lo == list.size() ? -1 : list.get(lo));
    }
}
