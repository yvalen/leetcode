package leetcode.bfsdfs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
 * LEETCODE 282
 * Given a string that contains only digits 0-9 and a target value, return all
 * possibilities to add binary operators (not unary) +, -, or * between the 
 * digits so they evaluate to the target value.
 * Examples:
 * 	"123", 6 -> ["1+2+3", "1*2*3"]
 * 	"232", 8 -> ["2*3+2", "2+3*2"]
 * 	"105", 5 -> ["1*0+5","10-5"]
 * 	"00", 0 -> ["0+0", "0-0", "0*0"]
 * 	"3456237490", 9191 -> []
 * 
 * Company: Google, Facebook
 * Difficulty: hard
 */
public class ExpressionAddOperators {
    // Use DFS for output all possible solutions
    // Time complexity: O(4^n)
    // Each digit has four different options: +, -, *, nothing.
    // T(n) = 3 * T(n-1) + 3 * T(n-2) + 3 * T(n-3) + ... + 3 *T(1);
    // T(n-1) = 3 * T(n-2) + 3 * T(n-3) + ... 3 * T(1);
    // Thus T(n) = 4T(n-1);
    public List<String> addOperators(String num, int target) {
        if (num == null || num.length() == 0)
            return Collections.emptyList();

        List<String> result = new ArrayList<>();
        dfs(num, target, result, 0, new StringBuilder(), 0, 0);
        return result;
    }

    // use long for currentResult to prevent overflow
    private void dfs(String num, int target, List<String> result, int start, StringBuilder sb, long currentResult, long prev) {
        if (start == num.length()) { // all chars in num have been processed
            if (target == currentResult) {
                result.add(sb.toString());
            }
            return;
        }
        
        for (int i = start; i < num.length(); i++) {
            if (i != start && num.charAt(start) == '0') {
                // handle 0 sequence, we cannot have multiple digits
                // starting with 0, e.g. 01, 02
                break;
            }
            
            // get the current number to process,
            // combination of digits after start (inclusive)
            long currentNum = Long.parseLong(num.substring(start, i+1));
            int len = sb.length();
            if (start == 0) { // don't prepend operator for the first digit
                dfs(num, target, result, i+1, sb.append(currentNum), currentNum, currentNum);
                sb.setLength(len);
            }
            else {
                // add op
                dfs(num, target, result, i+1, sb.append("+").append(currentNum), currentResult+currentNum, currentNum);
                sb.setLength(len);
                
                // subtract op
                dfs(num, target, result, i+1, sb.append("-").append(currentNum), currentResult-currentNum, -currentNum);
                sb.setLength(len);
                
                // multiply op
                dfs(num, target, result, i+1, sb.append("*").append(currentNum), currentResult-prev+prev*currentNum, prev*currentNum);
                sb.setLength(len);
            }
        }
    }
    
    public static void main(String[] args) {
        ExpressionAddOperators eao = new ExpressionAddOperators();
        // String num = "123";
        // int target = 6;
        String num = "232";
        int target = 8;
        System.out.println(eao.addOperators(num, target));
    }
}
