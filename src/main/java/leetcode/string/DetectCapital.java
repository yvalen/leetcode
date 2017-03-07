package leetcode.string;

/*
 * Given a word, you need to judge whether the usage of capitals in it is right or not.
 * We define the usage of capitals in a word to be right when one of the following cases holds:
 * - All letters in this word are capitals, like "USA".
 * - All letters in this word are not capitals, like "leetcode".
 * - Only the first letter in this word is capital if it has more than one letter, like "Google".
 * Otherwise, we define that this word doesn't use capitals in a right way.
 * Example 1:
 * 	Input: "USA"
 * 	Output: True
 * Example 2:
 * 	Input: "FlaG"
 * 	Output: False
 * Note: The input will be a non-empty word consisting of upper case and lower case Latin letters. 
 */
public class DetectCapital {
	public boolean detectCapitalUse_slow(String word) {
		if (word == null || word.isEmpty()) return true;
		
		Boolean prevUpper = null;
		for (int i = 0; i < word.length(); i++) {
			Character c = word.charAt(i);
			if (Character.isUpperCase(c)) {
				if (prevUpper != null && prevUpper.equals(Boolean.FALSE)) return false;
				prevUpper = Boolean.TRUE;
			}
			else {
				if (i>=2 && prevUpper != null && prevUpper.equals(Boolean.TRUE)) return false;
				prevUpper = Boolean.FALSE;
			}
		}
		
        return true;
    }
	
	public boolean detectCapitalUse_usingLengh(String word) {
		if (word == null || word.isEmpty()) return true;
		
		int numOfUpperCaseLetters = 0;
		for (int i = 0; i < word.length(); i++) {
			if (Character.isUpperCase(word.charAt(i))) numOfUpperCaseLetters++;
		}
		
		if (numOfUpperCaseLetters == 1) return Character.isUpperCase(word.charAt(0));
		
		return numOfUpperCaseLetters == 0 ||numOfUpperCaseLetters == word.length();
	}
	
	public static void main(String[] args) {
		DetectCapital d = new DetectCapital();
		
		String word = "NIy";
		System.out.println(d.detectCapitalUse_usingLengh(word));
	}
}
