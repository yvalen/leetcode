package company.airbnb;

import java.util.Arrays;

/*
 * Airbnb
 * 给个input list of floating points, 要求output list of integers, 满足以下两个constraint， 
 * 就是和跟Round(x1+x2+... +xn)的结果一样，但是minimize output 和input的绝对值差之和
 * #Input: A = [x1, x2, ..., xn]. 
 * # Sum T = Round(x1+x2+... +xn)  ;  178.93E => 179
 * # Output: B = [y1, y2, ...., yn]
 * # Constraint #1: y1+y2+...+yn = T
 * # Constraint #2: minimize sum(abs(diff(xi - yi)))
 * 举例
 * # A = [1.2, 2.3, 3.4]
 * # Round(1.2 + 2.3 + 3.4) = 6.9 => 7
 * # 1 + 2 + 3 => 6
 * 
 * # 1 + 3 + 3 => 7
 * # 0.2 + 0.7 + 0.4 = 1.3
 * 
 * # 1 + 2 + 4 => 7
 * # 0.2 + 0.3 + 0.6 = 1.1. 
 * 所以[1,2,4]比[1,3,3]要好
 */
public class RoundedNumber {
    // 思路就是先把所有floor加起来，然后看差多少，然后把多少个floor转成ceil
    // 转的时候按照num本身与ceil的距离排序
    public static int[] getClosestIntegers(double[] prices) {
        double sum = 0.0;
        int floorSum = 0;
        ValueDiff[] diffs = new ValueDiff[prices.length];
        for (int i = 0; i < prices.length; i++) {
            double p = prices[i];
            sum += p;
            int floor = (int) p;
            floorSum += floor;
            int ceil = (floor < p) ? floor+1 : floor;
            diffs[i]= new ValueDiff(floor, ceil, p - floor);
        }
        int roundedSum = (int) Math.round(sum);
        int diff = roundedSum - floorSum;
        Arrays.sort(diffs, (a, b) -> Double.compare(b.diff, a.diff));
        
        int[] result = new int[prices.length];
        int i = 0;
        while (diff-- > 0) {
            result[i] = diffs[i].ceil;
            i++;
        }
        
        while (i < prices.length) {
            result[i] = diffs[i].floor;
            i++;
        }
        
        return result;
    }

    private static class ValueDiff {
        private double diff;
        private int floor;
        private int ceil;
        ValueDiff(int floor, int ceil, double diff) {
            this.floor = floor;
            this.ceil = ceil;
            this.diff = diff;
        }
    }

    public static void main(String[] args) {
        //double[] prices = {1.2, 2.3, 3.4};
        //double[] prices = { 1.2, 3.7, 2.3, 4.0 };
       // double[] prices = { 1.2, 2.5, 3.6, 4.0};
        double[] prices={-0.4,1.3,1.3,1.3,1.3,1.3,1.3,1.3,1.3,1.3,1.3};
        int[] result = RoundedNumber.getClosestIntegers(prices);
        System.out.println(Arrays.toString(result));
    }
}
