package leetcode.string;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
 * LEETCODE 68
 * Given an array of words and a length L, format the text such that each line has exactly L characters and 
 * is fully (left and right) justified. You should pack your words in a greedy approach; that is, pack as many 
 * words as you can in each line. Pad extra spaces ' ' when necessary so that each line has exactly L characters. 
 * Extra spaces between words should be distributed as evenly as possible. If the number of spaces on a line do 
 * not divide evenly between words, the empty slots on the left will be assigned more spaces than the slots on the right. 
 * For the last line of text, it should be left justified and no extra space is inserted between words.
 * For example, words: ["This", "is", "an", "example", "of", "text", "justification."] L: 16.
 * Return the formatted lines as:
 * [
 * 	"This    is    an",
 * 	"example  of text",
 * 	"justification.  "
 * ]
 * Note: Each word is guaranteed not to exceed L in length. 
 * Corner Cases: A line other than the last line might contain only one word. What should you do in this case? 
 * In this case, that line should be left-justified.
 * 
 * Company: Facebook, LinkedIn, Airbnb
 * Difficulty: hard
 */
public class TextJustification {
    public List<String> fullJustify(String[] words, int maxWidth) {
        if (words == null || words.length == 0) return Collections.emptyList();

        List<String> result = new ArrayList<>();
        if (maxWidth == 0) { 
            // handle words={""} maxWidth=0
            result.add("");
            return result;
        }

        int end = 0;
        for (int start = 0; start < words.length; start = end) {
            int len = -1; // need to skip the space for the last word
            while (end < words.length && len + words[end].length() + 1 <= maxWidth) {
                len += words[end++].length() + 1;
            }
            
            // there is at least one space between words,
            // accounted for in the previous loop
            int space = 1;             
            int extra = 0; // extra space to fill
            // n words has n-1 spaces between them
            // need to minus one here because index end is exclusive
            int numOfGaps = end - start - 1;
            
            // don't need to calculate this if only one word can be accommodated
            // in the line or the last word is reached
            if (end != start + 1 && end != words.length) {
                // plus one here for the single space accounted for already
                space = (maxWidth - len) / numOfGaps + 1; 
                extra = (maxWidth - len) % numOfGaps;
            }
            
            // initialize with the first word
            StringBuilder sb = new StringBuilder(words[start]); 
            for (int i = start + 1; i < end; i++) { // start from second word
                for (int j = 0; j < space; j++) sb.append(" ");
                if (extra-- > 0) sb.append(" "); // use if here to evenly distributed the extra spaces
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
        //String[] words = { "" };
        //int maxWidth = 0;
        // String[] words = {"This", "is", "an", "example", "of", "text",
        // "justification."};
        // int maxWidth = 16;
        
        String[] words = {"Don't","go","around","saying","the","world","owes","you","a","living;","the","world","owes","you","nothing;","it","was","here","first."};
        int maxWidth = 30;
        System.out.println(t.fullJustify(words, maxWidth));

    }
}
