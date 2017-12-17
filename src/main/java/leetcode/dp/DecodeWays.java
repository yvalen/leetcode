package leetcode.dp;

public class DecodeWays {
    /**
     * A message containing letters from A-Z is being encoded to numbers using
     * the following mapping: 'A' -> 1 'B' -> 2 ... 'Z' -> 26 Given an encoded
     * message containing digits, determine the total number of ways to decode
     * it. For example, given encoded message "12", it could be decoded as "AB"
     * (1 2) or "L" (12). The number of ways decoding "12" is 2.
     * 
     * Company: Microsoft, Uber, Facebook Difficulty: medium
     */
    public int numDecodings_topDown(String s) {
        if (s == null || s.length() == 0)
            return 0;

        // represents number of decodings for ith element
        int[] dp = new int[s.length() + 1];
        dp[0] = 1; // "" -> 1
        dp[1] = s.charAt(0) == '0' ? 0 : 1;
        for (int i = 2; i <= s.length(); i++) {
            int second = Integer.parseInt(s.substring(i - 2, i));
            if (second >= 10 && second <= 26) {
                dp[i] = dp[i - 2];
            }

            if (s.charAt(i - 1) != '0') {
                dp[i] += dp[i - 1];
            }
        }

        return dp[s.length()];
    }

    public int numDecodings_bottomUp(String s) {
        if (s == null || s.length() == 0)
            return 0;

        int n = s.length();
        int[] dp = new int[n + 1]; // dp[i] is the number of ways to decode
                                   // substring (i, end);
        dp[n] = 1; // only one way when string is empty
        dp[n - 1] = s.charAt(n - 1) == '0' ? 0 : 1; // if last char is 0, it
                                                    // cannot be used; otherwise
                                                    // there is one way
        for (int i = n - 2; i >= 0; i--) {
            if (s.charAt(i) == '0')
                continue; // 0 cannot be used alone, it needs to be used
                          // together with the letter before it
            dp[i] = Integer.parseInt(s.substring(i, i + 2)) <= 26 ? dp[i + 2] + dp[i + 1] : dp[i + 1];
        }

        return dp[0];
    }

    /**
     * A message containing letters from A-Z is being encoded to numbers using
     * the following mapping way: 'A' -> 1 'B' -> 2 ... 'Z' -> 26 Beyond that,
     * now the encoded string can also contain the character '*', which can be
     * treated as one of the numbers from 1 to 9. Given the encoded message
     * containing digits and the character '*', return the total number of ways
     * to decode it. Also, since the answer may be very large, you should return
     * the output mod 10^9 + 7. Example 1: Input: "*" Output: 9 Explanation: The
     * encoded message can be decoded to the string: "A", "B", "C", "D", "E",
     * "F", "G", "H", "I". Example 2: Input: "1*" Output: 9 + 9 = 18 Note: - The
     * length of the input string will fit in range [1, 10^5]. - The input
     * string will only contain the character '*' and digits '0' - '9'.
     * 
     * Company: Facebook Difficulty: hard
     */
    public int numDecodingsII(String s) {
        if (s == null || s.length() == 0)
            return 0;
        int n = s.length();

        // f(i) = ( f(i-1) * ways(i) ) + ( f(i-2) *ways(i-1, i) )
        /*
         * 
         * long w1 = ways(s.charAt(0)); if (n == 1) return (int) w1;
         * 
         * long w2 = ways(s.charAt(1)) * w1 + ways(s.charAt(0), s.charAt(1));
         * for (int i = 2; i < n; i++) { long temp = w2; w2 = (ways(s.charAt(i))
         * * w2 + ways(s.charAt(i-1), s.charAt(i)) * w1) % (1000000000 + 7); w1=
         * temp; } return (int)w2;
         */

        long[] dp = new long[n + 1];
        dp[n] = 1;
        dp[n - 1] = ways(s.charAt(n - 1));
        for (int i = n - 2; i >= 0; i--) {
            char c1 = s.charAt(i), c2 = s.charAt(i + 1);
            dp[i] = (ways(c1) * dp[i + 1] + ways(c1, c2) * dp[i + 2]) % (1000000000 + 7);
            ;
        }

        return (int) dp[0];

    }

    // return the number of ways to decode a single character
    private int ways(char c) {
        if (c == '0')
            return 0;
        else if (c == '*')
            return 9;
        return 1;
    }

    // return the number of ways to decode a string formed of c1c2
    private int ways(char c1, char c2) {
        if (c1 != '*' && c2 != '*') {
            int val = Integer.parseInt(String.valueOf(c1) + String.valueOf(c2));
            return val >= 10 && val <= 26 ? 1 : 0;
        } else if (c1 == '*' && c2 == '*')
            return 15; // 11-19, 21-29
        else if (c1 == '*') {
            if (c2 >= '0' && c2 <= '6')
                return 2;
            return 1;
        } else {
            if (c1 == '1')
                return 9;
            else if (c1 == '2')
                return 6;
            return 0;
        }
    }

    public static void main(String[] args) {
        DecodeWays dw = new DecodeWays();
        // String s = "1001";
        String s = "*******"; // expect 11859129
        System.out.println(dw.numDecodingsII(s));
    }
}
