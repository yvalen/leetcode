package leetcode.hashtable;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/*
 * You are playing the following Bulls and Cows game with your friend: You write down a number and ask your friend to guess what the number is. 
 * Each time your friend makes a guess, you provide a hint that indicates how many digits in said guess match your secret number exactly in both 
 * digit and position (called "bulls") and how many digits match the secret number but locate in the wrong position (called "cows"). Your friend 
 * will use successive guesses and hints to eventually derive the secret number.
 * For example: 
 * Secret number:  "1807"
 * Friend's guess: "7810"
 * Hint: 1 bull and 3 cows. (The bull is 8, the cows are 0, 1 and 7.)
 * Write a function to return a hint according to the secret number and friend's guess, use A to indicate the bulls and B to indicate the cows. 
 * In the above example, your function should return "1A3B". Please note that both secret number and friend's guess may contain duplicate digits, 
 * for example:
 * Secret number:  "1123"
 * Friend's guess: "0111"
 * In this case, the 1st 1 in friend's guess is a bull, the 2nd or 3rd 1 is a cow, and your function should return "1A1B".
 * You may assume that the secret number and your friend's guess only contain digits, and their lengths are always equal.
 */
public class BullsAdnCows {
    public String getHint(String secret, String guess) {
        if (secret == null || secret.length() == 0 || guess == null || guess.length() == 0)
            return "0A0B";

        int bulls = 0, cows = 0;
        int[] count = new int[10]; // record the appearance of the digit, digit
                                   // in secret will increase the count, digit
                                   // in guess will decrease the count
        for (int i = 0; i < Math.min(secret.length(), guess.length()); i++) {
            char s = secret.charAt(i), g = guess.charAt(i);
            if (s == g) {
                bulls++;
            } else {
                // if previous part of guess contains the digit in secret
                if (count[s - '0']++ < 0)
                    cows++;

                // if previous part of secret contains the digit in guess
                if (count[g - '0']-- > 0)
                    cows++;
            }
        }

        return bulls + "A" + cows + "B";
    }

    public static void main(String[] args) {
        BullsAdnCows bc = new BullsAdnCows();
        // String secret = "1807", guess = "7810";
        // String secret = "1123", guess = "0111";
        String secret = "1122", guess = "2211";

        System.out.println(bc.getHint(secret, guess));
    }
}
