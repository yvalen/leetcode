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
        if (num1 == null || num1.isEmpty()) return num2;
        if (num2 == null || num2.isEmpty()) return num1;

        StringBuilder sb = new StringBuilder();
        int sum = 0;
        for (int i = num1.length() - 1, j = num2.length()-1; i >= 0 || j >= 0;) {
            sum /= 10;
            if (i >= 0) {
                sum += num1.charAt(i--) - '0';
            }
            if (j >= 0) {
                sum += num2.charAt(j--) - '0';
            }
            sb.append(sum % 10);
        }
        if (sum >= 10) sb.append(sum/10);
        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        AddStrings as = new AddStrings();
        String num1 = "9", num2 = "99";
        System.out.println(as.addStrings(num1, num2));
    }
}
