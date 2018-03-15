package leetcode.array;

/*
 * LEETCODE 717
 * We have two special characters. The first character can be represented by one bit 0. 
 * The second character can be represented by two bits (10 or 11). Now given a string 
 * represented by several bits. Return whether the last character must be a one-bit 
 * character or not. The given string will always end with a zero.
 * Example 1: 
 * Input: bits = [1, 0, 0]
 * Output: True
 * Explanation: The only way to decode it is two-bit character and one-bit character. 
 * So the last character is one-bit character.
 * Example 2:
 * Input: bits = [1, 1, 1, 0]
 * Output: False
 * Explanation: The only way to decode it is two-bit character and two-bit character. 
 * So the last character is NOT one-bit character.
 * Note:
 * - 1 <= len(bits) <= 1000.
 * - bits[i] is always 0 or 1.
 * 
 * Company: Quora, Google
 * Difficulty: easy
 * Similar Questions: 89(GrayCode)
 */
public class OneBitAndTwoBitCharacters {
    // put a iterative pointer in the beginning, and whenever you see next number equals to 0, 
    // you HAVE step one step over it, because you can’t step two steps due to the fact that 
    // all two steps begins with ‘1’, as you see(‘10’, or ‘11’). 
    public boolean isOneBitCharacter(int[] bits) { 
        int i = 0;
        while (i < bits.length-1) {
            if (bits[i] == 0) i++;
            else i += 2;
        }
        return i == bits.length - 1;
    }
    
    public static boolean isOneBitCharacter_greedy(int[] bits) { 
        int i = bits.length - 2;
        while (i >= 0 && bits[i] > 0) i--;
        System.out.println("i=" + i + " len=" + bits.length);
        return (bits.length-i) % 2 == 0;
    }
    
    public static void main(String[] args) {
        int[] bits = {1, 1, 1, 1, 1, 0};
        System.out.println(isOneBitCharacter_greedy(bits));
    }
}
