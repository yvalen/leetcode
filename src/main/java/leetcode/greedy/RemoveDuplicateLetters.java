package leetcode.greedy;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

/*
 * Given a string which contains only lower case letters, remove duplicate letters so that every letter appear once and only once. 
 * You must make sure your result is the smallest in lexicographical order among all possible results.
 * Example:
 * Given "bcabc" Return "abc"
 * Given "cbacdcbc" Return "acdb"
 * 
 * Company: Google
 * Difficulty: hard
 */
public class RemoveDuplicateLetters {
    // The basic idea is to find out the smallest result letter by letter (one
    // letter at a time). Here is the thinking process for input "cbacdcbc":
    // 1. find out the last appeared position for each letter
    // c - 7
    // b - 6
    // a - 2
    // d - 4
    // 2. find out the smallest index from the map in step 1 (a - 2)
    // 3. the first letter in the final result must be the smallest letter from
    // index 0 to index 2;
    // 4. repeat step 2 to 3 to find out remaining letters.
    // the smallest letter from index 0 to index 2: a
    // the smallest letter from index 3 to index 4: c
    // the smallest letter from index 4 to index 4: d
    // the smallest letter from index 5 to index 6: b
    // so the result is "acdb"
    // Notes:
    // - after one letter is determined in step 3, it need to be removed from
    // the "last appeared position map",
    // and the same letter should be ignored in the following steps
    // in step 3, the beginning index of the search range should be the index of
    // previous determined letter plus one
    public String removeDuplicateLetters_withMap(String s) {
        if (s == null || s.length() <= 1)
            return s;
        int n = s.length();

        // find out the last position for each letter
        Map<Character, Integer> indexMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            indexMap.put(s.charAt(i), i);
        }

        // result size is the number of unique characters
        char[] result = new char[indexMap.size()];
        int start = 0, end = getMinIndex(indexMap);

        for (int i = 0; i < result.length; i++) {
            char minChar = 'z' + 1;
            for (int j = start; j <= end; j++) {
                char c = s.charAt(j);
                if (indexMap.containsKey(c) && c < minChar) { // only need to
                                                              // consider char
                                                              // still in the
                                                              // map as the ones
                                                              // that have been
                                                              // processed are
                                                              // removed from
                                                              // the map
                    minChar = c;
                    start = j + 1; // update start position for the next letter
                }
            }
            result[i] = minChar;
            indexMap.remove(minChar); // remove the processed char from map

            if (s.charAt(end) == minChar)
                end = getMinIndex(indexMap);
        }

        return new String(result);
    }

    private int getMinIndex(Map<Character, Integer> indexMap) {
        if (indexMap.isEmpty())
            return -1;
        int minIndex = Integer.MAX_VALUE;
        for (int idx : indexMap.values()) {
            minIndex = Math.min(minIndex, idx);
        }
        return minIndex;
    }

    //
    // Use Stack
    //
    public String removeDuplicateLetters_withStack(String s) {
        if (s == null || s.length() <= 1)
            return s;
        char[] charArray = s.toCharArray();

        int[] occurrenceCount = new int[26]; // number of times the char appears
                                             // in the string
        for (char c : charArray) {
            occurrenceCount[c - 'a']++;
        }

        Deque<Character> deque = new ArrayDeque<>();
        boolean[] visited = new boolean[26]; // whether the char has been
                                             // processed - in the stack
        for (char c : charArray) {
            int idx = c - 'a';
            occurrenceCount[idx]--; // decrement the occurrence count
            if (visited[idx])
                continue; // skip the char that's already in stack

            // current char is smaller than the top of the stack and the top of
            // stack exists in s in a later position
            // the top element of the stack can be removed and added later
            while (!deque.isEmpty() && c < deque.peekLast() && occurrenceCount[deque.peekLast() - 'a'] > 0) {
                visited[deque.pollLast() - 'a'] = false; // pop the larger char
                                                         // and set the visited
                                                         // flag to false so
                                                         // that it can be
                                                         // processed later
            }
            deque.offerLast(c);
            visited[idx] = true;
        }

        StringBuilder sb = new StringBuilder();
        while (!deque.isEmpty()) {
            sb.append(deque.pollFirst());
        }
        return sb.toString();
    }

    //
    // Greedy
    //
    // Given the string s, the greedy choice (i.e., the leftmost letter in the
    // answer) is the smallest s[i], such that
    // the suffix s[i .. ] contains all the unique letters. (Note that, when
    // there are more than one smallest s[i]'s,
    // we choose the leftmost one. Why? Simply consider the example: "abcacb".)
    // After determining the greedy choice s[i], we get a new string s' from s
    // by
    // - removing all letters to the left of s[i],
    // - removing all s[i]'s from s.
    // We then recursively solve the problem w.r.t. s'.
    // The runtime is O(26 * n) = O(n).
    public String removeDuplicateLetters_greedy(String s) {
        if (s == null || s.length() <= 1)
            return s;

        int[] count = new int[26];
        for (char c : s.toCharArray())
            count[c - 'a']++;

        int pos = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c < s.charAt(pos))
                pos = i;
            if (--count[c - 'a'] == 0)
                break;
        }

        return s.charAt(pos) + removeDuplicateLetters_greedy(s.substring(pos + 1).replaceAll(s.charAt(pos) + "", ""));
    }

    public static void main(String[] args) {
        RemoveDuplicateLetters rdl = new RemoveDuplicateLetters();
        // String s = "bcabc";
        // String s = "cbacdcbc";
        // String s = "cdcbc";
        String s = "lllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooopppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssstttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaabbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbcccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccdddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeefffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggghhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiijjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkklllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmm";
        System.out.println(rdl.removeDuplicateLetters_greedy(s));
    }
}
