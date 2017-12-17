package leetcode.string;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * You are given a string, s, and a list of words, words, that are all of the same length. 
 * Find all starting indices of substring(s) in s that is a concatenation of each word in words 
 * exactly once and without any intervening characters.
 * For example, given:
 * 	s: "barfoothefoobarman"
 * 	words: ["foo", "bar"]
 * You should return the indices: [0,9]. (order does not matter). 
 */
public class ConcatenationOfAllWords {

    /*
     * Say in words there are m strings with length n. What is required to match
     * in S is a length of m*n string start with each position in S. And in the
     * m*n long string, every string in L appear only once. Algorithm is: scan
     * every m*n long string start from each position in S, see if all the
     * strings in words have been appeared only once using Map data structure.
     * If so, store the starting position. Complexity: O(N^2)
     */
    public List<Integer> findSubstring_bruteforce(String s, String[] words) {
        List<Integer> result = new ArrayList<>();

        if (s == null || s.isEmpty() || words == null || words.length == 0 || s.length() < words[0].length()) {
            return result;
        }

        Map<String, Integer> wordCount = new HashMap<>();
        for (String word : words) {
            wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
        }

        int len = words[0].length();
        for (int i = 0; i <= s.length() - words.length * len; i++) {
            Map<String, Integer> currCount = new HashMap<>(wordCount);
            for (int j = 0; j < words.length; j++) {
                String word = s.substring(i + j * len, i + j * len + len);
                if (currCount.get(word) != null) {
                    Integer count = currCount.get(word);
                    if (count > 1) {
                        currCount.put(word, count - 1);
                    } else {
                        currCount.remove(word);
                    }
                    if (currCount.isEmpty())
                        result.add(i);
                } else {
                    // advance i by 1 if no match
                    break;
                }
            }
        }

        return result;
    }

    /*
     * travel all the words combinations to maintain a window there are wl(word
     * len) times travel each time, n/wl words, mostly 2 times travel for each
     * word one left side of the window, the other right side of the window so,
     * time complexity O(wl * 2 * N/wl) = O(2N)
     */
    public List<Integer> findSubstring_slidingWindow(String s, String[] words) {
        List<Integer> result = new ArrayList<>();

        if (s == null || s.isEmpty() || words == null || words.length == 0 || s.length() < words[0].length()) {
            return result;
        }

        Map<String, Integer> wordCountMap = new HashMap<>();
        for (String word : words) {
            wordCountMap.put(word, wordCountMap.getOrDefault(word, 0) + 1);
        }

        int wordLen = words[0].length();
        Map<String, Integer> currentCountMap = new HashMap<>();
        for (int i = 0; i < wordLen; i++) { // wordLen times of travel
            int wordCount = 0, currentCount = 0;
            for (int left = i, right = i; right <= s.length() - wordLen; right = right + wordLen) {
                String word = s.substring(right, right + wordLen);
                if (wordCountMap.containsKey(word)) {
                    currentCountMap.put(word, currentCountMap.getOrDefault(word, 0) + 1);
                    if (currentCountMap.get(word) <= wordCountMap.get(word)) {
                        wordCount++;
                    }
                }
                currentCount++;

                if (wordCount == words.length) {
                    result.add(left);
                }

                if (currentCount == words.length) {
                    String leftWord = s.substring(left, left + wordLen);
                    if (wordCountMap.containsKey(leftWord)) {
                        if (currentCountMap.get(leftWord) <= wordCountMap.get(leftWord)) {
                            // do the same check as the increment
                            wordCount--;
                        }
                        currentCountMap.put(leftWord, currentCountMap.get(leftWord) - 1);
                    }
                    left += wordLen;
                    currentCount--;
                }
            }
            currentCountMap.clear();
        }

        return result;
    }

    public static void main(String[] args) {
        ConcatenationOfAllWords c = new ConcatenationOfAllWords();

        // String s = "barfoofoobarthefoobarman";
        // String[] words = {"bar","foo","the"};

        // String s = "barfoothefoobarman";
        // String[] words = {"bar","foo"};

        String s = "wordgoodgoodgoodbestword";
        String[] words = { "word", "good", "best", "good" };

        // String s = "lingmindraboofooowingdingbarrwingmonkeypoundcake";
        // String[] words = {"fooo","barr","wing","ding","wing"};
        System.out.println(c.findSubstring_slidingWindow(s, words));
    }

}
