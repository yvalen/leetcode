package leetcode.math;

/*
 * LEETCODE 415
 * Given two non-negative integers num1 and num2 represented as string, return the sum of num1 and num2.
 * Note:
 * - The length of both num1 and num2 is < 5100.
 * - Both num1 and num2 contains only digits 0-9.
 * - Both num1 and num2 does not contain any leading zero.
 * - You must not use any built-in BigInteger library or convert the inputs to integer directly.
 * 
 * Company: Google, Airbnb
 * Difficulty: easy
 * Similar Questions: 2(AddTwoNumbers), 43(MultiplyString)
 */
public class AddStrings {
    public String addStrings(String num1, String num2) {
        if (num1 == null || num1.isEmpty())
            return num2;
        if (num2 == null || num2.isEmpty())
            return num1;

        StringBuilder sb = new StringBuilder();
        int carry = 0;
        for (int i = num1.length() - 1, j = num2.length() - 1; i >= 0 || j >= 0; i--, j--) { // check
                                                                                             // i>=0
                                                                                             // ||
                                                                                             // j>=0
                                                                                             // so
                                                                                             // that
                                                                                             // we
                                                                                             // don't
                                                                                             // need
                                                                                             // another
                                                                                             // loop
            // need to add carry to val so that we can get proper carry by using
            // val for the next iteration
            int val = (i >= 0 ? num1.charAt(i) - '0' : 0) + (j >= 0 ? num2.charAt(j) - '0' : 0) + carry;
            sb.append(val % 10);
            carry = val > 9 ? 1 : 0;
        }
        if (carry == 1)
            sb.append(1); // need to check carry at the end
        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        AddStrings as = new AddStrings();
        String num1 = "9", num2 = "99";
        System.out.println(as.addStrings(num1, num2));
    }
}
