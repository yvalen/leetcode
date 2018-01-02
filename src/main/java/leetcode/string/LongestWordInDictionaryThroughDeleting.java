package leetcode.string;

import java.util.Arrays;
import java.util.List;

/*
 * LEETCODE 524
 * Given a string and a string dictionary, find the longest string in the dictionary that 
 * can be formed by deleting some characters of the given string. If there are more than 
 * one possible results, return the longest word with the smallest lexicographical order. 
 * If there is no possible result, return the empty string.
 * Example 1:
 * Input:
 * s = "abpcplea", d = ["ale","apple","monkey","plea"]
 * Output: "apple"
 * Example 2:
 * Input: s = "abpcplea", d = ["a","b","c"]
 * Output: "a"
 * Note:
 * - All the strings in the input will only contain lower-case letters.
 * - The size of the dictionary won't exceed 1,000.
 * - The length of all the strings in the input won't exceed 1,000.
 * 
 * Company: Google
 * Difficulty: medium
 * Similar Questions: 720(LongestWordInDictionary)
 */
public class LongestWordInDictionaryThroughDeleting {
    
    // iterate through dictionary, check if each dictionary word is subsequence of s
    // if it is, compare its length and lexicographic order with result and update result
    // Time complexity: O(nm), n is the number of words in dictionary, m is the average length of dict word
    public String findLongestWord(String s, List<String> d) {
        String result = "";
        if (s == null || s.isEmpty() || d.isEmpty()) return result;

        for (String t : d) {
            if (isSubsequebce(s, t)) {
                if (t.length() > result.length() || (t.length()==result.length() && t.compareTo(result) < 0)) {
                    result = t;
                }
            }
        }

        return result;
    }

    // check is t is subsequence of s
    // Time complexity: O(m) where m is the length of t
    private boolean isSubsequebce(String s, String t) {
        int j = 0;
        for (int i = 0; i < s.length() && j < t.length(); i++) {
            if (s.charAt(i) == t.charAt(j)) j++;
        }
        return j == t.length();
    }
    
    public static void main(String[] args) {
        LongestWordInDictionaryThroughDeleting lwd = new LongestWordInDictionaryThroughDeleting();
        String s = "abpcplea";
        List<String> d = Arrays.asList("ale","apple","monkey","plea");
        System.out.println(lwd.findLongestWord(s, d));
    }
}
