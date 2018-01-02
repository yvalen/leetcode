package leetcode.design;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
/*
 * LEETCODE 380
 * Design a data structure that supports all following operations in average O(1) time.
 * insert(val): Inserts an item val to the set if not already present.
 * remove(val): Removes an item val from the set if present.
 * getRandom: Returns a random element from current set of elements. Each element must have 
 * the same probability of being returned.
 * Example:
 * // Init an empty set.
 * RandomizedSet randomSet = new RandomizedSet();
 * // Inserts 1 to the set. Returns true as 1 was inserted successfully.
 * randomSet.insert(1);
 * // Returns false as 2 does not exist in the set.
 * randomSet.remove(2);
 * // Inserts 2 to the set, returns true. Set now contains [1,2].
 * randomSet.insert(2);
 * // getRandom should return either 1 or 2 randomly.
 * randomSet.getRandom();
 * // Removes 1 from the set, returns true. Set now contains [2].
 * randomSet.remove(1);
 * // 2 was already in the set, so return false.
 * randomSet.insert(2);
 * // Since 2 is the only number in the set, getRandom always return 2.
 * randomSet.getRandom();
 * 
 * Company: Google, Facebook, Amazon, Uber, Twitter, Yelp, Pocket Gems
 * Difficulty: medium
 * Similar Questions: 381(RandomizedCollection)
 */
public class RandomizedSet {
    private final Map<Integer, Integer> indexMap;
    private final List<Integer> list;
    private final Random random;

    /**
     * Initialize your data structure here.
     */
    public RandomizedSet() {
        indexMap = new HashMap<>();
        list = new ArrayList<>();
        random = new Random();
    }

    /**
     * Inserts a value to the set. Returns true if the set did not already
     * contain the specified element.
     */
    public boolean insert(int val) {
        if (indexMap.containsKey(val)) return false;
        list.add(val);
        indexMap.put(val, list.size()-1);
        return true;
    }

    /**
     * Removes a value from the set. Returns true if the set contained the
     * specified element.
     */
    public boolean remove(int val) {
        if (!indexMap.containsKey(val)) return false;
        
        // replace val in list with the last element
        list.set(indexMap.get(val), list.get(list.size()-1));
        // update the last element index in map
        indexMap.put(list.get(list.size()-1), indexMap.get(val));
        // remove val from index map
        indexMap.remove(val);
        // remove last element from list
        list.remove(list.size()-1);
        return true;
    }

    /** Get a random element from the set. */
    public int getRandom() {
        if (list.isEmpty()) return 0;
        int index = random.nextInt(list.size());
        return list.get(index);
    }

}
