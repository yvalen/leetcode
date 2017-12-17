package leetcode.backtracking;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
 * Given a string containing only digits, restore it by returning all possible valid IP address combinations.
 * For example: given "25525511135", return ["255.255.11.135", "255.255.111.35"]. (Order does not matter) 
 */
public class RestoreIPAddress {
    public List<String> restoreIpAddresses(String s) {
        if (s == null || s.isEmpty() || s.length() < 4 || s.length() > 12)
            return Collections.emptyList();

        List<String> result = new ArrayList<>();
        int len = s.length();

        // 3-loop divides the string s into 4 substring: s1, s2, s3, s4. Check
        // if each substring is valid.
        for (int i = 1; i < 4 && i < len - 2; i++) { // i is the end index of s1
            for (int j = i + 1; j < i + 4 && j < len - 1; j++) { // j is the end
                                                                 // index of s2
                for (int k = j + 1; k < j + 4 && k < len; k++) {
                    String s1 = s.substring(0, i), s2 = s.substring(i, j), s3 = s.substring(j, k), s4 = s.substring(k);
                    if (isValid(s1) && isValid(s2) && isValid(s3) && isValid(s4)) {
                        result.add(s1 + "." + s2 + "." + s3 + "." + s4);
                    }
                }
            }
        }

        return result;
    }

    private boolean isValid(String s) {
        if (s.length() > 3 || s.length() == 0 || (s.charAt(0) == '0' && s.length() > 1) || Integer.parseInt(s) > 255)
            return false;
        return true;
    }

    public List<String> restoreIpAddresses_backtrack(String s) {
        if (s == null || s.isEmpty() || s.length() < 4 || s.length() > 12)
            return Collections.emptyList();

        List<String> result = new ArrayList<>();
        helper(s, result, "", 0);
        return result;
    }

    private void helper(String s, List<String> result, String ip, int count) {
        if (s.isEmpty() || count == 4) {
            if (s.isEmpty() && count == 4) {
                result.add(ip.substring(1)); // remove the leading .
            }
            return;
        }

        for (int i = 1; i <= (s.charAt(0) == '0' ? 1 : 3) && i <= s.length(); i++) {
            String part = s.substring(0, i);
            if (Integer.parseInt(part) <= 255) {
                helper(s.substring(i), result, ip + "." + part, count + 1);
            }
        }
    }

    public static void main(String[] args) {
        RestoreIPAddress r = new RestoreIPAddress();
        String s = "0000";
        System.out.println(r.restoreIpAddresses_backtrack(s));
    }
}
