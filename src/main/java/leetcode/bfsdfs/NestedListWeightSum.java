package leetcode.bfsdfs;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


public class NestedListWeightSum {
	
	public interface NestedInteger {
		/** 
		 * @return true if this NestedInteger holds a single integer, rather than a nested list.
		 */
		public boolean isInteger();
		
		/**
		 * @return the single integer that this NestedInteger holds if it holds a single integer.
		 * Return null if this NestedInteger holds a nested list
		 */
		public Integer getInteger();
		
		/** 
		 * @return the nested list that this NestedInteger holds if it holds a nested list
		 * Return null if this NestedInteger holds a single integer
		 */
		public List<NestedInteger> getList();
	}
	
	/*
	 * Given a nested list of integers, return the sum of all integers in the list weighted by their depth.
	 * Each element is either an integer, or a list -- whose elements may also be integers or other lists.
	 * Example 1: Given the list [[1,1],2,[1,1]], return 10. (four 1's at depth 2, one 2 at depth 1)
	 * Example 2: Given the list [1,[4,[6]]], return 27. (one 1 at depth 1, one 4 at depth 2, and one 6 at depth 3; 1 + 4*2 + 6*3 = 27)
	 * 
	 * Company: LinkedIn
	 * Difficulty: easy
	 */
	public int depthSum(List<NestedInteger> nestedList) {
        int sum = 0;
        for (NestedInteger ni : nestedList) {
            sum += dfs(ni, 1, 0);
        }
        return sum;
    }
    
    private int dfs(NestedInteger nestedInt, int depth, int sum) {
        if (nestedInt.isInteger()) return nestedInt.getInteger() * depth;
        sum = 0;
        for (NestedInteger ni : nestedInt.getList()) {
            sum += dfs(ni, depth+1, sum);
        }
        return sum;
    }
	
    public int depthSum_bfs(List<NestedInteger> nestedList) {
        int sum = 0, depth = 1;
        while (nestedList.size() > 0) {
        	List<NestedInteger> nextLevel = new LinkedList<>();
        	for (NestedInteger ni : nestedList) {
        		if (ni.isInteger()) {
        			sum += ni.getInteger() * depth;
        		}
        		else {
        			nextLevel.addAll(ni.getList());
        		}
        	}
        	depth++;
        	nestedList = nextLevel;	
        }
        return sum;
    }
    
    /*
     * Different from the previous question where weight is increasing from root to leaf, now the weight is defined from bottom up. 
     * i.e., the leaf level integers have weight 1, and the root level integers have the largest weight.
     * Example 1: Given the list [[1,1],2,[1,1]], return 8. (four 1's at depth 1, one 2 at depth 2)
     * Example 2: Given the list [1,[4,[6]]], return 17. (one 1 at depth 3, one 4 at depth 2, and one 6 at depth 1; 1*3 + 4*2 + 6*1 = 17)
     * 
     * Company: LinkedIn
     * Difficulty: medium
     */
    public int depthSumInverse(List<NestedInteger> nestedList) {
        int sum = 0;
        for (NestedInteger ni : nestedList) {
        	dfsII(ni, new int[] {1});
        }
        return sum;
    }
    
    private int dfsII(NestedInteger nestedInteger, int[] depth) {
    	int sum = 0;
    	if (nestedInteger.isInteger()) {
    		sum = nestedInteger.getInteger() * depth[0];
    		depth[0]++;
    	}
    	else { 	   	
    		for (NestedInteger ni : nestedInteger.getList()) {
    			sum += dfsII(ni, depth);
    		}
    	}
    	return sum;
    }
    public static void main(String[] args) {
    	
    }

}
