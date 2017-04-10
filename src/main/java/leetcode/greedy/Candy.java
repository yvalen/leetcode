package leetcode.greedy;

import java.util.Arrays;

/*
 * There are N children standing in a line. Each child is assigned a rating value.
 * You are giving candies to these children subjected to the following requirements
 * - Each child must have at least one candy.
 * - Children with a higher rating get more candies than their neighbors.
 * What is the minimum candies you must give? 
 */
public class Candy {
	
	// Time O(n^2) we need to traverse the array at most n times
	// space O(n)
	public int candy_bruteForce(int[] ratings) {
		if (ratings == null || ratings.length == 0) return 0;
		int n = ratings.length;
		
		// candies array keeps track of the candies given to the students.
		// initialize candies array with 1, give 1 candy to every student
        int[] candies = new int[n];
        Arrays.fill(candies, 1);
        
        // continueGiving keeps track if any update occurs during the traversal
        // if no update occurs during the whole traversal it means we have 
        // reached the final state of distribution and we can stop the traversal
        boolean continueGiving = true;
        while (continueGiving) {
        	continueGiving = false;
        	// scan rating array from left to right and check element with its neighbor 
        	for (int i = 0; i < n; i++) {
        		if (i > 0 && ratings[i] > ratings[i-1] && candies[i] <= candies[i-1]) {
        			candies[i] = candies[i-1] + 1;
        			continueGiving = true;
        		}
        		if (i < n-1 && ratings[i] > ratings[i+1] && candies[i] <= candies[i+1]) {
        			candies[i] = candies[i+1] + 1;
        			continueGiving = true;
        		}
        	}
        }
        int sum = 0;
        for (int candy : candies) {
        	sum += candy;
        }
        
		return sum;
    }
	
	// time O(n), space O(n)
	public int candy_twoArray(int[] ratings) {
		if (ratings == null || ratings.length == 0) return 0;
		int n = ratings.length;
		
		int[] left2right = new int[n]; // store the number of candies required taking care of distribution relative to the left neighbor only
		Arrays.fill(left2right, 1);
		for (int i = 1; i < n; i++) {
			if (ratings[i] > ratings[i-1] && left2right[i] <= left2right[i-1]) {
				left2right[i] = left2right[i-1] + 1;
			}
		}
		
		int[] right2left = new int[n]; // store the number of candies required taking care of distribution relative to the right neighbor only
		Arrays.fill(right2left, 1);
		for (int i = n-2; i >= 0; i--) {
			if (ratings[i] > ratings[i+1] && right2left[i] <= right2left[i+1]) {
				right2left[i] = right2left[i+1] + 1;
			}
		}
		
		int sum = 0;
		for (int i = 0; i < n; i++) {
			sum += Integer.max(left2right[i], right2left[i]);
		}
		
		return sum;
	}
	
	// time O(n), space O(n)
	public int candy_oneArray(int[] ratings) {
		if (ratings == null || ratings.length == 0) return 0;
		int n = ratings.length;
		
		int[] candies = new int[n]; 
		Arrays.fill(candies, 1);
		for (int i = 1; i < n; i++) {
			if (ratings[i] > ratings[i-1] && candies[i] <= candies[i-1]) {
				candies[i] = candies[i-1] + 1;
			}
		}
		
		for (int i = n-2; i >= 0; i--) {
			if (ratings[i] > ratings[i+1] && candies[i] <= candies[i+1]) {
				candies[i] = candies[i+1] + 1;
			}
		}
		
		int sum = 0;
		for (int candy : candies) {
        	sum += candy;
        }
		
		return sum;
	}
	
	// time O(n), space O(1)
	// https://leetcode.com/articles/candy/
	public int candy_constantSpace(int[] ratings) {
		if (ratings == null || ratings.length == 0) return 0;
		int n = ratings.length;
		
		int sum = 0;
		int up = 0, down = 0; // keeps track of the number of elements on the rising slope and falling slope
		int oldSlope = 0;
		for (int i = 1; i < n; i++) {
			int newSlope = 0;
			if (ratings[i] > ratings[i-1]) newSlope = 1;
			else if (ratings[i] < ratings[i-1]) newSlope = -1;
			
			// update count at the end of the falling slope following a rising slope
			if ((oldSlope > 0 && newSlope == 0) || (oldSlope < 0 &&  newSlope >= 0)) {
				sum += count(up) + count(down) + Math.max(up, down);
				up = 0;
				down = 0;
			}
			
			if (newSlope > 0) up++;
			else if (newSlope < 0) down++;
			else sum++;
			
			oldSlope = newSlope;
		}
	
		sum += count(up) + count(down) + Math.max(up, down) + 1;
		return sum;
	}

	private int count(int val) {
		return (val * (val + 1)) / 2;
	}
}
