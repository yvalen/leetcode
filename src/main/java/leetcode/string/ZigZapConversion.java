package leetcode.string;

import java.util.ArrayList;
import java.util.List;

/*
 * LEETCODE 6
 * The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: 
 * (you may want to display this pattern in a fixed font for better legibility)
 * P   A   H   N
 * A P L S I I G
 * Y   I   R
 * And then read line by line: "PAHNAPLSIIGYIR"
 * Write the code that will take a string and make this conversion given a number of rows:
 * string convert(string text, int nRows);
 * convert("PAYPALISHIRING", 3) should return "PAHNAPLSIIGYIR". 
 * 
 * Difficulty: medium
 * https://fisherlei.blogspot.com/2013/01/leetcode-zigzag-conversion.html
 */
public class ZigZapConversion {
    // Time complexity O(n)
    public String convert_withStringBuilderArray(String s, int numRows) {
        if (s == null || s.isEmpty() || numRows <= 0)
            return s;

        StringBuilder[] sbs = new StringBuilder[numRows];
        for (int i = 0; i < numRows; i++) { // need to initialize StringBuilder
                                            // array
            sbs[i] = new StringBuilder();
        }

        int i = 0, len = s.length();
        char[] chars = s.toCharArray();
        while (i < len) {
            // vertically down
            for (int j = 0; j < numRows && i < len; j++) {
                sbs[j].append(chars[i++]);
            }

            // zig zag up, when traversing up, the first row and the last row
            // has no element,
            for (int j = numRows - 2; j > 0 && i < len; j--) {
                sbs[j].append(chars[i++]);
            }
        }

        for (int j = 1; j < numRows; j++) {
            sbs[0].append(sbs[j]);
        }

        return sbs[0].toString();
    }

    public String convert_withOneStringBuilder(String s, int numRows) {
        if (s == null || s.isEmpty() || numRows <= 1)
            return s;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numRows; i++) {
            for (int j = 0, k = i; k < s.length(); j++) {
                System.out.println("i=" + i + " j=" + j + " k=" + k);
                sb.append(s.charAt(k));
                k += ((i == 0 || (j % 2 == 0)) && (i != numRows - 1)) ? 2 * (numRows - i - 1) : 2 * i;
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        ZigZapConversion zz = new ZigZapConversion();
        // String s = "PAYPALISHIRING";
        // int numRows = 3;
        String s = "ABCDEFGHIJKLMN";
        int numRows = 4;
        System.out.println(zz.convert_withOneStringBuilder(s, numRows));
    }
}
