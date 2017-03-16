package leetcode.dp;

import java.util.Stack;

/*
 * Given a string containing just the characters '(' and ')', find the length of the longest valid (well-formed) parentheses substring.
 * For "(()", the longest valid parentheses substring is "()", which has length = 2.
 * Another example is ")()())", where the longest valid parentheses substring is "()()", which has length = 4. 
 * https://leetcode.com/articles/longest-valid-parentheses/
 */
public class LongestValidParentheses {
	
	// Complexity: O(n) - time, O(n) - space
	public int longestValidParentheses_DP(String s) {
		if (s == null || s.length() < 2) return 0;
		
		int n = s.length(), maxLen = 0;
		// dp array stores the length of the longest substring ends at ith index
		// valid substring must end with ), so dp[i]== 0 when s.charAt(i) is (
		int[] dp = new int[n];
		for (int i = 1; i < n; i++) {
			char c = s.charAt(i);
			if (c == ')') {
				if (s.charAt(i-1) == '(') {
					// string looks like ...()
					dp[i] = (i > 2 ? dp[i-2] : 0) + 2; // need to handle ()
				}
				else if (i - dp[i-1] > 0 && s.charAt(i-dp[i-1]-1) == '('){
					// string looks like ...))
					dp[i] = dp[i-1] + 2 + ((i - dp[i-1] >= 2) ? dp[i-dp[i-1] -2 ] : 0);
				}
				maxLen = Integer.max(maxLen, dp[i]);
			}
		}
		return maxLen;
    }
	
	/*
	 * Use stack while scanning the given string to check if the string scanned so far is valid.
	 * - Start by pushing -1 onto the stack. 
	 * - For every ‘(’ encountered push its index onto the stack.
	 * - For every ‘)’ encountered pop the topmost element and subtract the current element's index from the top element of the stack.  
	 * This is gives the length of the currently encountered valid string of parentheses.
	 * - If while popping the element, the stack becomes empty push the current element's index onto the stack. In this way, 
	 * we keep on calculating the lengths of the valid substrings, and return the length of the longest valid string at the end.
	 */
	public int longestValidParentheses_withStack(String s) {
		int maxLen = 0;
		Stack<Integer> stack = new Stack<>();
		stack.push(-1);
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '(') {
				stack.push(i);
			}
			else {
				stack.pop();
				if (stack.isEmpty()) {
					stack.push(i);
				}
				else {
					maxLen = Integer.max(maxLen, i - stack.peek());
				}
			}
		}
		return maxLen;
	}
	
	/*
	 * Use two counters left and right.
	 * - Traverse the string from left to right. Increment left for every ( and right for every )
	 * - Whenever left equals right calculate the length of current valid string  and keep track of maximum length substring found so far.
	 * - If right becomes greater than left reset left and right to 0.
	 * - Repeat the same step from right to left.
	 */
	public int longestValidParentheses_noExtraSpace(String s) {
		int maxLen = 0, left = 0, right = 0, n = s.length();
		
		for (int i = 0; i < n; i++) {
			if (s.charAt(i) == '(') left++;
			else right++;
			if (left == right) {
				maxLen = Integer.max(maxLen, 2 * right);
			}
			else if (right > left) {
				left = 0;
				right = 0;
			}
		}
		left = 0;
		right = 0;
		for (int i = n-1; i >=0; i--) {
			if (s.charAt(i) == '(') left++;
			else right++;
			if (left == right) {
				maxLen = Integer.max(maxLen, 2 * left);
			}
			else if (left > right) {
				left = 0;
				right = 0;
			}
		}
		return maxLen;
	}

	public static void main(String[] args) {
		LongestValidParentheses l = new LongestValidParentheses();
		//String s = "()";
		String s = ")(";
		System.out.println(l.longestValidParentheses_DP(s));
	}
}
