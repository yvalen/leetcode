package leetcode.hashtable;

import java.util.HashMap;
import java.util.Map;

/*
 * LEETCODE 290
 * Given a pattern and a string str, find if str follows the same pattern.
 * Here follow means a full match, such that there is a bijection between a
 * letter in pattern and a non-empty word in str. 
 * Examples: 
 * pattern = "abba", str = "dog cat cat dog" should return true 
 * pattern = "abba", str = "dog cat cat fish" should return false. 
 * pattern = "aaaa", str = "dog cat cat dog" should return false. 
 * pattern = "abba", str = "dog dog dog dog" should return false. 
 * Notes: You may assume pattern contains only lower case letters, and str contains 
 * lower case letters separated by a single space.
 * 
 * Company: Dropbox, Uber 
 * Difficulty: easy
 * Similar Questions: 205(IsomorphicStrings), 291(WordPatternII)
 */
public class WordPattern {
    
    public boolean wordPattern(String pattern, String str) {
        String[] strAry = str.split(" ");
        if (pattern.length() != strAry.length)
            return false;

        Map<Character, Integer> mapP = new HashMap<>();
        Map<String, Integer> mapS = new HashMap<>();
        for (int i = 0; i < pattern.length(); i++) {
            char p = pattern.charAt(i);
            String s = strAry[i];
            if (!mapP.getOrDefault(p, -1).equals(mapS.getOrDefault(s, -1)))
                return false;
            mapS.put(s, i);
            mapP.put(p, i);
        }
        return true;
    }
}
