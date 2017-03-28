package leetcode.backtracking;

import java.util.ArrayList;
import java.util.List;

/*
 * The gray code is a binary numeral system where two successive values differ in only one bit.
 * Given a non-negative integer n representing the total number of bits in the code, print the sequence of gray code. 
 * A gray code sequence must begin with 0.
 * For example, given n = 2, return [0,1,3,2]. Its gray code sequence is:
 * 	00 - 0
 * 	01 - 1
 * 	11 - 3
 * 	10 - 2
 * 
 */
public class GrayCode {
	
	// Generate the sequence iteratively. For example, when n=3, we can get the result based on n=2.
	// 00,01,11,10 -> (000,001,011,010 ) (110,111,101,100). The middle two numbers only differ at their highest bit, 
	// while the rest numbers of part two are exactly symmetric of part one.
	public List<Integer> grayCode(int n) {
        List<Integer> result = new ArrayList<>();
        result.add(0);
        for (int i = 0; i < n; i++) {
        	int size = result.size();
        	for (int j = size - 1; j >= 0; j--) {
        		result.add(result.get(j) | 1 << i);
        	}
        }
        return result;
    }
	
}
