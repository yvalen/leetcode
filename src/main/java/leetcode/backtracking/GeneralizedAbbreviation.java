package leetcode.backtracking;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
 * LEETCODE 320 
 * Write a function to generate the generalized abbreviations of a word.
 * Example: Given word = "word", return the following list (order does not matter):
 * ["word", "1ord", "w1rd", "wo1d", "wor1", "2rd", "w2d", "wo2", "1o1d", "1or1", 
 * "w1r1", "1o2", "2r1", "3d", "w3", "4"]
 * 
 * Company: Google
 * Difficulty: medium
 * Similar Questions: 78(Subsets), 288(UniqueWordAbbreviation), 411
 */
public class GeneralizedAbbreviation {
    /*
     * For each char c[i], either abbreviate it or not.
     * 1. Abbreviate: count accumulate num of abbreviating chars, but don't append it yet.
     * 2. Not Abbreviate: append accumulated num as well as current char c[i].
     * 3. In the end append remaining num.
     * Time complexity: O(2^n)
     */
    public List<String> generateAbbreviations(String word) {
        if (word == null) return Collections.emptyList();
        List<String> result = new ArrayList<>();
        backtrack(word, 0, 0, new StringBuilder(), result);
        return result;
    }
    
    private void backtrack(String word, int start, Integer count, StringBuilder sb, List<String> result) {
        int len = sb.length();
        if (start == word.length()) {
            // reach the end of the word
            if (count > 0) sb.append(count);
            result.add(sb.toString());
            sb.setLength(len);
            return;
        }
        else {
            // abbreviate
            backtrack(word, start+1, count+1, sb, result);

            // not abbreviate
            if (count > 0) sb.append(count);
            sb.append(word.charAt(start));
            backtrack(word, start+1, 0, sb, result); // reset count to 0
        }
        sb.setLength(len);
    }
     
    public static void main(String[] args) {
        GeneralizedAbbreviation ga = new GeneralizedAbbreviation();
        String word = "word";
        System.out.println(ga.generateAbbreviations(word));
    }
}
