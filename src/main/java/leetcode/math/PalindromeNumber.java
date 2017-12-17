package leetcode.math;

/*
 * LEETCODE 9
 * Determine whether an integer is a palindrome. Do this without extra space.
 * Hints: 
 * - Could negative integers be palindromes? (ie, -1) 
 * - If you are thinking of converting the integer to string, note the restriction of using extra space.
 * - You could also try reversing an integer. However, if you have solved the problem "Reverse Integer", 
 * you know that the reversed integer might overflow. How would you handle such case?
 * 
 * Difficulty: easy
 * Similar Questions: 234(PalindromeLinkedList)
 */
public class PalindromeNumber {
    public boolean isPalindrome_reverseNumber(int x) {
        if (x < 0 || // negative is not palindrome number
                (x % 10 == 0 && x != 0)) { // 10, 100, 1000, ... are not
                                           // palindrome number
            return false;
        }

        int c = x;

        // reverse the number
        long r = 0;
        while (c != 0) {
            r = r * 10 + c % 10;
            c = c / 10;
        }

        if (r > Integer.MAX_VALUE || r < Integer.MIN_VALUE || x != ((int) r)) {
            return false;
        }

        return true;
    }

    public boolean isPalindrome_compareHalf(int x) {
        if (x < 0 || // negative is not palindrome number
                (x % 10 == 0 && x != 0)) { // 10, 100, 1000, ... are not
                                           // palindrome number
            return false;
        }

        int r = 0;
        while (x > r) {
            r = r * 10 + x % 10;
            x = x / 10;
        }

        return (x == r || x == (r / 10)); // r/10 to remove the last digit for
                                          // odd number of digits
    }

}
