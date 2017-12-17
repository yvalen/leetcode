package leetcode.stack;

import java.util.Stack;


public class BasicCalculator {
	/*
	 * LEETCODE 224
	 * Implement a basic calculator to evaluate a simple expression string. The expression string may contain 
	 * open ( and closing parentheses ), the plus + or minus sign -, non-negative integers and empty spaces .
	 * You may assume that the given expression is always valid.
	 * Some examples:
	 * "1 + 1" = 2
	 * " 2-1 + 2 " = 3
	 * "(1+(4+5+2)-3)+(6+8)" = 23
	 * 
	 * Company: Google
	 * Difficulty: hard
	 * Similar Questions: 150(EvaluateReversePolishNotation), 227(Basic Calculator II),
	 * 241(DifferentWaysAddParentheses), 282(ExpressionAddOperators)
	 */
	public int calculate(String s) {
		// five possible input: 
		// 1. digit: it should be one digit from the current number
		// 2. '+': number is over, we can add the previous number and start a new number
		// 3. '-': same as above
		// 4. '(': push the previous result and the sign into the stack, set result to 0, 
		// just calculate the new result within the parenthesis.
		// 5. ')': pop out the top two numbers from stack, first one is the sign before this pair of parenthesis, 
		// second is the temporary result before this pair of parenthesis. We add them together.
		// Finally if there is only one number, from the above solution, we haven't add the number to the result, 
		// so we do a check see if the number is zero.

		int result = 0;
		if (s == null || s.length() == 0) return result;

		int number = 0, sign = 1;
		Stack<Integer> stack = new Stack<Integer>();
		for (char ch : s.toCharArray()) {
			if (Character.isDigit(ch)) {
				number = number * 10 + (ch - '0');  // need to multiply by 10 to handle multiple digits
			}
			else if (ch == '+') {
				result += sign * number;
				number = 0;
				sign  = 1;
			}
			else if (ch == '-') {
				result += sign * number;
				number = 0;
				sign  = -1;
			}
			else if (ch == '(') {
				// push result to stack first then sigh
				stack.push(result);
				stack.push(sign);
				// reset result and sign to calculate the value in parenthesis
				// no need to set number to 0 here because the char before ( must be 
				// + or - and number has been reset to 0 when processing that char
				result = 0;
				sign = 1;
			}
			else if (ch == ')') {
				result += sign * number; // value enclosed in parenthesis
				result *= stack.pop();  // apply the sign to the value enclosed in parenthesis
				result += stack.pop();  // add the value enclosed in parenthesis to previous result
				number = 0; // need to reset number here
			}
		}

		if (number != 0) result += sign * number;

		return result;
	}

	/*
	 * LEETCODE 227
	 * Implement a basic calculator to evaluate a simple expression string. The expression string contains only 
	 * non-negative integers, +, -, *, / operators and empty spaces. The integer division should truncate toward zero.
	 * You may assume that the given expression is always valid.
	 * Some examples:
	 * "3+2*2" = 7
	 * " 3/2 " = 1
	 * " 3+5 / 2 " = 5
	 * 
	 * Company: Airbnb
	 * Difficulty: medium
	 * Similar Questions: 244(BasicCalculator), 282(ExpressionAddOperators)
	 */
	public int calculateII(String s) {
		int result = 0;
		if (s == null || s.length() == 0) return result;

		int number = 0;
		char op = '+'; // previous operator, initialize to +
		Stack<Integer> stack = new Stack<>();
		for (int i = 0; i < s.length(); i++) {
			char ch = s.charAt(i);
			if (Character.isDigit(ch)) {

				number = number * 10 + (ch - '0'); // use ch-'0' to get current value
			}
			if (ch == '+' || ch == '-' || ch == '*' || ch == '/' || 
					i == s.length() - 1) {  // need to process the last number, cannot use else if here
				if (op == '*' || op == '/') { // subtract the top element from result before multiply/divide
					result -= stack.peek();
				}
				switch (op) {
				case '+': 
					stack.push(number);
					break;
				case '-':
					stack.push(-number);
					break;
				case '*':
					stack.push(stack.pop() * number);
					break;
				case '/':
					stack.push(stack.pop() / number);
					break;
				}
				op = ch;
				number = 0;
				result += stack.peek(); // update result with the number just pushed onto stack
			}  	
		}
		return result;
	}

	public static void main(String[] args) {
		BasicCalculator bc = new BasicCalculator();
		//String s = "2147483647";
		//System.out.println(bc.calculate(s));

		//String s = "3+2*2";
		String s = "3-2+5";
		System.out.println(bc.calculateII(s));
	}
}
