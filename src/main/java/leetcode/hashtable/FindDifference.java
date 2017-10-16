package leetcode.hashtable;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;


/**
 * LEETCODE 389
 * Given two strings s and t which consist of only lowercase letters. String t is generated 
 * by random shuffling string s and then add one more letter at a random position.
 * Find the letter that was added in t.
 * Example: input: s = "abcd", t = "abcde", output: e
 * Explanation: 'e' is the letter that was added.
 * 
 * Company: Google
 * Difficulty: easy
 * Similar Questions: 136(SingleNumber)
 */
public class FindDifference {
	public char findTheDifference(String s, String t) {
		int[] charCount = new int[26];
		for (char c : s.toCharArray()) {
			charCount[c-'a']++;
		}
		
		for (char c : t.toCharArray()) {
			int i = c - 'a';
			charCount[i]--;
			if (charCount[i] < 0) return c;
		}
		
		throw new IllegalArgumentException();
    }
	
	public static void main(String[] args) {
		FindDifference test = new FindDifference();
		
		char c = test.findTheDifference("abcd", "abcde");
		System.out.println(Character.toString(c));
	}

}
