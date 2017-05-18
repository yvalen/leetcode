package leetcode.string;

import java.util.Arrays;
import java.util.List;

/*
 * Given a sequence of words, check whether it forms a valid word square. A sequence of words forms a valid word square 
 * if the kth row and column read the exact same string, where 0 ≤ k < max(numRows, numColumns).
 * Note:
 * - The number of words given is at least 1 and does not exceed 500.
 * - Word length will be at least 1 and does not exceed 500.
 * - Each word contains only lowercase English alphabet a-z.
 */
public class ValidWordSquare {
	public boolean validWordSquare(List<String> words) {
		for (int i = 0; i < words.size(); i++) {
			String word = words.get(i);
			for (int j = 0; j < word.length(); j++) {
				if (j >= words.size() || words.get(j).length() <= i  || // need to check boundary 
						word.charAt(j) != words.get(j).charAt(i)) {
					return false;
				}
			}
		}
		return true;
    }
	
	public static void main(String[] args) {
		ValidWordSquare vws = new ValidWordSquare();
		//List<String> words = Arrays.asList("abcd", "bnrt", "crmy", "dtye");
		//List<String> words = Arrays.asList("abcd", "bnrt", "crm", "dt");
		//List<String> words = Arrays.asList("ball", "area", "read", "lady");
		//List<String> words = Arrays.asList("ball", "asee", "let", "lep");
		List<String> words = Arrays.asList("abc", "b");
		System.out.println(vws.validWordSquare(words));
	}

}
