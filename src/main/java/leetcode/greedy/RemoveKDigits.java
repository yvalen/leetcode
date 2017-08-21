package leetcode.greedy;

import java.util.Stack;

/*
 * Given a non-negative integer num represented as a string, remove k digits from the number so that the new number is the smallest possible.
 * Note:
 * - The length of num is less than 10002 and will be ≥ k.
 * - The given num does not contain any leading zero.
 * Example 1: 
 * Input: num = "1432219", k = 3
 * Output: "1219"
 * Explanation: Remove the three digits 4, 3, and 2 to form the new number 1219 which is the smallest.
 * 
 * Example 2:
 * Input: num = "10200", k = 1
 * Output: "200"
 * Explanation: Remove the leading 1 and the number is 200. Note that the output must not contain leading zeroes.
 * 
 * Example 3:
 * Input: num = "10", k = 2
 * Output: "0"
 * Explanation: Remove all the digits from the number and it is left with nothing which is 0.
 * 
 * Company: Google, Snapchat
 * Difficulty: medium
 */
public class RemoveKDigits {

	// scan from left to right, and remove the first "peak" digit; 
	// the peak digit is larger than its right neighbor. 
	// repeat this procedure k times
	public String removeKdigits(String num, int k) {
		if (num == null || k >= num.length()) return "0";
		
		while (k > 0) {
			int i = 0;
			while (i < num.length()-1 && num.charAt(i) <= num.charAt(i+1)) i++; 
			num = num.substring(0, i) + num.substring(i+1);
			k--;
		}
		
		while (num.startsWith("0")) {
			num = num.substring(1);
		}
		
		return num.isEmpty() ? "0" : num;
    }
	
	public String removeKdigits_withStack(String num, int k) {
		if (num == null || k >= num.length()) return "0";
		
		Stack<Character> stack = new Stack<>();
		int i = 0;
		while (i < num.length() && k > 0) { 
			while (k > 0 && !stack.isEmpty() && stack.peek() > num.charAt(i)) { // need to check k > 0 here to prevent popping more element than necessary
				stack.pop();
				k--;
			}
			stack.push(num.charAt(i));
			i++;
		}
		
		// handle string with all equal elements such as "1111"
		while (k > 0) {
			stack.pop();
			k--;
		}
		
		// construct return string
		StringBuilder sb = new StringBuilder();
		while (!stack.isEmpty()) {
			sb.insert(0,  stack.pop());
		}
		sb.append(num.substring(i));
		
		// trim leading zero
		while (sb.length() > 0 && sb.charAt(0) == '0') {
			sb.deleteCharAt(0);
		}
		
		return sb.length() == 0 ? "0" : sb.toString();
    }
	
	public static void main(String[] args) {
		RemoveKDigits rkd = new RemoveKDigits();
		String num = "1432219";
		int k = 3;
		
		//String num = "10200";
		//int k = 1;
		System.out.println(rkd.removeKdigits_withStack(num, k));
	}
}
