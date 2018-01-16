package leetcode.trie;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import leetcode.backtracking.WordSearch;

/*
 * LEETCODE 212
 * Given a 2D board and a list of words from the dictionary, find all words in the board. 
 * Each word must be constructed from letters of sequentially adjacent cell, where "adjacent" 
 * cells are those horizontally or vertically neighboring. The same letter cell may not be used 
 * more than once in a word. 
 * For example, 
 * given words = ["oath","pea","eat","rain"] and board =
 * [
 * 	['o','a','a','n'],
 * 	['e','t','a','e'],
 * 	['i','h','k','r'],
 * 	['i','f','l','v']
 * ]
 * Return ["eat","oath"].
 * Note: You may assume that all inputs are consist of lower case letters a-z. 
 * 
 * Company: Microsoft, Google, Airbnb
 * Difficulty: hard
 * Similar Questions: 79(WordSearch)
 */
public class WordSearchII {
    // DFS + backtrack + trie
    private static class TrieNode {
        private String word; // trie node stores the word
        private TrieNode[] next = new TrieNode[26];
    }

    private TrieNode buildTrie(String[] words) {
        TrieNode root = new TrieNode();
        for (String word : words) {
            TrieNode node = root; // one node per word starting from root
            for (char c : word.toCharArray()) {
                int idx = c - 'a';
                if (node.next[idx] == null)
                    node.next[idx] = new TrieNode();
                node = node.next[idx];
            }
            node.word = word; // store the word in the last node
        }
        return root;
    }

    public List<String> findWords(char[][] board, String[] words) {
        if (board == null || board.length == 0 || board[0].length == 0 || words == null || words.length == 0) {
            return Collections.emptyList();
        }

        TrieNode trie = buildTrie(words);

        List<String> result = new ArrayList<>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                dfs(board, i, j, trie, result);
            }
        }
        return result;
    }

    private static final int[][] OFFSETS = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

    private void dfs(char[][] board, int row, int col, TrieNode node, List<String> result) {
        if (row < 0 || row == board.length || col < 0 || col == board[0].length)
            return;

        if (board[row][col] == '#')
            return; // cell is visited

        char c = board[row][col];
        if (node.next[c - 'a'] == null)
            return; // stop current dfs if no word match

        board[row][col] = '#'; // mark the cell as visited, same letter can only
                               // be used once in a word

        node = node.next[c - 'a'];
        if (node != null && node.word != null) {
            result.add(node.word);
            node.word = null; // de-dup
        }

        // dfs
        for (int[] offset : OFFSETS) {
            int i = row + offset[0], j = col + offset[1];
            dfs(board, i, j, node, result);
        }

        board[row][col] = c; // backtrack, restore the cell value
    }
    
    public static void main(String[] args) {
        WordSearchII w = new WordSearchII();
        char[][] board = {
                { 'o', 'a', 'a', 'n' }, 
                { 'e', 't', 'a', 'e' }, 
                { 'i', 'h', 'k', 'r' },
                { 'i', 'f', 'l', 'v' } 
                };
        String[] words = { "oath", "pea", "eat", "rain" };
        System.out.println(w.findWords(board, words));
      
    }
}
