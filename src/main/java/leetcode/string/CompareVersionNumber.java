package leetcode.string;

/*
 * Compare two version numbers version1 and version2. If version1 > version2 return 1, if version1 < version2 return -1,
 * otherwise return 0. You may assume that the version strings are non-empty and contain only digits and the . character. 
 * The . character does not represent a decimal point and is used to separate number sequences. For instance, 2.5 is not 
 * "two and a half" or "half way to version three", it is the fifth second-level revision of the second first-level revision.
 * Here is an example of version numbers ordering: 0.1 < 1.1 < 1.2 < 13.37
 */
public class CompareVersionNumber {
	public int compareVersion_withSum(String version1, String version2) {
        if (version1 == null) return (version2 == null) ? 0 : -1;
        if (version2 == null) return (version1 == null) ? 0 : 1;
        
        int len1 = version1.length(), len2 = version2.length();
        int i = 0, j = 0;
        int num1 = 0, num2 = 0;
        while (i < len1 || j < len2) {
        	while (i < len1 && version1.charAt(i) != '.') {
        		num1 = num1 * 10 + (version1.charAt(i) - '0');
        		i++;
        	}
        	
        	while (j < len2 && version2.charAt(j) != '.') {
        		num2 = num2 * 10 + (version2.charAt(j) - '0');
        		j++;
        	}
        	
        	if (num1 > num2) return 1;
        	else if (num1 < num2) return -1;
        	
        	num1 = 0;
        	num2 = 0;
        	i++;
        	j++;
        }
        
        return 0;
    }
	
	public int compareVersion(String version1, String version2) {
        if (version1 == null) return (version2 == null) ? 0 : -1;
        if (version2 == null) return (version1 == null) ? 0 : 1;
        
        int len1 = version1.length(), len2 = version2.length();
        int start1 = 0, end1 = 0, start2 = 0, end2 = 0;
        while (start1 < len1 || start2 < len2) {  
        	while (end1 < len1 && version1.charAt(end1) != '.') end1++;
        	while (end2 < len2 && version2.charAt(end2) != '.') end2++;
        	int v1 = (start1 >= len1) ? 0 : Integer.valueOf(version1.substring(start1, end1)); // fill 0 if reaches end of string
        	int v2 = (start2 >= len2) ? 0 : Integer.valueOf(version2.substring(start2, end2)); 
        	if (v1 > v2) return 1;
        	else if (v1 < v2) return -1;
        	start1 = end1 + 1;
        	start2 = end2 + 1;
        	end1++; // need to increment end position as well 
        	end2++;
        }
        
        return 0;
    }
	
	
	public int compareVersion_withSplit(String version1, String version2) {
        if (version1 == null) return (version2 == null) ? 0 : -1;
        if (version2 == null) return (version1 == null) ? 0 : 1;
        
        String[] ary1 = version1.split("\\."); // need to escape . since . in regex means any character
        String[] ary2 = version2.split("\\.");
        int len = Integer.max(ary1.length, ary2.length);
        for (int i = 0; i < len; i++) {
        	int v1 = i < ary1.length ? Integer.parseInt(ary1[i]) : 0;
        	int v2 = i < ary2.length ? Integer.parseInt(ary2[i]) : 0;
        	if (v1 == v2) continue;
        	return (v1 > v2) ? 1 : -1;
        }
        
        return 0;	
	}	

	public static void main(String[] args) {
		CompareVersionNumber cvn = new CompareVersionNumber();
		//String version1 = "1", version2 = "01";
		String version1 = "1.0", version2 = "1.1";
		//String version1 = "1", version2 = "1.1";
		System.out.println(cvn.compareVersion(version1, version2));
	}
}
