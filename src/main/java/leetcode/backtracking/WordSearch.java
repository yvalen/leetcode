package leetcode.backtracking;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WordSearch {
    /*
     * Given a 2D board and a word, find if the word exists in the grid. The
     * word can be constructed from letters of sequentially adjacent cell, where
     * "adjacent" cells are those horizontally or vertically neighboring. The
     * same letter cell may not be used more than once. For example, given board
     * [ ['A','B','C','E'], ['S','F','C','S'], ['A','D','E','E'] ] word =
     * "ABCCED", -> returns true, word = "SEE", -> returns true, word = "ABCB",
     * -> returns false.
     */
    public boolean exist(char[][] board, String word) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (exist_helper(board, word, i, j, 0))
                    return true;
            }
        }
        return false;
    }

    private boolean exist_helper(char[][] board, String word, int row, int col, int index) {
        if (index == word.length())
            return true;

        if (row < 0 || col < 0 || row == board.length || col == board[0].length)
            return false;

        if (board[row][col] != word.charAt(index))
            return false;

        // apply bit mask for visited cell
        board[row][col] ^= 256;
        if (exist_helper(board, word, row - 1, col, index + 1) || exist_helper(board, word, row + 1, col, index + 1)
                || exist_helper(board, word, row, col - 1, index + 1)
                || exist_helper(board, word, row, col + 1, index + 1)) {
            return true;
        }
        board[row][col] ^= 256;
        return false;
    }

    /*
     * Given a 2D board and a list of words from the dictionary, find all words
     * in the board. Each word must be constructed from letters of sequentially
     * adjacent cell, where "adjacent" cells are those horizontally or
     * vertically neighboring. The same letter cell may not be used more than
     * once in a word. For example, given words = ["oath","pea","eat","rain"]
     * and board = [ ['o','a','a','n'], ['e','t','a','e'], ['i','h','k','r'],
     * ['i','f','l','v'] ] Return ["eat","oath"]. Note: You may assume that all
     * inputs are consist of lower case letters a-z.
     */
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
                findWords_helper(board, i, j, trie, result);
            }
        }
        return result;
    }

    private static final int[][] OFFSETS = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

    private void findWords_helper(char[][] board, int row, int col, TrieNode node, List<String> result) {
        if (row < 0 || col < 0 || row == board.length || col == board[0].length)
            return;

        if (board[row][col] == '#')
            return; // cell is visited

        char c = board[row][col];
        if (node.next[c - 'a'] == null)
            return; // stop current dfs if no word match

        node = node.next[c - 'a'];
        if (node.word != null) {
            result.add(node.word); // found a match, add to result
            node.word = null; // de-dup
        }

        board[row][col] = '#'; // mark the cell as visited

        // dfs
        for (int[] offset : OFFSETS) {
            int i = row + offset[0];
            int j = col + offset[1];
            findWords_helper(board, i, j, node, result);
        }

        board[row][col] = c; // backtrack, restore cell
    }

    public static void main(String[] args) {
        WordSearch w = new WordSearch();

        /*
         * char[][] board = { {'A','B','C','E'}, {'S','F','C','S'},
         * {'A','D','E','E'} }; String word = "ABCB";
         * System.out.println(w.exist(board, word));
         */

        char[][] board = { { 'o', 'a', 'a', 'n' }, { 'e', 't', 'a', 'e' }, { 'i', 'h', 'k', 'r' },
                { 'i', 'f', 'l', 'v' } };
        String[] words = { "oath", "pea", "eat", "rain" };
        System.out.println(w.findWords(board, words));
    }
}
