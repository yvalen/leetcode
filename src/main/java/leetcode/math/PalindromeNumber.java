package leetcode.math;

public class PalindromeNumber {
	public boolean isPalindrome_reverseNumber(int x) {
		if (x < 0 || // negative is not palindrome number
				(x % 10 == 0 && x !=0)) { // 10, 100, 1000, ... are not palindrome number
			return false;
		}
		
		int c = x;
		
        // reverse the number
		long r = 0;
		while (c != 0) {
			r = r * 10 + c % 10;
			c = c / 10;
		}
		
		if (r > Integer.MAX_VALUE || r < Integer.MIN_VALUE ||
				x != ((int) r)) {
			return false;
		}
		
		return true;
    }
	

	public boolean isPalindrome_compareHalf(int x) {
		if (x < 0 || // negative is not palindrome number
				(x % 10 == 0 && x !=0)) { // 10, 100, 1000, ... are not palindrome number
			return false;
		}
		
		int r = 0;
		while (x > r) {
			r = r * 10 + x % 10;
			x = x / 10;
		}
		
		return (x == r || x == (r/10));
    }
	
}
