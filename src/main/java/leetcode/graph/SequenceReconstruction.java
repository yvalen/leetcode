package leetcode.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/*
 * Check whether the original sequence org can be uniquely reconstructed from the sequences in seqs. The org sequence is a permutation 
 * of the integers from 1 to n, with 1 ≤ n ≤ 10^4. Reconstruction means building a shortest common supersequence of the sequences in seqs 
 * (i.e., a shortest sequence so that all sequences in seqs are subsequences of it). Determine whether there is only one sequence that can 
 * be reconstructed from seqs and it is the org sequence.
 * Example 1:
 * 	Input: org: [1,2,3], seqs: [[1,2],[1,3]] Output: false
 * 	Explanation: [1,2,3] is not the only one sequence that can be reconstructed, because [1,3,2] is also a valid sequence that can be 
 * 	reconstructed.
 * Example 2:
 * 	Input: org: [1,2,3], seqs: [[1,2]] Output: false
 * 	Explanation: The reconstructed sequence can only be [1,2].
 * Example 3:
 * 	Input: org: [1,2,3], seqs: [[1,2],[1,3],[2,3]] Output: true
 * 	Explanation: The sequences [1,2], [1,3], and [2,3] can uniquely reconstruct the original sequence [1,2,3].
 * Example 4:
 * 	Input: org: [4,1,5,2,6,3], seqs: [[5,2,6,3],[4,1,5,2]] Output: true
 * 
 * Company: Google
 */
public class SequenceReconstruction {
    /*
     * Hamiltonian path
     * https://www.cs.sfu.ca/~ggbaker/zju/math/euler-ham.html#ham
     */

    // BFS
    public boolean sequenceReconstruction_bfs(int[] org, List<List<Integer>> seqs) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        Map<Integer, Integer> inDegrees = new HashMap<>();
        for (List<Integer> seq : seqs) {
            for (int i = 0; i < seq.size(); i++) {
                int v = seq.get(i);
                graph.putIfAbsent(v, new ArrayList<>());
                inDegrees.putIfAbsent(v, 0); // set to 0 only if the vertex
                                             // never appears before, it may be
                                             // on other's adjacent list
                if (i > 0) {
                    graph.get(seq.get(i - 1)).add(v);
                    inDegrees.put(v, inDegrees.getOrDefault(v, 0) + 1);
                }
            }
        }

        if (org.length != inDegrees.size())
            return false;

        Queue<Integer> queue = new LinkedList<>();
        for (Map.Entry<Integer, Integer> entry : inDegrees.entrySet()) {
            if (entry.getValue() == 0)
                queue.offer(entry.getKey());
        }

        int index = 0;
        while (!queue.isEmpty()) {
            if (queue.size() > 1)
                return false; // there will be multiple answers when queue size
                              // is greater than 1
            int current = queue.poll();
            if (org[index++] != current)
                return false;

            for (int neighbor : graph.get(current)) {
                inDegrees.put(neighbor, inDegrees.get(neighbor) - 1);
                if (inDegrees.get(neighbor) == 0)
                    queue.offer(neighbor);
            }
        }

        return index == org.length;
    }

    public static void main(String[] args) {
        SequenceReconstruction sr = new SequenceReconstruction();
        int[] org = { 1, 2, 3 };
        List<List<Integer>> seqs = Arrays.asList(Arrays.asList(1, 2), Arrays.asList(1, 3), Arrays.asList(2, 3));
        System.out.println(sr.sequenceReconstruction_bfs(org, seqs));
    }
}
