package leetcode.hashtable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * Given a string, we can "shift" each of its letter to its successive letter, for example: "abc" -> "bcd". 
 * We can keep "shifting" which forms the sequence: "abc" -> "bcd" -> ... -> "xyz"
 * Given a list of strings which contains only lowercase alphabets, group all strings that belong to the same shifting sequence.
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
 */
public class GroupShitedStrings {
    public List<List<String>> groupStrings(String[] strings) {
        if (strings == null || strings.length == 0)
            return Collections.emptyList();

        Map<String, List<String>> map = new HashMap<>();
        for (String s : strings) {
            String key = generateKey(s);
            List<String> list = map.getOrDefault(key, new ArrayList<>());
            list.add(s);
            map.put(key, list);
        }

        return new ArrayList<>(map.values());
    }

    private String generateKey(String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < s.length(); i++) {
            int diff = s.charAt(i) - s.charAt(i - 1);
            sb.append('a' + (diff < 0 ? diff + 26 : diff)); // add 26 to diff if
                                                            // it is negative.
                                                            // handle cases like
                                                            // az,ba
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        GroupShitedStrings gss = new GroupShitedStrings();
        String[] strings = { "abc", "bcd", "acef", "xyz", "az", "ba", "a", "z" };
        System.out.println(gss.groupStrings(strings));
    }

}
