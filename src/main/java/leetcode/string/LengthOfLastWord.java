package leetcode.string;

/**
 * Given a string s consists of upper/lower-case alphabets and empty space
 * characters ' ', return the length of last word in the string. If the last
 * word does not exist, return 0. Note: A word is defined as a character
 * sequence consists of non-space characters only. For example, given s =
 * "Hello World", return 5.
 */
public class LengthOfLastWord {
    public int lengthOfLastWord(String s) {
        if (s == null || s.isEmpty() || s.trim().isEmpty())
            return 0;

        String[] strAry = s.split(" ");
        return strAry[strAry.length - 1].length();
    }

    public int lengthOfLastWord_wihtoutSpli(String s) {
        if (s == null)
            return 0;

        s = s.trim();
        if (s.isEmpty())
            return 0;

        int lastIdx = s.lastIndexOf(' ');
        return (lastIdx == -1) ? s.length() : s.substring(lastIdx + 1).length();
    }

    public static void main(String[] args) {
        LengthOfLastWord l = new LengthOfLastWord();

        String s = "a ";
        int len = l.lengthOfLastWord(s);
        System.out.println(len);

    }
}
