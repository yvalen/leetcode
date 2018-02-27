package company.airbnb;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
 * Given a string, convert lower case char into upper case
 * abC -> AbC, aBC, abC, ABC
 * 
 * Airbnb, Pinterest
 */
public class CombinationWithCases {

    public List<String> combinerLetters(String s) {
        if (s == null || s.isEmpty()) return Collections.emptyList();
        
        List<String> result = new ArrayList<>();
        combinerLetters(s, result, new StringBuilder(), 0);
        return result;
    }
    
    private void combinerLetters(String s, List<String> result, StringBuilder sb, int start) {
        if (sb.length() == s.length()) {
            result.add(new String(sb.toString()));
            return;
        }
        
        for (int i = start; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isLowerCase(c)) {
                sb.append(Character.toUpperCase(c));
                combinerLetters(s, result, sb, i+1);
                sb.deleteCharAt(sb.length()-1);
            }
           
            sb.append(c);
            combinerLetters(s, result, sb, i+1);
            sb.deleteCharAt(sb.length()-1);
        }
    }
    
    
    public static void main(String[] args) {
        CombinationWithCases c = new CombinationWithCases();
       
         
        String s =  "abC";
        System.out.println(c.combinerLetters(s));
    }

}
