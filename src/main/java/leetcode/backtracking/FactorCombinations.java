package leetcode.backtracking;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/*
 * Numbers can be regarded as product of its factors. For example, 8 = 2 x 2 x 2,  = 2 x 4.
 * Write a function that takes an integer n and return all possible combinations of its factors.
 * Note: You may assume that n is always positive. Factors should be greater than 1 and less than n.
 * Examples:
 * 	input: 1 , output: []
 * 	input: 37 , output: []
 * 	input: 12, output:
 * 	[
 * 		[2, 6],
 * 		[2, 2, 3],
 * 		[3, 4]
 * 	]
 * 	input: 32, output:
 * 	[
 * 		[2, 16],
 * 		[2, 2, 8],
 * 		[2, 2, 2, 4],
 * 		[2, 2, 2, 2, 2],
 * 		[2, 4, 4],
 * 		[4, 8]
 * 	]
 * 
 * Company: LinkedIn, Uber
 */
public class FactorCombinations {
	public List<List<Integer>> getFactors(int n) {
        List<List<Integer>> result = new ArrayList<>();
        getFactorsHelper(n, result, new LinkedList<>(), 2);
        return result;
    }
	
	private void getFactorsHelper(int n, List<List<Integer>> result, LinkedList<Integer> list, int start) {
		if (n == 1) {
			if (list.size() > 1) { // skip the list which contains one element 
				result.add(new ArrayList<>(list));
			}
			return;
		}
		
		// factors of an integer n (except for 1 and n) are always between 1 and sqrt(n)
		// if the factor is bigger than sqrt(n), then it's next factor will be smaller than sqrt(n), 
		// so we only have to loop until the index reaches sqrt(n);
		for (int i = start; i * i <= n; i++) {
			if (n % i != 0) continue;
			list.addLast(i);
			getFactorsHelper(n/i, result, list, i);
			list.removeLast();
		}
	
		list.addLast(n);
		getFactorsHelper(1, result, list, n); 
		list.removeLast();
		
	}
	
	public static void main(String[] args) {
		FactorCombinations fc = new FactorCombinations ();
		//int n = 32;
		int n = 16;
		System.out.println(fc.getFactors(n));
	}
}
