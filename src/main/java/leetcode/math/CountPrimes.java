package leetcode.math;

import java.util.BitSet;

import sun.tools.jar.resources.jar;

/*
 * LEETCODE 204
 * Count the number of prime numbers LESS THAN a non-negative number, n.
 * A prime number is a natural number GREATER than 1 that has exactly two 
 * distinct natural number divisors: 1 and itself.
 * 
 * Sieve of Eratosthenes Algorithm
 * 1. Create a list of consecutive integers from 2 through n: (2, 3, 4, ..., n).
 * 2. Initially, let p equal 2, the smallest prime number.
 * 3. Enumerate the multiples of p by counting to n from 2p in increments of p, 
 * and mark them in the list (these will be 2p, 3p, 4p, ...; the p itself should 
 * not be marked).
 * 4. Find the first number greater than p in the list that is not marked. If there 
 * was no such number, stop. Otherwise, let p now equal this new number (which is the 
 * next prime), and repeat from step 3.
 * 5. When the algorithm terminates, the numbers remaining not marked in the list are 
 * all the primes below n.
 * The main idea here is that every value given to p will be prime, because if it were 
 * composite it would be marked as a multiple of some other, smaller prime.
 * As a refinement, it is sufficient to mark the numbers in step 3 starting from p^2, 
 * as all the smaller multiples of p will have already been marked at that point. 
 * 
 * Company: Microsoft, Amazon
 * Difficulty: easy
 * Similar Questions: 263(Ugly Number), 264(Ugly Number II), 279(PerfectSquare)
 */
public class CountPrimes {
    public int countPrimes_booleanArray(int n) {
        if (n < 2) return 0;

        boolean[] notPrime = new boolean[n];
        int count = 0;
        for (int i = 2; i < n; i++) {
            if (!notPrime[i]) {
                count++;
                // start from p^2, need to check i< Math.sqrt(n), otherwise it will cause the 
                // array index out of bound when i*j exceeds Integer.MAX_VALUE
                for (int j = i; i< Math.sqrt(n) && i * j < n; j++) { 
                //for (int j = 2; i * j < n; j++) { 
                    notPrime[i * j] = true;
                }
            }
        }
        return count;
    }

    public int countPrimes_bitSet(int n) {
        if (n < 2) return 0;

        BitSet bitSet = new BitSet(n);
        bitSet.set(0, 2); // 0 and 1 are not prime 
        
        int count = 0;
        for (int i = 2; i < n; i++) {
            if (!bitSet.get(i)) {
                count++;
                for (int j = 2*i; j < n; j+= i) {
                    bitSet.set(j);
                }
            }
        }
        return count;
    }

    
    public static void main(String[] arg) {
        CountPrimes cp = new CountPrimes();
        int n = 30; // expected 10
        // int n = 3; // expect 1
        System.out.println(cp.countPrimes_booleanArray(n));

    }

}
