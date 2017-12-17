package leetcode.bfsdfs;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class NestedListWeight {
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

    /**
     * LEETCODE 339 Given a nested list of integers, return the sum of all
     * integers in the list weighted by their depth. Each element is either an
     * integer, or a list -- whose elements may also be integers or other lists.
     * Example 1: Given the list [[1,1],2,[1,1]], return 10. (four 1's at depth
     * 2, one 2 at depth 1) Example 2: Given the list [1,[4,[6]]], return 27.
     * (one 1 at depth 1, one 4 at depth 2, and one 6 at depth 3; 1 + 4*2 + 6*3
     * = 27)
     * 
     * Company: LinkedIn Difficulty: easy Similar Questions: 364(Nested List
     * Weight Sum II), 690(EmployeeImportance)
     */
    public int depthSum_recursive(List<NestedInteger> nestedList) {
        if (nestedList == null || nestedList.isEmpty())
            return 0;
        return depthSumDFS(nestedList, 1);
    }

    private int depthSumDFS(List<NestedInteger> nestedList, int depth) {
        int result = 0;
        for (NestedInteger itr : nestedList) {
            if (itr.isInteger()) {
                result += itr.getInteger() * depth;
            } else {
                result += depthSumDFS(itr.getList(), depth + 1);
            }
        }

        return result;
    }

    /**
     * LEETCODE 364 Given a nested list of integers, return the sum of all
     * integers in the list weighted by their depth. Each element is either an
     * integer, or a list -- whose elements may also be integers or other lists.
     * Different from the previous question where weight is increasing from root
     * to leaf, now the weight is defined from bottom up. i.e., the leaf level
     * integers have weight 1, and the root level integers have the largest
     * weight. Example 1: Given the list [[1,1],2,[1,1]], return 8. (four 1's at
     * depth 1, one 2 at depth 2) Example 2: Given the list [1,[4,[6]]], return
     * 17. (one 1 at depth 3, one 4 at depth 2, and one 6 at depth 1; 1*3 + 4*2
     * + 6*1 = 17) http://www.cnblogs.com/grandyang/p/5615583.html
     * 
     * Company: LinkedIn Difficulty: medium Similar Questions: 339(Nested List
     * Weight Sum)
     */
    public int depthSumInverse(List<NestedInteger> nestedList) {
        int unweighted = 0, weighted = 0;
        while (!nestedList.isEmpty()) {
            List<NestedInteger> nextLevel = new LinkedList<>();
            ;
            for (NestedInteger itr : nestedList) {
                if (itr.isInteger()) {
                    unweighted += itr.getInteger();
                } else {
                    nextLevel.addAll(nextLevel.size(), itr.getList());
                }
            }
            weighted += unweighted;
            nestedList = nextLevel;
        }
        return weighted;

    }
}
