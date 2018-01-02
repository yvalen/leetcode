package leetcode.string;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import leetcode.hashtable.LongestNonReaptingSubstring;

public class LongestSubstringTest {
	private static final String TEST_STRING_1 = "abcabcbb";
	private static final String TEST_STRING_2 = "bbbbb";
	private static final String TEST_STRING_3 = "pwwkew";
	private static final String TEST_STRING_4 = "c";
	private static final String TEST_STRING_5 = "aab";
	private static final String TEST_STRING_6 = "abba";
	private static final String TEST_STRING_7 = "abcabcbb";
	private static final int TEST_LENGTH_1 = 3;
	private static final int TEST_LENGTH_2 = 1;
	private static final int TEST_LENGTH_3 = 3;
	private static final int TEST_LENGTH_4 = 1;
	private static final int TEST_LENGTH_5 = 2;
	private static final int TEST_LENGTH_6 = 2;
	private static final int TEST_LENGTH_7 = 3;
	
	private LongestNonReaptingSubstring longestSubstring = new LongestNonReaptingSubstring();	
	
	@Ignore
	@Test
	public void usingSet() {
		int len1 = longestSubstring.lengthOfLongestSubstring_withSet(TEST_STRING_1);
		assertEquals(TEST_LENGTH_1, len1);
		
		int len2 = longestSubstring.lengthOfLongestSubstring_withSet(TEST_STRING_2);
		assertEquals(TEST_LENGTH_2, len2);
		
		int len3 = longestSubstring.lengthOfLongestSubstring_withSet(TEST_STRING_3);
		assertEquals(TEST_LENGTH_3, len3);
		
		int len4 = longestSubstring.lengthOfLongestSubstring_withSet(TEST_STRING_4);
		assertEquals(TEST_LENGTH_4, len4);
		
		int len5 = longestSubstring.lengthOfLongestSubstring_withSet(TEST_STRING_5);
		assertEquals(TEST_LENGTH_5, len5);
	}
	
	@Test
	public void usingMap() {
		//int len6 = longestSubstring.lengthOfLongestSubstring_withMap(TEST_STRING_6);
		//assertEquals(TEST_LENGTH_6, len6);
		
		int len7 = longestSubstring.lengthOfLongestSubstring_withMap(TEST_STRING_7);
		assertEquals(TEST_LENGTH_7, len7);
	}

}
