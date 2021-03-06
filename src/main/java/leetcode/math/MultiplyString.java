package leetcode.math;

/*
 * LEETCODE 43
 * Given two non-negative integers num1 and num2 represented as strings, 
 * return the product of num1 and num2.
 * Note:
 * 	- The length of both num1 and num2 is < 110.
 * 	- Both num1 and num2 contains only digits 0-9.
 * 	- Both num1 and num2 does not contain any leading zero.
 * 	- You must not use any built-in BigInteger library or convert the 
 * inputs to integer directly.
 * 
 * Company: Facebook, Twitter
 * Difficulty: medium
 * Similar Questions: 2(AddTwoNumbers), 67(AddBinary), 415(AddStrings), 66(PlusOne)
 */
public class MultiplyString {
    public String multiply_twoLoop(String num1, String num2) {
        int n1 = num1.length(), n2 = num2.length();
        int[] product = new int[n1 + n2];

        for (int i = n1 - 1; i >= 0; i--) {
            for (int j = n2 - 1; j >= 0; j--) {
                int d1 = num1.charAt(i) - '0', d2 = num2.charAt(j) - '0';
                // place num[i]*num[j] at position i+j+1
                product[i + j + 1] += d1 * d2; // add the new value to existing
                                               // product[i+j+1]
            }
        }

        // now perform carry
        int carry = 0;
        for (int i = n1 + n2 - 1; i >= 0; i--) {
            int val = product[i] + carry; // need to save the value since
                                          // product[i] will be changed in the
                                          // next statement
            product[i] = val % 10;
            carry = val / 10;
        }

        StringBuilder sb = new StringBuilder();
        for (int num : product) {
            if (num == 0 && sb.length() == 0)
                continue; // skip leading zero
            sb.append(num);
        }
        return sb.length() == 0 ? "0" : sb.toString();
    }

    // https://discuss.leetcode.com/topic/30508/easiest-java-solution-with-graph-explanation
    // perform carry while calculating the value
    public String multiply(String num1, String num2) {
        int n1 = num1.length(), n2 = num2.length();
        // num1[i] * num2[j] will be placed at indices [i + j, i + j + 1] of product
        // product[i+j] stores the carry value and product[i+j+1] stores the rightmost digit of the sum
        int[] product = new int[n1 + n2];

        for (int i = n1 - 1; i >= 0; i--) { // calculate from right to left
            for (int j = n2 - 1; j >= 0; j--) {
                int p1 = i+j, p2 = i+j+1;
                int val = product[p2] + (num1.charAt(i) - '0') * (num2.charAt(j) - '0');
                product[p2] = val % 10;
                // calculation is from right to left, product[p1] may >=10, but it will be corrected 
                // in the next iteration when p1 becomes p2
                product[p1] += val / 10;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int num : product) {
            if (num == 0 && sb.length() == 0) continue; // skip leading zero
            sb.append(num);
        }
        return sb.length() == 0 ? "0" : sb.toString(); // return "0" when sb is empty
    }

    public static void main(String[] args) {
        MultiplyString m = new MultiplyString();
        // String num1 = "123", num2 = "45";
        String num1 = "123456789", num2 = "987654321";
        // String num1 = "0", num2 = "0";
        System.out.println(m.multiply(num1, num2));
    }

}
