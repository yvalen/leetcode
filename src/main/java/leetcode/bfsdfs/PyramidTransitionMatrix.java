package leetcode.bfsdfs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/*
 * LEETCODE 756
 * We are stacking blocks to form a pyramid. Each block has a color which is a one 
 * letter string, like `'Z'`. For every block of color `C` we place not in the bottom 
 * row, we are placing it on top of a left block of color `A` and right block of color 
 * `B`. We are allowed to place the block there only if `(A, B, C)` is an allowed triple.
 * We start with a bottom row of bottom, represented as a single string. We also start 
 * with a list of allowed triples allowed. Each allowed triple is represented as a string 
 * of length 3. Return true if we can build the pyramid all the way to the top, otherwise false.
 * Example 1:
 * Input: bottom = "XYZ", allowed = ["XYD", "YZE", "DEA", "FFF"]
 * Output: true
 * Explanation: We can stack the pyramid like this:
 *     A
 *    / \
 *   D   E
 *  / \ / \
 * X   Y   Z
 * This works because ('X', 'Y', 'D'), ('Y', 'Z', 'E'), and ('D', 'E', 'A') are allowed triples.
 * Example 2:
 * Input: bottom = "XXYX", allowed = ["XXX", "XXY", "XYX", "XYY", "YXZ"]
 * Output: false
 * Explanation: We can't stack the pyramid to the top.
 * Note that there could be allowed triples (A, B, C) and (A, B, D) with C != D.
 * Note:
 * - bottom will be a string with length in range [2, 8].
 * - allowed will have length in range [0, 200].
 * - Letters in all strings will be chosen from the set {'A', 'B', 'C', 'D', 'E', 'F', 'G'}.
 * 
 * Company: Airbnb
 * Difficulty: medium
 */
public class PyramidTransitionMatrix {
    // keep reducing the current row of blocks to the row above until we hit the top block.
    public boolean pyramidTransition(String bottom, List<String> allowed) {
        Map<String, Set<Character>> map = new HashMap<>();
        for (String s : allowed) {
            map.computeIfAbsent(s.substring(0, 2), k-> new HashSet<>()).add(s.charAt(2)); 
        }
        return dfs_bruteforce(bottom, "", map);
    }

    private boolean dfs_bruteforce(String below, String above, Map<String, Set<Character>> map) {
        if (below.length() == 2 && above.length() == 1) return true;
        
        if (above.length() == below.length()-1) {
            return dfs_bruteforce(above, "", map);
        }
        
        int index = above.length();
        String base = below.substring(index, index+2);
        if (map.containsKey(base)) {
            for (char c : map.get(base)) {
                if (dfs_bruteforce(below, above+c, map)) return true;
            }
        }
        return false;
    }
    
    private boolean dfs(String bottom, Map<String, Set<Character>> map) {
        if (bottom.length() == 1) return true;
        
        for (int i = 0; i < bottom.length()-1; i++) {
            if (!map.containsKey(bottom.substring(i, i+1))) {
                return false;
            }
        }
         List<String> nextLevel = new ArrayList<>();
         getNextLevel(bottom, 0, nextLevel, new StringBuilder(), map);
         for (String next : nextLevel) {
             if (dfs(next, map)) return true;
         }
         return false;
    }
    
    private void getNextLevel(String bottom, int start, List<String> nextLevel, StringBuilder sb, Map<String, Set<Character>> map ) {
        if (sb.length() == bottom.length()-1) {
            nextLevel.add(sb.toString());
            return;
        }
        
        for (char c : map.get(bottom.substring(start, start+2))) {
            sb.append(c);
            getNextLevel(bottom, start+1, nextLevel, sb, map);
            sb.deleteCharAt(sb.length()-1);
        }
    }
}
