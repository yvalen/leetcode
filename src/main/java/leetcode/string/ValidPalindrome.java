package leetcode.string;

/*
 * Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.
 * For example, 
 * 	"A man, a plan, a canal: Panama" is a palindrome. 
 * 	"race a car" is not a palindrome. 
 * we define empty string as valid palindrome.
 */
public class ValidPalindrome {
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


	public static void main(String[] args) {
		ValidPalindrome v = new ValidPalindrome();
		String s = "ab";
		System.out.println(v.isPalindrome(s));
	}
}
