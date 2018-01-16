package leetcode.backtracking;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/*
 * LEETCODE 77
 * Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.
 * For example, If n = 4 and k = 2, a solution is:
 * [
 * 	[2,4],
 * 	[3,4],
 * 	[2,3],
 * 	[1,2],
 * 	[1,3],
 * 	[1,4],
 * ]
 * 
 * Difficulty: medium
 * Similar Questions: 39(Combination Sum), 46(Permutations)
 * 
 * https://betterexplained.com/articles/easy-permutations-and-combinations/
 * Total number of combinations: n!/((n-k)!k!)
 */
public class Combination {
    // Time complexity: O(n^min(k, n-k))
    public List<List<Integer>> combine(int n, int k) {
        if (n <= 0 || k <= 0 || k > n)
            return Collections.emptyList();
        List<List<Integer>> result = new ArrayList<>();
        helper(n, k, result, new LinkedList<>(), 1);
        return result;
    }

    private void helper(int n, int k, List<List<Integer>> result, LinkedList<Integer> list, int start) {
        if (list.size() == k) {
            result.add(new ArrayList<>(list));
            return;
        }

        for (int i = start; i <= n; i++) {
            list.addLast(i);
            // use i+1 instead of start+1 here so that the sequence is always in increasing order
            // combination doesn't have order, [1,2,3] is the same as [1,3,2]
            helper(n, k, result, list, i + 1); 
            list.removeLast();
        }
    }

    // Airbnb
    // Given a string, convert lower case char into upper case
    //  abC -> AbC, aBC, abC, ABC
    public List<String> combinerLetters(String s) {
        if (s == null || s.isEmpty()) return Collections.emptyList();
        
        List<String> result = new ArrayList<>();
        combinerLetters(s, result, new StringBuilder(), 0);
        return result;
    }
    
    private void combinerLetters(String s, List<String> result, StringBuilder sb, int start) {
        if (sb.length() == s.length()) {
            result.add(new String(sb.toString()));
            return;
        }
        
        for (int i = start; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isLowerCase(c)) {
                sb.append(Character.toUpperCase(c));
                combinerLetters(s, result, sb, i+1);
                sb.deleteCharAt(sb.length()-1);
            }
           
            sb.append(c);
            combinerLetters(s, result, sb, i+1);
            sb.deleteCharAt(sb.length()-1);
        }
    }
    
    
    public static void main(String[] args) {
        Combination c = new Combination();
       // int n = 4, k = 3;
        //System.out.println(c.combine(n, k));
        
         
        String s =  "abC";
        System.out.println(c.combinerLetters(s));
    }
}
