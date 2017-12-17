package leetcode.design;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * LEETCODE 244
 * This is a follow up of Shortest Word Distance. The only difference is now you are given the list of words and your method 
 * will be called repeatedly many times with different parameters. How would you optimize it? Design a class which receives 
 * a list of words in the constructor, and implements a method that takes two words word1 and word2 and return the shortest 
 * distance between these two words in the list.
 * Note: You may assume that word1 does not equal to word2, and word1 and word2 are both in the list.
 * 
 * Company: LinkedIn
 * Difficulty: medium
 * Similar Questions: 243, 244(ShortestWordDistance, I & III), 21(Merge Two Sorted Lists)
 */
public class WordDistance {
    private Map<String, List<Integer>> indexMap;
    private int maxDistance;

    public WordDistance(String[] words) {
        indexMap = new HashMap<>();
        maxDistance = words.length;
        for (int i = 0; i < words.length; i++) {
            List<Integer> indexList = indexMap.get(words[i]);
            if (indexList == null) {
                indexList = new ArrayList<>();
                indexMap.put(words[i], indexList);
            }
            indexList.add(i);
        }
    }

    public int shortest(String word1, String word2) {
        List<Integer> list1 = indexMap.get(word1);
        List<Integer> list2 = indexMap.get(word2);
        int distance = maxDistance;
        for (int i = 0, j = 0; i < list1.size() && j < list2.size();) {
            int idx1 = list1.get(i), idx2 = list2.get(j);
            distance = Integer.min(distance, Math.abs(idx2 - idx1));
            if (idx1 < idx2)
                i++;
            else
                j++;
        }
        return distance;
    }

    public static void main(String[] args) {
        String[] words = { "practice", "makes", "perfect", "coding", "makes" };
        WordDistance w = new WordDistance(words);
        String word1 = "coding", word2 = "practice";
        // String word1 = "makes", word2 = "coding";
        System.out.println(w.shortest(word1, word2));
    }
}
