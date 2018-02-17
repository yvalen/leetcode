package leetcode.string;

/*
 * LEETCODE 65
 * Validate if a given string is numeric.
 * Some examples:
 * "0" => true
 * " 0.1 " => true
 * "abc" => false
 * "1 a" => false"2e10" => true
 * Note: It is intended for the problem statement to be ambiguous. You should gather all requirements up front before implementing one.
 * 
 * Company: LinkedIn
 * Difficulty: medium
 * Similar Questions: 8(StringToInteger)
 */
public class ValidNumber {
    public boolean isNumber(String s) {
        if (s == null || s.isEmpty())
            return false;

        int i = 0, n = s.length();

        // skip leading white space
        while (i < n && Character.isWhitespace(s.charAt(i)))
            i++;

        // skip sign
        if (i < n && (s.charAt(i) == '+' || s.charAt(i) == '-'))
            i++;

        boolean isNumber = false; // need to use this flag to indicate there is
                                  // at lease one digit
        while (i < n && Character.isDigit(s.charAt(i))) {
            isNumber = true;
            i++;
        }

        // check for decimals
        if (i < n && s.charAt(i) == '.') {
            i++;
            while (i < n && Character.isDigit(s.charAt(i))) {
                isNumber = true;
                i++;
            }
        }

        // check for exponential
        if (i < n && isNumber && s.charAt(i) == 'e') {
            i++;
            isNumber = false; // need to make sure there is digits after e
            if (i < n && (s.charAt(i) == '+' || s.charAt(i) == '-'))
                i++;
            while (i < n && Character.isDigit(s.charAt(i))) {
                isNumber = true;
                i++;
            }
        }

        // skip trailing white space
        while (i < n && Character.isWhitespace(s.charAt(i)))
            i++;

        return isNumber && i == n;
    }

    public static void main(String[] args) {
        ValidNumber vn = new ValidNumber();
        // String s = ".";
        // String s = ".1";
        // String s = "2e10";
        String s = "3";
        System.out.println(vn.isNumber(s));
    }
}
