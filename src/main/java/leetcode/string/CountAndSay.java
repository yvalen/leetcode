package leetcode.string;

/*
 * LEETCODE 38
 * The count-and-say sequence is the sequence of integers with the first five terms as following:
 * 1.     1
 * 2.     11
 * 3.     21
 * 4.     1211
 * 5.     111221
 * 1 is read off as "one 1" or 11.
 * 11 is read off as "two 1s" or 21.
 * 21 is read off as "one 2, then one 1" or 1211.
 * Given an integer n, generate the nth term of the count-and-say sequence.
 * Note: Each term of the sequence of integers will be represented as a string.
 * Example 1: Input: 1 Output: "1"
 * Example 2: Input: 4 Output: "1211"
 * 
 * Company: Facebook
 * Difficulty: easy
 * Similar Questions: 271(EncodeDecodeString)
 * 
 * https://en.wikipedia.org/wiki/Look-and-say_sequence
 * "Count and Say problem" Write a code to do following:
 * n String to print
 * 0 1
 * 1 1 1
 * 2 2 1
 * 3 1 2 1 1
 * ...
 * 
 * Base case: n = 0 print "1"
 * for n = 1, look at previous string and write number of times a digit is seen and the digit itself. 
 * In this case, digit 1 is seen 1 time in a row... so print "1 1"
 * for n = 2, digit 1 is seen two times in a row, so print "2 1"
 * for n = 3, digit 2 is seen 1 time and then digit 1 is seen 1 so print "1 2 1 1"
 * for n = 4 you will print "1 1 1 2 2 1"
 * 
 * Consider the numbers as integers for simplicity. 
 * e.g. if previous string is "10 1" then the next will be "1 10 1 1" and the next one will be "1 1 1 10 2 1"
 */
public class CountAndSay {

    public String countAndSay(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Invalid n " + n);
        }
        if (n == 0) {
            return "1";
        }

        String result = "1";
        /*
         * for (int i = 1; i < n; i++) { int count = 1; StringBuilder sb = new
         * StringBuilder(); for (int j = 1; j < result.length(); j++) { if
         * (result.charAt(j) == result.charAt(j-1)) { count++; } else {
         * sb.append(count).append(result.charAt(j-1)); count = 1; } }
         * sb.append(count).append(result.charAt(result.length() - 1)); result =
         * sb.toString(); }
         */
        for (int i = 2; i <= n; i++) {
            StringBuilder sb = new StringBuilder();
            // end and count start from 0
            for (int j = 0, count = 0; j < result.length(); j++) { 
                count++; // always increment count since it starts from 0
                // check for last char here that no special handling outside the loop
                if (j == result.length() - 1 || result.charAt(j) != result.charAt(j + 1)) { 
                    sb.append(count).append(result.charAt(j));
                    count = 0; // reset count to 0
                }
            }
            result = sb.toString();
            System.out.println(result);
        }

        return result;
    }

    public static void main(String[] args) {
        CountAndSay cs = new CountAndSay();
        int n = 4;
        System.out.println(cs.countAndSay(n));
    }
}
