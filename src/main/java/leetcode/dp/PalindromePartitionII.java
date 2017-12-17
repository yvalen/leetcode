package leetcode.dp;

/*
 * Given a string s, partition s such that every substring of the partition is a palindrome.
 * Return the minimum cuts needed for a palindrome partitioning of s. For example, given s = "aab", 
 * return 1 since the palindrome partitioning ["aa","b"] could be produced using 1 cut. 
 */
public class PalindromePartitionII {

    // i is the starting index and j is the ending index. i must be passed as 0
    // and j as n-1
    // minPalPartion(str, i, j) = 0 if i == j. // When string is of length 1.
    // minPalPartion(str, i, j) = 0 if str[i..j] is palindrome.
    // If none of the above conditions is true, then minPalPartion(str, i, j)
    // can be
    // calculated recursively using the following formula.
    // minPalPartion(str, i, j) = Min { minPalPartion(str, i,
    // k)+1+minPalPartion(str, k+1, j) } for (k in i...j-1)
    public int minCut(String s) {
        if (s == null || s.isEmpty())
            return 0;

        int n = s.length();

        // isPalindrome[i][j] represents whether s(i..j) forms a palindrome
        boolean[][] isPalindrome = new boolean[n][n];

        // minCut[i] represents the minumum cut from s(i...n-1)
        int[] minCut = new int[n];

        for (int right = 0; right < n; right++) {
            int min = right; // maximum number of cuts
            for (int left = 0; left <= right; left++) {
                if (s.charAt(left) == s.charAt(right) && (right - left <= 1 || isPalindrome[left + 1][right - 1])) {
                    isPalindrome[left][right] = true;
                    min = (left == 0) ? 0 : Integer.min(min, minCut[left - 1] + 1);
                }
            }
            minCut[right] = min;
        }

        return minCut[n - 1];
    }

    public static void main(String[] args) {
        PalindromePartitionII p = new PalindromePartitionII();
        String s = "aab";
        System.out.println(p.minCut(s));

    }

}
