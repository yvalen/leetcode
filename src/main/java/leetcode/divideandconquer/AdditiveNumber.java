package leetcode.divideandconquer;

/*
 * Additive number is a string whose digits can form additive sequence. A valid additive sequence should contain at least three numbers. 
 * Except for the first two numbers, each subsequent number in the sequence must be the sum of the preceding two. For example:
 * "112358" is an additive number because the digits can form an additive sequence: 1, 1, 2, 3, 5, 8.
 * 1 + 1 = 2, 1 + 2 = 3, 2 + 3 = 5, 3 + 5 = 8
 * "199100199" is also an additive number, the additive sequence is: 1, 99, 100, 199.
 * 1 + 99 = 100, 99 + 100 = 199
 * Note: Numbers in the additive sequence cannot have leading zeros, so sequence 1, 2, 03 or 1, 02, 3 is invalid.
 * Given a string containing only digits '0'-'9', write a function to determine if it's an additive number.
 * Follow up: How would you handle overflow for very large input integers?  -> use BigInteger
 * 
 * Company: Epic Systems
 * Difficulty: medium
 */
public class AdditiveNumber {
	// Generate the first and second of the sequence, check if the rest of the string match the sum recursively. 
	// i and j are length of the first and second number. i should in the range of [0, n/2]. The length of their sum should >= max(i,j)
	public boolean isAdditiveNumber(String num) {
		if (num == null || num.length() < 3) return false;
		
		int n = num.length();
		
		// try all possible first number [0...i) where i < n/2. 
		for (int i = 1; i <= n/2; i++) {
			// try all possible second number [i...i+j) where n-i-j >= max(i, j)
			for (int j = 1; Math.max(i,  j) <= n-i-j; j++) {
				if (isValid(i, j, num)) return true;
			}
		}
	
		return false;
    }

	private boolean isValid(int i, int j, String num) {
		// check for leading zero in the first and second number
		// if the number has more than one digit, the first digit cannot be 0
		if (num.charAt(0) == '0' && i > 1) return false;
		if (num.charAt(i) == '0' && j > 1) return false;
		
		long val1 = Long.parseLong(num.substring(0, i));
		long val2 = Long.parseLong(num.substring(i, i+j));
		String sum;
		for (int k = i+j; k < num.length(); k += sum.length()) {
			val2 += val1;  // compute the sum and save it in val2 for the next iteration to use
			val1 = val2 - val1;  // update val1 with the old value of val2 for the next iteration to use
			sum = Long.toString(val2); // this is the sum we need to check next
			if (!num.startsWith(sum, k)) {  // sum is not at k
				return false;  
			}
		}
		
		return true;
	}
	
	public static void main(String[] args) {
		AdditiveNumber an = new AdditiveNumber ();
		//String num = "112358";
		String num = "000";
		System.out.println(an.isAdditiveNumber(num));
	}
}
