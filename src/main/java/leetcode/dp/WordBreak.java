package leetcode.dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WordBreak {

    /**
     * LEETCODE 139
     * Given a non-empty string s and a dictionary wordDict containing a list of
     * non-empty words, determine if s can be segmented into a space-separated
     * sequence of one or more dictionary words. You may assume the dictionary
     * does not contain duplicate words. 
     * For example, given s = "leetcode", dict = ["leet", "code"]. 
     * Return true because "leetcode" can be segmented as "leet code".
     * 
     * Company: Google, Facebook, Uber, Yahoo, Amazon, Bloomberg, Pocket Gems, 
     * Square, Coupang
     * Difficulty: medium
     * Similar Questions: 140(Word Break II)
     */
    public boolean wordBreak_recursive(String s, List<String> wordDict) {
        if (s == null || s.length() == 0)
            return true;

        Set<String> dictSet = wordDict.stream().collect(Collectors.toSet());
        return wordBreak_recursive_helper(s, dictSet);
    }

    /*
     * Consider each prefix and search it in dictionary. If the prefix is
     * present in dictionary, recur for rest of the string (or suffix). If the
     * recursive call for suffix returns true, we return true, otherwise try
     * next prefix. If we have tried all prefixes and none of them resulted in a
     * solution, we return false.
     * Time complexity: O(n^n)
     */
    public boolean wordBreak_recursive_helper(String s, Set<String> dictSet) {
        if (dictSet.contains(s))
            return true;

        for (int i = 1; i < s.length(); i++) {
            String prefix = s.substring(0, i);
            boolean checkSuffix = wordBreak_recursive_helper(s.substring(i, s.length()), dictSet);
            if (dictSet.contains(prefix) && checkSuffix)
                return true;
        }

        return false;
    }

    // Time complexity: O(n^2), Space complexity: O(n)
    public boolean wordBreak_dp(String s, List<String> wordDict) {
        if (s == null || s.length() == 0)
            return true;

        int n = s.length();

        // dp[i] stands for whether substring(0, i) can be segmented
        boolean[] dp = new boolean[n + 1];

        // dp[0] is for empty string which should return true
        dp[0] = true;

        // consider substring of all possible lengths starting from the
        // beginning using index i
        // for each such substring partition it into two substring s1 and s2 in
        // all possible ways using index j
        for (int i = 1; i <= n; i++) { // i refers to the length of the
                                       // substring considered so far from the
                                       // beginning, end index of s2
            for (int j = 0; j < i; j++) { // j refers to the index partitioning
                                          // the current substring into smaller
                                          // substring (0,j) and (j+1,i)
                if (dp[j] && wordDict.contains(s.substring(j, i))) {
                    dp[i] = true; // both substring are in the dictionary
                    break;
                }
            }
        }
        return dp[n];
    }
    

    /**
     * LEETCODE 140
     * Given a non-empty string s and a dictionary wordDict containing a list of
     * non-empty words, add spaces in s to construct a sentence where each word
     * is a valid dictionary word. You may assume the dictionary does not
     * contain duplicate words. Return all such possible sentences. For example,
     * given s = "catsanddog", dict = ["cat", "cats", "and", "sand", "dog"]. A
     * solution is ["cats and dog", "cat sand dog"].
     * 
     * Company: Dropbox, Google, Uber, Snapchat, Twitter 
     * Difficulty: hard
     * Similar Questions: 139(Word Break), 472(ConcatenatedWords)
     */
    public List<String> wordBreakII_dpBacktrack(String s, List<String> wordDict) {
        List<String> result = new ArrayList<>();

        if (s == null || s.length() == 0)
            return result;

        int n = s.length();
        List<String>[] dp = (List<String>[]) new List[n + 1];
        dp[0] = new ArrayList<>();

        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                String substr = s.substring(j, i);
                if (dp[j] != null && wordDict.contains(substr)) {
                    if (dp[i] == null)
                        dp[i] = new ArrayList<String>();
                    dp[i].add(substr);
                }
            }
        }

        if (dp[n] == null) return result;
        for (List<String> list : dp ) System.out.println(list); 
        constructResult(dp, s.length(), result, new ArrayList<>());
        return result;
    }
   
    private void constructResult(List<String>[] dp, int end, List<String> result, List<String> list) {
        if (end <= 0) {
            // we cannot modify list here as it will be used in the backtracking
            // since we start from the end of dp, list is populated with the from 
            // the last word to the first, first is at the end of the list
            // construct the result starting from the end
            String str = list.get(list.size() - 1);
            for (int i = list.size() - 2; i >= 0; i--) {
                str += " " + list.get(i);
            }
            result.add(str);
            return;
        }

        for (String last : dp[end]) {
            list.add(last);
            constructResult(dp, end - last.length(), result, list);
            list.remove(list.size() - 1);
        }
    }

    // check every possible prefix of the string s. If it is found in wordDict
    public List<String> wordBreakII_bruteforce(String s, List<String> wordDict) {
        return wordBreakII_bruteforce(s, wordDict, 0);
    }

    private List<String> wordBreakII_bruteforce(String s, List<String> wordDict, int start) {
        List<String> result = new ArrayList<>();
        if (start == s.length()) {
            result.add("");
        }

        for (int end = start + 1; end <= s.length(); end++) {
            String prefix = s.substring(start, end);
            if (wordDict.contains(prefix)) {
                List<String> list = wordBreakII_bruteforce(s, wordDict, end);
                for (String l : list) {
                    result.add(prefix + (l.equals("") ? "" : " ") + l);
                }
            }
        }
        return result;
    }

    // key is the current start index of the string currently considered
    Map<Integer, List<String>> map = new HashMap<>(); 
    public List<String> wordBreakII_memorization(String s, List<String> wordDict) {
        return wordBreakII_memorization(s, wordDict, 0);
    }



    // Time complexity: O(n^3). Size of recursion tree can go up to n^2​​. The creation of list takes n time.
    // Space complexity: O(n^3).The depth of the recursion tree can go up to n and each activation record 
    // can contains a string list of size n.
    private List<String> wordBreakII_memorization(String s, List<String> wordDict, int start) {
        if (map.containsKey(start))
            return map.get(start);

        List<String> result = new ArrayList<>();
        if (start == s.length()) {
            // need to add an empty string to list
            result.add("");
            return result;
        }

        for (int end = start + 1; end <= s.length(); end++) { // need to use <= here as end is exclusive
            String prefix = s.substring(start, end);
            if (wordDict.contains(prefix)) {
                List<String> list = wordBreakII_memorization(s, wordDict, end);
                for (String l : list) {
                    result.add(prefix + (l.equals("") ? "" : " ") + l); // need to use equals to compare
                }
            }
        }
        map.put(start, result);
        return result;
    }

    public static void main(String[] arg) {
        WordBreak w = new WordBreak();

        //String s = "cata";
        String s = "catsanddog";
        List<String> wordDict = Arrays.asList("cat", "cats", "and", "sand", "dog");

        // String s = "aaaaaaa";
        // List<String> wordDict = Arrays.asList("aaaa","aa");

        // String s = "a";
        // List<String> wordDict = Arrays.asList("a");

        List<String> result = w.wordBreakII_dpBacktrack(s, wordDict);
        System.out.println(result);
    }
}
