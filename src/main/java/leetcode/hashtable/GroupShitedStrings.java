package leetcode.hashtable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * LEETCODE 249
 * Given a string, we can "shift" each of its letter to its successive letter, 
 * for example: "abc" -> "bcd". 
 * We can keep "shifting" which forms the sequence: "abc" -> "bcd" -> ... -> "xyz"
 * Given a list of strings which contains only lowercase alphabets, group all strings 
 * that belong to the same shifting sequence.
 * For example, given: ["abc", "bcd", "acef", "xyz", "az", "ba", "a", "z"],
 * A solution is:
 * [
 * 	["abc","bcd","xyz"],
 * 	["az","ba"],
 * 	["acef"],
 * 	["a","z"]
 * ]
 * 
 * Company: Google, Uber
 * Difficulty: medium
 * Similar Questions: 49(GroupAnagrams)
 */
public class GroupShitedStrings {
    public List<List<String>> groupStrings(String[] strings) {
        if (strings == null || strings.length == 0)
            return Collections.emptyList();

        Map<String, List<String>> map = new HashMap<>();
        for (String s : strings) {
            String key = generateKey(s);
            map.putIfAbsent(key, new ArrayList<>());
            map.get(key).add(s);
        }
        System.out.println(map);
        
        return new ArrayList<>(map.values());
    }

    private String generateKey(String s) {
        StringBuilder sb = new StringBuilder();
        // key for single character is ""
        for (int i = 1; i < s.length(); i++) {
            int diff = s.charAt(i) - s.charAt(i - 1);
            // add 26 to diff if it is negative. handle cases like az,ba
            sb.append('a' + (diff < 0 ? diff + 26 : diff));
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        GroupShitedStrings gss = new GroupShitedStrings();
        String[] strings = { "abc", "bcd", "acef", "xyz", "az", "ba", "a", "z" };
        //String[] strings = {  "a", "z" };
        System.out.println(gss.groupStrings(strings));
    }

}
