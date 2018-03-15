package leetcode.graph;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Stack;

/*
 * LEETCODE 332
 * Given a list of airline tickets represented by pairs of departure and 
 * arrival airports [from, to], reconstruct the itinerary in order. All 
 * of the tickets belong to a man who departs from JFK. Thus, the itinerary 
 * must begin with JFK. 
 * Note: 
 * - If there are multiple valid itineraries, you should return the itinerary 
 * that has the smallest lexical order when read as a single string. For example, 
 * the itinerary ["JFK", "LGA"] has a smaller lexical order than ["JFK", "LGB"].
 * - All airports are represented by three capital letters (IATA code).
 * - You may assume all tickets form at least one valid itinerary. -> 
 * suggests that a Eulerian path exists.
 * Example 1:
 * tickets = [["MUC", "LHR"], ["JFK", "MUC"], ["SFO", "SJC"], ["LHR", "SFO"]]
 * Return ["JFK", "MUC", "LHR", "SFO", "SJC"].
 * Example 2:
 * tickets = [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
 * Return ["JFK","ATL","JFK","SFO","ATL","SFO"].
 * Another possible reconstruction is ["JFK","SFO","ATL","JFK","ATL","SFO"]. But it 
 * is larger in lexical order. 
 * 
 * Company: Google
 * Difficulty: medium
 */
public class ReconstructItinerary {
    /*
     * Eulerian Path: a path that visits every EDGE exactly once
     * https://en.wikipedia.org/wiki/Eulerian_path
     * https://www.cs.sfu.ca/~ggbaker/zju/math/euler-ham.html
     * 
     * A directed graph has an Eulerian trail if and only if at most one vertex
     * has (out-degree) − (in-degree) = 1, at most one vertex has (in-degree) −
     * (out-degree) = 1, every other vertex has equal in-degree and out-degree,
     * and all of its vertices with nonzero degree belong to a single connected
     * component of the underlying undirected graph. 
     * Hierholzer's algorithm: 
     * - Choose any starting vertex v, and follow a trail of edges from that
     * vertex until returning to v. It is not possible to get stuck at any
     * vertex other than v, because the even degree of all vertices ensures
     * that, when the trail enters another vertex w there must be an unused edge
     * leaving w. The tour formed in this way is a closed tour, but may not
     * cover all the vertices and edges of the initial graph. 
     * - As long as there exists a vertex u that belongs to the current tour but 
     * that has adjacent edges not part of the tour, start another trail from u, 
     * following unused edges until returning to u, and join the tour formed in this 
     * way to the previous tour. 
     * The idea is to keep following unused edges and removing them until we get stuck. 
     * Once we get stuck, we back-track to the nearest vertex in our current path that 
     * has unused edges, and we repeat the process until all the edges have been used
     */

    /*
     * In this problem, the path we are going to find is an itinerary which: 
     * - uses all tickets to travel among airports 
     * - preferably in ascending lexical order of airport code 
     * We start by building a graph and then sorting vertices in the adjacency 
     * list so that when we traverse the graph later, we can guarantee the lexical 
     * order of the itinerary can be as good as possible. 
     * When we have generated an itinerary, we check if we have used all our airline 
     * tickets. If not, we revert the change and try another ticket. We keep trying 
     * until we have used all our tickets.
     */
    public List<String> findItinerary_recursive(String[][] tickets) {
        // use min-heap to represent adjacent list so that we always visit
        // the smallest neighbor first
        Map<String, PriorityQueue<String>> graph = new HashMap<>();
        for (String[] ticket : tickets) {
            graph.putIfAbsent(ticket[0], new PriorityQueue<>());
            graph.get(ticket[0]).add(ticket[1]);
        }

        LinkedList<String> result = new LinkedList<>();
        // start from "JFK", apply Hierholzer's algorithm to find a Eulerian
        // path in the graph which is a valid reconstruction.
        dfs(graph, result, "JFK");
        return result;
    }

    private void dfs(Map<String, PriorityQueue<String>> graph, LinkedList<String> path, String from) {
        // base case, from is not the starting point of any itinerary, we have
        // reached end point
        PriorityQueue<String> toAirports = graph.get(from);
        // use while loop as we need to try all cities
        while (toAirports != null && !toAirports.isEmpty()) {
            dfs(graph, path, toAirports.poll());
        }
        path.addFirst(from);
    }
    
    public List<String> findItinerary_iterative(String[][] tickets) {
        // use min-heap to represent adjacent list so that we always visit
        // the smallest neighbor first
        Map<String, PriorityQueue<String>> graph = new HashMap<>();
        for (String[] ticket : tickets) {
            graph.computeIfAbsent(ticket[0], k -> new PriorityQueue<>()).add(ticket[1]);
        }

        LinkedList<String> result = new LinkedList<>();
        Stack<String> stack = new Stack<>();
        stack.push("JFK");
        while (!stack.isEmpty()) {
            //  keep going forward until you get stuck.
            // need to check stack.peek() in the loop as the stack is changed inside the loop
            while (graph.containsKey(stack.peek()) && !graph.get(stack.peek()).isEmpty()) {
                stack.push(graph.get(stack.peek()).poll());
            }
            result.addFirst(stack.pop()); // writing down the path backwards
        }
        return result;
    }
    
    public List<String> findItinerary_outputTickets(String[][] tickets) {
        // use min-heap to represent adjacent list so that we always visit
        // the smallest neighbor first
        Map<String, PriorityQueue<String>> graph = new HashMap<>();
        for (String[] ticket : tickets) {
            graph.computeIfAbsent(ticket[0], k -> new PriorityQueue<>()).add(ticket[1]);
        }

        LinkedList<String> result = new LinkedList<>();
        Stack<String> stack = new Stack<>();
        stack.push("JFK");
        while (!stack.isEmpty()) {
            //  keep going forward until you get stuck.
            // need to check stack.peek() in the loop as the stack is changed inside the loop
            while (graph.containsKey(stack.peek()) && !graph.get(stack.peek()).isEmpty()) {
                String src = stack.peek();
                String dest = graph.get(src).poll();
                result.add(src + "->" + dest);
                stack.push(dest);
            }
            stack.pop();
            //result.addFirst(stack.pop()); // writing down the path backwards
        }
        return result;
    }

    public static void main(String[] args) {
        ReconstructItinerary ri = new ReconstructItinerary();
        String[][] tickets = {
                {"MUC","LHR"},
                {"JFK","MUC"},
                {"SFO","SJC"},
                {"LHR","SFO"}
                
        };
        System.out.println(ri.findItinerary_iterative(tickets));
    }
}
