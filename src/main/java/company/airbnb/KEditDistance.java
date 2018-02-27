package company.airbnb;

import java.util.ArrayList;
import java.util.List;

/*
 * Find all words from a dictionary that are k edit distance away.
 * http://stevehanov.ca/blog/index.php?id=114
 */
public class KEditDistance {
    
    public List<String> getKEditDistance(String[] words, String target, int k) {
        TrieNode root = new TrieNode();
        for (String word : words) {
            insert(root, word);
        }
        
        int[] prevDist = new int[target.length()+1];
        for (int i = 0; i < prevDist.length; i++) {
            prevDist[i] = i;
        }
       
        List<String> result = new ArrayList<>();
        search("", target, root, k, prevDist, result);
        return result;
    }
    
    private void search(String current, String target, TrieNode node, int k, int[] prevDist, List<String> result) {
        if (node.isWord) {
            if (prevDist[target.length()] <= k) {
                result.add(current);
            }
            else {
                // distance is longer than k, stop processing
                return;
            }
        }
        
        for (int i = 0; i < 26; i++) {
            if (node.next[i] == null) continue; 
            char c = (char)(i + 'a');
            int[] currentDist = new int[target.length()+1];
            currentDist[0] = target.length()+1;
            for (int j = 1; j <= target.length(); j++) {
                if (target.charAt(j-1) == c) {
                    currentDist[j] = prevDist[j-1];
                }
                else {
                    currentDist[j] = Math.min(currentDist[j-1], Math.min(prevDist[j], prevDist[j-1])) + 1;
                }
            }
            search(current+c, target, node.next[i], k, currentDist, result);
        }
    }

    private static class TrieNode {
        private boolean isWord;
        private TrieNode[] next = new TrieNode[26];
    }

    private void insert(TrieNode root, String word) {
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            if (node.next[c-'a'] == null) {
                node.next[c-'a'] = new TrieNode();
            }
            node = node.next[c-'a'];
        }
        node.isWord = true;
    }
   
    public static void main(String[] args) {
        KEditDistance ked = new KEditDistance();
        String[] words = {"abc", "abd", "abcd", "adc"};
        String target = "ac";
        int k = 1;
        System.out.println(ked.getKEditDistance(words, target, k));
    }
}
