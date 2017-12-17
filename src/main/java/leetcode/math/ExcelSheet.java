package leetcode.math;

public class ExcelSheet {
    /*
     * Given a positive integer, return its corresponding column title as appear
     * in an Excel sheet. For example, 1 -> A 2 -> B 3 -> C ... 26 -> Z 27 -> AA
     * 28 -> AB
     */
    public String convertToTitle(int n) {
        StringBuilder sb = new StringBuilder();

        while (n > 0) {
            sb.append((char) ((n - 1) % 26 + 'A'));
            n = (n - 1) / 26;
        }
        return sb.reverse().toString();
    }

    /*
     * Given a column title as appear in an Excel sheet, return its
     * corresponding column number. For example, A -> 1 B -> 2 C -> 3 ... Z ->
     * 26 AA -> 27 AB -> 28
     */
    public int titleToNumber(String s) {
        int num = 0;
        for (int i = 0; i < s.length(); i++) {
            num = num * 26 + (s.charAt(i) - 'A' + 1);
        }
        return num;
    }

    public static void main(String[] atgs) {
        ExcelSheet e = new ExcelSheet();
        int n = 28;
        System.out.println(e.convertToTitle(n));

        String s = "AB";
        System.out.println(e.titleToNumber(s));
    }
}
