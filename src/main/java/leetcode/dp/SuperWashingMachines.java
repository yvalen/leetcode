package leetcode.dp;

/*
 * LEETCODE 517
 * You have n super washing machines on a line. Initially, each washing machine has some dresses or is empty.
 * For each move, you could choose any m (1 ≤ m ≤ n) washing machines, and pass one dress of each washing machine 
 * to one of its adjacent washing machines at the same time. Given an integer array representing the number of dresses 
 * in each washing machine from left to right on the line, you should find the minimum number of moves to make all the 
 * washing machines have the same number of dresses. If it is not possible to do it, return -1.
 * Example1 
 * Input: [1,0,5]
 * Output: 3
 * Explanation: 
 * 1st move:    1     0 <-- 5    =>    1     1     4
 * 2nd move:    1 <-- 1 <-- 4    =>    2     1     3    
 * 3rd move:    2     1 <-- 3    =>    2     2     2   
 * Example2
 * Input: [0,3,0]
 * Output: 2
 * Explanation: 
 * 1st move:    0 <-- 3     0    =>    1     2     0    
 * 2nd move:    1     2 --> 0    =>    1     1     1     
 * Example3
 * Input: [0,2,0]
 * Output: -1
 * Explanation: It's impossible to make all the three washing machines have the same number of dresses. 
 * Note:
 * - The range of n is [1, 10000].
 * - The range of dresses number in a super washing machine is [0, 1e5].
 * 
 * Company: Amazon
 * Difficulty: hard
 */
public class SuperWashingMachines {
    public int findMinMoves(int[] machines) {
        if (machines == null || machines.length <= 1)
            return 0;

        int sum = 0;
        for (int m : machines)
            sum += m;

        // check whether the sum of dresses in all machines can be divided by
        // count of machines
        // if not there is no solution
        if (sum % machines.length != 0)
            return -1;

        // we can always transfer a dress from one machine to another, one at a
        // time until every
        // machines reach the same number, so there must be a solution. In this
        // way, the total actions
        // is sum of operations on every machine.
        // Since we can operate several machines at the same time, the minimum
        // number of moves is the
        // maximum number of necessary operations on every machine.

        int avg = sum / machines.length;
        int count = 0, max = 0;
        for (int load : machines) {
            count += load - avg;
            max = Math.max(Math.max(max, Math.abs(count)), load - avg);
        }
        return max;
    }
}
