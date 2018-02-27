package company.airbnb;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/*
 * Given preference list for [[3, 5, 7, 9], [2, 3, 8], [5, 8]]
 * Return the overall preference list: [2, 3, 5, 8, 7, 9]
 * 
 * topological sort
 */
public class PreferenceList {
    public List<Integer> getPreference_bfs(List<List<Integer>> preferences) {
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        Map<Integer, Integer> inDegrees = new HashMap<>();
        for (List<Integer> preference : preferences) {
            for (int i = 0; i < preference.size()-1; i++) {
                Integer current = preference.get(i), next = preference.get(i+1);
                inDegrees.putIfAbsent(current, 0);
                inDegrees.putIfAbsent(next, 0);
                inDegrees.put(next, inDegrees.get(next)+1);
                graph.putIfAbsent(current, new HashSet<>());
                graph.get(current).add(next);
            }
        }
        
        Queue<Integer> queue = new LinkedList<>();
        for (Map.Entry<Integer, Integer> entry : inDegrees.entrySet()) {
            if (entry.getValue() == 0) queue.offer(entry.getKey());
        }
        
        List<Integer> result = new ArrayList<>();
        while (!queue.isEmpty()) {
            int current = queue.poll();
            result.add(current);
            if (graph.containsKey(current)) {
                for (int neighbor : graph.get(current)) {
                    inDegrees.put(neighbor, inDegrees.get(neighbor)-1);
                    if (inDegrees.get(neighbor) == 0) queue.offer(neighbor);
                }
            }
        }
        
        return result;
    }
    
    public List<Integer> getPreference_dfs(List<List<Integer>> preferences) {
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        for (List<Integer> preference : preferences) {
            for (int i = 0; i < preference.size()-1; i++) {
                Integer current = preference.get(i), next = preference.get(i+1);
                graph.putIfAbsent(current, new HashSet<>());
                graph.get(current).add(next);
            }
        }
        
        List<Integer> result = new ArrayList<>();
        Set<Integer> visited = new HashSet<>();
        for (Integer current : graph.keySet()) {
            if (!visited.contains(current)) {
                dfs(graph, current, visited, result);
            }
        }
        Collections.reverse(result);
        return result;
        
    }
    
    private void dfs(Map<Integer, Set<Integer>> graph, Integer current, Set<Integer> visited, List<Integer> result) {
        if (graph.containsKey(current)) {
            for (int neighbor : graph.get(current)) {
                if (visited.contains(neighbor)) continue;
                dfs(graph, neighbor, visited, result);
            }
        }
        visited.add(current);
        result.add(current);
    }

    
    public static void main(String[] args) {
        PreferenceList pl = new PreferenceList();
        List<List<Integer>> preferences = Arrays.asList(
                    Arrays.asList(3,5,7,9),
                    Arrays.asList(2,3,8),
                    Arrays.asList(5,8)
                );
        System.out.println(pl.getPreference_dfs(preferences));
    }
}
