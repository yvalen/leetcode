package leetcode.bfsdfs;

/*
 * A zero-indexed array A consisting of N different integers is given. The array contains all integers in the range [0, N - 1].
 * Sets S[K] for 0 <= K < N are defined as follows: S[K] = { A[K], A[A[K]], A[A[A[K]]], ... }. Sets S[K] are finite for each K 
 * and should NOT contain duplicates. Write a function that given an array A consisting of N integers, return the size of the 
 * largest set S[K] for this array.
 * Example 1:
 * Input: A = [5,4,0,3,1,6,2]
 * Output: 4
 * Explanation: 
 * A[0] = 5, A[1] = 4, A[2] = 0, A[3] = 3, A[4] = 1, A[5] = 6, A[6] = 2.
 * One of the longest S[K]:
 * S[0] = {A[0], A[5], A[6], A[2]} = {5, 6, 2, 0}
 * Note:
 * - N is an integer within the range [1, 20,000].
 * - The elements of A are all distinct.
 * - Each element of array A is an integer within the range [0, N-1].
 * 
 * Company: Apple
 * Difficulty: medium
 */
public class ArrayNesting {
    // start from every number find circle in those index-pointer-chain.
    // mark every number in the traversal as visited since A contains only
    // distict numbers
    public int arrayNesting_recursive(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;

        int maxLen = 0, n = nums.length;
        boolean[] visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            maxLen = Math.max(maxLen, dfs(nums, visited, i, 0));
        }
        return maxLen;
    }

    private int dfs(int[] nums, boolean[] visited, int idx, int depth) {
        if (visited[idx])
            return depth;
        visited[idx] = true;
        return dfs(nums, visited, nums[idx], depth + 1);
    }

    public int arrayNesting_iterative(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;

        int maxLen = 0, n = nums.length;
        boolean[] visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            int depth = 0;
            for (int j = i; !visited[j]; j = nums[j]) {
                visited[j] = true;
                depth++;
            }
            maxLen = Math.max(maxLen, depth);
        }
        return maxLen;
    }

    public static void main(String[] args) {
        ArrayNesting an = new ArrayNesting();
        int[] nums = { 5, 4, 0, 3, 1, 6, 2 };
        System.out.println(an.arrayNesting_iterative(nums));
    }
}
