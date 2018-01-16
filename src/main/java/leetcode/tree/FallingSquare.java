package leetcode.tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeMap;

/*
 * LEETCODE 699
 * On an infinite number line (x-axis), we drop given squares in the order they are given.
 * The i-th square dropped (positions[i] = (left, side_length)) is a square with the left-most 
 * point being positions[i][0] and side length positions[i][1]. The square is dropped with the 
 * bottom edge parallel to the number line, and from a higher height than all currently landed 
 * squares. We wait for each square to stick before dropping the next. The squares are infinitely 
 * sticky on their bottom edge, and will remain fixed to any positive length surface they touch 
 * (either the number line or another square). Squares dropped adjacent to each other will not 
 * stick together prematurely.
 * Return a list ans of heights. Each height ans[i] represents the current highest height of any 
 * square we have dropped, after dropping squares represented by positions[0], positions[1], ..., 
 * positions[i].
 * Example 1:
 * Input: [[1, 2], [2, 3], [6, 1]]
 * Output: [2, 5, 5]
 * Explanation: After the first drop of positions[0] = [1, 2]:
 * _aa
 * _aa
 * -------
 * The maximum height of any square is 2.
 * After the second drop of positions[1] = [2, 3]:
 * __aaa
 * __aaa
 * __aaa
 * _aa__
 * _aa__
 * --------------
 * The maximum height of any square is 5. The larger square stays on top of the smaller square 
 * despite where its center of gravity is, because squares are infinitely sticky on their bottom edge.
 * After the third drop of positions[1] = [6, 1]:
 * __aaa
 * __aaa
 * __aaa
 * _aa
 * _aa___a
 * --------------
 * The maximum height of any square is still 5. Thus, we return an answer of [2, 5, 5].
 * Example 2:
 * Input: [[100, 100], [200, 100]]
 * Output: [100, 100]
 * Explanation: Adjacent squares don't get stuck prematurely - only their bottom edge can stick to surfaces.
 * Note:
 * - 1 <= positions.length <= 1000.
 * - 1 <= positions[i][0] <= 10^8.
 * - 1 <= positions[i][1] <= 10^6.
 * 
 * Company: Uber, Square
 * Difficulty: hard
 * Similar Questions: 218(Skyline) 
 */
public class FallingSquare {
    // The squares divide the number line into many segments with different heights. 
    // Therefore we can use a TreeMap to store the number line. The key is the starting point of each segment 
    // and the value is the height of the segment. For every new falling square (s, l), we update those segments 
    // between s and s + l.
    public List<Integer> fallingSquares(int[][] positions) {
        if (positions == null || positions.length == 0) return Collections.emptyList();
        
        List<Integer> result = new ArrayList<>();
        TreeMap<Integer, Integer> map = new TreeMap<>();
        
        // at first, there is only one segment starting from 0 with height 0
        map.put(0, 0);
        
        // The global max height is 0
        int max = 0;
        
        for (int[] position : positions) {
            // the new segment
            int start = position[0], end = position[0]+position[1];
            
            // find the height among this range
            Integer key = map.floorKey(start); // get the greatest key less than start
            int h = map.get(key);
            for (; key != null && key < end; key = map.higherKey(key)) {
                h = Math.max(h, map.get(key));
            }
            h += position[1];
            
            // update global max height
            max = Math.max(max, h);
            result.add(max);
            
            // update new segment and delete previous segments among the range
            int tail = map.floorEntry(end).getValue();
            map.put(start, h);
            map.put(end, tail); // cannot use h, this will be the height added to the next new segment
            for (key = map.higherKey(start); key != null && key < end; key = map.higherKey(key)) {
                map.remove(key);
            }
            System.out.println(map);
        }
        
        return result;
    }
    
    public static void main(String[] args) {
        FallingSquare fs = new FallingSquare();
        int[][] positions = {
                {1, 2},
                {2, 3},
                {6, 1}
        };
        System.out.println(fs.fallingSquares(positions));
    }

}
