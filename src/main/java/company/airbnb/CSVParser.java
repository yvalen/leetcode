package company.airbnb;

import java.util.ArrayList;
import java.util.List;


/*
 * Airbnb
 * Parse an escaped string into csvformat
 * Input: csvformat
 * John,Smith,john.smith@gmail.com,Los Angeles,1
 * Jane,Roberts,janer@msn.com,"San Francisco, CA",0
 * "Alexandra ""Alex""",Menendez,alex.menendez@gmail.com,Miami,1 """Alexandra Alex"""
 * Output: escaped string
 * John|Smith|john.smith@gmail.com|Los Angeles|1
 * Jane|Roberts|janer@msn.com|San Francisco, CA|0
 * Alexandra "Alex"|Menendez|alex.menendez@gmail.com|Miami|1 "Alexandra Alex"
 */
public class CSVParser {
    
    public static String parseCSV(String str) {
        List<String> result = new ArrayList<>();
        boolean inQuote = false;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (inQuote) {
                if (c == '\"') {
                    if (i < str.length()-1 && str.charAt(i+1) == '\"') {
                        sb.append(c);
                    }
                    else {
                        inQuote = false;
                    }
                }
                else {
                    sb.append(c);
                }
            }
            else {
                if (c == '\"') {
                    inQuote = true;
                }
                else if (c == ',') {
                    result.add(sb.toString());
                    sb.setLength(0);
                }
                else {
                    sb.append(c);
                }
            }
            
        }
        if (sb.length() > 0) result.add(sb.toString());
        
        return String.join("|", result);
    }
    
    public static void main(String[] args) {
        //String str = "John,Smith,john.smith@gmail.com,Los Angeles,1";
        //String str = "Jane,Roberts,janer@msn.com,\"San Francisco, CA\",0";
        String str = "\"Alexandra \"\"Alex\"\"\",Menendez,alex.menendez@gmail.com,Miami,1 \"\"\"Alexandra Alex\"\"\"";
        System.out.println(parseCSV(str));
    }
    
}
