package leetcode.math;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
 * Evaluate the value of an arithmetic expression in Reverse Polish Notation.
 * Valid operators are +, -, *, /. Each operand may be an integer or another expression.
 * Some examples:
 * 	["2", "1", "+", "3", "*"] -> ((2 + 1) * 3) -> 9
 * 	["4", "13", "5", "/", "+"] -> (4 + (13 / 5)) -> 6
 * 
 * https://en.wikipedia.org/wiki/Reverse_Polish_notation
 */
public class ReversePolishNotation {
	
	/*
	 * The algorithm for evaluating any postfix expression is fairly straightforward:
	 * While there are input tokens left
	 * 		Read the next token from input.
	 * 		If the token is a value
	 * 			Push it onto the stack.
	 * 		Otherwise, the token is an operator (operator here includes both operators and functions).
	 * 			It is already known that the operator takes n arguments.
	 * 			If there are fewer than n values on the stack
	 * 				(Error) The user has not input sufficient values in the expression.
	 * 			Else, Pop the top n values from the stack.
	 * 			Evaluate the operator, with the values as arguments.
	 * 			Push the returned results, if any, back onto the stack.
	 * 	If there is only one value in the stack
	 * 		That value is the result of the calculation.
	 * 	Otherwise, there are more values in the stack
        	(Error) The user input has too many values.
	 */
	public int evalRPN(String[] tokens) {
		if (tokens == null || tokens.length == 0) return 0;
		
		Deque<Integer> stack = new LinkedList<>();
		for (String token : tokens) {
			if ("+".equals(token)) {
				stack.push(stack.pop() + stack.pop());
			} else if ("-".equals(token)) {
				stack.push(-stack.pop() + stack.pop());
			} else if ("*".equals(token)) {
				stack.push(stack.pop() * stack.pop());
			} else if ("/".equals(token)) {
				int val2 = stack.pop();
				int val1 = stack.pop();
				stack.push(val1 / val2);
			} else {
				stack.push(Integer.valueOf(token)); // parseInt returns primitive type while valueOf returns boxed type
			}
		}
		return stack.size() == 0 ? 0 : stack.pop();
    }

	
}
