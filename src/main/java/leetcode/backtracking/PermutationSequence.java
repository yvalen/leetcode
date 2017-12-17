package leetcode.backtracking;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/*
 * LEETCODE 60
 * The set [1,2,3,…,n] contains a total of n! unique permutations.
 * By listing and labeling all of the permutations in order,
 * We get the following sequence (ie, for n = 3):
 * 	"123"
 * 	"132"
 * 	"213"
 * 	"231"
 * 	"312"
 * 	"321"
 * Given n and k, return the kth permutation sequence.
 * Note: Given n will be between 1 and 9 inclusive.
 * 
 * Company: Twitter
 * Difficulty: medium
 * Similar Questions: 46(Permutations), 31(NextPermutation)
 */
public class PermutationSequence {
    private int count;

    public String getPermutation(int n, int k) {
        StringBuilder sb = new StringBuilder();
        boolean[] used = new boolean[n];
        helper(sb, n, k, used);
        return sb.toString();
    }

    private void helper(StringBuilder sb, int n, int k, boolean[] used) {
        if (sb.length() == n) {
            count++;
            return;
        }

        for (int i = 1; i <= n; i++) {
            if (used[i - 1])
                continue;

            sb.append(i);
            used[i - 1] = true;

            helper(sb, n, k, used);
            if (count == k)
                return; // return if found before backtrack

            sb.setLength(sb.length() - 1);
            used[i - 1] = false;
        }
    }

    // https://discuss.leetcode.com/topic/17348/explain-like-i-m-five-java-solution-in-o-n
    // For n numbers, permutations can be divided into n groups with (n - 1)!
    // elements in each group.
    // Thus, k / (n - 1)! is the group index among the current n (to be) sorted
    // groups, and k % (n - 1)!
    // is the sequence number k for next iteration.
    // Complexity: time - O(n^2) as list.remove(i) is O(n), space - O(n)
    public String getPermutation_withFactorial(int n, int k) {
        if (n <= 0 || k <= 0)
            return null;

        int[] factorial = new int[n]; // we only need factorial up to n-1
        factorial[0] = 1;
        List<Integer> nums = new ArrayList<>();
        for (int i = 1; i < n; i++) {
            nums.add(i);
            factorial[i] = i * factorial[i - 1];
        }
        nums.add(n); // add the last number to list

        k--; // 14th count from 1, turn to be 13th count from 0.
        StringBuilder sb = new StringBuilder();
        for (int i = n; i > 0; i--) {
            int index = k / factorial[i - 1];
            k %= factorial[i - 1];
            sb.append(nums.get(index));
            nums.remove(index); // need to remove the number used already from
                                // list
        }
        return sb.toString();
        /*
         * // create an array of factorial lookup, {1, 1, 2, 6, 24, ... n!}
         * int[] factorial = new int[n+1]; factorial[0] = 1; for (int i = 1; i
         * <= n; i++) { factorial[i] = i * factorial[i-1]; }
         * //IntStream.of(factorial).forEach(System.out::println);
         * 
         * List<Integer> numbers = IntStream.range(1,
         * 10).boxed().collect(Collectors.toList());
         * 
         * k--; // index starts from 0
         * 
         * StringBuilder sb = new StringBuilder(); for (int i = 1; i <=n; i++) {
         * int index = k / factorial[n-i]; sb.append(numbers.get(index));
         * numbers.remove(index); k -= index * factorial[n-i]; }
         * 
         * return sb.toString();
         */
    }

    public static void main(String[] args) {
        PermutationSequence p = new PermutationSequence();
        int n = 1, k = 1;
        // int n = 4, k =9;
        // int n = 9, k =161191;
        // System.out.println(p.getPermutation(n, k));
        System.out.println(p.getPermutation_withFactorial(n, k));

    }
}
