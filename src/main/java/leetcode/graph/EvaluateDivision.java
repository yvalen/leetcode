package leetcode.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/*
 * LEETCODE 399
 * Equations are given in the format A / B = k, where A and B are variables represented as strings, 
 * and k is a real number (floating point number). Given some queries, return the answers. If the 
 * answer does not exist, return -1.0.
 * Example: 
 * Given a / b = 2.0, b / c = 3.0. 
 * queries are: a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ? .
 * return [6.0, 0.5, -1.0, 1.0, -1.0 ].
 * The input is: vector<pair<string, string>> equations, vector<double>& values, vector<pair<string, string>> queries , 
 * where equations.size() == values.size(), and the values are positive. 
 * This represents the equations. Return vector<double>.
 * According to the example above:
 * 	equations = [ ["a", "b"], ["b", "c"] ],
 * 	values = [2.0, 3.0],
 * 	queries = [ ["a", "c"], ["b", "a"], ["a", "e"], ["a", "a"], ["x", "x"] ]. 
 * The input is always valid. You may assume that evaluating the queries will result in no division 
 * by zero and there is no contradiction. 
 * 
 * Company: Google
 * Difficulty: medium
 */
public class EvaluateDivision {
    public double[] calcEquation(String[][] equations, double[] values, String[][] queries) {
        Map<String, List<String>> graph = new HashMap<>();
        Map<String, List<Double>> valueMap = new HashMap<>();
        for (int i = 0; i < equations.length; i++) {
            String[] equation = equations[i];
            graph.putIfAbsent(equation[0], new ArrayList<>());
            graph.putIfAbsent(equation[1], new ArrayList<>());
            graph.get(equation[0]).add(equation[1]);
            graph.get(equation[1]).add(equation[0]);
            valueMap.putIfAbsent(equation[0], new ArrayList<>());
            valueMap.putIfAbsent(equation[1], new ArrayList<>());
            // entry in valueMap list corresponds the value of the element in the adj list
            valueMap.get(equation[0]).add(values[i]);
            valueMap.get(equation[1]).add(1 / values[i]);
        }

        double[] result = new double[queries.length];
        for (int i = 0; i < queries.length; i++) {
            result[i] = dfs(queries[i][0], queries[i][1], graph, valueMap, new HashSet<>(), 1.0);
        }

        return result;
    }

    private double dfs(String start, String end, Map<String, List<String>> graph, 
            Map<String, List<Double>> valueMap, Set<String> visited, double value) {
        if (!graph.containsKey(start)) return -1.0;

        if (start.equals(end)) return value;

        visited.add(start);
        List<String> adjList = graph.get(start);
        List<Double> valueList = valueMap.get(start);
        for (int i = 0; i < adjList.size(); i++) {
            // this is undirected graph, we need to use visited to track the nodes that has been processed
            // and only process unvisited nodes to avoid infinite loop
            if (!visited.contains(adjList.get(i))) {
                double val = dfs(adjList.get(i), end, graph, valueMap, visited, value * valueList.get(i));
                if (val != -1.0) { // find path
                    return val;
                }
            }
        }
        return -1.0;
    }

    public static void main(String[] args) {
        EvaluateDivision evalDiv = new EvaluateDivision();
        String[][] equations = { { "a", "b" }, { "b", "c" } };
        double[] values = { 2.0, 3.0 };
        //String[][] queries = { { "a", "c" }, { "b", "c" }, { "a", "e" }, { "a", "a" }, { "x", "x" } };
        String[][] queries = {  { "x", "x" } };
        double[] result = evalDiv.calcEquation(equations, values, queries);
        for (double r : result) {
            System.out.print(r + ", ");
        }
    }
}
