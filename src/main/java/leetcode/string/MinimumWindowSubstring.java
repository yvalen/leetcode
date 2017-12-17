package leetcode.string;

import java.util.HashMap;
import java.util.Map;

/*
 * Given a string S and a string T, find the minimum window in S which will contain all the characters in T in complexity O(n).
 * For example,
 * 	S = "ADOBECODEBANC"
 * 	T = "ABC"
 * Minimum window is "BANC".
 * Note: If there is no such window in S that covers all characters in T, return the empty string "".
 * If there are multiple such windows, you are guaranteed that there will always be only one unique minimum window in S.
 */
public class MinimumWindowSubstring {

    public String minWindow_bruteforce(String s, String t) {
        String result = "";

        if (s == null || s.isEmpty() || t == null || t.isEmpty() || s.length() < t.length())
            return result;

        Map<Character, Integer> charCountMap = new HashMap<>();
        for (int i = 0; i < t.length(); i++) {
            charCountMap.put(t.charAt(i), charCountMap.getOrDefault(t.charAt(i), 0) + 1);
        }

        int windowLen = Integer.MAX_VALUE, sLen = s.length(), tLen = t.length();
        Map<Character, Integer> currentCountMap = new HashMap<>();
        for (int i = 0; i < sLen; i++) {
            int charCount = 0;
            currentCountMap.clear();
            for (int j = i; j < sLen; j++) {
                Character c = s.charAt(j);
                if (charCountMap.containsKey(c)) {
                    currentCountMap.put(c, currentCountMap.getOrDefault(c, 0) + 1);
                    if (currentCountMap.get(c) <= charCountMap.get(c)) {
                        charCount++;
                    }
                }

                if (charCount == tLen) {
                    if (windowLen > j + 1 - i) {
                        windowLen = j + 1 - i;
                        result = s.substring(i, j + 1);
                    }
                    break;
                }
            }
        }

        return result;
    }

    public String minWindow(String s, String t) {
        String result = "";

        if (s == null || s.isEmpty() || t == null || t.isEmpty() || s.length() < t.length())
            return result;

        Map<Character, Integer> charCountMap = new HashMap<>();
        for (int i = 0; i < t.length(); i++) {
            charCountMap.put(t.charAt(i), charCountMap.getOrDefault(t.charAt(i), 0) + 1);
        }

        int windowLen = Integer.MAX_VALUE;
        int left = 0, right = 0, tLen = t.length(), charCount = 0;
        Map<Character, Integer> currentCountMap = new HashMap<>();
        while (right < s.length()) {
            Character rightChar = s.charAt(right);
            if (charCountMap.containsKey(rightChar)) {
                currentCountMap.put(rightChar, currentCountMap.getOrDefault(rightChar, 0) + 1);
                if (currentCountMap.get(rightChar) <= charCountMap.get(rightChar)) {
                    charCount++;
                }
            }
            right++;

            while (charCount == tLen) {
                if (windowLen > right - left) {
                    windowLen = right - left;
                    result = s.substring(left, right);
                }

                Character leftChar = s.charAt(left);
                if (charCountMap.containsKey(leftChar)) {
                    if (currentCountMap.get(leftChar) <= charCountMap.get(leftChar)) {
                        charCount--;
                    }
                    currentCountMap.put(leftChar, currentCountMap.get(leftChar) - 1);
                }
                left++;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        MinimumWindowSubstring m = new MinimumWindowSubstring();

        // String s = "ADOBECODEBANC", t = "ABC";
        String s = "cabwefgewcwaefgcf", t = "cae";

        // String s = "ADOBECA", t = "ABC";
        System.out.println("result=" + m.minWindow(s, t));
    }

}
