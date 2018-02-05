package leetcode.string;

/*
 * LEETCODE 640   
 * Solve a given equation and return the value of x in the form of string "x=#value". 
 * The equation contains only '+', '-' operation, the variable x and its coefficient. 
 * If there is no solution for the equation, return "No solution".
 * If there are infinite solutions for the equation, return "Infinite solutions".
 * If there is exactly one solution for the equation, we ensure that the value of x is an integer.
 * Example 1: Input: "x+5-3+x=6+x-2" Output: "x=2"
 * Example 2: Input: "x=x" Output: "Infinite solutions"
 * Example 3: Input: "2x=x" Output: "x=0"
 * Example 4: Input: "2x+3x-6x=x+2" Output: "x=-1"
 * Example 5: Input: "x=x+2" Output: "No solution"
 * 
 * Company: Amazon
 * Difficulty: medium
 */
public class SolveEquation {
    private static final String NO_SOLUTION = "No solution";
    private static final String INFINITE_SOLUTION = "Infinite solutions";

    public String solveEquation(String equation) {
        if (equation == null || equation.isEmpty())
            return NO_SOLUTION;

        String[] parts = equation.split("=");
        if (parts.length != 2)
            return NO_SOLUTION;

        int[] left = evaluate(parts[0]);
        int[] right = evaluate(parts[1]);

        if (left[0] == right[0]) {
            if (left[1] == right[1])
                return INFINITE_SOLUTION;
            else
                return NO_SOLUTION;
        }

        return "x=" + ((right[1] - left[1]) / (left[0] - right[0]));
    }

    private int[] evaluate(String s) {
        int[] result = new int[2]; // first element is the coefficient, second
                                   // is the constant

        // regex, split on + or -, and + and - will be in the right hand side
        // https://stackoverflow.com/questions/10804732/what-is-the-difference-between-and-in-regex
        // x+5-2x => {x, +5, -2x}
        // () for match group; 
        // ?= for match and include in res, positive look ahead, the split char will in the right hand side
        // [+-] means + or -;
        String[] tokens = s.split("(?=[+-])"); 
        for (String token : tokens) {
            if (token.equals("x") || token.equals("+x"))
                result[0]++;
            else if (token.equals("-x"))
                result[0]--;
            else if (token.contains("x")) {
            		// need to include the sign here
                result[0] += Integer.parseInt(token.substring(0, token.length() - 1)); 
            } else {
                result[1] += Integer.parseInt(token);
            }
        }

        return result;
    }

    public static void main(String[] args) {
        SolveEquation se = new SolveEquation();
        String equation = "x+5-3+x=6+x-2";
        System.out.println(se.solveEquation(equation));
    }
}
