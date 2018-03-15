package leetcode.string;

/*
 * LEETCODE 418
 * Given a rows x cols screen and a sentence represented by a list of non-empty 
 * words, find how many times the given sentence can be fitted on the screen.
 * Note:
 * - A word cannot be split into two lines.
 * - The order of words in the sentence must remain unchanged.
 * - Two consecutive words in a line must be separated by a single space.
 * - Total words in the sentence won't exceed 100.
 * - Length of each word is greater than 0 and won't exceed 10.
 * - 1 ≤ rows, cols ≤ 20,000.
 * Example 1:
 * Input: rows = 2, cols = 8, sentence = ["hello", "world"]
 * Output: 1
 * Explanation:
 * hello---
 * world---
 * The character '-' signifies an empty space on the screen.
 * Example 2:
 * Input: rows = 3, cols = 6, sentence = ["a", "bcd", "e"]
 * Output: 2
 * Explanation:
 * a-bcd- 
 * e-a---
 * bcd-e-
 * The character '-' signifies an empty space on the screen.
 * Example 3:
 * Input: rows = 4, cols = 5, sentence = ["I", "had", "apple", "pie"]
 * Output: 1
 * Explanation:
 * I-had
 * apple
 * pie-I
 * had--
 * The character '-' signifies an empty space on the screen.
 * 
 * Company: Google
 * Difficulty: medium
 */
public class SentenceScreenFitting {
    // Time complexity: O(rows + n)
    // Space complexity: O(n)
    public static int wordsTyping(String[] sentence, int rows, int cols) {
        // format sentence into one string 
        String s = String.join(" ", sentence) + " ";
        int start = 0; // counter for how many valid characters from s have been put to our screen.
        int len = s.length();
        for (int i = 0; i < rows; i++) {
            start += cols;
            if (s.charAt(start%len) == ' ') {
                // current row can be fitted, no need to add extra space to the end
                start++;
            }
            else {
                // the next word can’t fit to current row.
                // we need to remove extra characters from next word.
                while (start > 0 && s.charAt((start-1)%len) != ' ') {
                    start--;
                }
            }
        }
        return start / s.length();
    }

    public static void main(String[] args) {
        //String[] sentence = {"hello", "world"};
        String[] sentence = {"helloworld wolrd"};
        int rows = 2, cols = 8;
        System.out.println(wordsTyping(sentence, rows, cols));
    }

}