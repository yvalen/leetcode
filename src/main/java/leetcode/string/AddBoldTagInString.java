package leetcode.string;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import company.airbnb.KEditDistance;
import leetcode.array.Interval;

/*
 * LEETCODE 616
 * Given a string s and a list of strings dict, you need to add a closed pair 
 * of bold tag <b> and </b> to wrap the substrings in s that exist in dict. 
 * If two such substrings overlap, you need to wrap them together by only one 
 * pair of closed bold tag. Also, if two substrings wrapped by bold tags are 
 * consecutive, you need to combine them.
 * Example 1:
 * Input: s = "abcxyz123" dict = ["abc","123"]
 * Output: "<b>abc</b>xyz<b>123</b>"
 * Example 2:
 * Input: s = "aaabbcc" dict = ["aaa","aab","bc"]
 * Output: "<b>aaabbc</b>c"
 * Note:
 * - The given dict won't contain duplicates, and its length won't exceed 100.
 * - All the strings in input have length in range [1, 1000].
 * 
 * Company: Google
 * Difficulty: medium
 * Similar Questions: 56(MergeIntervals), 591
 */
public class AddBoldTagInString {
    public String addBoldTag(String s, String[] dict) {
        if (s == null || s.isEmpty() || dict == null || dict.length == 0) {
            return s;
        }
        
        int n = s.length();
        boolean[] isBold = new boolean[n];
        for (int i = 0; i < n; i++) {
            for (String word : dict) {
                boolean found = true;
                for (int j = 0; j < word.length(); j++) {
                    if (i+j >= n || s.charAt(i+j) != word.charAt(j)) {
                        found = false;
                        break;
                    }
                }
                if (found) {
                    for (int j = i; j < i+word.length(); j++) {
                        isBold[j] = true;
                    }
                }
            }
        }
        
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            if (isBold[i] && (i == 0 || !isBold[i-1])) {
                sb.append("<b>");
            }
            sb.append(s.charAt(i));
            if (isBold[i] && (i == n-1 || !isBold[i+1])) {
                sb.append("</b>");
            }
        }
        return sb.toString();
    }
    
    
    public String addBoldTag_interval(String s, String[] dict) {
        if (s == null || s.isEmpty() || dict == null || dict.length == 0) {
            return s;
        }
        
        List<Interval> intervals = new ArrayList<>();
        for (String word : dict) {
            int idx = s.indexOf(word, 0);
            while (idx != - 1) {
                intervals.add(new Interval(idx, idx+word.length()));
                idx = s.indexOf(word, idx+1); // need to use idx+1 here to find the next idx
            }
        }
        if (intervals.isEmpty()) return s;
        
        Collections.sort(intervals, (a,b)->a.start-b.start);
        List<Interval> merged = new ArrayList<>();
        merged.add(intervals.get(0));
        for (int i = 1, j = 0; i < intervals.size(); i++) {
            Interval interval = intervals.get(i);
            if (merged.get(j).end >= interval.start) {
                merged.get(j).end = Math.max(merged.get(j).end , interval.end);
            }
            else {
                merged.add(interval);
                j++;
            }
        }
        
        StringBuilder sb = new StringBuilder();
        int prev = 0;
        for (Interval interval : merged) {
            sb.append(s.substring(prev, interval.start));
            sb.append("<b>");
            sb.append(s.substring(interval.start, interval.end));
            sb.append("</b>");
            prev = interval.end;
        }
        if (prev < s.length()) sb.append(s.substring(prev));
        return sb.toString();
    }

    
    public static void main(String[] args) {
        AddBoldTagInString abs = new AddBoldTagInString();
        //String s = "abcxyz123";
        //String[] dict = {"abc","123"};
        String s = "aaabbcc";
        String[] dict = {"aaa","aab","bc"};
        System.out.println(abs.addBoldTag_interval(s, dict));
    }
}
