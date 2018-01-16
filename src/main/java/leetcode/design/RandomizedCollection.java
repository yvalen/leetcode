package leetcode.design;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

/*
 * LEETCODE 381
 * Design a data structure that supports all following operations in average O(1) time.
 * Note: Duplicate elements are allowed.
 * insert(val): Inserts an item val to the collection.
 * remove(val): Removes an item val from the collection if present.
 * getRandom: Returns a random element from current collection of elements. The probability of each 
 * element being returned is linearly related to the number of same value the collection contains.
 * Example:
 * // Init an empty collection.
 * RandomizedCollection collection = new RandomizedCollection();
 * // Inserts 1 to the collection. Returns true as the collection did not contain 1.
 * collection.insert(1);
 * // Inserts another 1 to the collection. Returns false as the collection contained 1. 
 * // Collection now contains [1,1].
 * collection.insert(1);
 * // Inserts 2 to the collection, returns true. Collection now contains [1,1,2].
 * collection.insert(2);
 * // getRandom should return 1 with the probability 2/3, and returns 2 with the probability 1/3.
 * collection.getRandom();
 * // Removes 1 from the collection, returns true. Collection now contains [1,2].
 * collection.remove(1);
 * // getRandom should return 1 and 2 both equally likely.
 * collection.getRandom();
 * 
 * Company: Yelp
 * Difficulty: hard
 * Similar Questions: 380(RandomizedSet)
 */
public class RandomizedCollection {
    private final Map<Integer, Set<Integer>> map;
    private final List<Integer> list;
    private final Random random;
    
    public RandomizedCollection() {
        map = new HashMap<>();
        list = new ArrayList<>();
        random = new Random();
    }
    
    /** 
     * Inserts a value to the collection. 
     * Returns true if the collection did not already contain the specified element. 
     * */
    public boolean insert(int val) {
        boolean exists = map.containsKey(val);
        list.add(val);
        map.putIfAbsent(val, new LinkedHashSet<>()); // use LinkedHashedSet for fast iteratiom
        map.get(val).add(list.size()-1);
        return exists;
    }
    
    /**
     * Removes a value from the collection. 
     * Returns true if the collection contained the specified element. 
     * */
    public boolean remove(int val) {
        if (!map.containsKey(val)) return false;
        
        // get the index for the first occurrence of val
        int index = map.get(val).iterator().next();   
        // remove this index from map, should do this before updating the last element
        // to handle case where the last element is the same as the val to be removed
        map.get(val).remove(index);
        // update list and map if val is in the middle
        if (index < list.size() - 1) {
            int lastIndex = list.size() - 1;
            int lastVal = list.get(lastIndex);
            list.set(index, lastVal);
            map.get(lastVal).remove(lastIndex);
            map.get(lastVal).add(index);
        }
        
        if (map.get(val).isEmpty()) map.remove(val);
        list.remove(list.size()-1);
     
        return true;
    }
    
    /** Get a random element from the collection. */
    public int getRandom() {
        if (list.isEmpty()) return 0;
        int idx = random.nextInt(list.size());
        return list.get(idx);
    }
   
    public static void main(String[] args) {
        RandomizedCollection rc = new RandomizedCollection();
        rc.insert(0);
        rc.insert(1);
        rc.insert(2);
        rc.insert(3);
        rc.insert(3);
        System.out.println(rc.remove(2));
        System.out.println(rc.remove(3));
        System.out.println(rc.remove(0));
    }

}
