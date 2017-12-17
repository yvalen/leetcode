package leetcode.divideandconquer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * Given a string of numbers and operators, return all possible results from computing all the different possible ways 
 * to group numbers and operators. The valid operators are +, - and *.
 * Example 1
 * 	Input: "2-1-1".
 * 		((2-1)-1) = 0
 * 		(2-(1-1)) = 2
 * 	Output: [0, 2]
 * Example 2
 * 	Input: "2*3-4*5"
 * 		(2*(3-(4*5))) = -34
 * 		((2*3)-(4*5)) = -14
 * 		((2*(3-4))*5) = -10
 * 		(2*((3-4)*5)) = -10
 * 		(((2*3)-4)*5) = 10
 * 	Output: [-34, -14, -10, -10, 10]
 */
public class DifferentWaysAddParentheses {

    private Map<String, List<Integer>> cache = new HashMap<>();

    public List<Integer> diffWaysToCompute_withSubstring(String input) {
        if (input == null || input.isEmpty())
            return Collections.emptyList();

        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c == '+' || c == '-' || c == '*') {
                String left = input.substring(0, i);
                String right = input.substring(i + 1);
                List<Integer> leftList = cache.getOrDefault(left, diffWaysToCompute_withSubstring(left));
                List<Integer> rightList = cache.getOrDefault(right, diffWaysToCompute_withSubstring(right));
                for (Integer l : leftList) {
                    for (Integer r : rightList) {
                        int current = l + r;
                        if (c == '-')
                            current = l - r;
                        else if (c == '*')
                            current = l * r;
                        result.add(current);
                    }
                }
            }
        }

        if (result.size() == 0)
            result.add(Integer.valueOf(input)); // add to result when there is
                                                // one number

        cache.put(input, result);
        return result;
    }

    public List<Integer> diffWaysToCompute(String input) {
        if (input == null || input.isEmpty())
            return Collections.emptyList();

        return diffWaysToComputeHelper(input, 0, input.length());
    }

    public List<Integer> diffWaysToComputeHelper(String input, int start, int end) {
        List<Integer> result = new ArrayList<>();
        /*
         * if (start == end) { result.add(0); return result; }
         */

        for (int i = start; i < end; i++) {
            char c = input.charAt(i);
            if (c == '+' || c == '-' || c == '*') {
                List<Integer> leftList = diffWaysToComputeHelper(input, start, i);
                List<Integer> rightList = diffWaysToComputeHelper(input, i + 1, end);
                for (Integer left : leftList) {
                    for (Integer right : rightList) {
                        switch (c) {
                        case '+':
                            result.add(left + right);
                            break;
                        case '-':
                            result.add(left - right);
                            break;
                        case '*':
                            result.add(left * right);
                            break;
                        default:
                            break;
                        }
                    }
                }
            }
        }
        if (result.isEmpty())
            result.add(Integer.valueOf(input.substring(start, end)));

        return result;
    }

    public static void main(String[] args) {
        DifferentWaysAddParentheses dwap = new DifferentWaysAddParentheses();
        // String input = "2-1-1";
        String input = "2*3-4*5";
        System.out.println(dwap.diffWaysToCompute(input));
    }
}
