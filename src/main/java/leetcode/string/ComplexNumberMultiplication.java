package leetcode.string;

/*
 * LEETCODE 537
 * Given two strings representing two complex numbers. You need to return a string representing their multiplication. 
 * Note i^2 = -1 according to the definition.
 * Example 1: Input: "1+1i", "1+1i" Output: "0+2i"
 * Explanation: (1 + i) * (1 + i) = 1 + i2 + 2 * i = 2i, and you need convert it to the form of 0+2i.
 * Example 2: Input: "1+-1i", "1+-1i" Output: "0+-2i"
 * Explanation: (1 - i) * (1 - i) = 1 + i2 - 2 * i = -2i, and you need convert it to the form of 0+-2i.
 * Note:
 * - The input strings will not have extra blank.
 * - The input strings will be given in the form of a+bi, where the integer a and b will both belong to the range of [-100, 100]. 
 * And the output should be also in this form.
 * 
 * Company: Amazon
 * Difficulty: medium
 */
public class ComplexNumberMultiplication {
    public String complexNumberMultiply(String a, String b) {
        if (a == null || b == null)
            return null;

        StringBuilder sb = new StringBuilder();
        int[] nums1 = parseString(a), nums2 = parseString(b);

        sb.append(nums1[0] * nums2[0] - nums1[1] * nums2[1]).append("+");
        sb.append(nums1[0] * nums2[1] + nums2[0] * nums1[1]).append("i");
        return sb.toString();
    }

    private int[] parseString(String s) {
        String[] tokens = s.split("\\+"); // need to escape + in regex
        return new int[] { Integer.parseInt(tokens[0]),
                Integer.parseInt(tokens[1].substring(0, tokens[1].length() - 1)) };
    }

    public static void main(String[] args) {
        ComplexNumberMultiplication cnm = new ComplexNumberMultiplication();
        String a = "1+-1i", b = "1+-1i";
        System.out.println(cnm.complexNumberMultiply(a, b));
    }
}
