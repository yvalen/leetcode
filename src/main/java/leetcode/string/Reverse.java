package leetcode.string;

public class Reverse {
    private void reverse(char[] chars, int start, int end) {
        while (start < end) {
            char c = chars[start];
            chars[start] = chars[end];
            chars[end] = c;
            start++;
            end--;
        }
    }

    /**
     * LEETCODE 344 Write a function that takes a string as input and returns
     * the string reversed. Example: Given s = "hello", return "olleh".
     * 
     * Difficulty: easy Similar Questions: 345(Reverse Vowels of a String),
     * 541(Reverse String II)
     */
    public String reverseString(String s) {
        if (s == null || s.length() <= 1)
            return s;

        char[] chars = s.toCharArray();
        for (int i = 0, j = s.length() - 1; i < j; i++, j--) {
            char c = chars[i];
            chars[i] = chars[j];
            chars[j] = c;
        }
        return new String(chars);
    }

    /**
     * LEETCODE 541 Given a string and an integer k, you need to reverse the
     * first k characters for every 2k characters counting from the start of the
     * string. If there are less than k characters left, reverse all of them. If
     * there are less than 2k but greater than or equal to k characters, then
     * reverse the first k characters and leave the other as original. Example:
     * Input: s = "abcdefg", k = 2 Output: "bacdfeg" Restrictions: - The string
     * consists of lower English letters only. - Length of the given string and
     * k will in the range [1, 10000]
     * 
     * Company: Google Difficulty: easy Similar Questions: 344(Reverse String)
     */
    public String reverseStr(String s, int k) {
        if (s == null || s.length() <= 1)
            return s;

        char[] chars = s.toCharArray();
        int start = 0, end = Math.min(k - 1, s.length() - 1);
        while (start < end && start < s.length()) {
            if ((start / k) % 2 == 0)
                reverse(chars, start, end);
            start += k;
            end = end < s.length() - k ? end + k : s.length() - 1;
        }

        return new String(chars);
    }

    /**
     * LEETCODE 557 Given a string, you need to reverse the order of characters
     * in each word within a sentence while still preserving whitespace and
     * initial word order. Example 1: Input: "Let's take LeetCode contest"
     * Output: "s'teL ekat edoCteeL tsetnoc" Note: In the string, each word is
     * separated by single space and there will not be any extra space in the
     * string.
     * 
     * Company: Zappos Difficulty: easy Similar Questions: 541(Reverse String
     * II)
     */
    public String reverseWordsIII(String s) {
        if (s == null || s.length() <= 1)
            return s;

        char[] chars = s.toCharArray();
        int i = 0, j = 0;
        while (j < s.length()) {
            while (j < s.length() && chars[j] != ' ') { // need to do the length
                                                        // check first,
                                                        // otherwise will get
                                                        // index out of bound
                                                        // error
                j++;
            }
            reverse(chars, i, j - 1); // end index should be j-1 since j points
                                      // to white space
            i = j + 1;
            j++;
        }
        return new String(chars);
    }

    /**
     * LEETCODE 151 Given an input string, reverse the string word by word. For
     * example, Given s = "the sky is blue", return "blue is sky the".
     * Clarification: - A word is defined as a sequence of non-space characters.
     * - The input string may contain leading or trailing spaces. But the
     * reversed string should not contain leading or trailing spaces. - There
     * could be multiple spaces between words in the input string. The reversed
     * string should contain a single space bwteen words.
     * 
     * Company: Microsoft, Bloomberg, Apple, Snapchat, Yekp Difficulty: medium
     * Similar Questions: 186(Reverse Words in a String II)
     */
    public String reverseWords_withCharArray(String s) {
        if (s == null || s.length() == 0)
            return s;

        int n = s.length();
        char[] chars = s.toCharArray();

        // reverse the whole string
        reverse(chars, 0, n - 1);

        // reverse each word
        int i = 0, j = 0;
        while (j < n) {
            // i and j both starts from 0, i is the start pointer, j is the end
            // pointer
            // (1) while(i<n && a[i]==' ') i++ is to skip the leading spaces
            // before every word.
            // (2) while (j<i) points j to the same position as i, because i has
            // already skipped the leading spaces in prior while
            // (3) while(j<n && a[j]!=' ') find the whole word starts from i,
            // ends at j until a[j] is ' ' or j<n
            // (4) while(i<j) i is the start index of last word, j-1 is the end
            // index of the last word, points i to the same position as j,
            // then start finding the next word
            while (i < j || (i < n && chars[i] == ' '))
                i++;
            while (j < i || (j < n && chars[j] != ' '))
                j++;
            reverse(chars, i, j - 1);
        }

        // remove redundant spaces
        i = 0;
        j = 0;
        while (j < n) {
            while (j < n && chars[j] == ' ')
                j++; // skip space
            while (j < n && chars[j] != ' ')
                chars[i++] = chars[j++]; // keep no space
            while (j < n && chars[j] == ' ')
                j++; // skip space
            if (j < n)
                chars[i++] = ' '; // add a single space between words
        }

        return new String(chars, 0, i);
    }

