package leetcode.hashtable;

import java.util.HashMap;
import java.util.Map;

/*
 * LEETCODE 76
 * Given a string S and a string T, find the minimum window in S which will contain 
 * all the characters in T in complexity O(n).
 * For example,
 * 	S = "ADOBECODEBANC"
 * 	T = "ABC"
 * Minimum window is "BANC".
 * Note: If there is no such window in S that covers all characters in T, return the 
 * empty string "". If there are multiple such windows, you are guaranteed that there 
 * will always be only one unique minimum window in S.
 * 
 * Company: Facebook, Uber, LinkedIn, Snapchat
 * Difficulty: hard
 * Similar Questions: 209(MinimumSizeSubarraySum)
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

    // https://discuss.leetcode.com/topic/30941/here-is-a-10-line-template-that-can-solve-most-substring-problems
    // 1. Use two pointers: left and right to represent a window.
    // 2. Move right to find a valid window.
    // 3. When a valid window is found, move left to find a smaller window.
    // Time complexity: O(n) The inner while loop (while(counter==0) is always from the "begin", and the "begin" 
    // never goes back. So the total running is roughly the first for loop time, plus the while loop and its inner 
    // while loop: O(n)+O(2n) = O(n).
    public String minWindow_withMap(String s, String t) {
        if (s == null || s.isEmpty() || t == null || t.isEmpty()) return "";

        Map<Character, Integer> charCount = new HashMap<>();
        for (Character c : t.toCharArray()) {
            charCount.put(c, charCount.getOrDefault(c, 0)+1);
        }

        int left = 0, right = 0, count = t.length(), minLen = Integer.MAX_VALUE, begin = 0;
        while (right < s.length()) {
            Character rightChar = s.charAt(right);
            if (charCount.containsKey(rightChar)) {
                if (charCount.get(rightChar) > 0) count--;
                charCount.put(rightChar, charCount.get(rightChar)-1);
            }
            right++;
            while (count == 0) { // valid window
                if (right-left < minLen) {
                    // use right-left instead of right-left+1 as 
                    // right has already incremented
                    minLen = right - left; 
                    begin = left;
                }
                Character leftChar = s.charAt(left);
                if (charCount.containsKey(leftChar)) {
                    if (charCount.get(leftChar) >= 0) count++;
                    charCount.put(leftChar, charCount.get(leftChar)+1);
                }
                left++;
            }
        }
        return minLen == Integer.MAX_VALUE ? "" : s.substring(begin, begin+minLen);
        /*
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
         */
    }
    
    public String minWindow(String s, String t) {
        if (s == null || s.isEmpty() || t == null || t.isEmpty()) return "";

        int[] charCount = new int[256];
        for (Character c : t.toCharArray()) {
            charCount[c]++;
        }
        
        int minLen = Integer.MAX_VALUE, begin = 0;
        for (int left = 0, right = 0, count = t.length(); right < s.length(); right++) {
            if (charCount[s.charAt(right)]-- > 0) {
                count--;
            }
            while (count == 0) {
                if (right-left+1 < minLen) {
                    minLen = right - left + 1;
                    begin = left;
                }
                if (charCount[s.charAt(left++)]++ >= 0) count++;
            }
        }
        return minLen == Integer.MAX_VALUE ? "" : s.substring(begin, begin+minLen);
    }

    public static void main(String[] args) {
        MinimumWindowSubstring m = new MinimumWindowSubstring();

         //String s = "ADOBECODEBANC", t = "ABC";
         String s = "ADOBECODBANC", t = "ABC";
        //String s = "cabwefgewcwaefgcf", t = "cae";

        // String s = "ADOBECA", t = "ABC";
        System.out.println("result=" + m.minWindow_withMap(s, t));
    }

}
