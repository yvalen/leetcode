package leetcode.math;

/*
 * Given an integer, return its base 7 string representation.
 * Example 1: Input: 100 Output: "202"
 * Example 2: Input: -7 Output: "-10"
 * Note: The input will be in range of [-1e7, 1e7]. 
 */
public class Base7 {
    public String convertToBase7(int num) {
        if (num == 0)
            return "0";

        StringBuilder sb = new StringBuilder();
        boolean isNegative = num < 0;
        num = Math.abs(num);
        while (num > 0) {
            sb.append(num % 7);
            num /= 7;
        }
        if (isNegative)
            sb.append("-");
        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        Base7 base7 = new Base7();
        // int num = 100;
        // int num = -7;
        int num = 0;
        System.out.println(base7.convertToBase7(num));
    }

}
