package leetcode.string;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Given a string, find the length of the longest substring without repeating characters.
 * Examples:
 * 	- Given "abcabcbb", the answer is "abc", which the length is 3.
 * 	- Given "bbbbb", the answer is "b", with the length of 1.
 * 	- Given "pwwkew", the answer is "wke", with the length of 3. 
 * 	Note that the answer must be a substring, "pwke" is a subsequence and not a substring
 * 
 */
public class LongestSubstring {
	
	// time complexity: O(2n) when all characters are same, 
	//  each character will be visited twice by i and j
	// space complexity: O(min(k, m)), set size bounded by 
	// 	the size of the string and the size of the charset/alphabet mmm.
	public int lengthOfLongestSubstring_withSet(String s) {
        if (s == null || s.isEmpty()) return 0;
        
        Set<Character> charSet = new HashSet<>(); 
        int n = s.length(), len = 0;
        for (int i = 0, j = 0; i < n && j < n;) {
        	if (!charSet.contains(s.charAt(j))) {
        		charSet.add(s.charAt(j++));
        		len = Integer.max(j - i, len);
        	}
        	else {
        		charSet.remove(s.charAt(i++));
        	}
        }
        
        return len;
    }
	
	// http://www.tecbar.net/longest-substring-without-repeating-characters-leetcode/
	// One pass:  scan the string from left one by one. If a duplicate character is found 
	// and its recent position is greater than the start position, then adjust the start position. 
	public int lengthOfLongestSubstring_withMap(String s) {
        if (s == null || s.isEmpty()) return 0;
        
        Map<Character, Integer> map = new HashMap<>(); // character to index map
        int len = 0, start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
        	char c = s.charAt(i);
        	if (map.containsKey(c) && start <= map.get(c)) {
        		start = map.get(c) + 1;
        	}
        	map.put(c, i);
        	end = i;
        	len = Integer.max(len, end-start+1);
        }
  
        return len;
    }

}
