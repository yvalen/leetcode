package leetcode.stack;

import java.util.Stack;

/*
 * LEETCODE 20
 * Given a string containing just the characters '(', ')', '{', '}', '[' and ']', 
 * determine if the input string is valid. The brackets must close in the correct 
 * order, "()" and "()[]{}" are all valid but "(]" and "([)]" are not.
 * Test cases: "[", ")"
 * 
 * Company: Google, Airbnb, Facebook, Twitter, Zenefits, Amazon, Microsoft, Bloomberg
 * Difficulty: easy
 * Similar Questions: 22(GenerateParentheses), 32(LongestValidParentheses), 
 * 301(RemoveInvalidParentheses)
 */
public class ValidParentheses {
    public boolean isValid_long(String s) {
        if (s == null || s.isEmpty())
            return true;

        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
            } else {
                if (stack.isEmpty())
                    return false; // need to check if stack is empty before pop
                char matchingChar = stack.pop();
                if (c == ')' && matchingChar != '(')
                    return false;
                if (c == ']' && matchingChar != '[')
                    return false;
                if (c == '}' && matchingChar != '{')
                    return false;
            }
        }
        return stack.isEmpty(); // only return true when stack is empty
    }

    public boolean isValid(String s) {
        if (s == null || s.isEmpty())
            return true;

        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(')
                stack.push(')');
            else if (c == '{')
                stack.push('}');
            else if (c == '[')
                stack.push(']');
            else if (stack.isEmpty() || stack.pop() != c)
                return false;
        }
        return stack.isEmpty();
    }

}
