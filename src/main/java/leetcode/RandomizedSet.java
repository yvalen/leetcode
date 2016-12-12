package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RandomizedSet {
	Map<Integer, Integer> valToIdxMap;
	List<Integer> valueList;
	
	/** 
	 * Initialize your data structure here. 
	 * */
    public RandomizedSet() {
    	this.valToIdxMap = new HashMap<>();
    	valueList = new ArrayList<>();
    }
    
    /** 
     * Inserts a value to the set. 
     * Returns true if the set did not already contain the specified element. 
     */
    public boolean insert(int val) {
       if (valToIdxMap.containsKey(val)) {
    	   return false;
       }
       
       valueList.add(val);
       valToIdxMap.put(val,  valueList.size() - 1);
       return true;
    }
    
    /** 
     * Removes a value from the set. 
     * Returns true if the set contained the specified element. 
     */
    public boolean remove(int val) {
    	if (valToIdxMap.containsKey(val)) {
     	   return false;
        }
        
    	int idx = valToIdxMap.get(val);
    	
    	return true;
    }
    
    /** Get a random element from the set. */
    public int getRandom() {
        return 0;
    }

}
