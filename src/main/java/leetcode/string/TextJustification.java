package leetcode.string;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
 * Given an array of words and a length L, format the text such that each line has exactly L characters and is fully (left and right) justified.
 * ou should pack your words in a greedy approach; that is, pack as many words as you can in each line. Pad extra spaces ' ' when necessary so that 
 * each line has exactly L characters. Extra spaces between words should be distributed as evenly as possible. If the number of spaces on a line do 
 * not divide evenly between words, the empty slots on the left will be assigned more spaces than the slots on the right. For the last line of text, 
 * it should be left justified and no extra space is inserted between words.
 * For example, words: ["This", "is", "an", "example", "of", "text", "justification."] L: 16.
 * Return the formatted lines as:
 * [
 * 	"This    is    an",
 * 	"example  of text",
 * 	"justification.  "
 * ]
 * Note: Each word is guaranteed not to exceed L in length. 
 * Corner Cases: A line other than the last line might contain only one word. What should you do in this case? In this case, that line should be left-justified.
 */
public class TextJustification {
    public List<String> fullJustify(String[] words, int maxWidth) {
        if (words == null || words.length == 0)
            return Collections.emptyList();

        List<String> result = new ArrayList<>();
        if (maxWidth == 0) { // handle words={""} maxWidth=0
            result.add("");
            return result;
        }

        int end = 0;
        for (int start = 0; start < words.length; start = end) {
            int len = -1; // need to skip the space for the last word
            for (; end < words.length && len + words[end].length() + 1 <= maxWidth; end++) {
                len += words[end].length() + 1;
            }

            int space = 1; // there is at least one space between words,
                           // accounted for in the previous loop
            int extra = 0; // extra space to fill
            int numOfGaps = end - start - 1; // n words has n-1 spaces between
                                             // them

            // don't need to calculate this if only one word can be accommodated
            // in the line
            // or the last word is reached
            if (end != start + 1 && end != words.length) {
                space = (maxWidth - len) / numOfGaps + 1; // plus 1 accounts for
                                                          // the space added in
                                                          // the previous loop
                extra = (maxWidth - len) % numOfGaps;
            }

            StringBuilder sb = new StringBuilder(words[start]); // initialize
                                                                // with the
                                                                // first word
            for (int i = start + 1; i < end; i++) {
                for (int j = 0; j < space; j++)
                    sb.append(" ");
                if (extra-- > 0)
                    sb.append(" ");
                sb.append(words[i]);
            }

            // handle the two cases we skip, one word in the line or last word
            // it should be left aligned
            int remaining = maxWidth - sb.length();
            while (remaining-- > 0) {
                sb.append(" ");
            }
            result.add(sb.toString());
        }

        return result;
    }

    public static void main(String[] args) {
        TextJustification t = new TextJustification();
        // String[] words = {};
        String[] words = { "" };
        int maxWidth = 0;
        // String[] words = {"This", "is", "an", "example", "of", "text",
        // "justification."};
        // int maxWidth = 16;
        System.out.println(t.fullJustify(words, maxWidth));

    }
}
