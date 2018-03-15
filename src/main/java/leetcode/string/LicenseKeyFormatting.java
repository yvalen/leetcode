package leetcode.string;

/*
 * LEETCODE 482
 * You are given a license key represented as a string S which consists only 
 * alphanumeric character and dashes. The string is separated into N+1 groups 
 * by N dashes. Given a number K, we would want to reformat the strings such 
 * that each group contains exactly K characters, except for the first group 
 * which could be shorter than K, but still must contain at least one character. 
 * Furthermore, there must be a dash inserted between two groups and all lower 
 * case letters should be converted to upper case. Given a non-empty string S 
 * and a number K, format the string according to the rules described above.
 * Example 1:
 * Input: S = "5F3Z-2e-9-w", K = 4
 * Output: "5F3Z-2E9W"
 * Explanation: The string S has been split into two parts, each part has 4 
 * characters. Note that the two extra dashes are not needed and can be removed.
 * Example 2:
 * Input: S = "2-5g-3-J", K = 2
 * Output: "2-5G-3J"
 * Explanation: The string S has been split into three parts, each part has 2 
 * characters except the first part as it could be shorter as mentioned above.
 * Note:
 * - The length of string S will not exceed 12,000, and K is a positive integer.
 * - String S consists only of alphanumeric characters (a-z and/or A-Z and/or 0-9) 
 * and dashes(-).
 * - String S is non-empty.
 * 
 * Company: Google
 * Difficulty: medium
 */
public class LicenseKeyFormatting {
    public static String licenseKeyFormatting(String S, int K) {
        StringBuilder sb = new StringBuilder();
        for (int i = S.length()-1, len = 0; i >= 0; i--) {
            if (S.charAt(i) == '-') continue;
            if (len > 0 && len % K == 0) sb.append("-");
            sb.append(Character.toUpperCase(S.charAt(i)));
            len++;
        }
        return sb.reverse().toString();
    }
    
    public static void main(String[] args) {
        //String S = "5F3Z-2e-9-w";
        //int K = 4;
        String S = "2-5g-3-J";
        int K = 2;
        //String S = "---";
        //int K = 3;
        System.out.println(licenseKeyFormatting(S, K));
    }
}
