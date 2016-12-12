package leetcode.list;

import java.util.Random;


/**
 * Reservoir sampling: https://en.wikipedia.org/wiki/Reservoir_sampling
 * Randomly choosing k samples from a list of n items, O(n)
 * 	1) Create an array reservoir[0..k-1] and copy first k items of stream[] to it.
 * 	2) Now one by one consider all items from (k+1)th item to nth item.
 * 		a) Generate a random number from 0 to i where i is index of current item in stream[]. 
 * 		   Let the generated random number is j.
 * 		b) If j is in range 0 to k-1, replace reservoir[j] with arr[i]
 * 
 */
public class RandomNode {
	private ListNode head;
	private final Random random;
	
	public RandomNode(ListNode head) {
		this.head = head;
		random = new Random();
	}

	/**
	 * 1. Initialize result as first node result = head->key 
	 * 2. Initialize n = 2
	 * 3. Now one by one consider all nodes from 2nd node onward.
	 * 		a. Generate a random number j from 0 to n-1. 
	 * 		b. If j is equal to 0 replace result with current node.
	 * 		c. n = n+1
	 * 		d. current = current->next
	 */
	public int getRandom() {
        int result = head.val;
        
        int len = 2;
        ListNode current = head.next;
        while (current != null) {
        	if (random.nextInt(len) == 0) {
        		result = current.val;
        	}
        	current = current.next;
        	len++;
        }
        
        return result;
    }
	
	
	
}
