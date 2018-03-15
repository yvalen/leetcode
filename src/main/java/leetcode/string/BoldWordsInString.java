package leetcode.string;

/*
 * LEETCODE 758
 * Given a set of keywords words and a string S, make all appearances of all 
 * keywords in S bold. Any letters between <b> and </b> tags become bold.
 * The returned string should use the least number of tags possible, and of 
 * course the tags should form a valid combination.
 * For example, given that words = ["ab", "bc"] and S = "aabcd", we should 
 * return "a<b>abc</b>d". Note that returning "a<b>a<b>b</b>c</b>d" would use
 * more tags, so it is incorrect.
 * Note:
 * - words has length in range [0, 50].
 * - words[i] has length in range [1, 10].
 * - S has length in range [0, 500].
 * - All characters in words[i] and S are lower case letters.
 * 
 * Company: Google
 * Difficulty: easy
 * 
 */
public class BoldWordsInString {
    public String boldWords(String[] words, String S) {
        if (S == null || S.isEmpty()) return S;
        
        int n = S.length();
        boolean[] isBold = new boolean[n];
        for (int i = 0; i < n; i++) {
            for (String word : words) {
                boolean inS = true;
                for (int j = 0; j < word.length(); j++) {
                    // need to check (i+j)th char in S with jth chat in word
                    if (i+j >= S.length() || S.charAt(i+j) != word.charAt(j)) {
                        inS = false;
                        break;
                    }
                }
                if (inS) {
                    for (int j = i; j < i+word.length(); j++) {
                        isBold[j] = true;
                    }
                }
            }
        }
        
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            if (isBold[i] && (i == 0 || !isBold[i-1])) {
                sb.append("<b>");
            }
            sb.append(S.charAt(i));
            if (isBold[i] && (i == n-1 || !isBold[i+1])) {
                sb.append("</b>");
            }
        }
        
        return sb.toString();
    }

}
