package leetcode.greedy;

import java.awt.List;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

import sun.security.util.UntrustedCertificates;


/*
 * LEETCODE 767
 * Given a string S, check if the letters can be rearranged so that two characters 
 * that are adjacent to each other are not the same. If possible, output any possible 
 * result.  If not possible, return the empty string.
 * Example 1:
 * Input: S = "aab"
 * Output: "aba"
 * Example 2:
 * Input: S = "aaab"
 * Output: ""
 * Note: S will consist of lower case letters and have length in range [1, 500].
 * 
 * Difficulty: medium
 * Similar Questions: 358(RearrangeStringKDistanceApart), 621(TaskScheduler)
 */
public class ReorganizeString {
    private static class Entry {
        char c;
        int count;
        Entry(char c, int count) {
            this.c = c;
            this.count = count;
        }
    }
    
    public String reorganizeString(String S) {
        
        int[] count = new int[26];
        for (char c : S.toCharArray()) {
            count[c-'a']++;
        }
        
        PriorityQueue<Entry> pq = new PriorityQueue<>((a,b) -> b.count - a.count);
        for (int i = 0; i < 26; i++) {
            if (count[i] > 0) {
                if (count[i] > (S.length()+1)/2) return "";
                pq.offer(new Entry((char)(i+'a'), count[i]));
            }
        }
        
        StringBuilder sb = new StringBuilder();
        while (pq.size() >= 2) {
            Entry e1 = pq.poll();
            Entry e2 = pq.poll();
            sb.append(e1.c).append(e2.c);
            
            // should enqueue e1 first as e1 is added to sb first
            if (--e1.count > 0) pq.offer(e1);
            if (--e2.count > 0) pq.offer(e2);
            
        }
        if (pq.size() > 0) sb.append(pq.poll());
        
        return sb.toString();
    }
}
