package leetcode.sort;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/*
 * Given a list of non negative integers, arrange them such that they form the largest number.
 * For example, given [3, 30, 34, 5, 9], the largest formed number is 9534330.
 * Note: The result may be very large, so you need to return a string instead of an integer.
 */
public class LargestNumber {
	public String largestNumber(int[] nums) {
		if (nums == null || nums.length == 0) return null;
		
		StringBuilder sb = new StringBuilder();
		/*
		List<String> numList = IntStream.of(nums).boxed().map(i -> i.toString()).collect(Collectors.toList());
		Collections.sort(numList, new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				// Comparator to decide which string should come first in concatenation
				String s1 = o1 + o2;
				String s2 = o2 + o1;
				return s2.compareTo(s1); // Collections.sort is in ascending order
			}
			
		});
		
		// return 0 when all elements are 0
		if (numList.get(0).equals("0")) return "0";
		
		for (String num : numList) {
			sb.append(num);
		}
		
		*/
		
		String[] strs = new String[nums.length];
		for (int i = 0; i < nums.length; i++) {
			//strs[i] = String.valueOf(nums[i]);
			strs[i] = String.valueOf(nums[i]);
		}
		
		Arrays.sort(strs,  (s1, s2) -> (s2+s1).compareTo(s1+s2));
		/*
		Arrays.sort(strs, new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				// Comparator to decide which string should come first in concatenation
				return (o2+o1).compareTo(o1+o2); // Collections.sort is in ascending order
			}
		});
		*/
		
		// return 0 when all elements are 0
		if (strs[0].charAt(0) == '0') return "0";
		
		for (String str : strs) {
			sb.append(str);
		}
		
		return sb.toString();
    }

	public static void main(String[] args) {
		LargestNumber ln = new LargestNumber();
		//int[] nums = {3, 30, 34, 5, 9};
		//int[] nums = {121, 12};
		int[] nums = {0, 0};
		System.out.println(ln.largestNumber(nums));
	}
}
