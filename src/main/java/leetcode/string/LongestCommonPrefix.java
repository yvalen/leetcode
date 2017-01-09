package leetcode.string;

//https://leetcode.com/articles/longest-common-prefix/
public class LongestCommonPrefix {
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
