package leetcode.math;

import java.util.HashMap;
import java.util.Map;

/*
 * LEETCODE 149
 * Given n points on a 2D plane, find the maximum number of points that 
 * lie on the same straight line.
 * 
 * A line can be represented by y = ax + b
 * For points (x1, y1) and (x2, y2) if they are on the same line we will have
 * y1 = kx1 + b
 * y2 = kx2 + b
 * We will have the slope k = (y2-y1) / (x2-x1) 
 * For each point p, we calculate the slope for all other points, those with 
 * the same slope will be on the same line as p 
 * 
 * Company: LinkedIn, Twitter, Apple
 * Difficulty: hard
 * Similar Questions: 356
 */
public class MaxPointOnLine {
    // Time complexity: O(n^2) Space : O(n)
    public int maxPoints(Point[] points) {
        if (points == null)
            return 0;
        if (points.length <= 2)
            return points.length;

        int result = 0, n = points.length;
        // stores the count of points with slope y/x, x is the key of
        // the outer map and y is the key of the inner map
        //Map<Integer, Map<Integer, Integer>> map = new HashMap<>();
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            // reset the map for the next point
            map.clear();
            int samep = 0, max = 0;
            for (int j = i + 1; j < n; j++) {
                int x = points[j].x - points[i].x;
                int y = points[j].y - points[i].y;

                if (x == 0 && y == 0) {
                    // same point
                    samep++;
                    continue;
                }

                // obtain the smallest integer pair
                int gcd = generateGCD(y, x);
                if (gcd != 0) {
                    x /= gcd;
                    y /= gcd;
                }
                
                /*
                map.computeIfAbsent(x, v -> new HashMap<>()).put(
                        y, map.get(x).getOrDefault(y, 0) + 1);
                max = Math.max(max, map.get(x).get(y));
                */
                String key = String.valueOf(x) + ":" + String.valueOf(y);
                map.put(key, map.getOrDefault(key, 0)+1);
                max = Math.max(max, map.get(key));
            }
            result = Math.max(result, max + samep + 1);
        }

        return result;
    }

    // greatest common divisor calculation
    // gcd(a, 0) = a
    // gcd(a, b) = gcd(b, a % b)
    private int generateGCD(int a, int b) {
        while (b != 0) {
            int tmp = b;
            b = a % b;
            a = tmp;
        }
        return a;
    }
    
    public static void main(String[] args) {
        MaxPointOnLine mpl = new MaxPointOnLine();
        System.out.println(mpl.generateGCD(8, 12));
    }

}
