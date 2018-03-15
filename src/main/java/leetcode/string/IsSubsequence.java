package leetcode.string;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * LEETCODE 392
 * Given a string s and a string t, check if s is subsequence of t.
 * You may assume that there is only lower case English letters in b
 * oth s and t. t is potentially a very long (length ~= 500,000) string, 
 * and s is a short string (<=100). A subsequence of a string is a new 
 * string which is formed from the original string by deleting some (can 
 * be none) of the characters without disturbing the relative positions of 
 * the remaining characters. 
 * (ie, "ace" is a subsequence of "abcde" while "aec" is not).
 * Example 1: s = "abc", t = "ahbgdc" Return true.
 * Example 2: s = "axc", t = "ahbgdc" Return false.
 * Follow up: If there are lots of incoming S, say S1, S2, ... , Sk where 
 * k >= 1B, and you want to check one by one to see if T has its subsequence. 
 * In this scenario, how would you change your code?
 * 
 * Company: Pinterest
 * Difficulty: medium
 */
public class IsSubsequence {
    public boolean isSubsequence(String s, String t) {
        if (t == null || t.isEmpty()) return s == null || s.isEmpty();
        if (s == null || s.isEmpty()) return true;
        
        for (int i = 0, j = 0; i < t.length(); i++) {
            if (s.charAt(j) == t.charAt(i)) {
                j++;
                if (j == s.length()) return true;
            }
        }
        return false;
    }
    
    /*
     * Follow-up
     * If we check each sk in this way, then it would be O(kn) time where 
     * k is the number of s and n is the length of t, Since there is a lot of s, 
     * it would be reasonable to pre-process t to generate something that is easy 
     * to search for if a character of s is in t. 
     * O(N) time for pre-processing t, creating the index map for t, 
     * O(Mlogn) for each S. M is the average size of S
     * Eg-1. s="abc", t="bahbgdca"
     * idx=[a={1,7}, b={0,3}, c={6}]
     * i=0 ('a'): prev=1
     * i=1 ('b'): prev=3
     * i=2 ('c'): prev=6 (return true)
     * Eg-2. s="abc", t="bahgdcb"
     * idx=[a={1}, b={0,6}, c={5}]
     * i=0 ('a'): prev=1
     * i=1 ('b'): prev=6
     * i=2 ('c'): prev=? (return false)
     */
    public boolean isSubsequence_binarySearch(String s, String t) {
        Map<Character, List<Integer>> indexMap = new HashMap<>();
        for (int i = 0 ; i < t.length(); i++) {
            Character c = t.charAt(i);
            indexMap.putIfAbsent(c, new ArrayList<>());
            indexMap.get(c).add(i);
        }
        
        int prev = -1;  // index of the previous char in t
        for (char c : s.toCharArray()) {
            if (!indexMap.containsKey(c)) return false;
            List<Integer> list = indexMap.get(c);
            /*
            int index = Collections.binarySearch(list, prev);
            if (index < 0) { // need to do this check as prev starts from -1
                index = -index - 1;
            }
            if (index == list.size()) {
                return false;
            }
            */
            prev = binarySearch(prev, list);
            if (prev == -1) return false;
            prev++; // need to increment prev by one here 
        }
        return true;
    }
    
    
    // find the first element of list that is greater than or equal to target
    private int binarySearch(int target, List<Integer> list) {
        int lo = 0, hi = list.size()-1;
        while (lo <= hi) {
            int mid = lo + (hi-lo)/2;
            if (list.get(mid) < target) lo = mid + 1;
            else hi = mid-1;
        }
        return lo == list.size() ? -1 : list.get(lo);
    }
    
    public static void main(String[] args) {
       IsSubsequence is = new IsSubsequence();
       //String s = "axc", t = "ahbgdc";
       //String s = "", t = "ahbgdc";
       //String s = "abc", t = "ahbgdc";
       //String s = "acb", t = "ahbgdc";
       String s = "aaa", t = "aaaabcabcdc";
       System.out.println(is.isSubsequence_binarySearch(s, t));
   }

}
