package leetcode.stack;

import java.util.Stack;

import com.sun.org.apache.xml.internal.resolver.helpers.PublicId;

import leetcode.array.ArrayUtil;

/*
 * LEETCODE 735
 * We are given an array asteroids of integers representing asteroids in a row. For each asteroid, 
 * the absolute value represents its size, and the sign represents its direction (positive meaning 
 * right, negative meaning left). Each asteroid moves at the same speed.
 * Find out the state of the asteroids after all collisions. If two asteroids meet, the smaller one 
 * will explode. If both are the same size, both will explode. Two asteroids moving in the same 
 * direction will never meet.
 * Example 1:
 * Input: asteroids = [5, 10, -5]
 * Output: [5, 10]
 * Explanation: The 10 and -5 collide resulting in 10.  The 5 and 10 never collide.
 * Example 2:
 * Input: asteroids = [8, -8]
 * Output: []
 * Explanation: The 8 and -8 collide exploding each other.
 * Example 3:
 * Input: asteroids = [10, 2, -5]
 * Output: [10]
 * Explanation: The 2 and -5 collide resulting in -5.  The 10 and -5 collide resulting in 10.
 * Example 4:
 * Input: asteroids = [-2, -1, 1, 2]
 * Output: [-2, -1, 1, 2]
 * Explanation: The -2 and -1 are moving left, while the 1 and 2 are moving right.
 * Asteroids moving the same direction never meet, so no asteroids will meet each other.
 * Note:
 * - The length of asteroids will be at most 10000.
 * - Each asteroid will be a non-zero integer in the range [-1000, 1000].
 * 
 * Company: Uber
 * Difficulty: medium
 * Similar Questions: 605(CanPlaceFlower)
 */
public class AsteroidCollision {
    /*
     * 1. Negative asteroids without any positive asteroids on the left can be ignored as they will 
     * never interact with the upcoming asteroids regardless of their direction.
     * 2. Positive asteroids (right-moving) may interact with negative asteroids (left-moving) that come later.
     * We can use a stack to simulate the collisions. We can iterate through the list of asteroids and 
     * handle the following scenarios:
     * 1. If stack is empty, we push that asteroid into it regardless of directions. Because negative asteroids 
     * will be part of the final result while positive asteroids may interact with future negative asteroids.
     * 2. If the asteroid is positive, push it into stack. It will never interact with existing asteroids in stack 
     * but may interact with future negative asteroids.
     * 3. If the asteroid is negative, we need to simulating the collision process by repeatedly popping the positive 
     * asteroids from the top of the stack and compare to see which asteroid survives the collision. We may or may not 
     * need to push the negative asteroid to res depending on the value of the positive asteroids it encounters. 
     * Push the negative asteroid if it survives all the collisions.
     */
    public int[] asteroidCollision(int[] asteroids) {
        if (asteroids == null || asteroids.length == 0) return asteroids;
        
        Stack<Integer> stack = new Stack<>();
        for (int asteroid : asteroids) {
            if (asteroid > 0) {
                stack.push(asteroid);
            }
            else {
                while (!stack.isEmpty() && stack.peek() > 0 && stack.peek() < -asteroid) {
                    stack.pop();
                }
                if (!stack.empty() && stack.peek() == -asteroid) stack.pop();
                else if (stack.isEmpty() || stack.peek() < 0) stack.push(asteroid);
            }
            
            /*
            if (stack.isEmpty() || asteroid > 0) {
                stack.push(asteroid);
            }
            else {
                if (stack.peek() < 0) {
                    stack.push(asteroid);
                }
                else {
                    int p = stack.pop();
                    int q = Math.abs(asteroid);
                    while (p < q &&!stack.isEmpty() && stack.peek() > 0) {
                        p = stack.pop();
                    }
                    if (p > q) stack.push(p);
                    else if (p == q) continue;
                    else stack.push(asteroid);
                }
            }*/
        }
        
        int[] result = new int[stack.size()];
        int index = stack.size() - 1;
        while (index >= 0) {
            result[index--] = stack.pop();
        }
        
        return result;
    }
    
   public static void main(String[] args) {
       AsteroidCollision ac = new AsteroidCollision();
       //int[] asteroids = {5, 10, -5};
       //int[] asteroids = {8, -8};
       //int[] asteroids = {10, 2, -5};
       //int[] asteroids = {-2, -1, 1, 2};
       int[] asteroids = {-2, -1, 1, -2};
       ArrayUtil.printArray(ac.asteroidCollision(asteroids), ",");
    }
}
