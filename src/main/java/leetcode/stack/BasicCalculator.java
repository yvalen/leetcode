package leetcode.stack;

import java.util.Stack;

import javafx.scene.layout.StackPane;

public class BasicCalculator {
    /*
     * LEETCODE 224 
     * Implement a basic calculator to evaluate a simple expression string. 
     * The expression string may contain open ( and closing parentheses ), 
     * the plus + or minus sign -, non-negative integers and empty spaces .
     * You may assume that the given expression is always valid. Some examples:
     * "1 + 1" = 2 " 2-1 + 2 " = 3 "(1+(4+5+2)-3)+(6+8)" = 23
     * 
     * Company: Google 
     * Difficulty: hard 
     * Similar Questions: 150(EvaluateReversePolishNotation), 227(Basic Calculator II),
     * 241(DifferentWaysAddParentheses), 282(ExpressionAddOperators)
     */
    public int calculate(String s) {
        // five possible input:
        // 1. digit: it should be one digit from the current number
        // 2. '+': number is over, we can add the previous number and start a
        // new number
        // 3. '-': same as above
        // 4. '(': push the previous result and the sign into the stack, set
        // result to 0,
        // just calculate the new result within the parenthesis.
        // 5. ')': pop out the top two numbers from stack, first one is the sign
        // before this pair of parenthesis,
        // second is the temporary result before this pair of parenthesis. We
        // add them together.
        // Finally if there is only one number, from the above solution, we
        // haven't add the number to the result,
        // so we do a check see if the number is zero.

        int result = 0;
        if (s == null || s.length() == 0)
            return result;

        int number = 0, sign = 1;
        Stack<Integer> stack = new Stack<Integer>();
        for (char ch : s.toCharArray()) {
            if (Character.isDigit(ch)) {
                // need to multiply by 10 to handle multiple digits
                number = number * 10 + (ch - '0'); 
            } else if (ch == '+' || ch == '-') {
                result += sign * number;
                number = 0;
                sign = ch == '-' ? -1 : 1;
            } else if (ch == '(') {
                // push result to stack first then sigh
                stack.push(result);
                stack.push(sign);
                // reset result and sign to calculate the value in parenthesis
                // no need to set number to 0 here because the char before (
                // must be + or - and number has been reset to 0 when processing 
                // that char
                result = 0;
                sign = 1;
            } else if (ch == ')') {
                // value enclosed in parenthesis
                result += sign * number; 
                // apply the sign to the value enclosed in parenthesis
                result *= stack.pop(); 
                // add the value enclosed in parenthesis to previous result
                result += stack.pop(); 
                // need to reset number here to handle (1)
                // no need to reset sign here as the next char after can only be + or -
                number = 0; 
            }
        }

        // need to process the last number, e.g 1
        if (number != 0) {
            result += sign * number;
        }

        return result;
    }

    /*
     * LEETCODE 227 
     * Implement a basic calculator to evaluate a simple expression string. 
     * The expression string contains only non-negative integers, +, -, *, / 
     * operators and empty spaces. The integer division should truncate toward zero. 
     * You may assume that the given expression is always valid.
     * Some examples: "3+2*2" = 7 " 3/2 " = 1 " 3+5 / 2 " = 5
     * 
     * Company: Airbnb 
     * Difficulty: medium 
     * Similar Questions: 244(BasicCalculator), 282(ExpressionAddOperators)
     */
    // perform multiplication and division first (they are on the lower level 
    // in the expression tree), then subtract and addition.
    public int calculateII(String s) {
        int result = 0;
        if (s == null || s.length() == 0)
            return result;

        int number = 0;
        char op = '+'; // previous operator, initialize to +
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (Character.isDigit(ch)) {
                number = number * 10 + (ch - '0'); 
            }
            
            // process the last number, cannot use else if as it could be end of string
            if (ch == '+' || ch == '-' || ch == '*' || ch == '/' || i == s.length() - 1) { 
                /*
                if (op == '*' || op == '/') { 
                    // subtract the top element from result before multiply/divide
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
                // update result with the number just pushed onto stack
                result += stack.peek(); 
                */
                
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
            }
        }
        
        while (!stack.isEmpty()) result += stack.pop();
        return result;
    }

    
    /*
     * LEETCODE 772
     * Implement a basic calculator to evaluate a simple expression string.
     * The expression string contains only non-negative integers, +, -, *, / 
     * operators , open ( and closing parentheses ) and empty spaces . The 
     * integer division should truncate toward zero. You may assume that the 
     * given expression is always valid. All intermediate results will be in 
     * the range of [-2147483648, 2147483647].
     * Some examples:
     * "1 + 1" = 2
     * " 6-4 / 2 " = 4
     * "2*(5+5*2)/3+(6/2+8)" = 21
     * "(2+6* 3+5- (3*14/7+2)*5)+3"=-12
     * Note: Do not use the eval built-in library function.
     * 
     * Company: Microsoft, Pocket Gem, Hulu, Houzz, Doordash, Jingchi
     * Difficulty: hard
     * Similar Questions: 224(Basic Calculator),  227(Basic Calculator II),
     * 770(Basic Calculator IV)
     */
    public int calculateIII(String s) {
        if (s == null || s.isEmpty()) return 0;
        
        int result = 0, num = 0;
        Stack<Integer> numStack = new Stack<>();
        Stack<Character> opStack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == ' ') continue;
            if (Character.isDigit(c)) {
                num = c - '0';
                while (i < s.length() - 1 && Character.isDigit(s.charAt(i+1))) {
                    num = num * 10 + num;
                    i++;
                }
                numStack.push(num);
                num = 0; // reset the number to 0 before next calculation
            }
            else if (c == '(') {
                opStack.push(c);
            }
            else if (c == ')') {
                while (opStack.peek() != '(') {
                    numStack.push(performOperation(opStack.pop(), numStack.pop(), numStack.pop()));
                }
                opStack.pop(); // get rid of '(' in the ops stack
            }
            else if (c == '+' || c == '-' || c == '*' || c == '/') {
               while (!opStack.isEmpty() && hasPrecedence(c, opStack.peek())) {
                   numStack.push(performOperation(opStack.pop(), numStack.pop(), numStack.pop()));
               }
               opStack.push(c);
            }
        }
        
        while (!opStack.isEmpty()) {
            numStack.push(performOperation(opStack.pop(), numStack.pop(), numStack.pop()));
        }
        return numStack.pop();
    }
    
    private int performOperation(char c, int b, int a) {
        switch (c){
        case '+' : return a + b;
        case '-': return a - b;
        case '*': return a * b;
        case '/': return b == 0 ? 0 :  a /b;
        }
        return 0;
    }
    
    // helper function to check precedence of current operator 
    // and the top operator in the ops stack, op1 is current
    private static boolean hasPrecedence(char op1, char op2) {
        if (op2 == '(' || op2 == ')') return false;
        if ((op1 == '*' || op1 == '/') && (op2 == '+' || op2 == '-')) return false;
        return true;
    }
    
    public static void main(String[] args) {
        BasicCalculator bc = new BasicCalculator();
        // String s = "2147483647";
        // System.out.println(bc.calculate(s));

       // String s = "3+2*2";
        //String s = "2*2+3";
        //String s = "3-2+5";
        //System.out.println(bc.calculateII(s));
        
        String s = "1 + 1";
        //String s = " 6-4 / 2 ";
        //String s = "2*(5+5*2)/3+(6/2+8)" ;
        System.out.println(bc.calculateIII(s));
    }
}
