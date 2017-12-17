package leetcode.design;

import java.util.HashMap;
import java.util.Map;

/**
 * LEETCODE 170 Design and implement a TwoSum class. It should support the
 * following operations: add and find. add - Add the number to an internal data
 * structure. find - Find if there exists any pair of numbers which sum is equal
 * to the value. For example, add(1); add(3); add(5); find(4) -> true find(7) ->
 * false
 * 
 * Company: LinkedIn Difficulty: easy
 */
public class TwoSum {
    private final Map<Integer, Integer> elemCountMap;

    public TwoSum() {
        elemCountMap = new HashMap<>();
    }

    /**
     * Add the number to an internal data structure.
     * 
     * @param number
     */
    public void add(int number) {
        Integer count = elemCountMap.get(number);
        if (count == null) {
            elemCountMap.put(number, 1);
        } else {
            elemCountMap.put(number, count + 1);
        }
    }

    /**
     * Find if there exists any pair of numbers which sum is equal to the value.
     * 
     * @param value
     * @return
     */
    public boolean find(int value) {
        for (Map.Entry<Integer, Integer> entry : elemCountMap.entrySet()) {
            Integer diff = value - entry.getKey();
            Integer diffCount = elemCountMap.get(diff);
            if (diffCount != null && (entry.getKey() != diff || diffCount > 1))
                return true;
        }

        return false;

    }

    public static void main(String[] args) {
        TwoSum twoSum = new TwoSum();

        twoSum.add(1);
        twoSum.add(-1);
        boolean found = twoSum.find(0);
        System.out.print(found);
    }

}
