package leetcode.stack;

import java.util.Stack;

/*
 * LEETCODE 150
 * Evaluate the value of an arithmetic expression in Reverse Polish Notation(https://en.wikipedia.org/wiki/Reverse_Polish_notation)
 * Valid operators are +, -, *, /. Each operand may be an integer or another expression.
 * Some examples:
 * ["2", "1", "+", "3", "*"] -> ((2 + 1) * 3) -> 9
 * ["4", "13", "5", "/", "+"] -> (4 + (13 / 5)) -> 6
 * Reverse Polish notation (RPN), also known as Polish postfix notation or simply postfix notation, is a mathematical notation in which 
 * operators follow their operands, in contrast to Polish notation (PN), in which operators precede their operands. It does not need any 
 * parentheses as long as each operator has a fixed number of operands. 
 * 
 * Company: LinkedIn
 * Difficulty: easy
 * Similar Questions: 244(BasicCalculator), 282(ExpressionAddOperators)
 */
public class EvaluateReversePolishNotation {
	public int evalRPN(String[] tokens) {
        if (tokens == null || tokens.length == 0) return 0;
        
        Stack<Integer> stack = new Stack<>();
        for (String token : tokens) {
        	if (token.equals("+")) {
                stack.push(stack.pop() + stack.pop());
            }
            else if (token.equals("-")) {
                stack.push(-stack.pop() + stack.pop());
            }
            else if (token.equals("*")) {
                stack.push(stack.pop() * stack.pop());
            }
            else if (token.equals("/")) {
            	int val1 = stack.pop();
                int val2 = stack.pop();
                stack.push(val2 / val1);
            }
            else {
            	stack.push(Integer.parseInt(token));
            }
        }
        return stack.pop();
    }

	public static void main(String[] args) {
		EvaluateReversePolishNotation rpn = new EvaluateReversePolishNotation();
		//String[] tokens = {"18"};
		String[] tokens = {"-3", "9", "*"};
		System.out.println(rpn.evalRPN(tokens));
	}
}
