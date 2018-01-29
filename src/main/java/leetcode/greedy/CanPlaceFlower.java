package leetcode.greedy;

/*
 * LEETCODE 605
 * Suppose you have a long flowerbed in which some of the plots are planted 
 * and some are not. However, flowers cannot be planted in adjacent plots - 
 * they would compete for water and both would die. 
 * Given a flowerbed (represented as an array containing 0  and 1, where 0 
 * means empty and 1 means not empty), and a number n, return if n new flowers 
 * can be planted in it without violating the no-adjacent-flowers rule.
 * Example 1:
 * Input: flowerbed = [1,0,0,0,1], n = 1
 * Output: True
 * Example 2:
 * Input: flowerbed = [1,0,0,0,1], n = 2
 * Output: False
 * Note:
 * - The input array won't violate no-adjacent-flowers rule.
 * - The input array size is in the range of [1, 20000].
 * - n is a non-negative integer which won't exceed the input array size.
 * 
 * Company: LinkedIn
 * Difficulty: easy
 */
public class CanPlaceFlower {
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        if (flowerbed == null || flowerbed.length == 0)
            return false;
        if (n <= 0)
            return true;

        for (int i = 0; i < flowerbed.length; i++) {
            /*
             * if (flowerbed[i] == 1) continue; if (i > 0 && flowerbed[i-1] ==
             * 1) continue; if (i < flowerbed.length-1 && flowerbed[i+1] == 1)
             * continue; n--; flowerbed[i] = 1; if (n == 0) return true;
             */
            if (flowerbed[i] == 0 &&  // need to use && for both condition that checks for index
                    (i == 0 || flowerbed[i - 1] == 0) && 
                    (i == flowerbed.length - 1 || flowerbed[i + 1] == 0)) {
                n--;
                // need to set the current position to 1 to remember the choice
                flowerbed[i] = 1; 
                if (n == 0)
                    return true;
            }

        }

        return false;
    }

    public static void main(String[] args) {
        CanPlaceFlower cpf = new CanPlaceFlower();
        int[] flowerbed = { 1, 0, 0, 0, 0, 1 };
        int n = 2;
        // int[] flowerbed = {0,0,1,0,1};
        // int n = 1;
        System.out.println(cpf.canPlaceFlowers(flowerbed, n));
    }
}
