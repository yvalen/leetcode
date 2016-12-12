package leetcode.hashtable;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;


/**
 * Given two strings s and t which consist of only lowercase letters. String t is generated 
 * by random shuffling string s and then add one more letter at a random position.
 * Find the letter that was added in t.
 * Example: input: s = "abcd", t = "abcde", output: e
 * Explanation: 'e' is the letter that was added.
 */
public class FindDifference {
	public char findTheDifference(String s, String t) {
		Set<Integer> letterSet = s.chars().boxed().collect(Collectors.toSet());
		System.out.println(letterSet);
		Optional<Integer> addedElem = t.chars().boxed().filter(e -> !letterSet.contains(e)).findAny();
		Integer i = addedElem.get();
		char c=  Character.forDigit(i, 10);
		return c;
		//return addedElem.isPresent() ? Character.forDigit(addedElem.get(), 10) : null;
		
    }
	
	public static void main(String[] args) {
		FindDifference test = new FindDifference();
		
		char c = test.findTheDifference("abcd", "abcde");
		System.out.println(Character.toString(c));
	}

}
