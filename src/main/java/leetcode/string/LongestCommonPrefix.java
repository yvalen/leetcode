package leetcode.string;

/*
 * LEETCODE 14
 * Write a function to find the longest common prefix string amongst an array of strings. 
 * 
 * Company: Yelp
 * Difficulty: easy
 * https://leetcode.com/articles/longest-common-prefix/
 */
public class LongestCommonPrefix {
	// iterate through the input strings, at each iteration i find the longest common
	// prefix LCP(S1...Si). When LCP(S1...Si) is empty ends the iteration. 
	// Time Complexity: O(n) where n is the sum of the characters in all string. Worst case all strings are same
	// Space complexity: O(1)
	public String longestCommonPrefix_horizontalScanning(String[] strs) {
		if (strs == null || strs.length == 0) return "";
		String prefix = strs[0];		
		for (int i = 1; i < strs.length; i++) {
			while(strs[i].indexOf(prefix) != 0) {
				prefix = prefix.substring(0, prefix.length() - 1);
			}
			if (prefix.isEmpty()) return "";
		}	
		return prefix;
    }
	
	
	// Compare characters from top to bottom on the same column (same character index of the strings) 
	// before moving on to the next column.
	// Time complexity : O(S), where S is the sum of all characters in all strings. In the worst case there will be 
	// n equal strings with length m and the algorithm performs S=m∗n character comparisons. Even though the worst case 
	// is still the same as Approach #1, in the best case there are at most n∗minLen comparisons where minLen is the length 
	// of the shortest string in the array.
    // Space complexity : O(1). We only used constant extra space.	
	public String longestCommonPrefix_verticalScanning(String[] strs) {
		if (strs == null || strs.length == 0) return "";
				
		for (int i = 0; i < strs[0].length(); i++) {
			char c = strs[0].charAt(i);
			for (int j = 1; j < strs.length; j++) {
				if (strs[j].length() == i || strs[j].charAt(i) != c) {
					return strs[0].substring(0, i);
				}
			}
		}	
		return strs[0];
		
    }
	
	public static void main(String[] args) {
		LongestCommonPrefix p = new LongestCommonPrefix();
		
		String[] ary = {"a"};
		String lcp = p.longestCommonPrefix_verticalScanning(ary);
		System.out.println(lcp);
	}

}
