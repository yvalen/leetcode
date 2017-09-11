package leetcode.stack;

import java.util.Stack;

/*
 * Given an encoded string, return it's decoded string. The encoding rule is: k[encoded_string], where the encoded_string inside 
 * the square brackets is being repeated exactly k times. Note that k is guaranteed to be a positive integer.
 * You may assume that the input string is always valid; No extra white spaces, square brackets are well-formed, etc. 
 * Furthermore, you may assume that the original data does not contain any digits and that digits are only for those repeat numbers, k. 
 * For example, there won't be input like 3a or 2[4].
 * Examples:
 * s = "3[a]2[bc]", return "aaabcbc".
 * s = "3[a2[c]]", return "accaccacc".
 * s = "2[abc]3[cd]ef", return "abcabccdcdcdef".
 * 
 * Company: Google
 * Difficulty: medium
 */
public class DecodeString {
	public String decodeString(String s) {
        if (s == null || s.isEmpty()) return s;
        
        Stack<Integer> countStack = new Stack<>();
        Stack<StringBuilder> sbStack = new Stack<>();
        int count = 0;
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) {
        	if (Character.isDigit(c)) {
        		// calculate count as count could contain multiple digits
        		count = count * 10 + (c - '0'); 
        	}
        	else if (c == '[') {
        		// end of count, push it to count stack and reset the counter for the next count
        		countStack.push(count);
        		count = 0;
        		// need to push sb here to initialize the stack
        		sbStack.push(sb);
        		sb = new StringBuilder();
        	}
        	else if (c == ']') {
        		// sb now contains the string that needs to be processed
        		int repeat = countStack.pop();
        		String str = sb.toString();
        		sb = sbStack.pop(); // append the result to the top element on the stack
        		while(repeat-- > 0) {
        			sb.append(str);
        		}
        	}
        	else {
        		sb.append(c);
        	}
        }
        return sb.toString();
    }
	
	public String decodeString_dfs(String s) {
        if (s == null || s.isEmpty()) return s;
        //return dfs(new StringBuilder(s), new int[] {0});
        return dfs(s, new int[] {0});
	}
	
	private String dfs(String s, int[] index) {
		int count = 0;
		StringBuilder result = new StringBuilder();
		
		while (index[0] < s.length()) {
			if (Character.isDigit(s.charAt(index[0]))) {
				while (Character.isDigit(s.charAt(index[0]))) {
					count = count * 10 + (s.charAt(index[0]) - '0');
					index[0]++;
				}
				index[0]++; // skip [
				
				// decode the rest 
				String rest = dfs(s, index);

				while(count > 0) {
					result.append(rest);
					count--;
				}
			}
			else if (s.charAt(index[0]) == ']') {
				index[0]++;  // need to increment the global index to process the rest of the string after ]
				return result.toString();
			}
			else {
				result.append(s.charAt(index[0]++));
			}
		}
		
		return result.toString();
	}
	
	public static void main(String[] args) {
		DecodeString ds = new DecodeString();
		//String s = "3[a]2[bc]";
		String s = "2[abc]3[cd]ef";
		System.out.println(ds.decodeString_dfs(s));
	}
}
