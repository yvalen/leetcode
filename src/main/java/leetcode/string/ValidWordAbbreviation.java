package leetcode.string;

import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;

/*
 * LEETCODE 408
 * Given a non-empty string s and an abbreviation abbr, return whether the string matches with the given abbreviation.
 * A string such as "word" contains only the following valid abbreviations:
 * ["word", "1ord", "w1rd", "wo1d", "wor1", "2rd", "w2d", "wo2", "1o1d", "1or1", "w1r1", "1o2", "2r1", "3d", "w3", "4"]
 * Notice that only the above abbreviations are valid abbreviations of the string "word". Any other string is not a 
 * valid abbreviation of "word".
 * Note: Assume s contains only lower case letters and abbr contains only lower case letters and digits.
 * Example 1:
 * Given s = "internationalization", abbr = "i12iz4n"
 * Return true.
 * Example 2:
 * Given s = "apple", abbr = "a2e"
 * Return false.
 * 
 * Company: Google
 * Difficulty: easy
 * Similar Questions: 
 */
public class ValidWordAbbreviation {
    public boolean validWordAbbreviation(String word, String abbr) {
        int i = 0, j = 0;
        while (i < word.length() && j < abbr.length()) {
            if (word.charAt(i) == abbr.charAt(j)) {
                i++;
                j++;
                continue;
            }
            
            // do this check instead of using Character.isDigit
            // because first digit cannot be zero
            if (abbr.charAt(j) <= '0' || abbr.charAt(j) > '9') {
                return false;
            }
            
            int k = j;
            while (j < abbr.length() && Character.isDigit(abbr.charAt(j))) j++;
            int repeat = Integer.parseInt(abbr.substring(k, j));
            i += repeat;
            
        }
        return i == word.length() && j == abbr.length();
    }
    
    public static void main(String[] args) {
        ValidWordAbbreviation vwa = new ValidWordAbbreviation ();
        //String word = "internationalization", abbr = "i12iz4n";
        //String word = "apple", abbr = "a2e";
        //String word = "internationalization", abbr = "i5a11o1";
        //String word = "a", abbr = "1";
        //String word = "a", abbr = "2";
        String word = "a", abbr = "01";
        System.out.println(vwa.validWordAbbreviation(word, abbr));
    }
}
