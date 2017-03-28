package leetcode.math;

/*
 * Given two binary strings, return their sum (also a binary string).
 * For example,
 * 	a = "11"
 * 	b = "1"
 * Return "100". 
 */
public class AddBinary   {
	public String addBinary(String a, String b) {
		if (a == null || a.isEmpty()) return b;
		if (b == null || b.isEmpty()) return a;
		
		if (a.length() > b.length()) {
			String temp = a;
			a = b;
			b = temp;
		}
		
		StringBuilder sb = new StringBuilder();
		int carry = 0;
		for (int i = a.length()-1, j = b.length()-1; j >= 0; i--, j--) {
			char c1 = i >= 0 ? a.charAt(i) : '0'; 
			char c2 = b.charAt(j);
			int val = carry + (c1 - '0') + (c2 - '0');
			if (val >= 2) {
				carry = 1;
				val -= 2;
			}
			else {
				carry = 0; // need to reset carry to 0 here
			}
			sb.append(val);
		}
		
		if (carry == 1) sb.append(1);
		
		return sb.reverse().toString();
    }
	
	public String addBinary_wtiDivideAndMod(String a, String b) {
		StringBuilder sb = new StringBuilder();
		int carry = 0, i = a.length() - 1, j = b.length() -1;
		while (i >= 0 || j >= 0) {
			int sum = carry;
			if (i >= 0) sum += a.charAt(i--) - '0';
			if (j >= 0) sum += b.charAt(j--) - '0';
			carry = sum / 2;
			sb.append(sum % 2);
		}
		
		if (carry != 0) sb.append(1);
		
		return sb.reverse().toString();
	}
	
	public static void main(String[] args) {
		AddBinary add = new AddBinary();
		//String a ="11", b = "1";
		String a ="1010", b = "1011";
		System.out.println(add.addBinary(a, b));
	}
}
