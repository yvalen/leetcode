package leetcode.backtracking;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class LetterCombinationOfPhoneNumber {
	private static final String[] KEYS = { "", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz" };
    
	public List<String> letterCombinations(String digits) {
		if (digits == null || digits.isEmpty()) return Collections.emptyList();
        List<String> result = new ArrayList<>();     
        StringBuilder sb = new StringBuilder();
        helper(digits, result, sb, 0);
        return result;
    }
	
	private void helper(String digits, List<String> result, StringBuilder sb, int idx) {
		if (idx == digits.length()) {
			result.add(sb.toString());
			return;
		}

		int digit = Character.getNumericValue(digits.charAt(idx));
		String key = KEYS[digit];
		for (int i = 0; i < key.length(); i++) {
			sb.append(key.charAt(i));
			helper(digits, result, sb, idx + 1);
			sb.setLength(sb.length() - 1);
		}
	}
	
	// BFS, use top element's length to determine the level of traversal, 
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
