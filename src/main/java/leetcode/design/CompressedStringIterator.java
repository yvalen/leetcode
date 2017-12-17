package leetcode.design;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/*
 * LEETCODE 604
 * Design and implement a data structure for a compressed string iterator. 
 * It should support the following operations: next and hasNext.
 * The given compressed string will be in the form of each letter followed by a positive integer representing the number 
 * of this letter existing in the original uncompressed string.
 * next() - if the original string still has uncompressed characters, return the next letter; Otherwise return a white space.
 * hasNext() - Judge whether there is any letter needs to be uncompressed.
 * Note: Please remember to RESET your class variables declared in StringIterator, as static/class variables are persisted 
 * across multiple test cases. 
 * Example:
 * StringIterator iterator = new StringIterator("L1e2t1C1o1d1e1");
 * iterator.next(); // return 'L'
 * iterator.next(); // return 'e'
 * iterator.next(); // return 'e'
 * iterator.next(); // return 't'
 * iterator.next(); // return 'C'
 * iterator.next(); // return 'o'
 * iterator.next(); // return 'd'
 * iterator.hasNext(); // return true
 * iterator.next(); // return 'e'
 * iterator.hasNext(); // return false
 * iterator.next(); // return ' '
 * 
 * Company: Google
 * Difficulty: easy
 * Similar Questions: 271(EncodeDecodeString), 146(LRUCache)
 */
public class CompressedStringIterator {
	/*
	// pre-compute the result in a list and use list iterator
	private List<Character> charList;
	private Iterator<Character> itr;
	public CompressedStringIterator(String compressedString) {
		if (compressedString != null) {
            int n = compressedString.length();
            charList = new ArrayList<>();
            for (int i = 0; i < n;) {
            	char c = compressedString.charAt(i++); // advance i by one here so that the next while loop checks digit directly
            	int count = 0; 
            	while (i < n && Character.isDigit(compressedString.charAt(i))) {
            		count = count * 10 + (compressedString.charAt(i) - '0');
            		i++;
            	}
            	while (count-- > 0) charList.add(c);
            }
		}
		else {
            charList = new ArrayList<>();
		}
		System.out.println(charList);
        itr = charList.listIterator();
	}
	public char next() {
		if (itr.hasNext()) return itr.next();
		return ' ';
    }
    
    public boolean hasNext() {
        return itr.hasNext();
    }
    */
	
	// on-demand
	// next() : use a global index to to keep a track of which compressed letter needs to be processed next,
	// and a global count to keep a track of the number of instances of the current letter which are still pending.
	// For each next check if there are more uncompressed letters left. If not return ' '. Otherwise check if there 
	// are more instances of the current letter pending. If so decrement the count and return the current letter.
	// If there aren't more instances pending for the current letter, update the index to point to the next letter, 
	// and update count for the next letter using decimal arithmetic.
	// hasNext(): if index reaches beyond the last index and count becomes zero, there are no more uncompressed letters exist.
	private int index;
	private int count;
	private char current;
	private final String s;
	public CompressedStringIterator(String compressedString) {
		s = compressedString;
    }
    
    public char next() {
        if (!hasNext()) return ' ';
    	if (count == 0) {
    		current = s.charAt(index++);
    		while (index < s.length() && Character.isDigit(s.charAt(index))) {
    			count = count * 10 + (s.charAt(index) - '0');
    			index++;
    		}
    	}
    	count--; // always decrement by one since we need to move forward by i in each next
    	return current;
    }
    
    public boolean hasNext() {
        return (index < s.length() || count > 0);
    }
    
    public static void main(String[] args) {
    	String compressedString = "L1e2t1C1o1d1e1";
    	//String compressedString = "x6";
    	CompressedStringIterator i = new CompressedStringIterator(compressedString);
    	
    	System.out.println(i.next());
    	System.out.println(i.next());
    	System.out.println(i.next());
    	System.out.println(i.hasNext());
    	System.out.println(i.next());
    	System.out.println(i.next());
    	System.out.println(i.next());
    	System.out.println(i.next());
    	System.out.println(i.next());
    	System.out.println(i.next());
    }
}
