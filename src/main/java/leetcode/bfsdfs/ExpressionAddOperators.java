package leetcode.bfsdfs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
 * Given a string that contains only digits 0-9 and a target value, return all possibilities to add 
 * binary operators (not unary) +, -, or * between the digits so they evaluate to the target value.
 * Examples:
 * 
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
    // Time complexity:
    public List<String> addOperators(String num, int target) {
        if (num == null || num.length() == 0)
            return Collections.emptyList();

        List<String> result = new ArrayList<>();
        dfs(num, target, result, "", 0, 0, 0);

        return result;
    }

    public void dfs(String num, int target, List<String> result, String path, int position, long currentResult,
            long prev) { // use long for currentResult for prevent overflow
        if (position == num.length()) { // all chars in num have been processed
            if (target == currentResult) {
                result.add(new String(path));
            }
            return;
        }

        for (int i = position; i < num.length(); i++) {
            if (i != position && num.charAt(position) == '0') { // handle 0
                                                                // sequence, we
                                                                // cannot have
                                                                // multiple
                                                                // digits
                                                                // starting with
                                                                // 0
                break;
            }
            long currentNum = Long.parseLong(num.substring(position, i + 1)); // get
                                                                              // the
                                                                              // current
                                                                              // number
                                                                              // to
                                                                              // process,
                                                                              // combination
                                                                              // of
                                                                              // digits
                                                                              // after
                                                                              // position
                                                                              // (inclusive)
            if (position == 0) { // don't prepend operator for the first digit
                dfs(num, target, result, path + currentNum, i + 1, currentNum, currentNum);
            } else {
                // add op
                dfs(num, target, result, path + "+" + currentNum, i + 1, currentResult + currentNum, currentNum);

                // subtract op
                dfs(num, target, result, path + "-" + currentNum, i + 1, currentResult - currentNum, -currentNum);

                // multiply op
                dfs(num, target, result, path + "*" + currentNum, i + 1, currentResult - prev + prev * currentNum,
                        prev * currentNum);
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
