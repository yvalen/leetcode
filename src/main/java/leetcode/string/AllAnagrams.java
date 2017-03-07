package leetcode.string;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * Given a string s and a non-empty string p, find all the start indices of p's anagrams in s.
 * Strings consists of lower case English letters only and the length of both strings s and p will not be larger than 20,100.
 * The order of output does not matter.
 */
public class AllAnagrams {
	public List<Integer> findAnagrams(String s, String p) {
        List<Integer> result = new ArrayList<>();
        
        if (s == null || s.isEmpty() || p == null || s.length() < p.length()) return result;
        
        int pLen = p.length();
        int[] hash = new int[128];
        for (int i = 0; i < pLen; i++) {
        	hash[p.charAt(i)]++;
        }
        
        int left = 0, right = 0, count = p.length(); 
        while (right < s.length()) {
        	if (hash[s.charAt(right)] > 0) {
        		count--;
        	}
        	hash[s.charAt(right)]--;
        	right++;
        	
        	if (count == 0) {
        		result.add(left);
        	}
        	
        	if (right - left == p.length()) {
        		if (hash[s.charAt(left)] >= 0) {
        			count++; 
        		}
        		hash[s.charAt(left)]++;
        		left++;
        	}
        }
        
        return result;
    }
	
	
	public static void main(String[] args) {
		AllAnagrams a = new AllAnagrams();
		
		String s = "baebabacb", p = "abc";
		System.out.println(a.findAnagrams(s, p));
		
	}
}
