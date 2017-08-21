package leetcode.dp;

/*
 * In the "100 game," two players take turns adding, to a running total, any integer from 1..10. The player who first causes the running total 
 * to reach or exceed 100 wins. What if we change the game so that players cannot re-use integers? For example, two players might take turns 
 * drawing from a common pool of numbers of 1..15 without replacement until they reach a total >= 100. Given an integer maxChoosableInteger and 
 * another integer desiredTotal, determine if the first player to move can force a win, assuming both players play optimally.
 * You can always assume that maxChoosableInteger will not be larger than 20 and desiredTotal will not be larger than 300.
 * Example
 * Input: maxChoosableInteger = 10 desiredTotal = 11
 * Output: false
 * Explanation: No matter which integer the first player choose, the first player will lose. The first player can choose an integer from 1 up to 10.
 * If the first player choose 1, the second player can only choose integers from 2 up to 10. The second player will win by choosing 10 and get a total 
 * of 11, which is >= desiredTotal. Same with other integers chosen by the first player, the second player will always win.
 * 
 * Company: LinkedIn
 */
public class CanIWin {
	public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
		// first player can choose a number which is greater than or equal to desired number he can win 
		if (desiredTotal <= maxChoosableInteger) return true;
		
		// the sum of the numbers in the pool is less than desired number, no one can win
		// sum of 1...n: https://en.wikipedia.org/wiki/1_%2B_2_%2B_3_%2B_4_%2B_%E2%8B%AF
		if (maxChoosableInteger*(maxChoosableInteger+1) / 2 < desiredTotal) return false;
		
		
		
        return (desiredTotal % (maxChoosableInteger+1) != 0);
    }
}
