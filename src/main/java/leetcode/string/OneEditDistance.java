package leetcode.string;

/*
 * Given two strings S and T, determine if they are both one edit distance apart.
 */
public class OneEditDistance {
	public boolean isOneEditDistance(String s, String t) {
        if (s == null) return t == null ? false : t.length() == 1;
        if (t == null) return s.length() == 1;
   
        int sLen = s.length(), tLen = t.length(), len = Integer.min(sLen, tLen);
        if (Math.abs(tLen - sLen) > 1) return false;
        
        for (int i = 0; i < len; i++) {
        	if (s.charAt(i) == t.charAt(i)) continue;
        	
        	if (sLen == tLen) return s.substring(i+1).equals(t.substring(i+1));
        	
        	if (sLen < tLen) return s.substring(i).equals(t.substring(i+1));
        	
        	return s.substring(i+1).equals(t.substring(i));
        }
        
        //All previous chars are the same, the only possibility is deleting the end char in the longer one of s and t 
        return sLen != tLen;
    }
	
	public boolean isOneEditDistance_withCharArray(String s, String t) {
        if (s == null) return t == null ? false : t.length() == 1;
        if (t == null) return s.length() == 1;
        
        if (s.length() > t.length()) {
        	String temp = s;
        	s = t;
        	t = temp;
        }
        
        int sLen = s.length(), tLen = t.length();
        if (tLen - sLen > 1) return false;
        
        boolean firstDiff = false;
        int i = 0, j = 0;
        while (i < sLen && j < tLen) {
        	if (s.charAt(i) == t.charAt(j)) {
        		i++;
        		j++;
        		continue;
        	}
        	
        	if (firstDiff) return false;
        	firstDiff = true;
        	
        	if (sLen == tLen) {
        		i++;
        		j++;
        	}
        	else {
        		j++;
        	}
        }
        
        // if all chars are the same, make sure j is at the last char of t to handle ("a", "")
        return firstDiff || j == tLen-1;
    }

	public static void main(String[] args) {
		OneEditDistance d = new OneEditDistance();
		//String s = "", t = "";
		String s = "a", t = "";
		//String s = "ab", t = "cab";
		System.out.println(d.isOneEditDistance_withCharArray(s, t));
	}
}
