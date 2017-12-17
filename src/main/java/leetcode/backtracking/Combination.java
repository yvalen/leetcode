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
 */
public class Combination {
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
            helper(n, k, result, list, i + 1); // use i+1 instead of start+1
                                               // here so that the sequence is
                                               // always in increasing order
            list.removeLast();
        }
    }

    public static void main(String[] args) {
        Combination c = new Combination();
        int n = 4, k = 2;
        System.out.println(c.combine(n, k));
    }
}
