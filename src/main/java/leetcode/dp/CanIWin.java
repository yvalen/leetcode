package leetcode.dp;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*
 * LEETCODE 464
 * In the "100 game," two players take turns adding, to a running total, 
 * any integer from 1..10. The player who first causes the running total 
 * to reach or exceed 100 wins. 
 * What if we change the game so that players cannot re-use integers? 
 * For example, two players might take turns drawing from a common pool of 
 * numbers of 1..15 without replacement until they reach a total >= 100. 
 * Given an integer maxChoosableInteger and another integer desiredTotal, 
 * determine if the first player to move can force a win, assuming both players 
 * play optimally. You can always assume that maxChoosableInteger will not be 
 * larger than 20 and desiredTotal will not be larger than 300.
 * Example
 * Input: maxChoosableInteger = 10 desiredTotal = 11
 * Output: false
 * Explanation: No matter which integer the first player choose, the first player 
 * will lose. The first player can choose an integer from 1 up to 10. If the first 
 * player choose 1, the second player can only choose integers from 2 up to 10. 
 * The second player will win by choosing 10 and get a total of 11, which is 
 * >= desiredTotal. Same with other integers chosen by the first player, the second 
 * player will always win.
 * 
 * Company: LinkedIn
 * Difficulty: medium
 */
public class CanIWin {
    // Time complexity: O(2^n)
    // there are 2^n sub-problems, with memorization we compute each sub-problem
    // once without memorization O(n!) - T(n) = n * T(n-1)
    public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
        // first player can choose a number which is greater than or equal to
        // desired number he can win
        if (desiredTotal <= maxChoosableInteger)
            return true;

        // the sum of the numbers in the pool is less than desired number, no
        // one can win
        // sum of 1...n:
        // https://en.wikipedia.org/wiki/1_%2B_2_%2B_3_%2B_4_%2B_%E2%8B%AF
        if (maxChoosableInteger * (maxChoosableInteger + 1) / 2 < desiredTotal)
            return false;

        // used stores which numbers have been used
        boolean[] used = new boolean[maxChoosableInteger];

        // map stores the outcome of the sub-problem
        // key is the string representation of the used array, 
        // numbers have been chosen value indicates whether 
        // we can reach the remaining desired total
        Map<String, Boolean> map = new HashMap<>();

        return helper(desiredTotal, used, map);
    }

    private boolean helper(int desiredTotal, boolean[] used, Map<String, Boolean> map) {
        System.out.println("desiredTotal=" + desiredTotal);
        // base case, the other play has reached the desired total, hence wins
        if (desiredTotal <= 0) {
            return false;
        }

        String key = Arrays.toString(used);
        System.out.println("key=" + key);
        System.out.println("map=" + map);
        if (map.containsKey(key))
            return map.get(key);

        for (int i = 0; i < used.length; i++) {
            if (used[i])
                continue; // skip used number

            used[i] = true; 
            // Minimax, whether the other player will lose
            if (!helper(desiredTotal - i - 1, used, map)) { 
                map.put(key, true);
                used[i] = false;
                return true;
            }
            used[i] = false; // backtrack
        }
        map.put(key, false);
        return false;
    }

    public boolean canIWin_withBits(int maxChoosableInteger, int desiredTotal) {
        // first player can choose a number which is greater than or equal to
        // desired number he can win
        if (desiredTotal <= maxChoosableInteger)
            return true;

        // the sum of the numbers in the pool is less than desired number, no
        // one can win
        // sum of 1...n:
        // https://en.wikipedia.org/wiki/1_%2B_2_%2B_3_%2B_4_%2B_%E2%8B%AF
        if (maxChoosableInteger * (maxChoosableInteger + 1) / 2 < desiredTotal)
            return false;

        // use an integer to represent which numbers have been used
        // i-th bit of state represent whether the i-th number is used, 0 -
        // unused, 1 - used
        int state = 0;

        // memo stores the result of state i
        // 0 - not checked, 1 - win, -1 - lose
        int[] memo = new int[1 << maxChoosableInteger]; // should use
                                                        // 1<<maxChoosableInteger
                                                        // instead of
                                                        // 1+maxChoosableInteger

        return helper_withBits(maxChoosableInteger, desiredTotal, state, memo);
    }

    private boolean helper_withBits(int max, int desiredTotal, int state, int[] memo) {
        if (memo[state] != 0)
            return memo[state] == 1;

        if (desiredTotal <= 0) {
            memo[state] = -1;
            return false;
        }

        for (int i = 0; i < max; i++) {
            // check if the state has been used
            if (((state >> i) & 1) == 1)
                continue;

            // need to pass the modified state, cannot modify state here
            // because we need it to change memo
            if (!helper_withBits(max, desiredTotal - i - 1, (state |= (1 << i)), memo)) { 
                memo[state] = 1;
                return true;
            }
        }
        memo[state] = -1;
        return false;
    }

    public static void main(String[] args) {
        CanIWin ciw = new CanIWin();
        int maxChoosableInteger = 6, desiredTotal = 16;
        //System.out.println(ciw.canIWin(maxChoosableInteger, desiredTotal));
        System.out.println(1 << maxChoosableInteger);
    }
}
