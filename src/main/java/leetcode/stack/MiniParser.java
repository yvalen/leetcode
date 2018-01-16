package leetcode.stack;

import java.util.Stack;

/*
 * LEETCODE 385
 * Given a nested list of integers represented as a string, implement a parser to deserialize it.
 * Each element is either an integer, or a list -- whose elements may also be integers or other lists.
 * Note: You may assume that the string is well-formed:
 * - String is non-empty.
 * - String does not contain white spaces.
 * - String contains only digits 0-9, [, - ,, ].
 * Example 1: Given s = "324", You should return a NestedInteger object which contains a single integer 324.
 * Example 2: Given s = "[123,[456,[789]]]", Return a NestedInteger object containing a nested list with 2 elements:
 * 1. An integer containing value 123.
 * 2. A nested list containing two elements:
 * 	i.  An integer containing value 456.
 * 	ii. A nested list with one element:
 * 		a. An integer containing value 789.
 * 
 * Company: Airbnb
 * Difficulty: medium
 */
public class MiniParser {
    public NestedInteger deserialize(String s) {
        if (!s.startsWith("["))
            return new NestedInteger(Integer.parseInt(s));

        Stack<NestedInteger> stack = new Stack<>();
        NestedInteger ni = null; // initialize to null
        boolean isNegative = false;
        int num = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '[') {
                if (ni != null) { 
                    stack.push(ni); 
                }
                ni = new NestedInteger();
            } else if (Character.isDigit(c)) {
                num = num * 10 + (c - '0');
            } else if (c == '-') {
                isNegative = true;
            } else if (c == ',' && s.charAt(i-1) != ']') {
                ni.add(new NestedInteger(num * (isNegative ? -1 : 1)));
                num = 0;
                isNegative = false;
            } else if (c == ']') {
                if (Character.isDigit(s.charAt(i-1))) {
                    ni.add(new NestedInteger(num * (isNegative ? -1 : 1)));
                    num = 0;
                    isNegative = false;
                }
                if (!stack.isEmpty()) {
                    NestedInteger top = stack.pop();
                    top.add(ni);
                    ni = top;
                }
            }
        }
        return ni;
    }

    public static void main(String[] args) {
        MiniParser mp = new MiniParser();
        //String s = "[123,[456,[789]]]";
        //String s = "[]";
        String s = "[[]]";
        System.out.println(mp.deserialize(s));
    }
}
