package leetcode.greedy;

/*
 * There are N gas stations along a circular route, where the amount of gas at station i is gas[i].
 * You have a car with an unlimited gas tank and it costs cost[i] of gas to travel from station i 
 * to its next station (i+1). You begin the journey with an empty tank at one of the gas stations.
 * Return the starting gas station's index if you can travel around the circuit once, otherwise return -1.
 * Note: The solution is guaranteed to be unique. 
 */
public class GasStation {
	public int canCompleteCircuit_bruteForce(int[] gas, int[] cost) {
        int n = gas.length;
        for (int i = 0; i < n; i++) {
        	int currentGas = 0, count = 0;
        	for (int j = i; count < n; j++, count++) {
        		if (j == n) j = 0;
        		if (gas[j] + currentGas < cost[j]) break;
        		currentGas = currentGas + gas[j] - cost[j];
        		if (count == n-1) return i;
        	}
        }
		return -1;
    }
	
	// If sum of gas is more than sum of cost, then there must be a solution.
	// The tank should never be negative, so restart whenever there is a negative number.
	public int canCompleteCircuit(int[] gas, int[] cost) {
        int gasSum = 0, costSum = 0, tank = 0, start = 0;
        for (int i = 0; i < gas.length; i++) {
        	gasSum += gas[i];
        	costSum += cost[i];
        	tank += gas[i] - cost[i];
        	if (tank < 0) {
        		start = i + 1;
        		tank = 0;
        	}
        }
		return gasSum >= costSum ? start : -1;
    }
	
	// every time we start from a station, we go as far as possible by increasing end until remaining gas is less than 0. 
	// If end finally hits start we know we can travel around from start. If we haven't traveled around, we know we cannot 
	// start from this station. Then we check the station before our start station if we can start from this station. 
	// Repeat until we have checked all stations.
	// Time complexity O(n)
	public int canCompleteCircuit_bothEnd(int[] gas, int[] cost) {
        int end = 0, start = gas.length - 1;
        int sum = gas[start] - cost[start];
        while (start > end) {
        	if (sum >= 0) {
        		sum += gas[end] - cost[end];
        		end++;
        	}
        	else {
        		--start;
        		sum += gas[start] - cost[start];
        	}
        }
        return sum >= 0 ? start : -1;
    }
	
	

	public static void main(String[] args) {
		GasStation g = new GasStation();
		int[] gas = {4};
		int[] cost = {5};
		System.out.println(g.canCompleteCircuit_bruteForce(gas, cost));
	}
}
