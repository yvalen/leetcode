package leetcode.string;

import leetcode.array.ArrayUtil;

/*
 * LEETCODE 28
 * Implement strStr(). Return the index of the first occurrence of needle 
 * in haystack, or -1 if needle is not part of haystack.
 * Example 1:
 * Input: haystack = "hello", needle = "ll"
 * Output: 2
 * Example 2:
 * Input: haystack = "aaaaa", needle = "bba"
 * Output: -1
 * 
 * Company: Facebook, Apple, Pocket Gem, Microsoft
 * Difficulty: easy
 * Similar Questions: 214(ShortestPalindrome), 459(RepeatedSubstringPattern)
 * 
 * rolling hash: 
 * https://discuss.leetcode.com/topic/8568/my-ac-java-solution-rolling-hash-but-hash-function-is-very-simple/2?show=24823
 */
public class StrStr {
    public int strStr(String haystack, String needle) {
        if (haystack == null || needle == null || needle.length() > haystack.length()) {
            return -1;
        }

        // traverse all the possible starting points of haystack (from 0 to
        // haystack.length() - needle.length())
        // and see if the following characters in haystack match those of
        // needle.
        for (int i = 0;; i++) {
            for (int j = 0;; j++) {
                if (j == needle.length())
                    return i;
                if (i + j == haystack.length())
                    return -1;
                if (haystack.charAt(i + j) != needle.charAt(j))
                    break;
            }
        }
    }

    // TODO
    // https://discuss.leetcode.com/topic/15569/explained-4ms-easy-c-solution/2
    // http://jakeboxer.com/blog/2009/12/13/the-knuth-morris-pratt-algorithm-in-my-own-words/
    // http://blog.csdn.net/v_july_v/article/details/7041827
    public int strStr_KMP(String haystack, String needle) {
        if (haystack == null || needle == null || needle.length() > haystack.length()) {
            return -1;
        }

        if (needle.length() == 0)
            return 0; // needed to handle special case "", ""

        int[] next = calculateNext(needle);
        int i = 0, j = 0;
        while (i < haystack.length() && j < needle.length()) {
            if (j == -1 || haystack.charAt(i) == needle.charAt(j)) {
                i++;
                j++;
            } else {
                j = next[j];
            }
        }

        if (j == needle.length())
            return i - j;

        return -1;
    }

    // Proper prefix: All the characters in a string, with one or more cut off
    // the end. “S”, “Sn”, “Sna”,
    // and “Snap” are all the proper prefixes of “Snape”.
    // Proper suffix: All the characters in a string, with one or more cut off
    // the beginning. “agrid”, “grid”,
    // “rid”, “id”, and “d” are all proper suffixes of “Hagrid”.
    // next stores the length of the longest proper prefix in the (sub)pattern
    // that matches a proper suffix in the same (sub)pattern.
    public int[] calculateNext(String s) {
        int[] next = new int[s.length()];
        next[0] = -1;
        int j = 0, k = -1;
        while (j < s.length() - 1) {
            if (k == -1 || s.charAt(k) == s.charAt(j)) { // k - prefix, j -
                                                         // suffix
                j++;
                k++;
                next[j] = k;
            } else {
                k = next[k];
            }
        }
        return next;
    }

    public static void main(String[] args) {
        StrStr s = new StrStr();
        String haystack = "mississippi", needle = "issip";
        // String haystack = "", needle = "";
        // String haystack = "a", needle = "";
        // String haystack = "mississippi", needle = "a";
        System.out.println(s.strStr_KMP(haystack, needle));

    }
}
