package leetcode.math;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;


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
    // https://stackoverflow.com/questions/267399/how-do-you-match-only-valid-roman-numerals-with-a-regular-expression
    // ^ - start
    // $ - end
    // () - group
    // ? - 0 or one occurrence
    private static final Pattern PATTERN = Pattern.compile("^M{0,3}(CM|CD|D?C{0,3})(XC|XL|L?X{0,3})(IX|IV|V?I{0,3})$");

    /*
     * LEETCODE 13 
     * Given a roman numeral, convert it to an integer. 
     * Input is guaranteed to be within the range from 1 to 3999.
     * 
     * Company: Facebook, Microsoft, Bloomberg, Uber, Facebook, Yahoo
     * Difficulty: easy 
     * Similar Questions: 12(Integer to Roman)
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

    private static final int[] VALUES = new int[26];
    static {
        VALUES['I'-'A'] = 1;
        VALUES['V'-'A'] = 5;
        VALUES['X'-'A'] = 10;
        VALUES['L'-'A'] = 50;
        VALUES['C'-'A'] = 100;
        VALUES['D'-'A'] = 500;
        VALUES['M'-'A'] = 1000;
    }
    
    public int romanToInt(String s) {
       if (!PATTERN.matcher(s).matches()) {
           throw new IllegalArgumentException("invalid input " + s);
       }
       
        // the last letter is always added
        int result = symbolValueMap.get(s.charAt(s.length() - 1));

        // walk the string from back to front, if one letter is less than its
        // latter one, this letter is subtracted; otherwise, it is added
        for (int i = s.length() - 2; i >= 0; i--) {
            int val = symbolValueMap.get(s.charAt(i));
            if (val < symbolValueMap.get(s.charAt(i + 1))) {
                // only do subtraction when r, "DCXXI"
                result -= val;
            } else {
                result += val;
            }
        }
        return result;
    }

    /*
     * LEETCODE 12 
     * Given an integer, convert it to a roman numeral. 
     * Input is guaranteed to be within the range from 1 to 3999.
     * 
     * Company: Twitter 
     * Difficulty: medium 
     * Similar Questions: 13(Roman to Integer), 273(IntegerToEnglishWords)
     */
    private static final String[] M = { "", "M", "MM", "MMM" }; // thousand
    private static final String[] C = { "", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM" }; // hundred
    private static final String[] X = { "", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC" }; // 10-100
    private static final String[] I = { "", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX" }; // < 10
    
    public String intToRoman(int num) {
        if (num <= 0)
            return "";
        return M[num / 1000] + C[(num % 1000) / 100] + X[(num % 100) / 10] + I[num % 10];
        // return M[num/1000] + C[(num/100)%10] + X[(num/10)%10] + I[num%10];
    }

    public static void main(String[] args) {
        Roman r = new Roman();

        // String s = "DCXXI";
        //String s = "XXI";
        //String s = "MCMXCVI";
        String s = "IIIIIV";
        System.out.println(r.romanToInt(s));
    }

}
