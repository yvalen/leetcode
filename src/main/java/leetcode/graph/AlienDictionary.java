package leetcode.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

/*
 * LEETCODE 269
 * There is a new alien language which uses the latin alphabet. However, the order among letters are unknown to you. 
 * You receive a list of non-empty words from the dictionary, where words are sorted lexicographically by the rules 
 * of this new language. Derive the order of letters in this language.
 * Example 1: given the following words in dictionary,
 * [
 * 		"wrt",
 * 		"wrf",
 * 		"er",
 * 		"ett",
 * 		"rftt"
 * ]
 * The correct order is: "wertf".
 * Example 2: given the following words in dictionary,
 * [
 * 		"z",
 * 		"x"
 * ]
 * The correct order is: "zx".
 * Example 3: given the following words in dictionary,
 * [
 * 		"z",
 * 		"x",
 * 		"z"
 * ]
 * The order is invalid, so return "".
 * Note:
 * - You may assume all letters are in lowercase.
 * - You may assume that if a is a prefix of b, then a must appear before b in the given dictionary.
 * - If the order is invalid, return an empty string.
 * - There may be multiple valid order of letters, return any one of them is fine.
 * 
 * Company: Google, Airbnb, Facebook, Twitter, Snapchat, Pocket Gems
 * Difficulty: hard
 * Similar Questions: 210(Course Schedule II)
 * 
 * Definition of lexicographic ordering: If two strings are different, then either they have different 
 * characters at some index that is a valid index for both strings, or their lengths are different, or both. 
 * If they have different characters at one or more index positions, let k be the smallest such index; then 
 * the string whose character at position k has the smaller value, as determined by using the < operator, 
 * lexicographically precedes the other string. In this case, compareTo returns the difference of the two 
 * character values at position k in the two string -- that is, the value: this.charAt(k)-anotherString.charAt(k)
 * If there is no index position at which they differ, then the shorter string lexicographically precedes the 
 * longer string. In this case, compareTo returns the difference of the lengths of the strings -- 
 * that is, the value: this.length()-anotherString.length()
 */
public class AlienDictionary {

    // Algorithm
    // https://courses.cs.washington.edu/courses/cse326/03wi/lectures/RaoLect20.pdf
    // 1. Identify edges that have no incoming edges. If no such edge, graph has cycles.
    // 2. Delete this vertex of in-degree 0 and all its outgoing edges. Place it in the output.
    // 3. Repeat step 1 and 2 until graph is empty
    public String alienOrder_indegree(String[] words) {
        if (words == null || words.length == 0)
            return "";

        // build a degree map for each character in all the words
        Map<Character, Integer> inDegrees = new HashMap<>();
        for (String word : words) {
            for (Character c : word.toCharArray()) {
                inDegrees.putIfAbsent(c, 0);
            }
        }

        // build the graph by comparing adjacent words, the first character that is
        // different between two adjacent words reflect the lexicographical order
        Map<Character, Set<Character>> graph = new HashMap<>();
        for (int i = 0; i < words.length - 1; i++) {
            String current = words[i], next = words[i + 1];
            if (current.length() > next.length() && current.startsWith(next)) {
                // invalid order because shorter string should precede longer one
                // lexicographically
                return "";
            }
            for (int j = 0; j < Math.min(current.length(), next.length()); j++) {
                if (current.charAt(j) != next.charAt(j)) {
                    Character c1 = current.charAt(j);
                    Character c2 = next.charAt(j);
                    graph.putIfAbsent(c1, new HashSet<>());
                    if (!graph.get(c1).contains(c2)) {
                        graph.get(c1).add(c2);
                        inDegrees.put(c2, inDegrees.get(c2) + 1);
                    }
                    // need to stop here because the order of the next characters 
                    // cannot be determined
                    break;
                }
            }
        }

        // bfs
        Queue<Character> queue = new LinkedList<>();
        for (Map.Entry<Character, Integer> entry : inDegrees.entrySet()) {
            if (entry.getValue() == 0)
                queue.offer(entry.getKey());
        }

        StringBuilder sb = new StringBuilder();
        while (!queue.isEmpty()) {
            Character current = queue.poll();
            sb.append(current);

            // some characters may not have any edges 
            if (!graph.containsKey(current))
                continue;

            for (Character c : graph.get(current)) {
                inDegrees.put(c, inDegrees.get(c) - 1);
                if (inDegrees.get(c) == 0)
                    queue.offer(c);
            }
        }

        return sb.length() == inDegrees.size() ? sb.toString() : "";
    }

    //
    // DFS
    // Time complexity: O(V+E)
    public String alienOrder_dfs(String[] words) {
        if (words == null || words.length == 0)
            return "";
        if (words.length == 1)
            return words[0]; // if there is one word, order can by any
                             // combination

        Map<Character, Set<Character>> graph = new HashMap<>();
        for (int i = 1; i < words.length; i++) {
            String prev = words[i - 1], current = words[i];
            // flag indicating whether we need to compare the two words
            boolean doCompare = true;
            for (int j = 0; j < Math.max(prev.length(), current.length()); j++) {
                // need to add all characters to graph
                if (j < prev.length()) graph.putIfAbsent(prev.charAt(j), new HashSet<>());
                if (j < current.length()) graph.putIfAbsent(current.charAt(j), new HashSet<>());
                if (j < prev.length() && j < current.length() && 
                        prev.charAt(j) != current.charAt(j) && doCompare) {
                    graph.get(prev.charAt(j)).add(current.charAt(j));
                    // don't compare any more since the order of the rest characters are undeterministic
                    doCompare = false;
                }
            }
        }
        System.out.println(graph);

        StringBuilder sb = new StringBuilder();
        boolean[] visited = new boolean[26];
        for (Character v : graph.keySet()) {
            if (!visited[v - 'a']) {
                if (!dfs(graph, v, sb, visited, new boolean[26]))
                    return "";
            }
        }
        return sb.reverse().toString();
    }

    private boolean dfs(Map<Character, Set<Character>> graph, Character v, StringBuilder sb, boolean[] visited,
            boolean[] onStack) {
        onStack[v - 'a'] = true;
        System.out.println("v=" + v + " adj=" + graph.get(v));
        if (graph.get(v) != null) {
            for (Character neighbor : graph.get(v)) {
                if (onStack[neighbor - 'a']) {
                    // need to check whether there is cycle first 
                    // if call dfs before this, will get into an infinite loop for z,x,z 
                    return false;
                }
                
                if (!visited[neighbor - 'a']) {
                    if (!dfs(graph, neighbor, sb, visited, onStack))
                        return false;
                } 
            }
        }
        sb.append(v);
        onStack[v - 'a'] = false;
        return true;
    }

    public static void main(String[] args) {
        AlienDictionary ad = new AlienDictionary();
        // String[] words = {"wrt", "wrf", "er", "ett", "rftt"};
        // String[] words = {"z", "x"};
        String[] words = {"z", "x", "z"};
        // String[] words = {"za","zb","ca","cb"}; // expect "abzc"
        // String[] words = {"z", "z"};
        // String[] words = {"wrt", "wrtkj"};
        //String[] words = { "wrtkj", "wrt" };
        // String[] words = {"ab", "adc"};
        // String[] words = {"wnlt"};
        System.out.println(ad.alienOrder_dfs(words));
    }

}
