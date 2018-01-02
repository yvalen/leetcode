package leetcode.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * LEETCODE 267
 * Given a string s, return all the palindromic permutations (without duplicates) of it. 
 * Return an empty list if no palindromic permutation could be form.
 * For example:
 * Given s = "aabb", return ["abba", "baab"].
 * Given s = "abc", return []. 
 * 
 * Dififculty: medium
 * Similar Questions: 31(NextPermutation), 47(Permutations II), 266(PalindromePermutation)
 */
public class PalindromePermutationII {
    public List<String> generatePalindromes(String s) {
        if (s == null || s.isEmpty())
            return Collections.emptyList();

        // build character count map and calculate odd count
        int oddCount = 0;
        Map<Character, Integer> countMap = new HashMap<>();
        for (Character c : s.toCharArray()) {
            countMap.put(c, countMap.getOrDefault(c, 0) + 1);
            // need to decrement by one when even to offset the increment in the previous count
            oddCount += countMap.get(c) % 2 != 0 ? 1 : -1; 
        }

        // palindrome can only have 1 odd count
        if (oddCount > 1)
            return Collections.emptyList();

        // characters used to construct the palindrome
        List<Character> charList = new ArrayList<>();
        String mid = "";
        for (Map.Entry<Character, Integer> entry : countMap.entrySet()) {
            int count = entry.getValue();
            if (count % 2 != 0) mid = String.valueOf(entry.getKey());
            // only need to use half of the string
            for (int i = 0; i < count / 2; i++) {
                charList.add(entry.getKey());
            }
        }

        List<String> result = new ArrayList<>();
        helper(charList, result, mid, new StringBuilder(), new boolean[s.length()]);
        return result;
    }

    private void helper(List<Character> charList, List<String> result, String mid, StringBuilder sb, boolean[] used) {
        if (sb.length() == charList.size()) {
            result.add(sb.toString() + mid + sb.reverse().toString());
            // need to restore the order of sb to contiue with backtracking
            // it is reversed when constructing the string added to result
            sb.reverse(); 
            return;
        }

        for (int i = 0; i < charList.size(); i++) {
            if (used[i]) continue;

            // prevent duplicate palindrome to be generated from repeated characters
            // check !used[i-1] is faster than check used[i-1]
            if (i > 0 && charList.get(i) == charList.get(i - 1) && !used[i - 1]) {
                continue; 
            }
            
            used[i] = true;
            sb.append(charList.get(i));
            // System.out.println(sb.toString());
            helper(charList, result, mid, sb, used);
            sb.deleteCharAt(sb.length() - 1);
            used[i] = false;
        }
    }

    public static void main(String[] args) {
        PalindromePermutationII pp = new PalindromePermutationII();
        // String s = "aabb";
        // String s = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
        //String s = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
        String s = "aabbcc";
        System.out.println(pp.generatePalindromes(s));
    }
}
