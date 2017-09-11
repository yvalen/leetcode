package leetcode.greedy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

/*
 * Given a non-empty string s and an integer k, rearrange the string such that the same characters are at least distance k from each other.
 * All input strings are given in lower case letters. If it is not possible to rearrange the string, return an empty string "".
 * Example 1: s = "aabbcc", k = 3 Result: "abcabc"
 * The same letters are at least distance 3 from each other.
 * Example 2: s = "aaabc", k = 3  Answer: ""
 * It is not possible to rearrange the string.
 * Example 3: s = "aaadbbcc", k = 2 Answer: "abacabcd" Another possible answer is: "abcabcda"
 * The same letters are at least distance 2 from each other.
 * 
 * Company: Google
 * Difficulty: hard
 */
public class RearrangeStringKDistanceApart {
	public String rearrangeString_heap(String s, int k) {
		if (s.length() == 1 || k == 0) return s;
        if (k < 0) return "";
		
        Map<Character, Integer> countMap = new HashMap<>();
        for (Character c : s.toCharArray()) {
        	countMap.put(c,  countMap.getOrDefault(c, 0)+1);
        }
        
        PriorityQueue<Map.Entry<Character, Integer>> pq = new PriorityQueue<>(countMap.size(),
        		(a, b) -> a.getValue()==b.getValue() ? a.getKey().compareTo(b.getKey()) : (b.getValue() - a .getValue()));
        pq.addAll(countMap.entrySet());
        Queue<Map.Entry<Character, Integer>> waitq = new LinkedList<>();
 		
        StringBuilder sb = new StringBuilder();
		while (!pq.isEmpty()) {
			Map.Entry<Character, Integer> entry = pq.poll();
			entry.setValue(entry.getValue()-1);
			sb.append(entry.getKey());
			waitq.offer(entry);
			
			if(waitq.size() < k) continue; // waitq is not full yet
			
			// release from waitq if char is already k chars apart
			entry = waitq.poll();
			if (entry.getValue() > 0) pq.offer(entry);               	
		}
		
		return sb.length() == s.length() ? sb.toString() : "";
    }

	public String rearrangeString(String s, int k) {
		if (s.length() == 1 || k == 0) return s;
        if (k < 0) return "";
		
        int[] count = new int[26];
        for (char c : s.toCharArray()) {
        	count[c-'a']++;
        }
        int[] position = new int[26]; // stores the next valid position for the char
        
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
        	int next = getNext(count, position, i);
        	if (next == -1) return "";
        	count[next]--;
        	position[next] = i + k;
        	sb.append((char) ('a' + next));
        }
        return sb.toString();
	}
	
	// select a candidate character which has most remain count and is allowed at current 
	// position (k distance from last position).
	private int getNext(int[] count, int[] position, int index) {
		int max = 0, next = -1;
		for (int i = 0; i < count.length; i++) {
			if (count[i] > max && position[i] <= index) {
				max = count[i];
				next = i;
			}
		}
		return next;
	}
	
	public static void main(String[] args) {
		RearrangeStringKDistanceApart rskda = new RearrangeStringKDistanceApart ();
		//String s = "aabbcc";
		//String s = "aaabc";
		//String s = "aaadbbcc";
		// int k = 3;
		//String s = "aa";
		//int k = 0;
		String s = "aab";
		int k = 2;
		System.out.println(rskda.rearrangeString_heap(s, k));
	}
}
