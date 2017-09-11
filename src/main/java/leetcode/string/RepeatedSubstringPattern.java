package leetcode.string;

/*
 * Given a non-empty string check if it can be constructed by taking a substring of it and appending multiple copies of the substring together. 
 * You may assume the given string consists of lower case English letters only and its length will not exceed 10000.
 * Example 1: Input: "abab" Output: True
 * Explanation: It's the substring "ab" twice.
 * Example 2: Input: "aba" Output: False
 * Example 3: Input: "abcabcabcabc" Output: True
 * Explanation: It's the substring "abc" four times. (And the substring "abcabc" twice.)
 * 
 * Company: Amazon, Google
 * Difficulty: easy
 */
public class RepeatedSubstringPattern {
	public boolean repeatedSubstringPattern_bruteforce(String s) {
		int n = s.length();
		for (int i = 1; i <= n / 2; i++) {
			if (n % i != 0) continue;
			if (match(s.substring(0, i), s)) return true;
		}
		return false;
    }
	
	private boolean match(String prefix, String s) {
		if (prefix.equals(s)) return true;
		
		if (prefix.length() > s.length() || !s.startsWith(prefix)) return false;
		
		return match(prefix, s.substring(prefix.length()));
	}
	
	
    // The length of the repeating substring must be a divisor of the length of the input string
    // Search for all possible divisor of str.length, starting for length/2
    // If i is a divisor of length, repeat the substring from 0 to i the number of times i is contained in s.length
    // If the repeated substring is equals to the input str return true
	public boolean repeatedSubstringPattern(String s) {
		int n = s.length();
		for (int i = n / 2; i > 0; i--) {
			if (n % i != 0) continue;
			StringBuilder sb = new StringBuilder();
			String pattern = s.substring(0, i);
			int j = n / i;
			while (j-- > 0) {
				sb.append(pattern);
			}
			if (sb.toString().equals(s)) return true;
		}
		return false;
		
    }
	
	
	public static void main(String[] args) {
		RepeatedSubstringPattern rsp = new RepeatedSubstringPattern();
		String s = "bb";
		System.out.println(rsp.repeatedSubstringPattern_bruteforce(s));
	}
}
