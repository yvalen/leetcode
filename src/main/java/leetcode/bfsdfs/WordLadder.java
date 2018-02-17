package leetcode.bfsdfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class WordLadder {
    /*
     * LEETCODE 127
     * Given two words (beginWord and endWord), and a dictionary's word list,
     * find the length of shortest transformation sequence from beginWord to
     * endWord, such that: 
     * - Only one letter can be changed at a time. 
     * - Each transformed word must exist in the word list. Note that beginWord 
     * is not a transformed word. 
     * For example, given beginWord = "hit" endWord = "cog"
     * wordList = ["hot","dot","dog","lot","log","cog"] 
     * As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog", 
     * return its length 5. 
     * Note: 
     * - Return 0 if there is no such transformation sequence. 
     * - All words have the same length. 
     * - All words contain only lower case alphabetic characters. 
     * - You may assume no duplicates in the word list. 
     * - You may assume beginWord and endWord are non-empty and are not the same.
     * 
     * Company: Facebook, Amazon, LinkedIn, Yelp, Snapchat
     * Difficulty: medium
     * Similar Questions: 126(Word Ladder II), 433
     */
    // Time complexity: O(nl), l - word length, n - number of words in the list
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> dict = new HashSet<>(wordList);
        
        // keep track of words that have been checked
        //HashSet<String> visited = new HashSet<>(); 
        
        Queue<String> q = new LinkedList<>();
        q.offer(beginWord);
        // start from 1 as we return right after we find endWord
        // at that point, endWors is on the next level
        int len = 1; 
        while (!q.isEmpty()) {
            len++;
            int qlen = q.size(); // need to consume all words at the same level
            for (int i = 0; i < qlen; i++) {
                String s = q.poll();
                Set<String> nextWords = getNextWords(s, dict);
                for (String word : nextWords) {
                    if (word.equals(endWord)) {
                        return len;
                    }
                    q.offer(word);
                    // remove word from dict to avoid using visited at all
                    dict.remove(word);
                }
            }
        }

        return 0;
    }

    // return all words in dictionary that differs from s by one character and
    // hasn't been visited
    // l = s.length O(l*26) -> O(l)
    private Set<String> getNextWords(String s, Set<String> dict) {
        Set<String> nextWords = new HashSet<>();
        char[] chars = s.toCharArray();
        for (int i = 0; i < s.length(); i++) {
            char c = chars[i];
            for (char j = 'a'; j <= 'z'; j++) { 
                // test all possible chars for position i
                // no need to worry about the same word as it will be removed 
                // from dict after being enqueued
                chars[i] = j; 
                String str = new String(chars);
                if (dict.contains(str)) {
                    // if (dict.contains(str) && !visited.contains(str)) {
                    nextWords.add(str);
                }
            }
            chars[i] = c; // restore string
        }
        return nextWords;
    }

    /*
     * LEETCODE 126
     * Given two words (beginWord and endWord), and a dictionary's word list,
     * find all shortest transformation sequence(s) from beginWord to endWord,
     * such that 
     * - Only one letter can be changed at a time 
     * - Each transformed word must exist in the word list. 
     * Note that beginWord is not a transformed word. 
     * For example, given beginWord = "hit" endWord = "cog"
     * wordList = ["hot","dot","dog","lot","log","cog"] 
     * Return [["hit","hot","dot","dog","cog"], ["hit","hot","lot","log","cog"] ]
     * Note:
     *  - Return an empty list if there is no such transformation sequence.
     *  - All words have the same length.
     *  - All words contain only lower case alphabetic characters.
     *  - You may assume no duplicates in the word list.
     *  - You may assume beginWord and endWord are non-empty and are not the same.
     * 
     * Company: Amazon, Yelp
     * Difficulty: hard
     * Similar Questions: 127(Word Ladder)
     */
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> result = new ArrayList<>();

        Set<String> dict = new HashSet<>(wordList);

        // distance from the starting word
        Map<String, Integer> distanceMap = new HashMap<>(); 
        distanceMap.put(beginWord, 0);

        Map<String, List<String>> neighborMap = new HashMap<>();

        // populate the shortest path graph
        bfs(beginWord, endWord, dict, neighborMap, distanceMap);
        System.out.println(neighborMap);
        System.out.println(distanceMap);

        // output shortest paths
        dfs(beginWord, endWord, neighborMap, distanceMap, result, new LinkedList<>());

        return result;
    }

    // use bfs to trace every node's distance from the start node (level by level).
    // with BFS we can be sure that the distance of each node is the shortest one , 
    // because once we have visited a node, we update the distance , if we first met one node ,
    // it must be the shortest distance. if we met the node again ,its distance must not be less 
    // than the distance we first met and set.
    private void bfs(String beginWord, String endWord, Set<String> dict, 
            Map<String, List<String>> neighborMap, Map<String, Integer> distanceMap) {
        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);
        while (!queue.isEmpty()) {
            int size = queue.size();
            boolean found = false;
            while (size-- > 0) {
                String word = queue.poll();
                //neighborMap.putIfAbsent(word, new ArrayList<>());
                int currentDistance = distanceMap.get(word);
                List<String> neighbors = getNeighbors(word, dict);
                neighborMap.put(word, neighbors);
                for (String neighbor : neighbors) {
                    //neighborMap.get(word).add(neighbor); // add neighbor

                    // skip visited neighbor
                    if (!distanceMap.containsKey(neighbor)) {
                        // add distance
                        distanceMap.put(neighbor, currentDistance + 1);
                        if (neighbor.equals(endWord)) {
                            found = true;
                        } else {
                            // shouldn't remove neighbor from dict as we need to all combination
                            queue.offer(neighbor);
                        }
                    }
                }
            }

            if (found) {
                // shortest path found, no need to go to the next level
                break;
            }
        }
    }

    private void dfs(String beginWord, String endWord, Map<String, List<String>> neighborMap,
            Map<String, Integer> distanceMap, List<List<String>> result, LinkedList<String> wordList) {
        // add beginWord to list here so that the last word will be added to backtracked properly
        wordList.add(beginWord);
        if (beginWord.equals(endWord)) {
            result.add(new ArrayList<>(wordList));
            // cannot return from here because we need to do backtrack on wordList
        } else {
            List<String> neighbors = neighborMap.get(beginWord);
            // need to do null check because the graph is not fully populated
            if (neighbors != null) {
                for (String neighbor : neighbors) {
                    if (distanceMap.get(neighbor) == distanceMap.get(beginWord) + 1) {
                        dfs(neighbor, endWord, neighborMap, distanceMap, result, wordList);
                    }
                }
            }
        }
        wordList.removeLast();
    }

    // get all neighbors of s, neighbor of s differs from s by one character and
    // is in dict
    private List<String> getNeighbors(String s, Set<String> dict) {
        List<String> neighbors = new ArrayList<>();
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char original = chars[i];
            for (char c = 'a'; c <= 'z'; c++) {
                chars[i] = c;
                if (c == original) continue;
                String neighbor = new String(chars);
                if (dict.contains(neighbor)) {
                    neighbors.add(neighbor);
                }
            }
            System.out.println("i=" + i + " neighbors=" + neighbors);
            chars[i] = original;
        }
        

        return neighbors;
    }

    public static void main(String[] args) {
        WordLadder w = new WordLadder();
        String beginWord = "hit", endWord = "cog";
        List<String> wordList = Arrays.asList("hot", "dot", "dog", "lot", "log", "cog");
        // List<String> wordList = Arrays.asList("hot","dot","dog","lot","log");
        // System.out.println(w.ladderLength(beginWord, endWord, wordList));

        // String beginWord = "a", endWord = "c";
        // List<String> wordList = Arrays.asList("a","b","c");
        //System.out.println(w.findLadders(beginWord, endWord, wordList));

        System.out.println(w.getNeighbors("lot", new HashSet<>(wordList)));
        
    }

}
