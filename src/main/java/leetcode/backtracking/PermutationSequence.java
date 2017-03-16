package leetcode.backtracking;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/*
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
			if (used[i-1]) continue;
			
			sb.append(i);
			used[i-1] = true;
			
			helper(sb, n, k, used);
			if (count == k) return; // return if found before backtrack
			
			sb.setLength(sb.length() - 1);
			used[i-1] = false;
		}
	}
	
	public String getPermutation_withFactorial(int n, int k) {
		// create an array of factorial lookup, {1, 1, 2, 6, 24, ... n!}
		int[] factorial = new int[n+1];
		factorial[0] = 1;
		for (int i = 1; i <= n; i++) {
			factorial[i] = i * factorial[i-1];
		}
		//IntStream.of(factorial).forEach(System.out::println);
		
		List<Integer> numbers = IntStream.range(1, 10).boxed().collect(Collectors.toList());
		
		k--; // index starts from 0
		
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <=n; i++) {
			int index = k / factorial[n-i];
			sb.append(numbers.get(index));
			numbers.remove(index);
			k -= index * factorial[n-i];
		}
		
		return sb.toString();
	}
	
	public static void main(String[] args) {
		PermutationSequence p = new PermutationSequence();
		//int n = 1, k =1;
		int n = 4, k =9;
		//int n = 9, k =161191;
		//System.out.println(p.getPermutation(n, k));
		System.out.println(p.getPermutation_withFactorial(n, k));
		
	}
}
