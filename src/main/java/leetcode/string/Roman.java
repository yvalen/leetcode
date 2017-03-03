package leetcode.string;

import java.util.HashMap;
import java.util.Map;

/*
 * https://en.wikipedia.org/wiki/Roman_numerals
 * Symbol 	I 	V 	X 	L 	C 	 D 	 M
 * Value 	1 	5 	10 	50 	100	500	1,000
 * Symbols are placed from left to right in order of value, starting with the largest. However there are a few special cases o avoid four characters 
 * being repeated in succession (such as IIII or XXXX), subtractive notation is used
 * 	- I placed before V or X indicates one less, so four is IV (one less than five) and nine is IX (one less than ten)
 * 	- X placed before L or C indicates ten less, so forty is XL (ten less than fifty) and ninety is XC (ten less than a hundred)
 * 	- C placed before D or M indicates a hundred less, so four hundred is CD (a hundred less than five hundred) and nine hundred is CM (a hundred less than a thousand)
 */
public class Roman {
	
	public int romanToInt(String s) {
		if (s == null || s.isEmpty()) return 0;
		
		Map<Character, Integer> map = new HashMap<>();
		map.put('I', 1);
		map.put('V', 5);
		map.put('X', 10);
		map.put('L', 50);
		map.put('C', 100);
		map.put('D', 500);
		map.put('M', 1000);
		
		int result = 0;
		char prev = '#'; 
		for (int i = s.length() - 1; i >= 0; i--) {
			char c = s.charAt(i);
			int val = map.get(c);
			if (val < result && c != prev) {
				result -= val; // only do substraction when current character is different from the following character, "DCXXI"
			}
			else {
				result += val;
			}
			prev = c;
		}
		
		return result;
    }
	
	/*
	 * Given an integer, convert it to a roman numeral.
	 * Input is guaranteed to be within the range from 1 to 3999.
	 */
	public String intToRoman(int num) {
		String[] M = {"", "M", "MM", "MMM"}; // max is 3999
		String[] C = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
		String[] X = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
		String[] I = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};
		
		return M[num/1000] + C[(num/100)%10] + X[(num/10)%10] + I[num%10];
    }
	
	public static void main(String[] args) {
		Roman r = new Roman();
		
		String s = "DCXXI";
		System.out.println(r.romanToInt(s));
	}

}
