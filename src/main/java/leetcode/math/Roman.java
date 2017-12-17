package leetcode.math;

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
 * Roman numeral system being basically decimal, each "place" is added separately, in descending sequence from left to right.
 */
public class Roman {

    /*
     * LEETCODE 13 Given a roman numeral, convert it to an integer. Input is
     * guaranteed to be within the range from 1 to 3999.
     * 
     * Company: Facebook, Microsoft, Bloomberg, Uber, Facebook, Yahoo
     * Difficulty: easy Similar Questions: 12(Integer to Roman)
     */
    private static final Map<Character, Integer> symbolValueMap = new HashMap<>();
    static {
        symbolValueMap.put('I', 1);
        symbolValueMap.put('V', 5);
        symbolValueMap.put('X', 10);
        symbolValueMap.put('L', 50);
        symbolValueMap.put('C', 100);
        symbolValueMap.put('D', 500);
        symbolValueMap.put('M', 1000);
    }

    public int romanToInt(String s) {
        if (s == null || s.isEmpty())
            return 0;

        // the last letter is always added
        int result = symbolValueMap.get(s.charAt(s.length() - 1));

        // walk the string from back to front, if one letter is less than its
        // latter one, this letter is subtracted;
        // otherwise, it is added
        for (int i = s.length() - 2; i >= 0; i--) {
            int val = symbolValueMap.get(s.charAt(i));
            if (val < symbolValueMap.get(s.charAt(i + 1))) {
                result -= val;
            } else {
                result += val;
            }
        }
        return result;

        /*
         * int result = 0; char prev = '#'; // the last letter is always added.
         * walk the string from back to front, // if one letter is less than its
         * latter one, this letter is subtracted. for (int i = s.length() - 1; i
         * >= 0; i--) { char c = s.charAt(i); int val = map.get(c); if (val <
         * result && c != prev) { result -= val; // only do subtraction when
         * current character is different from the following character, "DCXXI"
         * } else { result += val; } prev = c; } return result;
         */
    }

    /*
     * LEETCODE 12 Given an integer, convert it to a roman numeral. Input is
     * guaranteed to be within the range from 1 to 3999.
     * 
     * Company: Twitter Difficulty: medium Similar Questions: 13(Roman to
     * Integer), 273(IntegerToEnglishWords)
     */
    private static final String[] M = { "", "M", "MM", "MMM" }; // thousand
    private static final String[] C = { "", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM" }; // hundred
    private static final String[] X = { "", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC" }; // 10-100
    private static final String[] I = { "", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX" }; // <
                                                                                                      // 10

    public String intToRoman(int num) {
        if (num <= 0)
            return "";
        return M[num / 1000] + C[(num % 1000) / 100] + X[(num % 100) / 10] + I[num % 10];
        // return M[num/1000] + C[(num/100)%10] + X[(num/10)%10] + I[num%10];
    }

    public static void main(String[] args) {
        Roman r = new Roman();

        // String s = "DCXXI";
        String s = "XXI";
        System.out.println(r.romanToInt(s));
    }

}
