package leetcode.dp;

/* LEETCODE 174
 * The demons had captured the princess (P) and imprisoned her in the
 * bottom-right corner of a dungeon. The dungeon consists of M x N rooms laid
 * out in a 2D grid. Our valiant knight (K) was initially positioned in the
 * top-left room and must fight his way through the dungeon to rescue the
 * princess. The knight has an initial health point represented by a positive
 * integer. If at any point his health point drops to 0 or below, he dies
 * immediately. Some of the rooms are guarded by demons, so the knight loses
 * health (negative integers) upon entering these rooms; other rooms are either
 * empty (0's) or contain magic orbs that increase the knight's health (positive
 * integers). In order to reach the princess as quickly as possible, the knight
 * decides to move only rightward or downward in each step. Write a function to
 * determine the knight's minimum initial health so that he is able to rescue
 * the princess.
 * 
 * Company: Microsoft
 * Difficulty: hard
 */
public class DungeonGame {
    public int calculateMinimumHP(int[][] dungeon) {
        if (dungeon.length == 0 || dungeon[0].length == 0)
            return 0;

        int m = dungeon.length, n = dungeon[0].length;
        int[][] health = new int[m][n];

        health[m - 1][n - 1] = Math.max(1, 1 - dungeon[m - 1][n - 1]);

        // last row of health
        for (int i = n - 2; i >= 0; i--) {
            health[m - 1][i] = Math.max(1, health[m - 1][i + 1] - dungeon[m - 1][i]);
        }

        // last column of health
        for (int j = m - 2; j >= 0; j--) {
            health[j][n - 1] = Math.max(1, health[j + 1][n - 1] - dungeon[j][n - 1]);
        }

        for (int i = m - 2; i >= 0; i--) {
            for (int j = n - 2; j >= 0; j--) {
                int right = Math.max(1, health[i][j + 1] - dungeon[i][j]);
                int down = Math.max(1, health[i + 1][j] - dungeon[i][j]);
                health[i][j] = Math.min(down, right);
            }
        }

        return health[0][0];
    }
}
