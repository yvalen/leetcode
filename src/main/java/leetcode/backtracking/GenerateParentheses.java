package leetcode.backtracking;

import java.util.ArrayList;
import java.util.List;

/*
 * LEETCODE 22
 * Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
 * For example, given n = 3, a solution set is:
 * [
 * 	"((()))",
 * 	"(()())",
 * 	"(())()",
 * 	"()(())",
 * 	"()()()"
 * ]
 * 
 * Company: Google, Uber, Zenefits
 * Difficulty: medium
 * Similar Questions: 17(LetterCombinationOfPhoneNumber), 20(ValidParentheses)
 */
public class GenerateParentheses {
    // Use two integers to count the remaining left parenthesis (n) and the
    // right parenthesis (m) to be added
    // At each function call add a left parenthesis if n >0 and add a right
    // parenthesis if m>0.
    // Append the result and terminate recursive calls when both m and n are
    // zero.
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        helper(result, sb, n, n);
        return result;
    }

    private void helper(List<String> result, StringBuilder sb, int open, int close) {
        if (open == 0 && close == 0) {
            result.add(sb.toString());
            return;
        }

        if (open > 0) {
            sb.append('(');
            helper(result, sb, open - 1, close);
            sb.setLength(sb.length() - 1);
        }

        if (close > open) {
            sb.append(')');
            helper(result, sb, open, close - 1);
            sb.setLength(sb.length() - 1);
        }
    }

    public static void main(String[] args) {
        GenerateParentheses g = new GenerateParentheses();

        int n = 3;
        System.out.println(g.generateParenthesis(n));
    }
}
