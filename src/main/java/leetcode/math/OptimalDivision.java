package leetcode.math;

/*
 * LEETCODE 553
 * Given a list of positive integers, the adjacent integers will perform the float division. For example, [2,3,4] -> 2 / 3 / 4.
 * However, you can add any number of parenthesis at any position to change the priority of operations. You should find out how 
 * to add parenthesis to get the maximum result, and return the corresponding expression in string format. Your expression should 
 * NOT contain redundant parenthesis.
 * Example: Input: [1000,100,10,2] Output: "1000/(100/10/2)"
 * Explanation:
 * 1000/(100/10/2) = 1000/((100/10)/2) = 200
 * However, the bold parenthesis in "1000/((100/10)/2)" are redundant,  since they don't influence the operation priority. 
 * So you should return "1000/(100/10/2)". 
 * Other cases:
 * 1000/(100/10)/2 = 50
 * 1000/(100/(10/2)) = 50
 * 1000/100/10/2 = 0.5
 * 1000/100/(10/2) = 2
 * Note:
 * - The length of the input array is [1, 10].
 * - Elements in the given array will be in range [2, 1000].
 * - There is only one optimal division for each test case.
 * 
 * Company: Amazon
 * Difficulty: medium
 */
public class OptimalDivision {
    // divide the list into two parts left and right, and call function for
    // these two parts.
    // iterate i from start to end so that left=(start,i) and right=(i+1,end).
    // left and right parts return their maximum and minimum value and
    // corresponding strings.
    // Minimum value can be found by dividing minimum of left by maximum of
    // right i.e. minVal=left.min/right.max.
    // Maximum value can be found by dividing maximum of left by minimum of
    // right i.e. maxVal=left.max/right.min
    // Time complexity : O(n^3), dp array of size n^2is filled and filling of
    // each cell takes O(n) time.
    // Space complexity : O(n^3) dp array of size n^2​​ where each cell of array
    // contains string of length O(n).
    private static class DivisionResult {
        float minVal;
        float maxVal;
        String minString;
        String maxString;

        DivisionResult() {
            minVal = Float.MAX_VALUE;
            maxVal = Float.MIN_VALUE;
            minString = "";
            maxString = "";
        }
    }

    public String optimalDivision_dp(int[] nums) {
        int n = nums.length;
        if (n == 1)
            return String.valueOf(nums[0]);
        if (n == 2)
            return String.valueOf(nums[0]) + "/" + String.valueOf(nums[1]);

        DivisionResult[][] dp = new DivisionResult[n][n];
        DivisionResult result = optimal(nums, 0, n - 1, dp);
        return result.maxString;
    }

    private DivisionResult optimal(int[] nums, int start, int end, DivisionResult[][] dp) {
        if (dp[start][end] != null)
            return dp[start][end];

        DivisionResult result = new DivisionResult();
        if (start == end) {
            result.minVal = nums[start];
            result.maxVal = nums[start];
            result.minString = String.valueOf(nums[start]);
            result.maxString = String.valueOf(nums[start]);
            dp[start][end] = result;
            return result;
        }

        for (int i = start; i < end; i++) {
            DivisionResult left = optimal(nums, start, i, dp);
            DivisionResult right = optimal(nums, i + 1, end, dp);
            float min = left.minVal / right.maxVal, max = left.maxVal / right.minVal;
            if (result.minVal > min) {
                result.minVal = min;
                // need to use i==end-1 to check whether to add parenthesis
                result.minString = left.minString + "/"
                        + (i == end - 1 ? right.maxString : "(" + right.maxString + ")");
            }
            if (result.maxVal < max) {
                result.maxVal = max;
                result.maxString = left.maxString + "/"
                        + (i == end - 1 ? right.minString : "(" + right.minString + ")");
            }
        }

        dp[start][end] = result;
        return result;
    }

    //
    // Math
    // for X1/X2/X3/../Xn, no matter how you place parentheses, X1 always goes
    // to the numerator and X2 always goes to the denominator.
    // X1/X2/X3/../Xn => (X1/X2) * Y, need to maximize Y. Y is maximized when it
    // is equal to X3 *..*Xn.
    // So the answer is always X1/(X2/X3/../Xn) = (X1 *X3 *..*Xn)/X2
    // Time complexity: O(n) Space complexity: O(n)
    public String optimalDivision_math(int[] nums) {
        int n = nums.length;
        if (n == 1)
            return String.valueOf(nums[0]);
        if (n == 2)
            return String.valueOf(nums[0]) + "/" + String.valueOf(nums[1]);

        StringBuilder sb = new StringBuilder(); // cannot initialize with
                                                // StringBuilder(nums[0]), since
                                                // it will be used as
                                                // StringBuilder(int capacity)
        sb.append(nums[0]).append("/(").append(nums[1]);
        for (int i = 2; i < nums.length; i++) {
            sb.append("/").append(nums[i]);
        }
        sb.append(")");
        return sb.toString();
    }
}
