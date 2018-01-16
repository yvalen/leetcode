package leetcode.backtracking;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
 * LEETCODE 79
 * Given a 2D board and a word, find if the word exists in the grid. 
 * The word can be constructed from letters of sequentially adjacent cell, 
 * where "adjacent" cells are those horizontally or vertically neighboring. 
 * The same letter cell may not be used more than once. 
 * For example, given board
 * [ 
 * ['A','B','C','E'], 
 * ['S','F','C','S'], 
 * ['A','D','E','E'] 
 * ] 
 * word = "ABCCED", -> returns true, 
 * word = "SEE", -> returns true, 
 * word = "ABCB", -> returns false.
 * 
 * Company: Facebook, Microsoft, Bloomberg
 * Difficulty: medium
 * Similar Questions: 212(WordSearchII) 
 */
public class WordSearch {
    private static final int[][] OFFSETS = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
   
    public boolean exist(char[][] board, String word) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (dfs(board, word, i, j, 0))
                    return true;
            }
        }
        return false;
    }

    private boolean dfs(char[][] board, String word, int row, int col, int index) {
        if (index == word.length())
            return true;

        if (row < 0 || row >= board.length || col < 0 || col>= board[0].length) {
            // need to do the boundary check here 
            return false;
        }
        
        if (board[row][col] != word.charAt(index))
            return false;

        // apply bit mask for visited cell
        board[row][col] ^= 256;
        for (int[] offset : OFFSETS) {
            int x = row + offset[0], y = col + offset[1];
            // cannot do boundary check here as we use index to check for matching
            if (dfs(board, word, x, y, index+1)) return true;
        }
        board[row][col] ^= 256;
        return false;  
    }

    public static void main(String[] args) {
        WordSearch w = new WordSearch();
        /*
        char[][] board = {
                {'a'}
        };
        String word = "a";
        */
        char[][] board = {
                {'A','B','C','E'}, 
                {'S','F','C','S'},
                {'A','D','E','E'} 
        };
        String word = "ABCCED";
        System.out.println(w.exist(board, word));
    }
}
