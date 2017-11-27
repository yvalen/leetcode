package leetcode.backtracking;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/*
 * LEETCODE 17
 * Given a digit string, return all possible letter combinations that the number could represent.
 * A mapping of digit to letters (just like on the telephone buttons) is given below.
 * Input:Digit string "23"
 * Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
 * Note: Although the above answer is in lexicographical order, your answer could be in any order you want. 
 * 
 * Company: Google, Facebook, Amazom, Uber, Dropbox
 * Difficulty: medium
 * Similar Questions: 39(Combination Sum), 22(GenerateParentheses), 401(BinaryWatch)
 */
public class LetterCombinationOfPhoneNumber {
	private static final String[] KEYS = { "", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz" };
    
	// Time complexity: O(k^n) where k is the average length of strings in KEYS
	public List<String> letterCombinations(String digits) {
		if (digits == null || digits.isEmpty()) return Collections.emptyList();
        List<String> result = new ArrayList<>();     
        helper(digits, result, new StringBuilder(), 0);
        return result;
    }
	
	private void helper(String digits, List<String> result, StringBuilder sb, int idx) {
		if (idx == digits.length()) {
		//if (sb.length() == digits.length()) {
			result.add(sb.toString());
			return;
		}
		/*
		for (int i = idx; i < digits.length(); i++) {
            int digit = Character.getNumericValue(digits.charAt(i));
            for (Character c : KEYS[digit].toCharArray()) {
                sb.append(c);
                helper(digits, result, sb, i+1);
                sb.setLength(sb.length()-1);
            }
        }
        */

		int digit = Character.getNumericValue(digits.charAt(idx));
		String key = KEYS[digit];
		for (int i = 0; i < key.length(); i++) {
			sb.append(key.charAt(i));
			helper(digits, result, sb, idx + 1);
			sb.setLength(sb.length() - 1);
		}
	}
	
	// BFS, use top element's length to determine the level of traversal, 
	// Time complexity: n - number of digits, k - mapping length
	// outer loop execute n times, in each iteration we access the front elements in the queue with length of i
	// (worst case would be n). This makes it O(n^2). Inner loop runs k time, O(k*n^2)
	public List<String> letterCombinations_iterative(String digits) {
		if (digits == null || digits.isEmpty()) return Collections.emptyList();
 
        LinkedList<String> list = new LinkedList<>();
        list.add("");
        for (int i = 0; i < digits.length(); i++) {
        	String key = KEYS[Character.getNumericValue(digits.charAt(i))];
        	while (list.peek().length() == i) {
        		String s = list.remove();
        		for (int j = 0; j < key.length(); j++) {
        			list.add(s + key.charAt(j));	
        		}
        	}
        }
        
        return list;
    }
	
	public static void main(String[] args) {
		LetterCombinationOfPhoneNumber l = new LetterCombinationOfPhoneNumber();
		
		String digits = "23";
		System.out.println(l.letterCombinations(digits));
		
	}

}
