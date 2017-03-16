package leetcode.string;

/*
 * Given two binary strings, return their sum (also a binary string).
 * For example,
 * 	a = "11"
 * 	b = "1"
 * Return "100". 
 */
public class AddBinary {
	public String addBinary(String a, String b) {
		if (a == null || a.isEmpty()) return b;
		if (b == null || b.isEmpty()) return a;
		
		if (a.length() > b.length()) {
			String temp = a;
			a = b;
			b = temp;
		}
		int offset = b.length() - a.length();
		boolean carry = false;
		StringBuilder sb = new StringBuilder();
		for (int i = b.length() - 1; i >= 0; i--) {
			if (i - offset < 0) {
				if (carry) {
					int[] result = add(b.charAt(i), '1');
					carry = result[1] == 1;
					sb.append(result[0]);
				}
				else {
					sb.append(b.charAt(i));
				}
			}
			else {
				int[] result = add(b.charAt(i), a.charAt(i-offset));
				int temp = result[1];
				if (carry) {
					result[0] = result[0] == 1 ? 0 : 1;
					carry = result[0] == 0;
				}
				
			}
		}
		
		return b;
    }
	
	private int[] add(char c1, char c2) {
		int[] result = new int[2];
		
		if (c1 == '1' && c2 == '1') {
			result[0] = 0;
			result[1] = 1;
		}
		else if (c1 == '1' || c2 == '1') {
			result[0] = 1;
		}
		return result;
	}
}
