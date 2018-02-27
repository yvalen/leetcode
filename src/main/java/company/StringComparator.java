package company;

import java.util.Arrays;
import java.util.Comparator;


public class StringComparator implements Comparator<String> {

        @Override
        public int compare(String o1, String o2) {
            if (o1 == null) return o2 == null ? 0 : -1;
            if (o2 == null) return o1 == null ? 0 : 1;

            int i = 0, j = 0, len1 = o1.length(), len2 = o2.length();
            while (i < len1 && j < len2) {
                char c1 = o1.charAt(i), c2 = o2.charAt(j);
                if (c1 == c2) {
                    i++;
                    j++;
                    continue;
                }
                
                if (Character.isDigit(c1) && Character.isDigit(c2)) {
                    int end1 = getDigitEnd(o1, i), end2 = getDigitEnd(o2, j);
                    int d1 = Integer.parseInt(o1.substring(i, end1));
                    int d2 = Integer.parseInt(o2.substring(j, end2));
                    if (d1 != d2) return d1 > d2 ? 1 : -1;
                    i = end1;
                    j = end2;
                }
                else {
                    return c1 < c2 ? -1 : 1;
                }
            }
            if (i == len1 && j == len2) return 0;
            return i == len1 ? -1 : 1;
        }
        
        private int getDigitEnd(String s, int start) {
            while (start < s.length() && Character.isDigit(s.charAt(start))) {
                start++;
            }
            return start;
        }
    
    
    public static void main(String[] args) {
        String[] strs = {"ab123", "b0","a567", "a12", "a4", "a123", "1234", "123b", "12a45"};
        Arrays.sort(strs, new StringComparator());
        System.out.println(Arrays.toString(strs));
    }
    
    
}
