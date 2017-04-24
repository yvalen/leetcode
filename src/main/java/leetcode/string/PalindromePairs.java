package leetcode.string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * Given a list of unique words, find all pairs of distinct indices (i, j) in the given list, 
 * so that the concatenation of the two words, i.e. words[i] + words[j] is a palindrome.
 * Example 1: given words = ["bat", "tab", "cat"] return [[0, 1], [1, 0]]
 * The palindromes are ["battab", "tabbat"]
 * Example 2: given words = ["abcd", "dcba", "lls", "s", "sssll"] return [[0, 1], [1, 0], [3, 2], [2, 4]]
 * The palindromes are ["dcbaabcd", "abcddcba", "slls", "llssssll"]
 */
public class PalindromePairs {
	/*
	 * - Traverse the array, build map. Key is the reversed string, value is index in array (0 based)
	 * - Main logic part. Partition the word into left and right, and see 1) if there exists a candidate in map equals 
	 * to the left side of current word, and right side of current word is palindrome, so concatenate(current word, candidate) 
	 * forms a pair: left | right | candidate. 2) same for checking the right side of current word: candidate | left | right.
	 * - Edge case - check if empty string exists. It's interesting that for given words {"a", ""}, 
	 * it's expected to return two results [0,1] and [1,0]. Since my main logic can cover [0, 1] concatenate("a", ""), 
	 * so as to cover the other situation concatenate("", "a"), I need to traverse the words array again, find the 
	 * palindrome word candidate except "" itself, and add pair("", palindrome word) to the final answer.
	 * Time complexity: O(n*k^2)
	 */
	public List<List<Integer>> palindromePairs(String[] words) {
		if (words == null || words.length == 0) return Collections.emptyList();
		
		// create a dictionary with the reverse of each word as key and its index as value
		Map<String, Integer> dict = new HashMap<>();
		for (int i = 0; i < words.length; i++) {
			StringBuilder sb = new StringBuilder(words[i]);
			dict.put(sb.reverse().toString(), i);
		}
		
		
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < words.length; i++) {
        	// process each word
        	String word = words[i];
        	for (int j = 0; j <= word.length(); j++) { // need to include =
        		// partition the word into two pars
        		String s1 = word.substring(0, j);
        		String s2 = word.substring(j);
        		if (dict.containsKey(s1) && isPalindrome(s2)) {
        			result.add(Arrays.asList(j, dict.get(s1)));
        		}
        		if (dict.containsKey(s2) && isPalindrome(s1)) {
        			result.add(Arrays.asList(dict.get(s1), j));
        		}
        	}
        	
        	
        	
        	
        }
        
        return result;
    }	
	
	private boolean isPalindrome(String s) {
		if (s == null || s.isEmpty()) return true;		
		for (int i = 0, j = s.length()-1; i < j; i++, j--) {
			if (s.charAt(i) != s.charAt(j)) return false;
		}
		return true;
	}
	
}
