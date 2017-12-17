package leetcode.dp;

/*
 * LEETCODE 276
 * There is a fence with n posts, each post can be painted with one of the k colors. You have to paint all the posts such that 
 * no more than two adjacent fence posts have the same color (or no 3 adjacent posts have the same color).
 * Return the total number of ways you can paint the fence.
 * Note: n and k are non-negative integers. 
 * For example, for 4 post 2 color case (0 for black, 1 for red)
 * 0011 is a valid solution,
 * 0001 is not
 * 
 * Company: Google
 * Difficult: easy
 * Similar Questions: 198(House Robber), 213(House Robber II), 256(Paint House II), 265(Paint House II)
 */
public class PaintFence {
    /*
     * If n == 1, there would be k-ways to paint. If n == 2, there would be two
     * situations: - 2.1 You paint same color with the previous post: k*1 ways
     * to paint, named it as same - 2.2 You paint differently with the previous
     * post: k*(k-1) ways to paint this way, named it as diff If n >= 3, you can
     * always maintain these two situations, either paint the same color with
     * the PREVIOUS one, or differently. Since there is a rule:
     * "no more than two adjacent fence posts have the same color.", we can
     * further analyze: - from 2.1, since previous two are in the same color,
     * next one you could only paint differently, and it would form one part of
     * "paint differently" case in the n == 3 level, and the number of ways to
     * paint this way would equal to same*(k-1). - from 2.2, since previous two
     * are not the same, you can either paint the same color this time (diff*1)
     * ways to do so, or stick to paint differently (diff*(k-1)) times. Here you
     * can conclude, when seeing back from the next level, ways to paint the
     * same, or variable same would equal to diff*1 = diff, and ways to paint
     * differently, variable diff, would equal to same*(k-1)+diff*(k-1) = (same
     * + diff)*(k-1)
     */
    public int numWays(int n, int k) {
        if (n == 0)
            return 0;
        if (n == 1)
            return k;

        int same = k, diff = k * (k - 1); // for n == 2
        for (int i = 3; i <= n; i++) {
            int temp = diff;
            diff = same * (k - 1) + diff * (k - 1);
            same = temp; // post at i must be different from post at i-2, hence
                         // use diff value of i-1 for painting i in the same
                         // color as i-1
        }

        return same + diff;
    }
}
