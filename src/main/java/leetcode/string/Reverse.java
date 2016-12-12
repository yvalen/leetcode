package leetcode.string;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Reverse {
	/**
	 * Write a function that takes a string as input and returns the string reversed.
	 * Example: Given s = "hello", return "olleh".
	 */
	public String reverseString(String s) {
		char[] chars = s.toCharArray();
		for (int i = 0, j = s.length() - 1; i < j; i++, j--) {
			char c = chars[i];
			chars[i] = chars[j];
			chars[j] = c;
		}
		return new String(chars);
    }
	
	/**
	 * Given an input string, reverse the string word by word.
	 * For example, Given s = "the sky is blue", return "blue is sky the". 
	 */
	public String reverseWords(String s) {
		if (s == null) return null;
		
		s = s.trim();
		if (s.isEmpty()) return s;
		
        String[] words = s.split("\\s+");
        int len = words.length;
        StringBuilder sb = new StringBuilder();
        for (int i = len - 1; i>= 0; i--) {
        	if (sb.length() > 0) {
        		sb.append(" ");
        	}
        	sb.append(words[i]);
        }
        
        return sb.toString();
    }
	
	/**
	 * Given an input string, reverse the string word by word. A word is defined as a sequence of non-space characters.
	 * The input string does not contain leading or trailing spaces and the words are always separated by a single space.
	 * For example, Given s = "the sky is blue", return "blue is sky the". 
	 */
	public void reverseWordsInPlace(char[] s) {
		if (s == null) return;
		
		int n = s.length, start = 0;
		for (int i = 0; i <= n; i++) {
			if (i == n || s[i] == ' ') {
				reverseCharArray(s, start, i - 1);
				start = i + 1;
			}	
		}
		reverseCharArray(s, 0, n - 1);
    }
	
	private void reverseCharArray(char[] chars, int start, int end) {
		while (start < end) {
			char c = chars[start];
			chars[start] = chars[end];
			chars[end] = c;
			start++;
			end--;
		}
	}
	
	/**
	 * Write a function that takes a string as input and reverse only the vowels of a string.
	 * Example 1: Given s = "hello", return "holle".
	 * Example 2: Given s = "leetcode", return "leotcede".
	 * Note: The vowels does not include the letter "y". 
	 */
	public String reverseVowels(String s) {
		if (s == null) return null;
		
		char[] chars = s.toCharArray();
		int start = 0, end = chars.length - 1;
		while (start < end) {
			if (isVowel(chars[start]) && isVowel(chars[end])) {
				char c = chars[start];
				chars[start] = chars[end];
				chars[end] = c;
				start++;
				end--;
			}
			else if (isVowel(chars[start])) {
				end--;
				continue;
			}
			else if (isVowel(chars[end])) {
				start++;
				continue;
			}
			else {
				start++;
				end--;
			}
		}
		
		return new String(chars);
    }
	
	private boolean isVowel(char c) {
		return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c== 'u' ||
				c == 'A' || c == 'E' || c == 'I' || c == 'O' || c== 'U' ;
	}
	
	public static void main(String[] ars) {
		Reverse r = new Reverse();
		
		/*
		String words = "a";
		String reversedWords = r.reverseWords(words);
		System.out.println(reversedWords);
		*/
		
		/*
		String words = "hi! world";
		char[] chars = words.toCharArray();
		r.reverseWordsInPlace(chars);
		System.out.println(chars);
		*/
		
		String words = "leetcode";
		String reversedWords = r.reverseVowels(words);
		System.out.println(reversedWords);
		
	}
}
