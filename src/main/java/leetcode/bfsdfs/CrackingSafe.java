package leetcode.bfsdfs;

import java.util.HashSet;
import java.util.Set;

import com.sun.org.apache.xml.internal.security.utils.UnsyncBufferedOutputStream;

/*
 * LEETCODE 753
 * There is a box protected by a password. The password is n digits, 
 * where each letter can be one of the first k digits 0, 1, ..., k-1.
 * You can keep inputting the password, the password will automatically 
 * be matched against the last n digits entered. For example, assuming 
 * the password is "345", I can open it when I type "012345", but I enter 
 * a total of 6 digits. Please return any string of minimum length that 
 * is guaranteed to open the box after the entire string is input.
 * Example 1:
 * Input: n = 1, k = 2
 * Output: "01"
 * Note: "10" will be accepted too.
 * Example 2:
 * Input: n = 2, k = 2
 * Output: "00110"
 * Note: "01100", "10011", "11001" will be accepted too.
 * Note:
 * - n will be in the range [1, 4].
 * - k will be in the range [1, 10].
 * - k^n will be at most 4096.
 * 
 * Company: Google
 * Difficulty: hard
 */
public class CrackingSafe {
    // n digits with k numbers
    // there k^n combinations of the lock
    // to generate the string, reuse last n-1 digits of previous lock 
    public String crackSafe(int n, int k) {
        int goal = (int) Math.pow(k, n);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append('0');
        }
        // keeps track of completed edges
        Set<String> visited = new HashSet<>();
        visited.add(sb.toString());
        dfs(n, k, sb, goal, visited);
        return sb.toString();
    }
    
    private boolean dfs(int n, int k, StringBuilder sb, int goal, Set<String> visited) {
        if (visited.size() == goal) return true;
        String prev = sb.substring(sb.length()-n+1, sb.length());
        for (int i = 0; i < k; i++) {
            String next = prev + i;
            if (!visited.contains(next)) {
                sb.append(i);
                visited.add(next);
                if (dfs(n, k, sb, goal, visited)) {
                    return true;
                }
                visited.remove(next);
                sb.deleteCharAt(sb.length()-1);
            }
        }
        return false;
    }
}
