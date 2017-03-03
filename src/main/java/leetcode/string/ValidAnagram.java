package leetcode.string;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/*
 * Given two strings s and t, write a function to determine if t is an anagram of s.
 * For example,
 * 	s = "anagram", t = "nagaram", return true.
 * 	s = "rat", t = "car", return false.
 * Note: You may assume the string contains only lowercase alphabets.
 * Follow up: What if the inputs contain unicode characters? How would you adapt your solution to such case?
 */
public class ValidAnagram {
	public boolean isAnagram_withMap(String s, String t) {
		if ((s == null && t != null) ||
				(s != null && t == null) ||
				s.length() != t.length()) {
			return false;
		}
		
        Map<Character, Integer> charCount = new HashMap<>();
        for (Character c : s.toCharArray()) {
        	charCount.put(c, charCount.getOrDefault(c, 0) + 1);
        }
        
        for (Character c : t.toCharArray()) {
        	Integer count = charCount.get(c);
        	if (count == null) return false;
        	charCount.put(c, count-1);
        }
        
        for (Integer count : charCount.values()) {
        	if (count > 0) return false;
        }
        
		return true;
    }
	
	public boolean isAnagram_withSort(String s, String t) {
		if ((s == null && t != null) ||
				(s != null && t == null) ||
				s.length() != t.length()) {
			return false;
		}
		
		char[] schars = s.toCharArray();
		char[] tchars = t.toCharArray();
		Arrays.sort(schars);
		Arrays.sort(tchars);
		
		for (int i = 0; i < schars.length; i++) {
			if (schars[i] != tchars[i]) return false;
		}
		return true;
	}
	
	public boolean isAnagram_withArray(String s, String t) {
		if ((s == null && t != null) ||
				(s != null && t == null) ||
				s.length() != t.length()) {
			return false;
		}
		
		// use an array to track appearance of 26 letters, index will be char-'a'
		int[] alphabet = new int[26];
		for (int i = 0; i < s.length(); i++) alphabet[s.charAt(i)-'a']++; 
		for (int i = 0; i < s.length(); i++) {
			if (--alphabet[t.charAt(i) - 'a'] < 0) return false;
		}
		return true;
	}

}
