package leetcode.array;

public class ShortestWordDistance {
    /*
     * LEETCODE 243 Given a list of words and two words word1 and word2, return
     * the shortest distance between these two words in the list. For example,
     * assume that words = ["practice", "makes", "perfect", "coding", "makes"].
     * Given word1 = “coding”, word2 = “practice”, return 3. Given word1 =
     * "makes", word2 = "coding", return 1. Note: You may assume that word1 does
     * not equal to word2, and word1 and word2 are both in the list.
     * 
     * Company: LinkedIn Difficulty: easy Similar Questions: 244(WordDistance)
     */
    public int shortestDistance(String[] words, String word1, String word2) {
        int idx1 = -1, idx2 = -1, distance = words.length;
        for (int i = 0; i < words.length; i++) {
            if (words[i].equals(word1))
                idx1 = i;
            else if (words[i].equals(word2))
                idx2 = i;
            if (idx1 == -1 || idx2 == -1)
                continue;
            distance = Math.min(distance, Math.abs(idx1 - idx2));
        }
        return distance;
    }

    /*
     * LEETCODE 245 This is a follow up of Shortest Word Distance. The only
     * difference is now word1 could be the same as word2. Given a list of words
     * and two words word1 and word2, return the shortest distance between these
     * two words in the list. word1 and word2 may be the same and they represent
     * two individual words in the list. For example, Assume that words =
     * ["practice", "makes", "perfect", "coding", "makes"]. Given word1 =
     * “makes”, word2 = “coding”, return 1. Given word1 = "makes", word2 =
     * "makes", return 3. Note: You may assume word1 and word2 are both in the
     * list.
     * 
     * Company: LinkedIn Difficulty: medium Similar Questions: 243(Shortest Word
     * Distance), 244(WordDistiance)
     */
    public int shortestDistanceIII(String[] words, String word1, String word2) {
        int idx1 = -1, idx2 = -1, distance = words.length;
        boolean isSame = word1.equals(word2);
        for (int i = 0; i < words.length; i++) {
            if (words[i].equals(word1)) {
                if (isSame) {
                    idx1 = idx2; // for same word, idx1 is the previous index
                    idx2 = i;
                } else {
                    idx1 = i;
                }
            } else if (words[i].equals(word2))
                idx2 = i;
            if (idx1 == -1 || idx2 == -1)
                continue;
            distance = Integer.min(distance, Math.abs(idx1 - idx2));
        }
        return distance;
    }

}
