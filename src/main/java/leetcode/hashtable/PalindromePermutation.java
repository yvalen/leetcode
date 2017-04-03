package leetcode.hashtable;

import java.util.HashSet;
import java.util.Set;

/*
 * Given a string, determine if a permutation of the string could form a palindrome.
 * For example, "code" -> False, "aab" -> True, "carerac" -> True.
 */
public class PalindromePermutation {
	public boolean canPermutePalindrome(String s) {
		if (s == null || s.isEmpty()) return true;
		
		Set<Character> charSet = new HashSet<>();
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (charSet.contains(c)) {
				charSet.remove(c);
			}
			else {
				charSet.add(c);
			}
		}
		
		return charSet.size() <= 1; // size == 0 - even number, size == 1 - odd number
    }

	public static void main(String[] args) {
		PalindromePermutation p = new PalindromePermutation();
		//String s = "aab";
		//String s = "carerac";
		//String s = "code";
		String s = "aa";
		System.out.println(p.canPermutePalindrome(s));
	}
}
