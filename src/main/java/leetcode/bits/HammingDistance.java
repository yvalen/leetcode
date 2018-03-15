package leetcode.bits;

/*
 * LEETCODE 461
 * The Hamming distance between two integers is the number of positions 
 * at which the corresponding bits are different.
 * Given two integers x and y, calculate the Hamming distance.
 * Note: 0 ≤ x, y < 2^31.
 * Example: Input: x = 1, y = 4, Output: 2
 * Explanation:
 * 1   (0 0 0 1)
 * 4   (0 1 0 0)
 *        ↑   ↑
 * The above arrows point to positions where the corresponding bits are different.
 * 
 * Company: Facebook
 * Difficulty: easy
 * Similar Questions: 191(NumberOfOneBits), 477(TotalHammingDistance)
 */
public class HammingDistance {
    public int hammingDistance(int x, int y) {
        int z = x ^ y;
        int count = 0;
        while (z != 0) {
            z = z & (z - 1);
            count++;
        }
        return count;
    }

    public static void main(String[] args) {
        HammingDistance hd = new HammingDistance();
        int x = 1, y = 4;
        System.out.println(hd.hammingDistance(x, y));
    }
}
