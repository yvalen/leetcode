package leetcode.design;

import java.util.BitSet;

/**
 * Design a Phone Directory which supports the following operations:
 * - get: Provide a number which is not assigned to anyone.
 * - check: Check if a number is available or not.
 * - release: Recycle or release a number.
 * Example:
 * 	// Init a phone directory containing a total of 3 numbers: 0, 1, and 2.
 * 	PhoneDirectory directory = new PhoneDirectory(3);
 * 
 * 	// It can return any available phone number. Here we assume it returns 0.
 * 	directory.get();
 * 
 * 	// Assume it returns 1.
 * 	directory.get();
 * 
 * 	// The number 2 is available, so return true.
 * 	directory.check(2);
 * 
 * 	// It returns 2, the only number that is left.
 * 	directory.get();
 * 	
 * 	// The number 2 is no longer available, so return false.
 * 	directory.check(2);
 * 
 * 	// Release number 2 back to the pool.
 * 	directory.release(2);
 * 
 * 	// Number 2 is available again, return true.
 * 	directory.check(2);
 */
public class PhoneDirectory extends BitSet {

    public PhoneDirectory(int maxNumbers) {
        super(maxNumbers);
        flip(0, maxNumbers);
    }

    public int get() {
        if (isEmpty())
            return -1;
        int number = nextSetBit(0);
        clear(number);
        return number;
    }
    
    public boolean check(int number) {
        return get(number);
    }
    
    public void release(int number) {
        set(number);
    }
}
