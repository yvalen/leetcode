package leetcode.string;


public class ValidPalindrome {
	/*
	 * LEETCODE 125
	 * Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.
	 * For example, 
	 * 	"A man, a plan, a canal: Panama" is a palindrome. 
	 * 	"race a car" is not a palindrome. 
	 * we define empty string as valid palindrome.
	 * 
	 * Company: Facebook, Microsoft, Uber, Zenefits
	 * Difficulty: easy
	 * Similar Questions: 234(PalindromeLinkedList), 680(Valid Palindrome II)
	 */
	public boolean isPalindrome(String s) {
		if (s == null || s.isEmpty()) return true;
		
		int i = 0, j = s.length() -1;
		while (i < j) {
		    char ci = s.charAt(i), cj = s.charAt(j);
			if (!Character.isLetterOrDigit(ci)) i++;
			else if (!Character.isLetterOrDigit(cj)) j--;
			else {
				if (Character.toLowerCase(ci) != Character.toLowerCase(cj)) {
					return false;
				}
				i++; 
				j--;
			}
		}
		return true;
    }

	/*
	 * LEETCODE 680
	 * Given a non-empty string s, you may delete at most one character. Judge whether you can make it a palindrome.
	 * Example 1: Input: "aba" Output: True
	 * Example 2: Input: "abca" Output: True
	 * Explanation: You could delete the character 'c'.
	 * Note: The string will only contain lower case characters a-z. The maximum length of the string is 50000.
	 * 
	 * Company: Facebook
	 * Difficulty: easy
	 * Similar Questions: 125(Valid Palindrome)
	 */
	public boolean validPalindrome(String s) {
		if (s == null || s.length() <= 2) return true;
		int l = 0, r = s.length()-1;
		while (l < r) {
			if (s.charAt(l) != s.charAt(r)) { // find the first un-matched characters and try to compare by skipping either of them 
				return isPalindrome(s, l, r-1) || isPalindrome(s, l+1, r);
			}
			l++;
			r--;
		}
		
        return true;
    }

	private boolean isPalindrome(String s, int l, int r) {
		while (l < r) {
			if (s.charAt(l) != s.charAt(r)) return false;
			l++;
			r--;
		}
		return true;
	}
	
	public static void main(String[] args) {
		ValidPalindrome v = new ValidPalindrome();
		String s = "ab";
		System.out.println(v.isPalindrome(s));
	}
}
