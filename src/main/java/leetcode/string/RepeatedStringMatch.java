package leetcode.string;

/*
 * LEETCODE 686
 * Given two strings A and B, find the minimum number of times A has to be repeated such that B is a substring of it. If no such solution, return -1.
 * For example, with A = "abcd" and B = "cdabcdab". Return 3, because by repeating A three times (“abcdabcdabcd”), B is a substring of it;  
 * B is not a substring of A repeated two times ("abcdabcd").
 * Note: The length of A and B will be between 1 and 10000. 
 * 
 * Company: Google
 * Difficulty: easy
 * Similar Questions: 459(RepeatedSubstringPattern)
 */
public class RepeatedStringMatch {
    public int repeatedStringMatch(String A, String B) {
        if (A == null || A.isEmpty())
            return (B == null || B.isEmpty()) ? 0 : -1;

        int count = 0;
        StringBuilder sb = new StringBuilder();
        while (sb.length() < B.length()) {
            count++;
            sb.append(A);
        }

        if (sb.toString().contains(B))
            return count;

        if (sb.append(A).toString().contains(B))
            return count + 1;

        return -1;
    }

    public static void main(String[] args) {
        RepeatedStringMatch rsm = new RepeatedStringMatch();
        String A = "abcd", B = "cdabcdab";
        System.out.println(rsm.repeatedStringMatch(A, B));
    }
}
