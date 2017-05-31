package leetcode.math;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).
 * 0, 1, 8 are symmetrical around the horizontal axis, and 6 and 9 are the same as each other when rotated 180 degrees. 
 * https://en.wikipedia.org/wiki/Strobogrammatic_number
 */
public class StrobogrammaticNumber {
	private static final Map<Character, Character> map = new HashMap<>();
	static {
		map.put('0', '0');
		map.put('1', '1');
		map.put('8', '8');
		map.put('6', '9');
		map.put('9', '6');
	}
	
	/*
	 * Write a function to determine if a number is strobogrammatic. The number is represented as a string.
	 * For example, the numbers "69", "88", and "818" are all strobogrammatic.
	 */
	public boolean isStrobogrammatic(String num) {
		for (int i = 0, j = num.length()-1; i <= j; i++, j--) {
			if (!"00 11 88 696".contains(num.charAt(i) + "" + num.charAt(j))) return false;
			
			//char left = num.charAt(i), right = num.charAt(j);
			//if (!map.containsKey(left) || map.get(left) != right) return false;
		}
		
		return true; 
    }
	
	/*
	 * Find all strobogrammatic numbers that are of length = n. 
	 * For example, Given n = 2, return ["11","69","88","96"]. 
	 */
	public List<String> findStrobogrammatic(int n) {
        return findStrobogrammaticHelper(n, n);
    }
	
	private List<String>  findStrobogrammaticHelper(int n, int size) { 
        if (n <= 0) return Arrays.asList("");  // return a list with an empty string so that the recursion would work
        
        if (n == 1) return Arrays.asList("0", "1", "8");
        
        List<String> result = new ArrayList<>();
        List<String> subList = findStrobogrammaticHelper(n-2, size);
        for (String s : subList) {
        	if (n < size) result.add("0" + s + "0"); // compare n with original size to decide whether to use 0, 0 cannot be used the first time
        	
        	result.add("1" + s + "1");
        	result.add("8" + s + "8");
        	result.add("6" + s + "9");
        	result.add("9" + s + "6");
        }
        return result;
	}
	
	
	/*
	 * Write a function to count the total strobogrammatic numbers that exist in the range of low <= num <= high.
	 * For example, Given low = "50", high = "100", return 3. Because 69, 88, and 96 are three strobogrammatic numbers.
	 * Note: Because the range might be a large number, the low and high numbers are represented as string. 
	 */
	public int strobogrammaticInRange(String low, String high) {
		//if (low == null || high == null || low.compareTo(high) > 0) return 0; // "50".compareTo("100")=4
		int count = 0;
		for (int len = low.length(); len <= high.length(); len++) {
			char[] c = new char[len];
			 count += inRangeHelper(low, high, 0, len-1, c);
		}
		
        return count;
    }
	
	private static final char[][] pairs = {{'0', '0'}, {'1', '1'}, {'8', '8'}, {'6', '9'}, {'9', '6'}};
	private int inRangeHelper(String low, String high, int left, int right, char[] c) {
		if (left > right) {
			String s = String.valueOf(c);
			if (s.length() == low.length() && s.compareTo(low) < 0 ||
					s.length() == high.length() && s.compareTo(high) > 0) {
				return 0;
			}
			return 1; // found one
		}
		
		int count = 0;
		for (char[] pair : pairs) {
			c[left] = pair[0];
			c[right] = pair[1];
			if (c.length != 1 && c[0] == '0') continue;  // 0 can only be used in number with one digit
			if (left == right && pair[0] != pair[1]) continue;  // one digit in the middle cannot be 6 or 9
			count += inRangeHelper(low, high, left+1, right-1, c);
		}
		return count;
	}
	
	public static void main(String[] args) {
		StrobogrammaticNumber sn = new StrobogrammaticNumber();
		//int n = 3;
		//System.out.println(sn.findStrobogrammatic(n));
		
		//String low = "0", high = "0";

		String low = "50", high = "100";
		System.out.println(sn.strobogrammaticInRange(low, high));
	}
}
