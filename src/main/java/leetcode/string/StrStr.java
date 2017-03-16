package leetcode.string;

public class StrStr {
	public int strStr(String haystack, String needle) {
        if (haystack == null || needle == null || needle.length() > haystack.length()) {
        	return -1;
        }
        
        // traverse all the possible starting points of haystack (from 0 to haystack.length() - needle.length()) 
        // and see if the following characters in haystack match those of needle.
        for (int i = 0; ; i++) {
        	for (int j = 0; ; j++) {
        		if (j == needle.length()) return i;
        		if (i + j == haystack.length()) return -1;
        		if (haystack.charAt(i+j) != needle.charAt(j)) break;
        	}    	
        }
    }
	
	// TODO
	// https://discuss.leetcode.com/topic/15569/explained-4ms-easy-c-solution/2
	public int strStr_KMP(String haystack, String needle) {
		return -1;
	}
	
	public static void main(String[] args) {
		StrStr s = new StrStr();
		//String haystack = "mississippi", needle = "issip";
		String haystack = "", needle = "";
		//String haystack = "a", needle = "";
		//String haystack = "mississippi", needle = "a";
		System.out.println(s.strStr(haystack, needle));
	}
}
