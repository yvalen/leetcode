package leetcode.math;

import java.util.HashMap;
import java.util.Map;

/*
 * Given two integers representing the numerator and denominator of a fraction, return the fraction in string format.
 * If the fractional part is repeating, enclose the repeating part in parentheses.
 * For example,
 * 	Given numerator = 1, denominator = 2, return "0.5".
 * 	Given numerator = 2, denominator = 1, return "2".
 * 	Given numerator = 2, denominator = 3, return "0.(6)".
 */
public class FractionToRecurringDecimal {
	public String fractionToDecimal(int numerator, int denominator) {
		if (denominator == 0) throw new IllegalArgumentException("denominator cannot be 0");
		
		if (numerator == 0) return "0";
		
		StringBuilder sb = new StringBuilder();
		if (numerator < 0 ^ denominator < 0) sb.append("-"); // result will be negative when only one number is negative
		
		// cast to long to avoid overflow -2147483648 / -1, 
		// use absolute value since sign has been handled already
		long num = Math.abs(Long.valueOf(numerator));
		long den = Math.abs(Long.valueOf(denominator));
		
		// integral part
		sb.append(num / den);
		
		long remainder = num % den;
		// result is an integer
		if (remainder == 0) return sb.toString();
		
		// fractional part
		sb.append(".");
		Map<Long, Integer> remainderPos = new HashMap<>();  // record the position a reminder appears
		while (remainder > 0) {
			if (remainderPos.containsKey(remainder)) {
				// handle repeating remainder
				sb.insert(remainderPos.get(remainder), "(");
				sb.append(")");
				break;
			}
			remainderPos.put(remainder, sb.length());
			remainder *= 10;
			sb.append(remainder / den);
			remainder %= den;
		}
		return sb.toString();
    }

	public static void main(String[] args) {
		FractionToRecurringDecimal f = new FractionToRecurringDecimal();
		int numerator = -50, denominator = 8;
		System.out.println(f.fractionToDecimal(numerator, denominator));
	}
}
