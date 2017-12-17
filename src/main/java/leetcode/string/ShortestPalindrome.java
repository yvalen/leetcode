package leetcode.string;

/*
 * Given a string S, you are allowed to convert it to a palindrome by adding characters in front of it. 
 * Find and return the shortest palindrome you can find by performing this transformation.
 * For example:
 * Given "aacecaaa", return "aaacecaaa".
 * Given "abcd", return "dcbabcd".
 */
public class ShortestPalindrome {

    // find the longest palindrome substring starting at position 0.
    // Then insert the remaining of the string s to the front in a reverse order
    // Time complexity: O(n^2)
    public String shortestPalindrome_bruteforce(String s) {
        if (s == null || s.isEmpty())
            return s;

        int end = s.length() - 1;
        while (end > 0) {
            if (isPalindrome(s, 0, end))
                break;
            end--;
        }

        StringBuilder sb = new StringBuilder(s.substring(end + 1)); // substring
                                                                    // should
                                                                    // not
                                                                    // contain
                                                                    // the
                                                                    // character
                                                                    // at end
        sb.reverse(); // reverse the substring
        sb.append(s);
        return sb.toString();
    }

    private boolean isPalindrome(String s, int start, int end) {
        while (start < end) {
            if (s.charAt(start) != s.charAt(end))
                return false;
            start++;
            end--;
        }
        return true;
    }

    public String shortestPalindrome_kmp(String s) {
        if (s == null || s.isEmpty())
            return s;

        // build a temp string: s + # + reverse of s
        String temp = s + "#" + new StringBuilder(s).reverse().toString();
        int[] dfa = preprocess(temp);
        return new StringBuilder(s.substring(dfa[dfa.length - 1])).reverse().toString() + s;
    }

    private int[] preprocess(String p) {
        int[] dfa = new int[p.length()];
        for (int i = 1, j = 0; i < p.length(); i++) {
            while (j > 0 && p.charAt(i) != p.charAt(j)) {
                j = dfa[j - 1];
            }
            if (p.charAt(i) == p.charAt(j)) {
                j++;
            }
            dfa[i] = j;
        }
        return dfa;
    }

    public static void main(String[] args) {
        ShortestPalindrome sp = new ShortestPalindrome();
        String s = "aacecaaa";
        // String s = "abcd";
        System.out.println(sp.shortestPalindrome_kmp(s));
    }
}
