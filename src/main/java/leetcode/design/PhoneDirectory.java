package leetcode.design;

import java.util.BitSet;
import java.util.LinkedHashSet;
import java.util.Set;

/*
 * LEETCODE 379
 * Design a Phone Directory which supports the following operations: 
 * - get: Provide a number which is not assigned to anyone. 
 * - check: Check if a number is available or not. 
 * - release: Recycle or release a number. 
 * Example: 
 * // Init a phone directory containing a total of 3 numbers: 0, 1, and 2.
 * PhoneDirectory directory = new PhoneDirectory(3);
 * 
 * // It can return any available phone number. Here we assume it returns 0.
 * directory.get();
 * 
 * // Assume it returns 1. 
 * directory.get();
 * 
 * // The number 2 is available, so return true. 
 * directory.check(2);
 * 
 * // It returns 2, the only number that is left. 
 * directory.get();
 * 
 * // The number 2 is no longer available, so return false. 
 * directory.check(2);
 * 
 * // Release number 2 back to the pool. 
 * directory.release(2);
 * 
 * // Number 2 is available again, return true. 
 * directory.check(2);
 * 
 * Company: Google
 * Difficulty: medium
 */
public class PhoneDirectory  {
	private final BitSet available;
	//private final Set<Integer> available;

    public PhoneDirectory(int maxNumbers) {
    		available = new BitSet(maxNumbers);
        available.flip(0, maxNumbers);
    		/*
    		available = new LinkedHashSet<>();
        for (int i = 0; i < maxNumbers; i++) {
            available.add(i);
        }
        */
    }

    public int get() {
    		int result = available.nextSetBit(0);
        if (result == -1) return -1;
        available.flip(result);
        return result;
    		/*
    		if (available.isEmpty()) return -1;
        int result = available.iterator().next();
        available.remove(result);
        return result;
        */
    }

    public boolean check(int number) {
    		return available.get(number);
    		//return available.contains(number);
    }

    public void release(int number) {
    		available.set(number); // should use set instead of flip to hadnle release an unused number
    		//available.add(number);
    }
}
