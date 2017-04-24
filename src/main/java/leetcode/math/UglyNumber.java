package leetcode.math;

import java.util.PriorityQueue;

public class UglyNumber {
	/*
	 * Write a program to check whether a given number is an ugly number. Ugly numbers are positive numbers whose prime factors 
	 * only include 2, 3, 5. For example, 6, 8 are ugly while 14 is not ugly since it includes another prime factor 7.
	 * Note that 1 is typically treated as an ugly number. 
	 */
	/*
	public boolean isUgly(int num) {
       if (num <= 0) return false;
       
       if (num == 1) return true;
       
       while (num != 0) {
    	   if (num == 2 || num == 3 || num == 5) return true;
    	   
    	   if (num % 5 == 0) num /= 5;
    	   else if (num %3 == 0) num /= 3;
    	   else if (num % 2 == 0) num /= 2;
    	   else return false;
       }
       
       return false;
    }
    */
	
	public boolean isUgly(int num) {
		if (num <= 0) return false;
		if (num == 1) return true;
		int[] primeFactors = {2, 3, 5};
		for (int primeFactor : primeFactors) {
			while (num % primeFactor == 0) num /= primeFactor;
		}
		return num == 1;
    }

	
	/*
	 * Write a program to find the n-th ugly number. For example, 
	 * 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 is the sequence of the first 10 ugly numbers.
	 * Note that 1 is typically treated as an ugly number, and n does not exceed 1690. 
	 */
	// DP: We have an array k of first n ugly number. We only know, at the beginning, the first one, which is 1. 
	// k[1] = min( k[0]x2, k[0]x3, k[0]x5). The answer is k[0]x2. So we move 2's pointer to 1. Then we test:
	// k[2] = min( k[1]x2, k[0]x3, k[0]x5). And so on. Be careful about the cases such as 6, in which we need to 
	// forward both pointers of 2 and 3.
	public int nthUglyNumber(int n) {
		if (n <= 0) throw new IllegalArgumentException();
		
		int[] dp = new int[n];
		dp[0] = 1;
		int idx2 = 0, idx3 = 0, idx5 = 0;
		for (int i = 1; i < n; i++) {
			dp[i] = Integer.min(dp[idx2]*2, Integer.min(dp[idx3]*3, dp[idx5]*5));
			if (dp[i] % 2 == 0) idx2++;
			if (dp[i] % 3 == 0) idx3++;
			if (dp[i] % 5 == 0) idx5++;
		}
		
		return dp[n-1];
    }
	
	/*
	 * Write a program to find the nth super ugly number. Super ugly numbers are positive numbers whose all prime factors 
	 * are in the given prime list primes of size k. For example, [1, 2, 4, 7, 8, 13, 14, 16, 19, 26, 28, 32] is the sequence 
	 * of the first 12 super ugly numbers given primes = [2, 7, 13, 19] of size 4.
	 * Note:
	 * - 1 is a super ugly number for any given primes.
	 * - The given numbers in primes are in ascending order.
	 * - 0 < k ≤ 100, 0 < n ≤ 106, 0 < primes[i] < 1000.
	 * - The nth super ugly number is guaranteed to fit in a 32-bit signed integer. 
	 */
	// Time complexity O(kn) k - length of primes
	public int nthSuperUglyNumber(int n, int[] primes) {
		if (n <= 0 || primes == null || primes.length == 0) {
			throw new IllegalArgumentException();
		}
		
		int[] dp = new int[n];
		dp[0] = 1;
		int[] indexes = new int[primes.length];
		for (int i = 1; i < n; i++) {
			dp[i] = Integer.MAX_VALUE; // dp[i] is initialized as 0, cannot use it in Integer.min
			
			/*
			for (int j = 0; j < primes.length; j++) {
				dp[i] = Integer.min(dp[i], dp[indexes[j]] * primes[j]);
			}
			for (int j = 0; j < primes.length; j++) {
				if (dp[i] % primes[j] == 0) indexes[j]++; // need to use the final dp[i] value to decide which index to move forward
			}
			*/
			
			// one loop, 
			for (int j = 0; j < primes.length; j++) {
				if (primes[j]*dp[indexes[j]] == dp[i-1]) indexes[j]++;
				dp[i] = Integer.min(dp[i], dp[indexes[j]] * primes[j]);
			}
		}
		
		return dp[n-1];
    }
	
	// Time complexity O(nlogk) k - length of primes
	public int nthSuperUglyNumber_withHeap(int n, int[] primes) {
		if (n <= 0 || primes == null || primes.length == 0) {
			throw new IllegalArgumentException();
		}
		
		int[] dp = new int[n];
		dp[0] = 1;
		
		PriorityQueue<PrimeWrapper> pq = new PriorityQueue<>();
		for (int i = 0; i < primes.length; i++) {
			pq.add(new PrimeWrapper(primes[i], primes[i], 0));
		}
		
		for (int i = 1; i < n; i++) {
			dp[i] = pq.peek().value;
			while (pq.peek().value == dp[i]) { // move forward all corresponding indexes
				PrimeWrapper prime = pq.poll();
				pq.add(new PrimeWrapper(prime.prime, prime.prime * dp[prime.index], prime.index+1));
			}
		}
		
		return dp[n-1];
	}
	
	private static class PrimeWrapper implements Comparable<PrimeWrapper> {
		int prime;
		int value;
		int index;
		
		PrimeWrapper(int prime, int value, int index) {
			this.prime = prime;
			this.value = value;
			this.index = index;
		}

		@Override
		public int compareTo(PrimeWrapper o) {
			return this.value - o.value;
		}
	}
	
	public static void main(String[] args) {
		UglyNumber un = new UglyNumber();
		//int num = 8;
		//int num = 14;
		//int num = 20;
		//System.out.println(un.isUgly(num));
		
		//int n = 10;
		//System.out.println(un.nthUglyNumber(n));
		
		int n = 12;
		int[] primes = {2, 7, 13, 19};
		System.out.println(un.nthSuperUglyNumber_withHeap(n, primes));
	}
}