    public String reverseWords_withStringBuilder(String s) {
        if (s == null)
            return s;
        s = s.trim();
        if (s.length() <= 1)
            return s;

        String[] strs = s.split("\\s+");
        StringBuilder sb = new StringBuilder();
        for (int i = strs.length - 1; i >= 0; i--) {
            sb.append(strs[i]).append(" ");
        }
        sb.deleteCharAt(sb.length() - 1); // should use sb.length instead of
                                          // s.length since there could spaces
                                          // removed from the middle
        return sb.toString();
    }

    /**
     * LEETCODE 186 Given an input string, reverse the string word by word. A
     * word is defined as a sequence of non-space characters. The input string
     * does not contain leading or trailing spaces and the words are always
     * separated by a single space. For example, given s = "the sky is blue",
     * return "blue is sky the". Could you do it in-place without allocating
     * extra space?
     * 
     * Company: Microsoft, Amazon, Uber Difficulty: medium Similar Questions:
     * 151(Reverse Words in a String), 189(RotateArray)
     */
    public void reverseWordsII(char[] str) {
        reverse(str, 0, str.length - 1);
        int i = 0, j = 0;
        while (j < str.length) {
            while (j < str.length && str[j] != ' ')
                j++;
            reverse(str, i, j - 1);
            i = j + 1;
            j++;
        }
    }

    /**
     * LEETCODE 345 Write a function that takes a string as input and reverse
     * only the vowels of a string. Example 1: Given s = "hello", return
     * "holle". Example 2: Given s = "leetcode", return "leotcede". Note: The
     * vowels does not include the letter "y".
     * 
     * Company: Google Difficulty: easy Similar Questions: 344(Reverse String)
     */
    public String reverseVowels(String s) {
        if (s == null)
            return null;

        char[] chars = s.toCharArray();
        for (int i = 0, j = s.length() - 1; i < j;) {
            while (i < j && !isVowel(chars[i])) { // need to check i < j instead
                                                  // if i < s.length()
                i++;
            }
            while (i < j && !isVowel(chars[j])) { // need to check i < j instead
                                                  // if j >= 0
                j--;
            }
            if (i == s.length() || j < 0)
                break; // break if index is out of bound
            char c = chars[i];
            chars[i] = chars[j];
            chars[j] = c;
            i++;
            j--;
        }

        /*
         * int start = 0, end = chars.length - 1; while (start < end) { if
         * (isVowel(chars[start]) && isVowel(chars[end])) { char c =
         * chars[start]; chars[start] = chars[end]; chars[end] = c; start++;
         * end--; } else if (isVowel(chars[start])) { end--; continue; } else if
         * (isVowel(chars[end])) { start++; continue; } else { start++; end--; }
         * }
         */

        return new String(chars);
    }

    private boolean isVowel(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u' || c == 'A' || c == 'E' || c == 'I' || c == 'O'
                || c == 'U';
    }

    public static void main(String[] ars) {
        Reverse r = new Reverse();

        String words = "   a   b";
        String reversedWords = r.reverseWords_withCharArray(words);
        System.out.println(reversedWords);

        /*
         * String words = "hi! world"; char[] chars = words.toCharArray();
         * r.reverseWordsInPlace(chars); System.out.println(chars);
         */

        /*
         * //String words = "leetcode"; String words = "hello";
         * System.out.println(r.reverseVowels(words));
         */

        /*
         * String s = "abcdefgh"; int k = 3; System.out.println(r.reverseStr(s,
         * k));
         */
        /*
         * String s = "Let's take LeetCode contest";
         * System.out.println(r.reverseWordsIII(s));
         */
    }
}
