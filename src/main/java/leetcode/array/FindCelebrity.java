package leetcode.array;

/*
 * LEETCODE 277
 * Suppose you are at a party with n people (labeled from 0 to n - 1) and among them, 
 * there may exist one celebrity. The definition of a celebrity is that all the other 
 * n - 1 people know him/her but he/she does not know any of them. Now you want to find 
 * out who the celebrity is or verify that there is not one. The only thing you are 
 * allowed to do is to ask questions like: "Hi, A. Do you know B?" to get information of 
 * whether A knows B. You need to find out the celebrity (or verify there is not one) by 
 * asking as few questions as possible (in the asymptotic sense). You are given a helper 
 * function 
 * bool knows(a, b) 
 * which tells you whether A knows B. Implement a function int findCelebrity(n), your 
 * function should minimize the number of calls to knows.
 * Note: There will be exactly one celebrity if he/she is in the party. Return the 
 * celebrity's label if there is a celebrity in the party. If there is no celebrity, return -1. 
 * 
 * Company: Facebook, LinkedIn
 * Difficulty: medium
 */
public class FindCelebrity {
    public int findCelebrity(int n) {

        // if A knows B, A must not be celebrity, B could be celebrity
        int candidate = 0;
        for (int i = 1; i < n; i++) {
            if (knows(candidate, i))
                candidate = i;
        }
        // everyone to the left of candidate must not be celebrity because 
        // they know previous or current candidate
        // everyone to the right of candidate must not be celebrity because
        // candidate knows no one to the right, and celebrity should be known 
        // to everyone

        // check if candidate is celebrity
        for (int i = 0; i < n; i++) {
            if (i == candidate)
                continue;

            if (!knows(i, candidate) || // someone doesn't know candidate
                    knows(candidate, i)) { // candidate knows someone
                return -1;
            }
        }

        return candidate;
    }

    private boolean knows(int a, int b) {
        return true;
    }
}
