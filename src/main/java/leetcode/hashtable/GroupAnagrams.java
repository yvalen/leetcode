package leetcode.hashtable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/*
 * LEETCODE 49
 * Given an array of strings, group anagrams together.
 * For example, given: ["eat", "tea", "tan", "ate", "nat", "bat"],
 * Return:
 * [
 * 	["ate", "eat","tea"],
 * 	["nat","tan"],
 * 	["bat"]
 * ]
 * Note: All inputs will be in lower-case.
 * anagrams is a word, phrase, or name formed by rearranging the letters of another, 
 * such as cinema, formed from iceman.
 * 
 * Company: Facebook, Amazon, Bloomberg, Uber, Yelp
 * Difficulty: medium
 * Similar Questions: 242(ValidAnagram), 249(GroupShitedStrings)
 */
public class GroupAnagrams {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();

        for (String str : strs) {
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String key = new String(chars);
            map.putIfAbsent(key, new ArrayList<>());
            map.get(key).add(str);
        }

        return new ArrayList<List<String>>(map.values());
    }

    public static void main(String[] args) {
        GroupAnagrams g = new GroupAnagrams();

        String[] strs = { "", "" };
        System.out.println(g.groupAnagrams(strs));

    }
}
