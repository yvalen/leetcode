package leetcode.string;

/*
 * LEETCODE 161
 * Given two strings S and T, determine if they are both one edit distance apart.
 * One edit means remove/add/change 1 character.
 * 
 * Company: Facebook, Uber, Twitter, Snapchat
 * Difficulty: medium
 * Similar Questions: 72(EditDistance)
 */
public class OneEditDistance {
    public boolean isOneEditDistance(String s, String t) {
        if (s == null)
            return t == null ? false : t.length() == 1;
        if (t == null)
            return s.length() == 1;

        int sLen = s.length(), tLen = t.length(), len = Integer.min(sLen, tLen);
        if (Math.abs(tLen - sLen) > 1)
            return false;

        for (int i = 0; i < len; i++) {
            if (s.charAt(i) == t.charAt(i))
                continue;

            if (sLen == tLen)
                return s.substring(i + 1).equals(t.substring(i + 1));

            if (sLen < tLen)
                return s.substring(i).equals(t.substring(i + 1));

            return s.substring(i + 1).equals(t.substring(i));
        }

        // All previous chars are the same, the only possibility is deleting the
        // end char in the longer one of s and t
        return sLen != tLen;
    }

    public boolean isOneEditDistance_withSubstring(String s, String t) {
        if (s == null)
            return (t != null && t.length() == 1);
        if (t == null)
            return s.length() == 1;

        if (Math.abs(s.length() - t.length()) > 1)
            return false;

        for (int i = 0; i < Math.min(s.length(), t.length()); i++) {
            if (s.charAt(i) != t.charAt(i)) { // the first different character
                if (s.length() == t.length()) {
                    // s and t have the same length, we can only replace the
                    // first different char
                    return s.substring(i + 1).equals(t.substring(i + 1));
                }

                if (s.length() < t.length()) {
                    // s is shorter than t, we can only delete the first
                    // different char from t
                    return s.substring(i).equals(t.substring(i + 1));
                }

                // s is longer than t, we can only delete the first different
                // cgar from s
                return s.substring(i + 1).equals(t.substring(i));
            }
        }

        // all characters are same so far, we can only delete the last character
        // of the longer one
        return s.length() != t.length();
    }

    public boolean isOneEditDistance_withCharArray(String s, String t) {
        if (s == null) return t == null ? false : t.length() == 1;
        if (t == null) return s.length() == 1;

        int sLen = s.length(), tLen = t.length();
        if (sLen > tLen) return isOneEditDistance(t, s);
        if (tLen > sLen+1) return false;

        int i = 0, j = 0;
        boolean firstDiff = false;
        while (i < sLen && j < tLen) {
            if (s.charAt(i) == t.charAt(j)) {
                i++;
                j++;
            }
            else {
                if (firstDiff) return false;
                firstDiff = true;
                if (sLen == tLen) {
                    // replace
                    i++;
                    j++;
                }
                else {
                    // delete
                    j++;
                }
            }
        }

        // if all chars are the same, make sure j is at the 
        // last char of t to handle ("a", "") ("aa", "aab")
        return firstDiff || j == tLen-1;
    }

    public static void main(String[] args) {
        OneEditDistance d = new OneEditDistance();
        // String s = "", t = "";
        // String s = "a", t = "";
        // String s = "ab", t = "cab";
        String s = "ab", t = "ac";
        System.out.println(d.isOneEditDistance_withSubstring(s, t));
    }
}
