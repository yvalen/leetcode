package leetcode.bfsdfs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class NestedListWeightSum {

    public interface NestedInteger {
        /**
         * @return true if this NestedInteger holds a single integer, rather
         *         than a nested list.
         */
        public boolean isInteger();

        /**
         * @return the single integer that this NestedInteger holds if it holds
         *         a single integer. Return null if this NestedInteger holds a
         *         nested list
         */
        public Integer getInteger();

        /**
         * @return the nested list that this NestedInteger holds if it holds a
         *         nested list Return null if this NestedInteger holds a single
         *         integer
         */
        public List<NestedInteger> getList();
    }

    /*
     * LEETCODE 339 
     * Given a nested list of integers, return the sum of all integers in the list 
     * weighted by their depth. Each element is either an integer, or a list -- whose 
     * elements may also be integers or other lists.
     * Example 1: 
     * Given the list [[1,1],2,[1,1]], return 10. 
     * (four 1's at depth 2, one 2 at depth 1) 
     * Example 2: Given the list [1,[4,[6]]], return 27.
     * (one 1 at depth 1, one 4 at depth 2, and one 6 at depth 3; 
     * 1 + 4*2 + 6*3 = 27)
     * 
     * Company: LinkedIn 
     * Difficulty: easy 
     * Similar Questions: 364(Nested List Weight Sum II), 565(ArrayNesting), 
     * 690(EmployeeImportance)
     */
    public int depthSum_dfs(List<NestedInteger> nestedList) {
        if (nestedList == null || nestedList.isEmpty())
            return 0;
        return dfs(nestedList, 1);
    }

    private int dfs(List<NestedInteger> nestedList, int depth) {
        int sum = 0;
        for (NestedInteger ni : nestedList) {
            if (ni.isInteger()) {
                sum += ni.getInteger() * depth;
            } else {
                sum += dfs(ni.getList(), depth + 1);
            }
        }
        return sum;
    }

    public int depthSum_bfs(List<NestedInteger> nestedList) {
        int sum = 0, level = 1;
        while (nestedList.size() > 0) {
            List<NestedInteger> nextLevel = new LinkedList<>();
            for (NestedInteger ni : nestedList) {
                if (ni.isInteger()) {
                    sum += ni.getInteger() * level;
                } else {
                    nextLevel.addAll(ni.getList());
                }
            }
            level++;
            nestedList = nextLevel;
        }
        return sum;
    }

    /*
     * LEETCODE 364 
     * Different from the previous question where weight is increasing from root 
     * to leaf, now the weight is defined from bottom up, i.e., the leaf level 
     * integers have weight 1, and the root level integers have the largest weight. 
     * Example 1: 
     * Given the list [[1,1],2,[1,1]], return 8. (four 1's at depth 1, one 2 at depth 2) 
     * Example 2: 
     * Given the list [1,[4,[6]]], return 17. (one 1 at depth 3, one 4 at depth 2, and one
     * 6 at depth 1; 1*3 + 4*2 + 6*1 = 17)
     * 
     * Company: LinkedIn 
     * Difficulty: medium
     * Similar Questions: 339(Nested List Weight Sum), 565(ArrayNesting)
     */
    // Instead of multiplying by depth, add integers multiple times (by going
    // level by level and adding the unweighted sum to the weighted sum after
    // each level).
    public int depthSumInverse_bfs(List<NestedInteger> nestedList) {
        int unweighted = 0, weighted = 0;
        while (nestedList.size() > 0) {
            List<NestedInteger> nextLevel = new ArrayList<>();
            for (NestedInteger ni : nestedList) {
                if (ni.isInteger()) {
                    // add on top of existing value of unweighted, this guarantees 
                    // the outer layer element being added to the sum in terms of 
                    // their layers
                    unweighted += ni.getInteger();
                }
                else {
                    nextLevel.addAll(ni.getList());
                }
            }
            weighted += unweighted;
            nestedList = nextLevel;
        }
        return weighted;
    }
    
    public int depthSumInverse_dfs(List<NestedInteger> nestedList) {
        return depthSumInverse(nestedList, 0);
    }
    
    private int depthSumInverse(List<NestedInteger> nestedList, int prevSum) { 
        int sum = prevSum; // need to start with prevSum
        List<NestedInteger> nextLevel = new ArrayList<>();
        for (NestedInteger ni : nestedList) {
            if (ni.isInteger()) {
                // no need to multiple by depth here as the value will be added to
                // sum which will be used as the prevSum, it will added every time
                // level goes deeper
                sum += ni.getInteger();
            }
            else {
                nextLevel.addAll(ni.getList());
            }
        }
        
        // need to check for empty list here, otherwise will be in infinite loop
        return sum += nextLevel.isEmpty() ? 0 : depthSumInverse(nextLevel, sum);
    }
}
