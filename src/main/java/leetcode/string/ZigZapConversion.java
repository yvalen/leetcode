package leetcode.string;

import java.util.ArrayList;
import java.util.List;

/*
 * The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: 
 * (you may want to display this pattern in a fixed font for better legibility)
 * P   A   H   N
 * A P L S I I G
 * Y   I   R
 * And then read line by line: "PAHNAPLSIIGYIR"
 * Write the code that will take a string and make this conversion given a number of rows:
 * string convert(string text, int nRows);
 * convert("PAYPALISHIRING", 3) should return "PAHNAPLSIIGYIR". 
 */
public class ZigZapConversion {
	public String convert(String s, int numRows) {
		if (s == null || s.isEmpty() || numRows <= 0) return s;
		
		StringBuilder[] sbs = new StringBuilder[numRows];
		for (int i = 0; i < numRows; i++) {
			sbs[i] = new StringBuilder();
		}
		
		int i = 0, len = s.length();
		char[] chars = s.toCharArray();
		while (i < len) {
			// vertically down 
			for (int j = 0; j < numRows && i < len ; j++) {
				sbs[j].append(chars[i++]);
			}
			
			// zig zag up
			for (int j = numRows - 2; j > 0 && i < len; j--) {
				sbs[j].append(chars[i++]);
			}
		}
		
		for (int j = 1; j < numRows; j++) {
			sbs[0].append(sbs[j]);
		}
		
		return sbs[0].toString();
    }
}
