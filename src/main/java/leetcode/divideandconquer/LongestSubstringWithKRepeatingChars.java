package leetcode.divideandconquer;

/*
 * LEETCODE 395
 * Find the length of the longest substring T of a given string (consists of lower case letters only) 
 * such that every character in T appears no less than k times.
 * Example 1: 
 * Input: s = "aaabb", k = 3
 * Output: 3
 * The longest substring is "aaa", as 'a' is repeated 3 times.
 * Example 2: 
 * Input: s = "ababbc", k = 2
 * Output: 5
 * The longest substring is "ababb", as 'a' is repeated 2 times and 'b' is repeated 3 times.
 * 
 * Company: Baidu
 * Difficulty: medium
 */
public class LongestSubstringWithKRepeatingChars {
	
	// If every character appears at least k times, the whole string is ok. 
	// Otherwise split by a character that appears less than k times, 
	// because it will always be too infrequent and thus can't be part of any ok substring
	// and make the most out of the splits.
	// Time complexity: With O(1) alphabet size it's O(n) because it does O(n) work at each level 
	// (of the call tree) and there are O(1) levels. With unlimited alphabet, each level takes O(n^2) 
	// and there are O(n) levels. 
	public int longestSubstring(String s, int k) {
		if (s == null || s.length() < k) return 0;
		
		int[] count = new int[26];
		for (char c : s.toCharArray()) count[c-'a']++;
		
		char split = 0;
		for (int i = 0; i < 26; i++) {
			if (count[i] > 0 && count[i] < k) {
				split = (char) (i + 'a');
			}
		}
		
		if (split == 0) return s.length();
		
		String[] substrs = s.split(String.valueOf(split));
		int maxLen = 0;
		for (String substr : substrs) {
			maxLen = Math.max(maxLen, longestSubstring(substr, k));
		}
		
		return maxLen;
    }

}
