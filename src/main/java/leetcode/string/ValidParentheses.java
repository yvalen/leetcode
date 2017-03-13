package leetcode.string;

import java.util.Stack;

/*
 * Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
 * The brackets must close in the correct order, "()" and "()[]{}" are all valid but "(]" and "([)]" are not.
 * Test cases: "[", ")"
 */
public class ValidParentheses {
	public boolean isValid_long(String s) {
		if (s == null || s.isEmpty()) return true;
		
		Stack<Character> stack = new Stack<>();
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (c == '(' || c == '{' || c == '[') {
				stack.push(c);
			}
			else {
				if (stack.isEmpty()) return false;
				char matchingChar = stack.pop();
				if (c == ')' && matchingChar != '(') return false;
				if (c == ']' && matchingChar != '[') return false;
				if (c == '}' && matchingChar != '{') return false;
			}
		}
		return stack.isEmpty();
    }
	
	public boolean isValid(String s) {
		if (s == null || s.isEmpty()) return true;
		
		Stack<Character> stack = new Stack<>();
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (c == '(') stack.push(')');
			else if (c == '{') stack.push('}');
			else if (c == '[') stack.push(']');
			else if (stack.isEmpty() || stack.pop() != c) return false;
		}
		return stack.isEmpty();
    }


}
