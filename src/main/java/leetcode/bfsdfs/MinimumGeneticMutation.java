package leetcode.bfsdfs;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
 * LEETCODE 433
 * A gene string can be represented by an 8-character long string, with choices from "A", "C", "G", "T".
 * Suppose we need to investigate about a mutation (mutation from "start" to "end"), where ONE mutation 
 * is defined as ONE single character changed in the gene string. For example, "AACCGGTT" -> "AACCGGTA" 
 * is 1 mutation. Also, there is a given gene "bank", which records all the valid gene mutations. A gene 
 * must be in the bank to make it a valid gene string.
 * Now, given 3 things - start, end, bank, your task is to determine what is the minimum number of mutations 
 * needed to mutate from "start" to "end". If there is no such a mutation, return -1.
 * Note:
 * - Starting point is assumed to be valid, so it might not be included in the bank.
 * - If multiple mutations are needed, all mutations during in the sequence must be valid.
 * - You may assume start and end string is not the same.
 * Example 1:
 * start: "AACCGGTT"
 * end:   "AACCGGTA"
 * bank: ["AACCGGTA"]
 * return: 1
 * Example 2:
 * start: "AACCGGTT"
 * end:   "AAACGGTA"
 * bank: ["AACCGGTA", "AACCGCTA", "AAACGGTA"]
 * return: 2
 * Example 3:
 * start: "AAAAACCC"
 * end:   "AACCCCCC"
 * bank: ["AAAACCCC", "AAACCCCC", "AACCCCCC"]
 * return: 3
 * 
 * Company: Twitter
 * Difficulty: medium
 * Similar Questions: 127(Word Ladder)
 */
public class MinimumGeneticMutation {
    private static final char[] CHARS = {'A', 'C', 'G', 'T'};
    
    public int minMutation(String start, String end, String[] bank) {
        if(start.equals(end)) return 0;
  
        Set<String> bankSet = Stream.of(bank).collect(Collectors.toSet());
        
        Queue<String> queue = new LinkedList<>();
        queue.offer(start);
        int len = 0;
        while (!queue.isEmpty()) {
            len++;
            int size = queue.size();
            while (size-- > 0) {
                String current = queue.poll();
                Set<String> nextMutations = getNextMutations(current, bankSet);
                for (String mutation : nextMutations) {
                    if (end.equals(mutation)) return len;
                    queue.offer(mutation);
                    bankSet.remove(mutation);
                }
            }
        }
        return -1;
    }

    private Set<String> getNextMutations(String s, Set<String> bankSet) {
        Set<String> nextMutations = new HashSet<>();
        char[] chars = s.toCharArray();
        for (int i = 0; i < 8; i++) {
            char t = chars[i];
            for (char c : CHARS) {
                if (c == t) continue;
                chars[i] = c;
                String str = new String(chars);
                if (bankSet.contains(str)) nextMutations.add(str);
                chars[i] = t;
            }
        }
        return nextMutations;
    }
    
    public static void main(String[] args) {
        MinimumGeneticMutation mgm = new MinimumGeneticMutation();
        //String start = "AACCGGTT", end =  "AACCGGTA";
        //String[] bank = {"AACCGGTA"};
        
        String start = "AACCGGTT", end =  "AAACGGTA";
        String[] bank = {"AACCGGTA", "AACCGCTA", "AAACGGTA"};
 
        System.out.println(mgm.minMutation(start, end, bank));
    }
}
